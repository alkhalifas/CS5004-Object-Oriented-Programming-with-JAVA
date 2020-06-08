/**
 * Represents a Bishop class of a chess piece using a row, column, and color.
 */
public class Bishop extends AbstractChessPiece {
  public Bishop(int row, int column, Color color) {
    super(row, column, color);
  }

  /**
   * Returns the boolean value of whether or not a piece can move in the specific row and column.
   *
   * @return boolean value of whether or not the piece can move.
   */
  @Override
  public boolean canMove(int row, int column) {
    return super.canMove(row, column) && this.getRow() != row
            && this.getColumn() != column && Math.abs(row - this.getRow())
            == Math.abs(column - this.getColumn());
  }

  /**
   * Returns the boolean value of whether or not a piece can kill a piece in the specific row and
   * column.
   *
   * @return boolean value of whether or not the piece can kill.
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    return super.canKill(piece) && canMove(piece.getRow(), piece.getColumn());
  }
}