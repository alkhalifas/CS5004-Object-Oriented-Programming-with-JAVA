
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing Class for the rectangle animation class to ensure proper functionality.
 */
public class AnimatedRectangleShapeTest {

  private AnimatedRectangleShape aniRectangleOne;
  private AnimatedRectangleShape aniRectangleTwo;
  private AnimatedRectangleShape aniRectangleThree;
  private AnimatedRectangleShape aniRectangleFour;
  private AnimatedRectangleShape aniRectangleFive;

  @Before
  public void setup() {
    Shape rectangleOne;
    Shape rectangleTwo;
    Shape rectangleThree;
    Shape rectangleFour;
    Shape rectangleFive;

    rectangleOne = new Rectangle( 0, 5, 5, 5, 0, 0, 5);
    rectangleTwo = new Rectangle(100, 100, 100, 100, 100, 100, 200);
    rectangleThree = new Rectangle(200, 200, 200, 200, 255, 0, 0);
    rectangleFour = new Rectangle(5000, 5000, 5000, 5000, 0, 255, 0);
    rectangleFive = new Rectangle(5000, 5000, 5000, 5000, 0, 255, 0);

    aniRectangleOne = new AnimatedRectangleShape(rectangleOne, 0, 20);
    aniRectangleTwo = new AnimatedRectangleShape(rectangleTwo, 0, 50);
    aniRectangleThree = new AnimatedRectangleShape(rectangleThree, 10, 11);
    aniRectangleFour = new AnimatedRectangleShape(rectangleFour, 10, 11);
    aniRectangleFive = new AnimatedRectangleShape(rectangleFive, 50, 50);
  }

  @Test
  public void testToStringRectangleOne() {
    assertEquals("Type: Rectangle\n"
        + "Corner: (0, 5), Width: 5, Height: 5, Color: (0, 0, 5)\n"
        + "Appears at t=0\n"
        + "Disappears at t=20\n", aniRectangleOne.toString());
  }

  @Test
  public void testToStringRectangleTwo() {
    assertEquals("Type: Rectangle\n"
        + "Corner: (100, 100), Width: 100, Height: 100, Color: (100, 100, 200)\n"
        + "Appears at t=0\n"
        + "Disappears at t=50\n", aniRectangleTwo.toString());
  }

  @Test
  public void testToStringRectangleThree() {
    assertEquals("Type: Rectangle\n"
        + "Corner: (200, 200), Width: 200, Height: 200, Color: (255, 0, 0)\n"
        + "Appears at t=10\n"
        + "Disappears at t=11\n", aniRectangleThree.toString());
  }

  @Test
  public void testToStringRectangleFour() {
    assertEquals("Type: Rectangle\n"
            + "Corner: (5000, 5000), Width: 5000, Height: 5000, Color: (0, 255, 0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=11\n", aniRectangleFour.toString());
  }

  @Test
  public void testToStringRectangleFive() {
    assertEquals("Type: Rectangle\n"
            + "Corner: (5000, 5000), Width: 5000, Height: 5000, Color: (0, 255, 0)\n"
            + "Appears at t=50\n"
            + "Disappears at t=50\n", aniRectangleFive.toString());
  }
}