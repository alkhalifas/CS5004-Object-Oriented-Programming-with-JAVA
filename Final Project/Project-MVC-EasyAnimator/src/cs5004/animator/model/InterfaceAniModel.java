package cs5004.animator.model;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * This interface represent the model for the actual animation.
 */
public interface InterfaceAniModel {

  /**
   * This method creates a list of the shapes at the state of the animation at the given timestamp.
   * @param time which is represented in ticks which is unit less.
   */
  List<InterfaceInterpretShape> getState(int time);

  /**
   * THis method returns the shapes within the map.
   */
  LinkedHashMap<String, InterfaceInterpretShape> getShapes();


  /**
   * This method creates all the processes from the model.
   */
  LinkedHashMap<String, List<InterfaceInterpretStatusProcess>> getProcesses();

  /**
   * This method will retrieve the x of the model.
   */
  int getX();

  /**
   * This method will retrieve the y of the model.
   */
  int getY();

  /**
   * This method will retrieve the height of the model.
   */
  int getHeight();

  /**
   * This method will retrieve the width of the model.
   */
  int getWidth();

  /**
   * This method will retrieve the last tick of the model.
   **/
  int getLastTick();
}
