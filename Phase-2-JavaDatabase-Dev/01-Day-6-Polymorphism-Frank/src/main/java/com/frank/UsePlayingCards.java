package com.frank;

import java.util.ArrayList;
import java.util.Scanner;

public class UsePlayingCards {
	
    // Must be static since it is used in a static method	
	static Scanner theKeyboard = new Scanner(System.in);
	
	
// This is our application program which will instantiate object and use thier methods to manipulate them
// We know this is the application program because it has the main() method
	public static void main(String[] args) {

		AmericanPlayingCard aUSACard = new AmericanPlayingCard(1, "HEARTS");
		System.out.println("aUSACard is :");
		aUSACard.showCard();  // runs the AmericanPlayingCard showCard()

		AmericanPlayingCard aUSACard2 = new AmericanPlayingCard(14, "SPADES");
		System.out.println("aUSACard2 is :");
		aUSACard2.showCard();

		AmericanPlayingCard aUSACard3 = new AmericanPlayingCard(-1, "SPADES");
		System.out.println("aUSACard3 is :");
		aUSACard3.showCard();

		AmericanPlayingCard aUSACard4 = new AmericanPlayingCard(11, "John");
		System.out.println("aUSACard4 is :");
		aUSACard4.showCard();

		ItalianPlayingCard anItalianCard1 = new ItalianPlayingCard(13, "SWORDS");
		System.out.println("anItalianCard1 is :");
		anItalianCard1.showCard();  // Runs the ItalianPlayingCard showCard()

		ItalianPlayingCard anItalianCard2 = new ItalianPlayingCard(9, "COINS");
		System.out.println("anItalianCard2 is :");
		anItalianCard2.showCard();

		ItalianPlayingCard anItalianCard3 = new ItalianPlayingCard(11, "Daniel");
		System.out.println("anItalianCard3 is :");
		anItalianCard3.showCard();

		SwissPlayingCard aSwissCard1 = new SwissPlayingCard(13, "ROSES");
		System.out.println("aSwissCard1 is :");
		aSwissCard1.showCard();  // Runs the SwissPlayingCard showCard()

		SwissPlayingCard aSwissCard2 = new SwissPlayingCard(9, "SHIELDS");
		System.out.println("aSwissCard2 is :");
		aSwissCard2.showCard();

		SwissPlayingCard aSwissCard3 = new SwissPlayingCard(10, "ACORNS");
		System.out.println("anSwissCard3 is :");
		aSwissCard3.showCard();

		SwissPlayingCard aSwissCard4 = new SwissPlayingCard(11, "John");
		System.out.println("anSwissCard4 is :");
		aSwissCard4.showCard();
		
		System.out.println("\nPress enter to continue..."); theKeyboard.nextLine();
		
		System.out.println("\n--------- Polymorphism examples start here ---------------------------");

		PlayingCard aCard; // Define an object of the super class

		aCard = aUSACard;  // Assign a sub class object to the super class object
		aCard.showCard();  // Use the super class to invoke a method - run the showCard() method of the object IN aCard

		aCard = anItalianCard1;  // Assign a sub class object to the super class object
		aCard.showCard();        // Use the super class to invoke a method - run the showCard() method of the object IN aCard

		aCard = aSwissCard1;  // Assign a sub class object to the super class object
		aCard.showCard();     // Use the super class to invoke a method - run the showCard() method of the object IN aCard

//List<PlayingCard> myCards = new ArrayList();  // Defined an ArrayList of super class objects
ArrayList<PlayingCard> myCards = new ArrayList();  // Defined an ArrayList of super class objects

		myCards.add(anItalianCard1);  // add sub class object to my ArrayList of super class objects
		myCards.add(aUSACard);        // add sub class object to my ArrayList of super class objects
		myCards.add(aSwissCard1);     // add sub class object to my ArrayList of super class objects
		myCards.add(aUSACard2);       // add sub class object to my ArrayList of super class objects

		System.out.println("\n ---- Here come the elements from ArrayList ------\n");

// Display all the objects in my ArrayList - Polymorphism will cause the correct showCard() for the element to be run
        for(PlayingCard theCard : myCards) { // Define a super class object as element in the for each
        	theCard.showCard();              // Use the element in the for each to invoke the methods
		}

		System.out.println("\n ---- Using a function to display a card ------\n");
        displayCard(aSwissCard3);      // call the function to display our card
        displayCard(anItalianCard2);   // call the function to display our card

	}  // End of main()
	
	// This must be static because what calls it is static
	static void displayCard(PlayingCard cardyMcCardCard) {  // a function receives a super class object
		cardyMcCardCard.showCard();  // use the super class object to run a method - Polymorphish will run the correct method
	}
	
}  // End of class that holds main()
