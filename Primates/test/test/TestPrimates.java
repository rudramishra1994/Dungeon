package test;

import org.animal.primates.Drill;
import org.animal.primates.Guereza;
import org.animal.primates.Howler;
import org.animal.primates.Mangabey;
import org.animal.primates.Primates;
import org.animal.primates.Saki;
import org.animal.primates.Spider;
import org.animal.primates.Squirrel;
import org.animal.primates.Tamarin;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the Primate interface.
 * Validation of the data is being performed.
 */
public class TestPrimates {
  public static final String MALE = "Male";
  public static final String FEMALE = "Female";


  @Test(expected = IllegalArgumentException.class)
  public void TestSpeciesForNegativeWeight() {
    Primates primate = new Howler("Jimmy", MALE, 13, -20.2, 30.2, "Sap");
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestSpeciesForZeroWeight() {
    Primates primate = new Howler("Shanon", FEMALE, 13, 0, 30.2, "Sap");
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestSpeciesForZeroAge() {
    Primates primate = new Howler("Jimmy", MALE, 0, 10, 30.2, "Sap");
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestSpeciesForNegativeAge() {
    Primates primate = new Howler("Jimmy", MALE, -10, 10, 30.2, "Sap");
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestSpeciesForNegativeSize() {
    Primates primate = new Howler("Jimmy", MALE, -10, 10, -30.2, "Sap");
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestSpeciesForZeroSize() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 0, "Sap");
  }

  @Test
  public void TestGetName() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("Jimmy", primate.getName());
  }

  @Test
  public void TestGetAge() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals(10, primate.getAge());
  }

  @Test
  public void TestGetSex() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals(MALE, primate.getSex());
  }

  @Test
  public void TestGetSize() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals(32.1, primate.getSize(), 0.0000001);
  }

  @Test
  public void TestGetWeight() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals(10, primate.getWeight(), 0.0000001);
  }

  @Test
  public void TestUpdateAge() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    primate.updateAge(11);
    assertEquals(11, primate.getAge());
  }

  @Test
  public void TestUpdateWeight() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    primate.updateWeight(13.2);
    assertEquals(13.2, primate.getWeight(), 0.000001);
  }

  @Test
  public void TestUpdateSize() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    primate.updateSize(33.2);
    assertEquals(33.2, primate.getSize(), 0.000001);
  }

  @Test
  public void TestSpaceNeededByThePrimate() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    primate.updateSize(33.2);
    assertEquals(33.2, primate.getSize(), 0.000001);
  }

  @Test
  public void TestGetSpecies() {
    Primates primate = new Howler("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("howler", primate.getSpecies().toLowerCase());
    Primates primate2 = new Drill("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("drill", primate2.getSpecies().toLowerCase());
    Primates primate3 = new Guereza("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("guereza", primate3.getSpecies().toLowerCase());
    Primates primate4 = new Squirrel("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("squirrel", primate4.getSpecies().toLowerCase());
    Primates primate5 = new Mangabey("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("mangabey", primate5.getSpecies().toLowerCase());
    Primates primate6 = new Saki("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("saki", primate6.getSpecies().toLowerCase());
    Primates primate7 = new Spider("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("spider", primate7.getSpecies().toLowerCase());
    Primates primate8 = new Tamarin("Jimmy", MALE, 10, 10, 32.1, "Sap");
    assertEquals("tamarin", primate8.getSpecies().toLowerCase());

  }


}