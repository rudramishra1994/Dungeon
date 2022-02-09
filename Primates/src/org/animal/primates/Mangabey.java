package org.animal.primates;

/**
 * Represents Mangabey species of the new world primate.
 */
public class Mangabey extends NewWorldPrimate {
  /**
   * Constructs an instance of primate belonging to the Mangabey species.
   *
   * @param name          represents name of the primate of Mangabey species.
   * @param sex           represents sex of the primate of Mangabey species.
   * @param age           represents age of the primate of Mangabey species.
   * @param weight        represents weight of the primate of Mangabey species.
   * @param size          represents size of the primate of Mangabey species.
   * @param favouriteFood represents favourite food of the Mangabey species.
   */
  public Mangabey(String name, String sex, int age, double weight, double size,
                  String favouriteFood) {
    super(name, sex, age, weight, size, favouriteFood);

  }

}
