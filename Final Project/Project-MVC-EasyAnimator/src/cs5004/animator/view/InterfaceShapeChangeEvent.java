package cs5004.animator.view;

/**
 * This class represents a change in the shapes of an animation.
 */
public interface InterfaceShapeChangeEvent {
  /**
   * This method retrieves the type of change that is being made.
   *
   * @return the type of change.
   */
  ShapeChange getChangeType();

  /**
   * This method retrieves the type of shape being changed.
   *
   * @return the shape type.
   */
  String getShapeType();

  /**
   * This method retrieves the ID of the shape being changed.
   *
   * @return the shape ID
   */
  String getId();
}
