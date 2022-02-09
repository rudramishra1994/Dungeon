package org.pdp.random;

import java.util.Random;

/**
 * A randomizer that generates a random integer in the given range.
 */
public class ProdRandom implements IRandom {
  /**
   * Returns a true random integer in the given range.
   * @param lLimit Lower Limit.
   * @param hLimit Upper Limit.
   * @return a random integer.
   */
  @Override
  public int getRandomInteger(int lLimit, int hLimit) {
    Random random = new Random();
    return random.nextInt(hLimit - lLimit + 1) + lLimit;
  }
}
