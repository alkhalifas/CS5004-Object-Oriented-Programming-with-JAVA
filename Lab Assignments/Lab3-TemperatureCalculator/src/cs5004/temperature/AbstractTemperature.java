package cs5004.temperature;

import cs5004.temperature.Temperature;

/**
 * Represents an abstract class that implements the temperature interface.
 */
public abstract class AbstractTemperature implements Temperature {


  /**
   * Compares temperature with the other temperature, and returns -1 if its less, and 1 if its
   * greater.
   *
   * @return 1 if greater, and -1 if lesser.
   */
  @Override
  public int compareTo(Temperature other) {
    if (this.inCelsius() < other.inCelsius()) {
      return -1;
    } else if (this.inCelsius() > other.inCelsius()) {
      return 1;
    }
    return 0;
  }

}
