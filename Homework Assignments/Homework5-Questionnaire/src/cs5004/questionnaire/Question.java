package cs5004.questionnaire;

/**
 * Represents the Question interface outlining the methods for a standard Question.
 */
public interface Question {

  /**
   * Retrieves the question in the form of a String from a given object.
   *
   * @returns the question in the form of a String.
   */
  public abstract String getPrompt();

  /**
   * Retrieves the boolean requirement of whether or not the question is required.
   *
   * @returns the boolean of the requirement.
   */
  public abstract boolean isRequired();

  /**
   * Retrieves the answer that the user answered the question with.
   *
   * @returns the answer in the form of a string.
   */
  public abstract String getAnswer();

  /**
   * Copies or duplicates the Question object in addition to its associated data.
   *
   * @returns a copy of the Question object.
   */
  public abstract Question copy();

  /**
   * Allows the user to answer the question in which a String called answer is given.
   * @param answer in the form of a String.
   * @returns a copy of the Question object.
   */
  public abstract void answer(String answer);

}