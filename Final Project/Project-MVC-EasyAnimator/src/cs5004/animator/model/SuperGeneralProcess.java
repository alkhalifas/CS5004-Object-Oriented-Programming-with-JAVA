package cs5004.animator.model;


/**
 * This class represents the actual process that a shape can take within an animation.
 */
public class SuperGeneralProcess extends GeneralProcess implements InterfaceRotateShape {
  private final int startOrientation;
  private final int endOrientation;

  /**
   * Constructor for super master process that initializes each of the fields of the process.
   *
   * @param type is what type of process.
   * @param t1   is starting time of the process.
   * @param x1   is the starting x coordinate of the shape.
   * @param y1   is the starting y coordinate of the shape.
   * @param w1   is the starting width of the shape.
   * @param h1   is the starting height of the shape.
   * @param o1   is the starting rotationDegree of the shape.
   * @param r1   is the starting value for red of the shape.
   * @param g1   is the starting value for green of the shape.
   * @param b1   is the starting value for blue of the shape.
   * @param t2   is the ending time of the process.
   * @param x2   is the ending x coordinate of the shape.
   * @param y2   is the ending y coordinate of the shape.
   * @param w2   is the ending width of the shape.
   * @param h2   is the ending height of the shape.
   * @param o2   is the ending rotationDegree of the shape.
   * @param r2   is the ending red value for the shape.
   * @param g2   is the ending green value for the shape.
   * @param b2   is the ending blue value for the shape.
   * @throws IllegalArgumentException will be thrown if the start time and or the end time is
   *                                  negative, or if the width and or the height are negative. the
   *                                  end time.
   */
  public SuperGeneralProcess(String type, int t1, int x1, int y1, int w1, int h1, int o1, int r1,
                             int g1, int b1, int t2, int x2, int y2, int w2, int h2, int o2, int r2,
                             int g2, int b2) throws IllegalArgumentException {

    super(type, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);

    this.startOrientation = o1;
    this.endOrientation = o2;
  }

  @Override
  public InterfaceShape setState(int time, InterfaceShape shape) {
    shape.setRotationDegree(this.findPointAt(time, this.startOrientation, this.endOrientation));
    return super.setState(time, shape);
  }

  /**
   * A getter method that retrieves the starting rotation.
   *
   * @return the starting rotation.
   */
  @Override
  public int getStartRotationDegree() {
    return this.startOrientation;
  }

  /**
   * A getter method that retrieves the ending rotation.
   *
   * @return the ending rotation.
   */
  @Override
  public int getEndRotationDegree() {
    return this.endOrientation;
  }
}
