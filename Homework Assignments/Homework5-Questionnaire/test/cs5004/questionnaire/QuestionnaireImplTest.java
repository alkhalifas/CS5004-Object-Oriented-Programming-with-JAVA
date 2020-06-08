package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;

/**
 * Testing class for the QuestionnaireImpl class in which various cases are tested.
 */
public class QuestionnaireImplTest {
  private QuestionnaireImpl myQuestionList;
  private ShortAnswer shortAnswerQuestion;
  private YesNo yesNoQuestion;
  private Likert likertQuestion;


  @Before
  public void setup() {
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", true);
    likertQuestion = new Likert("Rate this survey", true);

    myQuestionList = new QuestionnaireImpl();
    myQuestionList.addQuestion("12", shortAnswerQuestion);
    myQuestionList.addQuestion("34", yesNoQuestion);
    myQuestionList.addQuestion("56", likertQuestion);
  }

  /**
   * Testing the getQuestion using identifier, and check the size to confirm the list was
   * constructed properly.
   */
  @Test
  public void testQuestionnaireImplConstructor() {
    myQuestionList = new QuestionnaireImpl();
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals(3, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the addQuestion() method using different questions with different information, and
   * confirming the length based on getRequiredQuestions.
   */
  @Test
  public void testAddQuestionGeneral() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", false);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);

    assertEquals(2, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the getQuestion using an empty identifier.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddQuestionGeneralInvalidIdentifier() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", false);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals(2, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the getQuestion using an null identifier.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddQuestionGeneralNullIdentifier() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", false);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion(null, yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals(2, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the getQuestion using three same question types.
   */
  @Test
  public void testAddQuestionSameThree() {
    myQuestionList = new QuestionnaireImpl();
    ShortAnswer shortAnswerQuestionOne = new ShortAnswer("Tell me your life?", true);
    ShortAnswer shortAnswerQuestionTwo = new ShortAnswer("Tell me a story?", true);
    ShortAnswer shortAnswerQuestionThree = new ShortAnswer("Where do you live?", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestionOne);
    myQuestionList.addQuestion("x34", shortAnswerQuestionTwo);
    myQuestionList.addQuestion("x56", shortAnswerQuestionThree);
    assertEquals(3, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the getQuestion / removeQuestions for a list.
   */
  @Test
  public void testRemove() {
    myQuestionList = new QuestionnaireImpl();
    ShortAnswer shortAnswerQuestionOne = new ShortAnswer("Tell me your life?", true);
    ShortAnswer shortAnswerQuestionTwo = new ShortAnswer("Tell me a story?", true);
    ShortAnswer shortAnswerQuestionThree = new ShortAnswer("Where do you live?", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestionOne);
    myQuestionList.addQuestion("x34", shortAnswerQuestionTwo);
    myQuestionList.addQuestion("x56", shortAnswerQuestionThree);
    myQuestionList.removeQuestion("x34");
    assertEquals(2, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the removeQuestion for a identifier that does not exist.
   */
  @Test
  public void testRemoveDoesNotExist() {
    myQuestionList = new QuestionnaireImpl();
    ShortAnswer shortAnswerQuestionOne = new ShortAnswer("Tell me your life?", true);
    ShortAnswer shortAnswerQuestionTwo = new ShortAnswer("Tell me a story?", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestionOne);
    myQuestionList.addQuestion("56", shortAnswerQuestionTwo);
    myQuestionList.removeQuestion("x34");
    assertEquals(2, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the removeQuestion for a identifier that does exist.
   */
  @Test
  public void testRemoveDoesExist() {
    myQuestionList = new QuestionnaireImpl();
    ShortAnswer shortAnswerQuestionOne = new ShortAnswer("Tell me your life?", true);
    ShortAnswer shortAnswerQuestionTwo = new ShortAnswer("Tell me a story?", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestionOne);
    myQuestionList.addQuestion("56", shortAnswerQuestionTwo);
    myQuestionList.removeQuestion("x12");
    assertEquals(1, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the getQuestion using the index location.
   */
  @Test
  public void testGetQuestionIndex() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", true);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals("Tell me your life", myQuestionList.getQuestion(1).getPrompt());
    assertEquals("Rate this survey", myQuestionList.getQuestion(3).getPrompt());
    assertEquals("Are you awake?", myQuestionList.getQuestion(2).getPrompt());
  }

  /**
   * Testing the getQuestion using the valid identifier.
   */
  @Test
  public void testGetQuestionIdentifier() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", true);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals("Tell me your life", myQuestionList.getQuestion("x12").getPrompt());
  }

  /**
   * Testing the getQuestion using a invalid identifier.
   */
  @Test(expected = NoSuchElementException.class)
  public void testGetQuestionInvalidIdentifier() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", true);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals("Tell me your life", myQuestionList.getQuestion("123123").getPrompt());
  }

  /**
   * Testing the getQuestion using a negative index location.
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetQuestionNegative() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", true);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals("Tell me your life", myQuestionList.getQuestion(-3).getPrompt());
  }

  /**
   * Testing the getQuestion using an out of bounds index location.
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetQuestionOutOfBounds() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", true);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals("Tell me your life", myQuestionList.getQuestion(55).getPrompt());
  }

  /**
   * Testing the getRequiredQuestions method using all three questions.
   */
  @Test
  public void testGetRequiredQuestionsGeneral() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", true);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals(3, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the getRequiredQuestions method using all three questions, with one false entry.
   */
  @Test
  public void testGetRequiredQuestionsGeneralWithFalse() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", false);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals(2, myQuestionList.getRequiredQuestions().size());
  }


  /**
   * Testing the getQuestion using three same question types, with some true and false.
   */
  @Test
  public void testGetRequiredQuestionsWithFalse() {
    myQuestionList = new QuestionnaireImpl();
    ShortAnswer shortAnswerQuestionOne = new ShortAnswer("Tell me your life?", true);
    ShortAnswer shortAnswerQuestionTwo = new ShortAnswer("Tell me a story?", false);
    ShortAnswer shortAnswerQuestionThree = new ShortAnswer("Where do you live?", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestionOne);
    myQuestionList.addQuestion("x34", shortAnswerQuestionTwo);
    myQuestionList.addQuestion("x56", shortAnswerQuestionThree);
    assertEquals(2, myQuestionList.getRequiredQuestions().size());
  }

  /**
   * Testing the getRequiredQuestions method using all three questions.
   */
  @Test
  public void testGetOptionaluestionsGeneral() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", true);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals(0, myQuestionList.getOptionalQuestions().size());
  }

  /**
   * Testing the getRequiredQuestions method using all three questions, with one false entry.
   */
  @Test
  public void testGetOptionalQuestionsGeneralWithFalse() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    yesNoQuestion = new YesNo("Are you awake?", false);
    likertQuestion = new Likert("Rate this survey", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);
    assertEquals(1, myQuestionList.getOptionalQuestions().size());
  }

  /**
   * Testing the getQuestion using three same question types, with some true and false.
   */
  @Test
  public void testGetOptionalQuestionsWithFalse() {
    myQuestionList = new QuestionnaireImpl();
    ShortAnswer shortAnswerQuestionOne = new ShortAnswer("Tell me your life?", true);
    ShortAnswer shortAnswerQuestionTwo = new ShortAnswer("Tell me a story?", false);
    ShortAnswer shortAnswerQuestionThree = new ShortAnswer("Where do you live?", true);
    myQuestionList.addQuestion("x12", shortAnswerQuestionOne);
    myQuestionList.addQuestion("x34", shortAnswerQuestionTwo);
    myQuestionList.addQuestion("x56", shortAnswerQuestionThree);
    assertEquals(1, myQuestionList.getOptionalQuestions().size());
  }

  /**
   * Testing the isComplete() method with all valid entry.
   */
  @Test
  public void testIsCompleteValid() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    shortAnswerQuestion.answer("I have a sad life");

    yesNoQuestion = new YesNo("Are you awake?", true);
    yesNoQuestion.answer(("Yes"));

    likertQuestion = new Likert("How do you feel about this decision?", true);
    likertQuestion.answer("Agree");

    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);

    assertEquals(true, myQuestionList.isComplete());
  }

  /**
   * Testing the isComplete() method with one invalid entry.
   */
  @Test
  public void testIsCompleteInValid() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    shortAnswerQuestion.answer("");

    yesNoQuestion = new YesNo("Are you awake?", true);
    yesNoQuestion.answer("Yes");

    likertQuestion = new Likert("How do you feel about this decision?", true);
    likertQuestion.answer("Agree");

    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);

    assertEquals(false, myQuestionList.isComplete());
  }

  /**
   * Testing the getResponses method using valid entries.
   */
  @Test
  public void testGetResponses() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    shortAnswerQuestion.answer("I have a sad life");

    yesNoQuestion = new YesNo("Are you awake?", true);
    yesNoQuestion.answer(("Yes"));

    likertQuestion = new Likert("How do you feel about this decision?", true);
    likertQuestion.answer("Agree");

    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);

    assertEquals("[I have a sad life, Yes, Agree]", myQuestionList.getResponses().toString());
  }

