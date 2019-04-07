/**
 * 
 */
package Strategy;

import java.util.List;

import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

/**
 * @author k_bethi
 *
 */
public class RandomStrategy implements PlayerBehaviorStrategy {

	/* (non-Javadoc)
	 * @see Strategy.PlayerBehaviorStrategy#reinforcementPhase(java.util.List, com.units.Territories, com.units.Contestant)
	 */
	@Override
	public void reinforcementPhase(List<Territories> territoryList, Territories territory,
			Contestant currentContestant) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see Strategy.PlayerBehaviorStrategy#attackPhase(java.util.List, java.util.List)
	 */
	@Override
	public void attackPhase(List<Territories> attackingTerritoryList, List<Territories> defendingTerritoryList) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public boolean fortificationPhase(List<Territories> selectedTerritoryList, List<Territories> adjTerritoryList,
			Contestant currentContestant) {
		// TODO Auto-generated method stub
		return false;
	}



}
