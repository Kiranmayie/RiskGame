package com.main;

import java.io.File;

import com.controller.GamePlayController;
import com.functionalities.MapFileLoader;
import com.functionalities.MapFunctionalities;
import com.units.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartGame implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {


			File file = MapFunctionalities.showFileChooser();

			MapFileLoader fileLoaderAndParser = new MapFileLoader();
			Map map = null;
			try {
				map = fileLoaderAndParser.parseAndReadMapFile(file);
			} catch (Exception ex) {
				System.out.print("Error Invalid Map");
				return;
			}
			GamePlayController controller = new GamePlayController(map);

	}

}
