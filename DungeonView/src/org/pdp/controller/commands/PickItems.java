package org.pdp.controller.commands;

import org.pdp.controller.PickOption;
import org.pdp.controller.UserCommandInterface;
import org.pdp.game.IDungeon;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents the PICK action of the user used to pick items in the dungeon.
 */
public class PickItems implements UserCommandInterface {

  private final Appendable appendable;
  private final Scanner scan;

  /**
   * Creates a single pick action of the user.
   * @param appendable appendable to append the result of the pick action.
   * @param scan scanner object to read user inputs
   */
  public PickItems(Appendable appendable, Scanner scan) {
    if (appendable == null || scan == null) {
      throw new IllegalArgumentException("Player cannot pick items");
    }
    this.appendable = appendable;
    this.scan = scan;
  }

  @Override
  public void execute(IDungeon dungeon) {
    if (dungeon == null) {
      throw new IllegalArgumentException(" Dungeon cannot be null.Cannot execute command without a "
        + "dungeon");
    }
    List<String> allowedPickActions = Stream.of(PickOption.values())
        .map(PickOption::name)
        .collect(Collectors.toList());
    try {
      appendable.append("What?\n");
      String pick;
      while (scan.hasNext()) {
        pick = scan.next();
        if (allowedPickActions.contains(pick)) {

          if (pick.equals(PickOption.ARROW.name())) {
            if (dungeon.pickArrow()) {
              appendable.append("There is no Arrow at this location");
            }
          } else if (pick.equals(PickOption.TREASURE.name())) {
            if (dungeon.pickTreasure()) {
              appendable.append("There is no Treasure at this location");
            }
          }
          return;
        } else {
          appendable.append("Invalid Item.Please select a valid item\n");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append Fail!!");
    }

  }
}
