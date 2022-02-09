package org.pdp.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Represents  a cave or tunnel in the dungeon.
 * It can be a cave with 1,3 or 4 entrance.
 * It can be a tunnel with 2 exits.
 */
class Location implements ILocation {

  private Map<Treasures, Integer> treasuresChest;
  private Map<Direction, Boolean> allowedDirections;
  private List<Monster> monsters;
  private final int xCoordinate;
  private final int yCoordinate;
  private Map<Weapon, Integer> weapons;
  private boolean isVisited;

  @Override
  public void setVisited(boolean visited) {
    isVisited = visited;
  }


  /**
   * Creates a location in the dungeon.
   * The location can be a cave or a tunnel.ove()
   *
   * @param xCoordinate the x_coordinate of the location in the 2D grid.
   * @param yCoordinate the y_coordinate of the location in the 2D grid.
   */
  protected Location(int xCoordinate, int yCoordinate) {

    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.treasuresChest = new TreeMap<>();
    this.allowedDirections = new HashMap<>();
    this.weapons = new TreeMap<>();
    this.monsters = new ArrayList<>();
    this.isVisited = false;
    for (Direction direction : Direction.values()) {
      this.allowedDirections.put(direction, false);//walls on all four sides.
    }

  }

  protected Location(ILocation location) {
    this.xCoordinate = location.getXCoordinate();
    this.yCoordinate = location.getYCoordinate();
    this.treasuresChest = location.getTreasureList();
    this.weapons = location.getWeaponList();
    this.monsters = location.getMonsters();
    this.allowedDirections = location.getAllowedDirectionToMove();
    this.isVisited = location.isVisited();
  }

  @Override
  public int getXCoordinate() {
    return this.xCoordinate;
  }

  @Override
  public int getYCoordinate() {
    return this.yCoordinate;
  }


  @Override
  public boolean equals(Object other) {
    if (!(other instanceof ILocation)) {
      return false;
    }
    Location o = (Location) other;
    return o.xCoordinate == this.xCoordinate && o.yCoordinate == this.yCoordinate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.xCoordinate, this.yCoordinate);
  }

  @Override
  public void setTreasureInLocation(Treasures treasures) {
    this.treasuresChest.put(treasures, treasuresChest.getOrDefault(treasures, 0) + 1);
  }

  @Override
  public Map<Treasures, Integer> removeTreasureInLocation() {
    Map<Treasures, Integer> treasureList = new TreeMap<>(this.treasuresChest);
    this.treasuresChest = new TreeMap<>();
    return treasureList;
  }

  @Override
  public Map<Weapon, Integer> removeWeaponInLocation() {
    Map<Weapon, Integer> weaponList = new TreeMap<>(this.weapons);
    this.weapons = new TreeMap<>();
    return weaponList;
  }

  @Override
  public void removeMonster() {
    this.monsters = new ArrayList<>();
  }


  @Override
  public void setAllowedDirection(Direction direction) {
    this.allowedDirections.put(direction, true);
  }

  @Override
  public Map<Treasures, Integer> getTreasureList() {
    return new TreeMap<>(this.treasuresChest);
  }

  @Override
  public Map<Weapon, Integer> getWeaponList() {
    return new HashMap<>(this.weapons);
  }

  @Override
  public Map<Direction, Boolean> getAllowedDirectionToMove() {
    return new HashMap<>(this.allowedDirections);
  }

  @Override
  public void updateHit() {
    this.monsters.get(0).updateHits();
  }

  @Override
  public boolean isVisited() {
    return isVisited;
  }

  @Override
  public List<Monster> getMonsters() {
    Monster b;
    List<Monster> ret = new ArrayList<>();
    if (this.monsters.size() > 0) {
      for (Monster m : this.monsters) {
        b = new Otyugh(m.getHits());
        ret.add(b);
      }
    }
    // b = new Otyugh(this.monsters.get(0).getHits());
    return ret;
  }

  @Override
  public String toString() {
    return String.format("[X:%s   Y:%s]", this.xCoordinate, this.yCoordinate);
  }

  @Override
  public void setWeapon(Weapon weapon) {
    this.weapons.put(weapon, weapons.getOrDefault(weapon, 0) + 1);
  }

  @Override
  public void setMonster(Monster monster) {
    this.monsters.add(monster);
  }


}
