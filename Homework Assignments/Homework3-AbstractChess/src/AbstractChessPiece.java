/**
 * Represents an abstract class implementing a chess piece using a row, column, and color.
 */
public abstract class AbstractChessPiece implements ChessPiece {
  private int row;
  private int column;
  private Color color;

  /**
   * Constructs an abstract chess piece using a row, column, and color in which a method checks that
   * the row and column are positive numbers.
   *
   * @IllegalArgumentException is thrown if the numbers are not positive.
   */
  public AbstractChessPiece(int row, int column, Color color) {
    checkRowAndColumn(row, column);
    this.row = row;
    this.column = column;
    this.color = color;
  }

  /**
   * Returns the row of the piece.
   *
   * @return double of the piece representing its row.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Method that confirms that the row and column are positive numbers.
   *
   * @return boolean value confirming that the two and column are positive.
   */
  protected void checkRowAndColumn(int row, int column) {
    if (!isValid(row) || !isValid(column)) {
      throw new IllegalArgumentException("Non-negative number only");
    }
  }

  /**
   * Method that confirms that the row and column are on the 7x7 board.
   *
   * @return boolean value confirming that the piece is on the board.
   */
  protected boolean isValid(int n) {
    return n >= 0 && n <= 7;
  }

  /**
   * Returns the column of the piece.
   *
   * @return double of the piece representing its column.
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * Returns the color of the piece.
   *
   * @return enum of the piece representing its color.
   */
  public Color getColor() {
    return this.color;
  }


  /**
   * Method that determines if the piece can move to a given location.
   *
   * @return boolean representing of the move can be made.
   */
  public boolean canMove(int row, int column) {
    checkRowAndColumn(row, column);
    return true;
  }

  /**
   * Method that determines if the piece can kill a piece at a given location.
   *
   * @return boolean representing whether the piece can kill the other.
   */
  public boolean canKill(ChessPiece piece) {
    return piece.getColor() != this.getColor();
  }

}