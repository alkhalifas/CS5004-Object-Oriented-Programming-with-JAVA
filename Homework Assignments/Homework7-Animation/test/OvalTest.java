import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for the Oval object to ensure correct functionality.
 */
public class OvalTest {

  private Oval ovalOne;
  private Oval ovalTwo;
  private Oval ovalThree;

  @Before
  public void setup() {
    ovalOne = new Oval(0, 0, 10, 10, 50, 50, 50);
    ovalTwo = new Oval(5, 5, 5, 5, 100, 100, 200);
    ovalThree = new Oval(400, 400, 400, 400, 255, 255, 255);
  }

  @Test
  public void testToStringRectangleOneSmallValues() {
    assertEquals("Type: Oval" + "\n" + "Corner: (0, 0), "
            + "Width: 10, Height: 10, Color: (50, 50, 50)" + "\n", ovalOne.toString());
  }

  @Test
  public void testToStringRectangleTwoMidValues() {
    assertEquals("Type: Oval" + "\n" + "Corner: (5, 5), Width: 5, "
            + "Height: 5, Color: (100, 100, 200)\n", ovalTwo.toString());
  }

  @Test
  public void testToStringRectangleLargeValues() {
    assertEquals("Type: Oval" + "\n" + "Corner: (400, 400), Width: 400, "
            + "Height: 400, Color: (255, 255, 255)\n", ovalThree.toString());
  }
}