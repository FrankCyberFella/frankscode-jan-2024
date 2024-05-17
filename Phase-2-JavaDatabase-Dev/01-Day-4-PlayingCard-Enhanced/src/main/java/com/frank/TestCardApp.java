package com.frank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import carddeck.CardDeck;
import playingcard.PlayingCard;

public class TestCardApp {

	/*************************************************************************************
	 * member variables accessible to all methods in this class
	 * 
	 * Note: they are marked static so they may be used by the static method main()
	 *                                                      or other class static methods
	 ************************************************************************************/

	private static final int numberDashes = 70;
	
	private static Scanner userInput = new Scanner(System.in);

	/*************************************************************************************
	 * Application program - instantiate object(s) of class 
     *                       and manipulate them to get work done
	 *
     * main() tells us its an application program
     *************************************************************************************/
	
	public static void main(String[] args) {
		
    	displayMessage("\nWelcome to Playing Card Demo App\n");
    	
    	/*************************************************************************************
    	 * Instantiate and display some PlayingCard objects
    	 ************************************************************************************/
    	
    	displayMessage("\nInstantiating a default Playing Card called aCard: new PlayingCard()\n", true);
    	
    	PlayingCard aCard = new PlayingCard();    	
    	System.out.print("aCard: \t\t" + aCard);
    	
    	displayMessage("\nInstantiating a King of Clubs called myCard: new PlayingCard(PlayingCard.CardValue.KING, PlayingCard.CardSuit.CLUB)\n", true);
    	
    	PlayingCard myCard = new PlayingCard(PlayingCard.CardValue.KING, PlayingCard.CardSuit.CLUB);
    	System.out.print("myCard: \t" + myCard);
    	
    	displayMessage("\nInstantiating a Two of Hearts called anotherCard: new PlayingCard(PlayingCard.CardValue.TWO, PlayingCard.CardSuit.HEART)\n", true);
    	
    	PlayingCard anotherCard = new PlayingCard(PlayingCard.CardValue.TWO, PlayingCard.CardSuit.HEART);
    	System.out.print("anotherCard: \t" + anotherCard);
    	
    	displayMessage("\nInstantiating a 2 of Hearts called someCard: new PlayingCard(2, PlayingCard.CardSuit.HEART)\n", true);
    	
    	PlayingCard someCard = new PlayingCard(2, PlayingCard.CardSuit.HEART);
    	System.out.print("someCard: \t" + someCard);
     	  
    	/*************************************************************************************
    	 * Allow program to pause so user can review output far or terminate program
    	 ************************************************************************************/
    	System.out.println("\n\nPress enter to continue...\n");
    	userInput.nextLine();
    	
    	/*************************************************************************************
    	 * Compare objects of the PlayingCard class to each other using == and .equals()
    	 ************************************************************************************/
    	
    	displayMessage("\nComparing anotherCard to someCard using ==\n", true);
    	
    	if (anotherCard == someCard) {
    		System.out.println(" anotherCard IS EQUAL to someCard");
    	}
    	else {
    		System.out.println(" anotherCard is NOT EQUAL to someCard");
    	}
    	 	
    	displayMessage("\nComparing anotherCard to someCard using .equals() method\n");
    	
    	if (anotherCard.equals(someCard) ) {
    		System.out.println(" anotherCard IS EQUAL to someCard");
    	}
    	else {
    		System.out.println(" anotherCard is NOT EQUAL to someCard");
    	}
    	
    	displayMessage("\nComparing anotherCard to aCard using .equals() method\n");
    	
    	if (anotherCard.equals(aCard) ) {
    		System.out.println(" anotherCard IS EQUAL to aCard");
    	}
    	else {
    		System.out.println(" anotherCard is NOT EQUAL to aCard");
    	}
  
    	/*************************************************************************************
    	 * Allow program to pause so user can review output far or terminate program
    	 ************************************************************************************/
    	System.out.println("\n\nPress enter to continue...\n");
    	userInput.nextLine();
    	
	
    	/*************************************************************************************
    	 * Demonstrate use of CardDeck class
    	 ************************************************************************************/
    	
		CardDeck myDeck = new CardDeck();

		displayMessage("\n-- Showing Cards in Deck\n", true);
		
		myDeck.showDeck();

		displayMessage("\n-- Removing Jokers from Deck\n");

		System.out.println("Number Jokers Removed: " + myDeck.removeJokers());
		System.out.println(" Number Cards in Deck: " + myDeck.numCardsLeft());
		
		displayMessage("\n-- Shuffling Deck\n");

		myDeck.shuffleDeck();

		displayMessage("\n-- Dealing Cards from Deck  After Shuffle\n");

		while (myDeck.anyCardsInDeck()) {
			System.out.println(myDeck.dealCard().toString());
		}
		
		displayMessage("\nResetting myDeck with no Jokers\n");
		
		myDeck.resetDeck(false); 
		
		displayMessage("\n-- Showing Cards in Deck after reset without Jokers\n");

		myDeck.showDeck();
		
		  	/*************************************************************************************
    	 * Allow program to pause so user can review output far or terminate program
    	 ************************************************************************************/
    	
    	System.out.println("\n\nPress enter to continue...\n");
    	userInput.nextLine();
    	
		
		/*************************************************************************************
    	 * Demonstrate use of PlayingCard andCardDeck  class to deal a hand of 5 cards
    	 ************************************************************************************/
    	
    	displayMessage("\n-- Shuffling Deck\n");

		myDeck.shuffleDeck();
		
		displayMessage("\nDealing a card hand of 5 cards\n");
		
		List<PlayingCard> cardHand = new ArrayList();
		
		for(int i=0; i < 5; i++) {
			cardHand.add(myDeck.dealCard());
		}
		
		System.out.println("\nCards in hand:\n");
		
		for(PlayingCard anElement : cardHand) {
			System.out.println(anElement);
		}
		
		
		displayMessage("\nThanks for using the PlayingCard demo app!\n");
	
	}  // End of main()
	
	/***********************************************************************************
	 * Helper methods for main()
	 * 
	 *  Note: they are marked static because they are called a static method
	 ************************************************************************************/
	
	static void displayMessage(String message) {
		System.out.println("-".repeat(numberDashes) 
		         + message 
		         + "-".repeat(numberDashes));
		
	}
	
	static void displayMessage(String message, boolean blankLineFirst) {
		
		if (blankLineFirst) {     // if blank line wanted before message
			System.out.println(); // display blank line
		}
		displayMessage(message);  // then display message

		
	}
	
} // End of TestCardApp class



