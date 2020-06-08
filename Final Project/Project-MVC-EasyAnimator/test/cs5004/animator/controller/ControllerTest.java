package cs5004.animator.controller;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.model.InterfacePlayBack;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.FrameChange;
import cs5004.animator.view.FrameChangeEvent;
import cs5004.animator.view.InterfacePlaybackView;
import cs5004.animator.view.InterfaceFrameChangeEvent;
import cs5004.animator.view.InterfaceShapeCell;
import cs5004.animator.view.InterfaceShapeChangeEvent;
import cs5004.animator.view.ShapeCell;
import cs5004.animator.view.ShapeChange;
import cs5004.animator.view.ShapeChangeEvent;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the controller to ensure proper functionality in the playback view.
 */
public class ControllerTest {
  private Controller controller;
  private InterfacePlayBack playbackBuilder;
  private InterfacePlaybackView view;
  private Map<String, List<InterpretStatusKeyFrame>> originalFrames;

  @Before
  public void setUp() {
    this.playbackBuilder = new AnimationModel.AnimationModelBuilder();
    FileReader in;
    try {
      in = new FileReader("./starter_code/smalldemo.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Please check the file path and name.");
    }
    AnimationReader.parseFile(in, this.playbackBuilder);
    this.view = new PlaybackView(20);
    this.controller = new Controller(this.playbackBuilder, this.view, 20);
    this.initOriginalFrames();
  }

  private void initOriginalFrames() {
    List<InterpretStatusKeyFrame> rect = new ArrayList<>();
    rect.add(new StatusKeyFrame(1, 200, 200, 50, 100, 0, new Color(255, 0, 0)));
    rect.add(new StatusKeyFrame(10, 200, 200, 50, 100, 0, new Color(255, 0, 0)));
    rect.add(new StatusKeyFrame(50, 300, 300, 50, 100, 0, new Color(255, 0, 0)));
    rect.add(new StatusKeyFrame(51, 300, 300, 50, 100, 0, new Color(255, 0, 0)));
    rect.add(new StatusKeyFrame(70, 300, 300, 25, 100, 0, new Color(255, 0, 0)));
    rect.add(new StatusKeyFrame(100, 200, 200, 25, 100, 0, new Color(255, 0, 0)));

    List<InterpretStatusKeyFrame> ellipse = new ArrayList<>();
    ellipse.add(new StatusKeyFrame(6, 440, 70, 120, 60, 0, new Color(0, 0, 255)));
    ellipse.add(new StatusKeyFrame(20, 440, 70, 120, 60, 0, new Color(0, 0, 255)));
    ellipse.add(new StatusKeyFrame(50, 440, 250, 120, 60, 0, new Color(0, 0, 255)));
    ellipse.add(new StatusKeyFrame(70, 440, 370, 120, 60, 0, new Color(0, 170, 85)));
    ellipse.add(new StatusKeyFrame(80, 440, 370, 120, 60, 0, new Color(0, 255, 0)));
    ellipse.add(new StatusKeyFrame(100, 440, 370, 120, 60, 0, new Color(0, 255, 0)));

    this.originalFrames = new LinkedHashMap<>();
    this.originalFrames.put("R", rect);
    this.originalFrames.put("C", ellipse);
  }

  @Test(expected = NullPointerException.class)
  public void testConstructorNullBuilder() {
    new Controller(null, this.view, 20);
  }

  @Test(expected = NullPointerException.class)
  public void testConstructorNullView() {
    new Controller(this.playbackBuilder, null, 20);
  }

  @Test
  public void testKeyframeAddEnd() {
    this.controller.start();
    assertEquals(this.originalFrames, this.originalFrames);
    InterfaceFrameChangeEvent event = new FrameChangeEvent(this.view, FrameChange.ADD,
            "R", 110, 200, 200, 25, 125, 10, new Color(255, 0, 0));
    this.controller.keyframeChanged(event);
    this.originalFrames.get("R").add(new StatusKeyFrame(110, 200, 200, 25, 125, 0,
            new Color(255, 0, 0)));
    assertEquals(this.originalFrames, this.view.getKeyframes());
  }

  @Test
  public void testMainKeyframeAddMiddleValue() {
    this.controller.start();
    InterfaceFrameChangeEvent event = new FrameChangeEvent(this.view, FrameChange.ADD,
            "R", 80, 200, 200, 25, 125, 1000, new Color(255, 0, 0));
    this.controller.keyframeChanged(event);
    this.originalFrames.get("R").add(5, new StatusKeyFrame(80, 200, 200, 25, 125, 0,
            new Color(255, 0, 0)));
    assertEquals(this.originalFrames, this.view.getKeyframes());
  }

  @Test
  public void testMainKeyframeAddFrontValue() {
    this.controller.start();
    InterfaceFrameChangeEvent event = new FrameChangeEvent(this.view, FrameChange.ADD,
            "C", 1, 440, 70, 120, 60, 1000, new Color(0, 0, 255));
    this.controller.keyframeChanged(event);
    this.originalFrames.get("C").add(0, new StatusKeyFrame(1, 440, 70, 120, 60, 0,
            new Color(0, 0, 255)));
    assertEquals(this.originalFrames, this.view.getKeyframes());
  }

  @Test
  public void testMainKeyframeEditText() {
    this.controller.start();
    InterfaceFrameChangeEvent event = new FrameChangeEvent(this.view, FrameChange.EDIT,
            "R", 100, 200, 200, 25, 125, 1000, new Color(255, 0, 0));
    this.controller.keyframeChanged(event);
    this.originalFrames.get("R").remove(5);
    this.originalFrames.get("R").add(new StatusKeyFrame(100, 200, 200, 25, 125, 0,
            new Color(255, 0, 0)));
    assertEquals(this.originalFrames, this.view.getKeyframes());
  }

  @Test
  public void testMainKeyframeDeleteFunction() {
    this.controller.start();
    InterfaceFrameChangeEvent event = new FrameChangeEvent(this.view, FrameChange.DELETE,
            "R", 100, 0, 0, 0, 0, 10000, new Color(0));
    this.controller.keyframeChanged(event);
    this.originalFrames.get("R").remove(5);
    assertEquals(this.originalFrames, this.view.getKeyframes());
  }

  @Test
  public void testShapeAdd() {
    this.controller.start();
    InterfaceShapeChangeEvent event = new ShapeChangeEvent(this.view, ShapeChange.ADD,
            "Rectangle", "rectangle");
    this.controller.shapeChanged(event);
    List<InterfaceShapeCell> shapes = new ArrayList<>();
    shapes.add(new ShapeCell("R", "Rectangle"));
    shapes.add(new ShapeCell("C", "Ellipse"));
    shapes.add(new ShapeCell("rectangle", "Rectangle"));
    assertEquals(shapes, this.view.getShapes());
  }

  @Test
  public void testShapeDelete() {
    this.controller.start();
    InterfaceShapeChangeEvent event = new ShapeChangeEvent(this.view, ShapeChange.DELETE,
            "", "R");
    this.controller.shapeChanged(event);
    List<InterfaceShapeCell> shapes = new ArrayList<>();
    shapes.add(new ShapeCell("C", "Ellipse"));
    assertEquals(shapes, this.view.getShapes());
  }

}
