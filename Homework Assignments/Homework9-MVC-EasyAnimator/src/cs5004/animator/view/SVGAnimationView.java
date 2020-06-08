package cs5004.animator.view;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.InterfaceAniModel;
import cs5004.animator.model.InterfaceInterpretProcess;
import cs5004.animator.model.InterfaceInterpretStatusProcess;
import cs5004.animator.model.InterfaceInterpretShape;

/**
 * This class allows for the creation of SVG files.
 */
public class SVGAnimationView implements InterfaceTextView {
  private final int numStoreUni;
  private final int x;
  private final int y;
  Appendable outputInfo;
  InterfaceAniModel model;


  /**
   * Constructor for SVGAnimation view that takes in the model for the animation and the tickspeed.
   *
   * @param model   model used for the animation.
   * @param ticksPS is the tickspeed of the animation.
   */
  public SVGAnimationView(InterfaceAniModel model, int ticksPS) {
    if (ticksPS < 1) {
      throw new IllegalArgumentException("Cannot pass a negative ticks per second");
    }
    this.model = model;
    outputInfo = new StringBuilder();
    this.numStoreUni = 1000 / ticksPS;
    this.x = model.getX();
    this.y = model.getY();
  }

  /**
   * Formats the given information as the XML language that converts the list of processes and
   * shapes into XML to be turned into an .svg file.
   */
  @Override
  public void play() {
    String type;
    tryAppend("<svg width=\"" + model.getWidth() + "\" height=\""
            + model.getHeight() + "\" version=\"1.1\"\n    "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (Map.Entry<String, InterfaceInterpretShape> entry : model.getShapes().entrySet()) {
      List<InterfaceInterpretStatusProcess> processes = model.getProcesses().get(entry.getKey());
      switch (entry.getValue().getShapeType()) {
        case "Rectangle":
          type = "rect";
          break;
        case "Ellipse":
          type = "ellipse";
          break;
        default:
          type = "";
      }
      this.tryAppend("<" + type + " id=\""
              + entry.getKey());
      this.tryAppend(this.getStartState(processes.get(0), type));
      this.tryAppend(this.convertProcesssToString(processes, type));
      this.tryAppend("</" + type + ">\n\n");
    }
    tryAppend("</svg>");
  }

  // the try catch for the append.
  private void tryAppend(String input) {
    try {
      outputInfo.append(input);
    } catch (IOException e) {
      throw new IllegalStateException("Bad Appendable");
    }
  }

  /**
   * Helper method that ingests the status list, and type and passes all associated information into
   * SVG format.
   */
  private String convertProcesssToString(List<InterfaceInterpretStatusProcess> list, String type) {
    StringBuilder output = new StringBuilder();
    String startingStringValue;
    String endingStringValue = "\" fill=\"freeze\" />\n";


    for (InterfaceInterpretStatusProcess process : list) {
      startingStringValue = "    <animate attributeType=\"xml\" begin=\""
              + process.getStartTime() * numStoreUni + "ms\" dur=\""
              + (process.getEndTime() - process.getStartTime()) * numStoreUni + "ms\" "
              + "attributeName=\"";

      if (process.getStartRotationDegree() != process.getEndRotationDegree()) {
        if (type.equals("rect")) {
          output.append("<animateTransform attributeName=\"transform\" attributeType=\"xml\""
                  + " type=\"rotate\" from=\"" + process.getStartRotationDegree() + " "
                  + (process.getStartX() + process.getStartWidth() / 2 - this.x) + " "
                  + (process.getStartY() + process.getStartHeight() / 2 - this.y)
                  + "\" to=\"" + process.getEndRotationDegree() + " "
                  + (process.getStartX() + process.getStartWidth() / 2 - this.x) + " "
                  + (process.getStartY() + process.getStartHeight() / 2 - this.y) + "\" dur=\""
                  + ((process.getEndTime() - process.getStartTime()) * numStoreUni) + "ms\" "
                  + "repeatCount=\"0\"/>\n");
        } else if (type.equals("ellipse")) {
          output.append("<animateTransform attributeName=\"transform\" attributeType=\"xml\""
                  + " type=\"rotate\" from=\"" + process.getStartRotationDegree() + " "
                  + (process.getStartX() - this.x + process.getStartWidth() / 2) + " "
                  + (process.getStartY() - this.y + process.getStartHeight() / 2)
                  + "\" to=\"" + process.getEndRotationDegree() + " "
                  + (process.getStartX() - this.x + process.getStartWidth() / 2) + " "
                  + (process.getStartY() - this.y + process.getStartHeight() / 2)
                  + "\" dur=\"" + ((process.getEndTime() - process.getStartTime()) * numStoreUni)
                  + "ms\" repeatCount=\"0\"/>\n");
        }
        continue;
      }
      if (process.getStartX() != process.getEndX()) {
        if (type.equals("rect")) {
          output.append(startingStringValue).append("x\" from=\"").append(process.getStartX()
                  - this.x)
                  .append("\" to=\"").append(process.getEndX()
                  - this.x).append(endingStringValue);
        } else {
          output.append(startingStringValue).append("cx\" from=\"")
                  .append(process.getStartX() - this.x + process.getStartWidth() / 2)
                  .append("\" to=\"")
                  .append(process.getEndX() - this.x + process.getEndWidth() / 2)
                  .append(endingStringValue);
        }
      }
      if (process.getStartY() != process.getEndY()) {
        if (type.equals("rect")) {
          output.append(startingStringValue).append("y\" from=\"")
                  .append(process.getStartY() - this.y)
                  .append("\" to=\"")
                  .append(process.getEndY() - this.y)
                  .append(endingStringValue);
        } else {
          output.append(startingStringValue).append("cy\" from=\"")
                  .append(process.getStartY() - this.y + process.getStartHeight() / 2)
                  .append("\" to=\"")
                  .append(process.getEndY() - this.y + process.getEndHeight() / 2)
                  .append(endingStringValue);
        }
      }
      if (process.getStartHeight() != process.getEndHeight()) {
        if (type.equals("rect")) {
          output.append(startingStringValue).append("height\" from=\"")
                  .append(process.getStartHeight())
                  .append("\" to=\"").append(process.getEndHeight())
                  .append(endingStringValue);
        } else {
          output.append(startingStringValue).append("ry\" from=\"")
                  .append(process.getStartHeight() / 2)
                  .append("\" to=\"").append(process.getEndHeight() / 2)
                  .append(endingStringValue);
        }
      }
      if (process.getStartWidth() != process.getEndWidth()) {
        if (type.equals("rect")) {
          output.append(startingStringValue).append("width\" from=\"")
                  .append(process.getStartWidth())
                  .append("\" to=\"").append(process.getEndWidth())
                  .append(endingStringValue);
        } else {
          output.append(startingStringValue).append("ry\" from=\"")
                  .append(process.getStartWidth() / 2)
                  .append("\" to=\"").append(process.getEndWidth() / 2)
                  .append(endingStringValue);
        }
      }
      if (process.getStartColor().getRed() != process.getEndColor().getRed()
              || process.getStartColor().getGreen() != process.getEndColor().getGreen()
              || process.getStartColor().getBlue() != process.getEndColor().getBlue()) {
        output.append(startingStringValue).append("fill\" from=\"rgb(")
                .append(process.getStartColor().getRed()).append(",")
                .append(process.getStartColor().getGreen()).append(",")
                .append(process.getStartColor().getBlue()).append(")\" to=\"rgb(")
                .append(process.getEndColor().getRed()).append(",")
                .append(process.getEndColor().getGreen()).append(",")
                .append(process.getEndColor().getBlue()).append(")").append(endingStringValue);
      }
    }
    return output.toString();
  }

  /**
   * Method that will build a string and intializes shape.
   */
  private String getStartState(InterfaceInterpretProcess process, String type) {
    StringBuilder output = new StringBuilder();
    switch (type) {
      case "rect":
        output = new StringBuilder("\" x=\"").append(process.getStartX() - this.x)
                .append("\" y=\"").append(process.getStartY() - this.y).append("\" width=\"")
                .append(process.getStartWidth()).append("\" height=\"")
                .append(process.getStartHeight())
                .append("\" fill=\"rgb(").append(process.getStartColor().getRed()).append(",")
                .append(process.getStartColor().getGreen()).append(",")
                .append(process.getStartColor().getBlue())
                .append(")\" visibility=\"visible\" >\n");
        break;
      case "ellipse":
        output = new StringBuilder("\" cx=\"")
                .append(process.getStartX() - this.x + process.getStartWidth() / 2)
                .append("\" cy=\"")
                .append(process.getStartY() - this.y + process.getStartHeight() / 2)
                .append("\" rx=\"")
                .append(process.getStartWidth() / 2).append("\" ry=\"")
                .append(process.getStartHeight() / 2)
                .append("\" fill=\"rgb(").append(process.getStartColor().getRed()).append(",")
                .append(process.getStartColor().getGreen()).append(",")
                .append(process.getStartColor().getBlue())
                .append(")\" visibility=\"visible\" >\n");
        break;
      default:
        return output.toString();
    }
    return output.toString();
  }

  /**
   * Retrieves the texts that represents the animation.
   */
  @Override
  public String getText() {
    return this.outputInfo.toString();
  }

}
