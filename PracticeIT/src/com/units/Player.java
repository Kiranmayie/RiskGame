package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Player implements Serializable {
	
	int playerid;
	String playername;
	int army;
	List<Territories> playerTrrtrlist;
	Player currentPlayer;
	List<Player> playersList;

	
	public Player(int playerid){
		this.playerid=playerid;
		this.playerTrrtrlist= new ArrayList();
	
	}
	
	
	public int getPlayerID(){
		return playerid;
	}
	
	
	public void setPlayerID(int playerid){
		this.playerid=playerid;
	}
	
	
	 public String getPlayername(){
		 return playername;
	 }
	 
	 
	 public void setPlayername(String playername){
		 this.playername=playername;
	 	 }
	 
	 
	 public int getPlayerarmy() {
		 return army;
	 	 }
	 
	 
	public void setPlayerarmy(int army){
		this.army=army;
	}
	
	
	public List<Territories> getPlayerTrrtrs(){
		return playerTrrtrlist;
	}
	
	
	public void setPlayerTrrtrs(List<Territories> playerTrrtrlist){
		this.playerTrrtrlist=playerTrrtrlist;
		} 
	
	
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (!(object instanceof Player)) {
			return false;
		}

		Player player = (Player) object;
		return player.getPlayerID() == playerid;
	}
	
	
	
		
	}


