package cs5004.animator.view;

/**
 * Represents a node in a list for the GUI that uses the shape.
 */
public interface InterfaceShapeCell {
  /**
   * Retrieves the shape ID.
   *
   * @return the shape ID
   */
  String getID();

  /**
   * Retrieves the shape's type such as rectangle or ellipse.
   *
   * @return the shape type
   */
  String getType();
}
