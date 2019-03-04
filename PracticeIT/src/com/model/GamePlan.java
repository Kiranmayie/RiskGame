package com.model;

import com.units.Territories;

import javafx.scene.control.ListView;


// TODO: Auto-generated Javadoc
/**
 * The Class GamePlan.
 */
public abstract class GamePlan {

	/**
	 * Attack phase.
	 *
	 * @param attackingTerritoryList the attacking territory list
	 * @param defendingTerritoryList the defending territory list
	 * @param gamePhase the game phase
	 */
	public abstract void attackPhase(ListView<Territories> attackingTerritoryList, ListView<Territories> defendingTerritoryList,
			PlayersAssignment gamePhase) ;

	

	
}
