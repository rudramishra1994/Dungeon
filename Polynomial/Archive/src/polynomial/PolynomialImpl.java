package polynomial;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a single variable polynomial.
 */
public class PolynomialImpl implements Polynomial {

  private ListOfTerm polynomial;

  /**
   * Creates an empty polynomial to which terms can be added.
   */
  public PolynomialImpl() {
    this.polynomial = new TermEmptyNode();
  }


  /**
   * Takes a string and creates a polynomial from the string.
   *
   * @param polynomial String representation of the polynomial.
   */
  public PolynomialImpl(String polynomial) {
    if (polynomial == null || polynomial.isEmpty() || polynomial.isBlank()) {
      throw new IllegalArgumentException("Polynomial String cannot be null or empty");
    }
    this.polynomial = new TermEmptyNode();
    Map<Integer, Integer> coeffPower = new TreeMap<>(Collections.reverseOrder());
    String[] terms = polynomial.toLowerCase().split(" ");
    for (String term : terms) {
      String[] content = term.trim().split("x");
      if (content.length > 1) {
        if (content[1].contains("+") || content[1].contains("-")) {
          throw new IllegalArgumentException("The terms in the "
                  + "polynomial should be space delimited");
        }
        int key;
        int value;

        key = Integer.parseInt(content[1].substring(1));
        value = Integer.parseInt(content[0]);

        coeffPower.put(key, coeffPower.getOrDefault(key, 0) + value);
      } else {
        coeffPower.put(0, coeffPower.getOrDefault(0, 0)
                + Integer.parseInt(content[0]));
      }
    }
    coeffPower.values().removeIf(f -> f == 0f);
    for (Integer i : coeffPower.keySet()) {
      addTerm(coeffPower.get(i), i);
    }
  }

  private PolynomialImpl(ListOfTerm list) {
    this.polynomial = list;
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other instanceof PolynomialImpl) {
      PolynomialImpl o = (PolynomialImpl) other;
      return new PolynomialImpl(polynomial.add(o.polynomial));
    }
    throw new IllegalArgumentException("Not an instance of polynomial type");
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (coefficient == 0 && power > 0) {
      throw new IllegalArgumentException("Coefficients have to be "
              + "non zero integers for non-constant values");
    } else if (power < 0) {
      throw new IllegalArgumentException("Exponents cannot be negative");
    }
    polynomial = polynomial.addTerm(coefficient, power);
  }

  @Override
  public boolean isSame(Polynomial poly) {
    PolynomialImpl other = (PolynomialImpl) poly;
    return this.polynomial.equals(other.polynomial);
  }

  @Override
  public double evaluate(double x) {
    return this.polynomial.evaluate(x);
  }

  @Override
  public int getCoefficient(int power) {
    return this.polynomial.getCoefficient(power);
  }

  @Override
  public int getDegree() {
    return this.polynomial.getDegree();
  }

  /**
   * Returns a string representation of the polynomial.
   *
   * @return returns the string representation of the polynomial.ß
   */
  @Override
  public String toString() {
    return polynomial.toString();
  }
}
