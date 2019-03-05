package com.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class MapCreateController initiates a new map windowbased on the user selection.
 */
public class MapCreateController implements EventHandler<ActionEvent> {
	
	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent event) {
	Stage stage = new Stage();
	 stage.setTitle("Map Selection");
	 MapBuildController mvc= new MapBuildController();
	 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapEditorNew.fxml"));
		loader.setController(mvc);

		Parent root = null;
		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

}}
