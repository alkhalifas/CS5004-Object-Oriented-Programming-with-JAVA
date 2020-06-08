
/**
 * This class represents the abstract animated shape class which allows for the addition of an
 * animation motion. This class utilizes an array list for the functionality.
 */
public class AbstractAnimatedShape implements AnimatedShape {
  protected int appearanceTime;
  protected int disappearanceTime;
  protected Shape shape;

  /**
   * This constructor creates an object using the shape and start and stop times for the animation.
   *
   * @param shape     which is the shape type of the object being animated.
   * @param startTime which is the start time of the objects animation.
   * @param endTime   which is the stop time of the  objects animation.
   */
  public AbstractAnimatedShape(Shape shape, int startTime, int endTime) {
    this.shape = shape;
    this.appearanceTime = startTime;
    this.disappearanceTime = endTime;
  }

}
