package com.model;

import java.util.List;
import com.controller.StartGameController;
import com.units.Player;




public class PlayersAssignment {
	
	Player currentPlayer;
	public static final Integer THREE_PLAYER_ARMIES = 35;
	public static final Integer FOUR_PLAYER_ARMIES = 30;
	public static final Integer FIVE_PLAYER_ARMIES = 25;
	public static final Integer SIX_PLAYER_ARMIES = 20;
	List<Player> playersList;
	private int trrtrsConquered;


public Player getCurrentPlayer() {
	return currentPlayer;
}

public static boolean playersArmyAssign(List<Player> players) {
	//MapUtil.appendTextToGameConsole("===Assigning armies to players.===\n", textArea);
	boolean state = false;
	int currentArmSz = 0;
	int numberPlayers = players.size();
	
 switch(numberPlayers)
 {
 case 3:
		currentArmSz = THREE_PLAYER_ARMIES;
 case 4:		
	 currentArmSz = FOUR_PLAYER_ARMIES;
 case 5:
		currentArmSz = FIVE_PLAYER_ARMIES;
 case 6:
		currentArmSz = SIX_PLAYER_ARMIES;
 }
 
	for (Player player : players) {
		player.setPlayerarmy(currentArmSz);
		System.out.println(player.getPlayername() + " assigned: " + currentArmSz + "\n");
		state = true;
	}
	return state;
}



}

