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


// TODO: Auto-generated Javadoc
/**
 * The Class StartGameController initiates a game and let the contestants play the game in round robin fashion.
 */
public class StartGameController implements Serializable {
	
	/** The map. */
	private Map map;
	
	/** The Player. */
	private Object Player;
	
	/** The number of players. */
	private Object numberOfPlayers;
	
	/** The number contestants. */
	public static int numberContestants;
	
	/** The names. */
	static String[] names;
	
	/** The player assign. */
	private PlayersAssignment playerAssign;
	 
 	/** The contestants. */
 	public  List<Contestant> contestants ;
	
	/** The pa. */
	PlayersAssignment pa;
	
	/**
	 * Counting contestants.
	 *
	 * @return the int
	 */
	public static int countingContestants()
	{ 
		System.out.print("Enter number of contestants");
		Scanner sc=new Scanner(System.in);
		numberContestants=sc.nextInt();
		return numberContestants;   
	
	}
	
	/**
	 * Instantiates a new start game controller.
	 *
	 * @param enhancedMap the enhanced map
	 */
	public StartGameController(Map enhancedMap)  {
			this.map=enhancedMap;
			StartGameController.numberContestants=countingContestants();
			}
	
	/**
	 * Creates the player.
	 *
	 * @param numberOfPlayers the number of players
	 * @param players the players
	 * @return the list
	 */
	public static List<Contestant> createPlayer(int numberOfPlayers, List<Contestant> players) {
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Contestant(i));
			
		}
		return players;
	}
	
	
		
	
}


