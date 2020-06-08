package cs5004.questionnaire;

/**
 * A class implementing the question interface in the form of a Yes No question.
 */
public class YesNo implements Question {
  private String question;
  private boolean isRequired;
  private String answer;

  /**
   * The constructor of the class that creates a new YesNo question object.
   *
   * @param question which is the asked question in the form of a String.
   * @param required which is the boolean argument of whether the question is required or not.
   */
  public YesNo(String question, boolean required) throws IllegalArgumentException {
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
  @Override
  public Question copy() {
    YesNo newYesNo = new YesNo(this.question, this.isRequired);
    if (!this.answer.equals("")) {
      newYesNo.answer(this.answer);
    }
    return newYesNo;
  }

  @Override
  public void answer(String answer) throws IllegalArgumentException {
    if (answer == null) {
      throw new IllegalArgumentException("Answer cant be empty");
    } else if (answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("No")) {
      this.answer = answer;
    } else {
      throw new IllegalArgumentException("Answer must be yes or no");
    }
  }
}

