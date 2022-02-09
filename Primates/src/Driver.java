import org.animal.primates.Drill;
import org.animal.primates.Howler;
import org.animal.primates.Primates;
import org.animal.primates.Saki;
import org.animal.primates.Squirrel;
import org.sanctuary.housing.JunglePrimateSanctuary;
import org.sanctuary.housing.Sanctuary;

import java.util.List;
import java.util.Map;

/**
 * driver class to see the output for jungle primate sanctuary.
 */
public class Driver {
  /**
   * Main function.Starting point of the program
   *
   * @param args console arguments
   */
  public static void main(String[] args) {
    System.out.println("Welcome to the Jungle Sanctuary");
    Sanctuary jungleSanctuary;
    try {
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

      System.out.println("List of Primates after adding to the sanctuary");
      System.out.print("-------------------------------------------------------\n");
      for (Primates primate : primates) {
        jungleSanctuary.addNewPrimateToIsolation(primate);
      }
      Map<String, String> allPrimateLocation = jungleSanctuary
              .fetchListOfPrimatesInAlphabeticalOrderWithLocation();
      for (Map.Entry<String, String> entry : allPrimateLocation.entrySet()) {
        System.out.println(" Primate: " + entry.getKey() + " Location : " + entry.getValue());
      }

      jungleSanctuary.removePrimateFromSanctuary("mackienzie");
      jungleSanctuary.removePrimateFromSanctuary("jack");

      System.out.println("After Removing mackienzie and Jack");
      System.out.print("-------------------------\n");
      allPrimateLocation = jungleSanctuary
              .fetchListOfPrimatesInAlphabeticalOrderWithLocation();
      for (Map.Entry<String, String> entry : allPrimateLocation.entrySet()) {
        System.out.println(" Primate: " + entry.getKey() + " Location : " + entry.getValue());
      }

      System.out.println("Species wise Location List");
      System.out.print("---------------------------------\n");
      Map<String, List<String>> speciesLocation =
              jungleSanctuary.fetchSpeciesInAlphabeticalOrderAndTheirLocationInSanctuary();
      for (Map.Entry<String, List<String>> entry : speciesLocation.entrySet()) {
        System.out.println(" Primate: " + entry.getKey() + " Location : " + entry.getValue());
      }

      System.out.println("Location List for a specific species");
      System.out.print("-----------------------------------------\n");
      List<String> givenSpeciesLocation = jungleSanctuary
              .fetchLocationOfAGivenSpecies(primate1.getSpecies());
      System.out.println(" Primate: " + primate1.getSpecies() + " Location : "
              + givenSpeciesLocation.toString());

      System.out.println("Shopping List ");
      System.out.print("-----------------\n");

      Map<String, Integer> shoppingList = jungleSanctuary.fetchShoppingList();
      System.out.println(shoppingList.toString());


      System.out.println("List of Primates after moving primates to enclosures");
      System.out.print("----------------------------------------------------------\n");
      jungleSanctuary.movePrimateToAGivenHousing("halle", "Enclosure3");
      jungleSanctuary.movePrimateToAGivenHousing("aron", "Enclosure2");
      jungleSanctuary.movePrimateToAGivenHousing("wille", "Enclosure1");
      jungleSanctuary.movePrimateToAGivenHousing("willo", "Enclosure1");
      jungleSanctuary.movePrimateToAGivenHousing("willq", "Enclosure1");
      allPrimateLocation = jungleSanctuary
              .fetchListOfPrimatesInAlphabeticalOrderWithLocation();
      for (Map.Entry<String, String> entry : allPrimateLocation.entrySet()) {
        System.out.println(" Primate: " + entry.getKey() + " Location : " + entry.getValue());
      }
      System.out.println("Species wise Location List after moving primates to enclosures");
      System.out.print("---------------------------------\n");
      speciesLocation =
              jungleSanctuary.fetchSpeciesInAlphabeticalOrderAndTheirLocationInSanctuary();
      for (Map.Entry<String, List<String>> entry : speciesLocation.entrySet()) {
        System.out.println(" Primate: " + entry.getKey() + " Location : " + entry.getValue());
      }


      System.out.println("Information List of Houses in Sanctuary");
      System.out.print("----------------------------------------------------------\n");

      Map<String, String> allHousingInfo = jungleSanctuary.fetchAllHousingInformation();
      for (Map.Entry<String, String> entry : allHousingInfo.entrySet()) {
        System.out.println(" HouseId: " + entry.getKey() + " Type : " + entry.getValue());
      }

      System.out.println("Information of chosen enclosure");
      System.out.print("----------------------------------------------------------\n");

      List<Primates> occupants = jungleSanctuary.fetchEnclosureInfo("Enclosure1");
      for (Primates p : occupants) {
        System.out.println(p.toString());
      }


    } catch (IllegalArgumentException | IllegalStateException e) {
      System.out.println(e.getMessage());
    }


  }
}
