package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

import static java.util.Objects.requireNonNull;

/**
 * This class represents the abstract class for shapes within the animation.
 */
public abstract class AbstractShape implements InterfaceShape {
  protected String shapeType;
  protected int width;
  protected int height;
  protected Point2D coordinatePosition;
  protected Color color;
  protected int rotationDegree;

  /**
   * A constructor that creates a new instance of the abstract class.
   */
  protected AbstractShape(int width, int height, Point2D coordinatePosition,
                          int rotationDegree, Color color) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height must be positive numbers.");
    }

    this.width = width;
    this.height = height;
    this.coordinatePosition = requireNonNull(coordinatePosition);
    this.rotationDegree = rotationDegree;
    this.color = requireNonNull(color);
  }

  /**
   * A constructor that creates a new instance of the abstract class.
   */
  protected AbstractShape() {
    this(0, 0, new Point2D.Double(0, 0), 0, new Color(0));
  }


  /**
   * A method that prints the current coordinate position of the object as a string.
   *
   * @return the position of the object as a string.
   */
  public String printPosition() {
    return "(" + this.coordinatePosition.getX() + ", " + this.coordinatePosition.getY() + ")";
  }

  /**
   * A method that returns the type of the shape as a string.
   *
   * @return the type of the shape.
   */
  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  /**
   * Method that returns the color of the shape as a Color object.
   *
   * @return the color of the shape.
   */
  @Override
  public Color getColor() {
    return new Color(this.color.getRGB());
  }

  /**
   * A method that sets the color of the object.
   *
   * @param color of the shape.
   */
  @Override
  public void setColor(Color color) {
    this.color = requireNonNull(color);
  }

  /**
   * Method that returns the color of a shape as a String.
   *
   * @return the color of a shape.
   */
  public String getTextColor() {
    return "(R: " + color.getRed() + ", G: "
            + color.getGreen() + ", B: "
            + color.getBlue() + ")";
  }

  /**
   * Method that returns the width of the shape.
   *
   * @return the width of the shape.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Sets the new width of the shape.
   *
   * @param width is the width of the shape.
   */
  @Override
  public void setWidth(int width) {
    if (width < 0) {
      throw new IllegalArgumentException("Cannot pass a negative width");
    }
    this.width = width;
  }

  /**
   * Method that returns the height of the shape.
   *
   * @return the height of the shape.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Sets the height of an object.
   *
   * @param height is the height.
   * @throws IllegalArgumentException if the height is not a positive number.
   */
  @Override
  public void setHeight(int height) {
    if (height < 0) {
      throw new IllegalArgumentException("Cannot pass a negative height");
    }
    this.height = height;
  }

  /**
   * Method that returns the rotation of the shape.
   *
   * @return the width of the shape.
   */
  @Override
  public int getShapeRotation() {
    return this.rotationDegree;
  }

  /**
   * Method that returns the coordinate position of a shape in a process.
   *
   * @return the coordinate position.
   */
  @Override
  public Point2D getPosition() {
    return new Point2D.Double(this.coordinatePosition.getX(), this.coordinatePosition.getY());
  }

  /**
   * Sets the coordinate position of the shape.
   *
   * @param coordinatePosition the new coordinates of the shape.
   */
  @Override
  public void setPosition(Point2D coordinatePosition) {
    this.coordinatePosition = requireNonNull(coordinatePosition);
  }

  /**
   * Method that returns the coordinate position of a shape in a process.
   *
   * @return the coordinate position as a String.
   */
  public String getTextPosition() {
    return "(" + coordinatePosition.getX() + ", " + coordinatePosition.getY() + ")";
  }

  /**
   * Sets the rotation angle of the shape.
   *
   * @param rotationDegree is the rotation degree.
   */
  @Override
  public void setRotationDegree(int rotationDegree) {
    this.rotationDegree = rotationDegree;
  }

  /**
   * Method that prints the attributes of a shape as a single string.
   *
   * @return a string with the attributes.
   */
  @Override
  public String toString() {
    return this.shapeType + "  " + this.coordinatePosition.getX() + "  "
            + this.coordinatePosition.getY() + "  "
            + this.width + "  " + this.height + "  " + this.rotationDegree + "  "
            + this.color.getRed() + "  " + this.color.getGreen() + "  " + this.color.getBlue();
  }


  /**
   * Method that creates a hashcode for the objects.
   *
   * @return hashcode integer.
   */
  @Override
  public int hashCode() {
    int result = 17;

    result = 31 * result + this.shapeType.hashCode();
    result = 31 * result + this.width;
    result = 31 * result + this.height;
    result = 31 * result + this.rotationDegree;
    long xLong = Double.doubleToLongBits(this.coordinatePosition.getX());
    result = 31 * result + (int) (xLong ^ (xLong >>> 32));
    long yLong = Double.doubleToLongBits(this.coordinatePosition.getX());
    result = 31 * result + (int) (yLong ^ (yLong >>> 32));
    result = 31 * result + this.color.getRGB();

    return result;
  }

  /**
   * Method that determines if objects are equal based on all attributes.
   *
   * @param other which is the other object.
   * @return boolean value of true if equal, false if not.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof AbstractShape)) {
      return false;
    }

    AbstractShape otherShape = (AbstractShape) other;

    return this.shapeType.equals(otherShape.getShapeType())
            && this.width == otherShape.getWidth()
            && this.height == otherShape.getHeight()
            && this.coordinatePosition.getX() == otherShape.getPosition().getX()
            && this.coordinatePosition.getY() == otherShape.getPosition().getY()
            && this.rotationDegree == otherShape.getShapeRotation()
            && this.color.getRGB() == otherShape.getColor().getRGB();
  }


}

