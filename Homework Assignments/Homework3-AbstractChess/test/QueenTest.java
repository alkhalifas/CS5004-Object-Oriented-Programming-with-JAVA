import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Represents a testing class for Queen.
 */
public class QueenTest {

  ChessPiece queenOne;
  ChessPiece queenTwo;
  ChessPiece queenThree;
  ChessPiece rookOne;
  ChessPiece rookTwo;

  @Before
  public void setup() {

    queenOne = new Queen(4, 3, Color.WHITE);
    queenTwo = new Queen(6, 5, Color.WHITE);
    queenThree = new Queen(6, 1, Color.BLACK);
    rookOne = new Rook(6, 5, Color.WHITE);
    rookTwo = new Rook(6, 1, Color.BLACK);

  }


  /**
   * Test whether the queen can move diagonally up right.
   */
  @Test
  public void testBishopMoveUpRight() {
    assertEquals(true, queenOne.canMove(6,5));
  }

  /**
   * Test whether the queen can move diagonally up left.
   */
  @Test
  public void testBishopMoveUpLeft() {
    assertEquals(true, queenOne.canMove(6,1));
  }

  /**
   * Test whether the queen can move diagonally down left.
   */
  @Test
  public void testBishopMoveDownLeft() {
    assertEquals(true, queenOne.canMove(2,1));
  }


  /**
   * Test whether the queen can move diagonally down right.
   */
  @Test
  public void testBishopMoveDownRight() {
    assertEquals(true, queenOne.canMove(2,5));
  }

  /**
   * Test whether the queen can move straight up.
   */
  @Test
  public void testBishopMoveUp() {
    assertEquals(true, queenOne.canMove(6,3));
  }

  /**
   * Test whether the queen can move straight down.
   */
  @Test
  public void testBishopMoveDown() {
    assertEquals(true, queenOne.canMove(2,3));
  }


  /**
   * Test whether the queen can move straight left.
   */
  @Test
  public void testBishopMoveLeft() {
    assertEquals(true, queenOne.canMove(1,3));
  }


  /**
   * Test whether the queen can move straight right.
   */
  @Test
  public void testBishopMoveRight() {
    assertEquals(true, queenOne.canMove(4,6));
  }

  /**
   * Test whether the queen can move to invalid position.
   */
  @Test
  public void testBishopMoveInvalidPos() {
    assertEquals(false, queenOne.canMove(3,0));
  }

  /**
   * Test whether the queen can kill itself.
   */
  @Test
  public void testQueenKillItself() {
    assertEquals(false, queenOne.canKill(queenOne));
  }

  /**
   * Test whether the queen can kill same color.
   */
  @Test
  public void testQueenKillSameColor() {
    assertEquals(false, queenOne.canKill(queenTwo));
  }


  /**
   * Test whether the queen can kill other color.
   */
  @Test
  public void testQueenKillDiffColor() {
    assertEquals(true, queenOne.canKill(queenThree));
  }

  /**
   * Test whether the queen can kill other piece same color.
   */
  @Test
  public void testQueenKillDiffPieceSameColor() {
    assertEquals(false, queenOne.canKill(rookOne));
  }

  /**
   * Test whether the queen can kill other piece other color.
   */
  @Test
  public void testQueenKillDiffPieceDiffColor() {
    assertEquals(true, queenOne.canKill(rookTwo));
  }
}