package cs5004.animator.model;

/**
 * THis class represents a process for a shape within the animation timeframe.
 */
public interface InterfaceProcess extends InterfaceInterpretProcess {
  /**
   * This method will mutate a shape within a process to a state at the given time of the overall
   * process.
   *
   * @param time  is the time to mutate the shape to the state.
   * @param shape the shape of the object before the process starts.
   * @return InterfaceShape which is the mutated shape.
   */
  InterfaceShape setState(int time, InterfaceShape shape);
}
