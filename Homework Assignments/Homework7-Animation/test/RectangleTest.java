
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing the rectangle class to ensure correct functionality.
 */
public class RectangleTest {

  private Rectangle rectangleOne;
  private Rectangle rectangleTwo;
  private Rectangle rectangleThree;
  private Rectangle rectangleMax;

  @Before
  public void setup() {
    rectangleOne = new Rectangle(5, 10, 1, 1, 1, 1, 1);
    rectangleTwo = new Rectangle(15, 20, 20, 20, 150, 150, 150);
    rectangleThree = new Rectangle(400, 400, 400, 400, 255, 255, 255);
    rectangleMax = new Rectangle(4000000, 4000000, 4000000, 4000000, 255, 255, 255);

  }

  @Test
  public void testToStringRectangleOne() {
    assertEquals("Type: Rectangle\n"
                    + "Corner: (5, 10), Width: 1, Height: 1, Color: (1, 1, 1)" + "\n",
            rectangleOne.toString());
  }

  @Test
  public void testToStringRectangleTwo() {
    assertEquals("Type: Rectangle\n"
                    + "Corner: (15, 20), Width: 20, Height: 20, Color: (150, 150, 150)" + "\n",
            rectangleTwo.toString());
  }

  @Test
  public void testToStringRectangleThree() {
    assertEquals("Type: Rectangle\n"
                    + "Corner: (400, 400), Width: 400, Height: 400, Color: (255, 255, 255)" + "\n",
            rectangleThree.toString());
  }

  @Test
  public void testToStringRectangleMax() {
    assertEquals("Type: Rectangle\n"
                    + "Corner: (4000000, 4000000), Width: 4000000, Height: 4000000, "
                    + "Color: (255, 255, 255)" + "\n",
            rectangleMax.toString());
  }
}