package polynomial;

import java.util.Objects;

class Term  {

  private int coefficient;
  private final int power;
  Term obj;


  protected Term(int coefficient, int power) {
    this.coefficient = coefficient;
    this.power = power;

  }

  protected void updateCoeffcient(int coefficient) {
    this.coefficient += coefficient;
  }

  protected int getCoefficient() {
    return coefficient;
  }

  protected int getPower() {
    return power;
  }

  @Override
  public boolean equals(Object b) {
    if (b instanceof Term) {
      Term other = (Term) b;
      return this.coefficient == other.coefficient && this.power == other.power;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(coefficient, power);
  }

  @Override
  public String toString() {
    String strCoefficient;
    if (coefficient > 0) {
      strCoefficient = String.format("%s%s", "+", coefficient);
    } else {
      strCoefficient = String.valueOf(coefficient);
    }
    if (power > 0) {
      return String.format("%sx^%s ", strCoefficient, power);
    } else {
      return String.format("%s", strCoefficient);
    }
  }
}
