package com.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.controller.StartGameController;
import com.model.GameDesign;
import com.model.PlayersAssignment;
import com.model.TraceOfVariables;
import com.units.Contestant;
import com.units.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class StartSavedGame implements EventHandler<ActionEvent>, Serializable {
	private Map map;

	private GameDesign gameModel;

	private int attackCount = 5;
	
	private PlayersAssignment playerGame;

	private int numberOfPlayersSelected;

	/**
	 * The @gamePlayerList list of players in the game.
	 */
	private List<Contestant> gamePlayerList;

	private Contestant playerPlaying;

	private Iterator<Contestant> playerIterator;

	//private Stack<Card> cardStack;

	private int numberOfCardSetExchanged;

	@Override
	public void handle(ActionEvent event) {
		StartGameController controller = fileChooser();
		final Stage mapSelectorStage = new Stage();
		mapSelectorStage.setTitle("Game Screen");
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MapSelectorLayout.fxml"));
		loader.setController(controller);
		Parent root = null;
		try {
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		mapSelectorStage.setScene(scene);
		mapSelectorStage.show();
	}
	public StartGameController fileChooser() {
		File file = MapSStep.savedGameChooser();
		StartGameController gpc = loadSavedFile(file);
		return gpc;		
	}


	
	public StartGameController loadSavedFile(File file) {
		StartGameController controller = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			controller = (StartGameController) in.readObject();
			in.close();
			fileIn.close();
		} catch (Exception i) {
			i.printStackTrace();
		}
		return controller;
	}
	

	

	/*public  void saveGame(TraceOfVariables traces){
		
        try
        {  
            FileOutputStream saveFile=new FileOutputStream("playersGame.ser");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(traces);
            save.close(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();            
        }       
    
      }
	public TraceOfVariables  loadGame(){
		TraceOfVariables traces = new TraceOfVariables();
		try
        {
            FileInputStream saveFile = new FileInputStream("playersGame.ser");
            ObjectInputStream save = new ObjectInputStream(saveFile);           
            traces = (TraceOfVariables ) save.readObject();
            save.close();

        }
        catch(Exception exc)
        {
            exc.printStackTrace(); 
        }
        return traces;
	}
	public TraceOfVariables traces(Integer armies_per_player, HashMap<String, Integer> army_per_country,
			 ArrayList<String> continent_list, HashMap<String, String> country_continent,
			 ArrayList<String> country_list, List<List<String>> country_per_player,
			 int total_players, File file,HashMap<String, Integer> player_names, List<List<String>>continent_list_per_player,
			 HashMap<String, Integer> contvalue, HashMap<String, Integer> contvalue1,
			 int total_country,ArrayList<String> country_name,int i){
		TraceOfVariables gameElements = new TraceOfVariables();
		 gameElements.noOfArmiesPerPlayer = armies_per_player;
			gameElements.armiesForEachtrrtrs = army_per_country;
			gameElements.continentList = continent_list;
			gameElements.territoriesForEachContinent = country_continent;
			gameElements.trrtrsList = country_list;
			gameElements.trrtsPercontestant = country_per_player;
			gameElements.totalContestants = total_players;
			gameElements.file = file;
			gameElements.contestantName = player_names;
			gameElements.continentListPerContestant = continent_list_per_player;
			gameElements.cValue = contvalue;
			gameElements.cValue1 = contvalue1;
			gameElements.totaltrrtrs = total_country;
			gameElements.trrtrsName = country_name;
			gameElements.file = file;
			gameElements.i = i;
			return gameElements;
	 }*/
}
