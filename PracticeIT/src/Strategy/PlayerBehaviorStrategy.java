/**
 * 
 */
package Strategy;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

/**
 * @author k_bethi
 *
 */
public interface PlayerBehaviorStrategy extends Serializable {

	void reinforcementPhase(List<Territories> territoryList, Territories territory, Contestant currentContestant);



default public List<Territories> getDefendingTerritory(Territories attackingTerritory) {
	List<Territories> defendingTerritories = attackingTerritory.getTouchingTrrtrsExpand().stream()
			.filter(t -> (attackingTerritory.getContestant() != t.getContestant())).collect(Collectors.toList());

	return defendingTerritories;

}



void attackPhase(List<Territories> attackingTerritoryList, List<Territories> defendingTerritoryList);







boolean fortificationPhase(List<Territories> selectedTerritoryList, List<Territories> adjTerritoryList,
		Contestant currentContestant);




}
