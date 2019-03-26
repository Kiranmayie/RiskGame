package com.main;

import com.model.StartUpPhase;

import Patterns.Observable;
import Patterns.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayersDominationView.
 */
public class PlayersDominationView implements Observer{

	/** The obs. */
	public Observable obs;
	
	 /**
 	 * Instantiates a new players domination view.
 	 *
 	 * @param obs the obs
 	 */
 	public PlayersDominationView(Observable obs){
		 Observer obs1=new PlayersDominationView();
		 //System.out.println(obs1);
		 this.obs=obs;
		 obs.registerObserver(obs1);
	 }
	
	 /**
 	 * Instantiates a new players domination view.
 	 */
 	public PlayersDominationView() {}
	 
	/* (non-Javadoc)
	 * @see Patterns.Observer#update(java.lang.Object)
	 */
	@Override
	public void update(Object arg1) {
		
String activePhase= (String) arg1;
		
		if(activePhase.equals("World")) {
			
			System.out.println(StartUpPhase.percentageOccupationEachPlayer+" is the percentage control of player "+StartUpPhase.contestantName);
			System.out.println(" and is left with "+StartUpPhase.numberOfArmies);
		}
		
	}

}
