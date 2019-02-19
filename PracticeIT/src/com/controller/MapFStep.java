package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MapFStep implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		
		//String new_Value;
		ClassLoader classLoader = getClass().getClassLoader();
		// TODO Auto-generated method stub
		final Stage MAP_F_STEP_STAGE = new Stage();
		MAP_F_STEP_STAGE.setTitle("Map Creator");
		Scene scene = new Scene(new Group(),300,240);
		
		final ComboBox map_selector = new ComboBox();
		map_selector.getItems().addAll("New Map","Load and Edit Map","exit");
		map_selector.setValue("New Map");
		
		
		
		 GridPane grid = new GridPane();
	        grid.setVgap(2);
	        grid.setHgap(6);
	        grid.setPadding(new Insets(3, 3, 3, 3));
	        
	       // grid.add(Main.LoadImage(scene, classLoader));
	        grid.add(map_selector, 2, 6);
	        Group root = (Group)scene.getRoot();
	        root.getChildren().add(grid);
	        MAP_F_STEP_STAGE.setScene(scene);
	        MAP_F_STEP_STAGE.show();
	        
	       
	}

	
}
