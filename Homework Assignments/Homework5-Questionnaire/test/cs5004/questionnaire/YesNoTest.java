package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for the YesNo class in which various cases are tested.
 */
public class YesNoTest {
  private YesNo simpleYesNoQuestionTrue;
  private YesNo simpleYesNoQuestionFalse;

  private YesNo emptyQuestionYesNoQuestion;
  private YesNo nullQuestionYesNoQuestion;


  @Before
  public void setup() {
    simpleYesNoQuestionTrue
            = new YesNo("How are you doing today?", true);
  }

  /**
   * Testing the getPrompt() method for a simple standard valid question with true req.
   */
  @Test
  public void testGetPromptValid() {
    assertEquals("How are you doing today?", simpleYesNoQuestionTrue.getPrompt());
  }

  /**
   * Testing the getPrompt() method for a simple standard valid question with true req.
   */
  @Test
  public void testGetPromptValidFalse() {
    simpleYesNoQuestionFalse = new YesNo("How are you doing today?", false);
    assertEquals("How are you doing today?", simpleYesNoQuestionFalse.getPrompt());
  }

  /**
   * Testing the getPrompt() method for an empty question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetPromptEmpty() {
    emptyQuestionYesNoQuestion = new YesNo("", true);
    assertEquals("", emptyQuestionYesNoQuestion.getPrompt());
  }

  /**
   * Testing the getPrompt() method for an null question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetPromptNull() {
    nullQuestionYesNoQuestion = new YesNo(null, true);
    assertEquals("", nullQuestionYesNoQuestion.getPrompt());
  }

  /**
   * Testing the isRequired() method with a false requirement.
   */
  @Test
  public void testIsRequiredFalse() {
    simpleYesNoQuestionFalse = new YesNo("How are you doing today?", false);
    assertEquals(false, simpleYesNoQuestionFalse.isRequired());
  }

  /**
   * Testing the isRequired() method with a true requirement.
   */
  @Test
  public void testIsRequiredTrue() {
    simpleYesNoQuestionTrue = new YesNo("How are you doing today?", true);
    assertEquals(true, simpleYesNoQuestionTrue.isRequired());
  }

  /**
   * Testing the getAnswer for a valid answer.
   */
  @Test
  public void testGetAnswerValid() {
    simpleYesNoQuestionTrue = new YesNo("How are you doing today?", true);
    simpleYesNoQuestionTrue.answer("Yes");
    assertEquals("Yes", simpleYesNoQuestionTrue.getAnswer());
  }

  /**
   * Testing the getAnswer for a valid answer with lower case spelling.
   */
  @Test
  public void testGetAnswerLowercase() {
    simpleYesNoQuestionTrue = new YesNo("How are you doing today?", true);
    simpleYesNoQuestionTrue.answer("yes");
    assertEquals("yes", simpleYesNoQuestionTrue.getAnswer());
  }

  /**
   * Testing the getAnswer for a valid answer with Upper case spelling.
   */
  @Test
  public void testGetAnswerUppercase() {
    simpleYesNoQuestionTrue = new YesNo("How are you doing today?", true);
    simpleYesNoQuestionTrue.answer("YES");
    assertEquals("YES", simpleYesNoQuestionTrue.getAnswer());
  }

  /**
   * Testing the getAnswer for a valid answer with Upper case spelling.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetAnswerInvalid() {
    simpleYesNoQuestionTrue = new YesNo("How are you doing today?", true);
    simpleYesNoQuestionTrue.answer("Yea");
    assertEquals("Yea", simpleYesNoQuestionTrue.getAnswer());
  }

  /**
   * Testing the getAnswer for a valid answer with Upper case spelling.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetAnswerEmpty() {
    emptyQuestionYesNoQuestion = new YesNo("This is a question", true);
    emptyQuestionYesNoQuestion.answer("");
    assertEquals("Yea", emptyQuestionYesNoQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer for a valid answer with Upper case spelling.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetAnswerNull() {
    nullQuestionYesNoQuestion = new YesNo("This is a question", true);
    nullQuestionYesNoQuestion.answer(null);
    assertEquals("Yes", nullQuestionYesNoQuestion.getAnswer());
  }

  /**
   * Testing the copy() method with a valid simple question.
   */
  @Test
  public void testCopyValid() {
    simpleYesNoQuestionTrue = new YesNo("How are you doing today?", true);
    simpleYesNoQuestionTrue.answer("Yes");
    assertEquals(simpleYesNoQuestionTrue.getPrompt(), simpleYesNoQuestionTrue.copy().getPrompt());
  }

  /**
   * Testing the copy() method with an empty question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCopyEmpty() {
    emptyQuestionYesNoQuestion = new YesNo("", true);
    emptyQuestionYesNoQuestion.answer("Yes");
    assertEquals(emptyQuestionYesNoQuestion.getPrompt(),
            emptyQuestionYesNoQuestion.copy().getPrompt());
  }

  /**
   * Testing the copy() method with an null question.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCopyNull() {
    nullQuestionYesNoQuestion = new YesNo(null, true);
    nullQuestionYesNoQuestion.answer(null);
    assertEquals(nullQuestionYesNoQuestion.getPrompt(),
            nullQuestionYesNoQuestion.copy().getPrompt());
  }

  /**
   * Testing the copy() method with a valid question and testing all get methods within it.
   */
  @Test
  public void testCopyValidAllProperties() {
    simpleYesNoQuestionTrue = new YesNo("Are you over 21 years old?", true);
    simpleYesNoQuestionTrue.answer("Yes");
    assertEquals(simpleYesNoQuestionTrue.getPrompt(), simpleYesNoQuestionTrue.copy().getPrompt());
    assertEquals(simpleYesNoQuestionTrue.getAnswer(), simpleYesNoQuestionTrue.copy().getAnswer());
    assertEquals(simpleYesNoQuestionTrue.getClass(), simpleYesNoQuestionTrue.copy().getClass());
  }

  /**
   * Testing the copy() method with a simple question but no answer.
   */
  @Test
  public void testCopyNoAnswer() {
    assertEquals("How are you doing today?", simpleYesNoQuestionTrue.copy().getPrompt());
  }

  /**
   * Testing the copy() method to ensure the objects are not the same hash code.
   */
  @Test
  public void testCopy() {
    assertEquals(true, simpleYesNoQuestionTrue.hashCode()
            != simpleYesNoQuestionTrue.copy().hashCode());
  }

}


