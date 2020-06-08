
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

/**
 * Testing Class for the color change to ensure proper functionality.
 */
public class ChangeColorTest {

  private ChangeColor changeColorRectangle;
  private ChangeColor changeColorOval;

  private AbstractAnimatedShape animatedRectangle;
  private AbstractAnimatedShape animatedOval;

  private TreeMap<Motion, String> motions = new TreeMap<>();

  @Before
  public void setup() {
    Shape rectangleOne;
    Shape ovalOne;

    rectangleOne = new Rectangle(5, 5, 50, 50, 0, 0, 255);
    animatedRectangle = new AnimatedRectangleShape(rectangleOne, 0, 50);
    changeColorRectangle = new ChangeColor(motions, animatedRectangle, 0, 50,
            255, 0, 0);


    ovalOne = new Oval(5, 5, 50, 50, 0, 0, 255);
    animatedOval = new AnimatedOvalShape(ovalOne, 0, 50);
    changeColorOval = new ChangeColor(motions, animatedOval, 0, 50, 255,
            0, 0);
  }

  @Test
  public void testToStringRectangle() {
    assertEquals("changes color from (0, 0, 255) to (255, 0, 0) from t=0 to t=50",
            changeColorRectangle.toString());
  }

  @Test
  public void testToStringOval() {
    assertEquals("changes color from (0, 0, 255) to (255, 0, 0) from t=0 to t=50",
            changeColorOval.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectangleStartTime() {
    ChangeColor testBadRectangle = new ChangeColor(motions, animatedRectangle, -10,
            10, 0, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectangleEndTime() {
    ChangeColor testBadRectangle = new ChangeColor(motions, animatedRectangle, 10,
            -10, 256, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectangleSameTime() {
    ChangeColor testBadRectangle = new ChangeColor(motions, animatedRectangle, 10,
            10, 0, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectangleTimeBounds() {
    ChangeColor testBadRectangle = new ChangeColor(motions, animatedRectangle, 10,
            1000, 0, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectangleSameColor() {
    ChangeColor newTest = new ChangeColor(motions, animatedRectangle, 0, 10,
            0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalStartTime() {
    ChangeColor testBadRectangle = new ChangeColor(motions, animatedRectangle, -10,
            10, 0, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalEndTime() {
    ChangeColor testBadRectangle = new ChangeColor(motions, animatedOval, 10,
            -10, 256, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalSameTime() {
    ChangeColor testBadRectangle = new ChangeColor(motions, animatedOval, 10,
            10, 0, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalTimeBounds() {
    ChangeColor testBadRectangle = new ChangeColor(motions, animatedOval, 10,
            1000, 0, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOvalSameColor() {
    ChangeColor newTest = new ChangeColor(motions, animatedOval, 0, 10,
            0, 0, 255);
  }
}