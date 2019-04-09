
package com.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.controller.StartGameController;
import com.model.PlayersAssignment;
import com.model.StartUpPhase;
import com.units.Contestant;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

import ConcretePatterns.Aggressive;
import ConcretePatterns.Cheater;
import ConcretePatterns.Human;
import Patterns.ContestantStrategies;
import Patterns.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
// TODO: Auto-generated Javadoc
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

	
	/** The sup. */
	private StartUpPhase sup;


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
				//selectedTerritoryList=pa.executingCurrentContestant();	
				//System.out.println(selectedTerritoryList);
				//contestantsList.addAll(contestants);

				for(int i=0;i<contestants.size();i++) {
						
					System.out.println(contestants.get(i).getContestantName() + "!....started playing.\n");
					System.out.println(contestants.get(i).getContestantName() + contestants.get(i).getBatallion() + " Batallion left.\n");
					if(contestants.get(i).getContestantStrategy().equals("Human") && contestants.get(i).getBatallion()>0) {
						
						ContestantStrategies human=new Human();
						System.out.println("Human");
						human.loadBatallion(contestants.get(i).getcontestantTrrtrlist(),contestants.get(i),contestants);
						human.attackPhase(contestants.get(i).getcontestantTrrtrlist(), contestants.get(i).getcontestantTrrtrlist(), contestants.get(i),enhancedMap);
						
					}
					
					else if(contestants.get(i).getContestantStrategy().equalsIgnoreCase("Aggressive") ) {
						
						ContestantStrategies aggressive=new Aggressive();
						if(contestants.get(i).getBatallion()>0) {
						aggressive.loadBatallion(contestants.get(i).getcontestantTrrtrlist(),contestants.get(i),contestants);
						}
						else if(contestants.get(i).getBatallion()>=0) {
							System.out.println(contestants.get(i).getContestantName());
							//System.out.println(contestants.get(i).getcontestantTrrtrlist());
							System.out.println(enhancedMap.getContinents());
						aggressive.attackPhase(contestants.get(i).getcontestantTrrtrlist(),contestants.get(i).getContestantTrrtrlist(), contestants.get(i),enhancedMap);
						System.out.println("Aggresive");
						
						}
					}
					else if(contestants.get(i).getContestantStrategy().equals("Cheater") && contestants.get(i).getBatallion()>0) {
						
						ContestantStrategies cheater=(ContestantStrategies) new Cheater();
						cheater.loadBatallion(contestants.get(i).getcontestantTrrtrlist(),contestants.get(i),contestants);
						System.out.println("Cheater");
					}
					
					
					if(i == contestants.size()-1) {
						for(Contestant currentContestant:contestants) {
							System.out.println(i);
							System.out.println(currentContestant.getBatallion());
						if(currentContestant.getContestantTrrtrlist().size() != MapSStep.count) {
							i=-1;
							break;
					}
						}
					}
				}
				
	}
}

