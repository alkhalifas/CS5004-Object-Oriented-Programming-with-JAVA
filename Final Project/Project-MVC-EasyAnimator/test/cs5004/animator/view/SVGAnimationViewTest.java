package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import cs5004.animator.EasyAnimator;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.InterfaceAniModel;

import static org.junit.Assert.assertEquals;

/**
 * Represents the tester class for the SVGAnimationView class.
 */
public class SVGAnimationViewTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private AnimationModel.AnimationModelBuilder playbackBuilder;
  private InterfaceAniModel model;

  @Before
  public void setUp() {
    this.playbackBuilder = new AnimationModel.AnimationModelBuilder();
    System.setOut(new PrintStream(outContent));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorZero() {
    model = playbackBuilder.build();
    SVGAnimationView view = new SVGAnimationView(model, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegative() {
    model = playbackBuilder.build();
    SVGAnimationView view = new SVGAnimationView(model, -1);
  }

  @Test
  public void testXMLAppending() {
    this.model = this.playbackBuilder.build();
    InterfaceTextView view = new SVGAnimationView(model, 1);
    assertEquals(view.getText(), "");
    view.play();
    assertEquals(view.getText(), "<svg width=\"1000\" height=\"600\""
            + " version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">\n</svg>");
  }

  @Test
  public void testXMLAppendingWithShapes() {
    this.playbackBuilder.declareShape("rectangle", "Rectangle")
            .addMotion("rectangle", 0, 10, 10, 10, 30, 0, 255,
                    0, 5, 20,
                    10, 10, 30, 0, 255, 0)
            .addMotion("rectangle", 5, 20, 10, 10, 30, 0, 255,
                    0, 10, 20,
                    20, 10, 30, 0, 255, 0)
            .declareShape("Steve", "Ellipse")
            .addMotion("Steve", 0, 1, 1, 20, 20, 255, 0,
                    0, 10, 20,
                    20, 20, 20, 255, 0, 0);
    this.model = this.playbackBuilder.build();
    InterfaceTextView view = new SVGAnimationView(model, 1);
    view.play();
    assertEquals(view.getText(), "<svg width=\"1000\" height=\"600\""
            + " version=\"1.1\"\n    xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"rectangle\" x=\"10\" y=\"10\" width=\"10\" height=\"30\""
            + " fill=\"rgb(0,255,0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"x\""
            + " from=\"10\" to=\"20\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" attributeName=\""
            + "y\" from=\"10\" to=\"20\" fill=\"freeze\" />\n</rect>\n\n"
            + "<ellipse id=\"Steve\" cx=\"11\" cy=\"11\" rx=\"10\" ry=\"10\""
            + " fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10000ms\""
            + " attributeName=\"cx\" from=\"11\" to=\"30\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10000ms\""
            + " attributeName=\"cy\" from=\"11\" to=\"30\" fill=\"freeze\" />\n"
            + "</ellipse>\n\n</svg>");
  }

  @Test
  public void testPlay1() {
    this.playbackBuilder.declareShape("R", "Rectangle")
            .addMotion("R", 0, 10, 10, 10, 30, 0, 255,
                    0, 5, 20,
                    10, 10, 30, 0, 255, 0)
            .addMotion("R", 5, 20, 10, 10, 30, 0, 255,
                    0, 10, 20,
                    20, 10, 30, 0, 255, 0)
            .declareShape("C", "Ellipse")
            .addMotion("C", 0, 1, 1, 20, 20, 255, 0,
                    0, 10, 20,
                    20, 20, 20, 255, 0, 0);
    this.model = this.playbackBuilder.build();
    InterfaceTextView view = new SVGAnimationView(model, 1);
    view.play();
    assertEquals(view.getText(), "<svg width=\"1000\" height=\"600\" version=\"1.1\"\n" +
            "    xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"10\" y=\"10\" width=\"10\" height=\"30\" fill=\"rgb(0,255,0)\" "
            + "visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"5000ms\" attributeName=\"x\" "
            + "from=\"10\" to=\"20\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"5000ms\" " +
            "attributeName=\"y\" "
            + "from=\"10\" to=\"20\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"C\" cx=\"11\" cy=\"11\" rx=\"10\" ry=\"10\" fill=\"rgb(255,0,0)\" "
            + "visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10000ms\" "
            + "attributeName=\"cx\" from=\"11\" to=\"30\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"10000ms\" "
            + "attributeName=\"cy\" from=\"11\" to=\"30\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>");
  }

  @Test
  public void testXMLAppendingWithMotions() {
    this.playbackBuilder.declareShape("rectangle", "Rectangle")
            .addMotion("rectangle", 0, 10, 10, 10, 30, 0, 255,
                    0, 5, 20,
                    10, 10, 30, 0, 255, 0)
            .addMotion("rectangle", 5, 20, 10, 10, 30, 0, 255,
                    0, 8, 20,
                    10, 10, 30, 0, 0, 255);
    this.model = this.playbackBuilder.build();
    InterfaceTextView view = new SVGAnimationView(model, 5);
    view.play();
    assertEquals(view.getText(), "<svg width=\"1000\" height=\"600\""
            + " version=\"1.1\"\n    xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"rectangle\" x=\"10\" y=\"10\" width=\"10\" height=\"30\""
            + " fill=\"rgb(0,255,0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"0ms\" dur=\"1000ms\""
            + " attributeName=\"x\" from=\"10\" to=\"20\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"1000ms\" dur=\"600ms\" attributeName=\""
            + "fill\" from=\"rgb(0,255,0)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n"
            + "</rect>\n\n</svg>");
  }

  @Test
  public void testXMLAppendingFromMainCommandline() {
    EasyAnimator.main(new String[]{"-view", "svg", "-in", "./starter_code/smalldemo.txt"});
    assertEquals("<svg width=\"360\" height=\"360\" version=\"1.1\"\n"
            + "    xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"0\" y=\"130\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\""
            + " visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\""
            + " attributeName=\"x\" from=\"0\" to=\"100\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"40000ms\" "
            + "attributeName=\"y\" from=\"130\" to=\"230\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"51000ms\" dur=\"19000ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"70000ms\" dur=\"30000ms\" "
            + "attributeName=\"x\" from=\"100\" to=\"0\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"70000ms\" dur=\"30000ms\" "
            + "attributeName=\"y\" from=\"230\" to=\"130\" fill=\"freeze\" />\n"
            + "</rect>\n\n"
            + "<ellipse id=\"C\" cx=\"300\" cy=\"30\" rx=\"60\" ry=\"30\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"20000ms\" dur=\"30000ms\" "
            + "attributeName=\"cy\" from=\"30\" to=\"210\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\" "
            + "attributeName=\"cy\" from=\"210\" to=\"330\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"50000ms\" dur=\"20000ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" "
            + "fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"70000ms\" dur=\"10000ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" "
            + "/>\n</ellipse>\n\n</svg>", outContent.toString());
  }

  @Test
  public void testXMLAppendingMainInputAndOutput() {
    EasyAnimator.main(new String[]{"-in", "./starter_code/toh-3.txt",
            "-out", "./starter_code/toh-3-output.svg",
            "-view", "svg"});
    List<String> lines;
    try {
      lines = Files.readAllLines(Paths.get("./starter_code/toh-3-output.svg"));
    } catch (IOException e) {
      throw new IllegalArgumentException("Please check file paths.");
    }
    StringBuilder output = new StringBuilder();
    for (String line : lines) {
      output.append(line);
      output.append("\n");
    }

    assertEquals("<svg width=\"410\" height=\"220\" version=\"1.1\"\n" +
                    "    xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "<rect id=\"disk1\" x=\"45\" y=\"130\" width=\"20\" height=\"30\" "
                    + "fill=\"rgb(0,49,90)\" visibility=\"visible\" >\n" +
                    "    <animate attributeType=\"xml\" begin=\"25000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"130\" to=\"0\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"36000ms\" dur=\"10000ms\" "
                    + "attributeName=\"x\" from=\"45\" to=\"345\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"47000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"190\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"89000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"190\" to=\"0\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"100000ms\" dur=\"10000ms\" "
                    + "attributeName=\"x\" from=\"345\" to=\"195\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"111000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"160\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"153000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"160\" to=\"0\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"164000ms\" dur=\"10000ms\" "
                    + "attributeName=\"x\" from=\"195\" to=\"45\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"175000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"190\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"217000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"190\" to=\"0\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"228000ms\" dur=\"10000ms\" "
                    + "attributeName=\"x\" from=\"45\" to=\"345\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"239000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"130\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"249000ms\" dur=\"8000ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,49,90)\" to=\"rgb(0,255,0)\" "
                    + "fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "\n" +
                    "<rect id=\"disk2\" x=\"22\" y=\"160\" width=\"65\" height=\"30\" "
                    + "fill=\"rgb(6,247,41)\" visibility=\"visible\" >\n" +
                    "    <animate attributeType=\"xml\" begin=\"57000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"160\" to=\"0\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"68000ms\" dur=\"10000ms\" "
                    + "attributeName=\"x\" from=\"22\" to=\"172\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"79000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"190\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"185000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"190\" to=\"0\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"196000ms\" dur=\"10000ms\" "
                    + "attributeName=\"x\" from=\"172\" to=\"322\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"207000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"160\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"217000ms\" dur=\"8000ms\" "
                    + "attributeName=\"fill\" from=\"rgb(6,247,41)\" to=\"rgb(0,255,0)\" "
                    + "fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "\n" +
                    "<rect id=\"disk3\" x=\"0\" y=\"190\" width=\"110\" height=\"30\" "
                    + "fill=\"rgb(11,45,175)\" visibility=\"visible\" >\n" +
                    "    <animate attributeType=\"xml\" begin=\"121000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"190\" to=\"0\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"132000ms\" dur=\"10000ms\" "
                    + "attributeName=\"x\" from=\"0\" to=\"300\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"143000ms\" dur=\"10000ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"190\" fill=\"freeze\" />\n" +
                    "    <animate attributeType=\"xml\" begin=\"153000ms\" dur=\"8000ms\" "
                    + "attributeName=\"fill\" from=\"rgb(11,45,175)\" to=\"rgb(0,255,0)\" "
                    + "fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "\n" +
                    "</svg>",
            output.toString().trim());
  }

  @Test
  public void testXMLAppendingTOH3() {
    EasyAnimator.main(new String[]{"-view", "svg", "-in", "./starter_code/toh-3.txt",
            "-out", "./starter_code/toh-3-output-20.svg", "-speed", "20"});
    List<String> lines;
    try {
      lines = Files.readAllLines(Paths.get("./starter_code/toh-3-output-20.svg"));
    } catch (IOException e) {
      throw new IllegalArgumentException("Please check file paths.");
    }
    StringBuilder output = new StringBuilder();
    for (String line : lines) {
      output.append(line);
      output.append("\n");
    }

    assertEquals("<svg width=\"410\" height=\"220\" version=\"1.1\"\n"
                    + "    xmlns=\"http://www.w3.org/2000/svg\">\n"
                    + "<rect id=\"disk1\" x=\"45\" y=\"130\" width=\"20\" height=\"30\" "
                    + "fill=\"rgb(0,49,90)\" visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"1250ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"130\" to=\"0\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"1800ms\" dur=\"500ms\" "
                    + "attributeName=\"x\" from=\"45\" to=\"345\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"2350ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"190\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"4450ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"190\" to=\"0\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"500ms\" "
                    + "attributeName=\"x\" from=\"345\" to=\"195\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"5550ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"160\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"7650ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"160\" to=\"0\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"8200ms\" dur=\"500ms\" "
                    + "attributeName=\"x\" from=\"195\" to=\"45\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"8750ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"190\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"10850ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"190\" to=\"0\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"11400ms\" dur=\"500ms\" "
                    + "attributeName=\"x\" from=\"45\" to=\"345\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"11950ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"130\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"12450ms\" dur=\"400ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,49,90)\" to=\"rgb(0,255,0)\" "
                    + "fill=\"freeze\" />\n" + "</rect>\n"
                    + "\n" + "<rect id=\"disk2\" x=\"22\" y=\"160\" width=\"65\" height=\"30\" "
                    + "fill=\"rgb(6,247,41)\" visibility=\"visible\" >\n"
                    +
                    "    <animate attributeType=\"xml\" begin=\"2850ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"160\" to=\"0\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"3400ms\" dur=\"500ms\" "
                    + "attributeName=\"x\" from=\"22\" to=\"172\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"3950ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"190\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"9250ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"190\" to=\"0\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"9800ms\" dur=\"500ms\" "
                    + "attributeName=\"x\" from=\"172\" to=\"322\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"10350ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"160\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"10850ms\" dur=\"400ms\" "
                    + "attributeName=\"fill\" from=\"rgb(6,247,41)\" to=\"rgb(0,255,0)\" "
                    + "fill=\"freeze\" />\n" + "</rect>\n" + "\n"
                    + "<rect id=\"disk3\" x=\"0\" y=\"190\" width=\"110\" height=\"30\" "
                    + "fill=\"rgb(11,45,175)\" visibility=\"visible\" >\n"
                    + "    <animate attributeType=\"xml\" begin=\"6050ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"190\" to=\"0\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"6600ms\" dur=\"500ms\" "
                    + "attributeName=\"x\" from=\"0\" to=\"300\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"7150ms\" dur=\"500ms\" "
                    + "attributeName=\"y\" from=\"0\" to=\"190\" fill=\"freeze\" />\n"
                    + "    <animate attributeType=\"xml\" begin=\"7650ms\" dur=\"400ms\" "
                    + "attributeName=\"fill\" from=\"rgb(11,45,175)\" to=\"rgb(0,255,0)\" "
                    + "fill=\"freeze\" />\n" + "</rect>\n" + "\n" + "</svg>",
            output.toString().trim());
  }

}

