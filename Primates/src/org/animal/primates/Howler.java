package org.animal.primates;

/**
 * Represents Howler species of the new world primate.
 */
public class Howler extends NewWorldPrimate {
  /**
   * Constructs an instance of primate belonging to the Howler species.
   *
   * @param name          represents name of the primate of Howler species.
   * @param sex           represents sex of the primate of Howler species.
   * @param age           represents age of the primate of Howler species.
   * @param weight        represents weight of the primate of howler species.
   * @param size          represents size of the primate of Howler species.
   * @param favouriteFood represents favourite food of the howler species.
   */
  public Howler(String name, String sex, int age, double weight, double size,
                String favouriteFood) {
    super(name, sex, age, weight, size, favouriteFood);
  }
}
