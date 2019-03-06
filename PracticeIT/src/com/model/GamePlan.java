package com.model;

import com.units.Territories;

import javafx.scene.control.ListView;
/**
 * The Class GamePlan is used to build strategy of attacking.
 */
public abstract class GamePlan {

	/**
	 * Attack phase.
	 * @param attackingTerritoryList 
	 * @param defendingTerritoryList 
	 * @param gamePhase 
	 */
	public abstract void attackPhase(ListView<Territories> attackingTerritoryList, ListView<Territories> defendingTerritoryList,
			PlayersAssignment gamePhase) ;

	

	
}
