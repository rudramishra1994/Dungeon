package org.animal.primates;

/**
 * Represents Guereza species of the new world primate.
 */
public class Guereza extends NewWorldPrimate {

  /**
   * Constructs an instance of primate belonging to the Guereza species.
   *
   * @param name          represents name of the primate of Guereza species.
   * @param sex           represents sex of the primate of Guereza species.
   * @param age           represents age of the primate of Guereza species.
   * @param weight        represents weight of the primate of Guereza species.
   * @param size          represents size of the primate of Guereza species.
   * @param favouriteFood represents favourite food of the Guereza species.
   */
  public Guereza(String name, String sex, int age, double weight, double size,
                 String favouriteFood) {
    super(name, sex, age, weight, size, favouriteFood);
  }
}

