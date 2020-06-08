
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class represents the change color method which is extending the abstract motion class. This
 * class creates a change color motion using the starting RGB and ending RBG.
 */
public class ChangeColor extends AbstractMotion {

  private int startRed;
  private int startGreen;
  private int startBlue;
  private int endRed;
  private int endGreen;
  private int endBlue;


  /**
   * Changes the color of an object from one set of RGB values to another set of RGB values. If the
   * colors are changed already, it will change based on the last change that happened. It will not
   * allow for a color change to happen to the same set of RGB values.
   *
   * @param trueMotions         the treemap of all the motions.
   * @param sampleAnimatedShape the shape of type AbstractAnimatedShape.
   * @param startTime           the starting time of the animation of scaling.
   * @param endTime             the end time of the animation of scaling.
   * @param endRed              the red color to end at.
   * @param endGreen            the green color to end at.
   * @param endBlue             the blue color to end at.
   * @throws IllegalArgumentException will be thrown if the RGB values are negative, or they are
   *                                  above 255, or of the end time happens before the start time,
   *                                  or of the animation happens outside the time frame.
   */
  public ChangeColor(TreeMap trueMotions, AbstractAnimatedShape sampleAnimatedShape,
                     int startTime, int endTime, int endRed,
                     int endGreen, int endBlue) throws IllegalArgumentException {
    super(startTime, endTime, Motions.COLOR);

    if (endRed < 0 || endGreen < 0 || endBlue < 0) {
      throw new IllegalArgumentException("RBG Cannot contain negative numbers.");
    }

    if (endRed > 255 || endGreen > 255 || endBlue > 255) {
      throw new IllegalArgumentException("RBG Cannot be above 255.");
    }

    if (endTime - startTime <= 0 || startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Start times must happen before stop times.");
    }

    if (startTime < sampleAnimatedShape.appearanceTime
            || endTime > sampleAnimatedShape.disappearanceTime) {
      throw new IllegalArgumentException("Animation must be in the correct time frames.");
    }

    TreeMap<Motion, String> collectedMotion = trueMotions;
    ArrayList<ChangeColor> colorMotions = new ArrayList<>();

    if (collectedMotion.size() <= 0) {
      this.startRed = sampleAnimatedShape.shape.getRed();
      this.startGreen = sampleAnimatedShape.shape.getGreen();
      this.startBlue = sampleAnimatedShape.shape.getBlue();
      this.endRed = endRed;
      this.endGreen = endGreen;
      this.endBlue = endBlue;
      if (this.startRed == endRed && this.startGreen == endGreen && this.startBlue == endBlue) {
        throw new IllegalArgumentException("The color cannot be the same!");
      }
      return;
    }

    for (Motion key : collectedMotion.keySet()) {
      if (key.getMotionType() == Motions.COLOR) {
        colorMotions.add((ChangeColor) key);
      }
    }

    if (colorMotions.size() <= 0) {
      this.startRed = sampleAnimatedShape.shape.getRed();
      this.startGreen = sampleAnimatedShape.shape.getGreen();
      this.startBlue = sampleAnimatedShape.shape.getBlue();
      this.endRed = endRed;
      this.endGreen = endGreen;
      this.endBlue = endBlue;
      return;
    }
    newMotionHelper(colorMotions, sampleAnimatedShape, startTime, endRed, endGreen, endBlue);
  }

  /**
   * Gets the ending red color of the RGB.
   *
   * @return the red color as an integer.
   */
  public int getEndRed() {
    return this.endRed;
  }

  /**
   * Gets the ending green color of the RGB.
   *
   * @return the green color as an integer.
   */
  public int getEndGreen() {
    return this.endGreen;
  }

  /**
   * Gets the ending blue color of the RGB.
   *
   * @return the blue color as an integer.
   */
  public int getEndBlue() {
    return this.endBlue;
  }

  /**
   * Sets the starting red color of the RGB.
   */
  public void setStartRed(int red) {
    this.startRed = red;
  }

  /**
   * Sets the starting green color of the RGB.
   */
  public void setStartGreen(int green) {
    this.startRed = green;
  }

  /**
   * Sets the starting blue color of the RGB.
   */
  public void setStartBlue(int blue) {
    this.startRed = blue;
  }

  /**
   * This method describes the change motion showing the starting RGB values and the ending RGB
   * values.
   *
   * @return a string describing the change color of the method.
   */
  @Override
  public String toString() {
    return "changes color from (" + startRed + ", " + startGreen + ", "
            + startBlue + ") to (" + endRed
            + ", " + endGreen + ", " + endBlue + ") from t="
            + startTime + " to t=" + endTime;
  }


  /**
   * THis helper function is used for the color change in order to help keep track, via an array
   * list, of the changes that are happening throughout the animation.
   *
   * @param colorMotions        an array list for tracking.
   * @param sampleAnimatedShape the shape of type AbstractAnimatedShape.
   * @param startTime           the staring time of the animation.
   * @param endRed              the ending red integer of the RGB.
   * @param endGreen            the ending green integer of the RGB.
   * @param endBlue             the ending blue integer of the RGB.
   */
  private void newMotionHelper(ArrayList<ChangeColor> colorMotions,
                               AbstractAnimatedShape sampleAnimatedShape,
                               int startTime, int endRed, int endGreen, int endBlue) {
    ChangeColor previousMotion = colorMotions.get(0);
    boolean lastLoop = false;

    for (ChangeColor motion : colorMotions) {

      if (lastLoop) {
        motion.setStartRed(previousMotion.endRed);
        motion.setStartGreen(previousMotion.endGreen);
        motion.setStartBlue(previousMotion.endBlue);
        break;
      }

      if (startTime < motion.getStartTime()) {
        this.startRed = sampleAnimatedShape.shape.getRed();
        this.startGreen = sampleAnimatedShape.shape.getGreen();
        this.startBlue = sampleAnimatedShape.shape.getBlue();
        this.endRed = endRed;
        this.endGreen = endGreen;
        this.endBlue = endBlue;
        motion.setStartRed(endRed);
        motion.setStartGreen(endGreen);
        motion.setStartBlue(endBlue);
        break;
      } else {
        if (startTime - motion.getEndTime() <= startTime - previousMotion.getEndTime()) {
          previousMotion = motion;
        }
        this.startRed = previousMotion.getEndRed();
        this.startGreen = previousMotion.getEndGreen();
        this.startBlue = previousMotion.getEndBlue();
        this.endRed = endRed;
        this.endGreen = endGreen;
        this.endBlue = endBlue;
        previousMotion = this;
        lastLoop = true;
      }
    }
  }


}
