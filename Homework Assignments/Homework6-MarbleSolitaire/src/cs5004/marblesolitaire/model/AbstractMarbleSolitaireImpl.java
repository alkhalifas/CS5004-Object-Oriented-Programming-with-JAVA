package cs5004.marblesolitaire.model;

import java.util.ArrayList;

/**
 * This class represents the operations / methods for the marble solitaire game. One object of the
 * model represents one game of marble solitaire.
 */
public abstract class AbstractMarbleSolitaireImpl implements MarbleSolitaireModel {

  protected int armThick;
  protected int sRow;
  protected int sCol;
  protected ArrayList<ArrayList<piecePos>> board;

  /**
   * Move a single marble from a given piecePos to another given piecePos. A move is valid only if
   * the from and to piecePoss are valid. Specific implementations may place additional constraints
   * on the validity of a move.
   *
   * @param fromRow    the row number of the piecePos to be moved from (starts at 0)
   * @param fromColumn the column number of the piecePos to be moved from (starts at 0)
   * @param toRow      the row number of the piecePos to be moved to (starts at 0)
   * @param toCol      the column number of the piecePos to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromColumn, int toRow, int toCol)
          throws IllegalArgumentException {
    if (fromRow < 0 || toRow < 0 || fromColumn < 0 || toCol < 0
            || fromRow >= 3 * armThick - 2
            || toRow >= 3 * armThick - 2 || fromColumn >= 3 * armThick - 2
            || toCol >= 3 * armThick - 2) {
      throw new IllegalArgumentException("This position is off the board.");
    }

    //Separate based on conceptual relatedness
    //Split them up based on relatedness
    //
    else if (((toRow == fromRow && toCol == fromColumn + 2)
            || (toRow == fromRow + 2 && toCol == fromColumn)
            || (toRow == fromRow && toCol == fromColumn - 2)
            || (toRow == fromRow - 2 && toCol == fromColumn))
            // Look fine /\

            && board.get(toRow).get(toCol) == piecePos.EMPTY
            && board.get(fromRow).get(fromColumn) == piecePos.MARBLE
            && board.get((toRow - fromRow) / 2 + fromRow)
            .get((toCol - fromColumn) / 2 + fromColumn) == piecePos.MARBLE) {
      this.board.get(toRow).set(toCol, piecePos.MARBLE);
      this.board.get(fromRow).set(fromColumn, piecePos.EMPTY);
      this.board.get((toRow - fromRow) / 2 + fromRow)
              .set((toCol - fromColumn) / 2 + fromColumn, piecePos.EMPTY);
    } else {
      throw new IllegalArgumentException("Bad move");
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return !(horizontalMoveHelper() || verticalMoveHelper());
  }

  /**
   * A helper function that iterates through the board and will return a boolean value for the
   * validity of the move.
   */
  protected boolean horizontalMoveHelper() {
    for (ArrayList<piecePos> piecePoss : board) {
      for (int j = 0; j < board.get(0).size() - 2; j++) {
        if ((piecePoss.get(j) == piecePos.MARBLE
                && piecePoss.get(j + 1) == piecePos.MARBLE
                && piecePoss.get(j + 2) == piecePos.EMPTY)
                || (piecePoss.get(j) == piecePos.EMPTY
                && piecePoss.get(j + 1) == piecePos.MARBLE
                && piecePoss.get(j + 2) == piecePos.MARBLE)) {
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
  @Override
  public String getGameState() {
    StringBuilder build = new StringBuilder();
    for (ArrayList<piecePos> list : board) {
      for (piecePos p : list) {
        switch (p) {
          case NULL:
            build.append("  ");
            continue;
          case MARBLE:
            build.append("O ");
            continue;
          case EMPTY:
            build.append("_ ");
            continue;
          default:
        }
      }
      while (build.charAt(build.length() - 1) == ' ') {
        build.deleteCharAt(build.length() - 1);
      }
      build.append("\n");
    }
    build.deleteCharAt(build.length() - 1);
    return build.toString();
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int result = 0;
    for (ArrayList<piecePos> list : board) {
      for (piecePos p : list) {
        if (p == piecePos.MARBLE) {
          result++;
        }
      }
    }
    return result;
  }

  /**
   * A helper function that will initialize the board depending on the parameters given the
   * constructor.
   */
  protected void initializeBoard() {
    ArrayList<ArrayList<piecePos>> result = new ArrayList<ArrayList<piecePos>>();
    ArrayList<piecePos> temporaryRow;
    for (int row = 0; row < armThick * 2 + armThick - 2; row++) {
      temporaryRow = new ArrayList<piecePos>();
      for (int col = 0; col < armThick * 2 + armThick - 2; col++) {
        if (this.invalidCheckHelper(row, col)) {
          temporaryRow.add(piecePos.NULL);
        } else if (row == sRow && col == sCol) {
          temporaryRow.add(piecePos.EMPTY);
        } else {
          temporaryRow.add(piecePos.MARBLE);
        }
      }
      result.add(temporaryRow);
    }
    board = result;
  }

  /**
   * A helper function that will validate the state of the empty cell.
   */
  protected void emptyCellValidHelper() throws IllegalArgumentException {
    if (this.invalidCheckHelper(sRow, sCol)) {
      throw new IllegalArgumentException("This is an invalid empty position");
    }
  }

  protected abstract boolean invalidCheckHelper(int row, int col);

  /**
   * This class represents the identity of the piece.
   */
  protected enum piecePos {
    MARBLE, EMPTY, NULL
  }
}