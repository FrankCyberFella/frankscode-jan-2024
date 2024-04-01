package com.frank.playingcard;

/*
 * A Class contains data and methods related to an object
 * 
 * Objects are instance of a Class - objects are created from a Class
 */
public class PlayingCard {
	
	/*********************************************************************
	 * Data for the PlayingCard Class
	 * 
	 * Data defined in a class is assigned an access type:
	 * 
	 *       public  - any one with an object can access and change the data
	 *       private - only methods defined in the class can 
	 *                 access and change the (Encapsulation)
	 *                 
	 * Data defined in the class are known as "instance variables"
	 *                                     or "member variables"
	 *                                     or "member instance variables"
	 *                                                   
	 * Every instance of the class gets a full set of instance variables
	 **********************************************************************/
	private String suit;
	private String color;
	private int    value;
	
	/*******************************************************************
	 * Constructors - initialize the values in the member data
	 *                when the object is instantiated
	 *                
	 * Constructors have the same name as the class and no return type
	 *              may be public of private (usually public)
	 *              may or may not have parameters
	 *              may be overloaded - same name, different parameters 
	 *             
	 * If not constructors are defined for the class, 
	 *      Java uses a default constructor which initializes based on datatype
	 *      (String/Object --> null, numerics --> boolean --> false       
	 *      
	 * If any constructor is defined a default constructor must also be defines                         
	 *                          
	 *******************************************************************/
	// 3-arg constructor for a PlayingCard
	public PlayingCard(int theValue, String theSuit, String theColor) {
		value = theValue; // set the instance variable value to parameter theValue
		suit  = theSuit;  // set the instance variable suit  to parameter theSuit
		color = theColor; // set the instance variable color to parameter theColor
	}
	
	// default constructor required since we have other constructors
	// a default takes no parameters and initializes to programmer defined values
	public PlayingCard() {
		value = 1;
		color =	"Red";	
		suit  = "Hearts";
	}
	
	/*******************************************************************
	 * getters and setters for the PlayingCard Class - allow controlled access to data
	 *
	 * getter - a method that returns a copy of the member variable 
	 * 
	 * setter - a method that allows access to change a member variable
	 * 
	 * Standard name for a getter is getVariableName
	 *                   a setter is setVariableName
	 *                   
	 * If you use standard names for getters and setters, many tools you
	 *                           use become easier
	 *                           
	 * If you don't use standard names for getters and setters, many tools you
	 *                           use become more difficult
	 *                                                    
	 * Let the IDE generate standard getters and setters and you may modify them if needed                                                   
	 *******************************************************************/
	
	public String getSuit() {   // Allow access to the value in suit
		return suit;
	}

	public void setSuit(String suit) { // Allow suit to be change by user
		this.suit = suit;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	/*******************************************************************
	 * Methods for the PlayingCard Class - process the data in class
	 *******************************************************************/
	// toString() to provide  String representation of an object when Java needs it
	// We need this to override the use of teh Object class generic toString() method
	
	//@word - annotation - information for the compiler
	
	@Override // This is an override to a higher level method with the same name
	          //      verify the function is coded correctly
	          // @Override is optional, but recommended
	
	public String toString() {
		return "PlayingCard [suit=" + suit + ", color=" + color + ", value=" + value + "]";
	}
	
}
