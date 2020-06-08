import java.util.NoSuchElementException;

/**
 * This class represents a rectangle object consisting of x, y, w, h.
 */
public class Rectangle {
  final private double x;
  final private double y;
  final private double h;
  final private double w;

  /**
   * Constructor Creates a rectangle using the x and y for its lower corner, and also the height and
   * width.
   *
   * @param x an x coordinate of the lower left corner.
   * @param y a y coordinate of the lower left corner.
   * @param w the width of the rectangle.
   * @param h the height of the rectangle.
   * @throws IllegalArgumentException if height or width are negative.
   */
  public Rectangle(double x, double y, double w, double h) {

    if (h < 0) {
      throw new IllegalArgumentException("The height cannot be negative! Please try again.");
    }
    if (w < 0) {
      throw new IllegalArgumentException("The width cannot be negative! Please try again.");
    }
    this.x = x;
    this.y = y;
    this.h = h;
    this.w = w;
  }

  /**
   * The method Overlap returns true if this rectangle overlaps with the other rectangle.
   */
  public boolean overlap(Rectangle other) {
    return (x < other.x + other.w && x + w > other.x && y < other.y + other.h && y + h > other.y);
  }

  /**
   * The method Intersect returns a Rectangle object that represents the overlap of this rectangle
   * and the other rectangle.
   *
   * @throws NoSuchElementException if there is no overlap between the rectangles.
   */

  public Rectangle intersect(Rectangle other) {
    if (!this.overlap(other)) {
      throw new NoSuchElementException("No Rectangle intersect.");
    }
    double newURx = Math.min(this.x + this.w, other.x + other.w);
    double newURy = Math.min(this.y + this.h, other.y + other.h);
    double newLLx = Math.max(this.x, other.x);
    double newLLy = Math.max(this.y, other.y);

    Rectangle rectangle = new Rectangle(newLLx, newLLy, newURx - newLLx, newURy - newLLy);
    return rectangle;
  }

  /**
   * The method Union returns a Rectangle object that represents the union of this rectangle and the
   * other rectangle.
   */
  public Rectangle union(Rectangle other) {

    double xMin = Math.min(this.x, other.x);
    double yMin = Math.min(this.y, other.y);
    double xMax = Math.max((this.x + this.w), (other.x + other.w));
    double yMax = Math.max((this.y + this.h), (other.y + other.h));
    double wNew = xMax - xMin;
    double hNew = yMax - yMin;
    return new Rectangle(xMin, yMin, wNew, hNew);
  }

  /**
   * The method toString returns a String consisting of x, y, w, and h in the format "x:1, y:2, w:3,
   * h:4".
   */
  @Override
  public String toString() {
    String justX = "x:" + Math.round(this.x);
    String justY = ", y:" + Math.round(y);
    String justW = ", w:" + Math.round(w);
    String justH = ", h:" + Math.round(h);
    return justX + justY + justW + justH;
  }


}