package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for the Likert class in which various cases are tested.
 */
public class LikertTest {
  private Likert simpleLikertQuestion;
  private Likert emptyLikertQuestion;
  private Likert falseLikertQuestion;


  @Before
  public void setup() {
    simpleLikertQuestion = new Likert("Did you have a good day?", true);
  }

  /**
   * Testing the getPrompt() method with a standard entry.
   */
  @Test public void testGetPromptStandard() {
    assertEquals("Did you have a good day?", simpleLikertQuestion.getPrompt());
  }

  /**
   * Testing the getPrompt() method with a empty entry.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testGetPromptEmpty() {
    emptyLikertQuestion = new Likert("", true);
    assertEquals("D", emptyLikertQuestion.getPrompt());
  }

  /**
   * Testing the getPrompt() method with a false entry.
   */
  @Test
  public void testGetPromptFalse() {
    falseLikertQuestion = new Likert("Today is a sunny day.", false);
    assertEquals("Today is a sunny day.", falseLikertQuestion.getPrompt());
  }


  /**
   * Testing the isRequired() method for a standard entry.
   */
  @Test public void testIsRequiredTrue() {
    assertEquals(true, simpleLikertQuestion.isRequired());
  }

  /**
   * Testing the isRequired() method for a false entry.
   */
  @Test public void testIsRequiredFalse() {
    falseLikertQuestion = new Likert("Today is a sunny day.", false);

    assertEquals(false, falseLikertQuestion.isRequired());
  }

  /**
   * Testing the getAnswer() method using a standard valid answer.
   */
  @Test
  public void testGetAnswerValid() {
    simpleLikertQuestion.answer("Agree");
    assertEquals("Agree", simpleLikertQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer() method using an invalid answer.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testGetAnswerInvalid() {
    simpleLikertQuestion.answer("i do agree");
    assertEquals("Agree", simpleLikertQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer() method using a valid lowercase answer.
   */
  @Test
  public void testGetAnswerCapitalize() {
    simpleLikertQuestion = new Likert("Did you have a good day?", true);
    simpleLikertQuestion.answer("disagree");
    assertEquals("disagree", simpleLikertQuestion.getAnswer());
  }

  /**
   * Testing the getAnswer() method when answer is Empty.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testGetAnswerEmpty() {
    emptyLikertQuestion = new Likert("Did you have a good day?", true);
    emptyLikertQuestion.answer("");
    assertEquals("Agree", simpleLikertQuestion.getAnswer());
  }


  /**
   * Testing the copy() method with a simple valid response to show same answer.
   */
  @Test public void testCopy() {
    simpleLikertQuestion = new Likert("Did you have a good day?", true);
    simpleLikertQuestion.answer("Agree");
    assertEquals("Agree", simpleLikertQuestion.copy().getAnswer());
  }

  /**
   * Testing the copy() method to show the same question.
   */
  @Test public void testCopyAnswer() {
    simpleLikertQuestion.answer("Agree");
    assertEquals("Did you have a good day?", simpleLikertQuestion.copy().getPrompt());
  }

}