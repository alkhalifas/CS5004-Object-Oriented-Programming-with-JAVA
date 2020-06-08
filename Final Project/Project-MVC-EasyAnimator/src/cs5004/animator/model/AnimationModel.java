package cs5004.animator.model;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.util.AnimationBuilder;


/**
 * This class represents the animation model of a process an animation using a list of the shapes
 * and a list of the processes.
 */
public class AnimationModel implements InterfaceAniModel {
  private final LinkedHashMap<String, List<InterfaceRotateShape>> processes;
  private final LinkedHashMap<String, InterfaceShape> shapes;

  private final int x;
  private final int y;

  private final int width;
  private final int height;


  /**
   * A private constructor for Animation model to allow for only our playbackBuilder to create new
   * models. This allows us to
   *
   * @param processes which is a linked hashmap that contains IDs and shapes connected to all of the
   *                  processes that the shape will have.
   * @param shapes    which is also a linked hashmap that contains the IDs and shapes.
   */
  private AnimationModel(LinkedHashMap<String, List<InterfaceRotateShape>> processes,
                         LinkedHashMap<String, InterfaceShape> shapes,
                         int x, int y, int width, int height) {
    this.processes = processes;
    this.shapes = shapes;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * THis is a helper method that serches for index of a process using binary search.
   *
   * @param list         the list of processes through which to search for a given animaiton.
   * @param startingTime the time to search for within the processes.
   * @return the index int of the process.
   */
  private static int indexOfProcess(List<InterfaceRotateShape> list, int startingTime) {
    if (list.isEmpty()) {
      return 0;
    }

    int indexMin = 0;
    int indexMax = list.size() - 1;

    while (indexMin < indexMax) {
      int indexMiddle = (indexMin + indexMax) / 2;
      int middleTime = list.get(indexMiddle).getStartTime();

      if (startingTime == middleTime) {
        return indexMiddle;
      } else if (startingTime > middleTime) {
        indexMin = indexMiddle + 1;
      } else {
        indexMax = indexMiddle - 1;
      }
    }

    if (startingTime < list.get(indexMin).getStartTime()) {
      return indexMin - 1;
    } else {
      return indexMin;
    }
  }

  /**
   * This method retrieves the state of the shapes. THis retrieves a list of the shapes at a
   * specific time interval.
   *
   * @param timeOfInterest the time in which the state should be retrieved.
   * @return the list of shapes that are the current state of the animation.
   */
  @Override
  public List<InterfaceInterpretShape> getState(int timeOfInterest) {
    List<InterfaceInterpretShape> output = new ArrayList<>();
    //iterate through the shape list
    for (Map.Entry<String, List<InterfaceRotateShape>> entry : this.processes.entrySet()) {
      String id = entry.getKey();
      InterfaceShape currShapes = this.shapes.get(id);

      // iterate via index
      int index = indexOfProcess(this.processes.get(id), timeOfInterest);
      if (index == -1) {
        continue;
      }
      if (timeOfInterest <= this.processes.get(id).get(index).getEndTime()) {
        this.processes.get(id).get(index).setState(timeOfInterest, currShapes);
      } else {
        continue;
      }
      double newX = currShapes.getPosition().getX() - this.x;
      double newY = currShapes.getPosition().getY() - this.y;
      currShapes.setPosition(new Point2D.Double(newX, newY));
      output.add(currShapes);
    }
    return output;
  }

  /**
   * This method creates a linked hash map and then returns the shapes within the map.
   *
   * @return the shapes within the map.
   */
  @Override
  public LinkedHashMap<String, InterfaceInterpretShape> getShapes() {
    LinkedHashMap<String, InterfaceInterpretShape> output = new LinkedHashMap<>();
    for (String currKey : shapes.keySet()) {
      output.put(currKey, shapes.get(currKey));
    }
    return output;
  }

  /**
   * This method creates a linked hash map and then returns the processes within the map.
   *
   * @return processes from model in the map.
   */
  @Override
  public LinkedHashMap<String, List<InterfaceInterpretStatusProcess>> getProcesses() {
    LinkedHashMap<String, List<InterfaceInterpretStatusProcess>> output = new LinkedHashMap<>();
    for (String key : this.processes.keySet()) {
      List<InterfaceInterpretStatusProcess> newProcesss = new ArrayList<>();
      newProcesss.addAll(this.processes.get(key));
      output.put(key, newProcesss);
    }
    return output;
  }

  /**
   * Returns the x coordinate of the model.
   */
  @Override
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y coordinate of the model.
   */
  @Override
  public int getY() {
    return this.y;
  }

  /**
   * Returns the width of the shape.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the shape.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the last tick in the process of the model.
   *
   * @return the last tick.
   */
  @Override
  public int getLastTick() {
    int output = 0;
    for (List<InterfaceRotateShape> processList : this.processes.values()) {
      output = Math.max(output, processList.get(processList.size() - 1).getEndTime());
    }
    return output;
  }

  /**
   * A description of the animation process that returns a string.
   *
   * @return String that is the description.
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    for (Map.Entry<String, List<InterfaceRotateShape>> entry : this.processes.entrySet()) {
      InterfaceShape shape = this.shapes.get(entry.getKey());
      output.append("Shape ").append(entry.getKey()).append(" ").append(shape.getShapeType())
              .append("\n");
      for (InterfaceProcess process : entry.getValue()) {
        StringBuilder temp = new StringBuilder(process.getType()).append(" ")
                .append(entry.getKey()).append(" ").append(process.getStartTime()).append(" ");

        process.setState(process.getStartTime(), shape);
        temp.append(this.getShapeInfo(shape)).append("    ");

        process.setState(process.getEndTime(), shape);
        temp.append(process.getEndTime()).append(" ").append(this.getShapeInfo(shape)).append("\n");
        output.append(temp);
      }
      output.append("\n");
    }
    return output.toString().trim();
  }

  /**
   * Helper for the toString method that gives a description of a shape.
   *
   * @param shape is the shape.
   * @return String that is the description.
   */
  private String getShapeInfo(InterfaceShape shape) {
    StringBuilder temp = new StringBuilder();
    temp.append(shape.getPosition().getX()).append(" ")
            .append(shape.getPosition().getY()).append(" ")
            .append(shape.getWidth()).append(" ")
            .append(shape.getHeight()).append(" ")
            .append(shape.getColor().getRed()).append(" ")
            .append(shape.getColor().getGreen()).append(" ")
            .append(shape.getColor().getBlue());
    return temp.toString();
  }

  /**
   * This class is the builder class for the animation model class. This allows the users to input
   * processes and shapes for the playBackBuilder.
   */
  public static class AnimationModelBuilder implements InterfacePlayBack {
    private LinkedHashMap<String, List<InterfaceRotateShape>> processes;
    private LinkedHashMap<String, InterfaceShape> shapesList;
    private int x = 0;
    private int y = 0;
    private int width = 1000;
    private int height = 600;


    /**
     * Constructor for the AnimationModelBuilder.
     */
    public AnimationModelBuilder() {
      this.processes = new LinkedHashMap<>();
      this.shapesList = new LinkedHashMap<>();
    }

    /**
     * A method that constructs a new model given the processes and shapes in the PlaybackBuilder.
     */
    public InterfaceAniModel build() {

      for (String key : this.shapesList.keySet()) {
        if (!this.processes.containsKey(key)) {
          throw new IllegalStateException("A shape must contain at least one processes");
        }
      }
      for (String key : this.processes.keySet()) {
        if (!this.shapesList.containsKey(key)) {
          throw new IllegalStateException("A process must have at least one shape");
        }
      }
      return new AnimationModel(this.processes,
              this.shapesList,
              this.x,
              this.y,
              this.width,
              this.height);
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder setBounds(int x, int y, int width, int height) {
      if (width < 0 || height < 0) {
        throw new IllegalArgumentException("Error! The shape cannot have a negative"
                + "width or height");
      }
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      return this;
    }

    /**
     * This method adds a new shape to the document.
     *
     * @param name The unique name of the shape.
     * @param type The type of shape such as a rectangle or ellipse.
     * @return a new addition to the list.
     */
    @Override
    public AnimationBuilder declareShape(String name, String type) {
      if (this.shapesList.containsKey(name)) {
        throw new IllegalArgumentException("Error!: This ID has already been given and"
                + "associated to a shape");
      }

      InterfaceShape shape;
      // TODO: Perhaps add a new shape here?
      switch (type.toLowerCase()) {
        case "rectangle":
          shape = new Rectangle();
          break;
        case "ellipse":
          shape = new Ellipse();
          break;
        default:
          shape = null;
      }
      if (shape == null) {
        throw new IllegalArgumentException("Shape type is invalid. Please check your spelling");
      }
      this.shapesList.put(name, shape);
      return this;
    }





    /**
     * Adds a transformation to the growing document.
     *
     * @param name is the name of the shape.
     * @param t1   is the start time of this transformation
     * @param x1   is the initial x-coordinatePosition.
     * @param y1   is the initial y-coordinatePosition.
     * @param w1   is the initial width of the shape
     * @param h1   is the initial height.
     * @param r1   is the initial red color-value.
     * @param g1   is the initial green color-value.
     * @param b1   is the initial blue color-value.
     * @param t2   is the end time of this transformation
     * @param x2   is the final x-coordinatePosition.
     * @param y2   is the final y-coordinatePosition.
     * @param w2   is the final width.
     * @param h2   is the final height.
     * @param r2   is the final red color-value.
     * @param g2   is the final green color-value.
     * @param b2   is the final blue color-value.
     * @return AniBuilder List.
     */
    @Override
    public AnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1, int o1,
                                      int r1, int g1, int b1, int t2, int x2, int y2, int w2,
                                      int h2, int o2, int r2, int g2, int b2) {
      String type = this.getType(x1, y1, w1, h1, o1, r1, g1, b1, x2, y2, w2, h2, o2, r2, g2, b2);
      InterfaceRotateShape process = new SuperGeneralProcess(type, t1, x1, y1, w1, h1, o1,
              r1, g1, b1, t2, x2, y2, w2, h2, o2, r2, g2, b2);
      if (this.processes.containsKey(name)) {
        this.addIfValidProcess(name, this.processes.get(name), process,
                indexOfProcess(this.processes.get(name), process.getStartTime()) + 1);
      } else {
        this.processes.put(name, new ArrayList<>(Collections.singletonList(process)));
      }
      return this;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name is the name of the shape.
     * @param t1   is the start time of this transformation
     * @param x1   is the initial x-coordinatePosition.
     * @param y1   is the initial y-coordinatePosition.
     * @param w1   is the initial width of the shape
     * @param h1   is the initial height.
     * @param r1   is the initial red color-value.
     * @param g1   is the initial green color-value.
     * @param b1   is the initial blue color-value.
     * @param t2   is the end time of this transformation
     * @param x2   is the final x-coordinatePosition.
     * @param y2   is the final y-coordinatePosition.
     * @param w2   is the final width.
     * @param h2   is the final height.
     * @param r2   is the final red color-value.
     * @param g2   is the final green color-value.
     * @param b2   is the final blue color-value.
     * @return AniBuilder List.
     */
    @Override
    public AnimationBuilder addMotion(String name, int t1,
                                      int x1, int y1,
                                      int w1, int h1,
                                      int r1, int g1, int b1,
                                      int t2,
                                      int x2, int y2,
                                      int w2, int h2,
                                      int r2, int g2, int b2) {

      String type = this.getType(x1, y1, w1, h1, r1, g1, b1, x2, y2, w2, h2, r2, g2, b2);
      InterfaceRotateShape process = new SuperGeneralProcess(type, t1, x1, y1, w1, h1,
              0, r1, g1, b1, t2, x2, y2, w2, h2, 0, r2, g2, b2);
      if (this.processes.containsKey(name)) {
        this.addIfValidProcess(name, this.processes.get(name), process,
                indexOfProcess(this.processes.get(name), process.getStartTime()) + 1);
      } else {
        this.processes.put(name, new ArrayList<>(Collections.singletonList(process)));
      }
      return this;
    }

    /**
     * This method will return a description of the motion.
     *
     * @param x1 is the initial x-coordinatePosition.
     * @param y1 is the initial y-coordinatePosition.
     * @param w1 is the initial width of the shape
     * @param h1 is the initial height.
     * @param r1 is the initial red color-value.
     * @param g1 is the initial green color-value.
     * @param b1 is the initial blue color-value.
     * @param x2 is the final x-coordinatePosition.
     * @param y2 is the final y-coordinatePosition.
     * @param w2 is the final width.
     * @param h2 is the final height.
     * @param r2 is the final red color-value.
     * @param g2 is the final green color-value.
     * @param b2 is the final blue color-value.
     * @return AniBuilder List.
     */
    private String getType(int x1, int y1, int w1, int h1, int o1, int r1, int g1, int b1,
                           int x2, int y2, int w2, int h2, int o2, int r2, int g2, int b2) {
      String testDescription = this.getType(x1, y1, w1, h1, r1, g1, b1, x2, y2, w2, h2, r2, g2, b2);

      if (o1 != o2) {
        if (testDescription.equals("Nothing")) {
          testDescription = "Rotate";
        } else {
          testDescription += " and Rotate";
        }
      }

      return testDescription;
    }

    /**
     * This method will return a description of the motion.
     *
     * @param x1 is the initial x-coordinatePosition.
     * @param y1 is the initial y-coordinatePosition.
     * @param w1 is the initial width of the shape
     * @param h1 is the initial height.
     * @param r1 is the initial red color-value.
     * @param g1 is the initial green color-value.
     * @param b1 is the initial blue color-value.
     * @param x2 is the final x-coordinatePosition.
     * @param y2 is the final y-coordinatePosition.
     * @param w2 is the final width.
     * @param h2 is the final height.
     * @param r2 is the final red color-value.
     * @param g2 is the final green color-value.
     * @param b2 is the final blue color-value.
     * @return AniBuilder List.
     */
    private String getType(int x1, int y1, int w1, int h1, int r1, int g1, int b1, int x2, int y2,
                           int w2, int h2, int r2, int g2, int b2) {
      String testDescription = "";

      if (x1 != x2 || y1 != y2) {
        testDescription = "Movement";
      }
      if (w1 != w2 || h1 != h2) {
        testDescription = "Scaling";
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        testDescription = "Color change";
      } else {
        return "Movement";
      }
      return testDescription;
    }


    /**
     * Helper method that adds a process into a list with a specific index when no overlap is
     * observed.
     */
    private void addIfValidProcess(String id, List<InterfaceRotateShape> list,
                                   InterfaceRotateShape process,
                                   int addIndex) {
      int startTick = process.getStartTime();
      InterfaceShape shapeCopy1 = this.shapesList.get(id).makeCopy();
      InterfaceShape shapeCopy2 = this.shapesList.get(id).makeCopy();

      if (addIndex == 0) {
        list.add(addIndex, process);
        return;
      }

      InterfaceProcess previousProcess = list.get(addIndex - 1);

      previousProcess.setState(previousProcess.getEndTime(), shapeCopy1);
      process.setState(startTick, shapeCopy2);

      if (previousProcess.getEndTime() != startTick) {
        throw new IllegalArgumentException("The start and end times of a process must overlap!");
      } else if (this.shapesAreEqual(shapeCopy1, shapeCopy2)) {
        list.add(addIndex, process);
      } else {
        throw new IllegalArgumentException("Illegal object management here.");
      }
    }

    /**
     * Method that determines if two shapes are equal.
     */
    private boolean shapesAreEqual(InterfaceShape shape1, InterfaceShape shape2) {
      return shape1.getShapeType().equals(shape2.getShapeType())
              && shape1.getWidth() == shape2.getWidth()
              && shape1.getHeight() == shape2.getHeight()
              && shape1.getPosition().getX() == shape2.getPosition().getX()
              && shape1.getPosition().getY() == shape2.getPosition().getY()
              && shape1.getShapeRotation() == shape2.getShapeRotation()
              && shape1.getColor().getRGB() == shape2.getColor().getRGB();
    }

    @Override
    public Dimension getNeededSpace() {
      int minX = this.x;
      int minY = this.y;
      int maxX = this.x;
      int maxY = this.y;

      for (List<InterfaceInterpretStatusProcess> processList : this.getProcesses().values()) {
        minX = Math.min(minX,
                processList.get(0).getStartX());
        minY = Math.min(minY,
                processList.get(0).getStartY());
        maxX = Math.max(maxX,
                processList.get(0).getStartX() + processList.get(0).getStartWidth());
        maxY = Math.max(maxY,
                processList.get(0).getStartY() + processList.get(0).getStartHeight());

        for (InterfaceInterpretProcess process : processList) {
          minX = Math.min(minX, process.getEndX());
          minY = Math.min(minY, process.getEndY());
          maxX = Math.max(maxX, process.getEndX() + process.getEndWidth());
          maxY = Math.max(maxY, process.getEndY() + process.getEndHeight());
        }
      }
      return new Dimension(maxX - minX, maxY - minY);
    }

    /**
     * Method that removes a process.
     */
    @Override
    public InterfacePlayBack removeProcess(String id, int time) {
      if (processes.get(id) == null) {
        throw new IllegalArgumentException("The given ID does not have any processes");
      }
      for (int i = 0; i < processes.get(id).size(); i++) {
        if (time == processes.get(id).get(i).getStartTime()) {
          processes.get(id).remove(i);
          return this;
        }
      }
      if (time == processes.get(id).get(processes.get(id).size() - 1).getEndTime()) {
        processes.get(id).remove(processes.get(id).size() - 1);
        return this;
      }
      throw new IllegalArgumentException("There are no processes to remove.");
    }

    /**
     * Method that removes a shape.
     */
    @Override
    public InterfacePlayBack removeShape(String id) {
      if (!shapesList.containsKey(id)) {
        throw new IllegalArgumentException("This ID is not associated with any shapes here");
      }
      shapesList.remove(id);
      processes.remove(id);
      return this;
    }

    /**
     * THis method returns the shapes within the map.
     *
     * @return the shapes within the map.
     */
    @Override
    public LinkedHashMap<String, InterfaceInterpretShape> getShapes() {
      LinkedHashMap<String, InterfaceInterpretShape> output = new LinkedHashMap<>();
      for (String key : shapesList.keySet()) {
        output.put(key, shapesList.get(key));
      }
      return output;
    }

    /**
     * This method creates a linked hash map for all the processes from the model and returns it.
     *
     * @return the map of the processes from the model.
     */
    @Override
    public LinkedHashMap<String, List<InterfaceInterpretStatusProcess>> getProcesses() {
      LinkedHashMap<String, List<InterfaceInterpretStatusProcess>> output = new LinkedHashMap<>();
      for (String key : this.processes.keySet()) {
        List<InterfaceInterpretStatusProcess> newProcesss = new ArrayList<>();
        newProcesss.addAll(this.processes.get(key));
        output.put(key, newProcesss);
      }
      return output;
    }

    @Override
    public AnimationBuilder addKeyframe(String name, int t, int x, int y, int w,
                                        int h, int r, int g, int b) {
      throw new UnsupportedOperationException("Error Please check settings");
    }


  }
}