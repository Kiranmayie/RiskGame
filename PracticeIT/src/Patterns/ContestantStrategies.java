package Patterns;

import java.util.List;

import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContestantStrategies.
 */
public interface ContestantStrategies {

	/**
	 * Load batallion.
	 *
	 * @param selectedTerritoryList the selected territory list
	 * @param currentContestant the current contestant
	 * @param Contestants the contestants
	 */
	public void loadBatallion(List<Territories> selectedTerritoryList,Contestant currentContestant,List<Contestant> Contestants);

	

	/**
	 * Attack phase.
	 *
	 * @param getcontestantTrrtrlist the getcontestant trrtrlist
	 * @param getcontestantTrrtrlist2 the getcontestant trrtrlist 2
	 * @param currentContestant the current contestant
	 * @param map the map
	 */
	public void attackPhase(List<Territories> getcontestantTrrtrlist, List<Territories> getcontestantTrrtrlist2,
			Contestant currentContestant,Map map);

	/**
	 * Reinforcement phase.
	 *
	 * @param territoryList the territory list
	 * @param currentContestant the current contestant
	 * @param map the map
	 * @return the territories
	 */
	Territories reinforcementPhase(List<Territories> territoryList, Contestant currentContestant,Map map);

}
