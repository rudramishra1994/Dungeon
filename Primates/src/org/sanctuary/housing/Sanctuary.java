package org.sanctuary.housing;

import org.animal.primates.Primates;

import java.util.List;
import java.util.Map;

/**
 * It is an interface for any sanctuary similar to Jungle Primate Sanctuary.
 * Assumption being made is that the functionalities provided are same.
 */
public interface Sanctuary {
  /**
   * Returns the list of primates and their location in alphabetical order of their name.
   *
   * @return Map of Primates and their location.
   */
  Map<String, String> fetchListOfPrimatesInAlphabeticalOrderWithLocation();

  /**
   * Returns the species and where they are located.
   *
   * @return Map of primates and species.
   */
  Map<String, List<String>> fetchSpeciesInAlphabeticalOrderAndTheirLocationInSanctuary();

  /**
   * returns the location at which a particular species can be found.
   *
   * @param species to look for
   * @return Map of the species and its location
   */
  List<String> fetchLocationOfAGivenSpecies(String species);

  /**
   * Returns a map of favourite food and its quantity.
   *
   * @return map of favourite food and its quantity.
   */
  Map<String, Integer> fetchShoppingList();

  /**
   * Remove a particular primate from the sanctuary.
   *
   * @param primateID unique primate id.
   */
  void removePrimateFromSanctuary(String primateID);

  /**
   * Returns the list of primate when given an enclosure.
   *
   * @param housingID a unique string
   * @return list of primates.
   */
  List<Primates> fetchEnclosureInfo(String housingID);

  /**
   * Adds a new primate arriving to isolation.
   *
   * @param primates of the primate.
   */
  void addNewPrimateToIsolation(Primates primates);



  /**
   * Increase the capacity of the sanctuary.
   *
   * @param isolation number of isolation to increase.
   * @param enclosure number of enclosure to increase.
   */
  void increaseCapacityOfTheSanctuary(int isolation, int enclosure);


  /**
   * Fetch all the housing information.
   *
   * @return list of different housing.
   */
  Map<String, String> fetchAllHousingInformation();

  /**
   * Moves the primate from its current location to a given housing.
   * @param isolation takes the houseId of destination isolation
   */
  void movePrimateToAGivenHousing(String primateId,String isolation);


}
