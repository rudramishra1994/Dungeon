package org.pdp.game;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a player who is travelling via the maze.
 */
class Player implements IPlayer {

  private Map<Treasures, Integer> listOfTreasure;
  private ILocation location;
  private final String name;
  private Map<Weapon, Integer> weaponList;
  private boolean status;
  private static final int DEFAULT_ARROWS = 3;

  /**
   * Creates a player who will travel the maze.
   *
   * @param name the name of the player.
   */
  public Player(String name) {
    this.name = name;
    this.status = true;
    this.listOfTreasure = new TreeMap<>();
    this.weaponList = new TreeMap<>();
    this.weaponList.put(Weapon.ARROW, DEFAULT_ARROWS);
  }

  Player(IPlayer player) {
    this.name = player.getPlayerName();
    this.weaponList = player.getPlayerWeapon();
    this.status = player.isAlive();
    this.listOfTreasure = player.getPlayerDescription();
    this.location = player.getPlayerLocation();
  }

  @Override
  public ILocation getPlayerLocation() {
    return new Location(this.location);
  }

  @Override
  public Map<Treasures, Integer> getPlayerDescription() {
    return new TreeMap<>(this.listOfTreasure);
  }

  @Override
  public void updatePlayerLocation(ILocation location) {
    if (location == null) {
      throw new IllegalArgumentException("Player location cannot be null");
    }
    this.location = location;
  }

  @Override
  public boolean gatherTreasure() {
    Map<Treasures, Integer> locationTreasures = this.location.removeTreasureInLocation();
    if (locationTreasures != null && locationTreasures.size() > 0) {
      locationTreasures.forEach((key, value)
          -> this.listOfTreasure.merge(key, value, Integer::sum));
    }
    return locationTreasures.size() > 0;
  }

  @Override
  public boolean gatherWeapon() {
    Map<Weapon, Integer> locationWeapon = this.location.removeWeaponInLocation();
    if (locationWeapon != null && locationWeapon.size() > 0) {
      locationWeapon.forEach((key, value)
          -> this.weaponList.merge(key, value, Integer::sum));
    }
    return locationWeapon.size() > 0;

  }

  @Override
  public boolean isAlive() {
    return this.status;
  }

  @Override
  public void shootArrow() {
    int numOfArrows = this.weaponList.get(Weapon.ARROW);
    if (numOfArrows > 0) {
      this.weaponList.put(Weapon.ARROW, numOfArrows - 1);
    } else {
      throw new IllegalArgumentException("You do not have arrow to shoot.Pick more to shoot");
    }
  }

  @Override
  public void setPlayerStatus(boolean status) {
    this.status = status;
  }

  @Override
  public Map<Weapon, Integer> getPlayerWeapon() {
    return new TreeMap<>(this.weaponList);
  }

  @Override
  public String getPlayerName() {
    return this.name;
  }

  @Override
  public void resetPlayer() {
    this.listOfTreasure = new TreeMap<>();
    this.status = true;
    this.weaponList = new TreeMap<>();
    weaponList.put(Weapon.ARROW, 3);
  }


}
