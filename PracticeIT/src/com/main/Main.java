package com.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * This main class inherits Application class.
 * @ref {@link www.github.com}
 */

public class Main extends Application {
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Risk Game ");
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			Group grp = new Group();
			Scene scn = new Scene(grp, 500, 450);
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.BOTTOM_CENTER);
		    vbox.getChildren().addAll(startPlayingGame(scn),  mapModification(scn), tournamentMode(scn),  exit(scn));			
			BorderPane bp = new BorderPane();
			bp.setBottom(vbox);
			bp.setCenter(LoadImage(scn, classLoader));
			grp.getChildren().addAll(bp );
			primaryStage.setScene(scn);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Load image.
	 * @param scn 
	 * @param classLoader 
	 * @return the image view
	 */
	public static ImageView LoadImage(Scene scn, ClassLoader classLoader) {
		final ImageView imageView = new ImageView();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(classLoader.getResource("pic.jpg").getFile()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image image = new Image(inputStream);
		imageView.setImage(image);
		imageView.fitWidthProperty().bind(scn.widthProperty());
		imageView.fitHeightProperty().bind(scn.heightProperty());
		imageView.setPreserveRatio(true);
		return imageView;
	}
	/**
	 * Exit.
	 * @param scn 
	 * @return the extbutton.
	 */
	public static Button exit(Scene scn) {
		Button extButton = new Button("Exit");
		extButton.setOnAction(e -> Platform.exit());
		return extButton;
	}

	
	
	/**
	 * Map modification.
	 * @param scn 
	 * @return the button
	 */
	public static Button mapModification(Scene scn) {
		Button mapMOdifyButton = new Button("Map Editor");
	    mapMOdifyButton.setOnAction(new MapFStep());
		return mapMOdifyButton;
	}


	/**
	 * Start playing game.
	 * @param scn 
	 * @return the button
	 */
	public static Button startPlayingGame(Scene scn) {
		Button gameButton = new Button("Start New Game");
		gameButton.setOnAction(new StartGame());
		return gameButton;
	}
	
	public static Button tournamentMode(Scene scn) {
		Button modeButton = new Button("Tournament Mode");
		modeButton.setOnAction(new TournamentMode());
		return modeButton;
	}
	/**
	 * The main method which invokes start method.
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
