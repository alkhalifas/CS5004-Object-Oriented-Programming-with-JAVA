/**
 * This class represents the animated oval shape which extends the abstract animated shape and
 * prints the description of the motion.
 */
public class AnimatedOvalShape extends AbstractAnimatedShape {

  /**
   * Constructs an animated oval shape using the shape type, start time, and end times.
   * @param shape     the shape type to be animated.
   * @param startTime the starting time of the animation
   * @param endTime   the ending time of the animation.
   */
  public AnimatedOvalShape(Shape shape, int startTime, int endTime) {
    super(shape, startTime, endTime);
  }

  /**
   * This method prints the string description of the animated shape showing its dimensions,
   * coordinates, and colors.
   *
   * @return
   */
  @Override
  public String toString() {
    return "Type: Circle\nCenter: ("
            + shape.getX() + ", " + shape.getY()
            + "), Width: " + shape.getWidth()
            + ", Height: " + shape.getHeight()
            + ", Color: (" + shape.getRed() + ", " + shape.getGreen() + ", " + shape.getBlue()
            + ")\nAppears at t=" + appearanceTime + "\nDisappears at t=" + disappearanceTime
            + "\n";
  }
}
