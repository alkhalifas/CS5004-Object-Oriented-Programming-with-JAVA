
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing Class for the AnimatedOvalShape Class to ensure proper functionality.
 */
public class AnimatedOvalShapeTest {

  private AnimatedOvalShape ovalOne;
  private AnimatedOvalShape ovalTwo;
  private AnimatedOvalShape ovalThree;
  private AnimatedOvalShape ovalFour;


  @Before
  public void setup() {

    Shape ovalOneObject;
    Shape ovalTwoObject;
    Shape ovalThreeObject;
    Shape ovalFourObject;

    ovalOneObject = new Oval( 5, 5, 5, 5, 5, 5, 5);
    ovalTwoObject = new Oval(100, 100, 100, 100, 100, 100, 100);
    ovalThreeObject = new Oval(200, 200, 200, 200, 200, 200, 200);
    ovalFourObject = new Oval(5000, 5000, 5000, 5000, 255, 255, 255);

    ovalOne = new AnimatedOvalShape(ovalOneObject, 5, 5);
    ovalTwo = new AnimatedOvalShape(ovalTwoObject, 100, 1000);
    ovalThree = new AnimatedOvalShape(ovalThreeObject, 200, 2000);
    ovalFour = new AnimatedOvalShape(ovalFourObject, 500, 5000);

  }

  @Test
  public void testToStringovalOne() {
    assertEquals("Type: Circle\n"
        + "Center: (5, 5), Width: 5, Height: 5, Color: (5, 5, 5)\n"
        + "Appears at t=5\n"
        + "Disappears at t=5\n", ovalOne.toString());
  }

  @Test
  public void testToStringOvalTwo() {
    assertEquals("Type: Circle\n"
        + "Center: (100, 100), Width: 100, Height: 100, Color: (100, 100, 100)\n"
        + "Appears at t=100\n"
        + "Disappears at t=1000\n", ovalTwo.toString());
  }

  @Test
  public void testToStringovalThree() {
    assertEquals("Type: Circle\n"
        + "Center: (200, 200), Width: 200, Height: 200, Color: (200, 200, 200)\n"
        + "Appears at t=200\n"
        + "Disappears at t=2000\n", ovalThree.toString());
  }

  @Test
  public void testToStringOvalFour() {
    assertEquals("Type: Circle\n"
            + "Center: (5000, 5000), Width: 5000, Height: 5000, Color: (255, 255, 255)\n"
            + "Appears at t=500\n"
            + "Disappears at t=5000\n", ovalFour.toString());
  }
}