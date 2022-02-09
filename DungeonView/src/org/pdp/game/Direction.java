package org.pdp.game;

/**
 * An enum that represents the four cardinal directions.
 * EAST,WEST,NORTH,SOUTH.
 */
public enum Direction {
  EAST(0, 1),
  WEST(0, -1),
  NORTH(-1, 0),
  SOUTH(1, 0);

  final int dx;
  final int dy;
  Direction opposite;

  static {
    NORTH.opposite = SOUTH;
    SOUTH.opposite = NORTH;
    EAST.opposite = WEST;
    WEST.opposite = EAST;
  }


  Direction(int dx, int dy) {
    this.dx = dx;
    this.dy = dy;
  }
}
