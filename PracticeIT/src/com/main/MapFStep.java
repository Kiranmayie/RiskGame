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
/**
 * The Class MapFStep implements Eventhandler, Authenticates the map.
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
			Group grp = new Group();
			Scene sc = new Scene(grp, 350, 280);
			VBox vb = new VBox();
			vb.setAlignment(Pos.BOTTOM_CENTER);
			vb.getChildren().addAll(loadingAndEditingMap(sc),  creatingNewMap(sc), exit(sc));			
			BorderPane bp = new BorderPane();
			bp.setBottom(vb);
			bp.setCenter(Main.LoadImage(sc, classLoader));
			grp.getChildren().addAll(bp );
			stg.setScene(sc);
			stg.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Exit.
	 * @param sc 
	 * @return the button
	 */
	public static Button exit(Scene sc) {
		Button extButton = new Button("Exit");
		extButton.setOnAction(e -> Platform.exit());
		return extButton;
	}
	/**
	 * Creating New Map
	 * @param sc 
	 * @return the button
	 */
	public static Button creatingNewMap(Scene sc) {
		Button newMapButton = new Button("New Map");
	    newMapButton.setOnAction(new MapCreateController());
		return newMapButton;
	}
	
	/**
	 * Loading and Editing Map
	 * @param sc 
	 * @return the button
	 */
	public static Button loadingAndEditingMap(Scene sc) {
		Button loadEditButton = new Button("Load and Edit Map");
		loadEditButton.setOnAction(new MapBuildController());
		return loadEditButton;
	}
	}
