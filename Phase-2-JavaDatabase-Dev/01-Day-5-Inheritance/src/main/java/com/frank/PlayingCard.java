package com.frank;

/***************************************************************************************
 * Generic Playing Card Class
 ***************************************************************************************/
public class PlayingCard {
	/***************************************************************************************
	 * Member Instance Variables - Each instance of the class gets a set
	 ***************************************************************************************/
	private int    value;       // Card value
	private String color;       // Card color  
	private String suit;        // Card suit 

	/***************************************************************************************
	 * 3-arg constructor when user uses value, suit and color to instantiate 
	 ***************************************************************************************/
	
	public PlayingCard(int value, String suit, String color) {
		this.value = value;
		this.suit  = suit;
		this.color = color;
	}

	/***************************************************************************************
	 * Getters - allow users to "see" the values in a Playing Card object
	 ***************************************************************************************/
	public int    getValue() {
		return value;
	}
	public String getColor() {
		return color;
	}
	public String getSuit()  { return suit;
	}
	/***************************************************************************************
	 * Setters - allow users to change the values in a Playing Card object
	 ***************************************************************************************/
	public void setValue(int value) {
		this.value = value;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	/***************************************************************************************
	 * Java does not know how to represent objectes of your class as Strings
	 * There are many times Java needs to represent your object as a String:
	 *
	 *    System.out.println("my Object" + myObject);   // Java needs to convert object to String
	 *
	 * If you don't provide a toString() method, the Object classes toString() is used
	 * so you override the Object class' toString()
	 *
	 * toString() override for the Object class: String toString() 
	 *                            to present the values of a Playing Card object as a String
	 ***************************************************************************************/
	/************************************************************************************************
	 * When a super class does not do what you want, override it
	 *
	 * When override a method for another class (super class) you must be sure the override
	 * interfaces the same way as the method you are overriding: same return type, name, parameters
	 *
	 * This means YOU must know what the interface for the method you are overriding looks like
	 *
	 * the optional @Override annotation tells the compiler to make sure my override has the same
	 * interface as the method I am overriding in my super class
	 *************************************************************************************************/
	@Override   // for the Object class: String toString() - Object toString method returns String / no parameters
	public String toString() {
		return "PlayingCard [value=" + value + ", color=" + color + ", suit=" + suit + ", getValue()=" + getValue()
				+ ", getColor()=" + getColor() + ", getSuit()=" + getSuit() + "]";
	}
	/***************************************************************************************
	 * Since the Object class' .equals() does not know how to compare objects of our class
	 * we override .equals() and we decide how to compare objects of our class
	 *
	 * equals() override for the Object class: boolean equals(Object) 
	 *                   to allow users to see if two Playing Cards have all the same values
	 ***************************************************************************************/
	@Override  // for the Object class: boolean equals(Object) - Object .equals returns boolean / receives an Object
	public boolean equals(Object obj) {    // Note: Parameter is a generic Object class because this is an override
		if (this == obj) {                 // If PlayingCard is being compared to itself...
			return true;                   //      it must be equal - return false
		}
		if (obj == null) {                 // If PlayingCard is being compared to a null object...
			return false;                  //       it must be unequal - return false
		}
		if (getClass() != obj.getClass()) {// If PlayingCard is being compared to a non-PlayingCard object...
			return false;                  //       it must be unequal - return false
		}
		PlayingCard other = (PlayingCard) obj; // Instantiate a PlayingCard from the object passed as parametr
		if (color != other.color) {            // If colors are not	equal...
			return false;                      //       it must be unequal - return false
		}
		if (suit != other.suit) {              // If suits are not equal...
			return false;                      //       it must be unequal - return false
		}
		if (value != other.value) {            // If values are not equal...
			return false;                      //       it must be unequal - return false
		}
		return true;                           // All of the above are true, the objects are equal - return true
	}
	/***************************************************************************************
	 * The Object class' clone method does not know how to make a clone of our object
	 * we override the .clone() method so we decide how to make a copy of object of our class
	 *
	 * clone() method to create a copy of a PlayingCard from a PlayingCard
	 *
	 * We can't use @Override here to ask compile to verify our override because
	 * out method has different return type and parameter type than the Object class clone method
	 * The Object class clone() method returns an Object and has no parameters
	 * Our classes clone() methods return a PlayingCard and has parameters
	 *    because we don't want to require a cast to our class when our class object is cloned
	 *    (we are making it easier for one to use our class)
	 ***************************************************************************************/
	public PlayingCard clone() {
		return new PlayingCard(this.value, this.suit, this.color);  // Use 3-arg ctor to create the object
	}
}
