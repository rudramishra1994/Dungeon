package org.pdp.controller;

import org.pdp.game.Direction;

import java.util.Map;

/**
 * Represents a list of features provided by the game to the user.
 */
public interface GameFeatures {

  /**
   *  Moves the player in the given direction.
   *  The direction is decided by mouse click or arrow key.
   * @param x x-coordinate
   * @param y y-coordinate
   */
  void movePlayer(int x , int y);


  /**
   * Move player in the given direction.
   * Direction : North ,South ,East,West.
   * @param direction Direction to move player.
   */
  void movePlayer(Direction direction);

  /**
   * Picks the arrow at the current location.
   */
  void pickArrow();

  /**
   * Picks the treasure at the given location.
   */
  void pickTreasure();



  /**
   * shoots the arrow in the given direction for a specified distance.
   * @param direction  Direction to shoot the arrow.
   * @param distance  Distance to shoot the arrow.
   */
  void shootArrow(Direction direction , int distance);


  /**
   * Restart Game with the same dungeon.
   */
  void restartGame();

  /**
   * Exit the game.
   */
  void exitGame();

  /**
   * Create new Model with new configuration.
   * @param configuration configuration setting.
   */
  void startNewGame(Map<String,String> configuration);
}
