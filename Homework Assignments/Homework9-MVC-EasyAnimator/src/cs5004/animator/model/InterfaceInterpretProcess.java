package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents the interpreting methods for the process for a shape during an animation.
 */
public interface InterfaceInterpretProcess {
  /**
   * This method will retrieve the start time for the process of the object.
   * @return the start time of the process.
   */
  int getStartTime();

  /**
   * This method will retrieve the end time for the process of the object.
   * @return returns the end time of the process.
   */
  int getEndTime();

  /**
   * This method will retrieve the shape type of the object.
   * @return the type of the shape.
   */
  String getType();

  /**
   * This method will retrieve the x coordinate type of the object.
   * @return the starting x coordinate.
   */
  int getStartX();

  /**
   * This method will retrieve the y coordinate type of the object.
   * @return the starting y coordinate.
   */
  int getStartY();

  /**
   * This method will retrieve width of the object.
   * @return the starting width.
   */
  int getStartWidth();

  /**
   * This method will retrieve height of the object.
   * @return the starting height.
   */
  int getStartHeight();

  /**
   * This method will retrieve starting color of the object.
   * @return the starting color of the shape in this process.
   */
  Color getStartColor();

  /**
   * This method will retrieve end x coordinate of the object.
   * @return the ending X coordinate.
   */
  int getEndX();

  /**
   * This method will retrieve end y coordinate of the object.
   * @return the end y coordinate.
   */
  int getEndY();

  /**
   * This method will retrieve end width of the object.
   * @return the ending width.
   */
  int getEndWidth();

  /**
   * This method will retrieve end height of the object.
   * @return the ending height.
   */
  int getEndHeight();

  /**
   * This method will retrieve the end color of the object.
   * @return the ending color
   */
  Color getEndColor();
}
