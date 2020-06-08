/**
 * This class represents a WordNode in the list which implements Sentence.
 */
public class WordNode implements Sentence {

  private String word; //variable / field
  private Sentence rest;

  /**
   * Construct an WordNode object using word and rest instance variables.
   *
   * @param word the information in this node.
   * @param rest the rest of this node.
   */
  public WordNode(String word, Sentence rest) {
    this.word = word;
    this.rest = rest;
  }

  /**
   * Computes and returns the number of words in a sentence. The punctuation does not count as a
   * word.
   *
   * @return the number of words.
   */
  @Override //done
  public int getNumberOfWords() {
    return 1 + rest.getNumberOfWords();
  }

  /**
   * Determines and returns the longest word in a sentence.
   *
   * @return longest word in the sentence.
   */
  @Override //done
  public String longestWord() {
    int thisOne = this.word.length();
    int restOne = this.rest.longestWord().length();

    if (thisOne > restOne) {
      return this.word;
    }

    return this.rest.longestWord();

  }

  /**
   * Returns a duplicate of a given sentence but is independent of the original list.
   *
   * @return The sentence.
   */
  @Override //done
  public Sentence clone() {
    return new WordNode(this.word, this.rest.clone());
  }

  /**
   * Will merge two sentences into a single sentence.
   *
   * @return The sentence.
   */
  @Override //done
  public Sentence merge(Sentence other) {
    return new WordNode(this.word, this.rest.merge(other));
  }


  /**
   * Convert the sentence into one string. There must be a space between every two words.
   *
   * @return The sentence in the form of a sting.
   */
  @Override //TODO: fix me
  public String toString() {
    return " " + this.word + rest.toString();
  }


}
