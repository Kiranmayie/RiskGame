package com.model;

import java.util.List;
import java.util.Scanner;

import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

public class StartUpPhase {
	
	private PlayersAssignment pa;
	private Contestant currentContestant;
	 
	public StartUpPhase (PlayersAssignment pa) {
		
		this.pa=pa;
	}
	
	 
	
	 public void StartUp(List<Contestant> contestants, Map enhancedMap, List<Territories> selectedTerritoryList) {
		// PlayersAssignment pa=new PlayersAssignment();
		if(contestants.size()>1) {
		for(Contestant contestant:contestants) {
		 //System.out.println(contestant.getcontestantTrrtrlist().size());
		contestant= pa.getReinforceBatallion(enhancedMap,contestant);
		System.out.println("Reinforcement has begun as per the rules:- "+contestant.getContestantName() + " "+contestant.getBatallion() + " armies left.");
		 
		 
		 System.out.println("Player "+contestant.getContestantName()+" :- Please select the following optons:- \n" +"Option 1: Getting and Placing New Armies \n"+"Option 2: Attacking \n"+ "Option 3: Fortifying");
		 Scanner sc=new Scanner(System.in);
		 int selector=sc.nextInt();
		 if(selector == 1) {
			 
			 //pa.territoryAssignToContestant(enhancedMap,contestants); 
			 
				selectedTerritoryList=pa.executingCurrentContestant();	
				//System.out.println(selectedTerritoryList);
				 pa.loadBatallion(selectedTerritoryList);
	 }
		 else if( selector == 2)  {
			 
			 pa.attackPhase(contestant.getContestantTrrtrlist(),contestant.getContestantTrrtrlist(), contestant);
		 }
}
		StartUp(contestants, enhancedMap, selectedTerritoryList);
}
	 }
}
	