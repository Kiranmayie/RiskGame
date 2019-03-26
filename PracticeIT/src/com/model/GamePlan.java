package com.model;

import com.units.Territories;
import com.units.Continents;
import com.units.Map;
import java.util.List;
import java.util.stream.Collectors;
import com.units.Contestant;
import com.units.Territories;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
// TODO: Auto-generated Javadoc
/**
 * The Class GamePlan is used to build strategy of attacking.
 * @author Sathwik
 */
public interface GamePlan {

	/**
	 * Attack phase.
	 *
	 * @param attackingTerritoryList the attacking territory list
	 * @param defendingTerritoryList the defending territory list
	 * @param gamePhase the game phase
	 */
	public abstract void attackPhase(ListView<Territories> attackingTerritoryList, ListView<Territories> defendingTerritoryList,
			PlayersAssignment gamePhase) ;

	
	/**
	 * Reinforcement phase.
	 *
	 * @param territoryList the territory list
	 * @param territory the territory
	 * @param pPlay the play
	 */
	public abstract void reinforcementPhase(ListView<Territories> territoryList, Territories territory,	Contestant pPlay);
	
	/**
	 * Fortification phase.
	 *
	 * @param selectedTerritory the selected territory
	 * @param adjTerritory the adj territory
	 * @param pPlay the play
	 * @return true, if successful
	 */
	boolean fortificationPhase(ListView<Territories> selectedTerritory, ListView<Territories> adjTerritory, Contestant pPlay);
	
	/**
	 * Checks if is fortification phase valid.
	 *
	 * @param map the map
	 * @param pPlaying the playing
	 * @return true, if is fortification phase valid
	 */
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
	
	/**
	 * Gets the retaining trrtrs.
	 *
	 * @param invadingTerritory the invading territory
	 * @return the retaining trrtrs
	 */
	default public List<Territories> getRetainingTrrtrs(Territories invadingTerritory) {
		List<Territories> defendingTerritories = invadingTerritory.getTouchingTrrtrsExpand().stream()
				.filter(t -> (invadingTerritory.getContestant() != t.getContestant())).collect(Collectors.toList());
		return defendingTerritories;

	}

	/**
	 * Attack move available.
	 *
	 * @param territories the territories
	 * @return true, if successful
	 */
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
