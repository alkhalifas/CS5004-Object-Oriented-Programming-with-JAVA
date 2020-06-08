/**
 * This interface represents a sentence made of nodes.
 */
public interface Sentence {


  /**
   * Computes and returns the number of words in a sentence. The punctuation does not count as a
   * word.
   *
   * @return the number of words.
   */
  int getNumberOfWords();

  /**
   * Determines and returns the longest word in a sentence.
   *
   * @return longest word in the sentence.
   */
  String longestWord();

  /**
   * Convert the sentence into one string. There must be a space between every two words.
   *
   * @return The sentence in the form of a sting.
   */
  String toString();

  /**
   * Returns a duplicate of a given sentence but is independent of the original list.
   *
   * @return The sentence.
   */
  Sentence clone();

  /**
   * Will merge two sentences into a single sentence.
   *
   * @return The sentence.
   */
  Sentence merge(Sentence other);


}