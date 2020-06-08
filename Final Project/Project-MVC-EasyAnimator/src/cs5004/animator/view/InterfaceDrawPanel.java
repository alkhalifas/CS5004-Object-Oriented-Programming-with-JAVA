package cs5004.animator.view;

import java.util.List;

import cs5004.animator.model.InterfaceInterpretShape;

/**
 * Interface represents the panel for drawing the animation using the draw() method.
 */
public interface InterfaceDrawPanel {

  /**
   * Method that paints the list of shapes onto the screen.
   * @param shapes the shapes to be painted on to the screen.
   */
  void draw(List<InterfaceInterpretShape> shapes);
}
