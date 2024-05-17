package com.frank;

import java.util.ArrayList;

/*************************************************************************
 * An interface defines methods that must be implemented 
 *       to be an instance of the interface
 * 
 * If you want to be a CardHand you must implement these behaviors
 * 
 * An interface represents the "type-of" relationship      
 ************************************************************************/

public interface CardHand {
	
	// return the PlayingCards in the hand as an ArrayList
	public ArrayList<PlayingCard> getHand();  
	
	// be able to add a PlayingCard to the CardHand
	public void addCard(PlayingCard aCard);
	
	// be able to remove all PlayingCards from the CardHand
	public void emptyHand();

	// be able to display all the playingCards in a CardHand
	public void show();

}
