package com.main;

import com.model.PlayersAssignment;

import Patterns.Observable;
import Patterns.Observer;

public class CardExchangeView implements Observer {

	public Observable obs;
	
	 public CardExchangeView(Observable obs){
		 Observer obs1=new CardExchangeView();
		 System.out.println(obs1);
		 this.obs=obs;
		 obs.registerObserver(obs1);
	 }
	 
	 public CardExchangeView() {}
	 
	@Override
	public void update(Object arg1) {
		 System.out.println("Card Exchange View The player has been assigned "+PlayersAssignment.CardCountAttacker+"of type " +PlayersAssignment.cardtypeAttacker);
		
		
	}

}
