package cs5004.animator.controller;

import java.awt.Color;

/**
 * Represents a keyframe that includes rotation.
 */
public class StatusKeyFrame extends Keyframe implements InterpretStatusKeyFrame {
  private final int rotationDegree;

  /**
   * Constructor for a keyframe that initializes all of its fields.
   *
   * @param frameTime      is this time this key frame occurs.
   * @param x              is the x coordinatePosition of this keyframe.
   * @param y              is the y coordinatePosition of this keyframe.
   * @param width          is the width of the shape at this keyframe.
   * @param height         is the height of the shape at this keyframe.
   * @param rotationDegree is the rotationDegree of the shape at this keyframe.
   * @param color          is the color of the shape at this keyframe.
   */
  public StatusKeyFrame(int frameTime, int x, int y, int width, int height,
                        int rotationDegree, Color color) {
    super(frameTime, x, y, width, height, color);
    this.rotationDegree = rotationDegree;
  }

  @Override
  public int getShapeRotation() {
    return this.rotationDegree;
  }

  @Override
  public String toString() {
    return "TIME : " + this.time + ": Position (" + this.x + ", " + this.y + "), " + this.width
            + "x"
            + this.height + ", color: ("
            + this.color.getRed() + ", " + this.color.getGreen() + ", "
            + this.color.getBlue() + ")";
  }
}
