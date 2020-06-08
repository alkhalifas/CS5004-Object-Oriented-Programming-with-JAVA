package cs5004.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * This class represents the actual implementation of the the games controller.
 */
public class Controller implements InterfaceController {

  private Readable readableOne;
  private Readable readableTwo;
  private Appendable appendableOne;
  private Appendable appendableTwo;
  private int intermediate;


  /**
   * This class represents the actual implementation of the the games controller.
   *
   * @param readable   is the variable used to interpret and parse the input from the user.
   * @param appendable is the variable used to add output to the user.
   * @throws IllegalArgumentException will be thrown if the values are null.
   */
  public Controller(Readable readable, Appendable appendable) throws IllegalArgumentException {
    if (readable == null || appendable == null) {
      throw new IllegalArgumentException("------ Controller Error: Arguments cannot be null.");
    }
    this.readableOne = readable;
    this.appendableOne = appendable;
  }

  /**
   * Method that reads input to ensure acceptable values entered.
   *
   * @param next is the next input value.
   * @returns boolean value of true if input is acceptable.
   */
  private boolean checkInput(int next) {
    if (next < 0) {
      this.gameAppendHelper("------ Game Error: Re-enter the move.\n",
              "------ Game Error: Appending error occurred. ");
      return false;
    } else {
      return true;
    }
  }

  /**
   * Method that will read input from user. User is to enter four numbers with no other characters
   * that represent startRowEmpty, startCol, endRow, endCol.
   * Example: 1 [enter], 2 [enter], 3 [enter], 4 [enter].
   *
   * @param game    is the current game of marble solitaire.
   * @param scanner variable for scanning the input values.
   */
  private boolean gameInput(MarbleSolitaireModel game, Scanner scanner) {
    int userInput = 0;
    int[] usersMove = new int[]{0, 0, 0, 0};
    while (scanner.hasNext() && userInput < 4) {
      String nextInput = scanner.next();
      int nextValue;
      nextValue = Integer.parseInt(nextInput);
      if (this.checkInput(nextValue)) {
        if (userInput <= 3) {
          usersMove[userInput] = nextValue - 1;
          userInput++;
        }
        if (userInput == 4) {
          try {
            game.move(usersMove[0], usersMove[1], usersMove[2], usersMove[3]);
          } catch (IllegalArgumentException e) {
            this.gameAppendHelper("------ Game Error: Move not valid. "
                            + e.getMessage() + "\n",
                    "------ Game Error: Appending error occurred.");
            userInput = 0;
          }
        }
      }
    }
    return true;
  }

  /**
   * Begins a new game of MarbleSolitaire. Using MarbleSolitaire.java, the user input will be parse
   * and subsequent moves played.
   *
   * @param model Represents the current game of MarbleSolitaire.
   * @throws IllegalArgumentException will be thrown if the game is not initialized correctly using
   *                                  appendables and readables.
   */
  @Override
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException {
    Scanner scanner = new Scanner(this.readableOne);
    try {
      while (!model.isGameOver()) {
        if (!scanner.hasNext()) {
          throw new IllegalStateException("------- Error: There are no more inputs.");
        }
        this.appCurrentGameState(model);
        this.appCurrentGameScore(model);
        if (!this.gameInput(model, scanner)) {
          String appendText = "Current game Score: " + model.getScore();
          this.appCurrentGameState(model);
          this.gameAppendHelper(appendText,
                  "------- Error: Unknown controller appending error.");
          return;
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("------- Error: Model is null. Please reconstruct.");
    }
    this.gameAppendHelper("Game is over!\n",
            "------- Error: Unknown controller appending error.");
    this.appCurrentGameState(model);
    this.appCurrentGameScore(model);
  }


  /**
   * Method that will append the current game state by calling 'getGameState()' for the game of
   * interest.
   *
   * @param game the current game of marble solitaire.
   */
  private void appCurrentGameState(MarbleSolitaireModel game) {
    this.gameAppendHelper(game.getGameState() + "\n",
            "------ Game Error: Cannot append game state.");
  }

  /**
   * Method that will append the current game score by calling 'getScore()' for the game of
   * interest.
   *
   * @param game the current game of marble solitaire.
   */
  private void appCurrentGameScore(MarbleSolitaireModel game) {
    String toAppend = "Game Score: " + game.getScore() + "\n";
    this.gameAppendHelper(toAppend, "------ Game Error: Cannot append game score.");
  }


  /**
   * Method used to establish two messages for same problem depending on boolean logic. Tries to
   * append a message to the appendable. Gives a specific error message if append fails.
   *
   * @param errorFeedBack is a String with a message to the user regarding the game.
   * @param errorMessage  is a String with a message to the user regarding the state of the game.
   * @throws IllegalStateException will be raised if the message cannot be appended.
   */
  private void gameAppendHelper(String errorFeedBack, String errorMessage) {
    try {
      this.appendableOne.append(errorFeedBack);
    } catch (IOException e) {
      throw new IllegalStateException(errorMessage);
    }
  }
}
