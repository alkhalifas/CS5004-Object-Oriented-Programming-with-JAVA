package cs5004.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


/**
 * This class represents the rectangle shape in the animation.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for rectangle that initializes its fields.
   */
  public Rectangle(int width, int height, Point2D coordinatePosition, int rotationDegree,
                   Color color) {
    super(width, height, coordinatePosition, rotationDegree, color);
    this.shapeType = "Rectangle";
  }

  /**
   * Constructor for rectangle that initializes its fields.
   */
  public Rectangle() {
    super();
    this.shapeType = "Rectangle";
  }

  /**
   * THis method draws the rectangle onto the screen.
   *
   * @param g are the graphics needed to display the shape.
   */
  @Override
  public void draw(Graphics2D g) {
    if (this.rotationDegree == 0) {
      g.fillRect((int) this.coordinatePosition.getX(), (int) this.coordinatePosition.getY(),
              this.width, this.height);
    } else {
      Graphics2D gg = (Graphics2D) g.create();
      gg.rotate(Math.toRadians(this.rotationDegree), this.coordinatePosition.getX()
                      + ((double) this.width) / 2,
              this.coordinatePosition.getY() + ((double) this.height) / 2);
      gg.fillRect((int) this.coordinatePosition.getX(), (int) this.coordinatePosition.getY(),
              this.width, this.height);
      gg.dispose();
    }
  }

  /**
   * THis method returns a deep copy of the current shape.
   *
   * @return a copy of the shape.
   */
  @Override
  public InterfaceShape makeCopy() {
    return new Rectangle(this.width, this.height,
            new Point2D.Double(this.coordinatePosition.getX(),
                    this.coordinatePosition.getY()),
            this.rotationDegree,
            new Color(this.color.getRGB()));
  }
}