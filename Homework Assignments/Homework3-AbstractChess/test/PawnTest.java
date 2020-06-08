import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Represents a testing class for Bishop.
 */
public class PawnTest {

  ChessPiece pawnOne;
  ChessPiece pawnTwo;
  ChessPiece pawnThree;
  ChessPiece rookOne;
  ChessPiece rookTwo;


  @Before
  public void setup() {

    pawnOne = new Pawn(0, 5, Color.WHITE);
    pawnTwo = new Pawn(5, 5, Color.WHITE);
    pawnThree = new Pawn(1, 4, Color.BLACK);
    rookOne = new Rook(1, 6, Color.BLACK);
    rookTwo = new Rook(2, 4, Color.BLACK);

  }

  /**
   * Test whether the pawn can move to its same position.
   */
  @Test
  public void testPawnMoveToSamePosition() {
    assertEquals(false, pawnOne.canMove(0,5));
  }

  /**
   * Test whether the pawn can move forward.
   */
  @Test
  public void testPawnMoveToForwardPosition() {
    assertEquals(true, pawnOne.canMove(1,5));
  }

  /**
   * Test whether the pawn can move left.
   */
  @Test
  public void testPawnMoveLeft() {
    assertEquals(false, pawnOne.canMove(0,6));
  }

  /**
   * Test whether the pawn can move right.
   */
  @Test
  public void testPawnMoveRight() {
    assertEquals(false, pawnOne.canMove(0,4));
  }

  /**
   * Test whether the pawn can move two spaces forward.
   */
  @Test
  public void testPawnMoveTwoForward() {
    assertEquals(false, pawnOne.canMove(2,5));
  }

  /**
   * Test whether the pawn can move backwards.
   */
  @Test
  public void testPawnMoveBackwards() {
    assertEquals(false, pawnTwo.canMove(4,5));
  }


  /**
   * Test illegal argument exception for invalid position placement.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBishopMoveToInvalidOffBoardPosition() {
    assertEquals(false, pawnOne.canMove(-3, -4));
  }


  /**
   * Test whether the pawn can kill itself.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPawnKillItself() {
    assertEquals(false, pawnOne.canKill(pawnOne));
  }

  /**
   * Test whether the pawn can kill same color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPawnKillSameColor() {
    assertEquals(false, pawnOne.canKill(pawnTwo));
  }

  /**
   * Test whether the pawn can kill other color.
   */
  @Test
  public void testPawnKillOtherColor() {
    assertEquals(true, pawnOne.canKill(pawnThree));
  }




}