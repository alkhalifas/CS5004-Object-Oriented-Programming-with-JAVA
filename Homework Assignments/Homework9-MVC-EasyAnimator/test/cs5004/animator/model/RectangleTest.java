package cs5004.animator.model;

import org.junit.Test;

import java.awt.geom.Point2D;


import java.awt.Color;


import static org.junit.Assert.assertEquals;

/**
 * Tests for all public methods of {@link Ellipse} and {@link Rectangle}.
 */
public class RectangleTest {
  private Rectangle rectangle;

  //Initializes the data for the tests.
  private void initializeData() {
    rectangle = new Rectangle(1, 3, new Point2D.Double(10, 10), 0, Color.GREEN);
  }

  /**
   * Tests that the constructor for shapes throw a null pointer if it is passed null for the
   * incoming coordinatePosition.
   */
  @Test(expected = NullPointerException.class)
  public void testBadConstructor1() {
    initializeData();
    rectangle = new Rectangle(1, 3, null, 0, Color.GREEN);
  }

  /**
   * Tests that the constructor for shapes throw a null pointer if it is passed null for the
   * incoming color.
   */
  @Test(expected = NullPointerException.class)
  public void testBadConstructor2() {
    initializeData();
    rectangle = new Rectangle(1, 3, new Point2D.Double(10, 10), 0, null);
  }

  /**
   * Test for getting the shape type.
   */
  @Test
  public void testGetShapeType() {
    initializeData();
    assertEquals(rectangle.getShapeType(), "Rectangle");
  }

  /**
   * Test for getting the color.
   */
  @Test
  public void testGetColor() {
    initializeData();
    assertEquals(rectangle.getColor(), Color.GREEN);
  }

  /**
   * Test for getting the color of the shape.
   */
  @Test
  public void testGetPosition() {
    initializeData();
    assertEquals(rectangle.getPosition(), new Point2D.Double(10, 10));
  }

  /**
   * Test for getting the height of the shape.
   */
  @Test
  public void testGetHeight() {
    initializeData();
    assertEquals(rectangle.getHeight(), 3);
  }

  /**
   * Test for getting the width of the shape.
   */
  @Test
  public void testGetWidth() {
    initializeData();
    assertEquals(rectangle.getWidth(), 1);
  }

  /**
   * Test for setting the width of the shape.
   */
  @Test
  public void testSetWidth() {
    initializeData();
    this.rectangle.setWidth(10);
    assertEquals(rectangle.getWidth(), 10);
  }

  /**
   * Tests that the set width method throws an exception when it is given a negative value for
   * width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetWidth() {
    initializeData();
    this.rectangle.setWidth(-10);
  }

  /**
   * Tests that the set width method throws an exception when it is given a negative value for
   * height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadSetHeight() {
    initializeData();
    this.rectangle.setHeight(-10);
  }

  /**
   * Test for setting the height of the shape.
   */
  @Test
  public void testSetHeight() {
    initializeData();
    this.rectangle.setHeight(10);
    assertEquals(rectangle.getHeight(), 10);
  }

  /**
   * Test for setting the coordinatePosition of the shape.
   */
  @Test
  public void testSetPosition() {
    initializeData();
    assertEquals(rectangle.getPosition(), new Point2D.Double(10, 10));
    this.rectangle.setPosition(new Point2D.Double(25, 15));
    assertEquals(rectangle.getPosition(), new Point2D.Double(25, 15));
  }

  /**
   * Test for setting the color of the shape.
   */
  @Test
  public void testSetColor() {
    initializeData();
    assertEquals(rectangle.getColor(), Color.GREEN);
    this.rectangle.setColor(Color.CYAN);
    assertEquals(rectangle.getColor(), Color.CYAN);
  }

}