package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {

    if (m == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    StringBuilder inputMessage;
    String strInput = null;
    int[] xy = new int[2];
    int counter = 0;
    boolean quit;
    boolean wasLastMoveAllowedInput = true;
    do {
      inputMessage = new StringBuilder();
      inputMessage.append(m).append("\n");
      inputMessage.append(String.format("Enter a move for %s:\n", m.getTurn().toString()));
      quit = false;
      try {
        if (wasLastMoveAllowedInput) {
          out.append(inputMessage);// We print this only if the valid integer and valid move.
        }
        while (counter < 2) {
          try {
            if (scan.hasNext()) {
              strInput = scan.next();
            }
            if (strInput.equalsIgnoreCase("q")) {
              quit = true;
              break;
            }

            xy[counter] = Integer.parseInt(strInput);
            counter++;

          } catch (NumberFormatException nfe) {
            String invalidInput = strInput;
            out.append("Not a valid number: ").append(invalidInput).append("\n");
            wasLastMoveAllowedInput = false;
          }
        }
        counter = 0; // reset counter if a move in the format [int ,int] is given.

        // append the game quit message followed by game state when the user quits.
        if (quit) {
          try {
            out.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
            return;
          } catch (IOException ioe) {
            throw new IllegalStateException("Append failed", ioe);
          }
        } else {
          m.move(xy[0] - 1, xy[1] - 1);
          wasLastMoveAllowedInput = true;
          //out.append(m.toString()).append("\n");
        }
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      } catch (IllegalArgumentException iae) {
        try {
          out.append(String.format("Not a valid move: %s, %s\n", xy[0], xy[1]));
          wasLastMoveAllowedInput = false;
        } catch (IOException ioe1) {
          throw new IllegalStateException("Append failed", ioe1);
        }
      }

    }
    while (!m.isGameOver());
    // append the game over message followed by the game state when the game is over.
    if (m.isGameOver()) {
      try {
        out.append(m.toString()).append("\n");
        out.append("Game is over! ");
        Player winner = m.getWinner();
        if (winner != null) {
          out.append(String.format("%s wins.\n", winner));
        } else {
          out.append("Tie game.\n");
        }
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed.", ioe);
      }
    }


  }

}
