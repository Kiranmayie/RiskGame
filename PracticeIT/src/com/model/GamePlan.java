package com.model;

import com.units.Territories;
import com.units.Contestant;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
/**
 * The Class GamePlan is used to build strategy of attacking.
 * @author sathwik
 */
public interface GamePlan {

	/**
	 * Attack phase.
	 * @param attackingTerritoryList 
	 * @param defendingTerritoryList 
	 * @param gamePhase 
	 */
	public abstract void attackPhase(ListView<Territories> attackingTerritoryList, ListView<Territories> defendingTerritoryList,
			PlayersAssignment gamePhase) ;

	
	public abstract void reinforcementPhase(ListView<Territories> territoryList, Territories territory,	Contestant pPlay);
	
	boolean fortificationPhase(ListView<Territories> selectedTerritory, ListView<Territories> adjTerritory, Contestant pPlay);
	

	
}
