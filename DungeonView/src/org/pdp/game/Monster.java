package org.pdp.game;

/**
 * Represents monsters that can be present in the dungeon.
 * Currently only one type of monsters present.
 */
public  interface Monster {
  /**
   * Return the hits of the monsters.
   * @return number of times the monster has been struck.
   */
  int getHits();

  /**
   * Returns if the monster is alive or dead.
   * @return true or false.
   */
  boolean isAlive();


  /**
  * updates the number times the monster is hit by arrow.
  */
  void updateHits();

}
