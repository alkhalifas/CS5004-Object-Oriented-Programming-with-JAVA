package cs5004.marblesolitaire;

import java.io.InputStreamReader;

import cs5004.marblesolitaire.controller.Controller;
import cs5004.marblesolitaire.model.AbstractMarbleSolitaireImpl;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;
import cs5004.marblesolitaire.model.hw09.EuropeanSolitaireModelImpl;
import cs5004.marblesolitaire.model.hw09.TriangleSolitaireModelImpl;

/**
 * This class contains the main driver that allows the game to be started and played with using the
 * command line.
 */
public final class MarbleSolitaire {

  /**
   * Main driver for the marble solitaire game which utilizes the controller to create a new game.
   */
  public static void main(String[] args) {
    int startRowEmpty = -1;
    int startColEmpty = -1;
    int start = -1;

    String game = args[0];
    AbstractMarbleSolitaireImpl gameImpl;
    InputStreamReader readable = new InputStreamReader(System.in);
    for (int i = 1; i < args.length; i += 2) {
      switch (args[i]) {
        case "-size":
          start = Integer.parseInt(args[i + 1]);
          break;
        case "-hole":
          startRowEmpty = Integer.parseInt(args[i + 1]);
          startColEmpty = Integer.parseInt(args[i + 2]);
          i += 1;
          break;
        case "":
          break;
        default:
          throw new IllegalArgumentException("------ Game Error: Please check command "
                  + "line arguments.");
      }
    }
    if (start < 0 && startRowEmpty < 0 && startColEmpty < 0) {
      switch (game) {
        case "european":
          gameImpl = new EuropeanSolitaireModelImpl();
          break;
        case "triangle":
          gameImpl = new TriangleSolitaireModelImpl();
          break;
        case "english":
          gameImpl = new MarbleSolitaireModelImpl();
          break;
        default:
          throw new IllegalArgumentException("------ Game Error: Please enter a valid game type"
                  + "such as 'european', 'english', or 'triangle'");
      }
    } else if (start < 0 && startRowEmpty > 0 && startColEmpty > 0) {
      switch (game) {
        case "european":
          gameImpl = new EuropeanSolitaireModelImpl(startRowEmpty, startColEmpty);
          break;
        case "triangle":
          gameImpl = new TriangleSolitaireModelImpl(startRowEmpty, startColEmpty);
          break;
        case "english":
          gameImpl = new MarbleSolitaireModelImpl(startRowEmpty, startColEmpty);
          break;
        default:
          throw new IllegalArgumentException("------ Game Error: Please enter a valid game type "
                  + "such as 'european', 'english', or 'triangle'");
      }
    } else if (start > 0 && startRowEmpty < 0 && startColEmpty < 0) {
      switch (game) {
        case "european":
          gameImpl = new EuropeanSolitaireModelImpl(start);
          break;
        case "triangle":
          gameImpl = new TriangleSolitaireModelImpl(start);
          break;
        case "english":
          gameImpl = new MarbleSolitaireModelImpl(start);
          break;
        default:
          throw new IllegalArgumentException("------ Game Error: Please enter a valid game type "
                  + "such as 'european', 'english', or 'triangle'");
      }
    } else {
      switch (game) {
        case "european":
          gameImpl = new EuropeanSolitaireModelImpl(start, startRowEmpty, startColEmpty);
          break;
        case "triangle":
          gameImpl = new TriangleSolitaireModelImpl(start, startRowEmpty, startColEmpty);
          break;
        case "english":
          gameImpl = new MarbleSolitaireModelImpl(start, startRowEmpty, startColEmpty);
          break;
        default:
          throw new IllegalArgumentException("------ Game Error: Please enter a valid game type "
                  + "such as 'european', 'english', or 'triangle'");
      }
    }
    new Controller(readable, System.out).playGame(gameImpl);
  }
}