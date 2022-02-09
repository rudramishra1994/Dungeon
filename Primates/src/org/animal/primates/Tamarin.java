package org.animal.primates;

/**
 * Represents Tamarin species of the new world primate.
 */
public class Tamarin extends NewWorldPrimate {
  /**
   * Constructs an instance of primate belonging to the Tamarin species.
   *
   * @param name          represents name of the primate of Tamarin species.
   * @param sex           represents sex of the primate of Tamarin species.
   * @param age           represents age of the primate of Tamarin species.
   * @param weight        represents weight of the primate of Tamarin species.
   * @param size          represents size of the primate of Tamarin species.
   * @param favouriteFood represents favourite food of the Tamarin species.
   */
  public Tamarin(String name, String sex, int age, double weight, double size,
                 String favouriteFood) {
    super(name, sex, age, weight, size, favouriteFood);
  }

}
