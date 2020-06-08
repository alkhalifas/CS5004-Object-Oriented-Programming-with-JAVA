

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Person class.
 */
public class BookTest {

  private Book john;

  @Before
  public void setUp() {

    john = new Book("John", "Doe", 19.45f);
  }

  @Test
  public void testFirst() {
    assertEquals("John", john.getTitle());

  }

  @Test
  public void testSecond() {
    assertEquals("Doe", john.getAuthor());
  }

  @Test
  public void testPrice() {
    assertEquals("1945", john.getPrice());
  }

}