package cs5004.animator.model;

import org.junit.Test;

import java.awt.geom.Point2D;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

/**
 * Testing the creation of a rectangle and its associated methods.
 */
public class RectangleTest {
  private Rectangle rectangle;

  private void setUp() {
    rectangle = new Rectangle(1, 3, new Point2D.Double(10, 10), 0, Color.GREEN);
  }

  /**
   * Tests that the constructor for shapes throw a null pointer if it is passed null for the
   * incoming coordinatePosition.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorNullCoordinates() {
    setUp();
    rectangle = new Rectangle(1, 3, null, 0, Color.GREEN);
  }

  /**
   * Tests that the constructor for rectangle given a null color.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorNullColor() {
    setUp();
    rectangle = new Rectangle(1, 3, new Point2D.Double(10, 10), 0, null);
  }

  /**
   * Testing the getShapeTYpe method.
   */
  @Test
  public void testGetShapeType() {
    setUp();
    assertEquals(rectangle.getShapeType(), "Rectangle");
  }

  /**
   * Testing the getColor method.
   */
  @Test
  public void testGetColor() {
    setUp();
    assertEquals(rectangle.getColor(), Color.GREEN);
  }

  /**
   * Testing the getPosition method.
   */
  @Test
  public void testGetPosition() {
    setUp();
    assertEquals(rectangle.getPosition(), new Point2D.Double(10, 10));
  }

  /**
   * Testing the getHeight method.
   */
  @Test
  public void testGetHeight() {
    setUp();
    assertEquals(rectangle.getHeight(), 3);
  }

  /**
   * Testing the getWidth method.
   */
  @Test
  public void testGetWidth() {
    setUp();
    assertEquals(rectangle.getWidth(), 1);
  }

  /**
   * Testing the setWidth method.
   */
  @Test
  public void testSetWidth() {
    setUp();
    this.rectangle.setWidth(10);
    assertEquals(rectangle.getWidth(), 10);
  }

  /**
   * Tests set width method with a negative number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetWidth() {
    setUp();
    this.rectangle.setWidth(-10);
  }

  /**
   * Tests set height method with a negative number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetHeight() {
    setUp();
    this.rectangle.setHeight(-10);
  }

  /**
   * Tests set height method.
   */
  @Test
  public void testSetHeight() {
    setUp();
    this.rectangle.setHeight(10);
    assertEquals(rectangle.getHeight(), 10);
  }

  /**
   * Tests set coordinate position method with a negative number.
   */
  @Test
  public void testSetPosition() {
    setUp();
    assertEquals(rectangle.getPosition(), new Point2D.Double(10, 10));
    this.rectangle.setPosition(new Point2D.Double(5, 5));
    assertEquals(rectangle.getPosition(), new Point2D.Double(5, 5));
  }

  /**
   * Testing the setColor method.
   */
  @Test
  public void testSetColor() {
    setUp();
    assertEquals(rectangle.getColor(), Color.GREEN);
    this.rectangle.setColor(Color.CYAN);
    assertEquals(rectangle.getColor(), Color.CYAN);
  }

}