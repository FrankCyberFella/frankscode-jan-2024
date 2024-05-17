package com.frank;

public class UsePlayingCards {
// This is the Application program - has main()
//
// instantiate and use object  of classes
//
	public static void main(String[] args) {
		System.out.println("\nInstantiating aUSACard as an ace of Hearts");
		AmericanPlayingCard aUSACard  = new AmericanPlayingCard(1, "HEARTS");  // Instantiate an Ace of Hearts
		System.out.println("aUSACard is : \n" + aUSACard);  // + requires a String - look for a toString()
		
		System.out.println("\nInstantiating aUSACard2 with a value that exceeds maximum");
		AmericanPlayingCard aUSACard2 = new AmericanPlayingCard(14, "SPADES"); // Value exceeds maximum
		System.out.println("aUSACard2 is : \n" + aUSACard2);

		System.out.println("\nInstantiating aUSACard3 with a value that is below minimum");

		AmericanPlayingCard aUSACard3 = new AmericanPlayingCard(-1, "SPADES"); // Value less than minimum
		System.out.println("aUSACard3 is : \n" + aUSACard3);

		// Use the PlayingCard (super class) .getValue() method since AmercianPlayingCard (subclass) doesn't have one
		System.out.println("The value in aUSACard3 is: " + aUSACard3.getValue());

		System.out.println("\nComparing aUSACard2 and aUSACard3");
		
		if (aUSACard2.equals(aUSACard3)) {     // Look for AmericanPlayingCard .equals first,
			                                   // then Playing Card .equals if none om AmericanPlayCard
			System.out.println("They are equal");
		}
		else {
			System.out.println("They are NOT equal");
		}
	}

}
