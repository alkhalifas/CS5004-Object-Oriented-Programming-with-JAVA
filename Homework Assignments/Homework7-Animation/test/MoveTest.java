
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;

/**
 * Testing class for move to ensure correct functionality.
 */
public class MoveTest {

  private Move moveRectangle;
  private Move moveOval;

  private AbstractAnimatedShape animatedRectangle;
  private AbstractAnimatedShape animatedOval;

  private TreeMap<Motion, String> motions = new TreeMap<>();

  @Before
  public void setup() {

    Shape rectangleShape;
    Shape ovalShape;

    rectangleShape = new Rectangle(5, 5, 50, 50, 100, 100, 100);
    animatedRectangle = new AnimatedRectangleShape(rectangleShape, 0, 100);
    moveRectangle = new Move(motions, animatedRectangle, 0, 50, 50, 50);

    ovalShape = new Oval(0, 0, 5, 5, 255, 255, 255);
    animatedOval = new AnimatedOvalShape(ovalShape, 0, 200);
    moveOval = new Move(motions, animatedOval, 10, 20, 5, 5);
  }

  @Test
  public void testToStringCorrect() {
    assertEquals("moves from (5, 5) to (50, 50) from t=0 to t=50", moveRectangle.toString());
    assertEquals("moves from (0, 0) to (5, 5) from t=10 to t=20", moveOval.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveRectangleIncorrectTimes() {
    Move badRecMove = new Move(motions, animatedRectangle, 0, 1000, 0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveRectangleNegativeTimes() {
    Move badRecMove = new Move(motions, animatedRectangle, -10, 100, 0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveRectangleNegativeEndTime() {
    Move badRecMove = new Move(motions, animatedRectangle, 10, -10, 0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveRectangleIncorrectLocations() {
    Move badRecMove = new Move(motions, animatedRectangle, 10, 50, 5,5);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveOvalIncorrectTimes() {
    Move badOvalMove = new Move(motions, animatedOval, 0, 1000, 0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveOvalNegativeTimes() {
    Move badOvalMove = new Move(motions, animatedOval, -10, 100, 0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveOvalNegativeEndTime() {
    Move badOvalMove = new Move(motions, animatedOval, 10, -10, 0,0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveOvalIncorrectLocations() {
    Move badOvalMove = new Move(motions, animatedOval, 10, 50, 0,0);
  }

}