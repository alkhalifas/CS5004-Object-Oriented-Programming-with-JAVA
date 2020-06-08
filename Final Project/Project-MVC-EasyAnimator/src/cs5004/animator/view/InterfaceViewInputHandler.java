package cs5004.animator.view;

import java.awt.Component;
import java.awt.Color;
import java.beans.PropertyChangeListener;

/**
 * This class represents an input handler for an playback view that.
 */
public interface InterfaceViewInputHandler {
  /**
   * Method that adds a listener that receives a SHAPE CHANGE event.
   */
  void addShapeChangeListener(InterfaceShapeChangeListener listener);

  /**
   * Method that adds a listener that receives a frame change.
   */
  void addFrameChangeListener(InterfaceFrameChangeListener listener);

  /**
   * Method that adds a listener that receives a property change event.
   */
  void addExportListener(PropertyChangeListener listener);

  /**
   * Helper method to export SVG file. THis method will create a pop up that allows user to specify
   * path.
   */
  void exportToSVG(Component parent);

  /**
   * Method that allows for a SHAPE CHANGE specified by the user.
   */
  void changeShape(Component parent, ShapeChange type, String id);

  /**
   * Method for a changeFrame using the parent, type and id and time only.
   */
  void changeFrame(Component parent, FrameChange type, String id, int time);

  /**
   * A frame change event in addition to other shape attributes as arguments.
   */
  void changeFrame(Component parent, FrameChange type, String id, int time, int x,
                   int y, int width, int height, int rotationDegree, Color color);
}
