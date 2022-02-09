package org.sanctuary.housing;

import org.animal.primates.Primates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A class which represents a sanctuary which houses New World Species.
 */
public class JunglePrimateSanctuary implements Sanctuary {


  private List<Housing> houselist;
  private int numOfIsolation;
  private int numOfEnclosure;
  private final double sizeOfEachEnclosure;
  private static final String ENCLOSURE = "ENCLOSURE";
  private static final String ISOLATION = "ISOLATION";

  /**
   * Creates instance of Jungle Primate Sanctuary which houses New World Primate.
   *
   * @param numOfIsolation      int :number of isolation cages
   * @param numberOfEnclosure   int : number of enclosures
   * @param sizeOfEachEnclosure int:size of each enclosure.
   */
  public JunglePrimateSanctuary(int numOfIsolation, int numberOfEnclosure,
                                int sizeOfEachEnclosure) {
    if (numberOfEnclosure <= 0 || numOfIsolation <= 0 || sizeOfEachEnclosure <= 0) {
      throw new IllegalArgumentException("Number of enclosure and isolation has to be positive");
    }
    this.houselist = new ArrayList<>();
    this.numOfEnclosure = numberOfEnclosure;
    this.numOfIsolation = numOfIsolation;
    this.sizeOfEachEnclosure = sizeOfEachEnclosure;
    try {
      for (int i = 0; i < numOfIsolation; i++) {
        houselist.add(new Isolation(i));
      }
      for (int i = 0; i < numberOfEnclosure; i++) {
        houselist.add(new Enclosure(i, sizeOfEachEnclosure));
      }
    } catch (IllegalArgumentException ial) {
      throw new IllegalArgumentException("Enter Valid size for the enclosures");
    }
  }

  @Override
  public Map<String, String> fetchListOfPrimatesInAlphabeticalOrderWithLocation() {
    Map<String, String> primate_LocationList = new TreeMap<>();
    for (Housing house : houselist) {
      if (house.isOccupied()) {
        List<Primates> occupants = house.getOccupants();
        for (Primates primate : occupants) {
          primate_LocationList.put(primate.getName(), house.getHouseID());
        }
      }
    }
    if (primate_LocationList.size() == 0) {
      throw new IllegalStateException("No Primate Present in the Sanctuary");
    }
    return primate_LocationList;
  }

  @Override
  public Map<String, List<String>> fetchSpeciesInAlphabeticalOrderAndTheirLocationInSanctuary() {
    Map<String, List<String>> speciesLocationInAlphabeticalOrder = new HashMap<>();
    for (Housing housing : houselist) {
      for (Primates p : housing.getOccupants()) {
        if (!speciesLocationInAlphabeticalOrder.containsKey(p.getSpecies())) {
          List<String> house = new ArrayList<>();
          speciesLocationInAlphabeticalOrder.put(p.getSpecies(), house);
        }
        List<String> locations = speciesLocationInAlphabeticalOrder.get(p.getSpecies());
        if (!locations.contains(housing.getHouseID())) {
          locations.add(housing.getHouseID());
        }
      }
    }
    return speciesLocationInAlphabeticalOrder;
  }

  @Override
  public List<String> fetchLocationOfAGivenSpecies(String species) {
    List<String> locations = new ArrayList<>();
    for (Housing house : houselist) {
      for (Primates primate : house.getOccupants()) {
        if (primate.getSpecies().equalsIgnoreCase(species)) {
          locations.add(house.getHouseID());
        }
        break;
      }
    }
    if (locations.size() == 0) {
      throw new IllegalArgumentException("Species not found in the Sanctuary");
    }
    return locations;
  }

  @Override
  public Map<String, Integer> fetchShoppingList() {
    Map<String, Integer> shoppingList = new TreeMap<>();
    for (Housing house : houselist) {
      if (house.isOccupied()) {
        for (Primates primate : house.getOccupants()) {
          int quantity;
          double primateSize = primate.getSize();
          if (primateSize > 20) {
            quantity = 500;
          } else if (primateSize >= 10 && primateSize < 20) {
            quantity = 250;
          } else {
            quantity = 100;
          }
          shoppingList.put(primate.getFavouriteFood(),
                  shoppingList.getOrDefault(primate.getFavouriteFood(),
                          0) + quantity);
        }
      }
    }
    if (shoppingList.size() == 0) {
      throw new IllegalStateException("No primates in the sanctuary");
    }
    return shoppingList;

  }

