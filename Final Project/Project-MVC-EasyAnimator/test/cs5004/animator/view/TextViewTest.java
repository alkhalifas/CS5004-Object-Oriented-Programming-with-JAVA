package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import cs5004.animator.EasyAnimator;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.InterfaceAniModel;


/**
 * THis class tests the functionality of the text output view to ensure methods are correct.
 */
public class TextViewTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private AnimationModel.AnimationModelBuilder aniBuilder;
  private InterfaceAniModel aniModel;

  @Before
  public void setUp() {
    this.aniBuilder = new AnimationModel.AnimationModelBuilder();
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void testAniModelText() {
    this.aniModel = this.aniBuilder.build();
    TextView view = new TextView(aniModel);
    assertEquals(view.getText(), "");
  }

  @Test
  public void testAniModelTextwithShapes() {
    this.aniBuilder.declareShape("R", "Rectangle")
            .addMotion("R", 0, 5, 5, 10, 10, 0, 0,
                    255, 5, 10,
                    10, 15, 15, 0, 255, 0)
            .addMotion("R", 5, 10, 10, 15, 15, 0,
                    255, 0, 15, 20,
                    20, 10, 30, 0, 255, 0)
            .declareShape("C", "Ellipse")
            .addMotion("C", 0, 5, 5, 20, 20, 255,
                    255, 255, 10, 20,
                    20, 20, 20, 255, 0, 0);
    this.aniModel = this.aniBuilder.build();
    TextView view = new TextView(aniModel);
    assertEquals(view.getText(), "");
    view.play();
    assertEquals("Create Rectangle R with center at Point2D.Double[0.0, 0.0], "
                    + "width of 0, height of 0.\n"
                    + "Transform R from location (5, 5) to (10, 10) and color "
                    + "java.awt.Color[r=0,g=0,b=255] to java.awt.Color[r=0,g=255,b=0] "
                    + "over t=0 to 5.\n" + "Transform R from location (10, 10) to (20, 20) and "
                    + "color java.awt.Color[r=0,g=255,b=0] to java.awt.Color[r=0,g=255,b=0] over "
                    + "t=5 to 15.\n" + "\n"
                    + "Create Ellipse C with center at Point2D.Double[0.0, 0.0], "
                    + "width of 0, height of 0.\n"
                    + "Transform C from location (5, 5) to (20, 20) "
                    + "and color java.awt.Color[r=255,g=255,b=255] to "
                    + "java.awt.Color[r=255,g=0,b=0] over t=0 to 10.",
            view.getText());
  }

  @Test
  public void testAniModelPlay() {
    this.aniModel = this.aniBuilder.build();
    TextView view = new TextView(aniModel);
    view.play();
    assertEquals(view.getText(), "");
  }

  @Test
  public void testAniModelTextPlayRectangleAndEllipse() {
    this.aniBuilder.declareShape("R", "Rectangle")
            .addMotion("R", 0, 5, 5, 10, 10, 0,
                    0, 255, 5, 10,
                    10, 15, 15, 0, 255, 0)

            .addMotion("R", 5, 10, 10, 15, 15, 0,
                    255, 0, 15, 20,
                    20, 10, 30, 0, 255, 0)

            .declareShape("C", "Ellipse")

            .addMotion("C", 0, 5, 5, 20, 20, 255,
                    255, 255, 10, 20,
                    20, 20, 20, 255, 0, 0);

    this.aniModel = this.aniBuilder.build();
    TextView view = new TextView(aniModel);
    view.play();
    assertEquals(view.getText(), "Create Rectangle R with center at Point2D."
            + "Double[0.0, 0.0], width of 0, height of 0.\n" +
            "Transform R from location (5, 5) to (10, 10) and color java.awt.Color"
            + "[r=0,g=0,b=255] to java.awt.Color[r=0,g=255,b=0] over t=0 to 5.\n" +
            "Transform R from location (10, 10) to (20, 20) and color java.awt.Color"
            + "[r=0,g=255,b=0] to java.awt.Color[r=0,g=255,b=0] over t=5 to 15.\n" + "\n"
            + "Create Ellipse C with center at Point2D.Double[0.0, 0.0], width of 0, height of 0.\n"
            + "Transform C from location (5, 5) to (20, 20) and color java.awt.Color"
            + "[r=255,g=255,b=255] to java.awt.Color[r=255,g=0,b=0] over t=0 to 10.");
  }

  @Test
  public void testAniModelWithMotions() {
    this.aniBuilder.declareShape("R", "Rectangle")
            .addMotion("R", 0, 5, 5, 10, 10, 0,
                    0, 255, 5, 10,
                    10, 15, 15, 0, 255, 0)

            .addMotion("R", 5, 10, 10, 15, 15, 0,
                    255, 0, 15, 20,
                    20, 10, 30, 0, 255, 0)

            .declareShape("C", "Ellipse")

            .addMotion("C", 0, 5, 5, 20, 20, 0,
                    0, 255, 10, 20,
                    20, 20, 20, 100, 0, 0)

            .addMotion("C", 10, 20, 20, 20, 20, 100,
                    0, 0, 22, 30,
                    30, 20, 20, 255, 0, 0);

    this.aniModel = this.aniBuilder.build();
    TextView view = new TextView(aniModel);
    view.play();
    assertEquals("Create Rectangle R with center at Point2D.Double[0.0, 0.0], "
                    + "width of 0, height of 0.\n"
                    + "Transform R from location (5, 5) to (10, 10) and color java.awt.Color"
                    + "[r=0,g=0,b=255] to java.awt.Color[r=0,g=255,b=0] over t=0 to 5.\n"
                    + "Transform R from location (10, 10) to (20, 20) and color java.awt.Color["
                    + "r=0,g=255,b=0] to java.awt.Color[r=0,g=255,b=0] over t=5 to 15.\n"
                    + "\n"
                    + "Create Ellipse C with center at Point2D.Double[0.0, 0.0], width of 0, "
                    + "height of 0.\n"
                    + "Transform C from location (5, 5) to (20, 20) and color java.awt.Color["
                    + "r=0,g=0,b=255] to java.awt.Color[r=100,g=0,b=0] over t=0 to 10.\n"
                    + "Transform C from location (20, 20) to (30, 30) and color java.awt.Color"
                    + "[r=100,g=0,b=0] to java.awt.Color[r=255,g=0,b=0] over t=10 to 22.",
            view.getText());
  }


  @Test
  public void testAniModelMainArguments() {
    EasyAnimator.main(new String[]{"-view", "text", "-in", "./starter_code/toh-3.txt"});
    assertEquals(outContent.toString(), "Create Rectangle disk1 with center at "
            + "Point2D.Double[0.0, 0.0], width of 0, height of 0.\n"
            + "Transform disk1 from location (190, 180) to (190, 180) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=1 to 1.\n"
            + "Transform disk1 from location (190, 180) to (190, 180) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=1 to 25.\n"
            + "Transform disk1 from location (190, 180) to (190, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=25 to 35.\n"
            + "Transform disk1 from location (190, 50) to (190, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=35 to 36.\n"
            + "Transform disk1 from location (190, 50) to (490, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=36 to 46.\n"
            + "Transform disk1 from location (490, 50) to (490, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=46 to 47.\n"
            + "Transform disk1 from location (490, 50) to (490, 240) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=47 to 57.\n"
            + "Transform disk1 from location (490, 240) to (490, 240) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=57 to 89.\n"
            + "Transform disk1 from location (490, 240) to (490, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=89 to 99.\n"
            + "Transform disk1 from location (490, 50) to (490, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=99 to 100.\n"
            + "Transform disk1 from location (490, 50) to (340, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=100 to 110.\n"
            + "Transform disk1 from location (340, 50) to (340, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=110 to 111.\n"
            + "Transform disk1 from location (340, 50) to (340, 210) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=111 to 121.\n"
            + "Transform disk1 from location (340, 210) to (340, 210) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=121 to 153.\n"
            + "Transform disk1 from location (340, 210) to (340, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=153 to 163.\n"
            + "Transform disk1 from location (340, 50) to (340, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=163 to 164.\n"
            + "Transform disk1 from location (340, 50) to (190, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=164 to 174.\n"
            + "Transform disk1 from location (190, 50) to (190, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=174 to 175.\n"
            + "Transform disk1 from location (190, 50) to (190, 240) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=175 to 185.\n"
            + "Transform disk1 from location (190, 240) to (190, 240) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=185 to 217.\n"
            + "Transform disk1 from location (190, 240) to (190, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=217 to 227.\n"
            + "Transform disk1 from location (190, 50) to (190, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=227 to 228.\n"
            + "Transform disk1 from location (190, 50) to (490, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=228 to 238.\n"
            + "Transform disk1 from location (490, 50) to (490, 50) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=238 to 239.\n"
            + "Transform disk1 from location (490, 50) to (490, 180) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=49,b=90] over t=239 to 249.\n"
            + "Transform disk1 from location (490, 180) to (490, 180) and color java.awt.Color"
            + "[r=0,g=49,b=90] to java.awt.Color[r=0,g=255,b=0] over t=249 to 257.\n"
            + "Transform disk1 from location (490, 180) to (490, 180) and color java.awt.Color"
            + "[r=0,g=255,b=0] to java.awt.Color[r=0,g=255,b=0] over t=257 to 302.\n"
            + "\n" +
            "Create Rectangle disk2 with center at Point2D.Double[0.0, 0.0], width of 0, "
            + "height of 0.\n" +
            "Transform disk2 from location (167, 210) to (167, 210) and color java.awt.Color["
            + "r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=1 to 1.\n"
            + "Transform disk2 from location (167, 210) to (167, 210) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=1 to 57.\n"
            + "Transform disk2 from location (167, 210) to (167, 50) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=57 to 67.\n"
            + "Transform disk2 from location (167, 50) to (167, 50) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=67 to 68.\n"
            + "Transform disk2 from location (167, 50) to (317, 50) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=68 to 78.\n"
            + "Transform disk2 from location (317, 50) to (317, 50) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=78 to 79.\n"
            + "Transform disk2 from location (317, 50) to (317, 240) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=79 to 89.\n"
            + "Transform disk2 from location (317, 240) to (317, 240) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=89 to 185.\n"
            + "Transform disk2 from location (317, 240) to (317, 50) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=185 to 195.\n"
            + "Transform disk2 from location (317, 50) to (317, 50) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=195 to 196.\n"
            + "Transform disk2 from location (317, 50) to (467, 50) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=196 to 206.\n"
            + "Transform disk2 from location (467, 50) to (467, 50) and color java.awt.Color["
            + "r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=206 to 207.\n"
            + "Transform disk2 from location (467, 50) to (467, 210) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=6,g=247,b=41] over t=207 to 217.\n"
            + "Transform disk2 from location (467, 210) to (467, 210) and color java.awt.Color"
            + "[r=6,g=247,b=41] to java.awt.Color[r=0,g=255,b=0] over t=217 to 225.\n"
            + "Transform disk2 from location (467, 210) to (467, 210) and color java.awt.Color"
            + "[r=0,g=255,b=0] to java.awt.Color[r=0,g=255,b=0] over t=225 to 302.\n"
            + "\n" +
            "Create Rectangle disk3 with center at Point2D.Double[0.0, 0.0], width of 0, "
            + "height of 0.\n"
            + "Transform disk3 from location (145, 240) to (145, 240) and color java.awt."
            + "Color[r=11,g=45,b=175] to java.awt.Color[r=11,g=45,b=175] over t=1 to 1.\n"
            + "Transform disk3 from location (145, 240) to (145, 240) and color java.awt."
            + "Color[r=11,g=45,b=175] to java.awt.Color[r=11,g=45,b=175] over t=1 to 121.\n"
            + "Transform disk3 from location (145, 240) to (145, 50) and color java.awt"
            + ".Color[r=11,g=45,b=175] to java.awt.Color[r=11,g=45,b=175] over t=121 to 131.\n"
            + "Transform disk3 from location (145, 50) to (145, 50) and color java.awt"
            + ".Color[r=11,g=45,b=175] to java.awt.Color[r=11,g=45,b=175] over t=131 to 132.\n"
            + "Transform disk3 from location (145, 50) to (445, 50) and color java.awt"
            + ".Color[r=11,g=45,b=175] to java.awt.Color[r=11,g=45,b=175] over t=132 to 142.\n"
            + "Transform disk3 from location (445, 50) to (445, 50) and color java.awt."
            + "Color[r=11,g=45,b=175] to java.awt.Color[r=11,g=45,b=175] over t=142 to 143.\n"
            + "Transform disk3 from location (445, 50) to (445, 240) and color java.awt"
            + ".Color[r=11,g=45,b=175] to java.awt.Color[r=11,g=45,b=175] over t=143 to 153.\n"
            + "Transform disk3 from location (445, 240) to (445, 240) and color java.awt."
            + "Color[r=11,g=45,b=175] to java.awt.Color[r=0,g=255,b=0] over t=153 to 161.\n"
            + "Transform disk3 from location (445, 240) to (445, 240) and color java.awt."
            + "Color[r=0,g=255,b=0] to java.awt.Color[r=0,g=255,b=0] over t=161 to 302.");
  }


  @Test
  public void testAniModelMainArgumentsSmallDemoWithOutput() {
    EasyAnimator.main(new String[]{"-view", "text", "-in", "./starter_code/smalldemo.txt", "-out",
        "./starter_code/code.txt"});
    List<String> lines;
    try {
      lines = Files.readAllLines(Paths.get("tmp/tmp.txt"));
    } catch (IOException e) {
      throw new IllegalArgumentException("smh my head");
    }
    StringBuilder output = new StringBuilder();
    for (String line : lines) {
      output.append(line);
      output.append("\n");
    }

    assertEquals("Create Rectangle R with center at Point2D.Double[0.0, 0.0]"
                    + ", width of 0, height of 0.\n"
                    + "Transform R from location (200, 200) to (200, 200) and color java."
                    + "awt.Color[r=255,g=0,b=0] to java.awt.Color[r=255,g=0,b=0] over t=1 to 10.\n"
                    + "Transform R from location (200, 200) to (300, 300) and color java."
                    + "awt.Color[r=255,g=0,b=0] to java.awt.Color[r=255,g=0,b=0] over t=10 to 50.\n"
                    + "Transform R from location (300, 300) to (300, 300) and color java."
                    + "awt.Color[r=255,g=0,b=0] to java.awt.Color[r=255,g=0,b=0] over t=50 to 51.\n"
                    + "Transform R from location (300, 300) to (300, 300) and color java."
                    + "awt.Color[r=255,g=0,b=0] to java.awt.Color[r=255,g=0,b=0] over t=51 to 70.\n"
                    + "Transform R from location (300, 300) to (200, 200) and color java."
                    + "awt.Color[r=255,g=0,b=0] to java.awt.Color[r=255,g=0,b=0] over t=70 to "
                    + "100.\n"
                    + "\n"
                    + "Create Ellipse C with center at Point2D.Double[0.0, 0.0], width "
                    + "of 0, height of 0.\n"
                    + "Transform C from location (440, 70) to (440, 70) and color java."
                    + "awt.Color[r=0,g=0,b=255] to java.awt.Color[r=0,g=0,b=255] over t=6 to 20.\n"
                    + "Transform C from location (440, 70) to (440, 250) and color java."
                    + "awt.Color[r=0,g=0,b=255] to java.awt.Color[r=0,g=0,b=255] over t=20 to 50.\n"
                    + "Transform C from location (440, 250) to (440, 370) and color java."
                    + "awt.Color[r=0,g=0,b=255] to java.awt.Color[r=0,g=170,b=85] over t=50 "
                    + "to 70.\n"
                    + "Transform C from location (440, 370) to (440, 370) and color java."
                    + "awt.Color[r=0,g=170,b=85] to java.awt.Color[r=0,g=255,b=0] over t=70 "
                    + "to 80.\n"
                    + "Transform C from location (440, 370) to (440, 370) and color java."
                    + "awt.Color[r=0,g=255,b=0] to java.awt.Color[r=0,g=255,b=0] over t=80 to 100.",
            output.toString().trim());
  }
}
