package cs5004.temperature;

/**
 * Represents a temperature.
 */
public interface Temperature extends Comparable<Temperature> {
  /**
   * Absolute zero, in degrees Celsius. For our purposes, no temperature can be below
   * this value.
   */
  double ABS_ZERO_C = -273.15f;

  /**
   * The temperature in degrees Celsius.
   *
   * @return the temperature in degrees Celsius
   */

  double inCelsius();

  /**
   * The temperature in degrees Fahrenheit.
   *
   * @return the temperature in degrees Fahrenheit
   */
  double inFahrenheit();

  /**
   * The temperature in degrees Kelvin.
   *
   * @return the temperature in degrees Kelvin
   */
  double inKelvin();

  /**
   * Add two temperatures together and return the resulting temperature.
   *
   * @return the new temperature
   */
  Temperature add(Temperature t);
}
