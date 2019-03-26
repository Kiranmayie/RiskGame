package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.main.PhaseView;
import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

import Patterns.Observable;
import Patterns.Observer;

public class StartUpPhase implements Observable{
	
	public ArrayList<Observer> observers;
	public PlayersAssignment pa;
	public static String contestantName="";
	public static int contestantArmiesleft=0;
	public boolean changed; 
	public StartUpPhase supLocal;
	 public PhaseView pv;
	
	public StartUpPhase() {
		 
		observers = new ArrayList<Observer>();
	 }
	 
    public StartUpPhase(PlayersAssignment pa) {
		 
		 this.pa = pa;
	 }
	 
	 public void StartUp(List<Contestant> contestants, Map enhancedMap, List<Territories> selectedTerritoryList) {
		 //PlayersAssignment pa=new PlayersAssignment();
		if(contestants.size()>1) {
			supLocal = new StartUpPhase();
			pv=new PhaseView(supLocal);
			
			 System.out.println(supLocal.observers.get(0));
				System.out.println(supLocal.observers.size());
				
		for(Contestant contestant:contestants) {
		 //System.out.println(contestant.getcontestantTrrtrlist().size());
		contestant= pa.getReinforceBatallion(enhancedMap,contestant);
		contestantName=contestant.getContestantName();
		contestantArmiesleft=contestant.getBatallion();
		System.out.println("Reinforcement has begun as per the rules:- "+contestant.getContestantName() + " "+contestant.getBatallion() + " armies left.");
		 
		 
		 System.out.println("Player "+contestant.getContestantName()+" :- Please select the following optons:- \n" +"Option 1: Getting and Placing New Armies \n"+"Option 2: Attacking \n"+ "Option 3: Fortifying");
		 Scanner sc=new Scanner(System.in);
		 int selector=sc.nextInt();

		 
		 if(selector == 1) {

			 somethingChanged();
			 System.out.println(supLocal.observers.get(0));
			 notifyObservers("Reinforcement");
		
			 //pa.territoryAssignToContestant(enhancedMap,contestants); 
				selectedTerritoryList=pa.executingCurrentContestant();	
				//System.out.println(selectedTerritoryList);
				 pa.loadBatallion(selectedTerritoryList);
	 }
		 else if( selector == 2)  {
			 
			 somethingChanged();
			 notifyObservers("ATTACK");
			 pa.attackPhase(contestant.getContestantTrrtrlist(),contestant.getContestantTrrtrlist(), contestant);
		 }
}
		StartUp(contestants, enhancedMap, selectedTerritoryList);
}
	 }
	 
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		System.out.println(observers.get(0));
		System.out.println(observers.size());
	}
	
	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if(i>=0) {
		observers.remove(i);
		}
	}
	
	@Override
	public void notifyObservers(String obj) {
		if(changed) {
			System.out.println(supLocal.observers);
			System.out.println(supLocal.observers.get(0));
		for(Observer obs:supLocal.observers) {
		
			obs.update(obj);
		}}
		
	}
	
	public void somethingChanged() {
		changed=true;
	}
	

}
	