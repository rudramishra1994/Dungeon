package test;

import org.pdp.controller.GameFeatures;
import org.pdp.game.IDungeonReadOnly;
import org.pdp.view.IGameMainView;

/**
 * Mock view used to test the dungeon in isolation.
 */
public class MockView implements IGameMainView {

  private StringBuilder log;

  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void updateGameStatusBoard(StringBuilder info) {
    log.append(info).append("\n");
  }

  @Override
  public void updatePlayerStatusBoard(StringBuilder info) {
    log.append(info).append("\n");
  }

  @Override
  public void updateLocationStatusBoard(StringBuilder info) {
    log.append(info).append("\n");
  }

  @Override
  public void setGameFeatures(GameFeatures feature) {
    log.append("setGameFeatures\n").append("\n");
  }

  @Override
  public void refreshGrid(boolean wasMoveSuccessful) {
    log.append("refreshGrid:").append(wasMoveSuccessful).append("\n");
  }

  @Override
  public void setModel(IDungeonReadOnly dungeon) {
    log.append("DungeonSet:").append(dungeon.isPlayerAlive()).append("\n");
  }

  @Override
  public void showExceptionDialogBox(String exceptionMsg) {
    log.append(exceptionMsg);
  }

  @Override
  public void resetGridOnNewGame() {
    log.append("resetGridOnNewGame");
  }

  @Override
  public void makeVisible() {
    log.append("makeVisible");
  }
}
