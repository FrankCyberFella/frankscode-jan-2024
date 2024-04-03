package com.frank;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/****************************************************************************************************
 * American Playing Card Class - subclass of generic PlayingCard
 ***************************************************************************************************/
//                subclass        is-a   superclass
public class AmericanPlayingCard extends PlayingCard{  // extends indicates inheritance
	/************************************************************************************************
	 * Constants for values related to American Playing Cards
	 ***********************************************************************************************/	
	private static final int    DEFAULTCARDVALUE = 0;
	private static final String DEFAULTCOLOR     = "BLACK";
	private static final String DEFAULTSUIT      = "Joker";
	private static final int    MAXVALUE         = 13;
	private static final int    MINVALUE         = 0;

	/**************************************************************************************************
	 * Maps used to validate/limit suits, colors and value names for American Playing Cards
	 *************************************************************************************************/	
	//                  key      value
	//                  suit  ,  color             new HashMap(); // OK too!
	private static Map<String,  String> suitMap  = new HashMap<String , String>();  // ~ says same as on left side (optional)
	//                  value , word
	private static Map<Integer, String> valueMap = new TreeMap<Integer, String>(); 
	
	/***************************************************************************************************
	 *Invoke method to populate maps with valid suits, colors & value names for American Playing Cards
	 **************************************************************************************************/	
	// We are using methods to initialize the suitMap and valueMap
	// methods have to be called from some program
	//
	// since the suitMap and valueMap are defined as static
	// only static methods can change their data
	//
	// static data can exist even if no objects exist
	// the suitMap and valueMap will exist if no AmericanPlayingCard objects exist
	// if no AmericanPlayingCard objects exist, no constructors are ever run
	//
	// if we called initializeMaps from a constructor it might never run
	// since we need to have data in suitMap and valueMap when constructor run
	// we tell Java to run initializeMaps() as soon as the Application starts
	//
	// To do so, define a static method outside any other part of the code

	static {             // static method to initialize maps before are ever used
       initializeMaps();
    }
	/***************************************************************************************************
	 * 0-arg / Default Constructor
	 **************************************************************************************************/		
	public AmericanPlayingCard() 
	{
		super(DEFAULTCARDVALUE, DEFAULTSUIT, DEFAULTCOLOR);  // Invoke 3-arg ctor in superclass
		                                                     // with our default values
	} 
	/***************************************************************************************************
	 * 2-arg Constructor for a user provided value and suit
	 ***************************************************************************************************/	
	public AmericanPlayingCard(int value, String suit) {
	// We MUST call a super class constructor as the very first thing we do in a subclass constructor
	// so the super class object that is automatically instantiated by Java gets initialized
	// Java has no idea how we want to initialize the super class object, so it makes up do it
	//  use the keyword super to access the super class

	//        value, suit, color - using the our suitMap to get the color based on the suit
		super(value, suit, suitMap.get(suit));       // Determine color and call super class 3-arg ctor
	
		if (value > MAXVALUE) {                      // If value provided is greater than max value allowed         
			setValue(MAXVALUE);                      //      set it to max value allowed
		}
		if (value < MINVALUE) {                      // If value provided is greater than min value allowed  
			setValue(MINVALUE);                      //      set it to min value allowed
		}
	}
	/****************************************************************************************************
	 * Initialize Maps to valid suit/color combinations and value names
	 ***************************************************************************************************/	
	private static void initializeMaps() {
		//            key        value
		suitMap.put("SPADES"  , "BLACK");
		suitMap.put("CLUBS"   , "BLACK");
		suitMap.put("DIAMONDS", "RED");
		suitMap.put("HEARTS"  , "RED");
		
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
}
