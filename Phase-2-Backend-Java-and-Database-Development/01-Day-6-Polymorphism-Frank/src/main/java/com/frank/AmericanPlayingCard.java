package com.frank;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AmericanPlayingCard extends PlayingCard{
	// static means there only shared by all
	// final means cannot be changed once it has been assigned a value
	// static final means only one occurrence, shared by all, that can't be changed
	private static final int    DEFAULTCARDVALUE = 0;
	private static final String DEFAULTCOLOR     = "BLACK";
	private static final String DEFAULTSUIT      = "Joker";
	private static final int    MAXVALUE         = 13;
	private static final int    MINVALUE         = 0;
	private static Map<String,  String> suitMap  = new HashMap();  // Associate a suit and a color
	private static Map<Integer, String> valueMap = new TreeMap();  // Associate a value and word describing it

	// Since the suitMap and valueMap are static - only static methods may change them
	// static data may exist without any objects of the class being instantiated
	// if the initializeMaps() method was in constructors, it may not run - our Maps would not get initialize
	// we need to tell Java to run initializeMap() when the applications which when static data is created
	// by putting the call to call to initializeMaps() outside any function/method - Java knows to run it at the
	//            start of the app

	// static is required because it is calling the static method initializeMaps()
	static {  // Note: no function/method definitions - anonymous function
		initializeMaps();  // call the function to initialize the suitMap and valueMap
	}
	
	public AmericanPlayingCard() 
	{
		super(DEFAULTCARDVALUE, DEFAULTSUIT, DEFAULTCOLOR);
	} 
// This constructor does not allow teh color to be sent as a parameter
//      since color is dependent on the suit, we will derive the color from the suit	
//      we will get the color from the suitMap for the suit
	public AmericanPlayingCard(int value, String suit) {
		// use the conditional operator (?) to provide conditional parameters to a method
		//
		// the way the conditional operator works    condition ? value-if-true : value-if-false
		//
		//                             true : false
		// suitMap.containsKey(suit) ? suit : DEFAULTSUIT
		//
		// if the suitMap contains a key that equals the suit we were passed, use it, otherwise use DEFAULTSUIT
		//
		//       color       != null ?      color        : defaultcolor
		// suitMap.get(suit) != null ? suitMap.get(suit) : DEFAULTCOLOR)
		//
		// if the value returned from the get for suitMap using the suit passed is not null, use the color from teh suitMap
		//                                            otherwise use the DEFAULTCOLOR
		//
		// if no color is returned from the suitMap, use the DEFAULTCOLOR
		//

		// call the super class constructor with the value passed and the suit and color we determined
		super(value,                                                        // Call super ctor with value passed
			  suitMap.containsKey(suit) ? suit : DEFAULTSUIT,               // If valid suit passed, use it otherwise use DEFAULTSUIT
		      suitMap.get(suit) != null ? suitMap.get(suit) : DEFAULTCOLOR);// If valid suit passed, use color for suit otherwise use DEFAULTCOLOR  
		
		if (value > MAXVALUE) {
			setValue(MAXVALUE);
		}
		if (value < MINVALUE) {
			setValue(MINVALUE);
		}
	}
// this must be a static method because it is changing static data (suitMap and valueMap)
	static private void initializeMaps() {  // initialize the suitMap and valueMaps
		//            key      ,  value
		suitMap.put("SPADES"   , "BLACK");
		suitMap.put("CLUBS"    , "BLACK");
		suitMap.put("DIAMONDS" , "RED");
		suitMap.put("HEARTS"   , "RED");
		suitMap.put(DEFAULTSUIT, DEFAULTCOLOR);  // the DEFAULTSUIT is associated with the DEFAULTCOLOR

		//        key ,value
		valueMap.put(0,"Joker");
		valueMap.put(1,"Ace");
		valueMap.put(2,"Two");
		valueMap.put(3,"Three");
		valueMap.put(4,"Four");
		valueMap.put(5,"Five");
		valueMap.put(6,"Six");
		valueMap.put(7,"Seven");
		valueMap.put(8,"Eight");
		valueMap.put(9,"Nine");
		valueMap.put(10,"Ten");
		valueMap.put(11,"Jack");
		valueMap.put(12,"Queen");
		valueMap.put(13,"King");
	}
	// It is common for a subclass to call a super class method it overrides - in this case toString()
	// because the sub class doesn't know how to handle the super class data
	// the sub class is only responsible for any data it adds to the super class
	// so all the sub class has to do is decide how to handle it's new data
	// and use the super class method to handle super class data
	//
	@Override
	public String toString() {
		return "AmericanPlayingCard: " 
	          +"Value: "  + valueMap.get(getValue())  // use the valueMap to get the value name
	          +" - Color: " + suitMap.get(getSuit())  // use the suitMap to get the color for the suit
			  +" - Suit: "  + getSuit()               // use the super class method to get the suit
				                                      // super. optional because the sub class does not a method called getSuit()
			  +" - super.toString()=" + super.toString() + "\n";  // call the super class toString()
		                                                          // super. is required because we have a method with the same name
		                                                          // if omitted super. we would be calling ourselves
	}

	// Display the card - note: use of toString()
	public void showCard() {
		System.out.println(this.toString());
	}

}
