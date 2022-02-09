
package org.pdp.random;

/**
 * Returns a deterministic random number generator which return the lower bound of the range.
 */
public class Lowerboundrandom implements IRandom {
  /**
   * Returns the lower bound of the range given.
   *
   * @param lLimit lower bound.
   * @param hLimit upper bound.
   * @return lower bound of the given range.
   */
  @Override
  public int getRandomInteger(int lLimit, int hLimit) {
    return lLimit;
  }
}
