import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Person class.
 */
public class EmployeeTest {

  private Employee ture;
  private Employee saleh;
  private Employee nic;


  @Before
  public void setUp() {

    ture = new Employee("Ture Carlson", 10, 10);
    saleh = new Employee("Saleh Alkhalifa", 5, 1);
    nic = new Employee("Nic Shepard", 5, 20);

  }

  @Test
  public void testToString() {
    assertEquals("Ture Carlson", ture.toString());
    assertEquals("Saleh Alkhalifa", saleh.toString());
    assertEquals("Nic Shepard", nic.toString());

  }

  @Test
  public void testGetHours() {
    assertEquals(0, ture.getHours(), 0.1);
    assertEquals(0, saleh.getHours(), 0.1);
    assertEquals(0, nic.getHours(), 0.1);

  }

  @Test
  public void testGetWeeklyCheck() {
    assertEquals("$0.00", ture.getWeeklyCheck().toString());
    assertEquals("$0.00", saleh.getWeeklyCheck().toString());
    assertEquals("$0.00", nic.getWeeklyCheck().toString());

  }

  @Test
  public void testAddHoursWorked() {
    ture.addHoursWorked(5);
    saleh.addHoursWorked(1);
    nic.addHoursWorked(5);

    assertEquals(5.0, ture.getHours(), 0.01);
    assertEquals(1.0, saleh.getHours(), 0.01);
    assertEquals(5.0, nic.getHours(), 0.01);

  }

  @Test
  public void testResetHoursWorked() {
    ture.resetHoursWorked();
    saleh.resetHoursWorked();
    nic.resetHoursWorked();

    assertEquals(0.0, ture.getHours(), 0.01);
    assertEquals(0.0, saleh.getHours(), 0.01);
    assertEquals(0.0, nic.getHours(), 0.01);

  }

}