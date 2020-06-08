package cs5004.animator.view;

import java.awt.event.ActionEvent;

/**
 * This class represents an action event handler for SHAPE CHANGEs from the playback view.
 */
public class ShapeChangeEvent extends ActionEvent implements InterfaceShapeChangeEvent {
  private final ShapeChange actionChangeType;
  private final String actionShapeType;
  private final String id;

  /**
   * This constructor creates a SHAPE CHANGE event.
   *
   * @param source the object where the event stats
   * @param changeType the type of change being made.
   * @param shapeType the type of the changing shape.
   * @param id the id of the shape.
   */
  public ShapeChangeEvent(Object source, ShapeChange changeType, String shapeType, String id) {
    super(source, ActionEvent.ACTION_PERFORMED, "SHAPE CHANGE");

    this.actionChangeType = changeType;
    this.actionShapeType = shapeType;
    this.id = id;
  }

  /**
   * This method retrieves the type of change that is being made.
   *
   * @return the type of change.
   */
  @Override
  public ShapeChange getChangeType() {
    return this.actionChangeType;
  }

  /**
   * This method retrieves the type of shape being changed.
   *
   * @return the shape type.
   */
  @Override
  public String getShapeType() {
    return this.actionShapeType;
  }

  /**
   * This method retrieves the ID of the shape being changed.
   *
   * @return the shape ID
   */
  @Override
  public String getId() {
    return this.id;
  }
}
