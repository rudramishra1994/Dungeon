package test;



import org.junit.Test;
import org.pdp.game.Arrowtracer;
import org.pdp.game.Direction;
import org.pdp.game.Dungeon;
import org.pdp.game.IDungeon;
import org.pdp.game.Smell;
import org.pdp.game.Treasures;
import org.pdp.game.Weapon;
import org.pdp.random.IRandom;
import org.pdp.random.Lowerboundrandom;
import org.pdp.random.ProdRandom;
import org.pdp.random.TestRandom;
import org.pdp.random.Upperboundrandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TestCases to test the functionalities of the Maze Solving Application.
 */
public class TestDungeonGame {
  /*

     wrapping Node used for testing.
      +   +   +---+---+   +   +
      | V | T | V   V   V | T |
      +---+   +---+   +   +   +
        T   V   V | V   V   M
      +---+   +---+   +   +---+
      | G   V | V | V | T | M |
      +---+   +   +---+   +   +
      | V   V | V   T   V   T |
      +---+   +   +---+---+---+
        T | T   V | V   M   T
      +   +---+   +---+   +---+
      | T | T   V   T   P | V |
      +   +   +---+---+   +   +
   */



  /*
    Non-wrapping Dungeon used for testing.
        +---+---+---+---+---+---+
        | C | C | C | C | C | C |
        +   +   +   +   +   +   +
        | T | V   V   V | T | T |
        +   +   +   +   +   +   +
        | T | T | T | V   V   G |
        +   +   +   +   +   +   +
        | P   V   V   V | T | T |
        +   +   +   +   +   +   +
        | T | T | T | V   V   V |
        +   +   +   +   +   +   +
        | C | C | C | C | C | C |
        +---+---+---+---+---+---+
   */

  @Test
  public void testDungeonCreation() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(4).build();


    IDungeon dungeon2 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();

    assertTrue(dungeon.isPlayerAlive());
    assertTrue(dungeon2.isPlayerAlive());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDungeonCreationZeroRows() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(0)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDungeonCreationZeroColumns() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(7)
        .setCol(0)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDungeonCreationNegativeColumns() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(9)
        .setCol(-6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDungeonCreationNegativeRows() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(-10)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDungeonCreationNegativeInterconnectivity() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(0)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(-4)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDungeonCreationInvalidType() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(7)
        .setCol(6)
        .setType(5)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDungeonCreationForNullRandom() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(null)
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLargeInterconnectivity() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(100)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroMonster() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(0)
        .setPlayerName("John Wick").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooManyMonsters() {
    IDungeon dungeon = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(100)
        .setPlayerName("John Wick").build();
  }

  @Test
  public void testNoMonsterInStartCave() {
    for (int i = 6; i < 40; i++) {
      for (int j = 6; j < 45; j++) {
        IDungeon dungeon = Dungeon.getBuilder()
            .setRow(i)
            .setCol(j)
            .setType(2)
            .setInterconnectivity(4)
            .setRandom(new ProdRandom())
            .setPercentageTreasure(0.5)
            .setNumOfMonsters(4)
            .setPlayerName("John Wick").build();
        assertTrue(dungeon.getMonsterCurrentLocation().size() == 0);
      }
    }
  }

  @Test
  public void testMonsterAtEndLocation() {
    for (int i = 6; i < 40; i++) {
      for (int j = 6; j < 45; j++) {
        IDungeon dungeon = Dungeon.getBuilder()
            .setRow(i)
            .setCol(j)
            .setType(2)
            .setInterconnectivity(4)
            .setRandom(new ProdRandom())
            .setPercentageTreasure(0.5)
            .setNumOfMonsters(4)
            .setPlayerName("John Wick").build();
        assertTrue(dungeon.getMonsterAtEndLocation().size() > 0);
      }
    }
  }


  @Test
  public void testAllNodeVisited() {
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH,
      Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.NORTH};
    Set<String> visited = new HashSet<>();

    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    List<Integer> l = null;
    for (Direction d : moves) {
      l = dungeon1.getCurrentLocation();
      visited.add(l.toString());
      long exits = dungeon1.getCurrentLocationAllowedDirection()
          .values().stream().filter(p -> p).count();
      dungeon1.movePlayer(d);
      assertTrue(exits != 0);

    }
    visited.add(l.toString());
    assertEquals(36, visited.size());

  }

