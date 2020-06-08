import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing the AbstractShape Class for functionality.
 */
public class AbstractShapeTest {
  private AbstractShape rectangleGeneric;
  private AbstractShape ovalGeneric;

  @Before
  public void setup() {
    rectangleGeneric = new Rectangle(0, 15, 15, 20, 1, 2, 3);
    ovalGeneric = new Oval(5, 5, 10, 20, 1, 1, 255);
  }

  @Test
  public void testGetXCorrect() {
    assertEquals(0, rectangleGeneric.getX());
    assertEquals(5, ovalGeneric.getX());
  }

  @Test
  public void testGetYCorrect() {
    assertEquals(15, rectangleGeneric.getY());
    assertEquals(5, ovalGeneric.getY());
  }

  @Test
  public void testGetWidthCorrect() {
    assertEquals(15, rectangleGeneric.getWidth());
    assertEquals(10, ovalGeneric.getWidth());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateNegativeWidth() {
    AbstractShape newShape = new Rectangle(5, 5, -15, 5, 10, 10, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateZeroWidth() {
    AbstractShape newShape = new Rectangle(5, 5, 0, 5, 10, 10, 10);
  }


  @Test
  public void testGetHeightCorrect() {
    assertEquals(20, rectangleGeneric.getHeight());
    assertEquals(20, ovalGeneric.getHeight());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateNegativeHeight() {
    AbstractShape newShape = new Rectangle(5, 5, 5, -5, 10, 10, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateZeroHeight() {
    AbstractShape newShape = new Rectangle(5, 5, 5, 0, 10, 10, 10);
  }

  @Test
  public void testGetRedCorrect() {
    assertEquals(1, rectangleGeneric.getRed());
    assertEquals(1, ovalGeneric.getRed());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateNegativeRed() {
    AbstractShape newShape = new Rectangle(5, 5, 5, 5, -5, 10, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateHighRed() {
    AbstractShape newShape = new Rectangle(5, 5, 5, 5, 260, 10, 10);
  }

  @Test
  public void testGetGreenCorrect() {
    assertEquals(2, rectangleGeneric.getGreen());
    assertEquals(1, ovalGeneric.getGreen());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateNegativeGreen() {
    AbstractShape newShape = new Rectangle(5, 5, 5, 5, 5, -10, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateHighGreen() {
    AbstractShape newShape = new Rectangle(5, 5, 5, 5, 1, 256, 10);
  }

  @Test
  public void testGetBlueCorrectMaxBlue() {
    assertEquals(3, rectangleGeneric.getBlue());
    assertEquals(255, ovalGeneric.getBlue());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateHighBlue() {
    AbstractShape newShape = new Rectangle(5, 5, 5, 5, 1, 10, 256);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInstantiateNegativeBlue() {
    AbstractShape newShape = new Rectangle(5, 5, 5, 5, 15, 10, -10);
  }

}