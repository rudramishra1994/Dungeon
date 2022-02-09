package org.pdp.controller;

import org.pdp.view.IGameMainView;

/**
 * An interface which represents the starting point of the controller.
 */
public interface IController extends GameFeatures {
  /**
   * Starting point for the execution of UI.
   */
  void execute();

  /**
   * Set view in the controller.
   *
   * @param view the view of the game.
   */
  void setView(IGameMainView view);
}
