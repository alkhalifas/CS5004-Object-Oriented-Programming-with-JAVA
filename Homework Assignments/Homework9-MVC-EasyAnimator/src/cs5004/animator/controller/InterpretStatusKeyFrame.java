package cs5004.animator.controller;

/**
 * This interface adds the functionality of rotating a shape.
 */
public interface InterpretStatusKeyFrame extends InterfaceInterpretKeyFrame {
  /**
   * Getter for the rotationDegree of this key frame.
   *
   * @return the rotationDegree in degrees of the shape at this frame.
   */
  int getShapeRotation();
}
