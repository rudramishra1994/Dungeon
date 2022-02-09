package org.pdp.random;

/**
 * Returns a deterministic random number generator which return the upper bound of the range.
 */
public class Upperboundrandom implements IRandom {

  /**
   * Returns the upper bound of the range given.
   *
   * @param lLimit Lower Limit.
   * @param hLimit Upper Limit.
   * @return a random integer.
   */
  @Override
  public int getRandomInteger(int lLimit, int hLimit) {
    return hLimit;
  }
}
