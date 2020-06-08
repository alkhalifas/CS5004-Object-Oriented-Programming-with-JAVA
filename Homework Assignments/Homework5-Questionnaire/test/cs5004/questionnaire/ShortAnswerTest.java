package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for the ShortAnswer class in which various cases are tested.
 */
public class ShortAnswerTest {
  private ShortAnswer simpleShortAnswerQuestion;
  private ShortAnswer emptyShortAnswerQuestion;
  private ShortAnswer nullShortAnswerQuestion;
  private ShortAnswer falseShortAnswerQuestion;


  @Before
  public void setup() {
    simpleShortAnswerQuestion = new ShortAnswer("Tell me about your life?", true);
  }

  /**
   * Testing the getPrompt() method.
   */
  @Test
  public void testGetPromptValid() {
    assertEquals("Tell me about your life?", simpleShortAnswerQuestion.getPrompt());
  }

  /**
   * Testing the getPrompt() method with false requirement.
   */
  @Test
  public void testGetPromptValidFalse() {
    falseShortAnswerQuestion = new ShortAnswer("Tell me about your life?", false);
    assertEquals("Tell me about your life?", falseShortAnswerQuestion.getPrompt());
  }

  /**
   * Testing the getPrompt() method for an empty question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetPromptEmpty() {
    emptyShortAnswerQuestion = new ShortAnswer("", true);
    assertEquals("Tell me about your life?", emptyShortAnswerQuestion.getPrompt());
  }

  /**
   * Testing the getPrompt() method for an null question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetPromptNull() {
    nullShortAnswerQuestion = new ShortAnswer(null, true);
    assertEquals("Tell me about your life?", nullShortAnswerQuestion.getPrompt());
  }

  /**
   * Testing the isRequired() method when true.
   */
  @Test
  public void testIsRequiredTrue() {
    assertEquals(true, simpleShortAnswerQuestion.isRequired());
  }

  /**
   * Testing the isRequired() method when false.
   */
  @Test
  public void testIsRequiredFalse() {
    falseShortAnswerQuestion = new ShortAnswer("Tell me about your life?", false);
    assertEquals(false, falseShortAnswerQuestion.isRequired());
  }

  /**
   * Testing the getAnswer() method for a valid string under 280.
   */
  @Test
  public void testGetAnswerValid() {
    simpleShortAnswerQuestion = new ShortAnswer("Tell me about your life?", true);
    simpleShortAnswerQuestion.answer("Life has been great!");
    assertEquals("Life has been great!", simpleShortAnswerQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer() method for a string at exactly 280.
   */
  @Test
  public void testGetAnswerValid280() {
    simpleShortAnswerQuestion = new ShortAnswer("Tell me about your life?", true);

    simpleShortAnswerQuestion.answer("Life has been great! I have been taking classes at "
            + "northeastern and things have been going well. Last semester I took a course called "
            + "Discrete Math which was challenging. This semester I took Java which has been more "
            + "fun. Its a lot of more, but more fun. Its difficult with work. ");

    assertEquals("Life has been great! I have been taking classes at northeastern and "
            + "things have been going well. Last semester I took a course called Discrete Math "
            + "which was challenging. This semester I took Java which has been more fun. Its a "
            + "lot of more, but more fun. Its difficult with work. ",
            simpleShortAnswerQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer() method for a string greater than 280.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetAnswerValidGreaterThan280() {

    simpleShortAnswerQuestion = new ShortAnswer("Tell me about your life?", true);

    simpleShortAnswerQuestion.answer("Life has been pretty great! I have been taking classes at "
            + "northeastern and things have been going well. Last semester I took a course called"
            + " Discrete Math which was challenging. This semester I took Java which has been more"
            + " fun. Its a lot of more, but more fun. Its difficult with work. ");

    assertEquals("Life has been pretty great! I have been taking classes at northeastern"
                    + " and things have been going well. Last semester I took a course called "
                    + "Discrete Math which was challenging. This semester I took Java which has "
                    + "been more fun. Its a lot of more, but more fun. Its difficult with work. ",
            simpleShortAnswerQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer() method for an empty answer.
   */
  @Test
  public void testGetAnswerEmpty() {
    simpleShortAnswerQuestion = new ShortAnswer("Tell me about your life?", true);
    simpleShortAnswerQuestion.answer("");
    assertEquals("", simpleShortAnswerQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer() method for an null answer.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetAnswerNull() {
    simpleShortAnswerQuestion = new ShortAnswer("Tell me about your life?", true);
    simpleShortAnswerQuestion.answer(null);
    assertEquals("", simpleShortAnswerQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer() method.
   */
  @Test
  public void testGetAnswer() {
    simpleShortAnswerQuestion = new ShortAnswer("Tell me about your life?", true);
    simpleShortAnswerQuestion.answer("Life has been great!");
    assertEquals("Life has been great!", simpleShortAnswerQuestion.getAnswer());
  }

  /**
   * Testing the copy() method with a valid simple question.
   */
  @Test
  public void testCopyValid() {
    simpleShortAnswerQuestion = new ShortAnswer("How are you doing today?", true);
    simpleShortAnswerQuestion.answer("Yes");
    assertEquals(simpleShortAnswerQuestion.getPrompt(),
            simpleShortAnswerQuestion.copy().getPrompt());
  }

  /**
   * Testing the copy() method with an empty question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCopyEmpty() {
    emptyShortAnswerQuestion = new ShortAnswer("", true);
    emptyShortAnswerQuestion.answer("Yes");
    assertEquals(emptyShortAnswerQuestion.getPrompt(), emptyShortAnswerQuestion.copy().getPrompt());
  }

  /**
   * Testing the copy() method with an null question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCopyNull() {
    nullShortAnswerQuestion = new ShortAnswer(null, true);
    nullShortAnswerQuestion.answer(null);
    assertEquals(nullShortAnswerQuestion.getPrompt(), nullShortAnswerQuestion.copy().getPrompt());
  }

  /**
   * Testing the copy() method with a valid question and testing all get methods within it.
   */
  @Test
  public void testCopyValidAllProperties() {
    simpleShortAnswerQuestion = new
            ShortAnswer("Are you over 21 years old?", true);
    simpleShortAnswerQuestion.answer("Yes");
    assertEquals(simpleShortAnswerQuestion.getPrompt(),
            simpleShortAnswerQuestion.copy().getPrompt());
    assertEquals(simpleShortAnswerQuestion.getAnswer(),
            simpleShortAnswerQuestion.copy().getAnswer());
    assertEquals(simpleShortAnswerQuestion.getClass(),
            simpleShortAnswerQuestion.copy().getClass());
  }

  /**
   * Testing the copy() method with a simple question but no answer.
   */
  @Test
  public void testCopyNoAnswer() {
    assertEquals("Tell me about your life?", simpleShortAnswerQuestion.copy().getPrompt());
  }

  /**
   * Testing the copy() method to ensure the objects are not the same hash code.
   */
  @Test
  public void testCopy() {
    assertEquals(true, simpleShortAnswerQuestion.hashCode()
            != simpleShortAnswerQuestion.copy().hashCode());
  }

}