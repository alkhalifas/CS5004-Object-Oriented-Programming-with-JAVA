package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cs5004.animator.model.InterfaceAniModel;
import cs5004.animator.model.InterfacePlayBack;
import cs5004.animator.model.InterfaceInterpretStatusProcess;
import cs5004.animator.model.InterfaceInterpretShape;
import cs5004.animator.model.SuperGeneralProcess;
import cs5004.animator.view.InterfacePlaybackView;
import cs5004.animator.view.InterfaceFrameChangeEvent;
import cs5004.animator.view.InterfaceFrameChangeListener;
import cs5004.animator.view.InterfaceShapeChangeEvent;
import cs5004.animator.view.InterfaceShapeChangeListener;
import cs5004.animator.view.InterfaceTextView;
import cs5004.animator.view.SVGAnimationView;

import static java.util.Objects.requireNonNull;

/**
 * This class represents the controller that implements the main playback view.
 */
public class Controller implements InterfaceController, ActionListener,
        InterfaceFrameChangeListener,
        InterfaceShapeChangeListener, PropertyChangeListener {

  private InterfacePlayBack playbackBuilder;
  private InterfacePlaybackView playbackView;

  private Timer timer;
  private int currentTickNum;
  private int ticksPS = 20;
  private int firstTickNum;
  private int lastTickNum;

  private boolean animationPaused = true;
  private boolean animationLooped = false;

  /**
   * This constructor constructs the controller. It will utilize the playbackBuilder of the ani,
   * a view type, as well as a rate of ticks per second from the user as arguments.
   *
   * @param playbackBuilder which is a playbackBuilder for this view and animation.
   * @param view which is the view for this constructor to show to the user to interact with.
   * @param ticksPS which is the starting speed for this animation.
   */
  public Controller(InterfacePlayBack playbackBuilder, InterfacePlaybackView view, int ticksPS) {
    this.playbackBuilder = requireNonNull(playbackBuilder);
    this.playbackView = requireNonNull(view);
    this.ticksPS = ticksPS;
    this.firstTickNum = this.getFirstTick();
    this.lastTickNum = playbackBuilder.build().getLastTick();
    this.currentTickNum = this.firstTickNum;
  }

  /**
   * A void method that will start the view and creates the animation with the given input.
   */
  public void play() {
    InterfaceAniModel model = this.playbackBuilder.build();
    if (this.animationPaused && this.currentTickNum
            < model.getLastTick()) {
      this.firstTickNum = this.getFirstTick();
      this.timer = new Timer();
      this.timer.schedule(new DrawFrameTask(model), 0, 1000 / this.ticksPS);
      animationPaused = false;
    }
  }

  /**
   * A method using action listener for changes in key frame. This is the functionalty that supports
   * the user to change key frames. TODO: Keep working on this for part3. Not working yet.
   *
   * @param event which is the event of the change by the user to be sent to the controller.
   */
  @Override
  public void keyframeChanged(InterfaceFrameChangeEvent event) {
    List<InterfaceInterpretStatusProcess> processes = playbackBuilder.getProcesses()
            .get(event.getId());
    if (event.getTime() < 0) {
      playbackView.displayError("The ticks must be positive!");
      return;
    }
    switch (event.getType()) {
      case ADD:
        if (processes == null) {
          try {
            playbackBuilder.addMotion(event.getId(), event.getTime(), event.getX(), event.getY(),
                    event.getWidth(), event.getHeight(), event.getShapeRotation(),
                    event.getColor().getRed(), event.getColor().getGreen(),
                    event.getColor().getBlue(), event.getTime(), event.getX(), event.getY(),
                    event.getWidth(), event.getHeight(), event.getShapeRotation(),
                    event.getColor().getRed(), event.getColor().getGreen(),
                    event.getColor().getBlue());
            this.playbackView.setKeyframes(this.convertToKeyFrames(this.playbackBuilder
                    .getProcesses()));
            lastTickNum = playbackBuilder.build().getLastTick();
          }
          catch (IllegalArgumentException e) {
            playbackView.displayError(e.getMessage());
          }
          return;
        }
        if (timeExists(event.getTime(), processes)) {
          playbackView.displayError("This keyframe already exists.");
          return;
        }
        if (event.getTime() < processes.get(0).getStartTime()) {
          try {
            playbackBuilder.addMotion(
                    event.getId(),
                    event.getTime(),
                    event.getX(),
                    event.getY(),
                    event.getWidth(),
                    event.getHeight(),
                    event.getShapeRotation(),
                    event.getColor().getRed(),
                    event.getColor().getGreen(),
                    event.getColor().getBlue(),
                    processes.get(0).getStartTime(),
                    processes.get(0).getStartX(),
                    processes.get(0).getStartY(),
                    processes.get(0).getStartWidth(),
                    processes.get(0).getStartHeight(),
                    processes.get(0).getStartRotationDegree(),
                    processes.get(0).getStartColor().getRed(),
                    processes.get(0).getStartColor().getGreen(),
                    processes.get(0).getStartColor().getBlue());

            playbackView.setKeyframes(convertToKeyFrames(playbackBuilder.getProcesses()));
            lastTickNum = playbackBuilder.build().getLastTick();
          }

          catch (IllegalArgumentException e) {
            playbackView.displayError(e.getMessage());
          }
          return;
        }
        else if (event.getTime() > processes.get(processes.size() - 1).getEndTime()) {
          InterfaceInterpretStatusProcess temp = processes.get(processes.size() - 1);
          try {
            playbackBuilder.addMotion(event.getId(), temp.getEndTime(),
                    temp.getEndX(), temp.getEndY(), temp.getEndWidth(),
                    temp.getEndHeight(), temp.getEndRotationDegree(), temp.getEndColor().getRed(),
                    temp.getEndColor().getGreen(), temp.getEndColor().getBlue(),
                    event.getTime(), event.getX(), event.getY(),
                    event.getWidth(), event.getHeight(), event.getShapeRotation(),
                    event.getColor().getRed(), event.getColor().getGreen(),
                    event.getColor().getBlue());
            if (temp.getStartTime() == temp.getEndTime()) {
              this.playbackBuilder.removeProcess(event.getId(), temp.getStartTime());
            }
            playbackView.setKeyframes(convertToKeyFrames(playbackBuilder.getProcesses()));
            lastTickNum = playbackBuilder.build().getLastTick();
          }
          catch (IllegalArgumentException e) {
            playbackView.displayError(e.getMessage());
          }
          return;
        }
        else {
          for (InterfaceInterpretStatusProcess process : processes) {
            if (event.getTime() == process.getStartTime()
                    || event.getTime() == process.getEndTime()) {
              this.playbackView.displayError("There is already a keyframe at this tick.");
              return;
            }
            if ( event.getTime() > process.getStartTime()
                    && event.getTime() < process.getEndTime()) {
              try {
                playbackBuilder.removeProcess(event.getId(), process.getStartTime());
                playbackBuilder.addMotion(event.getId(), process.getStartTime(),
                        process.getStartX(), process.getStartY(), process.getStartWidth(),
                        process.getStartHeight(), process.getStartRotationDegree(),
                        process.getStartColor().getRed(), process.getStartColor().getGreen(),
                        process.getStartColor().getBlue(), event.getTime(), event.getX(),
                        event.getY(), event.getWidth(), event.getHeight(),
                        event.getShapeRotation(),
                        event.getColor().getRed(), event.getColor().getGreen(),
                        event.getColor().getBlue());
                playbackBuilder.addMotion(event.getId(), event.getTime(), event.getX(),
                        event.getY(),
                        event.getWidth(), event.getHeight(), event.getShapeRotation(),
                        event.getColor().getRed(), event.getColor().getGreen(),
                        event.getColor().getBlue(), process.getEndTime(), process.getEndX(),
                        process.getEndY(), process.getEndWidth(), process.getEndHeight(),
                        process.getEndRotationDegree(), process.getEndColor().getRed(),
                        process.getEndColor().getGreen(), process.getEndColor().getBlue());
                playbackView.setKeyframes(convertToKeyFrames(playbackBuilder.getProcesses()));
                lastTickNum = playbackBuilder.build().getLastTick();
              }
              catch (IllegalArgumentException e) {
                playbackView.displayError(e.getMessage());
              }
              return;
            }
          }
        }
        return;
      case EDIT:
        if (!timeExists(event.getTime(), processes)) {
          playbackView.displayError("Keyframe does not exist at this time.");
          return;
        }

        if (event.getTime() == processes.get(0).getStartTime()) {
          try {
            playbackBuilder.removeProcess(event.getId(), event.getTime());
            playbackBuilder.addMotion(event.getId(), event.getTime(), event.getX(), event.getY(),
                    event.getWidth(), event.getHeight(), event.getShapeRotation(),
                    event.getColor().getRed(), event.getColor().getGreen(),
                    event.getColor().getBlue(), processes.get(0).getEndTime(),
                    processes.get(0).getEndX(), processes.get(0).getEndY(),
                    processes.get(0).getEndWidth(), processes.get(0).getEndHeight(),
                    processes.get(0).getEndRotationDegree(), processes.get(0).getEndColor()
                            .getRed(),
                    processes.get(0).getEndColor().getGreen(),
                    processes.get(0).getEndColor().getBlue());
            playbackView.setKeyframes(convertToKeyFrames(playbackBuilder.getProcesses()));
          } catch (IllegalArgumentException e) {
            playbackView.displayError(e.getMessage());
          }
        }
        else if (event.getTime() == processes.get(processes.size() - 1).getEndTime()) {
          try {
            playbackBuilder.removeProcess(event.getId(), event.getTime());

            InterfaceInterpretStatusProcess lastProcess = processes.get(processes.size() - 1);
            playbackBuilder.addMotion(event.getId(),
                    lastProcess.getStartTime(),
                    lastProcess.getStartX(),
                    lastProcess.getStartY(),
                    lastProcess.getStartWidth(),
                    lastProcess.getStartHeight(),
                    lastProcess.getStartRotationDegree(),
                    lastProcess.getStartColor().getRed(),
                    lastProcess.getStartColor().getGreen(),
                    lastProcess.getStartColor().getBlue(), event.getTime(),
                    event.getX(), event.getY(),
                    event.getWidth(), event.getHeight(),
                    event.getShapeRotation(), event.getColor().getRed(),
                    event.getColor().getGreen(), event.getColor().getBlue());
            playbackView.setKeyframes(convertToKeyFrames(playbackBuilder.getProcesses()));
          }
          catch (IllegalArgumentException e) {
            playbackView.displayError(e.getMessage());
          }
        }

        for (int i = 1; i < processes.size(); i++) {
          if (event.getTime() == processes.get(i).getStartTime()) {
            try {
              playbackBuilder.removeProcess(event.getId(), processes.get(i).getStartTime());
              playbackBuilder.removeProcess(event.getId(), processes.get(i - 1).getStartTime());
              playbackBuilder.addMotion(event.getId(), processes.get(i - 1).getStartTime(),
                      processes.get(i - 1).getStartX(),
                      processes.get(i - 1).getStartY(), processes.get(i - 1).getStartWidth(),
                      processes.get(i - 1).getStartHeight(),
                      processes.get(i - 1).getStartRotationDegree(),
                      processes.get(i - 1).getStartColor().getRed(),
                      processes.get(i - 1).getStartColor().getGreen(),
                      processes.get(i - 1).getStartColor().getBlue(),
                      event.getTime(), event.getX(), event.getY(),
                      event.getWidth(), event.getHeight(), event.getShapeRotation(),
                      event.getColor().getRed(), event.getColor().getGreen(),
                      event.getColor().getBlue());
              playbackBuilder.addMotion(event.getId(),
                      event.getTime(), event.getX(), event.getY(),
                      event.getWidth(), event.getHeight(), event.getShapeRotation(),
                      event.getColor().getRed(), event.getColor().getGreen(),
                      event.getColor().getBlue(), processes.get(i).getEndTime(),
                      processes.get(i).getEndX(), processes.get(i).getEndY(),
                      processes.get(i).getEndWidth(), processes.get(i).getEndHeight(),
                      processes.get(i).getEndRotationDegree(), processes.get(i).getEndColor()
                              .getRed(),
                      processes.get(i).getEndColor().getGreen(),
                      processes.get(i).getEndColor().getBlue());
              playbackView.setKeyframes(convertToKeyFrames(playbackBuilder.getProcesses()));
            }
            catch (IllegalArgumentException e) {
              playbackView.displayError(e.getMessage());
            }
          }
        }
        return;
      case DELETE:
        if (!timeExists(event.getTime(), processes)) {
          playbackView.displayError("Keyframe does not exist at this time.");
          return;
        }
        if (event.getTime() == processes.get(0).getStartTime()
                || (event.getTime() == processes.get(processes.size() - 1).getEndTime())) {
          try {
            playbackBuilder.removeProcess(event.getId(), event.getTime());
            playbackView.setKeyframes(convertToKeyFrames(playbackBuilder.getProcesses()));
          }
          catch (IllegalArgumentException e) {
            playbackView.displayError(e.getMessage());
          }
        }
        else {
          for (int i = 0; i < processes.size(); i++) {
            if (event.getTime() == processes.get(i).getStartTime()) {
              SuperGeneralProcess process = combine(processes.get(i - 1), processes.get(i));
              try {
                playbackBuilder.removeProcess(event.getId(), event.getTime());
                playbackBuilder.removeProcess(event.getId(), processes.get(i - 1).getStartTime());
                playbackBuilder.addMotion(event.getId(), process.getStartTime(),
                        process.getStartX(),
                        process.getStartY(), process.getStartWidth(), process.getStartHeight(),
                        process.getStartRotationDegree(),
                        process.getStartColor().getRed(), process.getStartColor().getGreen(),
                        process.getStartColor().getBlue(), process.getEndTime(), process.getEndX(),
                        process.getEndY(), process.getEndWidth(), process.getEndHeight(),
                        process.getEndRotationDegree(),
                        process.getEndColor().getRed(), process.getEndColor().getGreen(),
                        process.getEndColor().getBlue());
                playbackView.setKeyframes(convertToKeyFrames(playbackBuilder.getProcesses()));
              }
              catch (IllegalArgumentException e) {
                playbackView.displayError(e.getMessage());
              }
            }
          }
        }
        lastTickNum = playbackBuilder.build().getLastTick();
        return;
      default:
    }
  }

  // takes the starting values of one process and the ending values of a second to make one new
  // process.
  private SuperGeneralProcess combine(InterfaceInterpretStatusProcess process1,
                                      InterfaceInterpretStatusProcess process2) {
    return new SuperGeneralProcess(process1.getType(), process1.getStartTime(),
            process1.getStartX(),
            process1.getStartY(), process1.getStartWidth(), process1.getStartHeight(),
            process1.getStartRotationDegree(),
            process1.getStartColor().getRed(), process1.getStartColor().getGreen(),
            process1.getStartColor().getBlue(), process2.getEndTime(), process2.getEndX(),
            process2.getEndY(), process2.getEndWidth(), process2.getEndHeight(),
            process2.getEndRotationDegree(),
            process2.getEndColor().getRed(), process2.getEndColor().getGreen(),
            process2.getEndColor().getBlue());
  }


  // Checks if any process starts with the given time, or the last process ends on this time.
  private boolean timeExists(int time, List<InterfaceInterpretStatusProcess> list) {
    if (list == null) {
      return false;
    }
    else if (time == list.get(list.size() - 1).getEndTime()) {
      return true;
    }
    for (InterfaceInterpretStatusProcess process : list) {
      if (time == process.getStartTime()) {
        return true;
      }
    }
    return false;
  }

  /**
   * The action listener method for a SHAPE CHANGE event that allows a user to add or remove a
   * shape from an anmiation. If a shape is deleted, delete its associated processes and
   * send an updated list of shapes and keyframes. If any bad fields are sent with the process
   * call a display error method in the view to show to the user that the given operation is not
   * permitted.
   * @param event is the event created by and sent by the view to this controller to allow
   *              for communication between the two.
   */
  @Override
  public void shapeChanged(InterfaceShapeChangeEvent event) {
    switch (event.getChangeType()) {
      case ADD:
        if (playbackBuilder.getShapes().get(event.getId()) != null) {
          playbackView.displayError("Shape already exists with this id");
          return;
        }
        try {
          playbackBuilder.declareShape(event.getId(), event.getShapeType());
          playbackView.setShapes(playbackBuilder.getShapes());
          playbackView.setKeyframes(this.convertToKeyFrames(this.playbackBuilder.getProcesses()));
        }
        catch (IllegalArgumentException e) {
          playbackView.displayError(e.getMessage());
        }
        return;
      case DELETE:
        if (playbackBuilder.getShapes().get(event.getId()) == null) {
          playbackView.displayError("This shape cannot be deleted as the id does not exist");
          return;
        }
        try {
          playbackBuilder.removeShape(event.getId());
          playbackView.setShapes(playbackBuilder.getShapes());
          playbackView.setKeyframes(this.convertToKeyFrames(this.playbackBuilder.getProcesses()));
          lastTickNum = playbackBuilder.build().getLastTick();
        }
        catch (IllegalArgumentException e) {
          playbackView.displayError(e.getMessage());
        }
        return;
      default:
    }
  }

  /**
   * Invoked when various buttons are pressed from the view, allowing the user to play, pause,
   * loop, and restart the current animation.
   * @param event is the event created by and sent by the view.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case "START" :
        this.play();
        return;
      case "PAUSE" :
        if (!animationPaused) {
          animationPaused = true;
        }
        return;
      case "LOOP" :
        animationLooped = !animationLooped;
        return;
      case "RESTART":
        currentTickNum = firstTickNum;
        play();
        break;
      default:
        // no valid process received.
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    switch (event.getPropertyName()) {
      case "TICK SPEED":
        int speed;
        try {
          speed = Integer.parseInt(event.getNewValue().toString());
        }
        catch (NumberFormatException e) {
          playbackView.displayError("Not a number.");
          return;
        }
        if (Integer.parseInt(event.getNewValue().toString()) < 1) {
          playbackView.displayError("Cannot pass a negative number.");
          return;
        }

        ticksPS = speed;
        if (!animationPaused) {
          this.timer.cancel();
          this.timer = new Timer();
          this.timer.schedule(new DrawFrameTask(this.playbackBuilder.build()), 0,
                  1000 / this.ticksPS);
        }
        return;
      case "EXPORT":
        InterfaceTextView svg = new SVGAnimationView(playbackBuilder.build(), ticksPS);
        svg.play();
        try {
          FileWriter writer = new FileWriter("./resources/" + event.getNewValue().toString()
                  + ".svg");
          writer.append(svg.getText());
          writer.close();
        }
        catch (IOException e) {
          playbackView.displayError("Error occurred when trying to create a file.");
        }
        return;
      case "SLIDER" :
        currentTickNum = (int) ((Double.parseDouble(event.getNewValue().toString()) / 100.0)
                * (double) (lastTickNum - this.firstTickNum)) + firstTickNum;
        if (!animationPaused) {
          animationPaused = true;
        }
        playbackView.display(playbackBuilder.build().getState(currentTickNum));
        return;
      default:
    }
  }


  @Override
  public void start() {
    this.playbackView.setKeyframes(this.convertToKeyFrames(this.playbackBuilder.getProcesses()));
    this.playbackView.setShapes(this.playbackBuilder.getShapes());
    this.playbackView.addButtonListener(this);
    this.playbackView.addFrameChangeListener(this);
    this.playbackView.addShapeChangeListener(this);
    this.playbackView.addPropertyListener(this);
    this.playbackView.setWidth((int) this.playbackBuilder.getNeededSpace().getWidth());
    this.playbackView.setHeight((int) this.playbackBuilder.getNeededSpace().getHeight());
    this.playbackView.play();
    this.playbackView.display(this.playbackBuilder.build().getState(this.firstTickNum));
  }

  /**
   * A {@link TimerTask} that updates this visual view at each tick of the animation.
   */
  private class DrawFrameTask extends TimerTask {
    private final int lastTickNum;
    private final InterfaceAniModel model;

    // takes a final tick at which to stop the animation.
    private DrawFrameTask(InterfaceAniModel model) {
      super();
      this.lastTickNum = model.getLastTick();
      this.model = model;
    }

    /**
     * The action to be performed by this timer task, changing the states of each shape based
     * on its key frames and calls the display method on the view. It also checks if the looped
     * field and the animationPaused field are true and either loop the animation or just end it
     * the final tick.
     */
    @Override
    public void run() {
      if (animationPaused) {
        timer.cancel();
      }
      else if (currentTickNum >= this.lastTickNum) {
        if (animationLooped) {
          currentTickNum = firstTickNum;
        }
        else {
          timer.cancel();
          animationPaused = true;
        }
      }
      List<InterfaceInterpretShape> shapesAtTick = this.model.getState(currentTickNum);
      playbackView.display(shapesAtTick);
      playbackView.setSlider((double) currentTickNum / (double) (lastTickNum - firstTickNum));
      currentTickNum++;
    }
  }

  // Converts a list of processes into a list of keyframes.
  private Map<String, List<InterpretStatusKeyFrame>> convertToKeyFrames(Map<String,
          List<InterfaceInterpretStatusProcess>> map) {
    Map<String, List<InterpretStatusKeyFrame>> output = new LinkedHashMap<>();

    for (Map.Entry<String, List<InterfaceInterpretStatusProcess>> entry : map.entrySet()) {
      ArrayList<InterpretStatusKeyFrame> temp = new ArrayList<>();
      if (map.get(entry.getKey()) == null)  {
        output.put(entry.getKey(), new ArrayList<>());
        continue;
      }

      for (int i = 0; i < entry.getValue().size(); i++) {
        temp.add(this.convertToKeyFrame(entry.getValue().get(i)));
      }
      InterfaceInterpretStatusProcess last = entry.getValue().get(entry.getValue().size() - 1);

      if (last.getStartTime() != last.getEndTime()) {
        temp.add(new StatusKeyFrame(last.getEndTime(), last.getEndX(), last.getEndY(),
                last.getEndWidth(), last.getEndHeight(), last.getEndRotationDegree(),
                last.getEndColor()));
      }
      output.put(entry.getKey(), temp);
    }
    return output;
  }

  // converts a process to keyframes using its starting values.
  private InterpretStatusKeyFrame convertToKeyFrame(InterfaceInterpretStatusProcess process) {
    return new StatusKeyFrame(process.getStartTime(), process.getStartX(), process.getStartY(),
            process.getStartWidth(), process.getStartHeight(), process.getStartRotationDegree(),
            process.getStartColor());
  }

  // Gets the first tick of the animation and starts it at that. If there
  // are no processes then set tick to 0;
  private int getFirstTick() {
    Map<String, List<InterfaceInterpretStatusProcess>> processes = this.playbackBuilder
            .getProcesses();
    int firstTickNum = Integer.MAX_VALUE;

    for (String id : processes.keySet()) {
      if (!processes.get(id).isEmpty()) {
        firstTickNum = Math.min(firstTickNum, processes.get(id).get(0).getStartTime());
      }
    }
    if (firstTickNum == Integer.MAX_VALUE) {
      return 0;
    }
    return firstTickNum;
  }
}