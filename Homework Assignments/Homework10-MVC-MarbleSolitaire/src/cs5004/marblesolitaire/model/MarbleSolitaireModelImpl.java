package cs5004.marblesolitaire.model;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;

/**
 * This class represents the implementation of the MarbleSolitaire model class.
 */
public class MarbleSolitaireModelImpl
        extends AbstractMarbleSolitaireImpl
        implements MarbleSolitaireModel {

  /**
   * Constructs a new instance of the MarbleSolitaire game.
   */
  public MarbleSolitaireModelImpl() {
    armThick = 3;
    startRowEmpty = 3;
    startColEmpty = 3;
    this.initializeBoard();
  }

  /**
   * Constructs a new instance of the MarbleSolitaire game.
   *
   * @param startRowEmpty which represents the row of the empty space.
   * @param startColEmpty which represents the column of the empty space.
   */
  public MarbleSolitaireModelImpl(int startRowEmpty, int startColEmpty) {
    armThick = 3;
    this.startRowEmpty = startRowEmpty;
    this.startColEmpty = startColEmpty;
    this.emptyCellValidHelper();
    this.initializeBoard();
  }

  /**
   * Constructs a new instance of the MarbleSolitaire game.
   *
   * @param armThick which represents the row of the empty space.
   * @throws IllegalArgumentException if the arm thickness is less than 3.
   */
  public MarbleSolitaireModelImpl(int armThick) {
    if (armThick < 3 || armThick % 2 == 0) {
      throw new IllegalArgumentException("The arm thickness must be greater than 3!");
    }
    this.armThick = armThick;
    this.startRowEmpty = armThick / 2 * 3;
    this.startColEmpty = armThick / 2 * 3;
    this.emptyCellValidHelper();
    this.initializeBoard();
  }

  /**
   * Constructs a new instance of the MarbleSolitaire game.
   *
   * @param startRowEmpty     which represents the row of the empty space.
   * @param startColEmpty     which represents the column of the empty space.
   * @param armThick which represents the row of the empty space.
   * @throws IllegalArgumentException if the arm thickness is less than 3.
   */
  public MarbleSolitaireModelImpl(int armThick, int startRowEmpty, int startColEmpty) {
    if (armThick < 3 || armThick % 2 == 0) {
      throw new IllegalArgumentException("The arm thickness must be greater than 3!");
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
    return (x < armThick - 1 && y < armThick - 1)
            || (x < armThick - 1 && y >= armThick * 2 - 1)
            || (x >= armThick * 2 - 1 && y < armThick - 1)
            || (x >= armThick * 2 - 1 && y >= armThick * 2 - 1);
  }
}