package org.pdp.controller;

import org.pdp.game.Arrowtracer;
import org.pdp.game.Direction;
import org.pdp.game.Dungeon;
import org.pdp.game.IDungeon;
import org.pdp.game.Smell;
import org.pdp.game.Treasures;
import org.pdp.game.Weapon;
import org.pdp.view.IGameMainView;

import java.util.Map;

/**
 *  Represent the controller which interacts with the GUI.
 *  It takes the input from the user to play the game and also updates the grid as needed.
 */
public class SwingViewController implements IController {
  private IGameMainView view;
  private IDungeon model;

  /**
   * Creates instance of the controller which acts as a medium between the GUI and Model.
   * Its responsible for validating inputs and update the GUI as needed.
   * @param model the model instance
   */
  public SwingViewController(IDungeon model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null to play the game.");
    }
    this.model = model;
  }

  @Override
  public void setView(IGameMainView view) {
    if (view == null) {
      throw new IllegalArgumentException("Invalid view.Game cannot start with a null view");
    }
    this.view = view;
    this.view.setGameFeatures(this);

  }

  @Override
  public void execute() {
    this.view.updateLocationStatusBoard(generateLocationStatus());
    this.view.updatePlayerStatusBoard(generatePlayerStatus());
    this.view.makeVisible();
  }


  @Override
  public void movePlayer(int x, int y) {
    boolean wasMoveSuccessful = model.movePlayer(x, y);
    this.view.refreshGrid(wasMoveSuccessful);
    this.view.updateGameStatusBoard(gameStatusOnMove(wasMoveSuccessful));

  }

  @Override
  public void movePlayer(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Invalid Direction.Direction cannot be null");
    }
    boolean wasMoveSuccessful = model.movePlayer(direction);
    this.view.updateLocationStatusBoard(generateLocationStatus());
    this.view.updateGameStatusBoard(gameStatusOnMove(wasMoveSuccessful));
    this.view.refreshGrid(wasMoveSuccessful);
  }

  @Override
  public void pickArrow() {
    boolean pickSuccessful = model.pickArrow();
    this.view.updateGameStatusBoard(gameStatusPickArrow(pickSuccessful));
    this.view.updatePlayerStatusBoard(generatePlayerStatus());
    this.view.updateLocationStatusBoard(generateLocationStatus());
    this.view.refreshGrid(pickSuccessful);
  }

  @Override
  public void pickTreasure() {
    boolean pickSuccessful = model.pickTreasure();
    this.view.refreshGrid(pickSuccessful);
    this.view.updateGameStatusBoard(gameStatusPickTreasure(pickSuccessful));
    this.view.updatePlayerStatusBoard(generatePlayerStatus());
    this.view.updateLocationStatusBoard(generateLocationStatus());
  }




  @Override
  public void shootArrow(Direction direction, int distance) {
    try {
      Arrowtracer trace = this.model.shootArrow(distance, direction);
      this.view.updatePlayerStatusBoard(generatePlayerStatus());
      this.view.updateGameStatusBoard(generateGameStatusOnArrowShoot(trace));
      this.view.refreshGrid(true);
    } catch (IllegalArgumentException ex) {
      this.view.showExceptionDialogBox( ex.getMessage());
    }
  }

  @Override
  public void restartGame() {
    this.model.resetDungeon();
    this.view.resetGridOnNewGame();
  }

  @Override
  public void exitGame() {
    System.exit(0);
  }

  @Override
  public void startNewGame(Map<String, String> configuration) {

    if (configuration == null) {
      throw new IllegalArgumentException("Configuration cannot be empty.");
    }
    try {
      int row = Integer.parseInt(configuration.get("row"));
      int col = Integer.parseInt(configuration.get("col"));
      int interconnectivity = Integer.parseInt(configuration.get("con"));
      int difficulty = Integer.parseInt(configuration.get("difficulty"));
      Double treasure = Double.parseDouble(configuration.get("treasure"));
      String name = configuration.get("name");
      int dungeonType = configuration.get("dungeonType").equals("Wrapping") ? 1 : 2;
      this.model =
        Dungeon.getBuilder().setCol(col).setRow(row).setInterconnectivity(interconnectivity)
          .setPercentageTreasure(treasure).setPlayerName(name).setNumOfMonsters(difficulty)
          .setType(dungeonType).build();
      this.view.setModel(model);
      this.view.updatePlayerStatusBoard(generatePlayerStatus());
      this.view.updateLocationStatusBoard(generateLocationStatus());
      this.view.resetGridOnNewGame();
    } catch (NumberFormatException exr) {
      this.view.showExceptionDialogBox("Invalid Input. Please check your Input types");
    } catch (IllegalArgumentException ill) {
      this.view.showExceptionDialogBox(ill.getMessage());
    }
  }

  private StringBuilder generateGameStatusOnArrowShoot(Arrowtracer trace) {
    StringBuilder b = new StringBuilder();
    if (trace.getHits() == 0) {
      b.append("You shot an arrow into the darkness\n");
    } else if (trace.getHits() == 1) {
      b.append("ROAR!!!!!!!!!!!!!!!!!!!!\n").append("Monster hit\n");
    } else if (trace.getHits() == 2) {
      b.append("ROAR!!!!!!!!!!!!!!!!...........\n");
      b.append("You have killed a monster\n");
    }
    return b;
  }

  private StringBuilder generatePlayerStatus() {
    StringBuilder player_info = new StringBuilder();
    player_info.append("Player Name:").append(model.getPlayer().getPlayerName()).append("\n");
    Map<Treasures, Integer> treasure = model.getPlayerDescription();

    player_info.append("Collected Treasure\n");


    for (Treasures t : treasure.keySet()) {
      player_info.append(t.name()).append(":").append(treasure.get(t)).append("\n");
    }

    player_info.append("Weapons  Collected\n");
    Map<Weapon, Integer> weapons = model.getPlayerWeaponCollected();
    for (Weapon t : weapons.keySet()) {
      player_info.append(t.name()).append(":").append(weapons.get(t)).append("\n");
    }
    return player_info;

  }

  private StringBuilder generateLocationStatus() {
    Map<Treasures, Integer> treasures = model.getCurrentLocationTreasures();
    Smell intensity = model.getSmellIntensity(model.getPlayerCurrentLocation());


    StringBuilder locInfo = new StringBuilder();
    if (intensity.name().equals("LIGHT")) {
      locInfo.append("There is light pungent smell in the air\n");
    } else if (intensity.name().equals("HEAVY")) {
      locInfo.append("There is a very pungent smell in the air\n");
    }

    locInfo.append("Treasure Present:\n");

    for (Treasures t : treasures.keySet()) {
      locInfo.append(t.name()).append(":").append(treasures.get(t)).append("\n");
    }
    Map<Weapon, Integer> weapons = model.getLocationWeapon();
    locInfo.append("Weapons Present:\n");
    for (Weapon t : weapons.keySet()) {
      locInfo.append(t.name()).append(":").append(weapons.get(t)).append("\n");
    }
    return locInfo;
  }

  private StringBuilder gameStatusOnMove(boolean move) {
    StringBuilder status = new StringBuilder();
    if (move) {
      if (model.isPlayerAlive() && model.hasReachedGoal()) {
        status.append("You Won!!!!!!!!!!!");
      } else if (!model.isPlayerAlive()) {
        status.append("You Lost !!!!!!");
      } else {
        status.append("Player have moved to the next location\n");
      }
    } else {
      status.append("No Door in the selected direction\n");
    }
    return status;
  }

  private StringBuilder gameStatusPickArrow(boolean move) {
    StringBuilder status = new StringBuilder();
    if (move) {
      status.append("All arrows in the current location have been picked\n");
    } else {
      status.append("No arrows in the given location to pick\n");
    }
    return status;
  }

  private StringBuilder gameStatusPickTreasure(boolean move) {
    StringBuilder status = new StringBuilder();
    if (move) {
      status.append("All Treasures in the current location have been picked\n");
    } else {
      status.append("No Treasure in the given location to pick\n");
    }
    return status;
  }


}
