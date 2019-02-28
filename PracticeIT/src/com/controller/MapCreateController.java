package com.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MapCreateController implements EventHandler<ActionEvent> {
	
	@Override
	public void handle(ActionEvent event) {
	Stage stage = new Stage();
	 stage.setTitle("FXML Welcome");
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
