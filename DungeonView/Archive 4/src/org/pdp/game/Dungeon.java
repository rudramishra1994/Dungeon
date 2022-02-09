package org.pdp.game;

import org.pdp.random.IRandom;
import org.pdp.random.ProdRandom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;


/**
 * Represents a dungeon which a player will traverse collecting treasures on the way.
 */
public class Dungeon implements IDungeon {


  private static final int additionalCavesWithTreasure = 10;
  private static final int treasureLLimit = 1;
  private static final int treasureHLimit = 3;
  private List<List<ILocation>> dungeon;
  private List<List<ILocation>> backUpDungeon;
  private int rows;
  private int columns;
  private IPlayer player;
  private final int interconnectivity;
  private final double percentageCaveWithTreasure;
  private final int type;
  private ILocation startILocation;
  private ILocation endILocation;
  private static IRandom random;
  private final int monsters;
  private static final int MIN_DISTANCE = 5;
  private static final int MONSTER_FAR = 2;
  private static final int MONSTER_NEAR = 1;
  private static final int MAX_HITS = 2;
  private static final int MAX_DISTANCEARROW = 5;
  private static final int MIN_DISTANCEARROW = 1;


  /**
   * creates a dungeon which a player will traverse and collect treasures.
   *
   * @param row               number of rows.
   * @param col               number of columns
   * @param type              type of dungeon wrapping(1) or unwrapping(2).
   * @param interconnectivity number of paths between two ILocation in the dungeon.
   * @param cavePercentage    the percentage of caves with treasures
   * @param name              name of the player
   * @param random            a random number generator.
   */
  private Dungeon(int row, int col, int type, int interconnectivity,
                  IRandom random, double cavePercentage, String name, int monsters) {
    if (random == null) {
      throw new IllegalArgumentException("Randomizer cannot be null.");
    }
    if (name == null) {
      throw new IllegalArgumentException("Player name cannot be null");
    }
    if (row < 5 || col < 5 || interconnectivity < 0) {
      throw new IllegalArgumentException("Length or breadth cannot be less than 5 and  "
        + "Interconnectivity of the dungeon cannot be less than 0.");
    }

    if (type < 1 || type > 2) {
      throw new IllegalArgumentException("Invalid type. Select 1 "
        + "For Wrapping Dungeon and 2 For Non Wrapping dungeon");
    }

    if (monsters < 1) {
      throw new IllegalArgumentException("At least one monster required for the game.");
    }
    this.dungeon = new ArrayList<>(row);
    for (int i = 0; i < row; i++) {
      dungeon.add(new ArrayList<>(col));
    }
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        dungeon.get(i).add(new Location(i, j));
      }
    }
    this.rows = row;
    this.columns = col;
    this.type = type;
    this.interconnectivity = interconnectivity;
    this.percentageCaveWithTreasure = cavePercentage;
    this.monsters = monsters;
    Dungeon.random = random;
    initialization();
    createPlayer(name);
    this.player.updatePlayerLocation(this.startILocation);
    this.backUpDungeon = getDungeon();
  }

  public static DungeonBuilder getBuilder() {
    return new DungeonBuilder();
  }

  /**
   * This is a class responsible for building our dungeon.
   * Depending on the input from the user the dungeon will be build with a
   * combination of user given and default parameters.
   */
  public static class DungeonBuilder {
    private static final int DEFAULT_ROW = 6;
    private static final int DEFAULT_COLUMN = 6;
    private static final int DEFAULT_INTERCONNECTIVITY = 4;
    private static final double DEFAULT_TREASURE = 0.2;
    private static final int DEFAULT_TYPE = 1;
    private static final IRandom DEFAULT_RANDOMIZER = new ProdRandom();
    private static final int DEFAULT_MONSTERS = 1;
    private static final String PLAYER_NAME = "JOHN WICK";

    private int row;
    private int col;
    private int interconnectivity;
    private double percentageTreasure;
    private int type;
    private String playerName;
    private IRandom random;
    private int numOfMonsters;

    /**
     * Setter method for number of monsters in the dungeon.
     *
     * @param numOfMonsters number of monsters.
     * @return DungeonBuilder object.
     */
    public DungeonBuilder setNumOfMonsters(int numOfMonsters) {
      this.numOfMonsters = numOfMonsters;
      return this;
    }

    /**
     * Setter method for the randomizer used by the dungeon.
     *
     * @param random IRandom instance.
     * @return DungeonBuilder object.
     */
    public DungeonBuilder setRandom(IRandom random) {
      this.random = random;
      return this;
    }

    /**
     * Setter method to set the player name.
     *
     * @param playerName player name.
     * @return DungeonBuilder object.
     */
    public DungeonBuilder setPlayerName(String playerName) {
      this.playerName = playerName;
      return this;
    }

    /**
     * Setter method for the row of the dungeon.
     *
     * @param row row of the dungeon.
     * @return DungeonBuilder object.
     */
    public DungeonBuilder setRow(int row) {
      this.row = row;
      return this;
    }

    /**
     * Setter method for the column of the dungeon.
     *
     * @param col column of the dungeon.
     * @return DungeonBuilder object.
     */
    public DungeonBuilder setCol(int col) {
      this.col = col;
      return this;
    }

    /**
     * Setter method for the interconnectivity of the dungeon.
     *
     * @param interconnectivity interconnectivity of the dungeon.
     * @return DungeonBuilder object.
     */
    public DungeonBuilder setInterconnectivity(int interconnectivity) {
      this.interconnectivity = interconnectivity;
      return this;
    }

    /**
     * Setter method for the minimum caves of the dungeon with treasure.
     *
     * @param percentageTreasure minimum caves with treasure in the dungeon.
     * @return DungeonBuilder object.
     */
    public DungeonBuilder setPercentageTreasure(double percentageTreasure) {
      this.percentageTreasure = percentageTreasure;
      return this;
    }

    /**
     * Setter method for the minimum caves of the dungeon with treasure.
     *
     * @param type type of dungeon wrapping or non-wrapping
     * @return DungeonBuilder object.
     */
    public DungeonBuilder setType(int type) {
      this.type = type;
      return this;
    }

    /**
     * Creates a DungeonBuilder object used to set the parameter of the dungeon.
     */
    private DungeonBuilder() {
      this.interconnectivity = DEFAULT_INTERCONNECTIVITY;
      this.row = DEFAULT_ROW;
      this.col = DEFAULT_COLUMN;
      this.percentageTreasure = DEFAULT_TREASURE;
      this.type = DEFAULT_TYPE;
      this.random = DEFAULT_RANDOMIZER;
      this.numOfMonsters = DEFAULT_MONSTERS;
      this.playerName = PLAYER_NAME;
    }

    /**
     * Builds the dungeon.
     *
     * @return Dungeon object.
     */
    public Dungeon build() {
      return new Dungeon(row, col, type, interconnectivity, random, percentageTreasure,
        playerName, numOfMonsters);
    }

  }


  void createPlayer(String name) {
    this.player = new Player(name);
  }

  @Override
  public List<Integer> getStartLocation() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(this.startILocation.getXCoordinate());
    coordinates.add(this.startILocation.getYCoordinate());
    return coordinates;
  }

  @Override
  public List<Integer> getEndLocation() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(this.endILocation.getXCoordinate());
    coordinates.add(this.endILocation.getYCoordinate());
    return coordinates;
  }

  @Override
  public boolean movePlayer(int row, int col) {
    int drow = this.dungeon.size();
    int dcol = this.dungeon.get(0).size();
    ILocation currLocation = this.player.getPlayerLocation();
    for (Direction d : currLocation.getAllowedDirectionToMove().keySet()) {
      if (currLocation.getAllowedDirectionToMove().get(d)) {
        int nrow = (currLocation.getXCoordinate() + d.dx + drow) % drow;
        int ncol = (currLocation.getYCoordinate() + d.dy + dcol) % dcol;
        if (nrow == row && ncol == col) {
          return movePlayer(d);
        }
      }
    }
    return false;

  }

  @Override
  public boolean movePlayer(Direction d) {
    if (d == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }
    ILocation l = this.player.getPlayerLocation();
    int row = this.dungeon.size();
    int col = this.dungeon.get(0).size();
    if (l.getAllowedDirectionToMove().get(d)) {
      int nx = (l.getXCoordinate() + d.dx + row) % row;
      int ny = (l.getYCoordinate() + d.dy + col) % col;
      ILocation newPlayerLocation = this.dungeon.get(nx).get(ny);
      newPlayerLocation.setVisited(true);
      this.player.updatePlayerLocation(newPlayerLocation);
      this.player.getPlayerLocation();
      if (player.getPlayerLocation().getMonsters().size() > 0) {
        if (player.getPlayerLocation().getMonsters().get(0).getHits() < 1) {
          this.player.setPlayerStatus(false);
        } else if (player.getPlayerLocation().getMonsters().get(0).getHits() == 1) {
          this.player.setPlayerStatus(random.getRandomInteger(0, 1) == 0 ? false : true);
        } else {
          this.player.setPlayerStatus(true);
        }
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int distanceBetweenStartAndEnd() {
    return distanceBetweenTwoNodes(this.startILocation, this.endILocation);
  }


  private int distanceBetweenTwoNodes(ILocation source, ILocation destination) {
    if (source == null || destination == null) {
      throw new IllegalArgumentException("Source or Destination cannot be null");
    }
    Qnode qnode = new Qnode(source, 0);
    Queue<Qnode> queue = new LinkedList<>();
    queue.add(qnode);
    int row = this.dungeon.size();
    int col = this.dungeon.get(0).size();
    Set<Qnode> visited = new HashSet<>();
    while (queue.size() > 0) {
      Qnode q = queue.remove();
      if (q.getLocation().equals(destination)) {
        return q.getHeight();
      }
      visited.add(q);
      Map<Direction, Boolean> dMap = q.getLocation().getAllowedDirectionToMove();
      for (Direction d : dMap.keySet()) {
        if (dMap.get(d)) {
          int nx = (q.getLocation().getXCoordinate() + d.dx + row) % row;
          int ny = (q.getLocation().getYCoordinate() + d.dy + col) % col;
          Qnode child = new Qnode(this.dungeon.get(nx).get(ny), q.getHeight() + 1);
          if (!visited.contains(child)) {
            queue.add(child);
          }
        }
      }
    }
    return 0;
  }

  @Override
  public Arrowtracer shootArrow(int distance, Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Invalid direction.Direction cannot be null");
    }
    if (distance < MIN_DISTANCEARROW || distance > MAX_DISTANCEARROW) {
      throw new IllegalArgumentException("Distance of the arrow has to be between 1 and 5,"
        + " both inclusive");
    }
    ILocation arrowCurrLocation = this.player.getPlayerLocation();
    Arrowtracer trace = new Arrowtracer();
    this.player.shootArrow();
    int row = this.dungeon.size();
    int col = this.dungeon.get(0).size();
    while (distance > 0) {
      int nx = (arrowCurrLocation.getXCoordinate() + direction.dx + row) % row;
      int ny = (arrowCurrLocation.getYCoordinate() + direction.dy + col) % col;

      if (!arrowCurrLocation.getAllowedDirectionToMove().get(direction)) {
        trace.setHits(0);
        return trace;
      }
      trace.setDirectionList(direction);
      arrowCurrLocation = this.dungeon.get(nx).get(ny);
      if (isCave(arrowCurrLocation)) {
        distance--;
      } else {
        for (Direction d : arrowCurrLocation.getAllowedDirectionToMove().keySet()) {
          if (!d.equals(direction.opposite) && arrowCurrLocation.getAllowedDirectionToMove()
              .get(d)) {
            direction = d;
            break;
          }
        }

      }
    }
    if (arrowCurrLocation.getMonsters().size() > 0) {
      arrowCurrLocation.updateHit();
      int hits = arrowCurrLocation.getMonsters().get(0).getHits();
      if (hits == MAX_HITS) {
        arrowCurrLocation.removeMonster();
      }
      trace.setHits(hits);
      return trace;
    }
    return trace;
  }

  @Override
  public Map<Treasures, Integer> getPlayerDescription() {
    return this.player.getPlayerDescription();
  }

  @Override
  public Map<Weapon, Integer> getPlayerWeaponCollected() {
    return this.player.getPlayerWeapon();
  }

  @Override
  public List<Monster> getMonsterCurrentLocation() {
    return this.player.getPlayerLocation().getMonsters();
  }

  @Override
  public List<Monster> getMonsterAtEndLocation() {
    return this.endILocation.getMonsters();
  }


  @Override
  public Map<Weapon, Integer> getLocationWeapon() {
    return this.player.getPlayerLocation().getWeaponList();
  }

  @Override
  public Map<Treasures, Integer> getCurrentLocationTreasures() {
    return this.player.getPlayerLocation().getTreasureList();
  }

  @Override
  public Map<Direction, Boolean> getCurrentLocationAllowedDirection() {
    return this.player.getPlayerLocation().getAllowedDirectionToMove();
  }

  @Override
  public List<Integer> getCurrentLocation() {
    List<Integer> coordinates = new ArrayList<>();
    coordinates.add(this.player.getPlayerLocation().getXCoordinate());
    coordinates.add(this.player.getPlayerLocation().getYCoordinate());
    return coordinates;
  }

  @Override
  public boolean pickTreasure() {
    return this.player.gatherTreasure();
  }

  @Override
  public boolean pickArrow() {

    return this.player.gatherWeapon();
  }

  @Override
  public ILocation getPlayerCurrentLocation() {
    return new Location(this.player.getPlayerLocation());
  }

  @Override
  public IPlayer getPlayer() {
    return new Player(this.player);
  }


  private void initialization() {
    List<Edge> edges = getListOfEdges();
    createMazeUsingKrushkalAlgorithm(edges);
    distributeTreasures();
    assignStartAndEndILocation();
    if (getListOfCaves().size() > this.monsters) {
      distributeMonsters();
    } else {
      throw new IllegalArgumentException("Chosen difficulty is not possible in the current "
        + "dungeon");
    }
    distributeArrow();

  }

  @Override
  public Smell getSmellIntensity(ILocation source) {
    //ILocation source = this.player.getPlayerLocation();
    if (source == null) {
      throw new IllegalArgumentException("Location cannot be null to determine smell");
    }
    if (source.getMonsters().size() > 0) {
      return Smell.HEAVY;
    }
    Qnode qnode = new Qnode(source, 0);
    Queue<Qnode> queue = new LinkedList<>();
    queue.add(qnode);
    int monsterCount = 0;
    int row = this.dungeon.size();
    int col = this.dungeon.get(0).size();
    Set<Qnode> visited = new HashSet<>();
    while (queue.size() > 0) {
      Qnode q = queue.remove();
      visited.add(q);
      if (q.getHeight() == MONSTER_NEAR && q.getLocation().getMonsters().size() > 0) {
        return Smell.HEAVY;
      } else if (q.getHeight()
          == MONSTER_FAR && q.getLocation().getMonsters().size() > 0) {
        monsterCount++;
      } else if (q.getHeight() > MONSTER_FAR) {
        break;
      }
      Map<Direction, Boolean> dMap = q.getLocation().getAllowedDirectionToMove();
      for (Direction d : dMap.keySet()) {
        if (dMap.get(d)) {
          Qnode child =
              new Qnode(this.dungeon.get((q.getLocation().getXCoordinate() + d.dx + row) % row)
              .get((q.getLocation().getYCoordinate() + d.dy + col) % col), q.getHeight() + 1);
          if (!visited.contains(child)) {
            queue.add(child);
          }
        }
      }
    }
    if (monsterCount > 1) {
      return Smell.HEAVY;
    } else if (monsterCount == 1) {
      return Smell.LIGHT;
    } else {
      return Smell.NOSMELL;
    }

  }

  @Override
  public boolean isPlayerAlive() {
    return this.player.isAlive();
  }

  @Override
  public boolean hasReachedGoal() {
    return this.player.getPlayerLocation().equals(this.endILocation);
  }

  private void assignStartAndEndILocation() {
    List<ILocation> listOfCaves = getListOfCaves();
    this.startILocation = listOfCaves.get(random.getRandomInteger(0, listOfCaves.size() - 1));
    this.startILocation.setVisited(true);
    listOfCaves.remove(this.startILocation);
    do {
      this.endILocation = listOfCaves.get(random.getRandomInteger(0, listOfCaves.size() - 1));
      listOfCaves.remove(this.endILocation);
    }
    while (distanceBetweenTwoNodes(startILocation, endILocation) < MIN_DISTANCE
      && listOfCaves.size() > 0);

  }


  private void createMazeUsingKrushkalAlgorithm(List<Edge> list) {
    List<Edge> leftOverEdges = new ArrayList<>();
    Set<Set<ILocation>> setOfSetILocations = createSetForEachILocation();
    int row = this.dungeon.size();
    int col = this.dungeon.get(0).size();
    int srcX;
    int srcY;
    int destX;
    int destY;
    while (list.size() > 0) {
      Edge randomEdge = list.get(random.getRandomInteger(0, list.size() - 1));
      list.remove(randomEdge);
      //source ILocation coordinates.
      srcX = randomEdge.getSrcX();
      srcY = randomEdge.getSrcY();
      //destination ILocation coordinates.
      destX = (srcX + randomEdge.getDirection().dx + row) % row;
      destY = (srcY + randomEdge.getDirection().dy + col) % col;
      ILocation src = dungeon.get(srcX).get(srcY);
      ILocation dest = dungeon.get(destX).get(destY);

      Set<ILocation> srcSet = getILocationSet(setOfSetILocations, src);
      Set<ILocation> desSet = getILocationSet(setOfSetILocations, dest);
      if (srcSet != null && desSet != null) {
        if (!srcSet.equals(desSet)) {
          Set<ILocation> mergedSet = new HashSet<>();
          mergeSets(mergedSet, srcSet, desSet, setOfSetILocations, src, dest, randomEdge);
        } else {
          leftOverEdges.add(randomEdge);
        }
      }
    }
    if (leftOverEdges.size() < interconnectivity) {
      throw new IllegalArgumentException("Grid size too small to accommodate given "
        + "interconnectivity. Please Increase the size of  the maze.");
    }
    int counter = 0;
    while (counter < interconnectivity) {
      Edge randomEdge = leftOverEdges.get(random.getRandomInteger(0, leftOverEdges.size() - 1));
      ILocation src = dungeon.get(randomEdge.getSrcX()).get(randomEdge.getSrcY());
      ILocation dest = dungeon.get((randomEdge.getSrcX()
          + randomEdge.getDirection().dx + row) % row).get((randomEdge.getSrcY()
          + randomEdge.getDirection().dy + col) % col);

      Set<ILocation> srcSet = getILocationSet(setOfSetILocations, src);
      Set<ILocation> desSet = getILocationSet(setOfSetILocations, dest);

      Set<ILocation> mergedSet = new HashSet<>();
      if (srcSet != null && desSet != null) {
        mergeSets(mergedSet, srcSet, desSet, setOfSetILocations, src, dest, randomEdge);
      }

      leftOverEdges.remove(randomEdge);
      counter++;
    }

  }

  private void mergeSets(Set<ILocation> mergedSet, Set<ILocation> srcSet, Set<ILocation> desSet,
                         Set<Set<ILocation>> setOfSetILocations, ILocation src,
                         ILocation dest, Edge randomEdge) {
    Stream.of(srcSet, desSet).forEach(mergedSet::addAll);
    setOfSetILocations.remove(srcSet);
    setOfSetILocations.remove(desSet);
    setOfSetILocations.add(mergedSet);
    src.setAllowedDirection(randomEdge.getDirection());
    dest.setAllowedDirection(randomEdge.getDirection().opposite);
  }


  private Set<ILocation> getILocationSet(Set<Set<ILocation>> setOfSetlocations,
                                         ILocation location) {
    for (Set<ILocation> list : setOfSetlocations) {
      if (list.contains(location)) {
        return list;
      }
    }
    return null;
  }

  private void distributeMonsters() {
    List<ILocation> listOfCaves = getListOfCaves();
    int counter = this.monsters;
    if (this.monsters > listOfCaves.size() - 1) {
      throw new IllegalArgumentException("The dungeon cannot accommodate monsters.Increase size");
    }
    this.endILocation.setMonster(new Otyugh());//set monster in the end ILocation.
    counter--;
    listOfCaves.remove(this.endILocation);
    listOfCaves.remove(startILocation); // monster cannot be in the start ILocation.
    while (counter > 0 && listOfCaves.size() > 0) {
      int caveWithMonster = random.getRandomInteger(0, listOfCaves.size() - 1);
      listOfCaves.get(caveWithMonster).setMonster(new Otyugh());
      listOfCaves.remove(caveWithMonster);
      counter--;
    }
  }

  private void distributeArrow() {
    List<ILocation> allLocation = getAllLocation();
    int numberOfLocation = (int) Math.round(getAllLocation().size()
        * this.percentageCaveWithTreasure);
    while (numberOfLocation > 0) {
      int locationWithArrow = random.getRandomInteger(0, allLocation.size() - 1);
      allLocation.get(locationWithArrow).setWeapon(Weapon.ARROW);
      allLocation.remove(locationWithArrow);
      numberOfLocation--;
    }
    //additional arrows;
    allLocation = getAllLocation();
    numberOfLocation = 0;
    while (numberOfLocation < additionalCavesWithTreasure && allLocation.size() > 0) {
      int locationWithArrow = random.getRandomInteger(0, allLocation.size() - 1);
      allLocation.get(locationWithArrow).setWeapon(Weapon.ARROW);
      allLocation.remove(locationWithArrow);
      numberOfLocation++;
    }

  }

  private Set<Set<ILocation>> createSetForEachILocation() {
    Set<Set<ILocation>> setsOfLocation = new HashSet<>();
    for (List<ILocation> list : this.dungeon) {
      for (ILocation location : list) {
        Set<ILocation> locationSet = new HashSet<>();
        locationSet.add(location);
        setsOfLocation.add(locationSet);
      }
    }
    return setsOfLocation;
  }

  @Override
  public boolean isCave(ILocation location) {
    if (location == null) {
      throw new IllegalArgumentException("Null cannot be a cave");
    }
    long exits = location.getAllowedDirectionToMove()
        .values().stream().filter(p -> p).count();
    return exits != 2;
  }


  @Override
  public void resetDungeon() {
    this.dungeon = backUpDungeon;
    this.startILocation =
      backUpDungeon.get(startILocation.getXCoordinate()).get(startILocation.getYCoordinate());
    this.endILocation =
      backUpDungeon.get(endILocation.getXCoordinate()).get(endILocation.getYCoordinate());
    this.player.updatePlayerLocation(startILocation);
    this.player.resetPlayer();
  }

  private void distributeTreasures() {
    List<ILocation> listOfCaves = getListOfCaves();
    int cavesWithTreasure = (int) Math.round(this.percentageCaveWithTreasure * listOfCaves.size());
    Set<ILocation> cavesAllocatedTreasure = new HashSet<>();
    //Allocate first minimum 20 percent caves with treasure.
    while (cavesAllocatedTreasure.size() < cavesWithTreasure) {
      listOfCaves.remove(distributeTreasureHelper(cavesAllocatedTreasure, listOfCaves));
    }
    listOfCaves = getListOfCaves();
    int counter = 0;
    //Allocate some additional caves with treasure.
    while (counter < additionalCavesWithTreasure) {
      distributeTreasureHelper(cavesAllocatedTreasure, listOfCaves);
      counter++;
    }

  }

  private int distributeTreasureHelper(Set<ILocation> cavesAllocatedTreasure,
                                       List<ILocation> listOfCaves) {
    int caveIndex = Dungeon.random.getRandomInteger(0, listOfCaves.size() - 1);
    int treasureIndex = Dungeon.random.getRandomInteger(treasureLLimit, treasureHLimit);
    ILocation l = listOfCaves.get(caveIndex);
    l.setTreasureInLocation(treasureAllocator(treasureIndex));
    cavesAllocatedTreasure.add(listOfCaves.get(caveIndex));
    return caveIndex;
  }


  private Treasures treasureAllocator(int treasureIndex) {
    Treasures t = null;
    switch (treasureIndex) {
      case 1:
        t = Treasures.DIAMOND;
        break;
      case 2:
        t = Treasures.RUBY;
        break;
      case 3:
        t = Treasures.SAPPHIRE;
        break;
      default:
        break;
    }
    return t;
  }

  private List<ILocation> getListOfCaves() {
    List<ILocation> listOfCaves = new ArrayList<>();
    for (List<ILocation> list : this.dungeon) {
      for (ILocation location : list) {
        if (isCave(location)) {
          listOfCaves.add(location);
        }
      }
    }
    return listOfCaves;
  }


  private List<ILocation> getAllLocation() {
    List<ILocation> allLocations = new ArrayList<>();
    for (List<ILocation> list : this.dungeon) {
      allLocations.addAll(list);
    }
    return allLocations;
  }


  private List<Edge> getListOfEdges() {
    List<Edge> edges = new ArrayList<>();
    //Wrapping Dungeon
    int row = this.dungeon.size();
    int col = this.dungeon.get(0).size();
    if (this.type == 1) {
      for (int r = 0; r < row; r++) {
        for (int c = 0; c < col; c++) {
          edges.add(new Edge(r, c, Direction.NORTH));
          edges.add(new Edge(r, c, Direction.WEST));
        }
      }
    } else if (this.type == 2) { //non-wrapping dungeon.
      for (int r = 0; r < row; r++) {
        for (int c = 0; c < col; c++) {
          if (r > 0) {
            edges.add(new Edge(r, c, Direction.NORTH));
          }
          if (c > 0) {
            edges.add(new Edge(r, c, Direction.WEST));
          }
        }
      }
    }

    return edges;
  }


  private List<List<ILocation>> getDungeon() {
    List<List<ILocation>> dungeonCopy = new ArrayList<>();
    for (List<ILocation> list : this.dungeon) {
      List<ILocation> copyList = new ArrayList<>();
      for (ILocation l : list) {
        ILocation copyLocation = new Location(l);
        copyList.add(copyLocation);
      }
      dungeonCopy.add(copyList);
    }
    return dungeonCopy;
  }

  @Override
  public ILocation getLocationAtGivenCoordinates(int row, int col) {
    ILocation locationCopy = new Location(this.dungeon.get(row).get(col));
    return locationCopy;
  }

  @Override
  public ILocation getPlayerCurrentLcation() {
    return this.player.getPlayerLocation();
  }

  @Override
  public int getDungeonRows() {
    return rows;
  }

  @Override
  public int getDungeonColumns() {
    return columns;
  }
}
