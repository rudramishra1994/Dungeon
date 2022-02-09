package org.pdp.game;

class Otyugh implements Monster {

  private int hits;
  private static final int DEFAULT_HITS = 0;

  protected Otyugh() {
    this.hits = DEFAULT_HITS;
  }

  protected Otyugh(int hit) {
    this.hits = hit;
  }

  /**
   * Returns the number of times an Otyugh has been hit.
   *
   * @return number of hits.
   */
  @Override
  public int getHits() {
    return this.hits;
  }

  @Override
  public boolean isAlive() {
    return this.hits < 2;
  }


  @Override
  public void updateHits() {
    this.hits++;
  }


}
