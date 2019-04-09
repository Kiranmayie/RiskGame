package com.main;

import com.model.StartUpPhase;

import Patterns.Observable;
import Patterns.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class PhaseView.
 */
public class PhaseView implements Observer {

	 /** The obs. */
 	public Observable obs;

	 /**
 	 * Instantiates a new phase view.
 	 *
 	 * @param obs the obs
 	 */
 	public PhaseView(Observable obs){
		 Observer obs1=new PhaseView();
		 System.out.println(obs1);
		 this.obs=obs;
		 obs.registerObserver(obs1);
	 }
	
	 /**
 	 * Instantiates a new phase view.
 	 */
 	public PhaseView() {}
	 
	/* (non-Javadoc)
	 * @see Patterns.Observer#update(java.lang.Object)
	 */
	public void update(Object arg1) {
	
		String activePhase= (String) arg1;
		
		if(activePhase.equals("Reinforcement")) {
			
			
		System.out.println(activePhase+" phase is active for player :- "+ StartUpPhase.contestantName+" and is left with "+StartUpPhase.contestantArmiesleft+" armies.");
		
		}
		
		
		if(activePhase=="Attack") {
			
			System.out.println(activePhase+" phase is active for player :- "+ StartUpPhase.contestantName+" and is currently having these "+StartUpPhase.contestantArmiesleft+" armies.");
			
			}
	}

	
}
