package cs5004.marblesolitaire.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing class of the marble solitaire implementation to ensure proper functionality.
 */
public class MarbleSolitaireModelImplTest {

  private MarbleSolitaireModelImpl constGeneric;

  @Before
  public void setUp() {
    constGeneric = new MarbleSolitaireModelImpl();
  }

  /**
   * Testing the construction of a MSMI object using no arguments. MSMI = Marble Solitaire Model
   * Implementation.
   */
  @Test
  public void testConstructorNoParams() {
    constGeneric = new MarbleSolitaireModelImpl();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using arm thickness of three.
   */
  @Test
  public void testConstructorArmThickThree() {
    constGeneric = new MarbleSolitaireModelImpl(3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using arm thickness of five.
   */
  @Test
  public void testConstructorArmThickFive() {
    constGeneric = new MarbleSolitaireModelImpl(5);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using arm thickness of five.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThickNegative() {
    constGeneric = new MarbleSolitaireModelImpl(-3);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using arm thickness of five.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThickZero() {
    constGeneric = new MarbleSolitaireModelImpl(0);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 2 and col 2. This will also test the
   * invalid check helper function.
   */
  @Test
  public void testConstructorGoodRow2AndCol2() {
    constGeneric = new MarbleSolitaireModelImpl(2, 2);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 3 and col 3. This will also test the
   * invalid check helper function.
   */
  @Test
  public void testConstructorGoodRow3AndCol3() {
    constGeneric = new MarbleSolitaireModelImpl(3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row -3 and col -3. This will also test the
   * invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeRow3AndCol3() {
    constGeneric = new MarbleSolitaireModelImpl(-3, -3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 0 and col 0 which is upper left. This will
   * also test the invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorULCorner() {
    constGeneric = new MarbleSolitaireModelImpl(0, 0);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 0 and col 6 which is upper right. This will
   * also test the invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorURCorner() {
    constGeneric = new MarbleSolitaireModelImpl(0, 6);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 6 and col 6 which is lower right. This will
   * also test the invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorLRCorner() {
    constGeneric = new MarbleSolitaireModelImpl(6, 6);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 6 and col 0 which is lower left. This will
   * also test the invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorLLCorner() {
    constGeneric = new MarbleSolitaireModelImpl(6, 0);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 3, col 3 and arm 3. This will also test the
   * invalid check helper function.
   */
  @Test
  public void testConstructorThreeArguments() {
    constGeneric = new MarbleSolitaireModelImpl(3, 3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 3, col 3 and arm 3. This will also test the
   * invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeArgumentsNegativeRow() {
    constGeneric = new MarbleSolitaireModelImpl(0, 4, 5);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row -3, col 3 and arm 3. This will also test
   * the invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeArgumentsNegativeArm() {
    constGeneric = new MarbleSolitaireModelImpl(-3, 3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 0, col 0 and arm 0. This will also test the
   * invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeArgumentsAllZero() {
    constGeneric = new MarbleSolitaireModelImpl(0, 0, 0);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 5, col 5 and arm 5. This will also test the
   * invalid check helper function.
   */
  @Test
  public void testConstructorThreeArgumentsAllGood() {
    constGeneric = new MarbleSolitaireModelImpl(5, 5, 5);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 5, col -5 and arm 5. This will also test
   * the invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeArgumentsNegativeCol() {
    constGeneric = new MarbleSolitaireModelImpl(3, 5, -5);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", constGeneric.getGameState());
  }

  /**
   * Testing the construction of a MSMI object using row 5, col -5 and arm 5. This will also test
   * the invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeArgumentsNegRow() {
    constGeneric = new MarbleSolitaireModelImpl(3, -5, 5);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", constGeneric.getGameState());
  }


  /**
   * Testing the construction of a MSMI object using row 5, col 0 and arm 0. This will also test the
   * invalid check helper function.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThreeArgumentsZeroRowCol() {
    constGeneric = new MarbleSolitaireModelImpl(3, 0, 0);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", constGeneric.getGameState());
  }

}