package test;

import org.pdp.game.ILocation;
import org.pdp.game.IPlayer;
import org.pdp.game.Treasures;
import org.pdp.game.Weapon;

import java.util.HashMap;
import java.util.Map;

/**
 * Mock player to Test the controller in isolation.
 */
public class MockPlayer implements IPlayer {
  @Override
  public ILocation getPlayerLocation() {
    return new MockLocation();
  }

  @Override
  public Map<Treasures, Integer> getPlayerDescription() {
    return new HashMap<>();
  }

  @Override
  public boolean gatherTreasure() {
    return false;
  }

  @Override
  public boolean isAlive() {
    return false;
  }

  @Override
  public void updatePlayerLocation(ILocation location) {
    return;
  }

  @Override
  public void shootArrow() {
    return;
  }

  @Override
  public boolean gatherWeapon() {
    return false;
  }

  @Override
  public void setPlayerStatus(boolean status) {
    return;
  }

  @Override
  public Map<Weapon, Integer> getPlayerWeapon() {
    return new HashMap<>();
  }

  @Override
  public String getPlayerName() {
    return "Wick";
  }

  @Override
  public void resetPlayer() {
    return;
  }
}
