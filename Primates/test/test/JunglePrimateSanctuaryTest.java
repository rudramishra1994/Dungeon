package test;

import org.animal.primates.Drill;
import org.animal.primates.Howler;
import org.animal.primates.Primates;
import org.animal.primates.Saki;
import org.animal.primates.Squirrel;
import org.junit.Test;
import org.sanctuary.housing.JunglePrimateSanctuary;
import org.sanctuary.housing.Sanctuary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test Class to test the functionalities of the jungle primate class.
 * Such as add primate,remove primate,move primate ,increase capacity etc.
 */
public class JunglePrimateSanctuaryTest {


  protected JunglePrimateSanctuary createSanctuary(int isolation, int enclosure,
                                                   int enclosureSize) {
    return new JunglePrimateSanctuary(isolation, enclosure, enclosureSize);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkSanctuaryCreation() {
    Sanctuary jungleSanctuary = createSanctuary(100, 50, -100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkSanctuaryForIllegalIsolation() {
    createSanctuary(0, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkSanctuaryForIllegalFood() {
    Sanctuary jungleSanctuary = createSanctuary(0, 100, 100);
    Primates primate1 = new Drill("Jack", "Male", 12, 33.4, 25, "NoNuts");
    jungleSanctuary.addNewPrimateToIsolation(primate1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkSanctuaryForIllegalEnclosure() {
    Sanctuary jungleSanctuary = createSanctuary(100, -100, 100);
    Primates primate1 = new Drill("Jack", "Male", 12, 33.4, 25, "NoNuts");
    jungleSanctuary.addNewPrimateToIsolation(primate1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkSanctuaryForSameNamePrimateAddition() {
    Sanctuary jungleSanctuary = createSanctuary(100, 50, 100);
    Primates primate1 = new Howler("Jack", "Male", 12, 33.4, 25, "Fruit");
    Primates primate2 = new Drill("Jick", "Male", 12, 33.4, 25, "Nuts");
    jungleSanctuary.addNewPrimateToIsolation(primate1);
    jungleSanctuary.addNewPrimateToIsolation(primate2);
  }

  @Test
  public void testincreaseCapacityOfTheSanctuary() {
    Sanctuary jungleSanctuary = createSanctuary(100, 50, 100);
    Map<String, String> allHousingInfo = jungleSanctuary.fetchAllHousingInformation();
    assertEquals(150, allHousingInfo.size());
    jungleSanctuary.increaseCapacityOfTheSanctuary(20, 50);
    allHousingInfo = jungleSanctuary.fetchAllHousingInformation();
    System.out.println(allHousingInfo.toString());
  }

  @Test
  public void testAddPrimate() {
    Sanctuary jungleSanctuary;

    jungleSanctuary = new JunglePrimateSanctuary(20, 10, 100);
    Primates primate1 = new Squirrel("jack", "male", 12, 34.3, 33, "fruits");
    Primates primate2 = new Squirrel("jackie", "male", 12, 34.3, 33, "nuts");
    Primates primate3 = new Drill("jill", "male", 12, 34.3, 33, "sap");
    Primates primate4 = new Drill("aron", "male", 12, 34.3, 33, "fruits");
    Primates primate5 = new Howler("johnie", "male", 12, 34.3, 33, "seeds");
    Primates primate6 = new Howler("john", "male", 12, 34.3, 33, "fruits");

    Primates primate7 = new Saki("halle", "male", 12, 34.3, 33, "seeds");
    Primates primate8 = new Saki("henry", "male", 12, 34.3, 33, "fruits");
    Primates primate9 = new Squirrel("mack", "male", 12, 34.3, 33, "insects");
    Primates primate10 = new Squirrel("mackienzie", "male", 12, 34.3, 33, "insects");
    Primates primate11 = new Squirrel("willa", "male", 12, 34.3, 33, "fruits");
    Primates primate12 = new Squirrel("willi", "male", 12, 34.3, 33, "fruits");
    Primates primate13 = new Squirrel("wille", "male", 12, 34.3, 33, "fruits");
    Primates primate14 = new Squirrel("willo", "male", 12, 34.3, 33, "fruits");
    Primates primate15 = new Squirrel("willq", "male", 12, 34.3, 33, "fruits");
    Primates primate16 = new Squirrel("willf", "male", 12, 34.3, 33, "fruits");
    Primates[] primates = {primate1, primate2, primate3,
                              primate4, primate5, primate6,
                              primate7, primate8, primate9, primate10, primate11,
                              primate12, primate13, primate14, primate15, primate16};


    for (Primates primate : primates) {
      jungleSanctuary.addNewPrimateToIsolation(primate);
    }
    assertEquals(16, jungleSanctuary.fetchListOfPrimatesInAlphabeticalOrderWithLocation().size());
  }

  @Test
  public void testRemovePrimate() {
    Sanctuary jungleSanctuary = new JunglePrimateSanctuary(20, 10, 100);
    Primates primate1 = new Squirrel("jack", "male", 12, 34.3, 33, "fruits");
    Primates primate2 = new Squirrel("jackie", "male", 12, 34.3, 33, "nuts");
    Primates primate3 = new Drill("jill", "male", 12, 34.3, 33, "sap");
    Primates primate4 = new Drill("aron", "male", 12, 34.3, 33, "fruits");
    Primates primate5 = new Howler("johnie", "male", 12, 34.3, 33, "seeds");
    Primates primate6 = new Howler("john", "male", 12, 34.3, 33, "fruits");

    Primates primate7 = new Saki("halle", "male", 12, 34.3, 33, "seeds");
    Primates primate8 = new Saki("henry", "male", 12, 34.3, 33, "fruits");
    Primates primate9 = new Squirrel("mack", "male", 12, 34.3, 33, "insects");
    Primates primate10 = new Squirrel("mackienzie", "male", 12, 34.3, 33, "insects");
    Primates primate11 = new Squirrel("willa", "male", 12, 34.3, 33, "fruits");
    Primates primate12 = new Squirrel("willi", "male", 12, 34.3, 33, "fruits");
    Primates primate13 = new Squirrel("wille", "male", 12, 34.3, 33, "fruits");
    Primates primate14 = new Squirrel("willo", "male", 12, 34.3, 33, "fruits");
    Primates primate15 = new Squirrel("willq", "male", 12, 34.3, 33, "fruits");
    Primates primate16 = new Squirrel("willf", "male", 12, 34.3, 33, "fruits");
    Primates[] primates = {primate1, primate2, primate3,
                              primate4, primate5, primate6,
                              primate7, primate8, primate9, primate10, primate11,
                              primate12, primate13, primate14, primate15, primate16};


    for (Primates primate : primates) {
      jungleSanctuary.addNewPrimateToIsolation(primate);
    }
    assertEquals(16, jungleSanctuary.fetchListOfPrimatesInAlphabeticalOrderWithLocation().size());
    jungleSanctuary.removePrimateFromSanctuary("mackienzie");
    jungleSanctuary.removePrimateFromSanctuary("jack");
    assertEquals(14, jungleSanctuary.fetchListOfPrimatesInAlphabeticalOrderWithLocation().size());
  }

  @Test
  public void testMovePrimates() {
    Sanctuary jungleSanctuary = new JunglePrimateSanctuary(20, 10, 100);
    Primates primate1 = new Squirrel("jack", "male", 12, 34.3, 33, "fruits");
    Primates primate2 = new Squirrel("jackie", "male", 12, 34.3, 33, "nuts");
    Primates primate3 = new Drill("jill", "male", 12, 34.3, 33, "sap");
    Primates primate4 = new Drill("aron", "male", 12, 34.3, 33, "fruits");
    Primates primate5 = new Howler("johnie", "male", 12, 34.3, 33, "seeds");
    Primates primate6 = new Howler("john", "male", 12, 34.3, 33, "fruits");

    Primates primate7 = new Saki("halle", "male", 12, 34.3, 33, "seeds");
    Primates primate8 = new Saki("henry", "male", 12, 34.3, 33, "fruits");
    Primates primate9 = new Squirrel("mack", "male", 12, 34.3, 33, "insects");
    Primates primate10 = new Squirrel("mackienzie", "male", 12, 34.3, 33, "insects");
    Primates primate11 = new Squirrel("willa", "male", 12, 34.3, 33, "fruits");
    Primates primate12 = new Squirrel("willi", "male", 12, 34.3, 33, "fruits");
    Primates primate13 = new Squirrel("wille", "male", 12, 34.3, 33, "fruits");
    Primates primate14 = new Squirrel("willo", "male", 12, 34.3, 33, "fruits");
    Primates primate15 = new Squirrel("willq", "male", 12, 34.3, 33, "fruits");
    Primates primate16 = new Squirrel("willf", "male", 12, 34.3, 33, "fruits");
    Primates[] primates = {primate1, primate2, primate3,
                              primate4, primate5, primate6,
                              primate7, primate8, primate9, primate10, primate11,
                              primate12, primate13, primate14, primate15, primate16};


    for (Primates primate : primates) {
      jungleSanctuary.addNewPrimateToIsolation(primate);
    }
    jungleSanctuary.movePrimateToAGivenHousing("halle", "Enclosure3");
    jungleSanctuary.movePrimateToAGivenHousing("aron", "Enclosure2");
    jungleSanctuary.movePrimateToAGivenHousing("wille", "Enclosure1");
    jungleSanctuary.movePrimateToAGivenHousing("willo", "Enclosure1");
    jungleSanctuary.movePrimateToAGivenHousing("willq", "Enclosure1");
    List<Primates> occupant1 = jungleSanctuary.fetchEnclosureInfo("Enclosure1");
    List<Primates> occupant2 = jungleSanctuary.fetchEnclosureInfo("Enclosure2");
    assertEquals(3, occupant1.size());
    assertEquals(1, occupant2.size());
    jungleSanctuary.movePrimateToAGivenHousing("wille", "Isolation18");
    jungleSanctuary.movePrimateToAGivenHousing("willo", "Isolation19");
    assertEquals(1, occupant1.size());
    assertEquals(1, occupant2.size());
    occupant1 = jungleSanctuary.fetchEnclosureInfo("Enclosure1");
    assertEquals(1, occupant1.size());
  }

  @Test
  public void testSpeciesWiseLocation() {
    Sanctuary jungleSanctuary = new JunglePrimateSanctuary(20, 10, 100);
    Primates primate1 = new Squirrel("jack", "male", 12, 34.3, 33, "fruits");
    Primates primate2 = new Squirrel("jackie", "male", 12, 34.3, 33, "nuts");
    Primates primate3 = new Drill("jill", "male", 12, 34.3, 33, "sap");
    Primates primate4 = new Drill("aron", "male", 12, 34.3, 33, "fruits");
    Primates primate5 = new Howler("johnie", "male", 12, 34.3, 33, "seeds");
    Primates primate6 = new Howler("john", "male", 12, 34.3, 33, "fruits");

    Primates primate7 = new Saki("halle", "male", 12, 34.3, 33, "seeds");
    Primates primate8 = new Saki("henry", "male", 12, 34.3, 33, "fruits");
    Primates primate9 = new Squirrel("mack", "male", 12, 34.3, 33, "insects");
    Primates primate10 = new Squirrel("mackienzie", "male", 12, 34.3, 33, "insects");
    Primates primate11 = new Squirrel("willa", "male", 12, 34.3, 33, "fruits");
    Primates primate12 = new Squirrel("willi", "male", 12, 34.3, 33, "fruits");
    Primates primate13 = new Squirrel("wille", "male", 12, 34.3, 33, "fruits");
    Primates primate14 = new Squirrel("willo", "male", 12, 34.3, 33, "fruits");
    Primates primate15 = new Squirrel("willq", "male", 12, 34.3, 33, "fruits");
    Primates primate16 = new Squirrel("willf", "male", 12, 34.3, 33, "fruits");
    Primates[] primates = {primate1, primate2, primate3,
                              primate4, primate5, primate6,
                              primate7, primate8, primate9, primate10, primate11,
                              primate12, primate13, primate14, primate15, primate16};


    for (Primates primate : primates) {
      jungleSanctuary.addNewPrimateToIsolation(primate);
    }
    Map<String, List<String>> speciesLocation =
            jungleSanctuary.fetchSpeciesInAlphabeticalOrderAndTheirLocationInSanctuary();
    assertEquals(2, speciesLocation.get("Drill").size());
    assertEquals(10, speciesLocation.get("Squirrel").size());
    List<String> list = new ArrayList<>(speciesLocation.keySet());
    assertTrue(list.stream().sorted().collect(Collectors.toList()).equals(list));
  }

  @Test
  public void testFetchPrimatesInDictionaryorder() {
    Sanctuary jungleSanctuary;

    jungleSanctuary = new JunglePrimateSanctuary(20, 10, 100);
    Primates primate1 = new Squirrel("jack", "male", 12, 34.3, 33, "fruits");
    Primates primate2 = new Squirrel("jackie", "male", 12, 34.3, 33, "nuts");
    Primates primate3 = new Drill("jill", "male", 12, 34.3, 33, "sap");
    Primates primate4 = new Drill("aron", "male", 12, 34.3, 33, "fruits");
    Primates primate5 = new Howler("johnie", "male", 12, 34.3, 33, "seeds");
    Primates primate6 = new Howler("john", "male", 12, 34.3, 33, "fruits");

    Primates primate7 = new Saki("halle", "male", 12, 34.3, 33, "seeds");
    Primates primate8 = new Saki("henry", "male", 12, 34.3, 33, "fruits");
    Primates primate9 = new Squirrel("mack", "male", 12, 34.3, 33, "insects");
    Primates primate10 = new Squirrel("mackienzie", "male", 12, 34.3, 33, "insects");
    Primates primate11 = new Squirrel("willa", "male", 12, 34.3, 33, "fruits");
    Primates primate12 = new Squirrel("willi", "male", 12, 34.3, 33, "fruits");
    Primates primate13 = new Squirrel("wille", "male", 12, 34.3, 33, "fruits");
    Primates primate14 = new Squirrel("willo", "male", 12, 34.3, 33, "fruits");
    Primates primate15 = new Squirrel("willq", "male", 12, 34.3, 33, "fruits");
    Primates primate16 = new Squirrel("willf", "male", 12, 34.3, 33, "fruits");
    Primates[] primates = {primate1, primate2, primate3,
                              primate4, primate5, primate6,
                              primate7, primate8, primate9, primate10, primate11,
                              primate12, primate13, primate14, primate15, primate16};


    for (Primates primate : primates) {
      jungleSanctuary.addNewPrimateToIsolation(primate);
    }
    Map<String, String> speciesLocation =
            jungleSanctuary.fetchListOfPrimatesInAlphabeticalOrderWithLocation();
    List<String> list = new ArrayList<>(speciesLocation.keySet());
    assertTrue(list.stream().sorted().collect(Collectors.toList()).equals(list));
  }

  @Test
  public void testShoppingList() {
    Sanctuary jungleSanctuary;

    jungleSanctuary = new JunglePrimateSanctuary(20, 10, 100);
    Primates primate1 = new Squirrel("jack", "male", 12, 34.3, 33, "fruits");
    Primates primate2 = new Squirrel("jackie", "male", 12, 34.3, 33, "nuts");
    Primates primate3 = new Drill("jill", "male", 12, 34.3, 33, "sap");
    Primates primate4 = new Drill("aron", "male", 12, 34.3, 33, "fruits");
    Primates primate5 = new Howler("johnie", "male", 12, 34.3, 33, "seeds");
    Primates primate6 = new Howler("john", "male", 12, 34.3, 33, "fruits");

    Primates primate7 = new Saki("halle", "male", 12, 34.3, 33, "seeds");
    Primates primate8 = new Saki("henry", "male", 12, 34.3, 33, "fruits");
    Primates primate9 = new Squirrel("mack", "male", 12, 34.3, 33, "insects");
    Primates primate10 = new Squirrel("mackienzie", "male", 12, 34.3, 33, "insects");
    Primates primate11 = new Squirrel("willa", "male", 12, 34.3, 33, "fruits");
    Primates primate12 = new Squirrel("willi", "male", 12, 34.3, 33, "fruits");
    Primates primate13 = new Squirrel("wille", "male", 12, 34.3, 33, "fruits");
    Primates primate14 = new Squirrel("willo", "male", 12, 34.3, 33, "fruits");
    Primates primate15 = new Squirrel("willq", "male", 12, 34.3, 33, "fruits");
    Primates primate16 = new Squirrel("willf", "male", 12, 34.3, 33, "fruits");
    Primates[] primates = {primate1, primate2, primate3,
                              primate4, primate5, primate6,
                              primate7, primate8, primate9, primate10, primate11,
                              primate12, primate13, primate14, primate15, primate16};


    for (Primates primate : primates) {
      jungleSanctuary.addNewPrimateToIsolation(primate);
    }
    Map<String, Integer> shoppingList = jungleSanctuary.fetchShoppingList();
    int fruit = shoppingList.get("fruits");
    assertEquals(5000, fruit);
    fruit = shoppingList.get("insects");
    assertEquals(1000, fruit);
    fruit = shoppingList.get("nuts");
    assertEquals(500, fruit);
    fruit = shoppingList.get("sap");
    assertEquals(500, fruit);
    fruit = shoppingList.get("seeds");
    assertEquals(1000, fruit);

  }


}