  /**
   * Testing the getResponses method using valid entries.
   */
  @Test
  public void testGetResponsesEmpty() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    shortAnswerQuestion.answer("");

    yesNoQuestion = new YesNo("Are you awake?", true);
    yesNoQuestion.answer(("Yes"));

    likertQuestion = new Likert("How do you feel about this decision?", true);
    likertQuestion.answer("Agree");

    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);

    assertEquals("[, Yes, Agree]", myQuestionList.getResponses().toString());
  }


  /**
   * Testing the toString method to ensure that the single string is correct in format.
   */
  @Test
  public void testGetResponsesToString() {
    myQuestionList = new QuestionnaireImpl();
    shortAnswerQuestion = new ShortAnswer("Tell me your life", true);
    shortAnswerQuestion.answer("");

    yesNoQuestion = new YesNo("Are you awake?", true);
    yesNoQuestion.answer(("Yes"));

    likertQuestion = new Likert("How do you feel about this decision?", true);
    likertQuestion.answer("Agree");

    myQuestionList.addQuestion("x12", shortAnswerQuestion);
    myQuestionList.addQuestion("x34", yesNoQuestion);
    myQuestionList.addQuestion("x56", likertQuestion);

    assertEquals("Question: Tell me your life/n/nAnswer: /n/nQuestion: Are you "
            + "awake?/n/nAnswer: Yes/n/nQuestion: How do you feel about this decision?/n/nAnswer: "
            + "Agree/n/n", myQuestionList.toString());
  }


}