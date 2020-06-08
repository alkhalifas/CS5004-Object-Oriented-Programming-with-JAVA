package cs5004.animator.view;

/**
 * This method represents a node in a list for the GUI display in which they correspond to the
 * shapes.
 */
public class ShapeCell implements InterfaceShapeCell {
  private String id;
  private String type;

  /**
   * Constructor that creates a shape cell for a shape.
   *
   * @param id   the unique ID of the shape.
   * @param type the type of shape.
   */
  public ShapeCell(String id, String type) {
    this.id = id;
    this.type = type;
  }

  /**
   * Retrieves the shape ID.
   *
   * @return the shape ID
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * Retrieves the shape's type such as rectangle or ellipse.
   *
   * @return the shape type
   */
  @Override
  public String getType() {
    return this.type;
  }

  /**
   * Prints the String for the two assocaited attributes.
   *
   * @return the toString value
   */
  @Override
  public String toString() {
    return this.id + " - " + this.type;
  }

  /**
   * Determines equality based on attributes.
   *
   * @return boolean of true or false.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof ShapeCell)) {
      return false;
    }

    ShapeCell otherShapeCell = (ShapeCell) other;

    return this.id.equals(otherShapeCell.getID())
            && this.type.equals(otherShapeCell.getType());
  }

  @Override
  public int hashCode() {
    int result = 17;

    result = 31 * result + this.id.hashCode();
    result = 31 * result + this.type.hashCode();

    return result;
  }
}
