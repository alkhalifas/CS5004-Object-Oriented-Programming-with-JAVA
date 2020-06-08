package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class represents the model class of TicTacToe in which the board is set up, and the methods
 * are prepared to allow players to take turns and play the game.
 */
public class TicTacToeModel implements TicTacToe {
  private Player[][] board;
  private Player currentPlayer;

  /**
   * A an empty argument constructor that sets up a 3x3 board and sets the current player.
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.currentPlayer = Player.X;
  }

  /**
   * A toString method that prints the board with the current location of the two players as well as
   * the empty spaces.
   */
  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
  }

  /**
   * Execute a move in the position specified by the given row and column.
   *
   * @param row    the row of the intended move
   * @param column the column of the intended move
   * @throws IllegalArgumentException if the space is occupied or the position is otherwise invalid
   * @throws IllegalStateException    if the game is over
   */
  @Override
  public void move(int row, int column) throws IllegalArgumentException {
    if (row < 0 || row > 3 && column > 3 || column < 0) {
      throw new IllegalArgumentException("Off the board!");
    }
    if (board[row][column] != null) {
      throw new IllegalArgumentException("Occupied!!");
    }
    if (isGameOver()) {
      throw new IllegalStateException("game over!");
    }
    board[row][column] = currentPlayer;
    if (currentPlayer == Player.X) {
      currentPlayer = Player.O;
    } else {
      currentPlayer = Player.X;
    }
  }

  /**
   * Get the current turn, i.e., the player who will mark on the next call to move().
   *
   * @return the {@link Player} whose turn it is
   */
  @Override
  public Player getTurn() {
    return currentPlayer;
  }

  /**
   * Return whether the game is over. The game is over when either the board is full, or one player
   * has won.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    if (getWinner() != null) {
      return true;
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return the winner of the game, or {@code null} if there is no winner. If the game is not over,
   * returns {@code null}.
   *
   * @return the winner, or null if there is no winner
   */
  @Override
  public Player getWinner() {

    for (int i = 0; i < 3; i++) {
      if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
        return board[i][0];

      } else if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
        return board[0][i];
      }
    }

    if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      return board[0][0];
    }
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      return board[2][0];
    }
    return null;
  }

  /**
   * Return the current game state, as a 2D array of Player. A {@code null} value in the grid
   * indicates an empty position on the board.
   *
   * @return the current game board
   */
  @Override
  public Player[][] getBoard() {
    Player[][] copyBoard = new Player[3][3];
    for (int i = 0; i < 3; i++) {
      System.arraycopy(board[i], 0, copyBoard[i], 0, 3);
    }
    return copyBoard;
  }

  /**
   * Return the current {@link Player} mark at a given row and column, or {@code null} if the
   * position is empty.
   *
   * @param r the row
   * @param c the column
   * @return the player at the given position, or null if it's empty
   */
  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r > 3 && c > 3 || c < 0) {
      throw new IllegalArgumentException("Bad data");
    }
    return board[r][c];
  }
}