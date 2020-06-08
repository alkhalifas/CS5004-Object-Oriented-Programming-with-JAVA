import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Represents a testing class for Bishop.
 */
public class BishopTest {

  ChessPiece bishopOne;
  ChessPiece bishopTwo;
  ChessPiece bishopThree;
  ChessPiece rookOne;
  ChessPiece rookTwo;

  @Before
  public void setup() {

    bishopOne = new Bishop(0, 3, Color.WHITE);
    bishopTwo = new Bishop(7, 7, Color.WHITE);
    bishopThree = new Bishop(1, 4, Color.BLACK);
    rookOne = new Rook(1, 4, Color.BLACK);
    rookTwo = new Rook(2, 4, Color.BLACK);

  }

  /**
   * Test whether the bishop can move to its same position.
   */
  @Test
  public void testBishopMoveToSamePosition() {
    assertEquals(false, bishopOne.canMove(0, 3));
  }

  /**
   * Test whether the bishop can move to a valid new position up and left.
   */
  @Test
  public void testBishopMoveToValidNewPositionUpLeft() {
    assertEquals(true, bishopOne.canMove(2, 1));
  }

  /**
   * Test whether the bishop can move to a valid new position up and right.
   */
  @Test
  public void testBishopMoveToValidNewPositionUpRight() {
    assertEquals(true, bishopOne.canMove(2, 5));
  }

  /**
   * Test whether the bishop can move to a invalid Bishop position.
   */
  @Test
  public void testBishopMoveToInvalidNewPosition() {
    assertEquals(false, bishopOne.canMove(3, 4));
  }

  /**
   * Test whether the bishop can move to a valid new position.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBishopMoveToInvalidOffBoardPosition() {
    assertEquals(false, bishopOne.canMove(-3, -4));
  }

  /**
   * Test whether the bishop can kill itself.
   */
  @Test
  public void testBishopKillItself() {
    assertEquals(false, bishopOne.canKill(bishopOne));
  }

  /**
   * Test whether the bishop can kill same color.
   */
  @Test
  public void testBishopKillSameColor() {
    assertEquals(false, bishopOne.canKill(bishopTwo));
  }

  /**
   * Test whether the bishop can kill different color.
   */
  @Test
  public void testBishopKillDifferentColor() {
    assertEquals(true, bishopOne.canKill(bishopThree));
  }

  /**
   * Test whether the bishop can kill same color, different piece.
   */
  @Test
  public void testBishopKillDifferentColorDifferentPiece() {
    assertEquals(true, bishopOne.canKill(rookOne));
  }

  /**
   * Test whether the bishop can kill same color, different piece, invalid position.
   */
  @Test
  public void testBishopKillDifferentColorDifferentPieceInvalidPosition() {
    assertEquals(false, bishopOne.canKill(rookTwo));
  }


}