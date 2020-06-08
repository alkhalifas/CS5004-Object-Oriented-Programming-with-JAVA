package cs5004.animator.model;

/**
 * This class represents a process that can rotate a shape.
 */
public interface InterfaceInterpretStatusProcess extends InterfaceInterpretProcess {
  /**
   * This method will retrieve the rotationDegree of this process which the starting rotationDegree
   * of the shape.
   *
   * @return int that represents the rotationDegree in degrees
   */
  int getStartRotationDegree();

  /**
   * This method will retrieve the rotationDegree of this process which the final rotationDegree of
   * the shape.
   *
   * @return int that represents the rotationDegree in degrees
   */
  int getEndRotationDegree();
}
