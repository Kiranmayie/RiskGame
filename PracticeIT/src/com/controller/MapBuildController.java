package com.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapBuildController implements EventHandler<ActionEvent>{
	
	 Parent root ;
	 Stage stage = new Stage();
	public Button button;
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//Parent root ;
		try {
			   root = FXMLLoader.load(getClass().getResource("MapEditorNew.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
        Scene scene = new Scene(root);
    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
	
	}
	
	public void handleButtonClick() {
		
		button.setText("Stop Touching Me..!!");
	}
       
    
	
	 
	}

