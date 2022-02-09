package org.sanctuary.housing;

import org.animal.primates.Primates;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an isolation cage in which only a single primate can be housed.
 */
class Isolation implements Housing {


  private List<Primates> occupants;
  private final String housingID;
  private static final String housingType = "ISOLATION";

  /**
   * Creates an isolation type housing in the sanctuary.
   */
  public Isolation(int isolationNumber) {
    this.housingID = String.format("Isolation%s", isolationNumber);
    this.occupants = new ArrayList<>();
  }

  @Override
  public boolean removePrimate(String primateID) {
    for (Primates primate : occupants) {
      if (primate.getName().equals(primateID)) {
        occupants.remove(primate);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean addPrimates(Primates primate) {
    if (occupants.size() == 0) {
      occupants.add(primate);
      return true;
    }
    return false;
  }

  @Override
  public boolean isOccupied() {
    return occupants.size() > 0;
  }

  @Override
  public List<Primates> getOccupants() {
    return occupants;
  }

  @Override
  public String getHouseID() {
    return housingID;
  }

  @Override
  public String getHousingType() {
    return housingType;
  }
}
