package cs5004.questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Represents an implementation class of the interface Questionnaire.
 */
public class QuestionnaireImpl implements Questionnaire {
  List<IDQ> questionList;

  /**
   * Constructs a new array list called questionList.
   */
  public QuestionnaireImpl() {
    questionList = new ArrayList<>();
  }

  /**
   * Add a question to the questionnaire.
   *
   * @param identifier a name for the question <b>unique</b> within this questionnaire. Not null or
   *                   empty.
   * @param q          the {@link Question} to be added to the questionnaire
   */
  @Override
  public void addQuestion(String identifier, Question q) {
    questionList.add(new IDQ(identifier, q));
  }

  /**
   * Remove the question with the given identifier from the questionnaire.
   *
   * @param identifier the identifier of the question to be removed.
   * @throws NoSuchElementException if there is no question with the given identifier.
   */
  @Override
  public void removeQuestion(String identifier) {
    for (int i = 0; i < questionList.size(); i++) {
      if (questionList.get(i).id.equals(identifier)) {
        questionList.remove(i);
      }
    }
  }

  /**
   * Get the question with the given number, based on the order in which it was added to the
   * questionnaire, or the sorted order if the {@code sort()} method is called. The first question
   * is 1, second 2, etc.
   *
   * @param num the number of the question, counting from 1
   * @return the question
   * @throws IndexOutOfBoundsException if there is no such question num
   */
  @Override
  public Question getQuestion(int num) {
    if (num < 1 || num > questionList.size()) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    } else {
      return questionList.get(num - 1).getQuestion();
    }
  }

  /**
   * Get the question with the given identifier (question having been previously added to the
   * questionnaire).
   *
   * @param identifier the identifier of the question
   * @return the question
   * @throws NoSuchElementException if there is no question with the identifier
   */
  @Override
  public Question getQuestion(String identifier) {
    for (int i = 0; i < questionList.size(); i++) {
      if (questionList.get(i).id.equalsIgnoreCase(identifier)) {
        return questionList.get(i).q;
      } else {
        throw new NoSuchElementException("No such element!");
      }
    }
    return null;
  }

  /**
   * Return a list of all required questions in the questionnaire.
   *
   * @return the required questions.
   */
  @Override
  public List<Question> getRequiredQuestions() {
    List<Question> requiredquestionList = new ArrayList<>();
    for (IDQ idq : questionList) {
      if (idq.getQuestion().isRequired()) {
        requiredquestionList.add(idq.getQuestion());
      }
    }
    return requiredquestionList;
  }

  /**
   * Return a list of all optional questions in the questionnaire.
   *
   * @return the optional questions.
   */
  @Override
  public List<Question> getOptionalQuestions() {
    List<Question> optionalquestionList = new ArrayList<Question>();
    for (IDQ idq : questionList) {
      if (!idq.getQuestion().isRequired()) {
        optionalquestionList.add(idq.q);
      }
    }
    return optionalquestionList;
  }

  /**
   * Report if all required questions have some non-empty response.
   *
   * @return true if all required questions have responses, false otherwise.
   */
  @Override
  public boolean isComplete() {
    for (Question q : getRequiredQuestions()) {
      if (q.getAnswer().equals("") || q.getAnswer() == null) {
        return false;
      }
    }
    return true;
  }

  /**
   * Return a list of just the responses to all the questions in the questionnaire.
   *
   * @return the responses
   */
  @Override
  public List<String> getResponses() {
    List<String> listResponses = new ArrayList<>();
    for (IDQ i : questionList) {
      listResponses.add(i.getQuestion().getAnswer());
    }
    return listResponses;
  }

  /**
   * Produce a new questionnaire containing just the questions where the given predicate returns
   * true. The returned questionnaire is completely independent of this questionnaire. That is, the
   * questions in the returned questionnaire are <b>copies</b> of the original questions.
   *
   * @param pq the predicate
   * @return the new questionnaire
   */
  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    Questionnaire newFilterQuestionnaire = new QuestionnaireImpl();
    for (IDQ i : questionList) {
      if (pq.test(i.getQuestion())) {
        Question newFilterQuestion = null;
        if (i.getQuestion() instanceof YesNo) {
          newFilterQuestion = new YesNo(i.getQuestion().getPrompt(), i.getQuestion().isRequired());
        } else if (i.getQuestion() instanceof ShortAnswer) {
          newFilterQuestion = new ShortAnswer(i.getQuestion().getPrompt(),
                  i.getQuestion().isRequired());
        } else if (i.getQuestion() instanceof Likert) {
          newFilterQuestion = new Likert(i.getQuestion().getPrompt(), i.getQuestion().isRequired());
        }
        if (newFilterQuestion != null) {
          newFilterQuestion.answer(i.getQuestion().getAnswer());
        }
        newFilterQuestionnaire.addQuestion(i.getIdentifier(), newFilterQuestion);
      }
    }
    return newFilterQuestionnaire;
  }

  /**
   * Sort the questions according to the given comparator. Return values from {@code
   * getQuestion(int)} should reflect the new sorted order following sort.
   *
   * @param comp a comparator for Question
   */
  @Override
  public void sort(Comparator<Question> comp) {
    questionList.sort((a, b) -> comp.compare(a.q, b.q));
  }

  /**
   * Produce a single summary value based on the given folding function and seed value.
   *
   * @param bf   the folding function
   * @param seed the seed value
   * @param <R>  the return type
   * @return the summary value
   */
  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    if (seed == null) {
      throw new IllegalArgumentException("cannot be null");
    }
    for (IDQ i : questionList) {
      seed = bf.apply(i.getQuestion(), seed);
    }
    return seed;
  }

  /**
   * Convert the questionnaire into a single string in the format of
   * Question: [prompt] then two newlines
   * Answer: [answer] two newlines, and so on. Example result for a questionnaire with 3 questions:
   * Question: What is your name?
   *
   * <p>Answer: Sir Lancelot
   *
   * <p>Question: What is your quest?
   *
   * <p>Answer: I seek the Holy Grail.
   *
   * <p>Question: What is your favorite color?
   *
   * <p>Answer: Blue.
   *
   * @return the questionnaire as a String
   */
  public String toString() {
    String acc = "";
    for (IDQ i: questionList) {
      acc = acc + "Question: " + i.getQuestion().getPrompt()
              + "/n/nAnswer: " + i.getQuestion().getAnswer()
              + "/n/n";
    }
    return acc;
  }
























  /**
   * Represents a static class to assist with the use of identifiers within questions.
   */
  public static class IDQ {
    private String id;
    private Question q;

    /**
     * Constructs a private IDQ which takes in the id and question of the Question.
     *
     * @param id which is the identifying number of the object.
     * @param q  which is the question of the object.
     * @returns the IDQ object which has the id and question of the object.
     */
    private IDQ(String id, Question q) throws IllegalArgumentException {
      if (id == null || id.equals("") || q == null) {
        throw new IllegalArgumentException("Identifier cannot be empty.");
      }
      this.id = id;
      this.q = q;
    }

    /**
     * Retrieves the identifier of the associated Question object.
     *
     * @return the identifier as a string value.
     */
    public String getIdentifier() {
      return id;
    }

    /**
     * Retrieves the question of the associated Question object.
     *
     * @return the question as a string value.
     */
    public Question getQuestion() {
      return q;
    }
  }

}