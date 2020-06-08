package cs5004.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;

import cs5004.animator.model.InterfaceInterpretShape;

/**
 * This class represents an interface for a drawing panel for the GUI view.
 */
public class DrawPanel extends AbstractDrawPanel implements InterfaceDrawPanel {

  /**
   * This constructor creates a drawing panel for all calls.
   */
  public DrawPanel() {
    super();
  }

  @Override
  protected void drawShapesOn(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    for ( InterfaceInterpretShape shape : this.shapes ) {
      g2D.setColor(shape.getColor());
      shape.draw(g2D);
    }
  }
}
