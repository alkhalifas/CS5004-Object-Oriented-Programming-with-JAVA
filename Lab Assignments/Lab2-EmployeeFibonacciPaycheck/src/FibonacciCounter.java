/**
 * This class represents a fibonacci calculator.
 */
public class FibonacciCounter {
  private int count;
  private int fibNumber;

  /**
   * Constructs a fibonacci counter object.
   *
   * @param number the initial value of the count
   */
  public FibonacciCounter(int number) {
    count = number;
    fibNumber = fibonacciNumberValue(count);
  }

  private int fibonacciNumberValue(int count) {
    if (count == 0) {
      return 0;
    }
    if (count == 1) {
      return 1;
    }

    double first = Math.pow( ( (1 + Math.sqrt(5)) / 2 ), count );
    double second = Math.pow( ( (1 - Math.sqrt(5)) / 2 ), count );
    double  middle = first - second;
    double finalPart = (1 / (Math.sqrt(5))) * middle;

    return (int) finalPart;
  }

  /**
   * Returns the incremented fibonacci counter object.
   *
   * @return FibonacciCounter object
   */
  public FibonacciCounter increment() {
    count++;
    fibNumber = fibonacciNumberValue(count);
    return this;
  }

  /**
   * Returns the decremented fibonacci counter object.
   *
   * @return FibonacciCounter object
   */
  public FibonacciCounter decrement() {
    if ( count == 0 ) {
      return this;
    }
    count--;
    fibNumber = fibonacciNumberValue(count);
    return this;
  }

  /**
   * Returns the current count.
   *
   * @return fullName
   */
  public int getCount() {
    return count;
  }

  /**
   * Returns the current fibonacci number associated with the current count.
   *
   * @return fullName
   */
  public int getFibonacciNumber() {
    return fibNumber;
  }

}
