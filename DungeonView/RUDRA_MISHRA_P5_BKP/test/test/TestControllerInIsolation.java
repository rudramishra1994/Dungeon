package test;

import org.junit.Test;
import org.pdp.controller.IController;
import org.pdp.controller.SwingViewController;
import org.pdp.game.Direction;
import org.pdp.game.Dungeon;
import org.pdp.game.IDungeon;
import org.pdp.view.IGameMainView;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Test the controller in isolation from the view.
 */
public class TestControllerInIsolation {


  @Test
  public void movePlayerToGivenCoordinates() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.movePlayer(1, 2);
    assertTrue(b.toString().contains("Player moved to coordinates:12"));
    assertTrue(b1.toString().contains("refreshGrid:true"));

  }

  @Test
  public void TestMovePlayerInGivenDirection() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.movePlayer(Direction.NORTH);
    assertTrue(b.toString().contains("Direction Appended:NORTH"));

  }

  @Test
  public void TestPickArrow() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.pickArrow();
    assertTrue(b1.toString().contains("Treasure Present:\n"));
    assertTrue(b1.toString().contains("Weapons Present:\n"));
  }

  @Test
  public void TestPickTreasure() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.pickTreasure();
    assertTrue(b1.toString().contains("Collected Treasure\n"));
  }

  @Test
  public void TestShootArrow() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.shootArrow(Direction.WEST, 3);
    assertTrue(b1.toString().contains("You shot an arrow into the darkness"));
  }

  @Test
  public void TestRestartGame() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.restartGame();
    assertTrue(b.toString().contains("Dungeon reset done"));
    assertTrue(b1.toString().contains("resetGridOnNewGame"));
  }

  @Test
  public void TestStartNewGame() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    Map<String, String> config = new HashMap<>();
    config.put("row", "10");
    config.put("col", "10");
    config.put("con", "5");
    config.put("difficulty", "5");
    config.put("treasure", "0.6");
    config.put("dungeonType", "Wrapping");
    config.put("name", "wick");
    controller.startNewGame(config);
    assertTrue(b1.toString().contains("DungeonSet:true"));
  }

  @Test
  public void TestShowExceptionDialogBox() {

    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = Dungeon.getBuilder().build();
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    Map<String, String> config = new HashMap<>();
    config.put("row", "1");
    config.put("col", "1");
    config.put("con", "5");
    config.put("difficulty", "5");
    config.put("treasure", "0.6");
    config.put("dungeonType", "6");
    config.put("name", "wick");
    controller.startNewGame(config);
    assertTrue(b1.toString().contains("Length or breadth cannot be less than 5 and "
        + " Interconnectivity of the dungeon cannot be less than 0."));
  }

  @Test
  public void TestExecute() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.execute();
    assertTrue(b1.toString().contains("makeVisible"));
  }

  @Test
  public void TestMovePlayerModelGivenDirection() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.execute();
    controller.movePlayer(Direction.NORTH);
    assertTrue(b.toString().contains("Direction Appended:NORTH"));

  }

  @Test
  public void TestMovePlayerModelGivenCoordinates() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.execute();
    controller.movePlayer(10,12);
    assertTrue(b.toString().contains("Player moved to coordinates:1012"));
  }


  @Test
  public void TestMovePickArrowModel() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.execute();
    controller.pickArrow();
    assertTrue(b.toString().contains("Arrows Picked from Model"));
  }

  @Test
  public void TestMovePickTreasureModel() {
    StringBuilder b = new StringBuilder();
    StringBuilder b1 = new StringBuilder();
    IDungeon dungeon = new MockModel(b);
    IGameMainView view = new MockView(b1);
    IController controller = new SwingViewController(dungeon);
    controller.setView(view);
    controller.execute();
    controller.pickTreasure();
    assertTrue(b.toString().contains("Treasures Picked from Model"));
  }





}
