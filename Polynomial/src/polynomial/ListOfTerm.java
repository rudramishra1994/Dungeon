package polynomial;

/**
 * It represents the terms of a polynomial as a list.
 */
public interface ListOfTerm {

  /**
   * Adds two polynomial together to return a polynomial.
   *
   * @param other The second polynomial.
   * @return a list of polynomial terms that is the result of addition of two polynomials.
   */
  ListOfTerm add(ListOfTerm other);

  /**
   * Adds a new term to the polynomial.
   *
   * @param coefficient coefficient of the term.
   * @param power       exponent of the term.
   * @return returns the resultant list of polynomial.
   */
  ListOfTerm addTerm(int coefficient, int power);

  /**
   * Checks equality between two terms of a polynomial.
   *
   * @param emptyNode an empty term or the terminal node of the list.
   * @return true or false.
   */
  default boolean equalsEmptyNode(TermEmptyNode emptyNode) {
    return false;
  }

  /**
   * Checks equality between two terms of a polynomial.
   *
   * @param elementNode an empty term or the terminal node of the list.
   * @return true or false.
   */

  default boolean equalsElementNode(TermElementNode elementNode) {
    return false;
  }

  /**
   * Returns the coeffient of a term of a given power.
   *
   * @param power the exponent of the term searched for.
   * @return the coefficient of the term if it exists or 0.
   */
  int getCoefficient(int power);

  /**
   * Returns the value of the polynomial for a given value.
   *
   * @param x value of the variable of the polynomial.
   * @return value of the polynomial.
   */
  double evaluate(double x);

  /**
   * Returns the degree or the highest exponent of the polynomial.
   *
   * @return Highest exponent in the polynomial
   */
  int getDegree();

  /**
   * A helper method for toString to build the string form of the polynomial.
   *
   * @param stringBuilder a stringbuilder object to contain the polynomial.
   * @return the string form of the polynomial.
   */

  String toStringHelper(StringBuilder stringBuilder);

}
