/**
 * 
 */
package Strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

/**
 * @author k_bethi
 *
 */
public class BenevolentStrategy implements PlayerBehaviorStrategy {

	/* (non-Javadoc)
	 * @see Strategy.PlayerBehaviorStrategy#reinforcementPhase(java.util.List, com.units.Territories, com.units.Contestant)
	 */
	@Override
	public void reinforcementPhase(List<Territories> territoryList, Territories territory,
			Contestant currentContestant) {
		List<Territories> simplifiedList = getMinimumArmyFromTerritory(territoryList);
		territory = simplifiedList.get(0);
		territory.setBatallion(territory.getBatallion() + currentContestant.getBatallion());
		currentContestant.setBatallion(0);

	}

	private List<Territories> getMinimumArmyFromTerritory(List<Territories> territoryList) {
		Collections.sort(territoryList, new Comparator<Territories>() {
			@Override
			public int compare(Territories o1, Territories o2) {
				return Integer.valueOf(getDefendingTerritory(o2).size()).compareTo(getDefendingTerritory(o1).size());
			}
			
		});

		return territoryList;
	
	}

	/* (non-Javadoc)
	 * @see Strategy.PlayerBehaviorStrategy#attackPhase(java.util.List, java.util.List)
	 */
	@Override
	public void attackPhase(List<Territories> attackingTerritoryList, List<Territories> defendingTerritoryList) {
		System.out.println("No attack move for this player");
	}

	
	@Override
	public boolean fortificationPhase(List<Territories> selectedTerritoryList, List<Territories> adjTerritoryList,
			Contestant currentContestant) {
		List<Territories> simplifiedList = getMinimumArmyFromTerritory(selectedTerritoryList);
		for (Territories territory : simplifiedList) {
			if (territory.getBatallion() > 1) {
				List<Territories> fortifyingTerritory = getTerritoryOwnedByCurrentPlayer(territory);
				if (fortifyingTerritory.size() != 0) {
					Collections.sort(fortifyingTerritory, new Comparator<Territories>() {
						@Override
						public int compare(Territories o1, Territories o2) {
							return Integer.valueOf(o2.getBatallion()).compareTo(Integer.valueOf(o1.getBatallion()));
						}
					});
					territory.setBatallion(territory.getBatallion() + fortifyingTerritory.get(0).getBatallion() - 1);
					fortifyingTerritory.get(0).setBatallion(1);
					return true;
				}
			}
		}
		
		return false;
	}

	private List<Territories> getTerritoryOwnedByCurrentPlayer(Territories territory) {
		List<Territories> listWithValidAdjTerritory = territory.getTouchingTrrtrsExpand().stream()
				.filter(t -> (territory.getContestant().equals(t.getContestant()))).collect(Collectors.toList());
		return listWithValidAdjTerritory;
	}

	
}
