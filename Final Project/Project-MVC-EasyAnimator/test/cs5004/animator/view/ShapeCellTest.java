package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tester for the shape cell class.
 */
public class ShapeCellTest {
  InterfaceShapeCell cell1;
  InterfaceShapeCell cell2;

  @Before
  public void setUp() {
    cell1 = new ShapeCell("R", "Rectangle");
    cell2 = new ShapeCell("E", "Ellipse");
  }

  @Test
  public void testGetId() {
    assertEquals(cell1.getID(), "R");
    assertEquals(cell2.getID(), "E");
  }

  @Test
  public void testGetType() {
    assertEquals(cell1.getType(), "Rectangle");
    assertEquals(cell2.getType(), "Ellipse");
  }
}

