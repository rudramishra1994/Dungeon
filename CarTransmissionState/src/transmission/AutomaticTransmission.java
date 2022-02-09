package transmission;

/**
 * AutomaticTransmission class represent the state of a car in motion.
 * The state is represented using its speed and the gear in  use.
 */
public class AutomaticTransmission implements Transmission {


  private final int speedThresholdGear1To2;
  private final int speedThresholdGear2To3;
  private final int speedThresholdGear3To4;
  private final int speedThresholdGear4To5;
  private final int speedThresholdGear5To6;

  private static final int speedOfLight = 1079252849;
  private int currentSpeed;
  private int currentGear;

  /**
   * Creates a transmission state of a car where the car is at rest.
   *
   * @param speedThresholdGear1To2 speed at which gear shifts from 1 to 2 and back
   * @param speedThresholdGear2To3 speed at which gear shifts from 2 to 3 and back
   * @param speedThresholdGear3To4 speed at which gear shifts from 3 to 4 and back
   * @param speedThresholdGear4To5 speed at which gear shifts from 4 to 5 and back
   * @param speedThresholdGear5To6 speed at which gear shifts from 5 to 6 and back
   */
  public AutomaticTransmission(int speedThresholdGear1To2, int speedThresholdGear2To3,
                               int speedThresholdGear3To4, int speedThresholdGear4To5,
                               int speedThresholdGear5To6) {
    if (speedThresholdGear1To2 < 0 || speedThresholdGear2To3 < 0
            || speedThresholdGear3To4 < 0 || speedThresholdGear4To5 < 0
            || speedThresholdGear5To6 < 0) {
      throw new IllegalArgumentException("Threshold cannot be negative");
    } else if (speedThresholdGear1To2 == 0 || speedThresholdGear2To3 == 0
            || speedThresholdGear3To4 == 0 || speedThresholdGear4To5 == 0
            || speedThresholdGear5To6 == 0) {
      throw new IllegalArgumentException("Threshold speed for gear change cannot be zero");
    } else if (speedThresholdGear1To2 < speedThresholdGear2To3
            && speedThresholdGear2To3 < speedThresholdGear3To4
            && speedThresholdGear3To4 < speedThresholdGear4To5
            && speedThresholdGear4To5 < speedThresholdGear5To6) {
      this.speedThresholdGear1To2 = speedThresholdGear1To2;
      this.speedThresholdGear2To3 = speedThresholdGear2To3;
      this.speedThresholdGear3To4 = speedThresholdGear3To4;
      this.speedThresholdGear4To5 = speedThresholdGear4To5;
      this.speedThresholdGear5To6 = speedThresholdGear5To6;
      this.currentSpeed = 0;
      this.currentGear = 0;

    } else {
      throw new IllegalArgumentException("The speed threshold "
              + "for change of gear should be strictly"
              + " increasing");
    }
  }

  @Override
  public void increaseSpeed() {
    if (currentSpeed == speedOfLight) {
      throw new IllegalStateException("Speed of car cannot be more than speed of light");
    }
    currentSpeed++;
    setGear();


  }

  @Override
  public void decreaseSpeed() throws IllegalStateException {
    if (currentSpeed == 0) {
      throw new IllegalStateException("Cannot decrease speed.Speed cannot be negative.");
    }
    currentSpeed--;
    setGear();
  }

  @Override
  public int getSpeed() {
    return currentSpeed;
  }

  private void setGear() {
    if (currentSpeed == 0) {
      currentGear = 0;
    } else if (currentSpeed < speedThresholdGear1To2 && currentSpeed > 0) {
      currentGear = 1;
    } else if (currentSpeed >= speedThresholdGear1To2 && currentSpeed < speedThresholdGear2To3) {
      currentGear = 2;
    } else if (currentSpeed >= speedThresholdGear2To3 && currentSpeed < speedThresholdGear3To4) {
      currentGear = 3;
    } else if (currentSpeed >= speedThresholdGear3To4 && currentSpeed < speedThresholdGear4To5) {
      currentGear = 4;
    } else if (currentSpeed >= speedThresholdGear4To5 && currentSpeed < speedThresholdGear5To6) {
      currentGear = 5;
    } else {
      currentGear = 6;
    }
  }

  @Override
  public int getGear() {
    return currentGear;
  }

  @Override
  public String toString() {
    return String.format("Transmission (speed = %s, gear = %s)", currentSpeed, currentGear);

  }
}
