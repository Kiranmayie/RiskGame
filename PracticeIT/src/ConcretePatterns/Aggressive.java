package ConcretePatterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.main.CardExchangeView;
import com.model.Cards;
import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Territories;

import Patterns.ContestantStrategies;
import Patterns.Observer;

public class Aggressive implements ContestantStrategies{

	private Territories territory;
	PlayersAssignment pa=new PlayersAssignment();
	private Contestant  currentContestant;
	private CardExchangeView cev;
	private List<Territories> attackingTerritoriesList;
	private List<Territories> defendingTerritoriesList;

	@Override
	public void loadBatallion(List<Territories> selectedTerritoriesList, Contestant currentContestant,List<Contestant> Contestants) {
	
		System.out.println("Placing Batallion against player "+currentContestant.getContestantName());
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


	@Override
	public void reinforcementPhase(List<Territories> territoryList, Territories territory, Contestant currentContestant) {

		List<Territories> sortedList = getMaximumAdjacentAndArmy(territoryList);
		if (!sortedList.isEmpty()) {
			territory = sortedList.get(0);
			territory.setBatallion(territory.getBatallion() + currentContestant.getBatallion());
			currentContestant.setBatallion(0);
		}

	}

	public boolean contestantHasAValidAttackMove(List<Territories> territories) {

		territory = checkIfTerritoriesNull(territories);
		List<Territories> defendingTerritories = getDefendingTerritories(territory);
		if (defendingTerritories.size() > 0 && territory.getBatallion() > 1) {
			return true;
		}
		return false;
	}

	private List<Territories> getDefendingTerritories(Territories territory2) {
		List<Territories> defendingTerritories = new ArrayList<Territories>();
		defendingTerritories.addAll(territory.getTouchingTrrtrsExpand());
		return defendingTerritories;
	}

	public boolean fortificationPhase(List<Territories> selectedTerritoriesList, List<Territories> adjTerritoriesList,
			Contestant currentContestant) {

		List<Territories> sortedList = getMaximumAdjacentAndArmy(selectedTerritoriesList);
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
					fortifyingtTerritories.get(0)
							.setBatallion(fortifyingtTerritories.get(0).getBatallion() + territory.getBatallion() - 1);
					territory.setBatallion(1);
					return true;
				}
			}
		}
		return false;
	}

	private void attack(Territories attacking, Territories defending) {
		if(attacking.getBatallion()>4&&defending.getBatallion()>3)
		{int[] attackerdicevalues=pa.autoStartDiceRollattacker(3);
		int[] defenderdicevalues=pa.autoStartDiceRollDefender(2);
		for(int i=0;i<=3;i++) {
			for(int j=0;i<=2;j++) {
		if(attackerdicevalues[i]>defenderdicevalues[j])
		{	System.out.println("Attacker won "+attackerdicevalues[i-1]+" is greater than "+defenderdicevalues[j-1]);
					int count1=pa.attackTerritory(attacking, defending);
					System.out.println(count1);
					
								attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant);
					
			}
		
		
		else
		{	pa.autoStartDiceRollattacker(attacking.getBatallion()-1);
		pa.autoStartDiceRollDefender(defending.getBatallion()-1);}
		
			}
		}
		}
	}

@Override
	public void attackPhase(List<Territories> attackingTerritoriesList, List<Territories> defendingTerritoriesList,Contestant curretContestant) {

		territory = checkIfTerritoriesNull(attackingTerritoriesList);
		List<Territories> defendingTerritories = getDefendingTerritories(territory);
		Iterator<Territories> defendingTerritoriesIterator = defendingTerritories.iterator();
		while (defendingTerritoriesIterator.hasNext()) {
			if (territory.getBatallion() > 1) {
				attack(territory, defendingTerritoriesIterator.next());
				break;
			}

		}

	}

	public Territories checkIfTerritoriesNull(List<Territories> attackingTerritoriesList) {
		if (territory == null || (territory.getBatallion() <= 1 || getDefendingTerritories(territory).size() == 0)) {
			List<Territories> sortedList = getMaximumAdjacentAndArmy(attackingTerritoriesList);
			for (Territories t : sortedList) {
				if (t.getBatallion() > 1) {
					territory = t;
					break;
				}
			}
		}
		return territory;

	}

	public List<Territories> getTerritoriesOwnedByCurrentContestant(Territories territory) {
		List<Territories> listWithValidAdjacentTerriroty = territory.getTouchingTrrtrsExpand().stream().
				filter(t -> (territory.getContestant().equals(t.getContestant()))).collect(Collectors.toList());
		return listWithValidAdjacentTerriroty;
	}

	public List<Territories> getMaximumAdjacentAndArmy(List<Territories> list) {
		Collections.sort(list, new Comparator<Territories>() {
			@Override
			public int compare(Territories o1, Territories o2) {
				return Integer.valueOf(getDefendingTerritories(o2).size()).compareTo(getDefendingTerritories(o1).size());
			}
		});

		return list;
	}

	

}
