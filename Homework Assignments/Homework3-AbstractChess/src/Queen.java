/**
 * Represents a Queen class of a chess piece using a row, column, and color.
 */
public class Queen extends AbstractChessPiece {
  private Rook rook;
  private Bishop bishop;

  /**
   * Constructs a Queen class using the attributes of a rook and bishop since movement is the same.
   */
  public Queen(int row, int column, Color color) {
    super(row, column, color);
    this.rook = new Rook(row, column, color);
    this.bishop = new Bishop(row, column, color);
  }

  /**
   * Returns the boolean value of whether or not a piece can move in the specific row and column.
   *
   * @return boolean value of whether or not the piece can kill.
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    return super.canKill(piece) && canMove(piece.getRow(), piece.getColumn());
  }

  /**
   * Returns the boolean value of whether or not a piece can kill a piece in the specific row and
   * column.
   *
   * @return boolean value of whether or not the piece can move.
   */
  @Override
  public boolean canMove(int row, int column) {
    return super.canMove(row, column) && (rook.canMove(row, column) || bishop.canMove(row, column));
  }


}