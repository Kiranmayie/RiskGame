package com.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.controller.StartGameController;
import com.model.PlayersAssignment;

import com.units.Contestant;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class StartGame.
 */
public class StartGame implements EventHandler<ActionEvent>  {

	/** The enhanced map. */
	public static Map enhancedMap;
	
	/** The cntnts. */
	private Continents cntnts;
	
	/** The pa. */
	private PlayersAssignment pa;
	
	/** The current contestant. */
	private Contestant currentContestant;
	
	/** The trrtry 1. */
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
			System.out.println(selectedTerritoryList);
			
			 pa.loadBatallion(selectedTerritoryList);

	}

}