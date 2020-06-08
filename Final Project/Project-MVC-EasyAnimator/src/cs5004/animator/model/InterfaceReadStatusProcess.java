package cs5004.animator.model;

/**
 * This class represents a process that allows for shape rotation information retrieval.
 */
public interface InterfaceReadStatusProcess extends InterfaceReadProcess {
  /**
   * This method will retrieve the rotationDegree of this command which the starting rotationDegree
   * the shape.
   *
   * @return int that represents the rotationDegree in degrees
   */
  int getStartOrientation();

  /**
   * This method will retrieve  the rotationDegree of this command which the final rotationDegree
   * the shape.
   *
   * @return int that represents the rotationDegree in degrees
   */
  int getEndOrientation();
}
