package org.animal.primates;

/**
 * Represents the general class of tree dwelling animals called primates.
 * Generally primates are found in the dense tropical forest.
 * This class identifies many identifiers of each primate such as name,sex ,age,weight,size
 * and favourite food.
 */
public interface Primates {

  /**
   * Returns the name of the primate.
   */
  String getName();

  /**
   * Returns the sex of the primate.
   */
  String getSex();

  /**
   * Returns the age of the primate.
   */
  int getAge();

  /**
   * returns the weight of the primate.
   */
  double getWeight();

  /**
   * returns the size of th primate in inches.
   */
  double getSize();

  /**
   * returns the favourite food of the primate.
   */
  String getFavouriteFood();

  /**
   * Updates the weight of the primate.
   */
  void updateWeight(double weight);

  /**
   * Updates the Age of the Primate.
   */
  void updateAge(int age);

  /**
   * Updates the size of the primate.
   */
  void updateSize(double size);


  /**
   * Returns the space requirement of a primate in the enclosure.
   *
   * @return space needed by a primate depending on its size.
   */
  double spaceNeededByThePrimate();

  /**
   * Returns the type of species of the primate.
   *
   * @return String: species.
   */
  String getSpecies();


  /**
   * Represents a string representation of object.
   */
  String toString();

}
