import java.util.function.Function;

/**
 * This class represents the word node and implements sentence.
 */
public class WordNode<T>  implements Sentence<T>  {
  private T object;
  private Sentence<T> rest;

  /**
   * Constructs the word node.
   */
  public WordNode(T p,Sentence<T> rest) {
    this.object = p;
    this.rest = rest;
  }

  /**
   * Method that counts the number of elements in the list.
   */
  @Override //Done
  public int count() {
    return 1 + this.rest.count();
  }

  /**
   * Computes and returns the number of words in a sentence. The punctuation does not count as a
   * word.
   *
   * @return the number of words.
   */
  @Override //Done
  public int getNumberOfWords() {
    return 1 + this.rest.getNumberOfWords();
  }

  /**
   * Determines and returns the longest word in a sentence.
   *
   * @return longest word in the sentence.
   */
  @Override //Todo: Please fix me
  public String longestWord() {
    return null;
  }

  /**
   * Returns a duplicate of a given sentence but is independent of the original list.
   *
   * @return The sentence.
   */
  @Override //Todo: Please fix me
  public Sentence clone() {
    return null;
  }

  /**
   * Will merge two sentences into a single sentence.
   *
   * @return The sentence.
   */
  @Override //Todo: Please fix me
  public Sentence merge(Sentence other) {
    return null;
  }

  /**
   * Add an object to the front of this list.
   * @param object the object to be added to the front of this list.
   */
  @Override //Done
  public Sentence<T> addFront(T object) {
    return new WordNode(object,this);
  }

  /**
   * Add an object to the back of this list (so it is the last object in the
   * list.
   * @param object the object to be added to teh back of this list.
   */
  @Override //Done
  public Sentence<T> addBack(T object) {
    this.rest = this.rest.addBack(object);
    return this;
  }

  /**
   * Add an object to this list so that it occupies the provided index.
   * @param index the index to be occupied by this object, beginning at 0.
   * @param object the object to be added to the list.
   */
  @Override //Done
  public Sentence<T> add(int index,T object) {
    if ( index == 0 ) {
      return addFront(object);
    }
    else {
      this.rest = this.rest.add(index - 1,object);
      return this;
    }
  }

  /**
   * Return the number of objects currently in this list.
   * @return the size of the list
   */
  @Override //Todo: Please fix me
  public int getSize() {
    return 0;
  }


  /**
   * Remove the first instance of this object from this list.
   * @param object the object to be removed
   */
  @Override //Done
  public Sentence<T> remove(T object) {
    if (this.object.equals(object)) {
      return this.rest;
    }
    else {
      this.rest = this.rest.remove(object);
      return this;
    }
  }

  /**
   * Get the (index)th object in this list.
   * @param index the index of the object to be returned
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  @Override //Done
  public T get( int index ) throws IllegalArgumentException {
    if ( index == 0 ) {
      return this.object;
    }
    return this.rest.get( index - 1 );
  }

  /**
   * A general purpose map higher order function on this list, that returns
   * the corresponding list of type R.
   * @param converter the function that converts T into R
   * @param <R> the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list.
   */
  @Override //Done
  public <R> Sentence<R> map(Function<T,R> converter) {
    /* Starting from this list of T, the resulting list of type R is an
    element that contains this data converted to T, followed by the rest of
    the converted list
     */
    return new WordNode(
            converter.apply(this.object),
            this.rest.map(converter));
  }

  /**
   * A method that utilizes the higher level fold function using the countHelper.
   *
   * @return A integer.
   */
  @Override //Todo: Please fix me
  public int fold(Function countHelper) {
    return 0;
  }

  /**
   * Convert the sentence into one string. There must be a space between every two words.
   *
   * @return The sentence in the form of a sting.
   */
  @Override
  public String toString() {
    String objstring = this.object.toString();
    String rest = this.rest.toString();
    if ( rest.length() > 0 ) {
      return objstring + " " + rest;
    }
    else {
      return objstring;
    }
  }

  @Override
  public boolean countHelper() {
    return object.contains("z") || object.contains("Z");
  }

  @Override
  public boolean countPuncHelper() {
    return false;
  }

  @Override
  public Sentence code() {
    if ("AEIOUaeiou".contains(this.object.substring(0, 1))) {
      return new WordNode(object + "WAY", rest.code());
    }
    return new WordNode(object.substring(1) + object.substring(0, 1) + "AY", rest.code());
  }

}
