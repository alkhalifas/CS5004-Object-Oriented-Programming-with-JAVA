
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class represents the move method which is extending the abstract motion class. This class
 * creates a move motion using the starting coordinates and ending coordinates.
 */
public class Move extends AbstractMotion {

  private int startX;
  private int startY;
  private int endX;
  private int endY;


  /**
   * This method moves an object from one set of coordinates to another set of coordinates. The
   * motion is tracked using an array allowing the motion to ensure that the last set of coordinates
   * will be used as the starting coordinates.
   *
   * @param trueMotions         the array list for tracking.
   * @param sampleAnimatedShape the shape of type AbstractAnimatedShape.
   * @param startTime           the starting time of the animation of scaling.
   * @param endTime             the ending time of the movement method.
   * @param endX                the ending x coordinate of the method.
   * @param endY                the ending y coordinate of the method.
   * @throws IllegalArgumentException will be thrown if the end time happens before the start time,
   *                                  or if the times are not within the set time frame, or if the
   *                                  start coordinates and end coordinates are the same.
   */
  public Move(TreeMap trueMotions, AbstractAnimatedShape sampleAnimatedShape, int startTime,
              int endTime, int endX, int endY) throws IllegalArgumentException {

    super(startTime, endTime, Motions.MOVE);

    if (endTime - startTime <= 0 || startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("The start time must occur before the end time.");
    }

    if (startTime < sampleAnimatedShape.appearanceTime
            || endTime > sampleAnimatedShape.disappearanceTime) {
      throw new IllegalArgumentException("The animation must be in the correct time frame.");
    }

    ArrayList<Move> moveMotions = new ArrayList<>();

    if (((TreeMap<Motion, String>) trueMotions).size() <= 0) {
      this.startX = (int) sampleAnimatedShape.shape.getX();
      this.startY = (int) sampleAnimatedShape.shape.getY();
      this.endX = endX;
      this.endY = endY;
      if (this.startX == endX && this.startY == endY) {
        throw new IllegalArgumentException("The animation cannot remain stagnant.");
      }
      return;
    }

    for (Motion key : ((TreeMap<Motion, String>) trueMotions).keySet()) {
      if (key.getMotionType() == Motions.MOVE) {
        moveMotions.add((Move) key);
      }
    }

    if (moveMotions.size() <= 0) {
      this.startX = (int) sampleAnimatedShape.shape.getX();
      this.startY = (int) sampleAnimatedShape.shape.getY();
      this.endX = endX;
      this.endY = endY;
      if (this.startX == endX && this.startY == endY) {
        throw new IllegalArgumentException("The animation cannot remain stagnant.");
      }
      return;
    }
    newMotionHelper(moveMotions, sampleAnimatedShape, startTime, endX, endY);
  }

  /**
   * This method retrieves the X coordinate that the shape will end at.
   *
   * @return next x coordinate.
   */
  public int getToX() {
    return this.endX;
  }

  /**
   * This method retrieves the y coordinate that the shape will end at.
   *
   * @return next y coordinate.
   */
  public int getToY() {
    return this.endY;
  }

  /**
   * This method sets the X coordinate that the shape will start from.
   */
  public void setFromX(int x) {
    this.startX = x;
  }

  /**
   * This method sets the Y coordinate that the shape will start from.
   */
  public void setFromY(int y) {
    this.startY = y;
  }

  /**
   * This toString methods describes the movement of the method above.
   *
   * @return a string describing the movement of the move method.
   */
  @Override
  public String toString() {
    return "moves from (" + startX + ", " + startY + ") to ("
            + endX + ", " + endY
            + ") from t=" + startTime
            + " to t=" + endTime;
  }


  /**
   * This helper function is used with the move method allowing for it to keep track via an array
   * list of the movements that happened allowing it to be able to use the last movement as the
   * starting coordinates of the next animation.
   *
   * @param moveMotions         an array list for tracking.
   * @param sampleAnimatedShape the shape of type AbstractAnimatedShape.
   * @param startTime           the staring time of the animation.
   * @param endX                the ending x coordinate of the movement.
   * @param endY                the ending y coordinate of the movement.
   */
  private void newMotionHelper(ArrayList<Move> moveMotions,
                 AbstractAnimatedShape sampleAnimatedShape, int startTime, int endX, int endY) {
    Move previousMotion = moveMotions.get(0);
    boolean lastLoop = false;

    for (Move motion : moveMotions) {
      if (lastLoop) {
        motion.setFromX(previousMotion.endX);
        motion.setFromY(previousMotion.endY);
        break;
      }
      if (startTime < moveMotions.get(0).getStartTime()) {
        this.startX = (int) sampleAnimatedShape.shape.getX();
        this.startY = (int) sampleAnimatedShape.shape.getY();
        this.endX = endX;
        this.endY = endY;
        motion.setFromX(endX);
        motion.setFromY(endY);
        break;
      } else {
        if (startTime - motion.getEndTime() <= startTime - previousMotion.getEndTime()) {
          previousMotion = motion;
          this.startX = previousMotion.getToX();
          this.startY = previousMotion.getToY();
          this.endX = endX;
          this.endY = endY;
          previousMotion = this;
          lastLoop = true;
        }
      }
    }
  }
}
