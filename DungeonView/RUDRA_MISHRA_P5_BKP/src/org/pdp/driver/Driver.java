package org.pdp.driver;

import org.pdp.controller.CommandPatternController;
import org.pdp.controller.ICommandPatternController;
import org.pdp.controller.IController;
import org.pdp.controller.SwingViewController;
import org.pdp.game.Dungeon;
import org.pdp.game.IDungeon;
import org.pdp.random.ProdRandom;
import org.pdp.view.GameStartUp;
import org.pdp.view.GridMainWindow;
import org.pdp.view.IGameMainView;

import java.io.InputStreamReader;

/**
 * The driver class of the application. This is the first class from which the execution starts.
 * The class is responsible to help user interact to the application by reading user inputs from
 * the console.
 */
public class Driver {
  /**
   * The main function or the starting point of execution of the program.
   *
   * @param args an array of string arguments read from the console.
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      try {
        int row = Integer.parseInt(args[0]);
        int col = Integer.parseInt(args[1]);
        int wrapping = Integer.parseInt(args[2]);
        int interconnectivity = Integer.parseInt(args[3]);
        double treasure = Double.parseDouble(args[4]);
        int monsters = Integer.parseInt(args[5]);
        IDungeon dungeonModel = Dungeon.getBuilder()
            .setCol(col)
            .setRow(row)
            .setRandom(new ProdRandom())
            .setInterconnectivity(interconnectivity)
            .setType(wrapping)
            .setPercentageTreasure(treasure)
            .setNumOfMonsters(monsters)
            .setPlayerName("John Wick").build();
        Readable readable = new InputStreamReader(System.in);
        Appendable appendable = System.out;
        ICommandPatternController cpd = new CommandPatternController(readable, appendable,
            dungeonModel);
        cpd.playGame();
      } catch (NumberFormatException n) {
        System.err.println("Invalid command line argument.Please enter valid arguments");
        System.exit(-1);
      } catch (IllegalStateException | IllegalArgumentException e) {
        System.err.println(e.getMessage());
      }
      new GameStartUp();
    } else {
      try {
        IDungeon dungeon = Dungeon.getBuilder().build();
        IGameMainView view = new GridMainWindow(dungeon);
        IController controller = new SwingViewController(dungeon);
        controller.setView(view);
        controller.execute();
      } catch (IllegalArgumentException ex) {
        System.err.println(ex.getMessage());
      }

    }


  }


}
