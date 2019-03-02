package com.model;

import com.units.Territories;

import javafx.scene.control.ListView;


public abstract class GamePlan {

	public abstract void attackPhase(ListView<Territories> attackingTerritoryList, ListView<Territories> defendingTerritoryList,
			PlayersAssignment gamePhase) ;

	

	
}
