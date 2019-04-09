/**
 * 
 */
package ConcretePatterns;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Territories;

import Patterns.ContestantStrategies;

/**
 * @author kiran
 *
 */
public class Random implements ContestantStrategies {
		PlayersAssignment pa=new PlayersAssignment();
		private List<Territories> attackingTerritoriesList;
		private List<Territories> defendingTerritoriesList;
		private Contestant currentContestant;
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

	@Override
	public void attackPhase(List<Territories> getcontestantTrrtrlist, List<Territories> getcontestantTrrtrlist2,
			Contestant currentContestant) {

		List<Territories> attackTerList = currentContestant.getcontestantTrrtrlist();
		Iterator<Territories> terrIterator = attackTerList.iterator();
		while (terrIterator.hasNext()) {
			Territories attackingTerritories = terrIterator.next();
			if (attackingTerritories.getBatallion() > 1) {
				List<Territories> defendingTerritories = pa.getDefendingTerritory(attackingTerritories);
				if (defendingTerritories.size() == 0) {
					continue;
				}
				attack(attackingTerritories, defendingTerritories.get(0));
				break;
			}
		}
		
	}

	private void attack(Territories attacking, Territories defending) {
		if(attacking.getBatallion()>4&&defending.getBatallion()>3)
		{Integer[] attackerdicevalues=pa.autoStartDiceRollattacker(3);
		Integer[] defenderdicevalues=pa.autoStartDiceRollDefender(2);
		for(int i=0;i<=3;i++) {
			for(int j=0;i<=2;j++) {
		if(attackerdicevalues[i]>defenderdicevalues[j])
		{	System.out.println("Attacker won "+attackerdicevalues[i-1]+" is greater than "+defenderdicevalues[j-1]);
					int count1=pa.attackTerritory(attacking, defending, currentContestant);
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
	public void reinforcementPhase(List<Territories> territoryList, Territories territory,
			Contestant currentContestant) {
		int count = territoryList.size();
		Territories randomTerritories = territoryList.get(randomNumber(count - 1));
		int Batallion = currentContestant.getBatallion();

		if (Batallion > 0) {
			randomTerritories.setBatallion(randomTerritories.getBatallion() + Batallion);
			currentContestant.setBatallion(currentContestant.getBatallion() - Batallion);
			System.out.println(Batallion + ": assigned to territory " + randomTerritories.getAssignName() + "\n");
		}
	}
	public int randomNumber(int count) {
		return (int) (Math.random() * count) + 0;
	}

	public boolean fortificationPhase(List<Territories> selectedTerritoriesList, List<Territories> adjTerritoriesList,
			 Contestant currentContestant) {
		boolean isFortificationDone = false;
		List<Territories> selectedTerritories = selectedTerritoriesList;
		boolean fortifiyingStarted = true;
		Iterator<Territories> iterateTerritories = selectedTerritories.iterator();
		while (fortifiyingStarted && iterateTerritories.hasNext()) {
			Territories fortifyingTerritories = iterateTerritories.next();
			if (fortifyingTerritories.getBatallion() > 1) {
				List<Territories> adjTerritories = fortifyingTerritories.getTouchingTrrtrsExpand().stream()
						.filter(t -> fortifyingTerritories.getContestant().equals(t.getContestant()))
						.collect(Collectors.toList());

				if (adjTerritories.size() > 0) {
					Territories terrToBeFortified = adjTerritories.get(randomNumber(adjTerritories.size() - 1));
					int Batallion = fortifyingTerritories.getBatallion() - 1;
					terrToBeFortified.setBatallion(terrToBeFortified.getBatallion() + Batallion);
					System.out.println(Batallion + ": fortified to territory " + terrToBeFortified.getAssignName() + "\n");
					fortifyingTerritories.setBatallion(1);
					isFortificationDone = true;
					break;
				} else {
					continue;
				}

			}
		}
		return isFortificationDone;
	}

}
