package org.pdp.controller.commands;

import org.pdp.controller.AllowedDirectionInputs;
import org.pdp.controller.UserCommandInterface;
import org.pdp.game.Arrowtracer;
import org.pdp.game.Direction;
import org.pdp.game.IDungeon;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * It a class that represents the SHOOT action of the user.
 */
public class ShootArrow implements UserCommandInterface {


  private final Appendable appendable;
  private final Scanner scan;
  private final Map<String, Direction> directionMap;


  /**
   * Creates a shootArrow command which is responsible for shooting an arrow in a given direction.
   * @param appendable appendable object to append output to.
   * @param scan a scanner object to read next input from a readable object.
   * @param directionMap a map of direction and a boolean value.
   */
  public ShootArrow(Appendable appendable, Scanner scan, Map<String, Direction> directionMap) {
    if (appendable == null || scan == null || directionMap == null) {
      throw new IllegalArgumentException("Player will not be able to shoot");
    }
    this.appendable = appendable;
    this.scan = scan;
    this.directionMap = directionMap;
  }

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
      String direction = "N";//Default direction.
      while (scan.hasNext()) {
        direction = scan.next();
        if (!allowedInputsDirection.contains(direction)) {
          appendable.append("Invalid Direction.Please give a valid direction\n");
          continue;
        }
        break;
      }
      appendable.append("Distance(1-5)\n");
      int distance;

      while (scan.hasNext()) {
        try {
          distance = Integer.parseInt(scan.next());
          Arrowtracer trace = dungeon.shootArrow(distance, directionMap.get(direction));
          if (trace.getHits() == 1) {
            appendable.append("Monster has been hit!!! You hear a loud roar\n");
          } else if (trace.getHits() == 0) {
            appendable.append("You fired an arrow into the darkness\n");
          } else if (trace.getHits() == 2) {
            appendable.append("You killed a Monster!!!!!\n");
          }
          return;

        } catch (NumberFormatException n) {
          appendable.append("Enter a valid integer between 1 and 5\n");
        } catch (IllegalArgumentException ie) {
          appendable.append(ie.getMessage());
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append Failed");
    }


  }
}
