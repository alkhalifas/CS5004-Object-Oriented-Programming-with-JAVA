package cs5004.animator.view;

import java.awt.Color;

/**
 * A interface that represents changes within the key frames of a shape in the animation.
 */
public interface InterfaceFrameChangeEvent {

  /**
   * A getter method for the type of change that was made.
   *
   * @return A frame was changed.
   */
  FrameChange getType();

  /**
   * A getter method for the ID of the shape.
   *
   * @return the id of the shape.
   */
  String getId();

  /**
   * A getter method for the time.
   *
   * @return the time.
   */
  int getTime();

  /**
   * A getter method for the x coordinate.
   *
   * @return the x coordinate
   */
  int getX();

  /**
   * A getter method for the y coordinate.
   *
   * @return the y coordinate
   */
  int getY();

  /**
   * A getter method for the width of the shape.
   *
   * @return the width.
   */
  int getWidth();

  /**
   * A getter method for the height of the shape.
   *
   * @return the height.
   */
  int getHeight();

  /**
   * A getter method for the rotationDegree of the shape.
   *
   * @return the rotationDegree in degrees
   */
  int getShapeRotation();

  /**
   * A getter method for the color of the shape.
   *
   * @return the color object.
   */
  Color getColor();
}
