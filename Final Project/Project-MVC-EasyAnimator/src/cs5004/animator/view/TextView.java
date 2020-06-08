package cs5004.animator.view;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.InterfaceAniModel;
import cs5004.animator.model.InterfaceInterpretStatusProcess;
import cs5004.animator.model.InterfaceInterpretShape;

/**
 * Class that represents a text description of the given animation.
 */
public class TextView implements InterfaceTextView {
  private Appendable outputTemp;
  private InterfaceAniModel model;

  /**
   * A constructor that constructs a TextView by creating a long string.
   *
   * @param model is the model for which the TextView will create a constructor for
   */
  public TextView(InterfaceAniModel model) {
    this.model = model;
    outputTemp = new StringWriter();
  }

  /**
   * Retrieves the texts that represents the animation.
   */
  @Override
  public String getText() {
    return this.outputTemp.toString();
  }

  /**
   * Plays the animation using a selected type of view.
   */
  @Override
  public void play() {

    StringBuilder output = new StringBuilder();
    for (Map.Entry<String,
            List<InterfaceInterpretStatusProcess>> entry : this.model.getProcesses().entrySet()) {
      InterfaceInterpretShape shape = this.model.getShapes().get(entry.getKey());
      output.append("Create ").append(shape.getShapeType()).append(" ")
              .append(entry.getKey()).append(" ")
              .append("with center at ").append(shape.getPosition()).append(", width of ")
              .append(shape.getWidth()).append(", height of ")
              .append(shape.getHeight()).append(".")
              .append("\n");

      for (InterfaceInterpretStatusProcess process : entry.getValue()) {
        StringBuilder temp = new StringBuilder().append("Transform ");
        temp.append(entry.getKey()).append(" from location (")
                .append(process.getStartX()).append(", ")
                .append(process.getStartY()).append(") to (")
                .append(process.getEndX()).append(", ")
                .append(process.getEndY()).append(") and color ")
                .append(process.getStartColor()).append(" to ")
                .append(process.getEndColor()).append(" over t=")
                .append(process.getStartTime()).append(" to ")
                .append(process.getEndTime()).append(".")

                .append("\n");
        output.append(temp);
      }
      output.append("\n");
    }


    if (output.length() != 0) {
      output.delete(output.length() - 2, output.length());
    }
    outputTemp = output;
  }
}
