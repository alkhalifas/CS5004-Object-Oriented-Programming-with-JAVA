package cs5004.animator.view;

import java.awt.event.ActionListener;

/**
 * This interface represents a listener that receives a change event.
 */
public interface InterfaceShapeChangeListener extends ActionListener {
  /**
   * Method is used when the user changes a shape.
   */
  void shapeChanged(InterfaceShapeChangeEvent event);
}
