package test;

import org.pdp.game.Arrowtracer;
import org.pdp.game.Direction;
import org.pdp.game.IDungeon;
import org.pdp.game.ILocation;
import org.pdp.game.IPlayer;
import org.pdp.game.Monster;
import org.pdp.game.Smell;
import org.pdp.game.Treasures;
import org.pdp.game.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mock Model used to test the Controller in Isolation.
 */
public class MockModel implements IDungeon {

  private StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }



  @Override
  public Arrowtracer shootArrow(int distance, Direction direction) {
    log.append("Arrow Shot\n");
    return new Arrowtracer();
  }

  @Override
  public boolean pickTreasure() {
    log.append("Treasures Picked\n");
    return true;
  }

  @Override
  public boolean pickArrow() {
    log.append("Treasures Picked\n");
    return true;
  }

  @Override
  public ILocation getPlayerCurrentLocation() {
    log.append("Location fetched\n");
    return new MockLocation();
  }

  @Override
  public IPlayer getPlayer() {
    log.append("Player Fetched\n");
    return new MockPlayer();
  }

  @Override
  public boolean isCave(ILocation location) {
    log.append("Location is cave\n");
    return true;
  }

  @Override
  public boolean movePlayer(int x, int y) {
    log.append("Player moved to coordinates:").append(x).append(y);
    return true;
  }

  @Override
  public boolean movePlayer(Direction direction) {
    log.append("Direction Appended:").append(direction.name()).append("\n");
    return true;
  }

  @Override
  public void resetDungeon() {
    log.append("Dungeon reset done\n");
  }

  @Override
  public List<Integer> getStartLocation() {
    log.append("Start Location coordinates fetched");
    return new ArrayList<>();
  }

  @Override
  public List<Integer> getEndLocation() {
    log.append("End Location coordinates fetched \n");
    return new ArrayList<>();
  }

  @Override
  public int distanceBetweenStartAndEnd() {
    log.append("distance beteen two location found\n");
    return 0;
  }

  @Override
  public Map<Treasures, Integer> getPlayerDescription() {
    log.append("Player Description found\n");
    return new HashMap<>();
  }

  @Override
  public Map<Weapon, Integer> getPlayerWeaponCollected() {
    log.append("Player collected weapons found\n");
    return new HashMap<>();
  }

  @Override
  public List<Monster> getMonsterCurrentLocation() {
    log.append("current location monster found\n");
    return new ArrayList<>();
  }

  @Override
  public List<Monster> getMonsterAtEndLocation() {
    log.append("end location monster obtained\n");
    return new ArrayList<>();
  }

  @Override
  public Map<Weapon, Integer> getLocationWeapon() {
    log.append("Current Weapons obtained\n");
    return new HashMap<>();
  }

  @Override
  public Map<Treasures, Integer> getCurrentLocationTreasures() {
    log.append("Current Treasures obtained\n");
    return new HashMap<>();
  }

  @Override
  public Map<Direction, Boolean> getCurrentLocationAllowedDirection() {
    log.append("Allowed Direction obtained\n");
    return new HashMap<>();
  }

  @Override
  public List<Integer> getCurrentLocation() {
    log.append("Player coordinates obtained\n");
    return new ArrayList<>();
  }

  @Override
  public Smell getSmellIntensity(ILocation location) {
    log.append("Intense Smell found\n");
    return Smell.HEAVY;
  }

  @Override
  public boolean isPlayerAlive() {
    log.append("Player found alive\n");
    return true;
  }

  @Override
  public boolean hasReachedGoal() {
    log.append("Goal reached\n");
    return true;
  }

  @Override
  public ILocation getLocationAtGivenCoordinates(int x, int y) {
    log.append("Given : ").append(String.valueOf(x)).append(" Given : ").append(y).append("\n");
    return new MockLocation();
  }

  @Override
  public ILocation getPlayerCurrentLcation() {
    log.append("Player location obtained\n");
    return new MockLocation();
  }

  @Override
  public int getDungeonRows() {
    log.append("Dungeons row obtained\n");
    return 0;
  }

  @Override
  public int getDungeonColumns() {
    log.append("Dungeon Columns obtained\n");
    return 0;
  }
}
