import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Person class.
 */
public class PayCheckTest {

  private PayCheck turePay;
  private PayCheck salehPay;

  @Before
  public void setUp() {
    turePay = new PayCheck("Ture Carlson", 10, 10);
    salehPay = new PayCheck("Saleh Alkhalifa", 0, 10);

  }

  @Test
  public void getTotalPay() {
    assertEquals("$100.00", turePay.toString());
    assertEquals("$0.00", salehPay.toString());

  }

  @Test
  public void testToString() {
    assertEquals("$100.00", turePay.toString());
    assertEquals("$0.00", salehPay.toString());

  }


}