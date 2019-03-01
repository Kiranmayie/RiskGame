package com.main;
//package com.risk.main;

import java.io.IOException;
import java.net.URL;

//import com.risk.main.CreateMap;

//import com.risk.controller.MapEditorController;
import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is used to create map from MapLayout.
 * @author 
 * @version 1.0.0
 *
 */
public class BuildMap extends Application implements EventHandler<ActionEvent> {
	
	/*
	 * (non-Javadoc)
	 * This method is overridden to create a scene at UI end.
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	
	public static void main(String args[]) {
		
		//BuildMap bm=new BuildMap();
		launch(args);
		
		 }
	
	
	@Override
	public void handle(ActionEvent event) {
		VBox vbox=null;
		final Stage newMapStage = new Stage();
		newMapStage.setTitle("New Map");

		ChangeMap controller = new ChangeMap();

		FXMLLoader loader = new FXMLLoader();//getClass().getClassLoader().getResource("MapLayout.fxml"));
		try {
		loader.setLocation(new URL("file:///G:/MapLayout.fxml"));
		  vbox = loader.<VBox>load();
//System.out.println("Reached");
	//	Parent root = null;
		
			//System.out.println("Reached try");
			//root = (Parent) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("Reached catch");
			e.printStackTrace();
		}

		Scene scene = new Scene(vbox);
		newMapStage.setScene(scene);
		newMapStage.show();
	
}

	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Map");
		Button mapEditorButton = new Button("New Map");
		mapEditorButton.setOnAction(this);
		StackPane layout=new StackPane();
		layout.getChildren().add(mapEditorButton);
		Scene scene=new Scene(layout,300,250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	
	
	
	
	/*public static Button newMapButton() {
		
		//mapEditorButton.setOnAction(new BuildMap());
		//mapEditorButton.setMaxWidth(scene.getWidth());

		return map editorbutton;
	}*/
	}
