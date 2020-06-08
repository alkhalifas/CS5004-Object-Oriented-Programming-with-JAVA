package cs5004.marblesolitaire.model.hw09;

import java.util.ArrayList;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.AbstractMarbleSolitaireImpl;

/**
 * This class represents the marble solitaire game using the triangle version of the board.
 */
public class TriangleSolitaireModelImpl
        extends AbstractMarbleSolitaireImpl implements MarbleSolitaireModel {

  private final int diagonalDistance;
  //private final int dimensions;

  /**
   * No argument constructor that initializes the game board with a diagonal of 3, and starting
   * empty position of 0,0.
   */
  public TriangleSolitaireModelImpl() {
    this.startRowEmpty = 0;
    this.startColEmpty = 0;

    this.diagonalDistance = 5;
    this.initializeBoard();

  }

  /**
   * The single argument constructor for the triangle version of the marble solitaire game.
   *
   * @param dimensions The dimensions of the side of the board of the game.
   * @throws IllegalStateException will be thrown if the input values are not valid.
   */
  public TriangleSolitaireModelImpl(int dimensions) throws IllegalArgumentException {
    if (dimensions < 2) {
      throw new IllegalArgumentException("Must be an integer greater than 2!");
    }
    this.startRowEmpty = 0;
    this.startColEmpty = 0;

    this.diagonalDistance = dimensions;
    this.initializeBoard();

  }

  /**
   * The two argument constructor for the triangle version of the marble solitaire game.
   *
   * @param row The row of where the empty space should start.
   * @param col The column of where the empty space should start.
   * @throws IllegalStateException will be thrown if the input values are not valid.
   */
  public TriangleSolitaireModelImpl(int row, int col)
          throws IllegalArgumentException {
    this.startRowEmpty = row;
    this.startColEmpty = col;

    this.diagonalDistance = 5;
    this.initializeBoard();

  }

  /**
   * The three argument constructor for the triangle version of the marble solitaire game.
   *
   * @param dimensions The dimensions of the side of the board of the game.
   * @param row        The row of where the empty space should start.
   * @param col        The column of where the empty space should start.
   * @throws IllegalStateException will be thrown if the input values are not valid.
   */
  public TriangleSolitaireModelImpl(int dimensions, int row, int col)
          throws IllegalArgumentException {
    if (dimensions < 2) {
      throw new IllegalArgumentException("Must be an integer greater than 2!");
    }
    this.startRowEmpty = row;
    this.startColEmpty = col;

    this.diagonalDistance = dimensions;
    this.initializeBoard();

  }

  /**
   * Helper method that determines the validity of the armThick value.
   */
  @Override
  public boolean invalidCheckHelper(int row, int col) {
    return col > row;
  }


  /**
   * A helper function that will initialize the board depending on the parameters given the
   * constructor.
   */
  @Override
  protected void initializeBoard() {
    ArrayList<ArrayList<piecePos>> result = new ArrayList<ArrayList<piecePos>>();
    ArrayList<piecePos> temporaryRow;
    for (int row = 0; row < diagonalDistance; row++) {

      temporaryRow = new ArrayList<piecePos>();
      for (int col = 0; col < diagonalDistance; col++) {
        if (this.invalidCheckHelper(row, col)) {
          temporaryRow.add(piecePos.NULL);
        } else if (row == startRowEmpty && col == startColEmpty) {
          temporaryRow.add(piecePos.EMPTY);
        } else {
          temporaryRow.add(piecePos.MARBLE);
        }

      }
      result.add(temporaryRow);
    }
    board = result;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (toCol < 0 || fromCol < 0 || fromRow < 0 || fromCol > fromRow || toCol > toRow
            || fromRow >= diagonalDistance || toRow >= diagonalDistance) {

      throw new IllegalArgumentException("This move is not valid. Please enter a valid move.");
    } else if (((toRow == fromRow && toCol == fromCol + 2)// move left 2 or right 2
            || (toRow == fromRow && toCol == fromCol - 2)// move left 2 or right 2
            || (toRow == fromRow + 2 && toCol == fromCol)// move up 2 or down 2
            || (toRow == fromRow - 2 && toCol == fromCol)// move up 2 or down 2
            || (toRow == fromRow + 2 && toCol == fromCol + 2)// move up right 2 or down right 2
            || (toRow == fromRow - 2 && toCol == fromCol + 2)// move up right 2 or down right 2
            || (toRow == fromRow + 2 && toCol == fromCol - 2)// move up left 2 or down left 2
            || (toRow == fromRow - 2 && toCol == fromCol - 2))// move up left 2 or down left 2
            && board.get(fromRow).get(fromCol) == piecePos.MARBLE
            && board.get(toRow).get(toCol) == piecePos.EMPTY
            && board.get((toRow - fromRow) / 2 + fromRow)

            .get(((toCol - fromCol) / 2) + (fromCol)) == piecePos.MARBLE) {
      this.board.get(toRow).set(toCol, piecePos.MARBLE);
      this.board.get(fromRow).set(fromCol, piecePos.EMPTY);
      this.board.get((toRow - fromRow) / 2 + (fromRow)).set((toCol - fromCol)
              / 2 + (fromCol), piecePos.EMPTY);
    } else {
      throw new IllegalArgumentException("This move is not valid. Please enter a valid move.");
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return !(horizontalMoveHelper() || verticalMoveHelper() || horizontalMoveHelper());
  }

  /**
   * A helper function that iterates through the board and will return a boolean value for the
   * validity of the move.
   */
  public boolean horizontalMoveHelper() {
    for (int i = 0; i < board.size() - 2; i++) {
      for (int k = 0; k < board.get(0).size() - 2; k++) {
        if ((
                // Correct occupied and unoccupied spaces
                board.get(i).get(k) == piecePos.MARBLE // x y has marble
                        && board.get(i + 1).get(k + 1) == piecePos.MARBLE // upright x+1 y+1 marble
                        && board.get(i + 2).get(k + 2) == piecePos.EMPTY)  // upright x+2 y+2 empty

                || (board.get(i + 2).get(k) == piecePos.MARBLE // x+2 has marble
                && board.get(i + 1).get(k + 1) == piecePos.MARBLE // x+1 y+1 has marble
                && board.get(i).get(k + 2) == piecePos.EMPTY) //  x y+2 has empty

                || (board.get(i).get(k) == piecePos.EMPTY // x y has empty
                && board.get(i + 1).get(k + 1) == piecePos.MARBLE // x+1 y+1 has marble
                && board.get(i + 2).get(k + 2) == piecePos.MARBLE) // x+2 y+2 has marble
                || (board.get(i + 2).get(k) == piecePos.EMPTY // x+2 y has empty
                && board.get(i + 1).get(k + 1) == piecePos.MARBLE // x+1 y+1 has marble
                && board.get(i).get(k + 2) == piecePos.MARBLE)) { // y+2 has marble

          return true;
        }
      }
    }
    return false;
  }

  /**
   * A helper function that iterates through the board and will return a boolean value for the
   * validity of the move.
   */
  protected boolean verticalMoveHelper() {
    for (int i = 0; i < board.size() - 2; i++) {
      for (int j = 0; j < board.get(0).size(); j++) {
        if ((board.get(i).get(j) == piecePos.MARBLE
                && board.get(i + 1).get(j) == piecePos.MARBLE
                && board.get(i + 2).get(j) == piecePos.EMPTY)
                || (board.get(i).get(j) == piecePos.EMPTY
                && board.get(i + 1).get(j) == piecePos.MARBLE
                && board.get(i + 2).get(j) == piecePos.MARBLE)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character (O, X or space for
   * a marble, empty and invalid piecePos respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  public String getGameState() {
    StringBuilder builder = new StringBuilder();
    for (int row = 0; row < diagonalDistance; row++) {
      for (int i = row + 1; diagonalDistance - i > 0; i++) {
        builder.append(" ");
      }
      for (int col = 0; col < diagonalDistance; col++) {
        switch (board.get(row).get(col)) {
          case MARBLE:
            builder.append("O ");
            continue;
          case EMPTY:
            builder.append("_ ");
            continue;
          default:
            continue;
        }
      }
      while (builder.charAt(builder.length() - 1) == ' ') {
        builder.deleteCharAt(builder.length() - 1);
      }
      builder.append("\n");
    }
    builder.deleteCharAt(builder.length() - 1);
    return builder.toString();
  }


}
