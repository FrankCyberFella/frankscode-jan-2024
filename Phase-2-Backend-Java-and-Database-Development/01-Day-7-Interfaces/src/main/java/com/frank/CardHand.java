package com.frank;

import java.util.ArrayList;


public interface CardHand {
	
	public ArrayList<PlayingCard> getHand();

	public void addCard(PlayingCard aCard);
	
	public void emptyHand();

	public void show();

}
