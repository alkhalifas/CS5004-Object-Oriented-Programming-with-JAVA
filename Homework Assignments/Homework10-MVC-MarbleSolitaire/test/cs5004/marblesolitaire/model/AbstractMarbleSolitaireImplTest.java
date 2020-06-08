package cs5004.marblesolitaire.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Testing class for the Abstract MarbleSolitaireImpl to ensure correct functionality.
 */
public class AbstractMarbleSolitaireImplTest {
  private MarbleSolitaireModelImpl genericGame;

  /**
   * Testing the construction of a MSMI object using arm 3, row 3, and col 3.
   */
  @Test
  public void testConstructorWithParams() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", genericGame.getGameState());
  }

  /**
   * Testing the a valid move method on a board of arm 3, row 3 and col 3.
   */
  @Test
  public void testMoveValid() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 3, 3);
    assertFalse(genericGame.isGameOver());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", genericGame.getGameState());
  }

  /**
   * Testing the an invalid method that is outside the scope of the board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidOutsideBoard() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 20, 20);
    assertFalse(genericGame.isGameOver());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", genericGame.getGameState());
  }

  /**
   * Testing the an invalid method that has negative coordinates.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidNegativeCoordinates() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, -3, -3);
    assertFalse(genericGame.isGameOver());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", genericGame.getGameState());
  }

  /**
   * Testing the an invalid method that has negative coordinates.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidNegativeZeroValues() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 0, 0);
    assertFalse(genericGame.isGameOver());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", genericGame.getGameState());
  }


  /**
   * Testing the a series of valid move methods on a board of arm 3, row 3 and col 3.
   */
  @Test
  public void testMultiMoveValid() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 3, 3);
    genericGame.move(1, 2, 3, 2);
    genericGame.move(2, 4, 2, 2);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O O O\n"
            + "    _ O O\n"
            + "O O O _ _ O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", genericGame.getGameState());
  }

  /**
   * Testing invalid location move on a board of arm 3, row 3 and col 3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLocationMove() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(0, 0, 2, 2);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O O O\n"
            + "    _ O O\n"
            + "O O O _ _ O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", genericGame.getGameState());
  }

  /**
   * Testing invalid location move on a board of arm 3, row 3 and col 3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLengthMove() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(0, 0, 5, 5);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O O O\n"
            + "    _ O O\n"
            + "O O O _ _ O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", genericGame.getGameState());
  }

  /**
   * Testing invalid location move on a board of arm 3, row 3 and col 3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveDestination() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(0, 0, 5, 5);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O O O\n"
            + "    _ O O\n"
            + "O O O _ _ O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", genericGame.getGameState());
  }

  /**
   * Testing the a series of valid move methods on a board of arm 3, row 3 and col 3 to the end.
   * THis will also test the isGameOver for a game that is actually over.
   */
  @Test
  public void testMultiMoveToEnd() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 3, 3);
    genericGame.move(1, 2, 3, 2);
    genericGame.move(2, 4, 2, 2);
    genericGame.move(3, 2, 1, 2);
    genericGame.move(2, 6, 2, 4);
    genericGame.move(4, 6, 2, 6);
    genericGame.move(4, 5, 2, 5);
    genericGame.move(3, 3, 3, 5);
    genericGame.move(2, 5, 4, 5);
    genericGame.move(1, 4, 3, 4);
    genericGame.move(0, 2, 2, 2);
    genericGame.move(0, 3, 2, 3);
    genericGame.move(2, 2, 2, 4);
    genericGame.move(3, 4, 1, 4);
    genericGame.move(0, 4, 2, 4);
    genericGame.move(5, 2, 3, 2);
    genericGame.move(5, 3, 3, 3);
    genericGame.move(5, 4, 3, 4);
    genericGame.move(2, 0, 2, 2);
    genericGame.move(4, 0, 2, 0);
    genericGame.move(3, 2, 1, 2);
    genericGame.move(2, 4, 4, 4);
    genericGame.move(4, 5, 4, 3);
    genericGame.move(4, 3, 2, 3);

    assertTrue(genericGame.isGameOver());
    assertEquals("    _ _ _\n"
            + "    O _ _\n"
            + "O _ _ O _ _ O\n"
            + "_ _ _ _ _ _ _\n"
            + "_ O _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    O O O", genericGame.getGameState());
  }


  /**
   * Testing the getGameState method for a valid move. This will also test the isGameOver for a game
   * that is not over.
   */
  @Test
  public void testGetGameState() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 3, 3);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", genericGame.getGameState());
  }

  /**
   * Testing the a series of valid move methods on a board of arm 3, row 3 and col 3 to the end.
   * THis will also test the isGameOver for a game that is actually over and test the getScore
   * method.
   */
  @Test
  public void testGetScoreFullGame() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 3, 3);
    genericGame.move(1, 2, 3, 2);
    genericGame.move(2, 4, 2, 2);
    genericGame.move(3, 2, 1, 2);
    genericGame.move(2, 6, 2, 4);
    genericGame.move(4, 6, 2, 6);
    genericGame.move(4, 5, 2, 5);
    genericGame.move(3, 3, 3, 5);
    genericGame.move(2, 5, 4, 5);
    genericGame.move(1, 4, 3, 4);
    genericGame.move(0, 2, 2, 2);
    genericGame.move(0, 3, 2, 3);
    genericGame.move(2, 2, 2, 4);
    genericGame.move(3, 4, 1, 4);
    genericGame.move(0, 4, 2, 4);
    genericGame.move(5, 2, 3, 2);
    genericGame.move(5, 3, 3, 3);
    genericGame.move(5, 4, 3, 4);
    genericGame.move(2, 0, 2, 2);
    genericGame.move(4, 0, 2, 0);
    genericGame.move(3, 2, 1, 2);
    genericGame.move(2, 4, 4, 4);
    genericGame.move(4, 5, 4, 3);
    genericGame.move(4, 3, 2, 3);

    assertTrue(genericGame.isGameOver());
    assertEquals(8, genericGame.getScore());
  }

  /**
   * Testing the getScore for an incomplete game.
   */
  @Test
  public void testGetScoreIncompleteGame() {
    genericGame = new MarbleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 3, 3);
    assertFalse(genericGame.isGameOver());
    assertEquals(31, genericGame.getScore());
  }

}