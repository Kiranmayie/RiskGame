package ConcretePatterns;

import java.util.*;
import java.util.Iterator;

import java.util.List;
import java.util.stream.Collectors;
import com.units.Map;
import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Territories;

import Patterns.ContestantStrategies;

// TODO: Auto-generated Javadoc
/**
 * The Class Cheater.
 */
public class Cheater implements ContestantStrategies{
	
	/** The pa. */
	PlayersAssignment pa=new PlayersAssignment();
	
	/** The attack ter list. */
	private List<Territories> attackTerList;
	
	/** The terr. */
	private Territories terr;
	
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
	public void attackPhase(List<Territories> attackTerList, List<Territories> defTerList,
			Contestant currentContestant,Map map) {
		List<Territories> territoryWon = new ArrayList<Territories>();
		List<Territories> attackTerList1 = this.attackTerList;
		Iterator<Territories> terrIterator = attackTerList1.iterator();
		while (terrIterator.hasNext()) {
			Territories attackingTerritories = terrIterator.next();
			List<Territories> defendingTerritories = pa.getDefendingTerritory(attackingTerritories);
			if (defendingTerritories.size() > 0) {
				Territories defendingTerr = defendingTerritories.get(0);
				defendingTerr.setBatallion(1);
				attackingTerritories.setBatallion(attackingTerritories.getBatallion() - 1);

				defendingTerr.getContestant().getContestantTrrtrlist().remove(defendingTerr);
				defendingTerr.setContestant(attackingTerritories.getContestant());

				attackingTerritories.getContestant().getContestantTrrtrlist().add(defendingTerr);

				territoryWon.add(defendingTerr);
				System.out.println(defendingTerr.getAssignName() + " has been conquered by "
						+ attackingTerritories.getContestant().getContestantName() + "\n");
				break;
			} else {
				continue;
			}
		}
		attackTerList.addAll(territoryWon);
	}
	
	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#reinforcementPhase(java.util.List, com.units.Contestant, com.units.Map)
	 */
	public Territories reinforcementPhase(List<Territories> territoryList, 
			Contestant currentContestant,Map map) {
		for (Territories terr : territoryList) {
			terr.setBatallion(terr.getBatallion() * 2);
			System.out.println("Batallion have been doubled on territory " + terr.getAssignName() + "\n");
		}
		attackTerList.clear();
		attackTerList.addAll(territoryList);
		currentContestant.setBatallion(0);
		return terr;
	}
	
			/**
			 * Fortification phase.
			 *
			 * @param selectedTerritoriesList the selected territories list
			 * @param adjTerritoriesList the adj territories list
			 * @param contestantPlaying the contestant playing
			 * @return true, if successful
			 */
			public boolean fortificationPhase(List<Territories> selectedTerritoriesList, List<Territories> adjTerritoriesList,
					 Contestant contestantPlaying) {

				// ObservableList<Territories> selectedTerrList =
				// selectedTerritoriesList.getItems();
				Iterator<Territories> iterateTerritories = attackTerList.iterator();

				while (iterateTerritories.hasNext()) {
					Territories fortifyingTerritories = iterateTerritories.next();
					List<Territories> particularFortifyingTerritories = fortifyingTerritories.getTouchingTrrtrsExpand().stream()
							.filter(t -> (fortifyingTerritories.getContestant() != t.getContestant())).collect(Collectors.toList());

					for (Territories t : particularFortifyingTerritories) {
						t.setBatallion(t.getBatallion() * 2);
						System.out.println("Batallion have been doubled on territory " + t.getAssignName() + "\n");
					}
				}
				return true;
			}

			
			/**
			 * Checks if is fortification phase valid.
			 *
			 * @param map the map
			 * @param contestantPlaying the contestant playing
			 * @return true, if is fortification phase valid
			 */
			public boolean isFortificationPhaseValid(Map map, Contestant contestantPlaying) {
				boolean isFortificationAvaialble = false;
				outer: for (Territories territory : attackTerList) {
					if (territory.getContestant().equals(contestantPlaying)) {
						if (territory.getBatallion() > 1) {
							for (Territories adjterritory : territory.getTouchingTrrtrsExpand()) {
								if (adjterritory.getContestant().equals(contestantPlaying)) {
									isFortificationAvaialble = true;
									break outer;
								}
							}
						}
					}
				}

				return isFortificationAvaialble;
			}

			
			/**
			 * Contestant has A valid attack move.
			 *
			 * @param territories the territories
			 * @return true, if successful
			 */
			public boolean contestantHasAValidAttackMove(List<Territories> territories) {
				boolean hasAValidMove = false;
				if (attackTerList == null || attackTerList.isEmpty()) {
					attackTerList.addAll(territories);
				}
				for (Territories territory : attackTerList) {
					if (pa.getDefendingTerritory(territory).size() > 0) {
						hasAValidMove = true;
					}
				}

				if (!hasAValidMove) {
					System.out.println("No valid attack move avialble move to Fortification phase.\n");
					System.out.println("===Attack phase ended! === \n");
					return hasAValidMove;
				}
				return hasAValidMove;
			}

			


}
