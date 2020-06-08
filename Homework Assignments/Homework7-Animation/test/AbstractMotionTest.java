
import org.junit.Before;
import org.junit.Test;
import java.util.TreeMap;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for the Abstract Motion Class to ensure proper functionality.
 */
public class AbstractMotionTest {

  private Motion motionScaleOne;
  private Motion motionScaleTwo;
  private Motion motionMoveOne;
  private Motion motionMoveTwo;
  private Motion motionColorOne;
  private Motion motionColorTwo;

  @Before
  public void setup() {
    //Setup Shape and Motions:
    Shape rectangleShape;
    AbstractAnimatedShape sampleAnimatedShape;
    TreeMap<Motion, String> motions = new TreeMap<>();

    // Setup actual Shapes:
    rectangleShape = new Rectangle(10, 10, 10, 10, 50, 50, 50);
    sampleAnimatedShape = new AnimatedRectangleShape(rectangleShape, 0, 50000);

    // Setup the Motions:
    motionScaleOne = new Scale(motions, sampleAnimatedShape, 1, 10, 5, 5 );
    motionScaleTwo = new Scale(motions, sampleAnimatedShape, 1, 1000, 5000, 5000 );

    motionMoveOne = new Move(motions, sampleAnimatedShape, 20, 50, 5, 5 );
    motionMoveTwo = new Move(motions, sampleAnimatedShape, 1, 5000, 5000, 5000 );

    motionColorOne = new ChangeColor(motions, sampleAnimatedShape,0, 10, 25, 25, 25);
    motionColorTwo = new ChangeColor(motions, sampleAnimatedShape,0, 100, 255, 255, 255);
  }

  @Test
  public void testGetStartTimesCorrect() {
    assertEquals(1 , motionScaleOne.getStartTime());
    assertEquals(20, motionMoveOne.getStartTime());
    assertEquals(0, motionColorOne.getStartTime());
  }

  @Test
  public void testGetEndTimesCorrect() {
    assertEquals(10 , motionScaleOne.getEndTime());
    assertEquals(50, motionMoveOne.getEndTime());
    assertEquals(10, motionColorOne.getEndTime());
  }

  @Test
  public void testGetEndTimesNotCorrect() {
    assertEquals(1000 , motionScaleTwo.getEndTime());
    assertEquals(5000, motionMoveTwo.getEndTime());
    assertEquals(100, motionColorTwo.getEndTime());
  }

}