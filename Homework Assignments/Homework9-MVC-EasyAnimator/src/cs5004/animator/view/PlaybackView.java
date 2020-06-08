package cs5004.animator.view;

import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs5004.animator.controller.InterpretStatusKeyFrame;
import cs5004.animator.controller.StatusKeyFrame;
import cs5004.animator.model.InterfaceInterpretShape;

/**
 * This PlaybackView class is the GUI that will act as the fourth view for the animation. This will
 * allow users to scroll through the animation, select specific frames, select speed, play, pause,
 * restart, loop, and export to SVG.
 */
public class PlaybackView extends JFrame implements InterfacePlaybackView, ListSelectionListener,
        ActionListener, ChangeListener {

  private AbstractDrawPanel drawingPanel;
  private JPanel toolbar;
  private JPanel shapesEditor;
  private JPanel framesEditor;
  private JSlider sliderFunction;

  private JTextField tickSpeedField;
  private JList<InterfaceShapeCell> shapeListContainer;
  private JLabel frameListLabel;
  private JList<InterpretStatusKeyFrame> frameListContainer;
  private DefaultListModel<InterfaceShapeCell> shapesList;
  private DefaultListModel<InterpretStatusKeyFrame> framesList;
  private Map<String, List<InterpretStatusKeyFrame>> keyframes;

  private List<ActionListener> buttonListeners;
  private List<PropertyChangeListener> tickSpeedListeners;

  private int width = 1000;
  private int height = 600;
  private int shapeSelected = -1;
  private int frameSelected = -1;
  private InterfaceViewInputHandler handler;
  private boolean sliderPauser = true;

  /**
   * This constructor constructs the playback view which creates an enviroment similar to the visual
   * view. The user is able to interact with the view.
   *
   * @param ticksPS the starting speed of the animation in ticks per second
   */
  public PlaybackView(int ticksPS) {
    super();

    this.tickSpeedField = new JTextField("" + ticksPS);

    this.handler = new InputHandler();
    this.buttonListeners = new ArrayList<>();
    this.tickSpeedListeners = new ArrayList<>();
    this.keyframes = new LinkedHashMap<>();

    this.shapesList = new DefaultListModel<>();
    this.framesList = new DefaultListModel<>();

    this.setLayout(new BorderLayout());

    this.drawingPanel = new DrawPanel();
    this.drawingPanel.setPreferredSize(new Dimension(this.width, this.height));

    JScrollPane scrollPanel = new JScrollPane(this.drawingPanel);
    this.addBorder(scrollPanel);

    sliderFunction = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    sliderFunction.addChangeListener(this);
    sliderFunction.setPreferredSize(new Dimension(200, 50));
    JPanel display = new JPanel(new BorderLayout());
    display.add(sliderFunction, BorderLayout.SOUTH);
    display.add(scrollPanel, BorderLayout.CENTER);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.add(display, BorderLayout.CENTER);

    this.createShapesEditor();
    this.add(this.shapesEditor, BorderLayout.WEST);

    this.createFramesEditor();
    this.add(this.framesEditor, BorderLayout.EAST);

    this.createToolbar();
    this.add(this.toolbar, BorderLayout.SOUTH);

    this.setSize(this.width + 500, this.height + 50);
    //this.setBackground(Color.blue);

  }

  /**
   * Method that paints borders.
   */
  private void addBorder(@NotNull JComponent comp) {
    comp.setBorder(BorderFactory.createLineBorder(Color.black));
  }


  /**
   * Method that creates a toolbar at the bottom that supports the Play, Restart, and Pause
   * buttons.
   */
  private void createToolbar() {

    // Play Button
    JButton play = new JButton("PLAY");
    play.setActionCommand("PLAY");
    play.addActionListener(this);

    // Pause Button
    JButton pause = new JButton("PAUSE");
    pause.setActionCommand("PAUSE");
    pause.addActionListener(this);


    JPanel playPause = new JPanel();
    playPause.add(play);
    playPause.add(pause);

    // Loop Button
    JLabel loop = new JLabel("LOOP:");
    JCheckBox loopToggle = new JCheckBox();
    loopToggle.setActionCommand("LOOP");

    loopToggle.addActionListener(this);
    JPanel loopPanel = new JPanel();
    loopPanel.add(loop);
    loopPanel.add(loopToggle);

    // Restart Button
    JButton restart = new JButton("RESTART");
    restart.setActionCommand("RESTART");
    restart.addActionListener(this);

    // Export to SVG
    //JButton export = new JButton("EXPORT TO SVG");
    //export.setActionCommand("EXPORT");
    //export.addActionListener(this);

    // Set Ticks Per Second Speed Field
    JLabel speed = new JLabel("SET SPEED:");
    this.tickSpeedField.setPreferredSize(new Dimension(34, 26));
    this.tickSpeedField.setActionCommand("TICK SPEED");
    this.tickSpeedField.addActionListener(this);
    JLabel tickPerSec = new JLabel("Ticks/Second");
    JPanel tickPanel = new JPanel();
    tickPanel.add(speed);
    tickPanel.add(this.tickSpeedField);
    tickPanel.add(tickPerSec);


    this.toolbar = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10));
    this.toolbar.add(tickPanel);
    this.toolbar.add(playPause);
    this.toolbar.add(loopPanel);
    this.toolbar.add(restart);
    //this.toolbar.add(export);
    this.addBorder(this.toolbar);
  }

  private void createShapesEditor() {
    JLabel title = new JLabel("Shapes Menu", SwingConstants.CENTER);
    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
    title.setPreferredSize(new Dimension(254, 60));
    this.addBorder(title);

    this.shapeListContainer = new JList<>(this.shapesList);
    this.shapeListContainer.addListSelectionListener(this);
    JScrollPane scrollieShapes = new JScrollPane(this.shapeListContainer);
    this.addBorder(scrollieShapes);

    //JButton add = new JButton("ADD SHAPE");
    //add.setActionCommand("SHAPE ADD");
    //add.addActionListener(this);
    //JButton delete = new JButton("delete");
    //delete.setActionCommand("shape delete");
    //delete.addActionListener(this);
    //JPanel buttons = new JPanel();
    //buttons.add(add);
    //buttons.add(delete);
    //this.addBorder(buttons);

    this.shapesEditor = new JPanel(new BorderLayout());
    this.shapesEditor.add(title, BorderLayout.NORTH);
    this.shapesEditor.add(scrollieShapes, BorderLayout.CENTER);
    //this.shapesEditor.add(buttons, BorderLayout.SOUTH);
    this.shapesEditor.setPreferredSize(new Dimension(254, 82));
  }

  private void createFramesEditor() {
    JLabel title = new JLabel("Event List", SwingConstants.CENTER);
    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
    title.setPreferredSize(new Dimension(254, 60));
    this.addBorder(title);

    this.frameListContainer = new JList<>(this.framesList);
    this.frameListContainer.addListSelectionListener(this);
    JScrollPane scrollieFrames = new JScrollPane(this.frameListContainer);
    this.addBorder(scrollieFrames);
    this.frameListLabel = new JLabel("Select a Shape from Shapes Menu", SwingConstants.CENTER);
    this.frameListLabel.setPreferredSize(new Dimension(254, 40));
    this.addBorder(this.frameListLabel);
    JPanel frameListTitled = new JPanel(new BorderLayout());
    frameListTitled.add(scrollieFrames, BorderLayout.CENTER);
    frameListTitled.add(this.frameListLabel, BorderLayout.NORTH);

    //JButton edit = new JButton("edit");
    //edit.setActionCommand("frame edit");
    //edit.addActionListener(this);
    //JButton add = new JButton("add");
    //add.setActionCommand("frame add");
    //add.addActionListener(this);
    //JButton delete = new JButton("delete");
    //delete.setActionCommand("frame delete");
    //delete.addActionListener(this);
    //JPanel buttons = new JPanel();
    //buttons.add(edit);
    //buttons.add(add);
    //buttons.add(delete);
    //this.addBorder(buttons);

    this.framesEditor = new JPanel(new BorderLayout());
    this.framesEditor.add(title, BorderLayout.NORTH);
    this.framesEditor.add(frameListTitled, BorderLayout.CENTER);
    //this.framesEditor.add(buttons, BorderLayout.SOUTH);
    this.framesEditor.setPreferredSize(new Dimension(500, 271)); //Alter here for text
  }

  @Override
  public void play() {
    this.setVisible(true);
  }

  /**
   * This method will display a list of shapes onto the graphical interface for the user.
   */
  @Override
  public void display(List<InterfaceInterpretShape> shapes) {
    this.drawingPanel.draw(shapes);
  }

  /**
   * Retrieves the shapes to be displayed.
   *
   * @return a list of shapes
   */
  @Override
  public List<InterfaceShapeCell> getShapes() {
    List<InterfaceShapeCell> output = new ArrayList<>();
    for (int i = 0; i < this.shapesList.size(); i++) {
      output.add(this.shapesList.elementAt(i));
    }

    return output;
  }

  /**
   * Places a map of all shapes in animation in the view.
   *
   * @param shapes all the shapes in the animation.
   */
  @Override
  public void setShapes(Map<String, InterfaceInterpretShape> shapes) {
    this.shapesList.clear();

    for (String key : shapes.keySet()) {
      this.shapesList.addElement(new ShapeCell(key, shapes.get(key).getShapeType()));
    }

    this.framesList.clear();
    this.frameListContainer.clearSelection();
  }

  /**
   * Retrieves all keyframes.
   */
  @Override
  public Map<String, List<InterpretStatusKeyFrame>> getKeyframes() {
    return this.keyframes;
  }

  /**
   * Sets all keyframes.
   */
  @Override
  public void setKeyframes(Map<String, List<InterpretStatusKeyFrame>> keyframes) {
    this.keyframes = new LinkedHashMap<>();
    for (String id : keyframes.keySet()) {
      List<InterpretStatusKeyFrame> newList = new ArrayList<>();

      for (InterpretStatusKeyFrame frame : keyframes.get(id)) {
        newList.add(new StatusKeyFrame(frame.getTime(), frame.getX(), frame.getY(),
                frame.getWidth(), frame.getHeight(), frame.getShapeRotation(),
                frame.getColor()));
      }
      this.keyframes.put(id, newList);
    }
    this.updateFramesList();
  }

  /**
   * sets the width of a shape.
   */
  @Override
  public void setWidth(int width) {
    this.width = width;
    this.drawingPanel.setPreferredSize(new Dimension(this.width, this.height));
  }

  /**
   * sets the height of a shape.
   */
  @Override
  public void setHeight(int height) {
    this.height = height;
    this.drawingPanel.setPreferredSize(new Dimension(this.width, this.height));
  }

  /**
   * Adds a listener that can receive action events.
   */
  @Override
  public void addButtonListener(ActionListener listener) {
    this.buttonListeners.add(listener);
  }

  /**
   * Adds a listener that can receive property change events.
   */
  @Override
  public void addPropertyListener(PropertyChangeListener listener) {
    this.tickSpeedListeners.add(listener);
    this.handler.addExportListener(listener);
  }

  /**
   * Adds a listener that receives a SHAPE CHANGE event.
   */
  @Override
  public void addShapeChangeListener(InterfaceShapeChangeListener listener) {
    this.handler.addShapeChangeListener(listener);
  }

  /**
   * Adds a listener that receives a frame change event.
   */
  @Override
  public void addFrameChangeListener(InterfaceFrameChangeListener listener) {
    this.handler.addFrameChangeListener(listener);
  }

  /**
   * Displays an error on the screen indicating to the user an error occured.
   */
  @Override
  public void displayError(String s) {
    JOptionPane.showMessageDialog(this, s, "An error occurred",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Helper method for actions performed.
   */
  private void fireButtonEvent(String type) {
    for (ActionListener listener : this.buttonListeners) {
      listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, type));
    }
  }

  /**
   * Helper method for change events.
   */
  private void firePropertyChangeEvent(String type, String newValue) {
    for (PropertyChangeListener listener : this.tickSpeedListeners) {
      listener.propertyChange(new PropertyChangeEvent(this, type,
              newValue, newValue));
    }
  }

  /**
   * Helper method for updating the frames.
   */
  private void updateFramesList() {
    this.framesList.clear();

    if (this.shapeSelected >= 0 && this.shapeSelected < this.shapesList.size()) {
      String id = this.shapesList.elementAt(this.shapeSelected).getID();

      if (this.keyframes.containsKey(id)) {
        for (InterpretStatusKeyFrame cell : this.keyframes.get(id)) {
          this.framesList.addElement(cell);
        }
      }
    }
    if (this.frameSelected >= this.framesList.size()) {
      this.frameSelected = 0;
    }
    if (this.frameSelected >= 0) {
      this.frameListContainer.setSelectedIndex(this.shapeSelected);
    }
  }


  /**
   * Called whenever the shape or keyframe currently selected changes.
   *
   * @param event the event that characterizes the change.
   */
  @Override
  public void valueChanged(ListSelectionEvent event) {
    int newShapeSelected = this.shapeListContainer.getSelectedIndex();

    int newFrameSelected = -1;

    try {
      newFrameSelected = this.frameListContainer.getSelectedIndex();
    } catch (NullPointerException ignored) {
    }

    if (newFrameSelected != -1) {
      this.frameSelected = newFrameSelected;
    }

    if (newShapeSelected != -1 && newShapeSelected != this.shapeSelected) {
      this.shapeSelected = newShapeSelected;
      updateFramesList();
      this.frameListContainer.clearSelection();
      this.frameListLabel.setText("Shape: " + this.shapesList.getElementAt(this.shapeSelected));
    }
  }

  /**
   * Invoked whenever any button is pressed.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    String process = event.getActionCommand();

    if (process.equals("TICK SPEED")) {
      this.firePropertyChangeEvent("TICK SPEED", this.tickSpeedField.getText());
      return;
    }

    if (process.equals("EXPORT")) {
      this.handler.exportToSVG(this);
    }

    if (process.equals("SHAPE ADD")) {
      this.handler.changeShape(this, ShapeChange.ADD, "");
      return;
    }

    InterfaceShapeCell shape;
    if (this.shapeSelected != -1 || !this.shapeListContainer.isSelectionEmpty()) {
      shape = this.shapesList.elementAt(this.shapeSelected);
    } else {
      this.fireButtonEvent(process);
      return;
    }

    if (process.equals("shape delete")) {
      this.handler.changeShape(this, ShapeChange.DELETE, shape.getID());
      return;
    }

    if (process.equals("frame add")) {
      this.handler.changeFrame(this, FrameChange.ADD, shape.getID(), 0);
      return;
    }

    InterpretStatusKeyFrame keyframe;
    if (this.frameSelected != -1 || !this.frameListContainer.isSelectionEmpty()) {
      keyframe = this.framesList.elementAt(this.frameSelected);
    } else {
      this.fireButtonEvent(process);
      return;
    }

    switch (process) {
      case "frame edit":
        this.handler.changeFrame(this, FrameChange.EDIT, shape.getID(), keyframe.getTime(),
                keyframe.getX(), keyframe.getY(), keyframe.getWidth(), keyframe.getHeight(),
                keyframe.getShapeRotation(), keyframe.getColor());
        break;
      case "frame delete":
        this.handler.changeFrame(this, FrameChange.DELETE, shape.getID(),
                keyframe.getTime());
        break;
      default:
        this.fireButtonEvent(process);
    }
  }

  @Override
  public void stateChanged(ChangeEvent event) {
    if (sliderPauser) {
      this.firePropertyChangeEvent("SLIDER", "" + sliderFunction.getValue());
    } else {
      sliderPauser = true;
    }
  }

  @Override
  public void setSlider(double tick) {
    sliderPauser = false;
    sliderFunction.setValue((int) (tick * 100));
  }
}