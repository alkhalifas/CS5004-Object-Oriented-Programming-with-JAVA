
/**
 * The oval class creates an oval object using the coordinates, dimensions, and RGB colors.
 */
public class Oval extends AbstractShape {

  /**
   * The oval constructor creates an oval object using the coordinates, dimensions, and RGB colors.
   */
  protected Oval(int x, int y, int width, int height, int red, int green, int blue) {
    super(x, y, width, height, red, green, blue);
  }


  /**
   * Describes the object in which the type, coordinates, and other attributes are described in the
   * form of a string.
   *
   * @return The string description of the oval describing its coordinates, width, height, and RGB.
   */
  public String toString() {
    return "Type: Oval\nCorner: (" + x + ", " + y + "), Width: " + width
            + ", Height: " + height
            + ", Color: (" + red + ", " + green + ", " + blue + ")\n";
  }
}
