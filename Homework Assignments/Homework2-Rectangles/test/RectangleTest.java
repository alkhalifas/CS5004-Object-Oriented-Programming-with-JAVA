import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Rectangle class. Various rectangles are tested using extreme values.
 */
public class RectangleTest {

  private Rectangle rectangleA;
  private Rectangle rectangleB;
  private Rectangle rectangleC;
  private Rectangle rectangleD;
  private Rectangle rectangleE;
  private Rectangle rectangleF;
  private Rectangle rectangleG;


  @Before
  public void setUp() {
    rectangleA = new Rectangle(1000000000, 1.0, 2.0, 2.0);
    rectangleB = new Rectangle(6.0, 6.0, 2.0, 2.0);
    rectangleC = new Rectangle(-2, 2, 2, 2);
    rectangleD = new Rectangle(-1.0, -1.0, 2.0, 2.0);
    rectangleE = new Rectangle(-1.0, -1.0, 2.0, 2.0);
    rectangleF = new Rectangle(-3.0, -1.0, 2.0, 2.0);
    rectangleG = new Rectangle(-3, -3, 5, 5);

  }

  /**
   * Testing the functionality of the toString method when it comes to returned values.
   */
  @Test
  public void testToString() {
    assertEquals("x:1000000000, y:1, w:2, h:2", rectangleA.toString());
    assertEquals("x:-2, y:2, w:2, h:2", rectangleC.toString());
  }

  /**
   * Testing the overlap method using rectangles with extreme values (high, low, and negative).
   */
  @Test
  public void testOverlap() {
    assertEquals(false, rectangleA.overlap(rectangleB));
    assertEquals(false, rectangleA.overlap(rectangleC));
    assertEquals(false, rectangleB.overlap(rectangleC));
    assertEquals(false, rectangleA.overlap(rectangleD));
    assertEquals(true, rectangleD.overlap(rectangleE));
    assertEquals(false, rectangleE.overlap(rectangleF));
    assertEquals(false, rectangleE.overlap(rectangleF));
    assertEquals(true, rectangleF.overlap(rectangleG));

  }

  /**
   * Testing the overlap method again but for a very specific test case. The new rectangles are
   * formed within this test method.
   */
  @Test
  public void testOverlapAgain() {
    Rectangle rectangleOne = new Rectangle(0, 0, 20, 10);
    Rectangle rectangleTwo = new Rectangle(-55, -55, 5, 5);

    assertEquals(false, rectangleOne.overlap(rectangleTwo));

  }

  /**
   * Testing the intersect method with a specific use case based on the server's feedback, as well
   * as other ones.
   */
  @Test
  public void testIntersect() {
    Rectangle rectangleOne = new Rectangle(0, 0, 20, 10);
    Rectangle rectangleTwo = new Rectangle(-22, -22, 23, 23);

    assertEquals("x:0, y:0, w:1, h:1", rectangleOne.intersect(rectangleTwo).toString());
    assertEquals("x:-1, y:-1, w:2, h:2", rectangleD.intersect(rectangleE).toString());
    assertEquals("x:-3, y:-1, w:2, h:2", rectangleF.intersect(rectangleG).toString());

  }

  /**
   * Testing the intersect method with a specific use case based and a variety of other cases.
   */
  @Test
  public void testUnion() {
    Rectangle rectangleOne = new Rectangle(0, 0, 20, 10);
    Rectangle rectangleTwo = new Rectangle(-50, -48, 5, 5);

    assertEquals("x:-50, y:-48, w:70, h:58", rectangleOne.union(rectangleTwo).toString());
    assertEquals("x:6, y:1, w:999999996, h:7", rectangleA.union(rectangleB).toString());
    assertEquals("x:-2, y:2, w:10, h:6", rectangleB.union(rectangleC).toString());
    assertEquals("x:-3, y:-1, w:4, h:2", rectangleE.union(rectangleF).toString());

  }

}