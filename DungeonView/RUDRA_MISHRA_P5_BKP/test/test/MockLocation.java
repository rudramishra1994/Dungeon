package test;

import org.pdp.game.Direction;
import org.pdp.game.ILocation;
import org.pdp.game.Monster;
import org.pdp.game.Treasures;
import org.pdp.game.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mock location used for testing the controller in insolation.
 */
public class MockLocation implements ILocation {
  @Override
  public int getXCoordinate() {
    return 0;
  }

  @Override
  public int getYCoordinate() {
    return 0;
  }

  @Override
  public void setTreasureInLocation(Treasures treasures) {
    return;
  }

  @Override
  public Map<Treasures, Integer> removeTreasureInLocation() {
    return null;
  }

  @Override
  public Map<Weapon, Integer> removeWeaponInLocation() {
    return null;
  }

  @Override
  public void removeMonster() {
    return;
  }

  @Override
  public void setAllowedDirection(Direction direction) {
    return;
  }

  @Override
  public Map<Treasures, Integer> getTreasureList() {
    return new HashMap<>();
  }

  @Override
  public Map<Weapon, Integer> getWeaponList() {
    return new HashMap<>();
  }

  @Override
  public List<Monster> getMonsters() {
    return new ArrayList<>();
  }

  @Override
  public void setWeapon(Weapon weapon) {
    return;
  }

  @Override
  public void setMonster(Monster monster) {
    return;
  }

  @Override
  public Map<Direction, Boolean> getAllowedDirectionToMove() {
    return new HashMap<>();
  }

  @Override
  public void updateHit() {
    return;
  }

  @Override
  public boolean isVisited() {
    return true;
  }

  @Override
  public void setVisited(boolean visited) {
    return;
  }
}
