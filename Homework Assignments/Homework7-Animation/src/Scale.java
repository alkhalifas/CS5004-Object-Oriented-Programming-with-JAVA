
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * THis class represents the scale method which is extending the abstract motion class. This class
 * creates a scale motion using the starting width and height as well as the ending width and
 * height.
 */
public class Scale extends AbstractMotion {

  private int startWidth;
  private int startHeight;
  private int endWidth;
  private int endHeight;


  /**
   * This scale method allows an object to change or 'scale' its width and height to new values over
   * a period of time.
   *
   * @param trueMotions         the motion that will be used with TreeMap.
   * @param sampleAnimatedShape the shape of type AbstractAnimatedShape.
   * @param startTime           the starting time of the animation.
   * @param endTime             the ending time of the animation.
   * @param endWidth            the ending width of the scale method.
   * @param endHeight           the ending height of the scale method.
   * @throws IllegalArgumentException if the starting time is after the ending time, or of the
   *                                  dimensions are negative, or of the animation times not within
   *                                  the correct range.
   */
  public Scale(TreeMap trueMotions, AbstractAnimatedShape sampleAnimatedShape,
               int startTime, int endTime, int endWidth, int endHeight)
          throws IllegalArgumentException {

    super(startTime, endTime, Motions.SCALE);

    ArrayList<Scale> scaleMotions = new ArrayList<>();

    if (endTime - startTime <= 0 || startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Animation times cannot be negative.");
    }

    if (endWidth < 1 || endHeight < 1) {
      throw new IllegalArgumentException("Dimensions cannot be negative.");
    }

    if (startTime < sampleAnimatedShape.appearanceTime
            || endTime > sampleAnimatedShape.disappearanceTime) {
      throw new IllegalArgumentException("Animation must be within the correct times.");
    }

    if (((TreeMap<Motion, String>) trueMotions).size() <= 0) {
      this.startWidth = (int) sampleAnimatedShape.shape.getWidth();
      this.startHeight = (int) sampleAnimatedShape.shape.getHeight();
      this.endWidth = endWidth;
      this.endHeight = endHeight;
      if (this.startWidth == endWidth && this.startHeight == endHeight) {
        throw new IllegalArgumentException("Dimensions of the shape must change to animate!");
      }
      return;
    }

    for (Motion key : ((TreeMap<Motion, String>) trueMotions).keySet()) {
      if (key.getMotionType() == Motions.SCALE) {
        scaleMotions.add((Scale) key);
      }
    }

    if (scaleMotions.size() <= 0) {
      this.startWidth = (int) sampleAnimatedShape.shape.getWidth();
      this.startHeight = (int) sampleAnimatedShape.shape.getHeight();
      this.endWidth = endWidth;
      this.endHeight = endHeight;
      if (this.startWidth == endWidth && this.startHeight == endHeight) {
        throw new IllegalArgumentException("Dimensions of the shape must change to animate!");
      }
      return;
    }
    newMotionHelper(scaleMotions, sampleAnimatedShape, startTime, endWidth, endHeight);
    if (this.startWidth == endWidth && this.startHeight == endHeight) {
      throw new IllegalArgumentException("Dimensions of the shape must change to animate!");
    }
  }

  /**
   * THis method retrieves the ending width for the scale motion.
   *
   * @return the ending width value.
   */
  public int getEndWidth() {
    return this.endWidth;
  }

  /**
   * THis method retrieves the ending height for the scale motion.
   *
   * @return the ending height value.
   */
  public int getEndHeight() {
    return this.endHeight;
  }

  /**
   * THis method retrieves the starting width for the scale motion.
   */
  public void setStartWidth(int width) {
    this.startWidth = width;
  }

  /**
   * This method retrieves the starting height for the scale motion.
   */
  public void setStartHeight(int height) {
    this.startHeight = height;
  }

  @Override
  public String toString() {
    return "scales from (" + startWidth + ", "
            + startHeight + ") to (" + endWidth + ", " + endHeight
            + ") from t=" + startTime
            + " to t=" + endTime;
  }

  /**
   * This helper method is used with the scale method above in order to help track the motions that
   * are happening and use the last set of dimensions as the new dimensions.
   *
   * @param scaleMotions        the array list for tracking.
   * @param sampleAnimatedShape the shape of type AbstractAnimatedShape.
   * @param startTime           the starting time of the animation of scaling.
   * @param endWidth            the ending width of the scaling animation.
   * @param endHeight           the ending height of the scaling animation.
   */
  private void newMotionHelper(ArrayList<Scale> scaleMotions,
                               AbstractAnimatedShape sampleAnimatedShape,
                               int startTime, int endWidth, int endHeight) {

    Scale previousMotion = scaleMotions.get(0);
    boolean lastLoop = false;

    for (Scale motion : scaleMotions) {
      if (lastLoop) {
        System.out.println(previousMotion);
        motion.setStartWidth(previousMotion.endWidth);
        motion.setStartHeight(previousMotion.endHeight);
        break;
      }

      if (startTime < motion.getStartTime()) {
        this.startWidth = (int) sampleAnimatedShape.shape.getWidth();
        this.startHeight = (int) sampleAnimatedShape.shape.getHeight();
        this.endWidth = endWidth;
        this.endHeight = endHeight;
        motion.setStartWidth(endWidth);
        motion.setStartHeight(endHeight);
        break;

      } else {
        if (startTime - motion.getEndTime() <= startTime - previousMotion.getEndTime()) {
          previousMotion = motion;
          this.startWidth = previousMotion.getEndWidth();
          this.startHeight = previousMotion.getEndHeight();
          this.endWidth = endWidth;
          this.endHeight = endHeight;
          previousMotion = this;
          lastLoop = true;
        }
      }
    }
  }
}
