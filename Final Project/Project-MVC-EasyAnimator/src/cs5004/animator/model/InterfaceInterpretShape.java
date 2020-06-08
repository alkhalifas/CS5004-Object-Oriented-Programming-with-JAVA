package cs5004.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * This class represents the class for interpreting the shape for retrieving information.
 */
public interface InterfaceInterpretShape {

  /**
   * Returns the tyep of the shape.
   * @return The shape type.
   */
  String getShapeType();

  /**
   * Returns the width of the shape.
   * @return the width of the shape.
   */
  int getWidth();

  /**
   * Returns the height of the shape.
   * @return the height of the shape.
   */
  int getHeight();


  /**
   * Returns the degree of rotation of the shape.
   * @return the degree of rotation of the shape.
   */
  int getShapeRotation();

  /**
   * Returns the current coordinatePosition of the shape as a Point2D object.
   * @return the X and Y coordinate of the shape.
   */
  Point2D getPosition();

  /**
   * Returns the color of the shape in a color object.
   * @return the color of the shape.
   */
  Color getColor();

  /**
   * Implements the drawing of the shape onto the screen.
   * @param g the graphics variable to display the shape.
   */
  void draw(Graphics2D g);
}