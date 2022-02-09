package org.pdp.game;

/**
 * Represents the different mazes that a player can traverse through.
 * It can have two types of location tunnel and caves.
 * Tunnels have two exits exactly and caves have 1,2 0r 4 exits.
 */
public interface IDungeon extends IDungeonReadOnly {


  /**
   * This function moves the player in the given direction.
   *
   * @param direction The direction in which to move the player.
   * @return true if motion the given direction in possible,otherwise false.
   */

  boolean movePlayer(Direction direction);


  /**
   * Move player to the given X and Y coordinates.
   *
   * @param x x-coordinate
   * @param y y-coordinate
   * @return true/false depending on the move.
   */
  boolean movePlayer(int x, int y);


  /**
   * Returns a tracing object that has the directions that the arrow took.
   * It also contains the number of hit the monster if any has taken at the destination.
   *
   * @param distance  distance travelled by the arrow.
   * @param direction initial direction of the arrow.
   * @return an ArrowTracer object that has a list of direction and hit s
   */

  Arrowtracer shootArrow(int distance, Direction direction);


  /**
   * Returns true if the player successfully picks  treasures if it exists.
   *
   * @return true/false
   */
  boolean pickTreasure();


  /**
   * Returns true if the player successfully picks  weapons if it exists.
   *
   * @return true/false
   */
  boolean pickArrow();


  /**
   * Return player current location.
   *
   * @return current location.
   */
  ILocation getPlayerCurrentLocation();

  /**
   * Returns copy of the player.
   *
   * @return player copy.
   */
  IPlayer getPlayer();


  /**
   * Checks if a given location is a cave or a tunnel.
   *
   * @param location given location
   * @return true/false
   */
  boolean isCave(ILocation location);

  /**
   * Reset the dungeon on restart the same game.
   */
  void resetDungeon();


}
