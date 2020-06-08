import java.util.function.Function;

/**
 * This interface represents an empty node.
 */
public class EmptyNode<T> implements Sentence<T> {

  /**
   * Method that counts the number of elements in the list.
   */
  @Override //Done
  public int count() {
    return 0;
  }

  /**
   * Computes and returns the number of words in a sentence. The punctuation does not count as a
   * word.
   *
   * @return the number of words.
   */
  @Override //Done
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Determines and returns the longest word in a sentence.
   *
   * @return longest word in the sentence.
   */
  @Override //Done
  public String longestWord() {
    return "";
  }

  /**
   * Returns a duplicate of a given sentence but is independent of the original list.
   *
   * @return The sentence.
   */
  @Override //Done
  public Sentence clone() {
    return new EmptyNode();
  }

  /**
   * Will merge two sentences into a single sentence.
   *
   * @return The sentence.
   */
  @Override //Done
  public Sentence merge(Sentence other) {
    return other;
  }

  /**
   * A helper method to count elements.
   *
   * @return A boolean.
   */
  @Override //Done
  public boolean countHelper() {
    return false;
  }

  /**
   * A helper method to count punctuation elements.
   *
   * @return A boolean.
   */
  @Override //Done
  public boolean countPuncHelper() {
    return false;
  }

  /**
   * Add an object to the front of this list.
   *
   * @param object the object to be added to the front of this list.
   */
  @Override //Done
  public Sentence<T> addFront(T object) {
    return new WordNode(object, this);
  }

  /**
   * Add an object to the back of this list (so it is the last object in the list.
   *
   * @param object the object to be added to teh back of this list.
   */
  @Override //Done
  public Sentence<T> addBack(T object) {
    return addFront(object);
  }

  /**
   * Add an object to this list so that it occupies the provided index.
   *
   * @param index  the index to be occupied by this object, beginning at 0.
   * @param object the object to be added to the list.
   */
  @Override
  public Sentence<T> add(int index, T object) throws
          IllegalArgumentException {
    if (index == 0) {
      return addFront(object);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  /**
   * Return the number of objects currently in this list.
   *
   * @return the size of the list
   */
  @Override //Todo: Please fix me
  public int getSize() {
    return 0;
  }

  /**
   * Remove the first instance of this object from this list.
   *
   * @param object the object to be removed
   */
  @Override //Done
  public Sentence<T> remove(T object) {
    return this; //cannot remove from nothing!
  }

  /**
   * Get the (index)th object in this list.
   *
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override //Done
  public T get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }

  /**
   * A general purpose map higher order function on this list, that returns the corresponding list
   * of type R.
   *
   * @param converter the function that converts T into R
   * @param <R>       the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list, but has data of type R
   */
  @Override //Done
  public <R> Sentence<R> map(Function<T, R> converter) {
    return new EmptyNode();
  }

  /**
   * A method that utilizes the higher level fold function using the countHelper.
   *
   * @return A integer.
   */
  @Override
  public int fold(Function countHelper) {
    return 0;
  }

  /**
   * Convert the sentence into one string. There must be a space between every two words.
   *
   * @return The sentence in the form of a sting.
   */
  @Override //Done
  public String toString() {
    return "";
  }
}
