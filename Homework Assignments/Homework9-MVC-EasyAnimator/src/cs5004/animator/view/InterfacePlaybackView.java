package cs5004.animator.view;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import cs5004.animator.controller.InterpretStatusKeyFrame;
import cs5004.animator.model.InterfaceInterpretShape;

/**
 * This interface represents a playback view that uses a gui for the user.
 */
public interface InterfacePlaybackView extends InterfaceView {

  /**
   * This method will display a list of shapes onto the graphical interface for the user.
   */
  void display(List<InterfaceInterpretShape> shapes);

  /**
   * Retrieves the shapes to be displayed.
   *
   * @return a list of shapes
   */
  List<InterfaceShapeCell> getShapes();

  /**
   * Places a map of all shapes in animation in the view.
   *
   * @param shapes all the shapes in the animation.
   */
  void setShapes(Map<String, InterfaceInterpretShape> shapes);

  /**
   * Retrieves all keyframes.
   */
  Map<String, List<InterpretStatusKeyFrame>> getKeyframes();

  /**
   * Sets all keyframes.
   */
  void setKeyframes(Map<String, List<InterpretStatusKeyFrame>> keyframes);

  /**
   * sets the width of a shape.
   */
  void setWidth(int width);

  /**
   * sets the height of a shape.
   */
  void setHeight(int height);

  /**
   * Adds a listener that can receive action events.
   */
  void addButtonListener(ActionListener listener);

  /**
   * Adds a listener that can receive property change events.
   */
  void addPropertyListener(PropertyChangeListener listener);

  /**
   * Adds a listener that receives a SHAPE CHANGE event.
   */
  void addShapeChangeListener(InterfaceShapeChangeListener listener);

  /**
   * Adds a listener that receives a frame change event.
   */
  void addFrameChangeListener(InterfaceFrameChangeListener listener);

  /**
   * Displays an error on the screen indicating to the user an error occured.
   */
  void displayError(String s);


  /**
   * Method that allows that pushes an update to the slider for the given tick.
   */
  void setSlider(double tick);
}
