package com.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.PlayersAssignment;
import com.model.PlayersAssignment;
import com.units.Player;
import com.units.Map;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;


public class StartGameController implements Serializable {
	
	private Map map;
	private Object Player;
	private Object numberOfPlayers;
	static int numberPlayers;
	static String[] names;
	private PlayersAssignment playerAssign;
	List<Player> playerList;
	
	
	public StartGameController() {
	}
	
	
	public StartGameController(Map enhancedMap)  {
			this.map=enhancedMap;
			this.playerAssign = new PlayersAssignment();
			this.playerList = new ArrayList<>();
			StartGameController.createPlayer(numberPlayers,playerList);
			PlayersAssignment.playersArmyAssign(playerList);
			
			}
	
	public static List<Player> createPlayer(int numberOfPlayers, List<Player> players) {
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Player(i));
			
		}
		return players;
	}
	
	
		
	
}
