import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is the testing class for the PolynomialImpl class.
 */
public class PolynomialImplTest {
  private PolynomialImpl polyOne;
  private PolynomialImpl polyTwo;
  private PolynomialImpl polyThree;
  private PolynomialImpl polyFour;
  private PolynomialImpl polyFive;
  private PolynomialImpl polyAdder;
  private Node someNode;

  @Before
  public void setUp() {
    polyOne = new PolynomialImpl();
    polyTwo = new PolynomialImpl("3x^6");
    polyThree = new PolynomialImpl("");
    polyFour = new PolynomialImpl("7x^7 +3x^6 +2x^5 +1x^4 -5x^3 -3x^2 +2x^1");
    polyFive = new PolynomialImpl("+2x^1 +3x^6 +1x^4 -5x^3 +2x^5 -3x^2 7x^7");

    polyAdder = new PolynomialImpl();
    polyAdder.addTerm(1, 4);
    polyAdder.addTerm(2, 5);
    polyAdder.addTerm(3, 6);
  }

  // Test the creation of a polynomial with no arguments.
  @Test
  public void testConstructionWithoutTerms() {
    assertEquals("0", polyOne.toString());
  }

  // Test the creation of a polynomial with single string argument.
  @Test
  public void testConstructionWithTerms() {
    assertEquals("3x^6", polyTwo.toString());
  }

  // Test the creation of a polynomial with an empty string.
  @Test
  public void testConstructionWithBadTerms() {
    assertEquals("0", polyThree.toString());
  }

  // Test the creation of a polynomial with coefficient of zero.
  @Test
  public void testConstructionCoefficientZero() {
    assertEquals("0", polyOne.addTerm(0,6).toString());
  }

  // Test the creation of a full ordered polynomial.
  @Test
  public void testConstructionWithLongPoly() {
    assertEquals("7x^7 +3x^6 +2x^5 +1x^4 -5x^3 -3x^2 +2x^1", polyFour.toString());
  }

  // Test the creation of a full unordered polynomial.
  @Test
  public void testConstructionWithUnorderedLongPoly() {
    assertEquals("2x^1 +3x^6 +1x^4 -5x^3 +2x^5 -3x^2 +7x^7", polyFive.toString());
  }

  // Test the creation of a poly with a negative power
  @Test(expected = IllegalArgumentException.class)
  public void testConstructionWithNegativePower() {
    assertEquals(false, polyOne.addTerm(1, -1));
  }

  // Test the creation of the add method for good input
  @Test
  public void testAddMethodGoodData() {
    assertEquals("2x^1",
            polyOne.addTerm(1, 1).addTerm(1, 1).toString());
  }

  // Test the creation of the addTerm method for negative power
  @Test(expected = IllegalArgumentException.class)
  public void testAddMethodNegativePower() {
    assertEquals("2x^1",
            polyOne.addTerm(1, 1).addTerm(1, -6));
  }

  // Test the creation of the addTerm method for negative coefficient
  @Test
  public void testAddMethodNegativeCoefficient() {
    assertEquals("-5x^1",
            polyOne.addTerm(1, 1).addTerm(-6, 1).toString());
  }

  // Test the add term using a positive coefficient and a positive power.
  @Test
  public void testAddTerms() {
    polyOne.addTerm(3, 5);
    assertEquals("3x^5", polyOne.toString());
  }

  // Test adding many terms using the addTerm method using positive and negative coefficients.
  @Test
  public void testAddTermsMore() {
    polyOne.addTerm(3, 6);
    polyOne.addTerm(-4, 1);
    polyOne.addTerm(-5, 5);
    assertEquals("3x^6 -5x^5 -4x^1", polyOne.toString());
  }

  // Test the creation of the removeTerm method for positive power
  @Test
  public void testRemoveTermGoodData() {
    assertEquals("3x^6 +2x^5 +1x^4 -5x^3 -3x^2 +2x^1", polyFour.removeTerm(7).toString());
  }

  // Test the creation of the removeTerm method for negative power. It should not change the output.
  @Test
  public void testRemoveTermBadData() {
    assertEquals("7x^7 +3x^6 +2x^5 +1x^4 -5x^3 -3x^2 +2x^1",
            polyFour.removeTerm(-7).toString());
  }

  // Test get degree without any input in it which should return a zero.
  @Test
  public void testGetDegreeWithoutTerms() {
    assertEquals(0, polyOne.getDegree(), 0.01);
  }

  // Test get degree with a single term entered.
  @Test
  public void testGetDegreeWithSomeTerms() {
    assertEquals(6.0, polyTwo.getDegree(), 0.01);
  }

  // Test get degree with a ful polynomial.
  @Test
  public void testGetDegreeWithManyTerms() {
    assertEquals(7.0, polyFour.getDegree(), 0.01);
  }

  // Testing the get coefficient with no terms added to the polynomial.
  @Test
  public void testGetCoefficientWithoutTerms() {
    assertEquals(0, polyOne.getCoefficient(4), 0.01);
  }

  // Testing the get coefficient method with only one term in the polynomial.
  @Test
  public void testGetCoefficientSingleTerms() {
    assertEquals(3.0, polyTwo.getCoefficient(6), 0.01);
  }

  // Testing the get coefficient method with a full polynomial added.
  @Test
  public void testGetCoefficientFullPolyOfTerms() {
    assertEquals(3.0, polyFour.getCoefficient(6), 0.01);
  }

  // Testing the get coefficient method with a full polynomial added using a negative coefficient.
  // This should not happen, but if it does it will return zero.
  @Test
  public void testGetCoefficientFullPolyOfTermsNegativeCoeff() {
    assertEquals(0.0, polyFour.getCoefficient(-3), 0.01);
  }

  // Testing the evaluate method using positive x value.
  @Test
  public void testEvaluatePositiveX() {
    assertEquals(1120.0, polyFour.evaluate(2), 0.01);
  }

  // Testing the evaluate method using negative x value.
  @Test
  public void testEvaluateNegativeX() {
    assertEquals(-728, polyFour.evaluate(-2), 0.01);
  }

  // Testing the evaluate method using positive x on empty string.
  @Test
  public void testEvaluatePositiveXEmptyString() {
    assertEquals(0, polyThree.evaluate(2), 0.01);
  }

  // Testing the evaluate method using positive x on empty string.
  @Test
  public void testEvaluateZeroX() {
    assertEquals(0, polyThree.evaluate(0), 0.01);
  }

  // Testing the add method on two empty polynomials
  @Test
  public void testAddTwoPolynomials() {
    assertEquals("14x^7 +6x^6 +4x^5 +2x^4 -10x^3 -6x^2 +4x^1",
            polyFour.add(polyFive).toString());
  }

  // Testing the general use of the add method for p
  @Test
  public void testAddPolynomialsMore() {
    polyOne.addTerm(2, 1);
    polyOne.addTerm(-5, 3);
    polyOne.addTerm(-3, 2);
    polyOne.addTerm(7, 7);
    assertEquals("7x^7 +3x^6 +2x^5 +1x^4 -5x^3 -3x^2 +2x^1",
            polyOne.add(polyAdder).toString());
  }

  //Show that the add method creates a new object and does not take on the older objects.
  //Showing that the new object has a different identity using the hashcode.
  @Test
  public void testAddPolynomialsEquivalence() {
    assertEquals(false, polyFive.hashCode() == polyOne.hashCode());
    assertEquals(false, polyOne.hashCode() == polyOne.add(polyFive).hashCode());
    assertEquals(false, polyFive.hashCode() == polyOne.add(polyFive).hashCode());

  }
}