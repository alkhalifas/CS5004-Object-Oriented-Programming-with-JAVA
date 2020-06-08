import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Represents a testing class for rook.
 */
public class RookTest {

  ChessPiece rookOne;
  ChessPiece rookTwo;
  ChessPiece rookThree;
  ChessPiece queenOne;
  ChessPiece queenTwo;


  @Before
  public void setup() {

    rookOne = new Rook(4, 4, Color.WHITE);
    rookTwo = new Rook(6, 4, Color.WHITE);
    rookThree = new Rook(2, 4, Color.BLACK);
    queenOne = new Queen(4, 1, Color.WHITE);
    queenTwo = new Queen(4, 7, Color.BLACK);

  }

  /**
   * Test whether the rook can move up.
   */
  @Test
  public void testBishopMoveUp() {
    assertEquals(true, rookOne.canMove(5, 4));
  }

  /**
   * Test whether the rook can move down.
   */
  @Test
  public void testBishopMoveDown() {
    assertEquals(true, rookOne.canMove(2, 4));
  }

  /**
   * Test whether the rook can move right.
   */
  @Test
  public void testBishopMoveRight() {
    assertEquals(true, rookOne.canMove(4, 7));
  }

  /**
   * Test whether the rook can move left.
   */
  @Test
  public void testBishopMoveLeft() {
    assertEquals(true, rookOne.canMove(4, 1));
  }

  /**
   * Test whether the rook can move to invalid position for rook.
   */
  @Test
  public void testBishopMoveInvalidPosition() {
    assertEquals(false, rookOne.canMove(5, 2));
  }

  /**
   * Test whether the rook can move to invalid location off the board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBishopMoveInvalidBoardLocation() {
    assertEquals(false, rookOne.canMove(-5, -2));
  }

  /**
   * Test whether the rook can kill itself.
   */
  @Test
  public void testRookKillItself() {
    assertEquals(false, rookOne.canKill(rookOne));
  }

  /**
   * Test whether the rook can kill same color.
   */
  @Test
  public void testRookKillSameColor() {
    assertEquals(false, rookOne.canKill(rookTwo));
  }

  /**
   * Test whether the rook can kill different color.
   */
  @Test
  public void testRookKillDiffColor() {
    assertEquals(true, rookOne.canKill(rookThree));
  }

  /**
   * Test whether the rook can kill same color, different piece.
   */
  @Test
  public void testRookKillSameColorDifferentPiece() {
    assertEquals(false, rookOne.canKill(queenOne));
  }

  /**
   * Test whether the rook can kill same color, different piece.
   */
  @Test
  public void testRookKillDifferentColorDifferentPiece() {
    assertEquals(true, rookOne.canKill(queenTwo));
  }



}
