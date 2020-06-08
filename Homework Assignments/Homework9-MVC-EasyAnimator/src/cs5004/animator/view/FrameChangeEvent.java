package cs5004.animator.view;

import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * This class represents an action event for key frame changes for the playback view.
 */
public class FrameChangeEvent extends ActionEvent implements InterfaceFrameChangeEvent {
  private final FrameChange changeType;
  private final String id;
  private final int frameTime;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final int rotationDegree;
  private final Color color;

  /**
   * This constructor creates a Frame Change Event for receiving listener updates.
   *
   * @param source     is the object sending the event
   * @param changeType is the type of change being made.
   * @param id         is the id of the shape that this keyframe corresponds to
   * @param frameTime  is the tick at which this keyframe represents its shape
   * @param x          is the x coordinate of the shape at this frame
   * @param y          is the y coordinate of the shape at this frame
   * @param width      is the width of the shape at this frame
   * @param height     is the height of the shape at this frame
   * @param color      is the color of the shape at this frame
   */
  public FrameChangeEvent(Object source, FrameChange changeType, String id, int frameTime,
                          int x, int y,
                          int width, int height,
                          int rotationDegree, Color color) {
    super(source, ActionEvent.ACTION_PERFORMED, "frame change");

    this.changeType = changeType;
    this.id = id;
    this.frameTime = frameTime;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.rotationDegree = rotationDegree;
    this.color = color;
  }

  /**
   * A getter method for the type of change that was made.
   *
   * @return A frame was changed.
   */
  @Override
  public FrameChange getType() {
    return this.changeType;
  }

  /**
   * A getter method for the ID of the shape.
   *
   * @return the id of the shape.
   */
  @Override
  public String getId() {
    return this.id;
  }

  /**
   * A getter method for the time.
   *
   * @return the time.
   */
  @Override
  public int getTime() {
    return this.frameTime;
  }

  /**
   * A getter method for the x coordinate.
   *
   * @return the x coordinate
   */
  @Override
  public int getX() {
    return this.x;
  }

  /**
   * A getter method for the y coordinate.
   *
   * @return the y coordinate
   */
  @Override
  public int getY() {
    return this.y;
  }

  /**
   * A getter method for the width of the shape.
   *
   * @return the width.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * A getter method for the height of the shape.
   *
   * @return the height.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * A getter method for the rotationDegree of the shape.
   *
   * @return the rotationDegree in degrees
   */
  @Override
  public int getShapeRotation() {
    return this.rotationDegree;
  }

  /**
   * A getter method for the color of the shape.
   *
   * @return the color object.
   */
  @Override
  public Color getColor() {
    return new Color(this.color.getRGB());
  }
}