  @Test
  public void testPlayerCanReachDestination() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Direction[] moves = {Direction.EAST, Direction.EAST,
      Direction.EAST, Direction.NORTH, Direction.EAST, Direction.EAST};
    for (Direction d : moves) {
      dungeon1.movePlayer(d);
    }
    assertTrue(dungeon1.getEndLocation().equals(dungeon1.getCurrentLocation()));
  }


  @Test
  public void testMinimumTreasureCriteria() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH,
      Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.NORTH};
    List<Integer> l = null;
    Set<String> visited = new HashSet<>();
    int caves = 0;
    int cavesWithTreasure = 0;
    for (Direction d : moves) {
      l = dungeon1.getCurrentLocation();
      dungeon1.movePlayer(d);
      if (!visited.contains(l.toString())) {
        long exits = dungeon1.getCurrentLocationAllowedDirection()
            .values().stream().filter(p -> p).count();
        if (exits != 2) {
          caves++;
          if (dungeon1.getCurrentLocationTreasures().size() > 0) {
            cavesWithTreasure++;
          }
        }
      }
      visited.add(l.toString());
      double percentageCaveWihTreasure = (float) cavesWithTreasure / (float) caves;
      assertTrue(percentageCaveWihTreasure > 0.5);


    }
    visited.add(l.toString());
  }


  @Test
  public void testMinimumArrowComparableToTreasureCriteria() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH,
      Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.NORTH};
    List<Integer> l = null;
    Set<String> visited = new HashSet<>();

    int locationWithArrow = 0;
    for (Direction d : moves) {
      l = dungeon1.getCurrentLocation();
      dungeon1.movePlayer(d);
      if (!visited.contains(l.toString())) {
        if (dungeon1.getLocationWeapon().size() > 0) {
          locationWithArrow++;
        }
      }
      visited.add(l.toString());
    }

    visited.add(l.toString());
    double percentageLocationWithArrow = (double) locationWithArrow / 36;
    assertTrue(percentageLocationWithArrow > 0.5);
  }


  @Test
  public void testMinDistanceStartAndGoal() {
    for (int i = 6; i < 40; i++) {
      for (int j = 6; j < 40; j++) {
        IDungeon dungeon = Dungeon.getBuilder()
            .setRow(i)
            .setCol(j)
            .setType(2)
            .setInterconnectivity(4)
            .setRandom(new ProdRandom())
            .setPercentageTreasure(0.5)
            .setNumOfMonsters(4)
            .setPlayerName("John Wick").build();
        IDungeon dungeon2 = Dungeon.getBuilder()
            .setRow(i)
            .setCol(j)
            .setType(1)
            .setInterconnectivity(4)
            .setRandom(new ProdRandom())
            .setPercentageTreasure(0.5)
            .setNumOfMonsters(4)
            .setPlayerName("John Wick").build();
        int dist1 = dungeon.distanceBetweenStartAndEnd();
        assertTrue(dist1 >= 5);
        int dist2 = dungeon2.distanceBetweenStartAndEnd();
        assertTrue(dist2 >= 5);
      }
    }
  }

  @Test
  public void testPlayerStartsWithTreeArrows() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    int expected = dungeon1.getPlayerWeaponCollected().get(Weapon.ARROW);
    assertEquals(3, expected);
  }


  @Test
  public void testTotalMonster() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
        Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
        Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH,
        Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
        Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH,
        Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
        Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH,
        Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
        Direction.NORTH, Direction.NORTH};
    List<Integer> l = null;
    Set<String> visited = new HashSet<>();

    int monsters = 0;
    for (Direction d : moves) {
      l = dungeon1.getCurrentLocation();
      if (!visited.contains(l.toString())) {
        if (dungeon1.getMonsterCurrentLocation().size() > 0) {
          monsters++;
        }
      }
      visited.add(l.toString());
      dungeon1.movePlayer(d);
    }

    visited.add(l.toString());
    assertEquals(monsters, 5);
  }


  @Test
  public void checkForWrappingDungeonRowWrapping() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    int x = dungeon1.getCurrentLocation().get(0);
    int y = dungeon1.getCurrentLocation().get(1);

    //initial (3,5), final (3,0) o moving east.

    assertEquals(3, x);
    assertEquals(5, y);
    dungeon1.movePlayer(Direction.EAST);
    int newLocationX = dungeon1.getCurrentLocation().get(0);
    int newLocationY = dungeon1.getCurrentLocation().get(1);
    assertEquals(x, newLocationX);
    int expectedY = 0;
    assertEquals(expectedY, newLocationY);

  }

  @Test
  public void checkForWrappingDungeonColumnWrapping() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Direction[] moves = {Direction.EAST, Direction.EAST, Direction.EAST, Direction.NORTH,
      Direction.EAST, Direction.NORTH, Direction.NORTH};
    for (Direction d : moves) {
      dungeon1.movePlayer(d);
    }
    int x = dungeon1.getCurrentLocation().get(0);//0
    int y = dungeon1.getCurrentLocation().get(1);//3

    //Initial(0,3) Final (5,3)
    assertEquals(0, x);
    assertEquals(3, y);
    dungeon1.movePlayer(Direction.NORTH);
    int newLocationX = dungeon1.getCurrentLocation().get(0);
    int newLocationY = dungeon1.getCurrentLocation().get(1);
    assertEquals(5, newLocationX);
    assertEquals(3, newLocationY);

  }

  @Test
  public void TestIsStartLocationCave() {
    for (int i = 6; i < 15; i++) {
      for (int j = 6; j < 15; j++) {
        IDungeon dungeon1 = Dungeon.getBuilder()
            .setRow(i)
            .setCol(j)
            .setType(1)
            .setInterconnectivity(0)
            .setRandom(new TestRandom())
            .setPercentageTreasure(0.5)
            .setNumOfMonsters(5)
            .setPlayerName("John Wick").build();

        long exits = dungeon1.getCurrentLocationAllowedDirection()
            .values().stream().filter(p -> p).count();
        assertTrue(exits != 2);
      }
    }
  }

  @Test
  public void TestIsEndLocationCave() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Direction[] moves = {Direction.EAST, Direction.EAST,
      Direction.EAST, Direction.NORTH, Direction.EAST, Direction.EAST};
    for (Direction d : moves) {
      dungeon1.movePlayer(d);
    }
    long exits = dungeon1.getCurrentLocationAllowedDirection()
        .values().stream().filter(p -> p).count();
    assertTrue(exits != 2);
  }

  @Test
  public void testMovePlayerAgainstAWall() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Map<Direction, Boolean> possibleDirection = dungeon1.getCurrentLocationAllowedDirection();
    boolean wasMoveSuccessFull = dungeon1.movePlayer(Direction.WEST);
    assertEquals(possibleDirection.get(Direction.WEST), wasMoveSuccessFull);
    wasMoveSuccessFull = dungeon1.movePlayer(Direction.NORTH);
    assertEquals(possibleDirection.get(Direction.NORTH), wasMoveSuccessFull);
    wasMoveSuccessFull = dungeon1.movePlayer(Direction.SOUTH);
    assertEquals(possibleDirection.get(Direction.SOUTH), wasMoveSuccessFull);
    wasMoveSuccessFull = dungeon1.movePlayer(Direction.EAST);
    assertEquals(possibleDirection.get(Direction.EAST), wasMoveSuccessFull);

  }

  @Test
  public void testSomeColRowWrapSomeDoNot() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();

    int x = dungeon1.getCurrentLocation().get(0);
    int y = dungeon1.getCurrentLocation().get(1);
    assertEquals(3, x);
    assertEquals(5, y);
    dungeon1.movePlayer(Direction.EAST);//wrapping
    int nx = dungeon1.getCurrentLocation().get(0);
    int ny = dungeon1.getCurrentLocation().get(1);
    assertEquals(0, ny);
    assertEquals(x, nx);
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH};
    for (Direction d : moves) {
      dungeon1.movePlayer(d);
    }
    x = dungeon1.getCurrentLocation().get(0);
    y = dungeon1.getCurrentLocation().get(1);
    assertEquals(0, x);
    assertEquals(0, y);
    dungeon1.movePlayer(Direction.NORTH);//wall present
    nx = dungeon1.getCurrentLocation().get(0);
    ny = dungeon1.getCurrentLocation().get(1);
    assertEquals(nx, x);//player cannot move
    assertEquals(ny, y);//player cannot move
  }

  @Test
  public void testPlayerPicksTreasure() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    assertTrue(dungeon1.getPlayerDescription().size() < 1);//before first treasure
    dungeon1.pickTreasure();
    Map<Treasures, Integer> treasures = dungeon1.getPlayerDescription();
    assertEquals(51, treasures.get(Treasures.RUBY).intValue());

  }

  @Test
  public void testPlayerPicksArrows() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(0)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    assertTrue(dungeon1.getPlayerWeaponCollected().get(Weapon.ARROW) == 3);
    dungeon1.pickTreasure();
    Map<Weapon, Integer> weapon = dungeon1.getPlayerWeaponCollected();
    assertEquals(3, weapon.get(Weapon.ARROW).intValue());


  }

  @Test
  public void testNoTreasureInTunnel() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH,
      Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.NORTH};
    List<Integer> l = null;
    Set<String> visited = new HashSet<>();
    int tunnelsWithTreasure = 0;
    for (Direction d : moves) {
      l = dungeon1.getCurrentLocation();
      visited.add(l.toString());
      if (!visited.contains(l.toString())) {
        long exits = dungeon1.getCurrentLocationAllowedDirection()
            .values().stream().filter(p -> p).count();
        if (exits == 2) {
          if (dungeon1.getCurrentLocationTreasures().size() > 0) {
            tunnelsWithTreasure++;
          }
        }
      }
      dungeon1.movePlayer(d);
    }
    visited.add(l.toString());
    assertEquals(0, tunnelsWithTreasure);

  }


  @Test
  public void testNoMonsterInTunnel() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
        Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
        Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH,
        Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
        Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH,
        Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
        Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH,
        Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
        Direction.NORTH, Direction.NORTH};
    List<Integer> l = null;
    Set<String> visited = new HashSet<>();
    int tunnelsWithMonster = 0;
    for (Direction d : moves) {
      l = dungeon1.getCurrentLocation();
      visited.add(l.toString());
      if (!visited.contains(l.toString())) {
        long exits = dungeon1.getCurrentLocationAllowedDirection()
            .values().stream().filter(p -> p).count();
        if (exits == 2) {
          if (dungeon1.getMonsterCurrentLocation().size() > 0) {
            tunnelsWithMonster++;
          }
        }
      }
      dungeon1.movePlayer(d);

    }
    visited.add(l.toString());
    assertEquals(0, tunnelsWithMonster);
  }

  @Test
  public void testProdRandom() {
    IRandom random = new ProdRandom();
    for (int i = 0; i < 100; i++) {
      int value = random.getRandomInteger(1, 100);
      assertTrue(value >= 1 && value <= 1000);
    }
  }

  @Test
  public void testTestRandom() {
    IRandom random = new TestRandom();
    for (int i = 0; i < 100; i++) {
      int value = random.getRandomInteger(1, 1000);
      assertTrue(value == 500);
    }
  }


  @Test
  public void testPlayerStartLocation() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(1)
        .setRandom(new ProdRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    List<Integer> playerLocation = dungeon1.getCurrentLocation();
    assertEquals(playerLocation, dungeon1.getStartLocation());
  }

  @Test
  public void testPlayerLocationDescription() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();



    Map<Direction, Boolean> direction = dungeon1.getCurrentLocationAllowedDirection();
    assertFalse(direction.get(Direction.WEST));
    assertTrue(direction.get(Direction.NORTH));
    assertTrue(direction.get(Direction.EAST));
    assertTrue(direction.get(Direction.SOUTH));
    Map<Treasures, Integer> treasures = dungeon1.getCurrentLocationTreasures();
    assertEquals(51, treasures.get(Treasures.RUBY).intValue());
    assertEquals(2, dungeon1.getLocationWeapon().get(Weapon.ARROW).intValue());
  }

  @Test
  public void testArrowCurvingInTunnel() {
    /*
      +---+---+---+---+   +   +   +
      | C | C | C | C | T | T | T |
      +   +   +   +   +   +   +   +
        C   C | T | T | T | T | V
      +   +   +   +   +   +   +   +
      | T | T | T | T | T | T | T |
      +   +   +   +   +   +   +   +
        V   V   V   V   V   P | T
      +   +   +   +   +   +   +---+
        G | T | T | T | T | T | V
      +   +   +   +   +   +   +---+
        V | T | T | V   V   T | V
      +   +   +   +   +---+---+---+
      | C | C | C | C | C | C | C |
      +---+---+---+---+   +   +   +

     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(1).build();
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH};
    for (Direction d : moves) {
      dungeon1.movePlayer(d);
    }
    Arrowtracer trace = dungeon1.shootArrow(3, Direction.SOUTH);
    List<Direction> expectedDirection = new ArrayList<>(Arrays.asList(Direction.SOUTH,
        Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
        Direction.SOUTH, Direction.WEST, Direction.WEST));
    assertEquals(trace.getDirectionList(), expectedDirection);
  }

  @Test
  public void testArrowStoppingInCaveIfNoOppositeExit() {
    /*
      +---+---+---+---+   +   +   +
      | C | C | C | C | T | T | T |
      +   +   +   +   +   +   +   +
        C   C | T | T | T | T | V
      +   +   +   +   +   +   +   +
      | T | T | T | T | T | T | T |
      +   +   +   +   +   +   +   +
        V   V   V   V   V   P | T
      +   +   +   +   +   +   +---+
        G | T | T | T | T | T | V
      +   +   +   +   +   +   +---+
        V | T | T | V   V   T | V
      +   +   +   +   +---+---+---+
      | C | C | C | C | C | C | C |
      +---+---+---+---+   +   +   +

     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(1).build();
    Arrowtracer trace = dungeon1.shootArrow(5, Direction.SOUTH);
    assertTrue(trace.getDirectionList().size() < 5);
  }

  @Test
  public void TestArrowPassesCavesIfDirectionOpen() {
    /*
      +---+---+---+---+   +   +   +
      | C | C | C | C | T | T | T |
      +   +   +   +   +   +   +   +
        C   C | T | T | T | T | V
      +   +   +   +   +   +   +   +
      | T | T | T | T | T | T | T |
      +   +   +   +   +   +   +   +
        V   V   V   V   V   P | T
      +   +   +   +   +   +   +---+
        G | T | T | T | T | T | V
      +   +   +   +   +   +   +---+
        V | T | T | V   V   T | V
      +   +   +   +   +---+---+---+
      | C | C | C | C | C | C | C |
      +---+---+---+---+   +   +   +

     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(1).build();
    Arrowtracer trace = dungeon1.shootArrow(4, Direction.WEST);
    List<Direction> expectedDirection = new ArrayList<>(Arrays.asList(Direction.WEST,
        Direction.WEST, Direction.WEST, Direction.WEST));
    assertTrue(trace.getDirectionList().size() == 4);
    assertTrue(trace.getDirectionList().equals(expectedDirection));
  }


  @Test
  public void TestArrowPassesTunnelIfOppositeSideOpen() {
    /*
    +---+---+---+---+   +   +   +
    | C | C | C | C | T | T | T |
    +   +   +   +   +   +   +   +
      C   C | T | T | T | T | V
    +   +   +   +   +   +   +   +
    | T | T | T | T | T | T | T |
    +   +   +   +   +   +   +   +
      V   V   V   V   V   P | T
    +   +   +   +   +   +   +---+
      G | T | T | T | T | T | V
    +   +   +   +   +   +   +---+
      V | T | T | V   V   T | V
    +   +   +   +   +---+---+---+
    | C | C | C | C | C | C | C |
    +---+---+---+---+   +   +   +

   */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();
    Arrowtracer trace = dungeon1.shootArrow(2, Direction.SOUTH);
    List<Direction> expectedDirection = new ArrayList<>(Arrays.asList(Direction.SOUTH,
        Direction.SOUTH, Direction.WEST, Direction.WEST));
    assertTrue(trace.getDirectionList().size() == 4);
    assertTrue(trace.getDirectionList().equals(expectedDirection));

  }

  @Test
  public void testHeavySmell() {
    /*
      +---+---+---+---+   +   +   +
      | C | C | C | C | T | T | T |
      +   +   +   +   +   +   +   +
        C   C | T | T | T | T | V
      +   +   +   +   +   +   +   +
      | T | T | T | T | T | T | T |
      +   +   +   +   +   +   +   +
        V   V   M   M   M   P | T
      +   +   +   +   +   +   +---+
        G | T | T | T | T | T | M
      +   +   +   +   +   +   +---+
        M | T | T | V   V   T | V
      +   +   +   +   +---+---+---+
      | C | C | C | C | C | C | C |
      +---+---+---+---+   +   +   +


   */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();
    Smell smell = dungeon1.getSmellIntensity(dungeon1.getPlayer().getPlayerLocation());
    assertEquals(Smell.HEAVY, smell);
  }

  @Test
  public void testLightSmell() {
    /*
        +---+---+---+---+   +   +   +
        | C | C | C | C | T | T | T |
        +   +   +   +   +   +   +   +
          C   C | T | T | T | T | V
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
          V   V   M   M   M   P | T
        +   +   +   +   +   +   +---+
          G | T | T | T | T | T | M
        +   +   +   +   +   +   +---+
          M | T | T | V   V   T | V
        +   +   +   +   +---+---+---+
        | C | C | C | C | C | C | C |
        +---+---+---+---+   +   +   +


     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();

    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.shootArrow(1, Direction.WEST);
    Smell smell = dungeon1.getSmellIntensity(dungeon1.getPlayer().getPlayerLocation());
    assertEquals(Smell.LIGHT, smell);
  }

  @Test
  public void testNoSmell() {
    /*
        +---+---+---+---+   +   +   +
        | C | C | C | C | T | T | T |
        +   +   +   +   +   +   +   +
          C   C | T | T | T | T | V
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
          V   V   M   M   M   P | T
        +   +   +   +   +   +   +---+
          G | T | T | T | T | T | M
        +   +   +   +   +   +   +---+
          M | T | T | V   V   T | V
        +   +   +   +   +---+---+---+
        | C | C | C | C | C | C | C |
        +---+---+---+---+   +   +   +


     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();
    dungeon1.pickArrow();
    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.shootArrow(2, Direction.WEST);
    dungeon1.shootArrow(2, Direction.WEST);
    Smell smell = dungeon1.getSmellIntensity(dungeon1.getPlayer().getPlayerLocation());
    assertEquals(Smell.NOSMELL, smell);
  }

  @Test
  public void testPlayerKillsMonster() {
    /*
        +---+---+---+---+   +   +   +
        | C | C | C | C | T | T | T |
        +   +   +   +   +   +   +   +
          C   C | T | T | T | T | V
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
          V   V   M   M   M   P | T
        +   +   +   +   +   +   +---+
          G | T | T | T | T | T | M
        +   +   +   +   +   +   +---+
          M | T | T | V   V   T | V
        +   +   +   +   +---+---+---+
        | C | C | C | C | C | C | C |
        +---+---+---+---+   +   +   +


     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();

    dungeon1.pickArrow();
    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.movePlayer(Direction.WEST);
    assertTrue(dungeon1.isPlayerAlive());
  }

  @Test
  public void testPlayerKilled() {
    /*
        +---+---+---+---+   +   +   +
        | C | C | C | C | T | T | T |
        +   +   +   +   +   +   +   +
          C   C | T | T | T | T | V
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
          V   V   M   M   M   P | T
        +   +   +   +   +   +   +---+
          G | T | T | T | T | T | M
        +   +   +   +   +   +   +---+
          M | T | T | V   V   T | V
        +   +   +   +   +---+---+---+
        | C | C | C | C | C | C | C |
        +---+---+---+---+   +   +   +


     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();

    dungeon1.movePlayer(Direction.WEST);
    assertFalse(dungeon1.isPlayerAlive());
  }

  @Test
  public void testMonsterIsHitOnce() {
    /*
        +---+---+---+---+   +   +   +
        | C | C | C | C | T | T | T |
        +   +   +   +   +   +   +   +
          C   C | T | T | T | T | V
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
          V   V   M   M   M   P | T
        +   +   +   +   +   +   +---+
          G | T | T | T | T | T | M
        +   +   +   +   +   +   +---+
          M | T | T | V   V   T | V
        +   +   +   +   +---+---+---+
        | C | C | C | C | C | C | C |
        +---+---+---+---+   +   +   +


     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();

    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.movePlayer(Direction.WEST);
    assertTrue(dungeon1.getMonsterCurrentLocation().get(0).getHits() == 1);
  }

  @Test
  public void testMonsterIsHitTwiceAndKilled() {
    /*
      +---+---+---+---+   +   +   +
      | C | C | C | C | T | T | T |
      +   +   +   +   +   +   +   +
        C   C | T | T | T | T | V
      +   +   +   +   +   +   +   +
      | T | T | T | T | T | T | T |
      +   +   +   +   +   +   +   +
        V   V   M   M   M   P | T
      +   +   +   +   +   +   +---+
        G | T | T | T | T | T | M
      +   +   +   +   +   +   +---+
        M | T | T | V   V   T | V
      +   +   +   +   +---+---+---+
      | C | C | C | C | C | C | C |
      +---+---+---+---+   +   +   +

*/
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();

    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.movePlayer(Direction.WEST);
    assertTrue(dungeon1.getMonsterCurrentLocation().size() == 0);
  }

  @Test
  public void testPlayerKilledByInjuredMonster() {
    /*
        +   +   +   +   +   +   +   +
          P   M   M   M   M   M   V
        +   +   +   +   +   +   +---+
          V   V   V   V | T | T | V
        +   +   +   +   +   +   +---+
          V | T | T | T | T | T | V
        +   +   +   +   +   +   +---+
          V | T | T | T | T | T | V
        +   +   +   +   +   +   +---+
          C | T | T | T | T | T | G
        +   +   +   +   +   +   +---+
          T | C | C | C | C | C | C
        +---+---+---+---+---+---+---+
        | C | C | C | C | C | C | C |
        +   +   +   +   +   +   +   +


     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new Lowerboundrandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();

    dungeon1.shootArrow(1, Direction.EAST);
    dungeon1.movePlayer(Direction.EAST);
    assertFalse(dungeon1.isPlayerAlive());
    assertTrue(dungeon1.getMonsterCurrentLocation().size() > 0);
    assertTrue(dungeon1.getMonsterCurrentLocation().get(0).isAlive());
  }

  @Test
  public void testPlayerNotKilledByInjuredMonster() {
    /*
        +   +   +---+---+---+---+---+
          C   C | C | C | C | M | T
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
        | T | T | T | T | T | T | T |
        +   +   +   +   +   +   +   +
        | G   M   M   M   M   P   T |
        +   +   +---+---+---+---+---+


     */
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new Upperboundrandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();

    dungeon1.shootArrow(1, Direction.WEST);
    dungeon1.movePlayer(Direction.WEST);
    assertTrue(dungeon1.isPlayerAlive());
    //assertTrue(dungeon1.getMonsterCurrentLocation().size()>0);
    assertTrue(dungeon1.getMonsterCurrentLocation().get(0).isAlive());
  }

  @Test
  public void testLowerBoundRandom() {
    for (int i = 1; i < 100; i++) {
      for (int j = i; j < 100; j++) {
        assertEquals(i, new Lowerboundrandom().getRandomInteger(i, j));
      }
    }
  }

  @Test
  public void testPlayerStartsWithThreeArrows() {
    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(7)
        .setCol(7)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new Upperboundrandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").setNumOfMonsters(6).build();

    assertEquals(3,dungeon1.getPlayerWeaponCollected().get(Weapon.ARROW).intValue());
  }

  @Test
  public void testInterconnectivity() {
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH,
      Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.NORTH};
    Set<String> visited = new HashSet<>();

    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    List<Integer> l = null;
    int edges = 0;
    for (Direction d : moves) {
      l = dungeon1.getCurrentLocation();
      visited.add(l.toString());
      long exits = dungeon1.getCurrentLocationAllowedDirection()
          .values().stream().filter(p -> p).count();
      dungeon1.movePlayer(d);
      Map<Direction,Boolean> mp = dungeon1.getCurrentLocationAllowedDirection();
      for (Direction k : mp.keySet()) {
        if (k.equals(Direction.NORTH) || k.equals(Direction.WEST)) {
          if (mp.get(k)) {
            edges++;
          }
        }
      }
      assertTrue(exits != 0);

    }
    visited.add(l.toString());
    assertEquals(4, edges - 36 + 1);
  }

  @Test
  public void testArrowInBothTunnelAndCaves() {
    Direction[] moves = {Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.NORTH,
      Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
      Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH, Direction.NORTH,
      Direction.NORTH, Direction.EAST, Direction.NORTH, Direction.NORTH, Direction.SOUTH,
      Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.NORTH,
      Direction.NORTH, Direction.NORTH};
    Set<String> visited = new HashSet<>();

    IDungeon dungeon1 = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    List<Integer> l = null;
    int tunnelWithArrow = 0;
    int cavesWithArrow = 0;

    for (Direction d : moves) {
      l = dungeon1.getCurrentLocation();
      visited.add(l.toString());

      long exits = dungeon1.getCurrentLocationAllowedDirection()
          .values().stream().filter(p -> p).count();
      dungeon1.movePlayer(d);
      if (exits == 2 &&  dungeon1.getLocationWeapon().size() > 0) {
        tunnelWithArrow ++ ;
      } else if (exits != 2 &&  dungeon1.getLocationWeapon().size() > 0) {
        cavesWithArrow ++;
      }
      assertTrue(exits != 0);

    }
    visited.add(l.toString());
    assertTrue(cavesWithArrow > 0 && tunnelWithArrow > 0);
  }




}