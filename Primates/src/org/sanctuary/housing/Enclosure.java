package org.sanctuary.housing;

import org.animal.primates.Primates;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Enclosure of the sanctuary which houses a group of Primates of same species.
 */
class Enclosure implements Housing {

  private List<Primates> occupants;
  private double unOccupiedSpace;
  private final String housingID;
  private static final String housingType = "ENCLOSURE";

  /**
   * Creates a housing of enclosure type of given size.
   */
  Enclosure(int enclosureNumber, double sizeOfEnclosure) {
    if (sizeOfEnclosure <= 0) {
      throw new IllegalArgumentException("Size of the enclosure cannot be negative or zero");
    }
    this.housingID = String.format("Enclosure%s", enclosureNumber);

    this.unOccupiedSpace = sizeOfEnclosure;
    occupants = new ArrayList<>();
  }

  @Override
  public boolean removePrimate(String primateID) {
    for (Primates primate : occupants) {
      if (primate.getName().equals(primateID)) {
        occupants.remove(primate);
        updateOccupiedSpace(-primate.spaceNeededByThePrimate());
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean addPrimates(Primates primate) {
    if (this.unOccupiedSpace >= primate.spaceNeededByThePrimate()) {
      occupants.add(primate);
      updateOccupiedSpace(primate.spaceNeededByThePrimate());
      return true;
    }
    return false;

  }

  @Override
  public boolean isOccupied() {
    return occupants.size() >= 1;
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

  private void updateOccupiedSpace(double spaceNeededByPrimate) {
    this.unOccupiedSpace -= spaceNeededByPrimate;
  }


}
