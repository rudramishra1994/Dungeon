

import org.junit.Test;

import java.io.StringReader;

import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.assertTrue;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {

  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model


  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2 q");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 2 1 2 0 1 1"),
        new StringBuilder());
    c.playGame(null);//invalid model.
  }

  @Test
  public void testControllerForInvalidRow() {
    StringReader input = new StringReader("abc 1 1 2 0 2 1 2 2 0 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(new TicTacToeModel());
    assertTrue(gameLog.toString().contains("Not a valid number: abc"));

  }

  @Test
  public void testControllerInvalidColumn() {
    StringReader input = new StringReader("2 abc 2 1 1 2 1 1 2 0 2 1 0");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(new TicTacToeModel());
    assertTrue(gameLog.toString().contains("Not a valid number: abc"));
  }

  @Test
  public void testControllerRowOutOfBounds() {
    StringReader input = new StringReader("1 1 5 2 2 0 2 1 2 2 0 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(new TicTacToeModel());
    assertTrue(gameLog.toString().contains("Not a valid move: 5, 2"));
  }

  @Test
  public void testControllerColumnOutOfBounds() {
    StringReader input = new StringReader("4 2 1 1 2 5 2 0 2 1 2 2 0 1");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(new TicTacToeModel());
    assertTrue(gameLog.toString().contains("Not a valid move: 2, 5"));
  }

  @Test
  public void testControllerExitsCorrectlyWithFirstValueIsQ() {
    StringReader input = new StringReader("0 0 q 2");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(new TicTacToeModel());
    assertTrue(gameLog.toString().contains("Game quit! Ending game state:"));
  }

  @Test
  public void testControllerExitsCorrectlyWhenSecondValueIsQ() {
    StringReader input = new StringReader("1 1 2 2 1 q");
    Appendable gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(new TicTacToeModel());
    assertTrue(gameLog.toString().contains("Game quit! Ending game state:"));
  }

  @Test
  public void testControllerForValidMoves() {
    StringReader input = new StringReader("1 1 2 0 2 1 2 2 0 1");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(m);
    String strModel = "   | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + " O | X | O";
    assertTrue(gameLog.toString().contains(strModel));
  }

  @Test
  public void testControllerForOccupiedCell() {
    StringReader input = new StringReader("1 1 2 0 2 1 2 1 2 2 0 1");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(m);
    String strModel = "   | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + " O | X | O";
    assertTrue(gameLog.toString().contains("Not a valid move: 2, 1"));
  }

  @Test
  public void testControllerExecutionAfterInvalidMove() {
    StringReader input = new StringReader("1 1 2 0 2 1 2 1 2 2 0 1");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(m);
    String strModel = "   | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + " O | X | O";
    assertTrue(gameLog.toString().contains("Not a valid move: 2, 1"));
    assertTrue(gameLog.toString().contains(strModel));
  }

  @Test
  public void testControllerXWins() {
    StringReader input = new StringReader("1 1 2 0 2 1 2 1 2 2 0 1");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(m);
    String strModel = "   | X |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + " O | X | O";
    assertTrue(gameLog.toString().contains(strModel));
    assertTrue(gameLog.toString().contains("Game is over! X wins."));
  }

  @Test
  public void testControllerOWins() {
    StringReader input = new StringReader("1 1 2 0 0 2 2 1 1 2 2 2");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(m);
    String strModel = "   |   | X\n"
        + "-----------\n"
        + "   | X | X\n"
        + "-----------\n"
        + " O | O | O";
    assertTrue(gameLog.toString().contains(strModel));
    assertTrue(gameLog.toString().contains("Game is over! O wins."));
  }

  @Test
  public void testControllerTieGame() {
    StringReader input = new StringReader("2 0 1 1 2 2 2 1 0 0 1 0 1 2 0 2 0 1");
    Appendable gameLog = new StringBuilder();
    TicTacToe m = new TicTacToeModel();
    TicTacToeController c = new TicTacToeConsoleController(input,
        gameLog);// 4 is invalid row.
    c.playGame(m);
    String strModel;
    strModel = " X | X | O\n"
        + "-----------\n"
        + " O | O | X\n"
        + "-----------\n"
        + " X | O | X";
    assertTrue(gameLog.toString().contains(strModel));
    assertTrue(gameLog.toString().contains("Game is over! Tie game."));
  }



}
