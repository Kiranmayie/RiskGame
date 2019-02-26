package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.main.MapSStep;
import com.model.AuthenticatingEnhanedmap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapBuildController implements EventHandler<ActionEvent>{
	
	
	 Stage stage = new Stage();
	public Button button;
	@Override
	public void handle(ActionEvent arg0) {
		
		File mapReturnedFile = MapSStep.mapFileValidator();
		 MapSStep mapsstep=new MapSStep();
		 try {
			mapsstep.readingMapFile(mapReturnedFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 if(AuthenticatingEnhanedmap.x>0) {
			 
			 System.out.println("Sorry Something went wrong. We are investigating the issue.");;
		 }
		 else {
        stage.setTitle("FXML Welcome");
        try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MapEditorNew.fxml"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        stage.setResizable(true);
        stage.show(); 
		 }
	}
	
	public void handleButtonClick() {
		
		button.setText("Stop Touching Me..!!");
	}
       
    
	
	 
	}

