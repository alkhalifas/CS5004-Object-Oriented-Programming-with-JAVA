/**
 * This class represents an empty node in the list which implements Sentence.
 */
public class EmptyNode implements Sentence {

  /**
   * Construct an EmptyNode object using word and rest instance variables.
   */
  public EmptyNode() {
    String word = "";
    Sentence rest = null;
  }

  /**
   * Computes and returns the number of words in a sentence. The punctuation does not count as a
   * word.
   *
   * @return the number of words.
   */
  @Override //done
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Determines and returns the longest word in a sentence.
   *
   * @return longest word in the sentence.
   */
  @Override //done
  public String longestWord() {
    return "";
  }

  /**
   * Returns a duplicate of a given sentence but is independent of the original list.
   *
   * @return The sentence.
   */
  @Override //done
  public Sentence clone() {
    return new EmptyNode();
  }

  /**
   * Will merge two sentences into a single sentence.
   *
   * @return The sentence.
   */
  @Override //done
  public Sentence merge(Sentence other) {
    return other.clone();
  }

  /**
   * Convert the sentence into one string. There must be a space between every two words.
   *
   * @return The sentence in the form of a sting.
   */
  @Override //bad
  public String toString() {
    return "";
  }

}
