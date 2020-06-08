package cs5004.animator.model;

import org.junit.Test;

import java.awt.geom.Point2D;


import java.awt.Color;


import static org.junit.Assert.assertEquals;

/**
 * Tests for all public methods of {@link Ellipse} and {@link Rectangle}.
 */
public class EllipseTest {
  private Ellipse ellipse;

  //Initializes the data for the tests.
  private void initializeData() {
    ellipse = new Ellipse(2, 2, new Point2D.Double(1, 1), 0, Color.RED);
  }

  /**
   * Tests that the constructor for shapes throw a null pointer if it is passed null for the
   * incoming coordinatePosition.
   */
  @Test(expected = NullPointerException.class)
  public void testBadConstructor1() {
    initializeData();
    ellipse = new Ellipse(2, 2, null, 0, Color.RED);
  }


  /**
   * Test for getting the shape type.
   */
  @Test
  public void testGetShapeType() {
    initializeData();
    assertEquals(ellipse.getShapeType(), "Ellipse");
  }

  /**
   * Test for getting the color.
   */
  @Test
  public void testGetColor() {
    initializeData();
    assertEquals(ellipse.getColor(), Color.RED);
  }

  /**
   * Test for getting the color of the shape.
   */
  @Test
  public void testGetPosition() {
    initializeData();
    assertEquals(ellipse.getPosition(), new Point2D.Double(1, 1));
  }

  /**
   * Test for getting the height of the shape.
   */
  @Test
  public void testGetHeight() {
    initializeData();
    assertEquals(ellipse.getHeight(), 2);
  }

  /**
   * Test for getting the width of the shape.
   */
  @Test
  public void testGetWidth() {
    initializeData();
    assertEquals(ellipse.getWidth(), 2);
  }

  /**
   * Test for setting the width of the shape.
   */
  @Test
  public void testSetWidth() {
    initializeData();
    this.ellipse.setWidth(5);
    assertEquals(ellipse.getWidth(), 5);
  }


  /**
   * Test for setting the height of the shape.
   */
  @Test
  public void testSetHeight() {
    initializeData();
    this.ellipse.setHeight(5);
    assertEquals(ellipse.getHeight(), 5);
  }

  /**
   * Test for setting the coordinatePosition of the shape.
   */
  @Test
  public void testSetPosition() {
    initializeData();
    assertEquals(ellipse.getPosition(), new Point2D.Double(1, 1));
    this.ellipse.setPosition(new Point2D.Double(20, 20));
    assertEquals(ellipse.getPosition(), new Point2D.Double(20, 20));
  }

  /**
   * Test that the setPosition() method throws an Illegal Argument Exception when it is given null
   * as a coordinatePosition.
   */
  @Test(expected = NullPointerException.class)
  public void testBadSetPosition() {
    initializeData();
    this.ellipse.setPosition(null);
  }

  /**
   * Test for setting the color of the shape.
   */
  @Test
  public void testSetColor() {
    initializeData();
    assertEquals(ellipse.getColor(), Color.RED);
    this.ellipse.setColor(Color.BLACK);
    assertEquals(ellipse.getColor(), Color.BLACK);
  }

  /**
   * Test that the setColor() method throws an Illegal Argument Exception when it is given null as a
   * color.
   */
  @Test(expected = NullPointerException.class)
  public void testBadSetColor() {
    initializeData();
    this.ellipse.setColor(null);
  }
}
