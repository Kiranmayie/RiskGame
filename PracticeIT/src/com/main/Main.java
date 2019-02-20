package com.main;
//package com.risk.main;

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


public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Risk Map ");
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			
			
			Group base = new Group();
			
			Scene scene = new Scene(base, 300, 240);
			//scene.getStylesheets().add("application.css");
			
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.BOTTOM_CENTER);
			vbox.getChildren().addAll(startPlayingGame(scene),  mapModification(scene), exit(scene));			
			
			BorderPane bp = new BorderPane();
			bp.setBottom(vbox);
			bp.prefWidthProperty().bind(scene.widthProperty());
			bp.setCenter(LoadImage(scene, classLoader));

			base.getChildren().addAll(bp );

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static ImageView LoadImage(Scene scene, ClassLoader classLoader) {
		final ImageView imageView = new ImageView();

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(classLoader.getResource("pic.jpg").getFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image image = new Image(inputStream);
		imageView.setImage(image);
		

		imageView.fitWidthProperty().bind(scene.widthProperty());
		imageView.fitHeightProperty().bind(scene.heightProperty());
		imageView.setPreserveRatio(true);

		return imageView;
	}


	public static Button exit(Scene scene) {
		Button extButton = new Button("Exit");
		extButton.setOnAction(e -> Platform.exit());
		extButton.setMaxWidth(scene.getWidth());
		return extButton;
	}

	
	public static Button mapModification(Scene scene) {
		Button mapMOdifyButton = new Button("Map Editor");
	mapMOdifyButton.setOnAction(new MapFStep());
		mapMOdifyButton.setMaxWidth(scene.getWidth());

		return mapMOdifyButton;
	}

	
	public static Button startPlayingGame(Scene scene) {
		Button gameButton = new Button("Start New Game");
		//gameButton.setOnAction(new GamePlay());
		gameButton.setMaxWidth(scene.getWidth());
		return gameButton;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
