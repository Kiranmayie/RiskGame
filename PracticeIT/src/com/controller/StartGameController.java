package com.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Map;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;


public class StartGameController implements Serializable {
	
	private Map map;
	private Object Player;
	private Object numberOfPlayers;
	public static int numberContestants;
	static String[] names;
	private PlayersAssignment playerAssign;
	 public  List<Contestant> contestants ;
	PlayersAssignment pa;
	
	public static int countingContestants()
	{ 
		System.out.print("Enter number of contestants");
		Scanner sc=new Scanner(System.in);
		numberContestants=sc.nextInt();
		return numberContestants;   
	
	}
	
	public StartGameController(Map enhancedMap)  {
			this.map=enhancedMap;
			StartGameController.numberContestants=countingContestants();
			}
	
	public static List<Contestant> createPlayer(int numberOfPlayers, List<Contestant> players) {
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Contestant(i));
			
		}
		return players;
	}
	
	
		
	
}


