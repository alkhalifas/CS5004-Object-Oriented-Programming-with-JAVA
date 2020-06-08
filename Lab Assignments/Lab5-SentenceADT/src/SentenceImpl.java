import java.util.function.Function;

/**
 * This class represents an implementation for sentence.
 */
public class SentenceImpl<T> implements SentenceADT<T> {
  private Sentence<T> head;

  /**
   * Construct a sentence implementation object by creating an Empty node.
   */
  public SentenceImpl() {
    head = new EmptyNode();
  }

  /**
   * Construct a sentence implementation object by creating a word node.
   */
  private SentenceImpl(WordNode<T> head) {
    this.head = head;
  }

  /**
   * Add an object to the front of this list.
   *
   * @param b the object to be added to the front of this list
   */
  @Override //Done
  public void addFront(T b) {
    head = head.addFront(b);
  }

  /**
   * Add an object to the back of this list (so it is the last object in the list.
   *
   * @param b the object to be added to teh back of this list
   */
  @Override //Done
  public void addBack(T b) {
    head = head.addBack(b);
  }

  /**
   * Add an object to this list so that it occupies the provided index.
   *
   * @param index the index to be occupied by this object, beginning at 0
   * @param b     the object to be added to the list
   */
  @Override //Done
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  /**
   * Return the number of objects currently in this list.
   *
   * @return the size of the list
   */
  @Override //Done
  public int getSize() {
    return head.count();
  }

  /**
   * Remove the first instance of this object from this list.
   *
   * @param b the object to be removed
   */
  @Override //Done
  public void remove(T b) {
    head = head.remove(b);
  }

  /**
   * Method that counts the number of elements in the list.
   */
  @Override //Done
  public int count() {
    return 1 + this.head.count();
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
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    }
    else {
      throw new IllegalArgumentException("Invalid index");
    }

  }

  /**
   * A general purpose map higher order function on this list, that returns the corresponding list
   * of type R.
   *
   * @param converter the function that converts T into R
   * @param <R>       the type of data in the resulting list
   * @return the resulting list that is identical in structure to this list, but has data of type R
   */
  @Override
  public <R> SentenceADT<R> map(Function<T, R> converter) {
    //   return new SentenceImpl<>(head.map(converter));
    return null;
  }

  /**
   * Overwrites the toString method to pass a string.
   *
   * @return A string.
   */
  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }




  //Pig Latin Stuff goes here >
  //Use some of the methods to look at works, and convert to piglatin > concat

}


