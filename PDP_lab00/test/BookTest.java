import org.junit.Before;
import org.junit.Test;

import cs5010.lab00.Book;
import cs5010.lab00.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class to test methods of book class.
 */
public class BookTest {

  private Book book;

  @Before
  public void setUp() {
    Person author = new Person("Stephen", "Hawkins", 1947);
    this.book = new Book("The beginning of Time", author, 190.99f);
  }

  @Test
  public void testGetTitle() {
    assertEquals("The book title matches!", "The beginning of Time", this.book.getTitle());
  }


  @Test
  public void testGetAuthor() {
    Person actualAuthor = this.book.getAuthor();
    assertEquals("Author Matched!!!!", actualAuthor.getFirstName(), "Stephens");
    assertEquals("Last Name Matched", actualAuthor.getLastName(), "Hawkins");
    assertNotEquals("Date of birth does not match", actualAuthor.getYearOfBirth(), 1957);
  }

  @Test
  public void testGetPrice() {
    assertNotEquals("Price does not match", 200.00f, this.book.getPrice());
  }
}