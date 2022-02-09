package org.animal.primates;

/**
 * Represents Spider species of the new world primate.
 */
public class Spider extends NewWorldPrimate {
  /**
   * Constructs an instance of primate belonging to the Spider species.
   *
   * @param name          represents name of the primate of Spider species.
   * @param sex           represents sex of the primate of Spider species.
   * @param age           represents age of the primate of Spider species.
   * @param weight        represents weight of the primate of Spider species.
   * @param size          represents size of the primate of Spider species.
   * @param favouriteFood represents favourite food of the Spider species.
   */
  public Spider(String name, String sex, int age, double weight, double size,
                String favouriteFood) {
    super(name, sex, age, weight, size, favouriteFood);
  }
}
