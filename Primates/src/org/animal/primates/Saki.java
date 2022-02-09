package org.animal.primates;

/**
 * Represents Saki species of the new world primate.
 */

public class Saki extends NewWorldPrimate {

  /**
   * Constructs an instance of primate belonging to the Saki species.
   *
   * @param name          represents name of the primate of Saki species.
   * @param sex           represents sex of the primate of Saki species.
   * @param age           represents age of the primate of Saki species.
   * @param weight        represents weight of the primate of Saki species.
   * @param size          represents size of the primate of Saki species.
   * @param favouriteFood represents favourite food of the Saki species.
   */
  public Saki(String name, String sex, int age, double weight, double size, String favouriteFood) {
    super(name, sex, age, weight, size, favouriteFood);
  }
}
