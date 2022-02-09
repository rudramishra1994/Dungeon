package org.animal.primates;

/**
 * This class implements the behavior and the defines the characteristics common to species.
 */
abstract class NewWorldPrimate implements Primates {

  protected String name;
  protected String sex;
  protected int age;
  protected double weight;
  protected double size;
  protected String favouriteFood;
  private static final String MALE = "male";
  private static final String FEMALE = "female";
  private static final int SIZE_SMALL = 10;
  private static final int SIZE_BIG = 20;
  private static final int SPACE_SMALL = 1;
  private static final int SPACE_MID = 5;
  private static final int SPACE_LARGE = 20;


  protected NewWorldPrimate(String name, String sex, int age, double weight, double size,
                            String favouriteFood) {
    if (age <= 0) {
      throw new IllegalArgumentException("Age of the primate cannot be negative or zero");
    }
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight of the primate cannot be negative or zero");
    }

    if (size <= 0) {
      throw new IllegalArgumentException("Size of the Primate cannot be negative or zero");
    }

    if (!(sex.equalsIgnoreCase(MALE) || sex.equalsIgnoreCase(FEMALE))) {
      throw new IllegalArgumentException("Sex of the primate has to be \"male\" or \"female\"");
    }
    FavouriteFood[] food = FavouriteFood.values();
    boolean foodAllowed = false;
    for (FavouriteFood f : food) {
      if (f.name().equalsIgnoreCase(favouriteFood)) {
        foodAllowed = true;
        break;
      }
    }

    if (!foodAllowed) {
      throw new IllegalArgumentException("Favourite Food is not allowed");
    }

    this.age = age;
    this.name = name;
    this.sex = sex;
    this.size = size;
    this.weight = weight;
    this.favouriteFood = favouriteFood;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getSex() {
    return sex;
  }

  @Override
  public int getAge() {
    return age;
  }

  @Override
  public double getWeight() {
    return weight;
  }

  @Override
  public double getSize() {
    return size;
  }

  @Override
  public String getFavouriteFood() {
    return favouriteFood;
  }

  @Override
  public void updateWeight(double weight) {
    this.weight = weight;
  }

  @Override
  public void updateAge(int age) {
    this.age = age;
  }

  @Override
  public void updateSize(double size) {
    this.size = size;
  }


  @Override
  public double spaceNeededByThePrimate() {
    double spaceNeeded;
    if (size < SIZE_SMALL) {
      spaceNeeded = SPACE_SMALL;
    } else if (size >= SIZE_SMALL && size < SIZE_BIG) {
      spaceNeeded = SPACE_MID;
    } else {
      spaceNeeded = SPACE_LARGE;
    }
    return spaceNeeded;
  }

  @Override
  public String getSpecies() {
    return this.getClass().getSimpleName();
  }

  @Override
  public String toString() {
    return String.format("Name=%s  Sex=%s Favourite Food=%s",
            this.name, this.sex, this.favouriteFood);
  }

}
