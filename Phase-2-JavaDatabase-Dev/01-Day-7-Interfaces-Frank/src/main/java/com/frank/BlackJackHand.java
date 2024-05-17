package com.frank;

import java.util.ArrayList;

/***********************************************************************************
 * A BlackJack hand is a type of CardHand
 * 
 * It implements the CardHand interface
 * 
 * BlackHandHand must implement all the methods required in the CardHand interface
 **********************************************************************************/
// implements indicate a class is a type of another class - using interface
public class BlackJackHand implements CardHand {

	private int numCardsInHand = 2;
	
	private ArrayList<PlayingCard> aHand;
	
	public BlackJackHand() {
		aHand = new ArrayList<PlayingCard>(numCardsInHand);
	}
	
	/***********************************************************************************
	 * Methods required in the CardHand interface
	 **********************************************************************************/
	@Override // ask the compiler to be sure we have define the required correctly
	          //     does this method signature match the one required by the interface
	public ArrayList<PlayingCard> getHand() {
		return aHand;
	}

	@Override
	public void addCard(PlayingCard aCard) {
		aHand.add(aCard);
		numCardsInHand++;
	}

	@Override
	public void emptyHand() {
		aHand.clear();

	}

	@Override
	public void show() {
		for (PlayingCard aCard : aHand)
		{
			System.out.println(aCard);
		}
			return;
	}
	/***********************************************************************************
	 * Helper Methods for BlackJackHand
	 **********************************************************************************/
	public void dealHand(CardDeck aDeck) {
		for (int i=0; i < numCardsInHand; i++ ) {
			aHand.add(aDeck.dealCard());
		}	
	}
	
}
	
