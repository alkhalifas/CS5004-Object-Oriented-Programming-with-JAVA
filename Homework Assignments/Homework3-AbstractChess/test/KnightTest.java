import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Represents a testing class for Bishop.
 */
public class KnightTest {

  ChessPiece knightOne;
  ChessPiece knightTwo;
  ChessPiece knightThree;
  ChessPiece pawnOne;
  ChessPiece pawnTwo;

  @Before
  public void setup() {

    knightOne = new Bishop(0, 2, Color.WHITE);
    knightTwo = new Bishop(7, 7, Color.WHITE);
    knightThree = new Bishop(2, 5, Color.BLACK);
    pawnOne = new Rook(2, 4, Color.BLACK);
    pawnTwo = new Rook(7, 7, Color.BLACK);

  }

  /**
   * Test whether the knight can move to its same position.
   */
  @Test
  public void testKnightMoveToSamePosition() {
    assertEquals(false, knightOne.canMove(0, 2));
  }

  /**
   * Test whether the knight can move to a invalid new position up and left.
   */
  @Test
  public void testKnightMoveToInvalidNewPositionUpLeft() {
    assertEquals(false, knightOne.canMove(1, 7));
  }

  /**
   * Test whether the knight can move to a invalid new position up and right.
   */
  @Test
  public void testKnightMoveToInvalidNewPositionUpRight() {
    assertEquals(false, knightOne.canMove(7, 1));
  }

  /**
   * Test whether the knight can move to a valid new position up and right.
   */
  @Test
  public void testKnightMoveToValidPosition() {
    assertEquals(false, knightOne.canMove(3, 4));
  }

  /**
   * Test whether the knight can kill itself.
   */
  @Test
  public void testBishopKillItself() {
    assertEquals(false, knightOne.canKill(knightOne));
  }

  /**
   * Test whether the knight can kill same color.
   */
  @Test
  public void testKnightKillSameColor() {
    assertEquals(false, knightOne.canKill(knightTwo));
  }

  /**
   * Test whether the knight can kill different color.
   */
  @Test
  public void testBishopKillDifferentColor() {
    assertEquals(false, knightOne.canKill(knightThree));
  }

  /**
   * Test whether the knight can kill same color, different piece.
   */
  @Test
  public void testBishopKillDifferentColorDifferentPiece() {
    assertEquals(true, knightOne.canKill(pawnOne));
  }

  /**
   * Test whether the knight can kill same color, different piece, invalid position.
   */
  @Test
  public void testBishopKillDifferentColorDifferentPieceInvalidPosition() {
    assertEquals(false, knightOne.canKill(pawnTwo));
  }

}