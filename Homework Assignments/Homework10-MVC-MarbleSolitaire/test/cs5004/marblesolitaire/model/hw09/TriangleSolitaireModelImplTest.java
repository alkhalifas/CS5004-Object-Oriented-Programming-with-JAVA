package cs5004.marblesolitaire.model.hw09;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * This class represents the testing class for the triangle version of the game.
 */
public class TriangleSolitaireModelImplTest {

  private TriangleSolitaireModelImpl genericGame;

  @Test
  public void testConstructorsAll() {
    genericGame = new TriangleSolitaireModelImpl();
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O", genericGame.getGameState());
    genericGame = new TriangleSolitaireModelImpl(5);
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O", genericGame.getGameState());
    genericGame = new TriangleSolitaireModelImpl(2, 2);
    assertEquals(
            "    O\n" +
                    "   O O\n" +
                    "  O O _\n" +
                    " O O O O\n" +
                    "O O O O O", genericGame.getGameState());
    genericGame = new TriangleSolitaireModelImpl(5, 2, 2);
    assertEquals(
            "    O\n" +
                    "   O O\n" +
                    "  O O _\n" +
                    " O O O O\n" +
                    "O O O O O", genericGame.getGameState());

  }

  @Test
  public void testConstructorOneParam() {
    genericGame = new TriangleSolitaireModelImpl(5);
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O", genericGame.getGameState());
  }

  @Test
  public void testConstructorTwoParams() {
    genericGame = new TriangleSolitaireModelImpl(2, 2);
    assertEquals(
            "    O\n" +
                    "   O O\n" +
                    "  O O _\n" +
                    " O O O O\n" +
                    "O O O O O", genericGame.getGameState());
  }

  @Test
  public void testConstructorWithThreeParams() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    assertEquals(
            "    O\n" +
                    "   O O\n" +
                    "  O _ O\n" +
                    " O O O O\n" +
                    "O O O O O", genericGame.getGameState());
  }

  @Test
  public void testConstructorWithBiggerParams() {
    genericGame = new TriangleSolitaireModelImpl(9, 5, 3);
    assertEquals(
            "        O\n" +
                    "       O O\n" +
                    "      O O O\n" +
                    "     O O O O\n" +
                    "    O O O O O\n" +
                    "   O O O _ O O\n" +
                    "  O O O O O O O\n" +
                    " O O O O O O O O\n" +
                    "O O O O O O O O O", genericGame.getGameState());
  }

  /**
   * Testing the a valid move method on a board of arm 3, row 3 and col 3.
   */
  @Test
  public void testMoveValid() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    genericGame.move(4, 3, 2, 1);
    assertFalse(genericGame.isGameOver());

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O O O _ O", genericGame.getGameState());
  }

  /**
   * Testing an invalid method that is outside the scope of the board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidOutsideBoard() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    genericGame.move(4, 3, 20, 10);
    assertFalse(genericGame.isGameOver());

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O O O _ O", genericGame.getGameState());
  }

  /**
   * Testing the an invalid method that has negative coordinates.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidNegativeCoordinates() {
    genericGame = new TriangleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, -3, -3);
    assertFalse(genericGame.isGameOver());

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O O O _ O", genericGame.getGameState());
  }

  /**
   * Testing the an invalid method that has negative coordinates.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidNegativeZeroValues() {
    genericGame = new TriangleSolitaireModelImpl(3, 3, 3);
    genericGame.move(3, 1, 0, 0);
    assertFalse(genericGame.isGameOver());

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O O O _ O", genericGame.getGameState());
  }

  /**
   * Testing the a series of valid move methods on a board of dim 5, row 2 and col 1.
   */
  @Test
  public void testMultiMoveValid() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    genericGame.move(4, 3, 2, 1);
    genericGame.move(4, 1, 4, 3);
    genericGame.move(4, 4, 4, 2);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O _ O _ _", genericGame.getGameState());
  }

  /**
   * Testing invalid location move on a board of dim 5, row 2 and col 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLocationMove() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    genericGame.move(0, 0, 2, 2);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O _ O _ _", genericGame.getGameState());
  }

  /**
   * Testing invalid location move on a board of dim 5, row 2 and col 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLengthMove() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    genericGame.move(0, 0, 3, 3);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O _ O _ _", genericGame.getGameState());
  }

  /**
   * Testing invalid location move on a board of dim 5, row 2 and col 1 to its own location.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveSamePosition() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    genericGame.move(4, 3, 4, 3);
    assertFalse(genericGame.isGameOver());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O _ O _ _", genericGame.getGameState());
  }

  /**
   * Testing the a series of valid move methods on a board of dim 5, row 2 and col 3 to the end.
   * THis will also test the isGameOver for a game that is actually over.
   */
  @Test
  public void testMultiMoveValidToEnd() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    genericGame.move(4, 3, 2, 1);
    genericGame.move(4, 1, 4, 3);
    genericGame.move(4, 4, 4, 2);
    genericGame.move(3, 0, 3, 2);
    genericGame.move(3, 3, 3, 1);
    genericGame.move(1, 0, 3, 2);
    genericGame.move(3, 2, 3, 0);
    genericGame.move(1, 1, 3, 3);
    genericGame.move(3, 0, 1, 0);
    genericGame.move(0, 0, 2, 0);

    assertTrue(genericGame.isGameOver());
    assertEquals(4, genericGame.getScore());
    assertEquals("    _\n" +
            "   _ _\n" +
            "  O _ _\n" +
            " _ _ _ O\n" +
            "O _ O _ _", genericGame.getGameState());
  }

  /**
   * Testing the getScore for an incomplete game.
   */
  @Test
  public void testGetScoreIncompleteGame() {
    genericGame = new TriangleSolitaireModelImpl(5, 2, 1);
    genericGame.move(4, 3, 2, 1);
    assertFalse(genericGame.isGameOver());
    assertEquals(13, genericGame.getScore());
  }

  @Test
  public void failingJUnitTest1() {
    genericGame = new TriangleSolitaireModelImpl(2, 0, 1);
    assertEquals(
            " O\n" +
                    "O O", genericGame.getGameState());
  }

}