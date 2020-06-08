
/**
 * This abstract class represents the Shapes of the animation. The interface allows for different
 * shapes to be created using the methods below. A shape consists of a name, coordinates at X and Y,
 * a width and height, and a color represented by red blue adn green.
 */
public abstract class AbstractShape implements Shape {

  protected int x;
  protected int y;

  protected int width;
  protected int height;

  protected int red;
  protected int green;
  protected int blue;

  /**
   * This constructor constructs an abstract shape which centers the shape using the coordinates and
   * colors the object using the RBG values.
   *
   * @param x      is the x coordinate of the object.
   * @param y      is the y coordinate of the object.
   * @param width  is the width of the object.
   * @param height is the height of the object.
   * @param red    is the red part of the RGB of the color of the object.
   * @param green  is the green part of the RGB of the color of the object.
   * @param blue   is the blue part of the RGB of the color of the object.
   */
  protected AbstractShape(int x, int y, int width, int height, int red, int green, int blue)
          throws IllegalArgumentException {

    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Width and height must be positive!");
    }

    if (red < 0 || blue < 0 || green < 0) {
      throw new IllegalArgumentException("RGB values must be positive!");
    }

    if (red > 255 || blue > 255 || green > 255) {
      throw new IllegalArgumentException("RGB values must be less than 256!");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Retrieves the X coordinate representing the center of the object.
   *
   * @return the X coordinate of the object.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Retrieves the Y coordinate representing the center of the object.
   *
   * @return the Y coordinate of the object.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Retrieves the width of the object.
   *
   * @return the width of the object.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Retrieves the height of the object.
   *
   * @return the height of the object.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Retrieves the red portion of the RGB of the color of the object.
   *
   * @return the red value of the RGB color.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Retrieves the blue portion of the RGB of the color of the object.
   *
   * @return the blue value of the RGB color.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Retrieves the green portion of the RGB of the color of the object.
   *
   * @return the green value of the RGB color.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Prints the output or read back of the object.
   *
   * @return the output or read back of the object.
   */
  public String toString() {
    return "";
  }

}
