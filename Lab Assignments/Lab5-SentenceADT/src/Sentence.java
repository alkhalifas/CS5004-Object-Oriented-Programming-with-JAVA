import java.util.function.Function;

/**
 * This interface represents a sentence.
 */
public interface Sentence<T> {

  /**
   * Add an object to the front of this list.
   *
   * @param b the object to be added to the front of this list.
   */
  Sentence<T> addFront(T b);

  /**
   * Add an object to the back of this list (so it is the last object in the list.
   *
   * @param b the object to be added to teh back of this list.
   */
  Sentence<T> addBack(T b);

  /**
   * Add an object to this list so that it occupies the provided index.
   *
   * @param index the index to be occupied by this object, beginning at 0.
   * @param b     the object to be added to the list.
   */
  Sentence<T> add(int index, T b) throws IllegalArgumentException;

  /**
   * Return the number of objects currently in this list.
   *
   * @return the size of the list.
   */
  int getSize();

  /**
   * Remove the first instance of this object from this list.
   *
   * @param b the object to be removed.
   */
  Sentence<T> remove(T b);

  /**
   * Get the (index)th object in this list.
   *
   * @param index the index of the object to be returned.
   * @return the object at the given index.
   * @throws IllegalArgumentException if an invalid index is passed.
   */
  T get(int index) throws IllegalArgumentException;

  /**
   * A general purpose map higher order function on this list, that returns the corresponding list
   * of type R.
   *
   * @param converter the function that converts T into R.
   * @param <R>       the type of data in the resulting list.
   * @return the resulting list that is identical in structure to this list.
   */
  <R> Sentence<R> map(Function<T, R> converter);

  /**
   * Method that counts the number of elements in the list.
   */
  int count();

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


  /**
   * A helper method to count elements.
   *
   * @return A boolean.
   */
  boolean countHelper();

  /**
   * A helper method to count punctuation elements.
   *
   * @return A boolean.
   */
  boolean countPuncHelper();

  /**
   * A method that utilizes the higher level fold function using the countHelper.
   *
   * @return A integer.
   */
  int fold(Function<Sentence, Boolean> countHelper);

  Sentence code();
}
