package com.main;
import java.io.File;
import java.io.FileNotFoundException;
import com.controller.StartGameController;
import com.units.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartGame implements EventHandler<ActionEvent>  {

	@Override
	public void handle(ActionEvent event) {


			File file = MapSStep.mapFileValidator();

			MapSStep read = new MapSStep();
			Map enhancedMap = null;
				try {
					enhancedMap = read.readingMapFile(file);
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			
			
			StartGameController controller = new StartGameController(enhancedMap);

	}

}