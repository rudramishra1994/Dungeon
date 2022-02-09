package org.pdp.controller;

import org.pdp.controller.commands.MovePlayer;
import org.pdp.controller.commands.PickItems;
import org.pdp.controller.commands.ShootArrow;
import org.pdp.game.Direction;
import org.pdp.game.IDungeon;
import org.pdp.game.Smell;
import org.pdp.game.Treasures;
import org.pdp.game.Weapon;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller which is responsible for taking input from the user performing those actions.
 * These actions being MOVE/SHOOT/PICK.
 */
public class CommandPatternController implements ICommandPatternController {
  private final Readable readable;
  private final Appendable appendable;
  private final IDungeon dungeon;
  private static final String option = "MOVE,PICK or SHOOT.Enter command exactly as mentioned\n";


  /**
   * Creates o contoller object which then takes user input to perform user requested actions.
   * @param readable a readable object to read input from.
   * @param appendable an appendable object to put output to.
   * @param dungeon a dungeon object.
   */
  public CommandPatternController(Readable readable, Appendable appendable, IDungeon dungeon) {
    if (readable == null || appendable == null || dungeon == null) {
      throw new IllegalArgumentException("Controller creation failed");
    }
    this.readable = readable;
    this.appendable = appendable;
    this.dungeon = dungeon;
  }

  @Override
  public void playGame() {
    Map<String, Direction> directionMap = getDirectionMap();
    Scanner scan = new Scanner(readable);
    UserCommandInterface uci ;
    List<String> allowedPlayerActions = Stream.of(PlayerAction.values())
        .map(PlayerAction::name)
        .collect(Collectors.toList());

    try {

      appendable.append("Welcome to Maze Runner\n");
      appendable.append("Find your path through the maze fighting monsters and picking "
          + "treasures\n");
      appendable.append("Enter N to move player towards North\n");
      appendable.append("Enter S to move player towards South\n");
      appendable.append("Enter E to move player towards East\n");
      appendable.append("Enter W to move player towards West\n");
      appendable.append("Enter \"EXIT\" to exit the game\n");
      appendPlayerAndLocationDescription();
      while (scan.hasNext() && dungeon.isPlayerAlive() && !dungeon.hasReachedGoal()) {
        String option = scan.next();
        if (!allowedPlayerActions.contains(option)) {
          appendable.append("Enter a valid option.Enter option exactly as mentioned\n");
          continue;
        }
        switch (option) {
          case "EXIT":
            appendable.append("Exiting Game\n");
            return;
          case "MOVE":
            uci = new MovePlayer(appendable, scan, directionMap);
            uci.execute(dungeon);
            if ((dungeon.hasReachedGoal() && dungeon.isPlayerAlive()) || !dungeon.isPlayerAlive()) {
              return;
            }
            appendPlayerAndLocationDescription();
            break;
          case "PICK":
            uci = new PickItems(appendable, scan);
            uci.execute(dungeon);
            appendPlayerAndLocationDescription();
            break;
          case "SHOOT":
            uci = new ShootArrow(appendable, scan, directionMap);
            uci.execute(dungeon);
            appendPlayerAndLocationDescription();
            break;
          default:
            break;
        }
      }

    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed");
    }
  }

  private void appendPlayerAndLocationDescription() {
    Map<Direction, Boolean> allowedDirection = dungeon.getCurrentLocationAllowedDirection();
    Map<Treasures, Integer> treasuresAtCurrLocation = dungeon.getCurrentLocationTreasures();
    Map<Treasures, Integer> treasureCollectedPlayer = dungeon.getPlayerDescription();
    Map<Weapon, Integer> weaponAtCurrLocation = dungeon.getLocationWeapon();
    Map<Weapon, Integer> weaponOnPlayer = dungeon.getPlayerWeaponCollected();
    Smell intensity = dungeon.getSmellIntensity(dungeon.getPlayer().getPlayerLocation());
    try {
      if (intensity.equals(Smell.HEAVY)) {
        appendable.append("There is a very pungent smell in the air.\n");
      } else if (intensity.equals(Smell.LIGHT)) {
        appendable.append("There is a light pungent smell in the air.\n");
      }
      if (allowedDirection.values().stream().filter(p -> p).count() != 2) {
        appendable.append("Currently in a cave\n");
      } else {
        appendable.append("Currently in a tunnel\n");
      }
      for (Treasures t : treasureCollectedPlayer.keySet()) {
        appendable.append(String.format("You have gathered %s %s\n", treasureCollectedPlayer.get(t),
            t.name()));
      }

      for (Weapon w : weaponOnPlayer.keySet()) {
        appendable.append(String.format("You have %s %s\n",
            weaponOnPlayer.get(w),
            w.name()));
      }
      for (Treasures t : treasuresAtCurrLocation.keySet()) {
        appendable.append(String.format("Current location has %s %s\n",
            treasuresAtCurrLocation.get(t),
            t.name()));
      }
      for (Weapon w : weaponAtCurrLocation.keySet()) {
        appendable.append(String.format("Current location has %s %s\n",
            weaponAtCurrLocation.get(w),
            w.name()));
      }
      appendable.append("Doors are in  ");
      for (Direction d : allowedDirection.keySet()) {
        if (allowedDirection.get(d)) {
          appendable.append(d.name());
          appendable.append(" ");
        }
      }
      appendable.append("\n");
      appendable.append(option);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append fail!!!");
    }
  }

  private Map<String, Direction> getDirectionMap() {
    Map<String, Direction> directionMap = new TreeMap<>();
    directionMap.put("N", Direction.NORTH);
    directionMap.put("S", Direction.SOUTH);
    directionMap.put("E", Direction.EAST);
    directionMap.put("W", Direction.WEST);
    return directionMap;
  }




}
