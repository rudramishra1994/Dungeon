package org.pdp.random;

/**
 * An interface that represents two types of random number generator.
 * A deterministic random number generator.
 * A pure random number generator.
 */
public interface IRandom {
  /**
   * Returns a pseudo random number in the given range.
   *
   * @param lLimit lower bound.
   * @param hLimit upper bound.
   * @return an integer between lower and upper bound both inclusive.
   */
  int getRandomInteger(int lLimit, int hLimit);
}
