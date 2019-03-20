package com.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.controller.StartGameController;
import com.model.PlayersAssignment;

import com.units.Contestant;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * The Class StartGame implements EventHandler, Inititates the Game Play.
 */
public class StartGame implements EventHandler<ActionEvent>  {
	/** The enhanced map. */
	public static Map enhancedMap;
	/** The Continents. */
	private Continents cntnts;
	/** The Player Assignment. */
	private PlayersAssignment pa;
	/** The current contestant. */
	private Contestant currentContestant;
	
	/** The Territories 1. */
	private Territories trrtry1;
	
	/** The contestants. */
	private List<Contestant> contestants;
	
	/** The selected territory list. */
	List<Territories> selectedTerritoryList;

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent event) {
		 List<Contestant> contestants = new ArrayList<>();
		 PlayersAssignment pa=new PlayersAssignment();
			File file = MapSStep.mapFileValidator();
			MapSStep read = new MapSStep();
			Map enhancedMap = null;
				try {
					enhancedMap = read.readingMapFile(file);
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			StartGameController controller = new StartGameController(enhancedMap);
			contestants = pa.createContestant(StartGameController.numberContestants,contestants);
			//Iterator<Contestants> contestantLoopser
			PlayersAssignment.playersArmyAssign(contestants);
			pa.territoryAssignToContestant(enhancedMap,contestants); 
			selectedTerritoryList=pa.executingCurrentContestant();	
			//System.out.println(selectedTerritoryList);
			 pa.loadBatallion(selectedTerritoryList);
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
			 
	}

}}