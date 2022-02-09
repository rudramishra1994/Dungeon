package test;

import org.junit.Test;
import org.pdp.controller.CommandPatternController;
import org.pdp.controller.ICommandPatternController;
import org.pdp.game.Dungeon;
import org.pdp.game.IDungeon;
import org.pdp.game.Treasures;
import org.pdp.game.Weapon;
import org.pdp.random.TestRandom;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the interaction of the controller with the model.
 */
public class Testcontoller {

  @Test
  public void testCommandPatternController() {
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertEquals(13, appendable.toString().split("\n").length);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCommandPatternControllerReadableNull() {
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();

    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(null, appendable,
        dungeonModel);
    cpd.playGame();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCommandPatternControllerAppendableNull() {
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("");
    ICommandPatternController cpd = new CommandPatternController(readable, null,
        dungeonModel);
    cpd.playGame();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCommandPatternControllerDungeonNull() {
    Readable readable = new StringReader("");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        null);
    cpd.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testCommandPatternControllerAppendFailed() {
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("");
    Appendable appendable = new FailingAppendable();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
  }

  @Test
  public void testCommandPatternControllerMovePlayerEast() {
    /*
      +---+---+---+---+---+---+
      | C | C | C | C | C | C |
      +   +   +   +   +   +   +
      | T | V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   G |
      +   +   +   +   +   +   +
      | P   V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   V |
      +   +   +   +   +   +   +
      | C | C | C | C | C | C |
      +---+---+---+---+---+---+
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();

    List<Integer> start = dungeonModel.getCurrentLocation();
    Readable readable = new StringReader("MOVE E");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    List<Integer> end = dungeonModel.getCurrentLocation();
    assertEquals(0,start.get(0) - end.get(0));
    assertEquals(1,end.get(1).intValue());
  }

  @Test
  public void testCommandPatternControllerMovePlayerWest() {
    /*
      +---+---+---+   +   +   +
      | C | C | C | T | C   C |
      +   +   +   +   +   +   +
        V | T | T | T | T | V
      +   +   +   +   +   +   +
      | T | T | V   V   G   T |
      +   +   +   +   +   +---+
        P   V   V | T | T | T
      +   +   +   +   +   +   +
        V | T | T | V | T   V
      +   +   +   +---+---+---+
      | C | C | C | C | C | C |
      +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();

    List<Integer> start = dungeonModel.getCurrentLocation();
    Readable readable = new StringReader("MOVE W");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    List<Integer> end = dungeonModel.getCurrentLocation();
    assertEquals(0,start.get(0) - end.get(0));
    assertEquals(5, end.get(1).intValue());

  }

  @Test
  public void testCommandPatternControllerMovePlayerNorth() {
    /*
      +---+---+---+   +   +   +
      | C | C | C | T | C   C |
      +   +   +   +   +   +   +
        V | T | T | T | T | V
      +   +   +   +   +   +   +
      | T | T | V   V   G   T |
      +   +   +   +   +   +---+
        P   V   V | T | T | T
      +   +   +   +   +   +   +
        V | T | T | V | T   V
      +   +   +   +---+---+---+
      | C | C | C | C | C | C |
      +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();

    List<Integer> start = dungeonModel.getCurrentLocation();
    Readable readable = new StringReader("MOVE N");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    List<Integer> end = dungeonModel.getCurrentLocation();
    assertEquals(2,end.get(0).intValue());
    assertEquals(start.get(1),end.get(1));
  }

  @Test
  public void testCommandPatternControllerMovePlayerSouth() {
    /*
      +---+---+---+   +   +   +
      | C | C | C | T | C   C |
      +   +   +   +   +   +   +
        V | T | T | T | T | V
      +   +   +   +   +   +   +
      | T | T | V   V   G   T |
      +   +   +   +   +   +---+
        P   V   V | T | T | T
      +   +   +   +   +   +   +
        V | T | T | V | T   V
      +   +   +   +---+---+---+
      | C | C | C | C | C | C |
      +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();


    Readable readable = new StringReader("MOVE S");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    List<Integer> start = dungeonModel.getCurrentLocation();
    List<Integer> end = dungeonModel.getCurrentLocation();
    assertEquals(4,end.get(0).intValue());
    assertTrue(appendable.toString().contains("Player was moved in the specified direction"));
    assertEquals(start.get(1),end.get(1));
  }

  @Test
  public void testCommandPatternControllerMovePlayerExit() {
    /*
      +---+---+---+   +   +   +
      | C | C | C | T | C   C |
      +   +   +   +   +   +   +
        V | T | T | T | T | V
      +   +   +   +   +   +   +
      | T | T | V   V   G   T |
      +   +   +   +   +   +---+
        P   V   V | T | T | T
      +   +   +   +   +   +   +
        V | T | T | V | T   V
      +   +   +   +---+---+---+
      | C | C | C | C | C | C |
      +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setPlayerName("John Wick").build();

    Readable readable = new StringReader("EXIT");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertTrue(appendable.toString().contains("Exiting Game"));
  }

  @Test
  public void testCommandPatternControllerPlayerKillsMonster() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("SHOOT E 1 SHOOT E 1");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertTrue(appendable.toString().contains("You killed a Monster!!!!!"));
  }

  @Test
  public void testCommandPatternControllerMonsterKillsPlayer() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("MOVE E");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertTrue(appendable.toString().contains("Killed by Monster!!Game Over!! You lost!!"));
    assertFalse(dungeonModel.isPlayerAlive());
  }


  @Test
  public void testCommandPatternControllerPickTreasure() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("PICK TREASURE");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertTrue(appendable.toString().contains("You have gathered 51 RUBY"));
    int expected = dungeonModel.getPlayerDescription().get(Treasures.RUBY);
    assertEquals(51,expected);
  }

  @Test
  public void testCommandPatternControllerPickArrow() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("PICK ARROW");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    assertEquals(3,dungeonModel.getPlayerWeaponCollected().get(Weapon.ARROW).intValue());
    cpd.playGame();

    assertTrue(appendable.toString().contains("You have 5 ARROW"));
  }

  @Test
  public void testCommandPatternControllerInvalidOperation() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("GGRNR");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();

    assertTrue(appendable.toString().contains("Enter a valid option.Enter "
        + "option exactly as mentioned"));
  }

  @Test
  public void testCommandPatternControllerInvalidDirectionMove() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("MOVE X");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();

    assertTrue(appendable.toString().contains("Invalid Direction.Please give a valid direction"));
  }

  @Test
  public void testCommandPatternControllerInvalidArrowDistanceMoreThanFive() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("SHOOT E 10");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertTrue(appendable.toString().contains("Distance of the arrow has to be between 1 and 5,"
        + " both inclusive"));
  }

  @Test
  public void testCommandPatternControllerInvalidArrowDistanceLessThanOne() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("SHOOT E 0");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertTrue(appendable.toString().contains("Distance of the arrow has to be between 1 and 5,"
        + " both inclusive"));
  }

  @Test
  public void testCommandPatternControllerInjuredMonster() {
    /*
    +---+---+---+   +   +   +
    | C | C | C | T | C   C |
    +   +   +   +   +   +   +
      V | T | T | T | T | V
    +   +   +   +   +   +   +
    | T | T | V   M   G   T |
    +   +   +   +   +   +---+
      P   M   M | T | T | T
    +   +   +   +   +   +   +
      M | T | T | V | T   V
    +   +   +   +---+---+---+
    | C | C | C | C | C | C |
    +---+---+---+   +   +   +
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(1)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("SHOOT E 2");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertTrue(appendable.toString().contains("Monster has been hit!!! You hear a loud roar"));
  }

  @Test
  public void testCommandPatternControllerDirectionMoveFalse() {
    /*
      +---+---+---+---+---+---+
      | C | C | C | C | C | C |
      +   +   +   +   +   +   +
      | T | V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   G |
      +   +   +   +   +   +   +
      | P   V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   V |
      +   +   +   +   +   +   +
      | C | C | C | C | C | C |
      +---+---+---+---+---+---+
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("MOVE W");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();
    assertTrue(appendable.toString().contains("Player move unsuccessful"));
  }

  @Test
  public void testCommandPatternControllerDirectionInvalidPick() {
    /*
      +---+---+---+---+---+---+
      | C | C | C | C | C | C |
      +   +   +   +   +   +   +
      | T | V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   G |
      +   +   +   +   +   +   +
      | P   V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   V |
      +   +   +   +   +   +   +
      | C | C | C | C | C | C |
      +---+---+---+---+---+---+
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(5)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("MOVE S MOVE S PICK TREASURE");
    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();

    assertTrue(appendable.toString().contains("There is no such item at this location"));
  }


  @Test
  public void testCommandPatternControllerPlayerWins() {
    /*
      +---+---+---+---+---+---+
      | C | C | C | C | C | C |
      +   +   +   +   +   +   +
      | T | V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   G |
      +   +   +   +   +   +   +
      | P   V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   V |
      +   +   +   +   +   +   +
      | C | C | C | C | C | C |
      +---+---+---+---+---+---+
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("MOVE E MOVE E MOVE E MOVE N SHOOT E 2 SHOOT E 2 MOVE E "
        + "MOVE E");

    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
          dungeonModel);
    cpd.playGame();

    assertTrue(appendable.toString().contains("GAME OVER!!!!!!!You have won!!!"));
  }

  @Test
  public void testCommandPatternControllerArrowFiredNotAtArrow() {
    /*
      +---+---+---+---+---+---+
      | C | C | C | C | C | C |
      +   +   +   +   +   +   +
      | T | V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   G |
      +   +   +   +   +   +   +
      | P   V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   V |
      +   +   +   +   +   +   +
      | C | C | C | C | C | C |
      +---+---+---+---+---+---+
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("SHOOT S 3");

    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();

    assertTrue(appendable.toString().contains("You fired an arrow into the darkness"));
  }


  @Test
  public void testCommandPatternControllerArrowUnsuccessfulMove() {
    /*
      +---+---+---+---+---+---+
      | C | C | C | C | C | C |
      +   +   +   +   +   +   +
      | T | V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   G |
      +   +   +   +   +   +   +
      | P   V   V   V | T | T |
      +   +   +   +   +   +   +
      | T | T | T | V   V   V |
      +   +   +   +   +   +   +
      | C | C | C | C | C | C |
      +---+---+---+---+---+---+
     */
    IDungeon dungeonModel = Dungeon.getBuilder()
        .setRow(6)
        .setCol(6)
        .setType(2)
        .setInterconnectivity(4)
        .setRandom(new TestRandom())
        .setPercentageTreasure(0.5)
        .setNumOfMonsters(1)
        .setPlayerName("John Wick").build();
    Readable readable = new StringReader("MOVE W");

    Appendable appendable = new StringBuilder();
    ICommandPatternController cpd = new CommandPatternController(readable, appendable,
        dungeonModel);
    cpd.playGame();

    assertTrue(appendable.toString().contains("Player move unsuccessful"));
  }


}
