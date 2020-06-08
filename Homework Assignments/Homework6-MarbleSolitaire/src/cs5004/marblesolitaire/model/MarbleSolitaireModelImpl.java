package cs5004.marblesolitaire.model;

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
    sRow = 3;
    sCol = 3;
    this.initializeBoard();
  }

  /**
   * Constructs a new instance of the MarbleSolitaire game.
   *
   * @param sRow which represents the row of the empty space.
   * @param sCol which represents the column of the empty space.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    armThick = 3;
    this.sRow = sRow;
    this.sCol = sCol;
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
    this.sRow = armThick / 2 * 3;
    this.sCol = armThick / 2 * 3;
    this.emptyCellValidHelper();
    this.initializeBoard();
  }

  /**
   * Constructs a new instance of the MarbleSolitaire game.
   *
   * @param sRow     which represents the row of the empty space.
   * @param sCol     which represents the column of the empty space.
   * @param armThick which represents the row of the empty space.
   * @throws IllegalArgumentException if the arm thickness is less than 3.
   */
  public MarbleSolitaireModelImpl(int armThick, int sRow, int sCol) {
    if (armThick < 3 || armThick % 2 == 0) {
      throw new IllegalArgumentException("The arm thickness must be greater than 3!");
    }
    this.armThick = armThick;
    this.sRow = sRow;
    this.sCol = sCol;
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