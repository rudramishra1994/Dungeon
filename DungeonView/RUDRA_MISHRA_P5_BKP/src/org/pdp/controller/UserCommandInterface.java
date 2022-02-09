package org.pdp.controller;


import org.pdp.game.IDungeon;

/**
 * An interface that defines an action which is executed as per user text command.
 * The action can be MOVE , PICK OR SHOOT.
 */
public interface UserCommandInterface {
  /**
   * Execute the action selected by the user.
   * @param dungeon instance of a dungeon.
   */
  void execute(IDungeon dungeon) ;
}
