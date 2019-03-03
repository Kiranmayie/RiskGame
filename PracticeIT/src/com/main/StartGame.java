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

public class StartGame implements EventHandler<ActionEvent>  {

	public static Map enhancedMap;
	private Continents cntnts;
	private PlayersAssignment pa;
	private Contestant currentContestant;
	private Territories trrtry1;
	private List<Contestant> contestants;
	List<Territories> selectedTerritoryList;

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
			 pa.loadBatallion(selectedTerritoryList);

	}

}