  @Override
  public void removePrimateFromSanctuary(String primateID) {
    boolean occupantRemoved = false;
    for (Housing house : houselist) {
      if (house.isOccupied()) {
        List<Primates> occupants = house.getOccupants();
        for (Primates primate : occupants) {
          if (primate.getName().equals(primateID)) {
            occupants.remove(primate);
            occupantRemoved = true;
            break;
          }
        }
      }
    }
    if (!occupantRemoved) {
      throw new IllegalArgumentException("Incorrect primateID.No such primate exists");
    }

  }

  @Override
  public List<Primates> fetchEnclosureInfo(String housingID) {
    for (Housing house : houselist) {
      if (house.getHouseID().equals(housingID)) {
        return house.getOccupants();
      }
    }
    throw new IllegalArgumentException("No such enclosure present in the sanctuary");
  }

  @Override
  public void addNewPrimateToIsolation(Primates species) throws IllegalArgumentException {
    if (checkIfPrimateNamePresent(species)) {
      throw new IllegalArgumentException("Primate Name Already Present "
              + "Please Use a different name.");
    }
    for (Housing house : houselist) {
      if (house.getHousingType().equalsIgnoreCase("isolation") && !house.isOccupied()) {
        if (house.addPrimates(species)) {
          return;
        }
      }

    }
    throw new ArrayIndexOutOfBoundsException("No Available Isolation left");
  }


  @Override
  public void increaseCapacityOfTheSanctuary(int isolation, int enclosure) {

    if (isolation < 0 || enclosure < 0) {
      throw new IllegalArgumentException("Increase in capacity cannot be zero");
    }
    for (int i = numOfIsolation + 1; i <= numOfIsolation + isolation; i++) {
      this.houselist.add(new Isolation(i));
    }
    for (int i = numOfEnclosure + 1; i <= numOfIsolation + isolation; i++) {
      this.houselist.add(new Enclosure(i, sizeOfEachEnclosure));
    }
    numOfIsolation += isolation;
    numOfEnclosure += enclosure;

  }


  @Override
  public Map<String, String> fetchAllHousingInformation() {
    Map<String, String> listOfAllHousing = new TreeMap<>();
    for (Housing house : houselist) {
      listOfAllHousing.put(house.getHouseID(), house.getHousingType());
    }
    return listOfAllHousing;

  }

  @Override
  public void movePrimateToAGivenHousing(String primateId, String housingId) {
    try {
      Housing source = getSource(primateId);
      Housing h = getTargetHousing(housingId);
      Primates p = getTargetPrimate(primateId);
      if (h.getHousingType().equals(ISOLATION)) {
        if (!h.isOccupied()) {
          h.addPrimates(p);
        } else {
          source.addPrimates(p);
          throw new IllegalArgumentException("Enter a different housing Id."
                  + "Current housing is occupied");
        }
      }
      if (h.getHousingType().equals(ENCLOSURE)) {
        if (h.isOccupied()) {
          if (h.getOccupants().get(0).getSpecies().equals(p.getSpecies())) {
            boolean primateMoved = h.addPrimates(p);

            if (!primateMoved) {
              source.addPrimates(p);
              throw new IllegalArgumentException("The Enclosure does not have enough space"
                      + "Please Enter a new enclosure");
            }
          } else {
            throw new IllegalArgumentException("The Enclosure has different species.please "
                    + "Enter a new enclosure");
          }
        } else {
          h.addPrimates(p);
        }
      }
    } catch (IllegalArgumentException ial) {
      throw new IllegalArgumentException(ial.getMessage());
    }
  }


  private Primates getTargetPrimate(String primateId) {
    Primates target;
    for (Housing house : houselist) {
      for (Primates p : house.getOccupants()) {
        if (p.getName().equals(primateId)) {
          target = p;
          house.removePrimate(primateId);
          return target;
        }
      }
    }
    throw new IllegalArgumentException("Incorrect primate ID.Enter correct primate Id move.");
  }

  private Housing getSource(String primateId) {
    for (Housing house : houselist) {
      for (Primates p : house.getOccupants()) {
        if (p.getName().equals(primateId)) {
          return house;
        }
      }
    }
    throw new IllegalArgumentException("Incorrect primate ID.Enter correct primate Id move.");
  }

  private Housing getTargetHousing(String housingId) {
    for (Housing house : houselist) {
      if (house.getHouseID().equals(housingId)) {
        return house;
      }
    }
    throw new IllegalArgumentException("Incorrect House Id.Enter the correct house Id");

  }

  private boolean checkIfPrimateNamePresent(Primates primates) {
    for (Housing house : houselist) {
      for (Primates p : house.getOccupants()) {
        if (p.getName().equalsIgnoreCase(primates.getName())) {
          return true;
        }
      }
    }
    return false;
  }


}
