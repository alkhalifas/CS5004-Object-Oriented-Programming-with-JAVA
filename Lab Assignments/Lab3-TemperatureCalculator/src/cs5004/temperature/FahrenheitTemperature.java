package cs5004.temperature;

/**
 * Represents a Fahrenheit temperature implementing the Temperature class.
 */
public class FahrenheitTemperature extends AbstractTemperature {
  private double temperature;

  /**
   * Construct CelsiusTemperature object representing the temperature in Fahrenheit.
   *
   * @param temperature the Fahrenheit in celsius.
   * @IllegalArgumentException will be thrown for invalid temperatures.
   */
  public FahrenheitTemperature(double temperature) {
    if (temperature < -459.67f) {
      throw new IllegalArgumentException("Invalid temperature");
    }
    this.temperature = temperature;
  }

  /**
   * Construct CelsiusTemperature object representing the temperature in Fahrenheit.
   *
   * @param temperatureC The temperature in Celsius.
   * @param b            the boolean value of true that is given when the temperature is in
   *                     Celsius.
   */
  public FahrenheitTemperature(double temperatureC, boolean b) {
    CelsiusTemperature temp = new CelsiusTemperature(temperatureC);
    this.temperature = temp.inFahrenheit();

  }

  /**
   * Returns the value of the temperature in Celsius.
   *
   * @return temperature in Celsius.
   */
  @Override
  public double inCelsius() {
    double c = (temperature - 32) * (5.0 / 9.0);
    return c;
  }

  /**
   * Returns the value of the temperature in Fahrenheit.
   *
   * @return temperature in Fahrenheit.
   */
  @Override
  public double inFahrenheit() {
    return temperature;
  }

  /**
   * Returns the value of the temperature in Kelvin.
   *
   * @return temperature in Kelvin.
   */
  @Override
  public double inKelvin() {
    return inCelsius() - ABS_ZERO_C;
  }

  /**
   * Returns the value of the two temperatures added.
   *
   * @return temperature in Fahrenheit.
   */
  @Override
  public Temperature add(Temperature t) {
    Temperature temp = new FahrenheitTemperature(temperature + t.inFahrenheit());
    return temp;
  }

  /**
   * Returns the value of the temperature in Fahrenheit in the form of a formatted string.
   *
   * @return temperature in Celsius.
   */
  @Override
  public String toString() {
    return String.format("%.1f° Fahrenheit", temperature);
  }

}