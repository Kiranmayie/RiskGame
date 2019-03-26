package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.main.MapSStep;
import com.main.PhaseView;
import com.main.PlayersDominationView;
import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

import Patterns.Observable;
import Patterns.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class StartUpPhase.
 */
public class StartUpPhase implements Observable{
	
	/** The observers. */
	public ArrayList<Observer> observers;
	
	/** The pa. */
	public PlayersAssignment pa;
	
	/** The contestant name. */
	public static String contestantName="";
	
	/** The contestant armiesleft. */
	public static int contestantArmiesleft=0;
	
	/** The changed. */
	public boolean changed; 
	
	/** The sup local. */
	public StartUpPhase supLocal;
	 
 	/** The pv. */
 	public PhaseView pv;
	 
 	/** The pdv. */
 	public PlayersDominationView pdv;
	 
 	/** The percentage occupation each player. */
 	public static double percentageOccupationEachPlayer=0;
	
	/** The number of armies. */
	public static int numberOfArmies =0;
	
	/**
	 * Instantiates a new start up phase.
	 */
	public StartUpPhase() {
		 
		observers = new ArrayList<Observer>();
	 }
	 
    /**
     * Instantiates a new start up phase.
     *
     * @param pa the pa
     */
    public StartUpPhase(PlayersAssignment pa) {
		 
		 this.pa = pa;
	 }
	 
	 /**
 	 * Start up.
 	 *
 	 * @param contestants the contestants
 	 * @param enhancedMap the enhanced map
 	 * @param selectedTerritoryList the selected territory list
 	 */
 	public void StartUp(List<Contestant> contestants, Map enhancedMap, List<Territories> selectedTerritoryList) {
		 //PlayersAssignment pa=new PlayersAssignment();
		if(contestants.size()>1) {
			supLocal = new StartUpPhase();
			pv=new PhaseView(supLocal);
			pdv=new PlayersDominationView(supLocal);
			Scanner scOne= new Scanner(System.in);
			//worldDominationSelection(contestants);Scanner scOne=new Scanner(System.in);
			 System.out.println("Choose among following and type:- \n"+" World - to view World Domination \n"+"Forward - To move ahead");
			 String input=scOne.next();
				//System.out.println(supLocal.observers.size());
				if(input.equals("World")) {
					for(Contestant contestant:contestants) {
						
						contestantName=contestant.getContestantName();
						contestantArmiesleft=contestant.getBatallion();
						double num=contestant.getcontestantTrrtrlist().size();
						double dem=MapSStep.count;
						System.out.println(num+"  "+dem);
						percentageOccupationEachPlayer = (num / dem) * 100;
						numberOfArmies = contestant.getBatallion();
						somethingChanged();
						notifyObservers();
					}}

					
				
				
				else {
		for(Contestant contestant:contestants) {

		contestant= pa.getReinforceBatallion(enhancedMap,contestant);
		contestantName=contestant.getContestantName();
		contestantArmiesleft=contestant.getBatallion();
		System.out.println("Reinforcement has begun as per the rules:- "+contestant.getContestantName() + " "+contestant.getBatallion() + " armies left.");
		 
		 
		 System.out.println("Player "+contestant.getContestantName()+" :- Please select the following optons:- \n" +"Option 1: Getting and Placing New Armies \n"+"Option 2: Attacking \n"+ "Option 3: Fortifying");
		 Scanner sc=new Scanner(System.in);
		 int selector=sc.nextInt();

		 
		 if(selector == 1) {

			 somethingChanged();
			// System.out.println(supLocal.observers.get(0));
			 notifyObservers("Reinforcement");
		
			 //pa.territoryAssignToContestant(enhancedMap,contestants); 
				selectedTerritoryList=pa.executingCurrentContestant();	
				//System.out.println(selectedTerritoryList);
				 pa.loadBatallion(selectedTerritoryList);
	 }
		 else if( selector == 2)  {
			 
			 somethingChanged();
			 notifyObservers("Attack");
			 pa.attackPhase(contestant.getContestantTrrtrlist(),contestant.getContestantTrrtrlist(), contestant);
		 }
		 
        else if( selector == 3)  {
			 
			 somethingChanged();
			 notifyObservers("Fortification");
			 pa.attackPhase(contestant.getContestantTrrtrlist(),contestant.getContestantTrrtrlist(), contestant);
		 }
}
		StartUp(contestants, enhancedMap, selectedTerritoryList);
}}
	 }
	 
	/* (non-Javadoc)
	 * @see Patterns.Observable#registerObserver(Patterns.Observer)
	 */
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		System.out.println(observers.get(0));
		System.out.println(observers.size());
	}
	
	/* (non-Javadoc)
	 * @see Patterns.Observable#removeObserver(Patterns.Observer)
	 */
	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if(i>=0) {
		observers.remove(i);
		}
	}
	
	/* (non-Javadoc)
	 * @see Patterns.Observable#notifyObservers(java.lang.String)
	 */
	@Override
	public void notifyObservers(String obj) {
		if(changed) {
			//System.out.println(supLocal.observers);
		//	System.out.println(supLocal.observers.get(0));
		for(Observer obs:supLocal.observers) {
		
			obs.update(obj);
		}}
		
	}
	
	/**
	 * Notify observers.
	 */
	public void notifyObservers() {
		if(changed) {
			pdv.update("World");
			
		}
	}
	
	/**
	 * Something changed.
	 */
	public void somethingChanged() {
		changed=true;
	}
	
	/*public void worldDominationSelection(List<Contestant> contestants){
	Scanner scOne=new Scanner(System.in);
	 System.out.println("Choose among following and type:- \n"+" World - to view World Domination \n"+"Forward - To move ahead");
	 String input=scOne.next();
		//System.out.println(supLocal.observers.size());
		if(input.equals("World")) {
			for(Contestant contestant:contestants) {
				
				contestantName=contestant.getContestantName();
				contestantArmiesleft=contestant.getBatallion();
				double num=contestant.getcontestantTrrtrlist().size();
				double dem=MapSStep.count;
				System.out.println(num+"  "+dem);
				percentageOccupationEachPlayer = (num / dem) * 100;
				numberOfArmies = contestant.getBatallion();
				somethingChanged();
				notifyObservers();
			}}

}*/
	}