package org.pdp.view;

import org.pdp.controller.GameFeatures;
import org.pdp.game.IDungeonReadOnly;

/**
 * Represents the main game grid.
 * It contains the actual game grid and the information panel.
 * It also includes the menu to provide new game ,restart game and exit option.
 */
public interface IGameMainView {
  /**
   * Update the Game status board everytime the player makes a move.
   *
   * @param info a string builder containing the text to be displayed in the board.
   */
  void updateGameStatusBoard(StringBuilder info);

  /**
   * Update the Player status board everytime the player makes a move.
   *
   * @param info a string builder containing the text to be displayed in the board.
   */
  void updatePlayerStatusBoard(StringBuilder info);

  /**
   * Update the Location status board everytime the player makes a move.
   *
   * @param info a string builder containing the text to be displayed in the board.
   */
  void updateLocationStatusBoard(StringBuilder info);

  /**
   * Wires the view and the controller together to perform the action based on events.
   * It establishes the connection needed to perform events on mouse click, key press etc.
   *
   * @param feature the controller instance.
   */
  void setGameFeatures(GameFeatures feature);

  /**
   * Repaints the visible cell of the grid on player move/action.
   *
   * @param wasMoveSuccessful whether the move is successful or a failure.
   */
  void refreshGrid(boolean wasMoveSuccessful);

  /**
   * Updates the model associated with the view on new game command.
   * @param dungeon the readonly model.
   */
  void setModel(IDungeonReadOnly dungeon);

  /**
   * Shows an exception message when an exception is thrown from the view/controller.
   *
   * @param exceptionMsg the message to be displayed.
   */
  void showExceptionDialogBox(String exceptionMsg);

  /**
   * Removes the old panels and adds new panel to the grid on new game.
   */
  void resetGridOnNewGame();

  /**
   * make the game visible.
   */
  void makeVisible();

}
