package cs5004.animator.view;

import java.awt.event.ActionListener;

/**
 * This interface represents the listener that will receive events when users make a change in
 * keyframes.
 */
public interface InterfaceFrameChangeListener extends ActionListener {
  /**
   * Method that is used when a event change happens.
   */
  void keyframeChanged(InterfaceFrameChangeEvent event);
}
