import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * This class contains all the unit tests for various kinds of shapes.
 */
public class ChessPieceTest {

  ChessPiece knightOne;
  ChessPiece pawnOne;
  ChessPiece bishopOne;
  ChessPiece queenOne;
  ChessPiece rookOne;

  @Before
  public void setup() {
    knightOne = new Knight(3, 4, Color.WHITE);
    pawnOne = new Pawn(1, 1, Color.BLACK);
    bishopOne = new Bishop(4, 5, Color.WHITE);
    queenOne = new Queen(5, 3, Color.BLACK);
    rookOne = new Rook(7, 7, Color.WHITE);

  }

  /**
   * This test will test getting colors from each of the objects.
   */
  @Test
  public void testGetColorAllPieces() {
    assertEquals("WHITE", knightOne.getColor().toString());
    assertEquals("BLACK", pawnOne.getColor().toString());
    assertEquals("WHITE", bishopOne.getColor().toString());
    assertEquals("BLACK", queenOne.getColor().toString());
    assertEquals("WHITE", rookOne.getColor().toString());
  }

  /**
   * This test will test getting colors from each of the objects.
   */
  @Test
  public void testGetColumnsAllPieces() {
    assertEquals(4, knightOne.getColumn());
    assertEquals(1, pawnOne.getColumn());
    assertEquals(5, bishopOne.getColumn());
    assertEquals(3, queenOne.getColumn());
    assertEquals(7, rookOne.getColumn());
  }

  /**
   * This test will test getting colors from each of the objects.
   */
  @Test
  public void testGetRowsAllPieces() {
    assertEquals(3, knightOne.getRow());
    assertEquals(1, pawnOne.getRow());
    assertEquals(4, bishopOne.getRow());
    assertEquals(5, queenOne.getRow());
    assertEquals(7, rookOne.getRow());
  }


}
