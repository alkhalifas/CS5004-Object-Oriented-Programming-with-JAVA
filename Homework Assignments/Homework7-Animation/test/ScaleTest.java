
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

/**
 * Testing class for the scale class to ensure proper functionality.
 */
public class ScaleTest {

  private Scale scaleRectangleUp;
  private Scale scaleRectangleDown;
  private Scale scaleOvalUp;
  private Scale scaleOvalDown;

  private AbstractAnimatedShape animatedRectangle;
  private AbstractAnimatedShape animatedOval;
  private TreeMap<Motion, String> motions = new TreeMap<>();

  @Before
  public void setup() {
    Shape rectangle;
    Shape circle;

    rectangle = new Rectangle(0, 5, 50, 50, 0, 0, 255);
    animatedRectangle = new AnimatedRectangleShape(rectangle, 0, 50);
    scaleRectangleUp = new Scale(motions, animatedRectangle, 10, 20, 100, 100);
    scaleRectangleDown = new Scale(motions, animatedRectangle, 10, 20, 10, 10);

    circle = new Oval(0, 0, 50, 50, 0, 255, 0);
    animatedOval = new AnimatedOvalShape(circle, 0, 100);
    scaleOvalUp = new Scale(motions, animatedOval, 20, 40, 100, 100);
    scaleOvalDown = new Scale(motions, animatedOval, 20, 40, 5, 5);
  }

  @Test
  public void testToStringRectangleScaleUpAndDown() {
    assertEquals("scales from (50, 50) to (100, 100) from t=10 to t=20",
            scaleRectangleUp.toString());
    assertEquals("scales from (50, 50) to (10, 10) from t=10 to t=20",
            scaleRectangleDown.toString());
  }

  @Test
  public void testToStringOvalScaleUpAndDown() {
    assertEquals("scales from (50, 50) to (100, 100) from t=20 to t=40",
            scaleOvalUp.toString());
    assertEquals("scales from (50, 50) to (5, 5) from t=20 to t=40",
            scaleOvalDown.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectangleBadStartTime() {
    Scale testBadRectangle = new Scale(motions, animatedRectangle, -5, 10, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectangleBadEndTime() {
    Scale testBadRectangle = new Scale(motions, animatedRectangle, 5, -10, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectangleBadTimes() {
    Scale testBadRectangle = new Scale(motions, animatedRectangle, 5, 10000, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectangleSameTimes() {
    Scale testBadRectangle = new Scale(motions, animatedRectangle, 5, 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectangleSameScales() {
    Scale testBadRectangle = new Scale(motions, animatedRectangle, 25, 30, 50, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRectangleNegativeScale() {
    Scale testBadRectangle = new Scale(motions, animatedRectangle, 25, 30, -50, -50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOvalBadStartTime() {
    Scale testBadRectangle = new Scale(motions, animatedOval, -5, 10, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOvalBadEndTime() {
    Scale testBadRectangle = new Scale(motions, animatedOval, 5, -10, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOvalBadTimes() {
    Scale testBadRectangle = new Scale(motions, animatedOval, 5, 10000, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOvalSameTimes() {
    Scale testBadRectangle = new Scale(motions, animatedOval, 5, 5, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOvalSameScales() {
    Scale testBadRectangle = new Scale(motions, animatedOval, 25, 30, 50, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOvalNegativeScale() {
    Scale testBadRectangle = new Scale(motions, animatedOval, 25, 30, -50, -50);
  }


}