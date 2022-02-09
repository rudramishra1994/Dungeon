package org.animal.primates;

/**
 * Represents Squirrel species of the new world primate.
 */
public class Squirrel extends NewWorldPrimate {
  /**
   * Constructs an instance of primate belonging to the Squirrel species.
   *
   * @param name          represents name of the primate of Squirrel species.
   * @param sex           represents sex of the primate of Squirrel species.
   * @param age           represents age of the primate of Squirrel species.
   * @param weight        represents weight of the primate of Squirrel species.
   * @param size          represents size of the primate of Squirrel species.
   * @param favouriteFood represents favourite food of the Squirrel species.
   */
  public Squirrel(String name, String sex, int age, double weight, double size,
                  String favouriteFood) {
    super(name, sex, age, weight, size, favouriteFood);
  }
}
