package com.main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.IOException;

import com.controller.MapBuildController;
import com.units.Map;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The Class newMapCreation implements EventHandler in Creating New Map
 */
public class newMapCreation implements EventHandler<ActionEvent> {
	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	public void handle(ActionEvent event) {
			final Stage newMapStage = new Stage();
			newMapStage.setTitle("New Map");
			MapBuildController controller = new MapBuildController();
			FXMLLoader loadMap = new FXMLLoader(getClass().getClassLoader().getResource("MapEditorNew.fxml"));
			loadMap.setController(controller);
			Parent root = null;
			try {
				root = (Parent) loadMap.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scene scene = new Scene(root);
			newMapStage.setScene(scene);
			newMapStage.show();
		}
	}