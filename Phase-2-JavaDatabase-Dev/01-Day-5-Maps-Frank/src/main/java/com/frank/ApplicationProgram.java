package com.frank;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ApplicationProgram {

		public static void main(String[] args) {

			System.out.println("=".repeat(60));
			System.out.println(" -------- MAP Examples --------");
			System.out.println("=".repeat(60));
			System.out.println();

			// Map is a Collection class to store and access key-value pairs
			//
			// The key   - is a unique identifier to associate with a value
			// The value - the data to be associated with a key
			//
			// A Map is also known as as "associative array"
			//
			// the content of the key must be unique
			// the content of the value does not have to be unique

			// Types of Maps:
			//
			//       HashMap       - entries are stored in an unknown order
			//       TreeMap       - entries are stored in key sequence order
			//       LinkedHashMap - entries are stored in the order in which they were added

			// Two common ways of defining a map:
			//
			//    Map<key-data-type, value-data-type>        nameOfMap = new typeOfMap();
			//
			//    typeOfMap<key-data-type, value-data-type>  nameOfMap = new typeOfMap();
			//
			// Define a Map where the key is a String and the value is String
			//
			//    Map<String, String>     myMap = new HashMap();
			//    HashMap<String, String> myMap = new HashMap();
			//    Map<String, String>     myMap = new HashMap<String, String>();
			//    HashMap<String, String> myMap = new HashMap<String, String>();
			//
			//    Map<String, String>     myMap = new TreeMap();
			//    TreeMap<String, String> myMap = new TreeMap();
			//    Map<String, String>     myMap = new TreeMap<String, String>();
			//    TreeMap<String, String> myMap = new TreeMap<String, String>();
			//
			//    Map<String, String>           myMap = new LinkedHashMap();
			//    LinkedHashMap<String, String> myMap = new LinkedHashMap();
			//    LinkedMap<String, String>     myMap = new LinkedHashMap<String, String>();
			//    HashMap<String, String>       myMap = new LinkedHashMap<String, String>();

			// Define a Map to associate a Starship with their captain - ex. "Enterprise" - "James T Kirk"
			// key   - Starship Name  - String object
			// value - the name of the place they live is a String
			//
			//   key     value
			//   type ,  type   Map name         = new typeOfMap();
			Map<String, String> starshipCaptains = new LinkedHashMap(); // a LinkedHashMap stores the entries in an entry sequence
			                                                            // a TreeMap stores the entries in key sequence
			                                                            // a HashMap stores the entries in an unknown sequence
			                                                       
			// Add a few entries to our Map use .put(content-of-key, content-of-value)
			starshipCaptains.put("Enterprise", "James T Kirk");
			starshipCaptains.put("Enterprise-A", "James T Kirk");
			starshipCaptains.put("Enterprise-B", "John Harriman");
			starshipCaptains.put("Enterprise-D","Jean-Luc Picard");
			starshipCaptains.put("Enterprise-E", "Jean-Luc Picard");
			starshipCaptains.put("Enterprise-F","Elizabeth Shelby, Admiral");
			starshipCaptains.put("Enterprise-G","Seven of Nine");
			starshipCaptains.put("Excelsior","Hikaru Sulu");
			starshipCaptains.put("Odyssey","Keough");
			starshipCaptains.put("Defiant","Benjamin Sisko");
			starshipCaptains.put("Voyager,","Catherine Janeway");
			starshipCaptains.put("Titan","William Riker");

			System.out.println("\n-------- Retrieve entries from Map by key ---------");
			
			// use .get(key) to retrieve an entry from the Map - returns the value associated with key given or null
			System.out.println("Starship Enterprise-D was captained by: " + starshipCaptains.get("Enterprise-D"));
			System.out.println("Starship Titan was captained by: "        + starshipCaptains.get("Titan"));
			System.out.println("Starship Excelsior was captained by: "    + starshipCaptains.get("Excelsior"));
			System.out.println("Starship Sutherland was captained by: "   + starshipCaptains.get("Sutherland"));
			System.out.println("Starship Enterprise was captained by: "   + starshipCaptains.get("Enterprise"));
			System.out.println("Starship Frank was captained by: "        + starshipCaptains.get("Frank"));

			// Typically the key is stored in a variable
			String shipWeWant = "Defiant";
			System.out.println("Starship " + shipWeWant  + " was captained by: " + starshipCaptains.get(shipWeWant));
			

			// Since the content of a key must be unique in a Map
			// If you try to add an entry with existing key 
			//       it updates the value for the existing key - AND DOESN'T TELL YOU!!!
			System.out.println("\n-------- add Spock as Captain of the Enterprise ---------");
			starshipCaptains.put("Enterprise", "Spock"); // "Enterprise" is already a key in the Map
			System.out.println("Starship Enterprise was captained by: " + starshipCaptains.get("Enterprise"));


			System.out.println("\n-------- Check to see if key \"Sutherland\" is already in Map and do not add if it is ---------");
			
			shipWeWant = "Sutherland";
			
			// .containsKey(key) - Check to see if a key is already in the Map before we try to add it
			
			if (starshipCaptains.containsKey(shipWeWant)) {   // Check to see if Map contains the key in whoWeWant
				System.out.println(shipWeWant + " is already in the Map - continuing will update, so not adding");
			}
			else {  // if shipWeWant is not already in the Map - add them
				System.out.println(shipWeWant + " is not in the Map - continuing to add it");
				starshipCaptains.put(shipWeWant,"Data");
			}
			System.out.println("\n" + shipWeWant + " was captained by: " + starshipCaptains.get(shipWeWant));
					

			System.out.println("------------------------------------------------------");
			System.out.println("\n-------- Process all the entries in a Map  ---------");

			// To process all the entries in a Map
			//    we need to get all the keys in the Map and use them to access the values in the Map
			//
			// .keySet() may be used to retrieve all the keys from Map as a Set object
			//
			// A Set object an a unique set of values from a Collection class
			//
			//   To define a Set object:   Set<data-type> name = something-that-gives-us-a-Set

			Set<String> theKeys = starshipCaptains.keySet();  // Hold all the keys from the Map - keys are String in our Map

			// We can go through the Set of keys one at time retrieving an entry from the Map
			
			// for-each loop for(dataType elemName : collectionObject)
			//     it goes through the entire collection object from start to end
			//     assigning the current element to elemName given
			//     You use the elemName in the loop to process the current element
			for (String aKey : theKeys) {  // Go through theKeys one at a time storing the current key in aKey
				System.out.println("Entry in Map for key: " + aKey + " is: " + starshipCaptains.get(aKey));  // get the value for current key
			}

			System.out.println("\n-------- Remove Map entry with key \"Odyssey\" from Map -------");
			// .remove(key) - will remove an entry from the Map for the key given

			starshipCaptains.remove("Odyssey"); // remove the entry for the "Odyssey" from the Map
			System.out.println("Starship Odyssey was captained by: " + starshipCaptains.get("Odyssey"));

		} // End of our main()
	} // End of the class that contains main()

