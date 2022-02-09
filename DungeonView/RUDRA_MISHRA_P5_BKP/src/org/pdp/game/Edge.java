package org.pdp.game;

import java.util.Objects;

class Edge     {
  private final int srcX;
  private final int srcY;
  private final Direction direction;

  protected Edge(int x, int y, Direction direction) {
    this.srcX = x;
    this.srcY = y;
    this.direction = direction;
  }


  protected int getSrcX() {
    return srcX;
  }

  protected int getSrcY() {
    return srcY;
  }

  protected Direction getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Edge)) {
      return false;
    }
    Edge other = (Edge) o;
    return other.srcX == this.srcX && other.srcY == this.srcY
            && other.direction.name().equals(this.direction.name());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.srcX, this.srcY, this.direction.name());
  }


}
