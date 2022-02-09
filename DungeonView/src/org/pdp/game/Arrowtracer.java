package org.pdp.game;

import java.util.ArrayList;
import java.util.List;

/**
 * A class which traces the path of the arrow.
 * It stores the direction of the arrow at each location.
 * It stores if the arrow hits a monster.
 */
public class Arrowtracer {


  int hits;
  List<Direction> directionList;

  /**
   * Creates an arrow tracing object used to trace an arrow.
   */
  public Arrowtracer() {
    this.directionList = new ArrayList<>();
    this.hits = 0;
  }

  /**
   * returns the direction of the arrow in each location through which it travelled.
   *
   * @return list of direction.
   */
  public List<Direction> getDirectionList() {
    return new ArrayList<>(directionList);
  }

  protected void setDirectionList(Direction direction) {
    if ( direction == null) {
      throw new IllegalArgumentException("The arrow direction cannot be null");
    }
    this.directionList.add(direction);
  }

  /**
   * Returns the number of hits that the monster has at a location.
   *
   * @return number of times a monster has been hit .i.e and integer
   */
  public int getHits() {
    return hits;
  }

  protected void setHits(int hits) {
    this.hits = hits;
  }


}
