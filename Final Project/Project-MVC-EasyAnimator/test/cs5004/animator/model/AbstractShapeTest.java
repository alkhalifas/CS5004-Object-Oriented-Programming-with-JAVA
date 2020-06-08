package cs5004.animator.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Testing class for the abstract shape class in which the functionality will be confirmed.
 */
public class AbstractShapeTest {

  private Rectangle rectangle;
  private Ellipse ellipse;


  private void setUp() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);
  }

  @Test(expected = NullPointerException.class)
  public void testNullPosition() {
    rectangle = new Rectangle(10, 10, null, 5, Color.BLUE);
    ellipse = new Ellipse(10, 10, null, 5, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    rectangle = new Rectangle(-10, 10, new Point2D.Double(10, 10), 5, Color.BLUE);
    ellipse = new Ellipse(-10, 10, new Point2D.Double(10, 10), 5, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    rectangle = new Rectangle(10, -10, new Point2D.Double(10, 10), 5, Color.BLUE);
    ellipse = new Ellipse(10, -10, new Point2D.Double(10, 10), 5, Color.RED);
  }

  @Test(expected = NullPointerException.class)
  public void testNullColor() {
    rectangle = new Rectangle(1, 3, new Point2D.Double(10, 10), 0, null);
    ellipse = new Ellipse(0, 0, new Point2D.Double(10, 10), 5, null);

  }

  @Test
  public void testGetShapeType() {
    setUp();
    assertEquals(ellipse.getShapeType(), "Ellipse");
    assertEquals(rectangle.getShapeType(), "Rectangle");
  }

  @Test
  public void testGetColor() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);
    assertEquals(rectangle.getColor(), Color.BLUE);
    assertEquals(ellipse.getColor(), Color.RED);
  }

  @Test
  public void testGetPosition() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);
    assertEquals(rectangle.getPosition(), new Point2D.Double(10, 10));
    assertEquals(ellipse.getPosition(), new Point2D.Double(20, 20));
  }

  @Test
  public void testGetTextPosition() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);
    assertEquals(rectangle.getTextPosition(), "(10.0, 10.0)");
    assertEquals(ellipse.getTextPosition(), "(20.0, 20.0)");
  }


  @Test
  public void testGetHeight() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);
    assertEquals(ellipse.getHeight(), 10);
    assertEquals(rectangle.getHeight(), 10);
  }

  @Test
  public void testGetWidth() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);
    assertEquals(ellipse.getWidth(), 10);
    assertEquals(rectangle.getWidth(), 10);
  }

  @Test
  public void testSetWidth() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);

    this.ellipse.setWidth(15);
    assertEquals(ellipse.getWidth(), 15);
    this.rectangle.setWidth(55);
    assertEquals(rectangle.getWidth(), 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadSetWidth() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);

    this.rectangle.setWidth(-5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadSetHeight() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);

    this.rectangle.setHeight(-10);
  }

  @Test
  public void testSetCorrectHeight() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);

    this.ellipse.setHeight(5);
    assertEquals(ellipse.getHeight(), 5);
    this.rectangle.setHeight(10);
    assertEquals(rectangle.getHeight(), 10);
  }

  @Test
  public void testSetPosition() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);
    assertEquals(ellipse.getPosition(), new Point2D.Double(20, 20));

    this.ellipse.setPosition(new Point2D.Double(20, 20));
    assertEquals(ellipse.getPosition(), new Point2D.Double(20, 20));
    assertEquals(rectangle.getPosition(), new Point2D.Double(10, 10));
    assertEquals(rectangle.getTextPosition(), "(10.0, 10.0)");

    this.rectangle.setPosition(new Point2D.Double(25, 15));
    assertEquals(rectangle.getPosition(), new Point2D.Double(25, 15));
  }

  @Test(expected = NullPointerException.class)
  public void testBadSetPosition() {
    this.ellipse.setPosition(null);
  }

  @Test
  public void testSetColor() {
    rectangle = new Rectangle(10, 10, new Point2D.Double(10, 10), 0, Color.BLUE);
    ellipse = new Ellipse(10, 10, new Point2D.Double(20, 20), 0, Color.RED);
    assertEquals(ellipse.getColor(), Color.RED);

    this.ellipse.setColor(Color.BLACK);
    assertEquals(ellipse.getColor(), Color.BLACK);
    assertEquals(rectangle.getColor(), Color.BLUE);
    assertEquals(rectangle.getTextColor(), "(R: 0, G: 0, B: 255)");

    this.rectangle.setColor(Color.ORANGE);
    assertEquals(rectangle.getColor(), Color.ORANGE);
  }

  @Test(expected = NullPointerException.class)
  public void testBadSetColor() {
    this.ellipse.setColor(null);
  }
}
