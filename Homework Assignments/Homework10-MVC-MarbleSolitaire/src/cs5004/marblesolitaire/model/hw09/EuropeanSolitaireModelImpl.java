package cs5004.marblesolitaire.model.hw09;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.AbstractMarbleSolitaireImpl;

/**
 * This class represents the marble solitaire game using the european version of the board.
 */
public class EuropeanSolitaireModelImpl
        extends AbstractMarbleSolitaireImpl implements MarbleSolitaireModel {


  /**
   * No argument constructor that initializes the game board with a thickness of 3, and starting
   * empty position of 3,3.
   */
  public EuropeanSolitaireModelImpl() {
    this.armThick = 3;
    this.startRowEmpty = 3;
    this.startColEmpty = 3;
    this.initializeBoard();
  }

  /**
   * The single argument constructor for the european version of the marble solitaire game.
   *
   * @param armThick The thickness of the arm of the board of the game.
   * @throws IllegalStateException will be thrown if the input values are not valid.
   */
  public EuropeanSolitaireModelImpl(int armThick) throws IllegalArgumentException {
    if (armThick < 3 || armThick % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness "
              + "must be a positive odd number 3 or greater");
    }
    this.armThick = armThick;
    this.startRowEmpty = armThick / 2 * 3;
    this.startColEmpty = armThick / 2 * 3;
    this.emptyCellValidHelper();
    this.initializeBoard();
  }


  /**
   * The two argument constructor for the european version of the marble solitaire game.
   *
   * @param startRowEmpty The row of where the empty space should start.
   * @param startColEmpty The column of where the empty space should start.
   * @throws IllegalStateException will be thrown if the input values are not valid.
   */
  public EuropeanSolitaireModelImpl(int startRowEmpty, int startColEmpty)
          throws IllegalStateException {
    armThick = 3;
    this.startRowEmpty = startRowEmpty;
    this.startColEmpty = startColEmpty;
    this.emptyCellValidHelper();
    this.initializeBoard();
  }

  /**
   * The three argument constructor for the european version of the marble solitaire game.
   *
   * @param armThick      The thickness of the arm of the board of the game.
   * @param startRowEmpty The row of where the empty space should start.
   * @param startColEmpty The column of where the empty space should start.
   * @throws IllegalStateException will be thrown if the input values are not valid.
   */
  public EuropeanSolitaireModelImpl(int armThick, int startRowEmpty, int startColEmpty)
          throws IllegalArgumentException {
    if (armThick % 2 == 0 || armThick < 3) {
      throw new IllegalArgumentException("Arm Thickness must be greater than or equal to 3!");
    }
    this.armThick = armThick;
    this.startRowEmpty = startRowEmpty;
    this.startColEmpty = startColEmpty;
    this.emptyCellValidHelper();
    this.initializeBoard();
  }

  /**
   * Helper method that determines the validity of the armThick value.
   */
  @Override
  protected boolean invalidCheckHelper(int x, int y) {
    return (x + y < armThick - 1
            || x <= y - armThick * 2 + 1
            || y <= x - armThick * 2 + 1
            || x + y >= 5 * armThick - 4);
  }

}
