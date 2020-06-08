/**
 * The rectangle class creates an oval object using the coordinates, dimensions, and RGB colors.
 */
public class Rectangle extends AbstractShape {

  /**
   * The rectangle constructor creates an oval object using the coordinates, dimensions, and RGB
   * colors.
   */
  protected Rectangle(int x, int y, int width, int height, int red, int green, int blue) {
    super(x, y, width, height, red, green, blue);
  }

  /**
   * Describes the object in which the type, coordinates, and other attributes are described in the
   * form of a string.
   *
   * @return The string description of the rectangle giving its coordinates, width, height, and RGB.
   */
  @Override
  public String toString() {
    return "Type: Rectangle" + "\n"
            + "Corner: (" + x + ", " + y + "), Width: " + width
            + ", Height: " + height
            + ", Color: (" + red + ", " + green + ", " + blue + ")\n";
  }
}
