/**
 * This interface contains all operations that all types of chess pieces should support.
 */
public interface ChessPiece {

  /**
   * Returns the row of the chess piece coordinate.
   *
   * @return the rwo.
   */
  int getRow();

  /**
   * Returns the column of the chess piece coordinate.
   *
   * @return the column.
   */
  int getColumn();

  /**
   * Returns the color of the chess piece coordinate.
   *
   * @return the color.
   */
  Color getColor();

  /**
   * Returns the boolean value of whether or not a piece can move correctly.
   *
   * @return the boolean true of false.
   */
  boolean canMove(int row, int column);

  /**
   * Returns the boolean value of whether or not a piece can kill correctly.
   *
   * @return the boolean true of false.
   */
  boolean canKill(ChessPiece piece);
}