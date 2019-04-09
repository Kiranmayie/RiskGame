package com.main;

import com.model.PlayersAssignment;

import Patterns.Observable;
import Patterns.Observer;


// TODO: Auto-generated Javadoc
/**
 * The Class CardExchangeView.
 */
public class CardExchangeView implements Observer {

	/** The obs. */
	public Observable obs;
	
	 /**
 	 * Instantiates a new card exchange view.
 	 *
 	 * @param obs the obs
 	 */
 	public CardExchangeView(Observable obs){
		 Observer obs1=new CardExchangeView();
		 System.out.println(obs1);
		 this.obs=obs;
		 obs.registerObserver(obs1);
	 }
	 
	 /**
 	 * Instantiates a new card exchange view.
 	 */
 	public CardExchangeView() {}
	 
	/* (non-Javadoc)
	 * @see Patterns.Observer#update(java.lang.Object)
	 */
	@Override
	public void update(Object arg1) {
		 System.out.println("The player has been assigned"+PlayersAssignment.CardCount+"of type" +PlayersAssignment.cardtype);
		
	}

}
