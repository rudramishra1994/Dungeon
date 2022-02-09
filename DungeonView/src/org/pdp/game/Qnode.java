package org.pdp.game;

import java.util.Objects;

class Qnode {
  private final ILocation location;
  private final int height;

  Qnode(ILocation location, int height) {
    this.location = location;
    this.height = height;
  }

  int getHeight() {
    return height;
  }

  ILocation getLocation() {
    return location;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Qnode)) {
      return false;
    }
    Qnode o = (Qnode) other;
    return this.location.equals(o.location);

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.location.getXCoordinate(), this.location.getYCoordinate());
  }

}
