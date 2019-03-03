package com.main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.controller.MapBuildController;
import com.controller.MapCreateController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class MapFStep.
 */
public class MapFStep implements EventHandler<ActionEvent>{

	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	@Override
	public void handle(ActionEvent arg0) {
		Stage stg = new Stage();
		stg.setTitle("Map Selector");;
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			
			//group class object created
			Group base = new Group();
			
			Scene scene = new Scene(base, 300, 240);
			//scene.getStylesheets().add("application.css");
			
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.BOTTOM_CENTER);
			vbox.getChildren().addAll(startPlayingGame(scene),  mapModification(scene), exit(scene));			
			
			BorderPane bp = new BorderPane();
			bp.setBottom(vbox);
			bp.prefWidthProperty().bind(scene.widthProperty());
			bp.setCenter(Main.LoadImage(scene, classLoader));

			base.getChildren().addAll(bp );

			stg.setScene(scene);
			stg.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

				
			
	       
	/**
	 * Exit.
	 *
	 * @param scene the scene
	 * @return the button
	 */
	public static Button exit(Scene scene) {
		Button extButton = new Button("Exit");
		extButton.setOnAction(e -> Platform.exit());
		extButton.setMaxWidth(scene.getWidth());
		return extButton;
	}

	
	/**
	 * Map modification.
	 *
	 * @param scene the scene
	 * @return the button
	 */
	public static Button mapModification(Scene scene) {
		Button newMapButton = new Button("New Map");
	    newMapButton.setOnAction(new MapCreateController());
		newMapButton.setMaxWidth(scene.getWidth());

		return newMapButton;
	}
	
	/**
	 * Start playing game.
	 *
	 * @param scene the scene
	 * @return the button
	 */
	public static Button startPlayingGame(Scene scene) {
		Button loadEditButton = new Button("Load and Edit Map");
		loadEditButton.setOnAction(new MapBuildController());
		loadEditButton.setMaxWidth(scene.getWidth());
		return loadEditButton;
	}
	}
