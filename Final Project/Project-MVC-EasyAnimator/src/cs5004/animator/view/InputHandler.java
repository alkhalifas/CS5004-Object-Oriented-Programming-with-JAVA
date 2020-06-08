package cs5004.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

/**
 * This class represents the playbackview to handle a pop up that will be used for save as event.
 */
public class InputHandler implements InterfaceViewInputHandler {
  private List<InterfaceShapeChangeListener> actionShapeChangeListeners;
  private List<InterfaceFrameChangeListener> actionFrameChangeListeners;
  private List<PropertyChangeListener> actionExportListeners;

  // This field holds all shape types that the program recognizes.
  private final String[] shapeChoices = new String[]{"Rectangle", "Ellipse"};

  /**
   * A default constructor that initializes the listener lists.
   */
  public InputHandler() {
    this.actionShapeChangeListeners = new ArrayList<>();
    this.actionFrameChangeListeners = new ArrayList<>();
    this.actionExportListeners = new ArrayList<>();
  }

  @Override
  public void addShapeChangeListener(InterfaceShapeChangeListener listener) {
    this.actionShapeChangeListeners.add(listener);
  }

  @Override
  public void addFrameChangeListener(InterfaceFrameChangeListener listener) {
    this.actionFrameChangeListeners.add(listener);
  }

  @Override
  public void addExportListener(PropertyChangeListener listener) {
    this.actionExportListeners.add(listener);
  }

  private void fireExportEvent(String fileName) {
    for (PropertyChangeListener listener : this.actionExportListeners) {
      listener.propertyChange(new PropertyChangeEvent(this, "EXPORT", fileName, fileName));
    }
  }

  private void fireShapeChangeEvent(ShapeChange changeType, String shapeType, String id) {
    for (InterfaceShapeChangeListener listener : this.actionShapeChangeListeners) {
      listener.shapeChanged(new ShapeChangeEvent(this, changeType, shapeType, id));
    }
  }

  private void fireFrameChangeEvent(FrameChange type, String id, int time, int x, int y,
                                    int width, int height, int rotationDegree, Color color) {
    for (InterfaceFrameChangeListener listener : this.actionFrameChangeListeners) {
      listener.keyframeChanged(new FrameChangeEvent(this, type, id, time, x, y, width,
              height, rotationDegree, color));
    }
  }

  private void fireFrameDeleteEvent(String id, int time) {
    for (InterfaceFrameChangeListener listener : this.actionFrameChangeListeners) {
      listener.keyframeChanged(new FrameChangeEvent(this, FrameChange.DELETE, id, time,
              0, 0, 0, 0, 0, new Color(0)));
    }
  }

