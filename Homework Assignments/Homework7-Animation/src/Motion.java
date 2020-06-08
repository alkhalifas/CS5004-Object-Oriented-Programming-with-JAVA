/**
 * This class represents the physical motion of the object. The motion is represented by the timing
 * in which it happens using the start time and stop time of the motion.
 */
public interface Motion {

  /**
   * This method will retrieve the start time of the objects motion.
   *
   * @return the start time of the objects motion.
   */
  int getStartTime();

  /**
   * This method will retrieve the stop time of the objects motion.
   *
   * @return the stop time of the objects motion.
   */
  int getEndTime();

  /**
   * This method will retrieve the type of motion that the object will do.
   *
   * @return the type of the objects motion.
   */
  Motions getMotionType();

  /**
   * Prints the motion name, its start, and stop times.
   *
   * @returns the text of the motion name, its start, and stop times.
   */
  String toString();

}
