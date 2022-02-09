package org.pdp.game;

import java.util.List;
import java.util.Map;

/**
 * Represents a location in the dungeon.
 * The location can be a tunnel or a cave.
 * A tunnel has 2 exits and a cave can have 1,3 or 4 exits
 */
public interface ILocation {

  /**
   * Returns the x_coordinate of the location in the 2D matrix.
   *
   * @return x coordinate;
   */
  int getXCoordinate();

  /**
   * Returns the y-coordinate of the location in the 2D matrix.
   *
   * @return y coordinate;
   */

  int getYCoordinate();


  /**
   * Assign treasure while distributing treasure.
   *
   * @param treasures treasure to add to location
   */
  void setTreasureInLocation(Treasures treasures);

  /**
   * Removes the treasures at a location and returns them.
   *
   * @return map of treasure and their quantity.
   */
  Map<Treasures, Integer> removeTreasureInLocation();

  /**
   * Removes the weapons at a location and returns them.
   *
   * @return weapons of treasure and their quantity.
   */
  Map<Weapon, Integer> removeWeaponInLocation();

  /**
   * Removes the monster at a location.
   */
  void removeMonster();

  /**
   * creates an exit in given direction.
   *
   * @param direction direction
   */
  void setAllowedDirection(Direction direction);

  /**
   * returns the list of treasure without removing them.
   *
   * @return map of treasure and their quantity.
   */
  Map<Treasures, Integer> getTreasureList();

  /**
   * returns the list of weapon without removing them.
   *
   * @return map of weapon and their quantity.
   */
  Map<Weapon, Integer> getWeaponList();

  /**
   * Returns a copy of a list of monster at a location.
   *
   * @return list of monster.
   */
  List<Monster> getMonsters();

  /**
   * Assigns weapon to a location.
   *
   * @param weapon weapon instance.
   */
  void setWeapon(Weapon weapon);

  /**
   * assigns monster to the location.
   *
   * @param monster monster to be present at the location.
   */
  void setMonster(Monster monster);

  /**
   * returns a map of all four direction and if movement allowed in that direction.
   *
   * @return map of direction and true/false value.
   */
  Map<Direction, Boolean> getAllowedDirectionToMove();

  /**
   * Updates the hits of the monster in the location.
   */
  void updateHit();

  /**
   * Returns if a player has visited a location.
   * @return true or false;
   */
  boolean isVisited();

  /**
   * Sets the visited status of a location.
   *
   * @param visited true/false
   */
  void setVisited(boolean visited);


}
