package cs5004.questionnaire;

/**
 * A class implementing the question interface in the form of a Likert question.
 */
public class Likert implements Question {
  private String question;
  private boolean isRequired;
  private String answer;

  /**
   * The constructor of the class that creates a new Likert question object.
   *
   * @param question which is the asked question in the form of a String.
   * @param required which is the boolean argument of whether the question is required or not.
   * @throws IllegalArgumentException if the question is empty.
   */
  public Likert(String question, boolean required) throws IllegalArgumentException {
    if (question == null || question.isEmpty()) {
      throw new IllegalArgumentException("Please fill in required fields");
    }
    this.question = question;
    this.isRequired = required;
    this.answer = "";
  }

  /**
   * A method that returns the question of the associated Question object.
   *
   * @returns question which is the asked question in the form of a String.
   */
  @Override
  public String getPrompt() {
    return this.question;
  }

  /**
   * A method that returns the requirement of the associated Question object.
   *
   * @returns isRequired which is the boolean argument of whether the question is required or not.
   */
  @Override
  public boolean isRequired() {
    return this.isRequired;
  }

  /**
   * A method that returns the answer of a given Question object.
   *
   * @returns answer which is the answer to a question in the form of a string.
   */
  @Override
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Copies or duplicates the Question object in addition to its associated data.
   *
   * @returns a copy of the Question object.
   */
  @Override //Done
  public Question copy() {
    Likert newLikert = new Likert(this.question, this.isRequired);
    if (!this.answer.equals("")) {
      newLikert.answer(this.answer);
    }
    return newLikert;
  }

  /**
   * Allows the user to answer the question in which a String called answer is given.
   *
   * @param answer in the form of a String.
   * @returns a copy of the Question object.
   */
  @Override
  public void answer(String answer) throws IllegalArgumentException {
    if (answer == null || answer.equals("")) {
      throw new IllegalArgumentException("Cannot be empty or null");
    }
    else if (answer.equalsIgnoreCase("Strongly Agree")
            || answer.equalsIgnoreCase("Agree")
            || answer.equalsIgnoreCase("Neither agree nor Disagree")
            || answer.equalsIgnoreCase("Disagree")
            || answer.equalsIgnoreCase("Strongly Disagree")) {
      this.answer = answer;
    } else {
      throw new IllegalArgumentException("Answer needs to follow Likert scale!");
    }
  }
}