  @Override
  public void exportToSVG(Component parent) {
    JLabel saveAsLabel = new JLabel("Save as:");
    JTextField nameInput = new JTextField();
    nameInput.setPreferredSize(new Dimension(150, 30));
    JLabel extensionLabel = new JLabel(".svg");
    JPanel namePanel = new JPanel();
    namePanel.add(saveAsLabel);
    namePanel.add(nameInput);
    namePanel.add(extensionLabel);

    JLabel infoLabel = new JLabel("(file will be saved to the \"out\" folder)");

    JPanel popUp = new JPanel(new BorderLayout());
    popUp.add(namePanel, BorderLayout.CENTER);
    popUp.add(infoLabel, BorderLayout.SOUTH);

    int result = JOptionPane.showConfirmDialog(parent, popUp, "Export to SVG",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    JLabel errorMsg = new JLabel("Please enter a name", SwingConstants.CENTER);
    errorMsg.setForeground(Color.RED);
    popUp.add(errorMsg, BorderLayout.NORTH);

    while (result == JOptionPane.OK_OPTION) {
      if (!nameInput.getText().equals("")) {
        this.fireExportEvent(nameInput.getText());
        return;
      }
      else {
        result = JOptionPane.showConfirmDialog(parent, popUp, "Add Shape",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  @Override
  public void changeShape(Component parent, ShapeChange type, String id) {
    if (type == ShapeChange.DELETE) {
      this.fireShapeChangeEvent(ShapeChange.DELETE, "", id);
      return;
    }

    JLabel idLabel = new JLabel("ID:");
    JTextField idInput = new JTextField();
    idInput.setPreferredSize(new Dimension(100, 50));
    JPanel idPanel = new JPanel();
    idPanel.add(idLabel);
    idPanel.add(idInput);

    JLabel typeLabel = new JLabel("type:");
    JComboBox<String> typeInput = new JComboBox<>(this.shapeChoices);
    JPanel typePanel = new JPanel();
    typePanel.add(typeLabel);
    typePanel.add(typeInput);

    JPanel popUp = new JPanel(new BorderLayout());
    popUp.add(idPanel, BorderLayout.NORTH);
    popUp.add(typePanel, BorderLayout.SOUTH);

    int result = JOptionPane.showConfirmDialog(parent, popUp, "Add Shape",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    JLabel errorMsg = new JLabel("Invalid ID", SwingConstants.CENTER);
    errorMsg.setForeground(Color.RED);
    JPanel error = new JPanel(new BorderLayout());
    error.add(popUp, BorderLayout.CENTER);
    error.add(errorMsg, BorderLayout.NORTH);

    while (result == JOptionPane.OK_OPTION) {
      if (!idInput.getText().equals("")) {
        this.fireShapeChangeEvent(ShapeChange.ADD, typeInput.getSelectedItem().toString(),
                idInput.getText());
        return;
      }
      else {
        result = JOptionPane.showConfirmDialog(parent, error, "Add Shape",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  @Override
  public void changeFrame(Component parent, FrameChange type, String id, int time) {
    if (type == FrameChange.EDIT) {
      throw new IllegalArgumentException("Editing requires additional arguments.");
    }
    this.changeFrame(parent, type, id, time, 0, 0, 0, 0, 0, new Color(0));
  }

  @Override
  public void changeFrame(Component parent, FrameChange type, String id, int time, int x,
                          int y, int width, int height, int rotationDegree, Color color) {
    String change;
    JTextField timeInput;
    JTextField xInput;
    JTextField yInput;
    JTextField wInput;
    JTextField hInput;
    JTextField oInput;
    JTextField rInput;
    JTextField gInput;
    JTextField bInput;

    switch (type) {
      case EDIT:
        timeInput = new JTextField("" + time);
        xInput = new JTextField("" + x);
        yInput = new JTextField("" + y);
        wInput = new JTextField("" + width);
        hInput = new JTextField("" + height);
        oInput = new JTextField("" + rotationDegree);
        rInput = new JTextField("" + color.getRed());
        gInput = new JTextField("" + color.getGreen());
        bInput = new JTextField("" + color.getBlue());
        change = "Edit Keyframe";
        break;
      case ADD:
        timeInput = new JTextField();
        xInput = new JTextField();
        yInput = new JTextField();
        wInput = new JTextField();
        hInput = new JTextField();
        oInput = new JTextField();
        rInput = new JTextField();
        gInput = new JTextField();
        bInput = new JTextField();
        change = "Add Keyframe";
        break;
      case DELETE:
        this.fireFrameDeleteEvent(id, time);
        return;
      default:
        throw new IllegalArgumentException("No valid change type given.");
    }

    BorderLayout layout = new BorderLayout();
    JPanel timePanel = new JPanel(layout);
    if (type == FrameChange.ADD) {
      JLabel timeLabel = new JLabel("time");
      JPanel timeFields = new JPanel(new GridLayout(1, 2));
      JLabel tickLabel = new JLabel("tick:");
      timeFields.add(tickLabel);
      timeFields.add(timeInput);
      timeFields.setPreferredSize(new Dimension(144, 39));
      layout.setVgap(39);
      timePanel.add(timeLabel, BorderLayout.NORTH);
      timePanel.add(timeFields, BorderLayout.CENTER);
    }

    JLabel posLabel = new JLabel("coordinatePosition");
    JPanel posFields = new JPanel(new GridLayout(2, 2));
    JLabel xLabel = new JLabel("x-pos:");
    JLabel yLabel = new JLabel("y-pos:");
    posFields.add(xLabel);
    posFields.add(xInput);
    posFields.add(yLabel);
    posFields.add(yInput);
    posFields.setPreferredSize(new Dimension(144, 78));
    JPanel coordinatePositionPanel = new JPanel(new BorderLayout());
    coordinatePositionPanel.add(posLabel, BorderLayout.NORTH);
    coordinatePositionPanel.add(posFields, BorderLayout.CENTER);

    JLabel sizeLabel = new JLabel("size");
    JPanel sizeFields = new JPanel(new GridLayout(2, 2));
    JLabel wLabel = new JLabel("width:");
    JLabel hLabel = new JLabel("height:");
    sizeFields.add(wLabel);
    sizeFields.add(wInput);
    sizeFields.add(hLabel);
    sizeFields.add(hInput);
    sizeFields.setPreferredSize(new Dimension(144, 78));
    JPanel sizePanel = new JPanel(new BorderLayout());
    sizePanel.add(sizeLabel, BorderLayout.NORTH);
    sizePanel.add(sizeFields, BorderLayout.CENTER);

    BorderLayout layout2 = new BorderLayout();
    JPanel rotationDegreePanel = new JPanel(layout2);
    JLabel rotationDegreeHeader = new JLabel("rotationDegree");
    JPanel rotationDegreeFields = new JPanel(new GridLayout(1, 2));
    JLabel pointLabel = new JLabel("rotate:");
    JLabel unitLabel = new JLabel("°");
    rotationDegreeFields.add(pointLabel);
    rotationDegreeFields.add(oInput);
    rotationDegreeFields.add(unitLabel);

    rotationDegreeFields.setPreferredSize(new Dimension(160, 39));
    layout2.setVgap(39);
    rotationDegreePanel.add(rotationDegreeHeader, BorderLayout.NORTH);
    rotationDegreePanel.add(rotationDegreeFields, BorderLayout.CENTER);

    JLabel colorLabel = new JLabel("color");
    JPanel colorFields = new JPanel(new GridLayout(3, 3));
    JLabel rLabel = new JLabel("red:");
    JLabel gLabel = new JLabel("green:");
    JLabel bLabel = new JLabel("blue:");
    JLabel rangeLabel1 = new JLabel("[0, 255]");
    JLabel rangeLabel2 = new JLabel("[0, 255]");
    JLabel rangeLabel3 = new JLabel("[0, 255]");
    colorFields.add(rLabel);
    colorFields.add(rInput);
    colorFields.add(rangeLabel1);
    colorFields.add(gLabel);
    colorFields.add(gInput);
    colorFields.add(rangeLabel2);
    colorFields.add(bLabel);
    colorFields.add(bInput);
    colorFields.add(rangeLabel3);
    JPanel colorPanel = new JPanel(new BorderLayout());
    colorPanel.add(colorLabel, BorderLayout.NORTH);
    colorPanel.add(colorFields, BorderLayout.CENTER);

    JPanel popUp = new JPanel();
    if (type == FrameChange.ADD) {
      popUp.add(timePanel);
    }
    popUp.add(coordinatePositionPanel);
    popUp.add(sizePanel);
    popUp.add(rotationDegreePanel);
    popUp.add(colorPanel);

    int result = JOptionPane.showConfirmDialog(parent, popUp, change, JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);

    JLabel errorMsg = new JLabel("Invalid input", SwingConstants.CENTER);
    errorMsg.setForeground(Color.RED);
    JPanel error = new JPanel(new BorderLayout());
    error.add(popUp, BorderLayout.CENTER);
    error.add(errorMsg, BorderLayout.NORTH);

    while (result == JOptionPane.OK_OPTION) {
      try {
        this.fireFrameChangeEvent(type, id, Integer.parseInt(timeInput.getText()),
                Integer.parseInt(xInput.getText()), Integer.parseInt(yInput.getText()),
                Integer.parseInt(wInput.getText()), Integer.parseInt(hInput.getText()),
                Integer.parseInt(oInput.getText()),
                new Color(Integer.parseInt(rInput.getText()), Integer.parseInt(gInput.getText()),
                        Integer.parseInt(bInput.getText())));
        return;
      }
      catch (NumberFormatException e) {
        result = JOptionPane.showConfirmDialog(parent, error, change, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}