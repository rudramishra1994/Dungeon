package org.pdp.random;

/**
 * A deterministic randomizer.
 */
public class TestRandom implements IRandom {

  /**
   * Returns the average of the upper and lower limit rounded down.
   * @param lLimit Lower Limit.
   * @param hLimit Upper Limit.
   * @return average of lLimit and Hlimit.
   */
  @Override
  public int getRandomInteger(int lLimit, int hLimit) {
    return (int)Math.floor((float) (lLimit + hLimit) / 2);
  }
}
