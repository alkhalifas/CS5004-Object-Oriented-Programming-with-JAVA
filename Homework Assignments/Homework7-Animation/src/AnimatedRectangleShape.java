/**
 * This class represents the animated rectangle shape which extends the abstract animated shape and
 * prints the description of the motion.
 */
public class AnimatedRectangleShape extends AbstractAnimatedShape {

  /**
   * Constructs an animated rectangle shape using the shape type, start time, and end times.
   * @param shape     the shape type to be animated.
   * @param startTime the starting time of the animation
   * @param endTime   the ending time of the animation.
   */
  public AnimatedRectangleShape(Shape shape, int startTime, int endTime) {
    super(shape, startTime, endTime);
  }


  /**
   * This toString method prints out the description of the animated object.
   *
   * @return a string containing the coordinates, dimensions, and the RGB values of the shape.
   */
  @Override
  public String toString() {
    return "Type: Rectangle\nCorner: (" + shape.getX() + ", " + shape.getY() + "), Width: "
            + shape.getWidth() + ", Height: " + shape.getHeight()
            + ", Color: (" + shape.getRed() + ", " + shape.getGreen() + ", " + shape.getBlue()
            + ")\nAppears at t=" + appearanceTime
            + "\nDisappears at t=" + disappearanceTime + "\n";
  }
}
