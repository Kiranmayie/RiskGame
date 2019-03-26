package com.main;

import com.model.StartUpPhase;

import Patterns.Observable;
import Patterns.Observer;

/**
 * @author Sathwik
 * @author Sidharth Sharma
 * @author Kiran Mayie
 * @author Amandeep Kaur khosa
 * @author Pritpal Kaur
 *
 */
public class PhaseView implements Observer {

	 public Observable obs;

	 public PhaseView(Observable obs){
		 Observer obs1=new PhaseView();
		 System.out.println(obs1);
		 this.obs=obs;
		 obs.registerObserver(obs1);
	 }
	
	 public PhaseView() {}
	 
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
