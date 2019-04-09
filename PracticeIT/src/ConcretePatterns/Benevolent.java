package ConcretePatterns;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

import Patterns.ContestantStrategies;

// TODO: Auto-generated Javadoc
/**
 * The Class Benevolent.
 */
public class Benevolent implements ContestantStrategies {
		
		/** The pa. */
		PlayersAssignment pa=new PlayersAssignment();
		
		/** The territory. */
		private Territories territory;
	
	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#loadBatallion(java.util.List, com.units.Contestant, java.util.List)
	 */
	@Override
	public void loadBatallion(List<Territories> selectedTerritoriesList, Contestant currentContestant,
			List<Contestant> Contestants) {
		System.out.println("Placing Batallion against contestant "+currentContestant.getContestantName());
		System.out.println(selectedTerritoriesList);
		int contestantBatallion = currentContestant.getBatallion();
		
		if (currentContestant!= null && contestantBatallion > 0)  {
			for(Territories terrtry:selectedTerritoriesList) {
			
			if(terrtry.getTouchingTrrtrs().size()>1) {
				
	terrtry.setBatallion(terrtry.getBatallion() + 1);
	currentContestant.setBatallion(contestantBatallion - 1);
	System.out.println(selectedTerritoriesList);
	System.out.println( terrtry.getAssignName() + ":-" + terrtry.getBatallion() + "-" + currentContestant.getContestantName());
	break;
}

	}
		}
		
	}

	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#attackPhase(java.util.List, java.util.List, com.units.Contestant, com.units.Map)
	 */
	@Override
	public void attackPhase(List<Territories> getcontestantTrrtrlist, List<Territories> getcontestantTrrtrlist2,
			Contestant currentContestant,Map map) {
		// No attack move for this contestant
		
	}
	
	/**
	 * Fortification phase.
	 *
	 * @param selectedTerritoriesList the selected territories list
	 * @param adjTerritories the adj territories
	 * @param currentContestant the current contestant
	 * @return true, if successful
	 */
	public boolean fortificationPhase(List<Territories> selectedTerritoriesList, List<Territories> adjTerritories,
			 Contestant currentContestant) {
		// TODO Auto-generated method stub
		List<Territories> sortedList = getMinimumArmyFromTerritories(selectedTerritoriesList);
		for (Territories territory : sortedList) {
			if (territory.getBatallion() > 1) {
				List<Territories> fortifyingtTerritories = getTerritoriesOwnedByCurrentContestant(territory);
				if (fortifyingtTerritories.size() != 0) {
					Collections.sort(fortifyingtTerritories, new Comparator<Territories>() {
						@Override
						public int compare(Territories o1, Territories o2) {
							return Integer.valueOf(o2.getBatallion()).compareTo(Integer.valueOf(o1.getBatallion()));
						}
					});
					territory.setBatallion(territory.getBatallion() + fortifyingtTerritories.get(0).getBatallion() - 1);
					fortifyingtTerritories.get(0).setBatallion(1);
					return true;
				}
			}
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#reinforcementPhase(java.util.List, com.units.Contestant, com.units.Map)
	 */
	@Override
	public Territories reinforcementPhase(List<Territories> territoryList, 
			Contestant currentContestant,Map map) {
		List<Territories> sortedList = getMinimumArmyFromTerritories(territoryList);
		territory = sortedList.get(0);
		territory.setBatallion(territory.getBatallion() + currentContestant.getBatallion());
		currentContestant.setBatallion(0);
		return territory;
		
	}

	/**
	 * Contestant has A valid attack move.
	 *
	 * @param territories the territories
	 * @return true, if successful
	 */
	public boolean contestantHasAValidAttackMove(List<Territories> territories) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Gets the territories owned by current contestant.
	 *
	 * @param territory the territory
	 * @return the territories owned by current contestant
	 */
	public List<Territories> getTerritoriesOwnedByCurrentContestant(Territories territory) {
		List<Territories> listWithValidAdjacentTerriroty = territory.getTouchingTrrtrsExpand().stream()
				.filter(t -> (territory.getContestant().equals(t.getContestant()))).collect(Collectors.toList());
		return listWithValidAdjacentTerriroty;
	}

	/**
	 * Gets the minimum army from territories.
	 *
	 * @param list the list
	 * @return the minimum army from territories
	 */
	public List<Territories> getMinimumArmyFromTerritories(List<Territories> list) {
		Collections.sort(list, new Comparator<Territories>() {
			@Override
			public int compare(Territories o1, Territories o2) {
				return Integer.valueOf(pa.getDefendingTerritory(o2).size()).compareTo(pa.getDefendingTerritory(o1).size());
			}
		});

		return list;
	}

}
