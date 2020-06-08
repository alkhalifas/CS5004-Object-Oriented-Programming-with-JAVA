package cs5004.animator.view;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import cs5004.animator.model.InterfaceInterpretShape;

/**
 * This class represents the class that sets the visual view panel and allows for interpretation of
 * shapes.
 */
public abstract class AbstractDrawPanel extends JPanel implements InterfaceDrawPanel {
  protected List<InterfaceInterpretShape> shapes;

  protected AbstractDrawPanel() {
    super();
    this.shapes = null;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.shapes != null) {
      this.drawShapesOn(g);
    }
  }

  protected abstract void drawShapesOn(Graphics g);

  @Override
  public void draw(List<InterfaceInterpretShape> shapes) {
    this.shapes = shapes;
    this.repaint();
  }
}
