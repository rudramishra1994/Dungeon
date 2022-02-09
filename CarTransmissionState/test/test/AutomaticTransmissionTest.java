package test;


import org.junit.Test;

import transmission.AutomaticTransmission;
import transmission.Transmission;

import static org.junit.Assert.assertEquals;

/**
 * Tests AutomaticTransmission class to ensure that the car is in a valid transmission state.
 */
public class AutomaticTransmissionTest {

  private static final int speedOfLight = 1079252849;

  protected AutomaticTransmission getTransmissionState(int speedThresholdGear1To2,
                                                       int speedThresholdGear2To3,
                                                       int speedThresholdGear3To4,
                                                       int speedThresholdGear4To5,
                                                       int speedThresholdGear5To6) {
    return new AutomaticTransmission(speedThresholdGear1To2, speedThresholdGear2To3,
            speedThresholdGear3To4, speedThresholdGear4To5, speedThresholdGear5To6);
  }

  /**
   * Test if any two gear threshold are equal.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfTwoSpeedThresholdsAreEqual() {
    getTransmissionState(50, 75, 75,
            150, 200);
  }

  /**
   * Test if threshold1 is zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testThreshold1To2Zero() {
    getTransmissionState(0, 75,
            100, 150, 200);
  }

  /**
   * Test if threshold2 is zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testThreshold2To3Zero() {
    getTransmissionState(10, 0,
            100, 150, 200);
  }

  /**
   * Test if threshold3 is zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testThreshold3To4Zero() {
    getTransmissionState(30, 75,
            0, 150, 200);
  }

  /**
   * Test if threshold4 is zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testThreshold4To5Zero() {
    getTransmissionState(30, 75,
            100, 0, 200);
  }

  /**
   * Test if threshold5 is zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testThreshold5To6Zero() {
    getTransmissionState(30, 75,
            100, 150, 0);
  }

  /**
   * Tests when threshold1 out of order.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfSpeedThresholdsAreStrictlyIncreasing1to2() {
    getTransmissionState(500, 70, 100,
            130, 150);
  }


  /**
   * Tests when threshold2 out of order.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfSpeedThresholdsAreStrictlyIncreasing2To3() {
    getTransmissionState(50, 30, 100,
            130, 150);
  }


  /**
   * Tests when threshold3 out of order.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfSpeedThresholdsAreStrictlyIncreasing3To4() {
    getTransmissionState(50, 70, 40,
            130, 150);
  }


  /**
   * Tests when threshold4 out of order.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfSpeedThresholdsAreStrictlyIncreasing4To5() {
    getTransmissionState(50, 70, 100,
            90, 150);
  }


  /**
   * Tests when threshold5 out of order.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIfSpeedThresholdsAreStrictlyIncreasing5To6() {
    getTransmissionState(50, 70, 100,
            130, 60);
  }

  /**
   * Tests for the gear for different car speeds.
   */
  @Test
  public void testGetGear() {
    Transmission carTransmission = getTransmissionState(30, 50, 70, 110, 150);
    int[] differentSpeedToTestGear = {10, 45, 55, 80, 105, 170};
    int[] differentGearToTestFor = {1, 2, 3, 4, 4, 6};
    assertEquals(0, carTransmission.getGear());
    for (int j = 0; j < differentSpeedToTestGear.length; j++) {
      while (carTransmission.getSpeed() < differentSpeedToTestGear[j]) {
        carTransmission.increaseSpeed();
      }
      assertEquals(differentGearToTestFor[j], carTransmission.getGear());
    }

  }

  /**
   * Test increase speed to check if the speed is increased  and gear changed correctly.
   */
  @Test
  public void testIncreaseSpeed() {
    Transmission carTransmission = getTransmissionState(30, 50, 70, 110, 150);
    int[] differentSpeedToTestIncreaseSpeed = {10, 45, 55, 80, 115, 170};
    int[] differentGearAtDifferentSpeed = {1, 2, 3, 4, 5, 6};
    for (int i = 0; i < differentSpeedToTestIncreaseSpeed.length; i++) {
      while (carTransmission.getSpeed() < differentSpeedToTestIncreaseSpeed[i]) {
        carTransmission.increaseSpeed();
      }
      assertEquals(differentSpeedToTestIncreaseSpeed[i], carTransmission.getSpeed());
      assertEquals(differentGearAtDifferentSpeed[i], carTransmission.getGear());
    }
  }

  /**
   * Test if the decreaseSpeed decreases speed when called.
   */

  @Test
  public void testDecreaseSpeed() {
    Transmission carTransmission = getTransmissionState(40, 60, 100, 140, 160);
    for (int i = 0; i < 54; i++) {
      carTransmission.increaseSpeed();
    }
    for (int i = 0; i < 20; i++) {
      carTransmission.decreaseSpeed();
    }
    assertEquals(34, carTransmission.getSpeed());
  }

  /**
   * Checks if the speed decreases and becomes -ve if current speed is zero.
   */
  @Test(expected = IllegalStateException.class)
  public void testDecreaseSpeedForNegativeSpeed() {
    Transmission carTransmission = getTransmissionState(35, 60, 95, 110, 150);
    carTransmission.decreaseSpeed();
  }

  /**
   * Checks if speed increases and exceeds speed of light.
   */

  @Test(expected = IllegalStateException.class)
  public void testIncreaseSpeedForSpeedOfLight() {
    Transmission carTransmission = getTransmissionState(35, 60, 95, 110, 150);
    for (int j = 0; j <= speedOfLight; j++) {
      carTransmission.increaseSpeed();
    }
  }

  /**
   * Check the string representation of transmission.
   */

  @Test
  public void testToString() {
    Transmission carTransmission = getTransmissionState(35, 60, 95, 110, 150);
    for (int i = 0; i < 66; i++) {
      carTransmission.increaseSpeed();
    }
    assertEquals(String.format("Transmission (speed = %s, gear = %s)", 66, 3),
            carTransmission.toString());
  }

  @Test
  public void testToStringCarIdleState() {
    Transmission carTransmission = getTransmissionState(35, 60, 95, 110, 150);
    assertEquals(String.format("Transmission (speed = %s, gear = %s)", 0, 0),
            carTransmission.toString());
  }

  /**
   * Check the value of gear when the speed decreases.
   */
  @Test
  public void TestGearForDecreaseInSpeed() {
    int[] differentSpeedToTestIncreaseSpeed = {0, 10, 45, 65, 100, 115, 170};
    Transmission carTransmission = getTransmissionState(35, 60, 95, 110, 150);
    int[] differentGearsToCheck = {0, 1, 2, 3, 4, 5, 6};
    for (int i = 0; i < 180; i++) {
      carTransmission.increaseSpeed();
    }
    for (int i = differentSpeedToTestIncreaseSpeed.length - 1; i >= 0; i--) {
      while (differentSpeedToTestIncreaseSpeed[i] <= carTransmission.getSpeed()
              && carTransmission.getSpeed() > 0) {
        carTransmission.decreaseSpeed();
      }
      assertEquals(differentGearsToCheck[i], carTransmission.getGear());
    }
  }


}