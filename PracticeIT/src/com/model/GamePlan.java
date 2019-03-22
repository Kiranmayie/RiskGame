package com.model;

import com.units.Territories;
import com.units.Continents;
import com.units.Map;
import com.units.Contestant;
import com.units.Territories;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
/**
 * The Class GamePlan is used to build strategy of attacking.
 * @author Sathwik
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
	
	default boolean isFortificationPhaseValid(Map map, Contestant pPlaying) {
		boolean isFortificationAvaialble = false;
		outer: for (Continents continent : map.getContinents()) {
			for (Territories territory : continent.getTrrtrs()) {
				if (territory.getContestant().equals(pPlaying)) {
					if (territory.getBatallion() > 1) {
					/*	for (Territories touchingTerritory : territory.getTouchingTrrtrs()) {
							if (touchingTerritory.getContestant().equals(pPlaying)) {
								isFortificationAvaialble = true;
								break outer;
							}
						}*/
					}
				}
			}
		}
		
		return isFortificationAvaialble;
	}

	
}
