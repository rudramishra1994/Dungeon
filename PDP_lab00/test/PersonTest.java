import cs5010.lab00.Person;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class to test Person class methods.
 */

public class PersonTest {

  private Person person;

  @Before
  public void setUp() {
    this.person = new Person("John",
            "doe",
            1994);
  }

  @Test
  public void testFirstName() {
    assertEquals("names are same", this.person.getFirstName(), "John");
  }

  @Test
  public void testLastName() {
    assertNotEquals("Last Name Do not match", this.person.getLastName(), "kar");
  }

  @Test
  public void testYearOfBirth() {
    assertEquals("Year of Bath Matches", this.person.getYearOfBirth(), 1994);
  }


}