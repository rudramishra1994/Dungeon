package weather;

import java.util.Objects;

/**
 * Stevenson weather reading consisting of air temperature, dew point, rainfall and wind velocity.
 * Stevenson reading are obtained from stevenson shelters which protect the instruments used to take
 * readings
 */
public final class StevensonReading implements WeatherReading {

  /*
   Heat Index constants. As the constants are common to all object
   They are declared as class variable.This helps in saving memory
   as we do not make copies for every object We do not need to change
   the value of the constants if the methods using them. We need to change
   them only at one place.
   */
  private static final double HI_C1 = -8.78469475556;
  private static final double HI_C2 = 1.61139411;
  private static final double HI_C3 = 2.33854883889;
  private static final double HI_C4 = -0.14611605;
  private static final double HI_C5 = -0.012308094;
  private static final double HI_C6 = -0.0164248277778;
  private static final double HI_C7 = 0.002211732;
  private static final double HI_C8 = 0.00072546;
  private static final double HI_C9 = -0.000003582;

  /*
   Constraints on the parameters of the constructor.
   We need to change the constraints only in a single
   place, and it gets reflected in all methods using them.
   We declared the constraints as static final as they are
   immutable and common to all Stevenson readings.
   */
  private static final double MIN_WINDSPEED = 0;
  private static final double MIN_RAIN = 0;
  private static final double MIN_AIRTEMPERATURE = -273.15;
  private static final double MAX_AIRTEMPMINUSDEWPOINT = 20;

  private final double airTemperature;
  private final double dewPoint;
  private final double rainReceived;
  private final double windSpeed;

  /**
   * Constructs instance of stevenson weather reading.
   *
   * @param airTemperature represent the air temperature
   * @param dewPoint       represents the temperature at which the dues are formed.
   * @param rainReceived   represent the rain received in the last 24 hours
   * @param windSpeed      represents the wind velocity.
   */
  public StevensonReading(double airTemperature, double dewPoint,
                          double windSpeed, double rainReceived) {

    if (rainReceived < MIN_RAIN) {
      throw new IllegalArgumentException("Total Rain Received has to be non negative");
    } else if (windSpeed < MIN_WINDSPEED) {
      throw new IllegalArgumentException("Wind speed has to be non-negative");
    } else if (airTemperature < MIN_AIRTEMPERATURE) {
      throw new IllegalArgumentException("Air Temperature cannot be less than absolute zero.");
    } else if (dewPoint > airTemperature) {
      throw new IllegalArgumentException("Dew point cannot be more than air temperature");
    } else if ((airTemperature - dewPoint) >= MAX_AIRTEMPMINUSDEWPOINT) {
      throw new IllegalArgumentException("Negative Relative Humidity not allowed");
    }


    this.airTemperature = airTemperature;
    this.dewPoint = dewPoint;
    this.rainReceived = rainReceived;
    this.windSpeed = windSpeed;
  }

  @Override
  public int getTemperature() {

    return (int) Math.round(airTemperature);
  }

  @Override
  public int getDewPoint() {
    return (int) Math.round(dewPoint);
  }

  @Override
  public int getWindSpeed() {
    return (int) Math.round(windSpeed);
  }

  @Override
  public int getTotalRain() {
    return (int) rainReceived;
  }

  /*
      Uses a private function  "calculateRelativeHumidityWithPrecision" to
      calculate the relative humidity.The precise value of relative humidity
      is needed to calculate other values such as heat index. Hence, we are
      using a private function to calculate it.
     */
  @Override
  public int getRelativeHumidity() {

    return (int) Math.round(calculateRelativeHumidityWithPrecision());
  }

  @Override
  public int getHeatIndex() {
    return (int) Math.round(calculateHeatIndex(calculateRelativeHumidityWithPrecision()));
  }

  @Override
  public int getWindChill() {
    double airTemperatureInFahrenheit = celsiusToFahrenheit();
    return (int) Math.round((35.74 + 0.6215 * airTemperatureInFahrenheit
            - 35.75 * Math.pow(windSpeed, 0.16)
            + 0.4275 * airTemperatureInFahrenheit * Math.pow(windSpeed, 0.16)));
  }

  @Override
  public String toString() {
    return String.format("Reading: T = %s, D = %s, v = %s, rain = %s",
            this.getTemperature(), this.getDewPoint(),
            this.getWindSpeed(), this.getTotalRain());
  }

  @Override
  public int hashCode() {
    return Objects.hash(airTemperature, dewPoint, windSpeed, rainReceived);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof WeatherReading)) {
      return false;
    }
    /*
    Object obj typecast to StevensonReading class. It allows us to compare the precise
    values of private members using dot operator as the getter methods return the approximate
    value of the member variable with makes comparison inaccurate.
     */

    StevensonReading stevensonReading = (StevensonReading) obj;
    return Double.compare(this.airTemperature, stevensonReading.airTemperature) == 0
            && Double.compare(this.rainReceived, stevensonReading.rainReceived) == 0
            && Double.compare(this.windSpeed, stevensonReading.windSpeed) == 0
            && Double.compare(this.dewPoint, stevensonReading.dewPoint) == 0;

  }

  /*
    calculates and returns precise floating point value of relative humidity
   */
  private double calculateRelativeHumidityWithPrecision() {
    return 100 - 5 * (airTemperature - dewPoint);
  }

  /*
  converts celsius to Fahrenheit
 */
  private double celsiusToFahrenheit() {
    return 1.8 * airTemperature + 32;
  }

  /*
  Calculates and returns the precise floating point value of heat index.
   */
  private double calculateHeatIndex(double relativeHumidity) {
    return HI_C1 + HI_C2 * airTemperature
            + HI_C3 * relativeHumidity
            + HI_C4 * airTemperature * relativeHumidity
            + HI_C5 * Math.pow(airTemperature, 2)
            + HI_C6 * Math.pow(relativeHumidity, 2)
            + HI_C7 * Math.pow(airTemperature, 2) * relativeHumidity
            + HI_C8 * airTemperature * Math.pow(relativeHumidity, 2)
            + HI_C9 * Math.pow(airTemperature, 2) * Math.pow(relativeHumidity, 2);
  }

}
