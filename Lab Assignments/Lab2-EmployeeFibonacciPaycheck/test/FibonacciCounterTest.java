
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Fibonacci calculator.
 */
public class FibonacciCounterTest {

  private FibonacciCounter fibTesterNumOne;
  private FibonacciCounter fibTesterNumTwo;

  @Before
  public void setUp() {
    fibTesterNumOne = new FibonacciCounter(2);
    fibTesterNumTwo = new FibonacciCounter(21);
  }

  @Test
  public void testGetCount() {
    assertEquals(2, fibTesterNumOne.getCount());
    assertEquals(21, fibTesterNumTwo.getCount());
  }

  @Test
  public void testGetFibonacciNumber() {
    assertEquals(1, fibTesterNumOne.getFibonacciNumber());
    assertEquals(10946, fibTesterNumTwo.getFibonacciNumber());
  }

  @Test
  public void testIncrement() {
    fibTesterNumOne.increment();
    assertEquals(3,fibTesterNumOne.getCount() );
  }

  @Test
  public void testDecrement() {
    fibTesterNumOne.decrement();
    assertEquals(1,fibTesterNumOne.getCount() );
  }

}