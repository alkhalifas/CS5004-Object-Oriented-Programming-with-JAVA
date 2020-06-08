
/**
 * This abstract class represents the motions of the Shapes of the animation. The interface allows
 * for different motions to be created with a motion being constructed using a motion type, a start
 * time, and an end time.
 */
public abstract class AbstractMotion implements Motion {

  protected Motions motionType;
  protected int startTime;
  protected int endTime;

  /**
   * This constructor constructs an abstract motion object. This object is created using a motion
   * type, a start time, and a stop time.
   *
   * @param motionType which is the motion type of the object.
   * @param startTime  which is the time in which the object begins its motion.
   * @param stopTime   which is the time in which the object stops its motion.
   * @throws IllegalArgumentException if the times are negative.
   * @throws IllegalArgumentException if the stop time is before the start time.
   */
  protected AbstractMotion(int startTime, int stopTime, Motions motionType)
          throws IllegalArgumentException {
    this.startTime = startTime;
    this.endTime = stopTime;
    this.motionType = motionType;
  }

  /**
   * This method will retrieve the type of motion that the object will do.
   *
   * @return the type of the objects motion.
   */
  @Override
  public Motions getMotionType() {
    return this.motionType;
  }


  /**
   * This method will retrieve the start time of the objects motion.
   *
   * @return the start time of the objects motion.
   */
  @Override
  public int getStartTime() {
    return startTime;
  }

  /**
   * This method will retrieve the stop time of the objects motion.
   *
   * @return the stop time of the objects motion.
   */
  @Override
  public int getEndTime() {
    return endTime;
  }

  /**
   * Prints the motion name, its start, and stop times.
   *
   * @return Prints the motion name, its start, and stop times.
   */
  public String toString() {
    return "";
  }
}
