package cs5004.animator.util;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import cs5004.animator.model.InterfaceAniModel;
import cs5004.animator.util.AnimationBuilder;

/**
 * A helper to read animation data and construct an animation from it.
 */
public class AnimationReader {

  /**
   * A factory for producing new animations, given a source of shapes and a
   * playbackBuilder for constructing animations.
   *
   * <p>
   * The input file format consists of two types of lines:
   * <ul>
   * <li>Shape lines: the keyword "shape" followed by two identifiers (i.e.
   * alphabetic strings with no spaces), giving the unique name of the shape,
   * and the type of shape it is.</li>
   * <li>Motion lines: the keyword "motion" followed by an identifier giving the name
   * of the shape to move, and 16 integers giving the initial and final conditions of the motion:
   * eight numbers giving the time, the x and y coordinates, the width and height,
   * and the red, green and blue color values at the start of the motion; followed by 
   * eight numbers for the end of the motion.  See {@link AnimationBuilder#addMotion}</li>
   * </ul>
   * </p>
   *
   * @param readable The source of data for the animation
   * @param playbackBuilder  A playbackBuilder for helping to construct a new animation
   * @return
   */
  public static InterfaceAniModel parseFile(Readable readable, AnimationBuilder playbackBuilder) {
    Objects.requireNonNull(readable, "Must have non-null readable source");
    Objects.requireNonNull(playbackBuilder, "Must provide a non-null AnimationBuilder");
    Scanner s = new Scanner(readable);
    // Split at whitespace, and ignore # comment lines
    s.useDelimiter(Pattern.compile("(\\p{Space}+|#.*)+")); 
    while (s.hasNext()) {
      String word = s.next();
      switch (word) {
        case "canvas":
          readCanvas(s, playbackBuilder);
          break;
        case "shape":
          readShape(s, playbackBuilder);
          break;
        case "motion":
          readMotion(s, playbackBuilder);
          break;
        default:
          throw new IllegalStateException("Unexpected keyword: " + word + s.nextLine());
      }
    }
    return playbackBuilder.build();
  }

  private static void readCanvas(Scanner s, AnimationBuilder playbackBuilder) {
    int[] vals = new int[4];
    String[] fieldNames = {"left", "top", "width", "height"};
    for (int i = 0; i < 4; i++) {
      vals[i] = getInt(s, "Canvas", fieldNames[i]);
    }
    playbackBuilder.setBounds(vals[0], vals[1], vals[2], vals[3]);
  }

  private static void readShape(Scanner s, AnimationBuilder playbackBuilder) {
    String name;
    String type;
    if (s.hasNext()) {
      name = s.next();
    } else {
      throw new IllegalStateException("Shape: Expected a name, but no more input available");
    }
    if (s.hasNext()) {
      type = s.next();
    } else {
      throw new IllegalStateException("Shape: Expected a type, but no more input available");
    }
    playbackBuilder.declareShape(name, type);
  }

  private static void readMotion(Scanner s, AnimationBuilder playbackBuilder) {
    String[] fieldNames = new String[]{
      "initial time",
      "initial x-coordinate", "initial y-coordinate",
      "initial width", "initial height",
      "initial red value", "initial green value", "initial blue value",
      "final time",
      "final x-coordinate", "final y-coordinate",
      "final width", "final height",
      "final red value", "final green value", "final blue value",
      "initial rotationDegree", "final rotationDegree"
    };
    int[] vals = new int[18];
    String name;
    if (s.hasNext()) {
      name = s.next();
    } else {
      throw new IllegalStateException("Motion: Expected a shape name, but no more input available");
    }
    for (int i = 0; i < 16; i++) {
      vals[i] = getInt(s, "Motion", fieldNames[i]);
    }
    if (s.hasNextInt()) {
      vals[16] = getInt(s, "Motion", fieldNames[16]);
      vals[17] = getInt(s, "Motion", fieldNames[17]);
    }
    else {
      playbackBuilder.addMotion(name,
              vals[0 ], vals[1 ], vals[2 ], vals[3 ], vals[4 ], vals[5 ], vals[6 ], vals[7 ],
              vals[8 ], vals[9 ], vals[10], vals[11], vals[12], vals[13], vals[14], vals[15]);
      return;
    }

    playbackBuilder.addMotion(name,
            vals[0 ], vals[1 ], vals[2 ], vals[3 ], vals[4 ], vals[5 ], vals[6 ], vals[7 ],
            vals[8 ], vals[9 ], vals[10], vals[11], vals[12], vals[13], vals[14], vals[15],
            vals[16], vals[17]);
  }
  
  private static int getInt(Scanner s, String label, String fieldName) {
    if (s.hasNextInt()) {
      return s.nextInt();
    } else if (s.hasNext()) {
      throw new IllegalStateException(
              String.format("%s: expected integer for %s, got: %s", label, fieldName, s.next()));
    } else {
      throw new IllegalStateException(
              String.format("%s: expected integer for %s, but no more input available",
                            label, fieldName));
    }
  }

}
