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

public class StartUpPhase implements Observable{
	
	public ArrayList<Observer> observers;
	public PlayersAssignment pa;
	public static String contestantName="";
	public static int contestantArmiesleft=0;
	public boolean changed; 
	public StartUpPhase supLocal;
	 public PhaseView pv;
	 public PlayersDominationView pdv;
	 public static double percentageOccupationEachPlayer=0;
	public static int numberOfArmies =0;
	
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
			 pa.fortificationPhase(contestant.getContestantTrrtrlist(),contestant.getContestantTrrtrlist(), contestant);
		 }
}
		StartUp(contestants, enhancedMap, selectedTerritoryList);
}}
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
			//System.out.println(supLocal.observers);
		//	System.out.println(supLocal.observers.get(0));
		for(Observer obs:supLocal.observers) {
		
			obs.update(obj);
		}}
		
	}
	
	public void notifyObservers() {
		if(changed) {
			pdv.update("World");
			
		}
	}
	
	public void somethingChanged() {
		changed=true;
	}
	

	}