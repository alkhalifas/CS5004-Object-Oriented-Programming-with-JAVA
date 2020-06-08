/**
 * This interface represents the Shapes of the animation. The interface allows for different shapes
 * to be created using the methods below. A shape consists of a name, coordinates at X and Y, a
 * width and height, and a color represented by red blue adn green.
 */
public interface Shape {


  /**
   * Retrieves the X coordinate representing the center of the object.
   */
  int getX();

  /**
   * Retrieves the Y coordinate representing the center of the object.
   */
  int getY();

  /**
   * Retrieves the width of the object.
   */
  int getWidth();

  /**
   * Retrieves the width of the object.
   */
  int getHeight();

  /**
   * Retrieves the red portion of the RGB of the color of the object.
   */
  int getRed();

  /**
   * Retrieves the blue portion of the RGB of the color of the object.
   */
  int getBlue();

  /**
   * Retrieves the green portion of the RGB of the color of the object.
   */
  int getGreen();

  /**
   * Prints the output or read back of the object.
   */
  String toString();
}