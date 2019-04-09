package com.model;

import com.units.Territories;
import com.units.Continents;
import com.units.Map;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.units.Contestant;
import com.units.Territories;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
/**
 * The Class GamePlan is used to build strategy of attacking.
 * @author Sathwik
 */
public interface GamePlan extends Serializable {

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
						for (Territories touchingTerrtrs : territory.getTouchingTrrtrsExpand()) {
							if (touchingTerrtrs.getContestant().equals(pPlaying)) {
								isFortificationAvaialble = true;
								break outer;
							}
						}
					}
				}
			}
		}
		
		return isFortificationAvaialble;
	}
	
	default public List<Territories> getRetainingTrrtrs(Territories invadingTerritory) {
		List<Territories> defendingTerritories = invadingTerritory.getTouchingTrrtrsExpand().stream()
				.filter(t -> (invadingTerritory.getContestant() != t.getContestant())).collect(Collectors.toList());
		return defendingTerritories;

	}

	default boolean attackMoveAvailable(ListView<Territories> territories) {
		boolean ifValidMove = false;
		for (Territories territory : territories.getItems()) {
			if (territory.getBatallion() > 1 && getRetainingTrrtrs(territory).size() > 0) {
				ifValidMove = true;
			}
		}

		if (!ifValidMove) {
			System.out.println("No Attack left move to fortification: <b> Attack Phase Ended</b> ");
			return ifValidMove;
		}
		return ifValidMove;
	}

	
}
