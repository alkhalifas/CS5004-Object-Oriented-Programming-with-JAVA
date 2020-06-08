package cs5004.animator.view;

import java.util.Timer;
import java.util.List;
import java.util.TimerTask;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import cs5004.animator.model.InterfaceAniModel;
import cs5004.animator.model.InterfaceInterpretProcess;
import cs5004.animator.model.InterfaceInterpretStatusProcess;
import cs5004.animator.model.InterfaceInterpretShape;

/**
 * This class represents a visual view that displays the animation.
 */
public class VisualAnimationView extends JFrame implements InterfaceView {
  private InterfaceAniModel model;
  private Timer timer;
  private AbstractDrawPanel panel;
  private final int tempo;

  /**
   * This constructor creates a GUIAnimation view.
   *
   * @param model the incoming model used.
   * @param ticksPS is the tickRate.
   */
  public VisualAnimationView(InterfaceAniModel model, int ticksPS) {
    super();

    if (model == null) {
      throw new IllegalArgumentException("------ Error: The mode must not be null.");
    }
    if (ticksPS <= 0) {
      throw new IllegalArgumentException("------ Error: The ticks per second must be positive.");
    }

    this.model = model;
    this.tempo = ticksPS;

    this.panel = new DrawPanel();
    this.panel.setPreferredSize(this.getNeededSpace());

    JScrollPane scrollPane = new JScrollPane(this.panel);

    this.setSize(this.model.getWidth(), this.model.getHeight());

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.add(scrollPane);

    this.timer = new Timer();
  }

  /**
   * Plays the animation using a selected type of view.
   */
  @Override
  public void play() {
    this.setVisible(true);
    this.timer.schedule(new DrawFrameTask(this.model.getLastTick()), 0, 1000 / this.tempo);
  }

  /**
   * Returns the length adn height for the background.
   */
  private Dimension getNeededSpace() {
    int outputWidth = 0;
    int outputHeight = 0;
    for (List<InterfaceInterpretStatusProcess> processList : this.model.getProcesses().values()) {
      outputWidth = Math.max(outputWidth,
              processList.get(0).getStartX() + processList.get(0).getStartWidth());
      outputHeight = Math.max(outputHeight,
              processList.get(0).getStartY() + processList.get(0).getStartHeight());

      for (InterfaceInterpretProcess process : processList) {
        outputWidth = Math.max(outputWidth, process.getEndX() + process.getEndWidth());
        outputHeight = Math.max(outputHeight, process.getEndY() + process.getEndHeight());
      }
    }
    return new Dimension(outputWidth, outputHeight);
  }

  /**
   * A helpder method that updates this visual view at each tick of the animation.
   */
  private class DrawFrameTask extends TimerTask {
    private int tick = 0;
    private final int lastTickNum;

    private DrawFrameTask(int lastTickNum) {
      super();
      this.lastTickNum = lastTickNum;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
      if (this.tick >= this.lastTickNum) {
        timer.cancel();
      }
      List<InterfaceInterpretShape> shapesAtTick = model.getState(this.tick);
      panel.draw(shapesAtTick);
      this.tick ++;
    }
  }
}