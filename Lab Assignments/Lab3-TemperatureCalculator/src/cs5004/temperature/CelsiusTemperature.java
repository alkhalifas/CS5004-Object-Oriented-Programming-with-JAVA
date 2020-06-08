package cs5004.temperature;

/**
 * Represents a Celsius temperature implementing the Temperature class.
 */
public class CelsiusTemperature extends AbstractTemperature {
  private double temperature;

  /**
   * Construct CelsiusTemperature object representing the temperature in celsius.
   *
   * @param temperature the temperature in celsius.
   * @IllegalArgumentException will be thrown for invalid temperatures.
   */
  public CelsiusTemperature(double temperature) {
    if (temperature < ABS_ZERO_C) {
      throw new IllegalArgumentException("Invalid temperature");
    }
    this.temperature = temperature;
  }

  /**
   * Construct CelsiusTemperature object representing the temperature in celsius.
   *
   * @param temperatureF The temperature in fahrenheit.
   * @param b            the boolean value of true that is given when the temperature is in
   *                     Fahrenheit.
   * @IllegalArgumentException will be thrown for invalid temperatures.
   */
  public CelsiusTemperature(double temperatureF, boolean b) {
    FahrenheitTemperature temp = new FahrenheitTemperature(temperatureF);
    this.temperature = temp.inCelsius();
  }

  /**
   * Returns the value of the temperature in Celsius.
   *
   * @return temperature in Celsius.
   */
  @Override
  public double inCelsius() {
    return temperature;
  }

  /**
   * Returns the value of the temperature in Fahrenheit.
   *
   * @return temperature in Fahrenheit.
   */
  @Override
  public double inFahrenheit() {
    double f = temperature * (9.0 / 5.0) + 32;
    return f;
  }

  /**
   * Returns the value of the temperature in Kelvin.
   *
   * @return temperature in Kelvin.
   */
  @Override
  public double inKelvin() {
    return temperature - ABS_ZERO_C;
  }


  /**
   * Returns the value of the two temperatures added.
   *
   * @return temperature in Celsius.
   */
  @Override
  public Temperature add(Temperature t) {
    Temperature temp = new CelsiusTemperature(temperature + t.inCelsius());
    return temp;
  }

  /**
   * Returns the value of the temperature in Celsius in the form of a formatted string.
   *
   * @return temperature in Celsius.
   */
  @Override
  public String toString() {
    return String.format("%.1f° Celsius", temperature);
  }

}