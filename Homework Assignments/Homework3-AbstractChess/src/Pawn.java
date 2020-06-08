/**
 * Represents a pawn class of a chess piece using a row, column, and color.
 */
public class Pawn extends AbstractChessPiece {
  public Pawn(int row, int column, Color color) {
    super(row, column, color);
  }


  /**
   * Returns the boolean value of whether or not a piece can move in the specific row and column.
   *
   * @return boolean value of whether or not the piece can move.
   */
  @Override
  public boolean canMove(int row, int column) {
    boolean validPosition = super.canMove(row, column);
    if (!validPosition) {
      throw new IllegalArgumentException("Non-negative number only");
    }
    if (getColor() == Color.WHITE) {
      if (this.getRow() + 1 == row && this.getColumn() == column) {
        return true;
      }
    }
    if (getColor() == Color.BLACK) {
      if (this.getRow() - 1 == row && this.getColumn() == column) {
        return true;
      }
    }
    return false;
  }


  /**
   * Returns the boolean value of whether or not a piece can kill a piece in the specific row and
   * column.
   *
   * @return boolean value of whether or not the piece can kill.
   */
  @Override
  public boolean canKill(ChessPiece piece) {

    if (this.getColor() == piece.getColor()) {
      throw new IllegalArgumentException("Same Color!!:");
    }

    if (this.getColor() == Color.WHITE) {

      if (piece.getRow() == this.getRow() + 1 && piece.getColumn() == this.getColumn() + 1) {
        return true;
      }
      if (piece.getRow() == this.getRow() + 1 && piece.getColumn() == this.getColumn() - 1) {
        return true;
      }
    }

    if (this.getColor() == Color.BLACK) {

      if (piece.getRow() == this.getRow() - 1 && piece.getColumn() == this.getColumn() + 1) {
        return true;
      }
      return piece.getRow() == this.getRow() - 1 && piece.getColumn() == this.getColumn() - 1;
    }
    return false;
  }

}


