package cs5004.tictactoe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents the controller that allows the game of Tic Tac Toe to be played.
 */
public class TicTacToeConsoleController implements TicTacToeController {
  private final Appendable out;
  private final Scanner scan;

  /**
   * This constructs a TicTacToe controller object using input and output.
   * @param in which represents the input value for Readable class.
   * @param out which represents the output value for the Appendable class.
   * @throws IllegalArgumentException if the in or out values are null.
   * @throws IllegalStateException if the number of inputs run out.
   *
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Values cannot be null!");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {

    try {
      playGameHelper(m);
      Integer row = null;
      Integer col = null;
      String token = "";


      while (!m.isGameOver()) {
        token = scan.next();
        if (token.equalsIgnoreCase("q")) {
          break;
        }

        try {
          int someInt = Integer.parseInt(token);
          if (row == null) {
            row = someInt;
          } else {
            col = someInt;
            m.move(row - 1, col - 1);
            if (m.isGameOver()) {
              out.append(m.toString()).append("\n");
              out.append("Game is over! ");
              if (m.getWinner() != null) {
                out.append(m.getWinner().toString() + " wins.\n");
              } else {
                out.append("Tie game.\n");
              }
              break;
            }
            playGameHelper(m);
            row = col = null;
          }
        } catch (NumberFormatException e) {
          out.append("This is not a valid number: " + token).append("\n");
        }
      }

      if (!m.isGameOver() && token.equalsIgnoreCase("q")) {
        out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
      } else if (!m.isGameOver()) {
        throw new IllegalStateException("Ran out of inputs");
      }
    } catch (IOException e) {
      scan.close();
      throw new IllegalStateException("append failed", e);
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("failed to read from readable");
    }
  }

  /**
   * This method is a helper function that uses getTurn and toString to correctly append values.
   */
  private void playGameHelper(TicTacToe m) throws IOException {
    out.append(m.toString()).append("\n");
    out.append("Enter a move for " + m.getTurn().toString()).append(":\n");
  }
}
