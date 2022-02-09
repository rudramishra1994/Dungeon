package org.pdp.game;

import java.util.Map;

/**
 * Represents different types of players who can travel through the dungeon.
 */
public interface IPlayer {

  /**
   * Returns the location of the player in the dungeon.
   *
   * @return location of the player in the dungeon
   */
  ILocation getPlayerLocation();

  /**
   * returns the Map of treasure collected by the player.
   *
   * @return weapon and its quantity in a map.
   */
  Map<Treasures, Integer> getPlayerDescription();

  /**
   * Returns true if the treasure were successfully picked.
   * @return true /false
   */
  boolean gatherTreasure();

  /**
   * Returns the status of the player .i.e dead or alive.
   *
   * @return true or false.
   */
  boolean isAlive();

  /**
   * Updates the location of the player as the player moves.
   *
   * @param location the location of the player.
   */
  void updatePlayerLocation(ILocation location);

  /**
   * updates the number of arrows that the player has.
   */
  void shootArrow();

  /**
   * Returns true if the weapon were successfully picked.
   * @return true /false
   */
  boolean gatherWeapon();

  /**
   * sets the player  status dead or alive.
   *
   * @param status set the player status .i.e dead or alive.
   */
  void setPlayerStatus(boolean status);

  /**
   * returns map of player collected weapons and their quantity.
   *
   * @return weapon map
   */
  Map<Weapon, Integer> getPlayerWeapon();

  /**
   * Returns the name of the player.
   * @return player name.
   */
  String getPlayerName();

  /**
   * The function is used to reset the player of the game on restart.
   *
   */
  void resetPlayer();
}
