package org.pdp.controller.commands;

import org.pdp.controller.AllowedDirectionInputs;
import org.pdp.controller.UserCommandInterface;
import org.pdp.game.Direction;
import org.pdp.game.IDungeon;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * It is a command class that represents moving of the player in the dungeon.
 *
 */
public class MovePlayer implements UserCommandInterface {

  private final Appendable appendable;
  private final Scanner scan;
  private final Map<String, Direction> directionMap;

  /**
   * Creates an object that represents move action of the player.
   * @param appendable an object to which output of the game is written.
   * @param scan a scanner object used to read user input from the System.in.
   * @param directionMap a map of direction and if door exit in that direction at a location.
   */
  public MovePlayer(Appendable appendable, Scanner scan, Map<String, Direction> directionMap) {
    if (appendable == null || scan == null || directionMap == null) {
      throw new IllegalArgumentException("Player will not be able to move");
    }
    this.appendable = appendable;
    this.scan = scan;
    this.directionMap = directionMap;
  }

  /**
   * A method responsible for moving the player through the dungeon in the given direction.
   */
  @Override
  public void execute(IDungeon dungeon) {
    if (dungeon == null) {
      throw new IllegalArgumentException(" Dungeon cannot be null.Cannot execute command without a "
        + "dungeon");
    }
    List<String> allowedInputsDirection = Stream.of(AllowedDirectionInputs.values())
        .map(AllowedDirectionInputs::name)
        .collect(Collectors.toList());
    try {
      appendable.append("Direction?\n");
      String direction;
      while (scan.hasNext()) {
        direction = scan.next();
        if (allowedInputsDirection.contains(direction)) {

          boolean moveSuccessful = dungeon.movePlayer(directionMap.get(direction));
          if (moveSuccessful) {
            appendable.append("Player was moved in the specified direction\n");
          } else {
            appendable.append("Player move unsuccessful\n");
          }
          if (!dungeon.isPlayerAlive()) {
            appendable.append("Killed by Monster!!Game Over!! You lost!!\n");
            return;
          } else if (dungeon.hasReachedGoal() && dungeon.isPlayerAlive()) {
            appendable.append("GAME OVER!!!!!!!You have won!!!\n");
            return;
          }
          break;
        } else {
          appendable.append("Invalid Direction.Please give a valid direction\n");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append Failed");
    }


  }
}
