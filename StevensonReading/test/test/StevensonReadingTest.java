package test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import weather.StevensonReading;
import weather.WeatherReading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests WeatherReading Interface implemented by StevensonReading Class.
 * This class test the public methods of the weather reading interface
 * against illegal arguments ensuring that exceptions are thrown
 * and also checks if correct values are returned
 * for legal arguments
 */
public class StevensonReadingTest {

  private WeatherReading stevensonReading;

  @Before
  public void setUp() {
    stevensonReading = newStevensonReading(25.5, 15.1, 10.2, 100);
  }

  protected WeatherReading newStevensonReading(double airtemperature, double dewPoint,
                                               double windSpeed, double rainReceived) {
    return new StevensonReading(airtemperature, dewPoint, windSpeed, rainReceived);
  }

  @Test
  public void testToString() {
    String expectedValue = String.format("Reading: T = %s, D = %s, v = %s, rain = %s",
            23, 16, 70, 100);
    String actualVal = newStevensonReading(23.1, 16.2, 69.88, 100.3).toString();
    assertEquals(expectedValue, actualVal);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfValidWindSpeed() {
    newStevensonReading(23.5, 21.7, -8, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfValidRainfall() {
    newStevensonReading(26.8, 12, 99, -110.2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfValidDewPointTemperature() {
    newStevensonReading(20.2, 23.4, 97, 75);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfValidAirTemperature() {
    newStevensonReading(-281.22, -267.8, 21, 89);
  }

  /*
   * Checks if the difference between air temperature and dew point is less than 20
   * Also checks the exception message
   * Reference :
   * 1.https://junit.org/junit4/javadoc/4.12/org/junit/rules/ExpectedException.html
   * 2.https://stackoverflow.com/questions/11426042/which-is-better-expectedexception-or-testexpected/11429529
   */
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testIfValidAirTemperatureDewPointDifference() {
    exceptionRule.expect(IllegalArgumentException.class);
    exceptionRule.expectMessage("Negative Relative Humidity not allowed");
    newStevensonReading(32.2, 9.4, 98, 77);
  }

  @Test
  public void testGetTemperature() {
    assertEquals(30, newStevensonReading(29.5, 12.5, 77, 98).getTemperature());
  }

  @Test
  public void testGetDewPoint() {
    assertEquals(13, newStevensonReading(29.5, 12.5, 77, 98).getDewPoint());
  }

  @Test
  public void testGetRelativeHumidity() {
    assertEquals(54, newStevensonReading(19.3, 10, 58.3, 119.1).getRelativeHumidity());
  }

  @Test
  public void testGetRainfall() {
    assertEquals(98, newStevensonReading(29.5, 11.5, 77, 98).getTotalRain());
  }

  @Test
  public void testGetWindSpeed() {
    assertEquals(77, newStevensonReading(29.5, 14.5, 77, 98).getWindSpeed());
  }

  @Test
  public void testGetWindChill() {
    assertEquals(91, newStevensonReading(30, 11, 45, 77).getWindChill());
  }

  @Test
  public void testEquals() {
    assertTrue(stevensonReading.equals(stevensonReading));//An object always equal to itself
    //object with same member values are same
    assertTrue(stevensonReading.equals(newStevensonReading(25.5, 15.1, 10.2, 100)));
    assertFalse(stevensonReading.equals(newStevensonReading(28.9, 15.6, 45.6, 80)));
    assertFalse(stevensonReading.equals(new Integer(10)));//Object not of the same type
    assertFalse(newStevensonReading(31, 15, 8, 9).equals(newStevensonReading(32, 15, 98, 100)));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testEqualForInvalidArguments() {
    assertFalse(newStevensonReading(33, 15, -9, 100).equals(newStevensonReading(33, 15, 9, 100)));
    assertFalse(newStevensonReading(33, 15, 33, -9).equals(newStevensonReading(33, 15, 33, 10)));
    assertFalse(newStevensonReading(38, 15, 19, 100).equals(newStevensonReading(33, 15, 9, 100)));
    assertFalse(newStevensonReading(-281.9, -266, 19, 100).
            equals(newStevensonReading(33, 15, 9, 100)));

  }

  @Test
  public void testHashCode() {
    int actualReading = newStevensonReading(25.5, 15.1, 10.2, 100).hashCode();
    assertEquals(stevensonReading.hashCode(), actualReading);
    actualReading = newStevensonReading(23.8, 16.4, 70, 23).hashCode();
    assertNotEquals(stevensonReading.hashCode(), actualReading);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHashCodeForIllegalArguments() {
    assertEquals(newStevensonReading(33, 15, -9, 100).hashCode(),
            (newStevensonReading(33, 15, -9, 100).hashCode()));
    assertEquals(newStevensonReading(33, 15, 33, -9).hashCode(),
            newStevensonReading(33, 15, 33, -9).hashCode());
    assertEquals(newStevensonReading(38, 15, 19, 100).hashCode(),
            newStevensonReading(33, 15, 9, 100).hashCode());
    assertEquals(newStevensonReading(-281.9, -266, 19, 100).hashCode(),
            newStevensonReading(-281.9, -266, 19, 100).hashCode());
  }

  @Test
  public void testHeatIndex() {
    assertEquals(31, newStevensonReading(30, 20, 45, 100).getHeatIndex());
    assertNotEquals(100, newStevensonReading(30.2, 14, 8, 45).getHeatIndex());
  }

}