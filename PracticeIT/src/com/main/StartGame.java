package com.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.controller.StartGameController;
import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartGame implements EventHandler<ActionEvent>  {

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
			PlayersAssignment.playersArmyAssign(contestants);
			

	}

}