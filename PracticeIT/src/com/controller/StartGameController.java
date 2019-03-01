package com.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.PlayersAssignment;
import com.model.PlayersAssignment;
import com.units.Contestant;
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
	List<Contestant> playerList;
	
	
	public StartGameController() {
	}
	
	
	public StartGameController(Map enhancedMap)  {
			this.map=enhancedMap;
			this.playerAssign = new PlayersAssignment();
			this.playerList = new ArrayList<>();
			StartGameController.createPlayer(numberPlayers,playerList);
			PlayersAssignment.playersArmyAssign(playerList);
			
			}
	
	public static List<Contestant> createPlayer(int numberOfPlayers, List<Contestant> players) {
		for (int i = 0; i < numberOfPlayers; i++) {
			players.add(new Contestant(i));
			
		}
		return players;
	}
	
	
		
	
}