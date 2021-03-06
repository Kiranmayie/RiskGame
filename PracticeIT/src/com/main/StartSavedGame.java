package com.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.controller.StartGameController;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class StartSavedGame.
 */
public class StartSavedGame implements EventHandler<ActionEvent> {

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub

		StartGameController controller = fileChooser();

		final Stage mapSelectorStage = new Stage();
		mapSelectorStage.setTitle("Game Screen");

		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapSelectorLayout.fxml"));
		loader.setController(controller);
		Parent root = null;
		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		mapSelectorStage.setScene(scene);
		mapSelectorStage.show();
	}
	
	/**
	 * File chooser.
	 *
	 * @return the start game controller
	 */
	public StartGameController fileChooser() {
		File file = MapSStep.savedGameChooser();
		StartGameController gpc = loadSavedFile(file);
		return gpc;		
	}


	
	/**
	 * Load saved file.
	 *
	 * @param file the file
	 * @return the start game controller
	 */
	public StartGameController loadSavedFile(File file) {
		StartGameController controller = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			controller = (StartGameController) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
		return controller;
	}
}
