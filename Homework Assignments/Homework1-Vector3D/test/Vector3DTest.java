import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Person class.
 */
public class Vector3DTest {

  private Vector3D testCoordinateOne;
  private Vector3D testCoordinateTwo;


  @Before
  public void setUp() {

    testCoordinateOne = new Vector3D(0.7541984199187675, 0.3844070381499734, 0.005813437543794499);
    testCoordinateTwo = new Vector3D(0.7578421700265368, 0.37717440621103354, 0.005704057512001544);

  }

  @Test
  public void testGetX() {
    assertEquals(0.7541984199187675, testCoordinateOne.getX(), 0.00);
    assertEquals(0.7578421700265368, testCoordinateTwo.getX(), 0.00);

  }

  @Test
  public void testGetY() {
    assertEquals(0.3844070381499734, testCoordinateOne.getY(), 0.00);
    assertEquals(0.37717440621103354, testCoordinateTwo.getY(), 0.00);

  }

  @Test
  public void testGetZ() {
    assertEquals(0.005813437543794499, testCoordinateOne.getZ(), 0.00);
    assertEquals(0.005704057512001544, testCoordinateTwo.getZ(), 0.00);

  }

  @Test
  public void testToString() {
    assertEquals("(0.75,0.38,0.01)", testCoordinateOne.toString());
    assertEquals("(0.76,0.38,0.01)", testCoordinateTwo.toString());

  }

  @Test
  public void testGetMagnitude() {
    assertEquals(0.846, testCoordinateOne.getMagnitude(), 0.001);
    assertEquals(0.846, testCoordinateTwo.getMagnitude(), 0.001);

  }

  @Test // ################ ASK HOW TO TEST THIS OUT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  public void testNormalize() {
    assertEquals("(0.89,0.45,0.01)", testCoordinateOne.normalize().toString());
    assertEquals("(0.90,0.45,0.01)", testCoordinateTwo.normalize().toString());

  }

  @Test
  public void testAngleBetween() {
    assertEquals(0.548, testCoordinateTwo.angleBetween(testCoordinateOne), 0.001);
  }

  @Test
  public void testAdd() {
    assertEquals("(1.51,0.76,0.01)", testCoordinateTwo.add(testCoordinateOne).toString());
  }

  @Test
  public void testMultiply() {
    assertEquals("(3.79,1.89,0.03)", testCoordinateTwo.multiply(5).toString());
  }

  @Test
  public void testDotProduct() {
    assertEquals(0.716, testCoordinateTwo.dotProduct(testCoordinateOne), 0.001);
  }


}

