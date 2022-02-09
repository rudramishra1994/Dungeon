package org.pdp.game;

import java.util.List;
import java.util.Map;

/**
 * This represents a read only interface which is supplied to the interface.
 */
public interface IDungeonReadOnly {

  /**
   * returns the coordinates of start location of the maze.
   *
   * @return start location.
   */
  List<Integer> getStartLocation();

  /**
   * Returns the coordinates of goal location of the maze.
   *
   * @return end location.
   */
  List<Integer> getEndLocation();



  /**
   * Returns the minimum distance the player has to cover to reach the end.
   *
   * @return integer distance;
   */
  int distanceBetweenStartAndEnd();


  /**
   * returns the Map of treasure collected by the player.
   *
   * @return weapon and its quantity in a map.
   */
  Map<Treasures, Integer> getPlayerDescription();

  /**
   * returns map of player collected weapons and their quantity.
   *
   * @return weapon map
   */
  Map<Weapon, Integer> getPlayerWeaponCollected();

  /**
   * Returns a copy of a list of monster at current location.
   *
   * @return list of monster.
   */
  List<Monster> getMonsterCurrentLocation();

  /**
   * Returns a  list of monster at end location.
   *
   * @return list of monster.
   */
  List<Monster> getMonsterAtEndLocation();

  /**
   * returns the list of weapon without removing them.
   *
   * @return map of weapon and their quantity.
   */
  Map<Weapon, Integer> getLocationWeapon();

  /**
   * returns the list of treasure without removing them.
   *
   * @return map of treasure and their quantity.
   */
  Map<Treasures, Integer> getCurrentLocationTreasures();

  /**
   * returns a map of all four direction and if movement allowed in that direction.
   *
   * @return map of direction and true/false value.
   */
  Map<Direction, Boolean> getCurrentLocationAllowedDirection();

  /**
   * Returns the coordinates of current location of the maze.
   *
   * @return end location.
   */
  List<Integer> getCurrentLocation();


  /**
   * Returns smell intensity at current location.
   * @param location location at whioc smell needs to be calculated.
   * @return Smell intensity.
   */
  Smell getSmellIntensity(ILocation location);

  /**
   * Returns the status of the player .i.e true or false.
   * @return true or false.
   */
  boolean isPlayerAlive();

  /**
   * Returns if the goal location has been reached.
   *
   * @return a true or false
   */
  boolean hasReachedGoal();


  /**
   * Returns a deep copy of the location at the given coordinates.
   * @param x x-coordinate.
   * @param y y-coordinate.
   * @return location object.
   */
  ILocation getLocationAtGivenCoordinates(int x, int y);

  /**
   * Returns the copy of current instance of location of the player.
   * @return location of player.
   */
  ILocation getPlayerCurrentLcation();

  /**
   * Returns the number of rows.
   * @return rows
   */
  int getDungeonRows();

  /**
   * returns the number of columns.
   * @return columns.
   */
  int getDungeonColumns();

}
