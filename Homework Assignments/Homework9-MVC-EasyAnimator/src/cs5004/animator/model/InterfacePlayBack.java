package cs5004.animator.model;

import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;

import cs5004.animator.util.AnimationBuilder;

/**
 * Represents the playbackBuilder for an user changeable view.
 */
public interface InterfacePlayBack extends AnimationBuilder {

  /**
   * This method removes a process from the current playbackBuilder with the given start time.
   * @param id is the id of the shape that the process you are removing is associated with.
   * @param startingTime is the start time of the process you remove.
   * @return the playbackBuilder after removal.
   */
  InterfacePlayBack removeProcess(String id, int startingTime);

  /**
   * This method removes a shape from the current playbackBuilder.
   * @param id is the id of the shape that the process you are removing is associated with.
   * @return the playbackBuilder after removal.
   */
  InterfacePlayBack removeShape(String id);

  /**
   * This method will retrieve the processes from the playbackBuilder.
   * @return a map of the ids for the processes in the playbackBuilder.
   */
  LinkedHashMap<String, List<InterfaceInterpretStatusProcess>> getProcesses();

  /**
   * This method will retrieve the shapes from the playbackBuilder.
   * @return a map of the ids for the shapes in the playbackBuilder.
   */
  LinkedHashMap<String, InterfaceInterpretShape> getShapes();

  /**
   * This method will retrieve the needed space from the playbackBuilder.
   * @return the dimensions for the animation.
   */
  Dimension getNeededSpace();
}
