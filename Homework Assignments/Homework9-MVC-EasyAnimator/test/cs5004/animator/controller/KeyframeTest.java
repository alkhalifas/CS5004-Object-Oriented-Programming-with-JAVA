package cs5004.animator.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import cs5004.animator.controller.InterfaceInterpretKeyFrame;
import cs5004.animator.controller.Keyframe;


/**
 * Testing class for keyframe class to ensure proper functionality.
 */
public class KeyframeTest {
  private InterfaceInterpretKeyFrame keyframeOne;
  private InterfaceInterpretKeyFrame keyframeTwo;

  @Before
  public void setUp() {
    keyframeOne = new Keyframe(1, 2, 3, 4, 5, new Color(1, 2, 3));
    keyframeTwo = new Keyframe(100, 20, 30, 400, 500, new Color(255, 0, 255));
  }

  @Test
  public void testGetX() {
    assertEquals(2, keyframeOne.getX());
    assertEquals(20, keyframeTwo.getX());
  }

  @Test
  public void testGetY() {
    assertEquals(3, keyframeOne.getY());
    assertEquals(30, keyframeTwo.getY());
  }

  @Test
  public void testGetTime() {
    assertEquals(1, keyframeOne.getTime());
    assertEquals(100, keyframeTwo.getTime());
  }

  @Test
  public void testGetWidth() {
    assertEquals(4, keyframeOne.getWidth());
    assertEquals(400, keyframeTwo.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(5, keyframeOne.getHeight());
    assertEquals(500, keyframeTwo.getHeight());
  }

  @Test
  public void testGetColor() {
    assertEquals(new Color(1, 2, 3), keyframeOne.getColor());
    assertEquals(new Color(255, 0, 255), keyframeTwo.getColor());
  }

  @Test
  public void testToString() {
    assertEquals(keyframeOne.toString(), "t-1: at (2, 3), 4x5, color: (1, 2, 3)");
    assertEquals(keyframeTwo.toString(), "t-100: at (20, 30), 400x500, color: (255, 0, 255)");
  }
}