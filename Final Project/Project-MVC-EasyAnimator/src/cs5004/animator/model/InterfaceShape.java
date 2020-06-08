package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * This interface class represents the interface for the shapes that will be used in the process of
 * the animation.
 */
public interface InterfaceShape extends InterfaceInterpretShape {

  /**
   * This method creates a deep copy of the shape.
   *
   * @return a copy of the shape.
   */
  InterfaceShape makeCopy();

  /**
   * A setter method that sets the coordinates of a shape.
   *
   * @param point X Y coordinates of the shape.
   */

  void setPosition(Point2D point);

  /**
   * A setter method that sets the color of a shape.
   *
   * @param color of the shape.
   */
  void setColor(Color color);


  /**
   * A setter method that sets the width of a shape.
   *
   * @param width is the width of the shape.
   */
  void setWidth(int width);

  /**
   * A setter method that sets the height of a shape.
   *
   * @param height is the height.
   */
  void setHeight(int height);

  /**
   * A setter method that sets the rotation of a shape.
   *
   * @param rotationDegree is the rotation degree.
   */
  void setRotationDegree(int rotationDegree);

}
