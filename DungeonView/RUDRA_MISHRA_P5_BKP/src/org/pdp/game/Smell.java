package org.pdp.game;

/**
 * The Intensity of smell at a location based on monster proximity.
 * Heavy if a monstor is 1 step way.
 * Heavy if multiple monsters at 2 step distance.
 * Light if 1 monster at two step distance.
 * No smell other wise.
 */
public enum Smell {
  LIGHT,
  HEAVY,
  NOSMELL;

}
