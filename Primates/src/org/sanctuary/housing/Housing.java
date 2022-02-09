package org.sanctuary.housing;

import org.animal.primates.Primates;

import java.util.List;

/**
 * A common representation for the different type of housing available in the sanctuary.
 */
interface Housing {
  /**
   * Removes a given primate from the housing and returns the primate.
   */
  boolean removePrimate(String primateId);

  /**
   * Add a given primate to the housing.
   */
  boolean addPrimates(Primates primate);

  /**
   * Checks if the housing is occupied.
   *
   * @return True or False
   */
  boolean isOccupied();

  /**
   * Returns the list of primates in the housing.
   *
   * @return returns a list of Primates.
   */
  List<Primates> getOccupants();

  /**
   * Returns the uniqueHouseID of the housing.
   *
   * @return String UUID
   */
  String getHouseID();

  /**
   * Returns the type of housing.
   *
   * @return String : ISOLATION,ENCLOSURE... etc
   */
  String getHousingType();


}
