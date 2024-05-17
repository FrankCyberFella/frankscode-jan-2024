package com.frank;

public class PlayingCard {
	
	private int value;    
	private String color;    
	private String suit;     
	
	
	public PlayingCard(int value, String suit, String color) {
		this.value = value;
		this.suit  = suit;
		this.color = color;
	}

	// a method is defined with: access-type   return-type   name(parameters)
	//                             public       data-type - type of data returned bythe method
	//                             private      void      - no data is returned by the method
	//
	// public void - anyone with object of the class may use the method (public)
	//               no data is returned from the method (void)
	//
	// The body of the method follows the definition inside { }

	public int getValue() {
		return value;
	}
	public String getColor() {
		return color;
	}
	public String getSuit() {
		return suit;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}

	// If the behavior of the super class methods is not what you want - you override them to do what you want
	//
	// Right now, we don't like what the Object class toString() and equals() methods do, so we override them

	@Override // Optional - Ask compiler to verify this is proper override of the super class method
	          //            a proper override has same return-type, name, parameters
	          // we are overriding the Object class toString() method which:
	          //            returns a String, is called toString() and receives no parameters
	public String toString() {
		return "PlayingCard [value=" + value + ", color=" + color + ", suit=" + suit + ", getValue()=" + getValue()
				+ ", getColor()=" + getColor() + ", getSuit()=" + getSuit() + "]";
	}

	@Override // Optional - Ask compiler to verify this is proper override of the super class method
	          //            a proper override has same return-type, name, parameters
	          // we are overriding the Object class equals() method which:
 	          //            returns a boolean, is called equals() and receives an Object class object
	          // Note: Using an Object class object usually requires casting to another class to use
	          //       an Object class object is a generic object and Java needs to know the specific class
	public boolean equals(Object obj) {  // return a boolean and receive an Object class object I am calling obj
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PlayingCard other = (PlayingCard) obj;  // We need to create a PlayingCard object from the generic object
		                                        // by casting to PlayingCard class anc assign to new object so we can us it
		if (color != other.color) {
			return false;
		}
		if (suit != other.suit) {
			return false;
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}

// super class is required to have any method where Polymorphism might be used - even if it doesn't need it
public void showCard() {} // required for Polymorphism to work - even though it does nothing

} // End of PlayingCard class
