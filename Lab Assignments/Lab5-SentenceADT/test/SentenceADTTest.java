import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * This class represents a test class for sentence.
 */
public class SentenceADTTest {

  private SentenceADT<String> stringList;

  @Before
  public void setup() {

    stringList = new SentenceImpl<String>();
  }

  // Testing the use of addFront
  @Test
  public void testAddFrontSentence() {
    stringList.addFront("Amgen");
    stringList.addFront("Like");
    stringList.addFront(("I"));
    assertEquals("(I Like Amgen)", stringList.toString());
  }

  // Testing the use of addBack
  @Test
  public void testAddBackSentence() {
    stringList.addBack("For");
    stringList.addBack("The");
    stringList.addBack(("Patients"));
    assertEquals("(For The Patients)", stringList.toString());
  }

  // Testing the use of addBack with Punctuation
  @Test
  public void testAddBackSentencePunc() {
    stringList.addBack("For");
    stringList.addBack("The");
    stringList.addBack(("Patients"));
    stringList.addBack(("!"));
    assertEquals("(For The Patients !)", stringList.toString());
  }

  // Testing the use of add within a sentence.
  @Test
  public void testAddVariousPositions() {
    stringList.addFront("Amgen");
    stringList.addFront("Like");
    stringList.addFront(("I"));
    stringList.add(1, "really");
    assertEquals("(I really Like Amgen)", stringList.toString());
  }

  // Testing the use of add within a sentence.
  @Test
  public void testGetSize() {
    stringList.addFront("Amgen");
    stringList.addFront("Like");
    stringList.addFront(("I"));
    stringList.add(1, "really");
    assertEquals(4, stringList.getSize());
  }

  // Testing the use of add within a sentence.
  @Test
  public void testCounting() {
    stringList.addFront("Amgen");
    stringList.addFront("Like");
    stringList.addFront(("I"));
    stringList.add(1, "really");
    assertEquals(4, stringList.getSize());
  }

  @Test
  public void testStringList() {
    stringList.addFront("won");
    stringList.addFront("Patriots");
    stringList.addBack("Super");
    stringList.addBack("Bowl");
    stringList.add(2, "the");
    assertEquals("(Patriots won the Super Bowl)", stringList.toString());
    assertEquals(5, stringList.getSize());
    assertEquals("Super", stringList.get(3));

    stringList.remove("Patriots");
    stringList.addFront("Falcons");
    stringList.add(1, "did");
    stringList.add(2, "not");
    stringList.remove("won");
    stringList.add(3, "win");
    assertEquals("(Falcons did not win the Super Bowl)", stringList.toString());
    assertEquals(7, stringList.getSize());

  }

  @Test
  public void testMap() {
    //convert the list of strings above to a list that contains the length of
    // each word in the list
    String sentence = "The quick brown fox jumps over the lazy dog";
    String []words = sentence.split("\\s+");
    for (String w:words) {
      stringList.addBack(w);
    }

    SentenceADT<Integer> wordLengths = stringList.map(s -> s.length());
    assertEquals("The mapped list's length does not match the original list!",
            stringList.getSize(),wordLengths.getSize());
    for ( int i = 0; i < words.length; i++) {
      assertEquals(words[i].length(),(int)wordLengths.get(i));
    }
  }

}