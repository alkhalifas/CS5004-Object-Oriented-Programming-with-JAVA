package cs5004.marblesolitaire.controller;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * This interface class represents the controller for the game.
 */
public interface InterfaceController {


  /**
   * Begins a new game of MarbleSolitaire. Using MarbleSolitaire.java, the user input will be parse
   * and subsequent moves played.
   *
   * @param game Represents the current game of MarbleSolitaire.
   * @throws IllegalArgumentException will be thrown if the game is not initialized correctly using
   *                                  appendables and readables.
   */
  void playGame(MarbleSolitaireModel game) throws IllegalArgumentException;

}
