package carddeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import playingcard.PlayingCard;
import playingcard.PlayingCard.CardSuit;
import playingcard.PlayingCard.CardValue;
/***************************************************************************************************
 * Class to Simulate a Standard Deck of Playing Cards
 ***************************************************************************************************/

public class CardDeck {
	/*************************************************************************************************
	 * CardDeck has a set of PlayingCards - "has-a" relationship - define the object in the class
	 ***************************************************************************************************/

	// Define a list to hold PlayingCards
	List<PlayingCard> deckOfCards = new ArrayList<PlayingCard>();  // Frank's style 
//	List<PlayingCard> deckOfCards = new ArrayList<>();       // Works too! 
//	List<PlayingCard> deckOfCards = new ArrayList(); 	     // Works too! - common style
//	ArrayList<PlayingCard> deckOfCards = new ArrayList<>();  // Works too! - less flexibility - no Polymorphism	

	/***************************************************************************************************
	 * Number of non-Joker cards per suit
	 ***************************************************************************************************/
	private static final int numberCardsForSuit = 13;

	/***************************************************************************************************
	 * Default Constructor
	 ***************************************************************************************************/
	
	public CardDeck() {
		resetDeck(true); // create deck with jokers - initialize using a helper method
	}

	/***************************************************************************************************
	 * Return number of cards in Deck
	 ***************************************************************************************************/
	
	public int numCardsLeft() {
		return deckOfCards.size();
	}
	/***************************************************************************************************
	 * Display cards in deck
	 ***************************************************************************************************/
	
	public void showDeck() {
		System.out.println("Number of Cards in Deck: " + deckOfCards.size());
	/***************************************************************************************************
	 * Note use of for-each loop instead of iterative for loop	
	 * 
	 * for-each may be used to traverse an array or Collections class linear object from start to end
	 * 
	 * syntax: for(datatype name-for-element : array-Collections-class-object)
	 * 
	 * The for-each may be used to traverse an array or Collections class linear object from start to end
	 *     assigning each element to the variable specfied to the left of the colon
	 *     
	 * Use the element variable in the body of teh loop to reference the current element 
	 * 
	 * Advantage over iterative for-loop is easier to code
	 * 
	 * Disadvantages over iterative for-loop:  1. Must always go from start to end
	 *                                         2. Cannot start in the middle or stop before end
	 *                                         3. You don't know which element number is currently processing
	 * 
	 ***************************************************************************************************/
		// go through deckOfCards on element at a time, assigning the current element to aCard
		for (PlayingCard aCard : deckOfCards) {
			aCard.showCardWithHash();  // use the current element stored in a aCard to run showCardWithHash() 
		}
		/* to do same thing with iterative for loop
		for (int i=0; i < deckOfCards.size(); i++) {
			deckOfCards.get(i).showCardWithHash();  // use the current element stored in a aCard to run showCardWithHash() 
		}
		*/
	}
	/***************************************************************************************************
	 * Answer question are they any cards in the deck
	 ***************************************************************************************************/
	
	public boolean anyCardsInDeck() {
		if (deckOfCards.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/***************************************************************************************************
	 * Deal a card from the top of the deck
	 ***************************************************************************************************/
	public PlayingCard dealCard() {
		if (deckOfCards.size() > 0) {
			return deckOfCards.remove(0);  // retrieve the first element and remove it
		}
		else {
			 return null;
		}
	}
	/***************************************************************************************************
	 * Create a deck with or without Jokers
	 ***************************************************************************************************/
	public void resetDeck(boolean withJoker) {
		deckOfCards.clear();              // Remove any existing cards from the deck

		for (int i = 1; i <= numberCardsForSuit; i++) {    // Generate a set of clubs
			deckOfCards.add(new PlayingCard(i, PlayingCard.CardSuit.CLUB));
		}
		for (int i = 1; i <= numberCardsForSuit; i++) {    // Generate a set of hearts
			deckOfCards.add(new PlayingCard(i, PlayingCard.CardSuit.HEART));
		}
		for (int i = 1; i <= numberCardsForSuit; i++) {    // Generate a set of spades
			deckOfCards.add(new PlayingCard(i, PlayingCard.CardSuit.SPADE));
		}
		for (int i = 1; i <= numberCardsForSuit; i++) {     // Generate a set of diamonds
			deckOfCards.add(new PlayingCard(i, PlayingCard.CardSuit.DIAMOND));
		}

		if (withJoker) {  // If Jokers requested, add them
			deckOfCards.add(new PlayingCard(CardValue.JOKER, CardSuit.JOKER));
			deckOfCards.add(new PlayingCard(CardValue.JOKER, CardSuit.JOKER));
		}
	}
	/***************************************************************************************************
	 * Create New Deck with Shuffled Cards
	 ***************************************************************************************************/
	public void shuffleDeck() {
		resetDeck(false);                  // Reload deck without Jokers
		Collections.shuffle(deckOfCards);  // Use Collections class shuffle() to randomize cards in deck
	}
	
	/***************************************************************************************************
	 * Remove any Jokers from a Deck
	 ***************************************************************************************************/
	public int removeJokers() {
		ArrayList<PlayingCard> aJoker = new ArrayList<PlayingCard>();  // Needed to hold a Joker
		aJoker.add(new PlayingCard(CardValue.JOKER, CardSuit.JOKER));  // Add a Joker to ArrayList
		int numCardsBefore = deckOfCards.size();                       // Remember # cards before removal
		// .removeAll() - remove elements in the first Arraylist that match elements in the second
		deckOfCards.removeAll(aJoker);                                 // Remove any Jokers from deck
		return numCardsBefore - deckOfCards.size();                    // Return # of Jokers removed
	}
}