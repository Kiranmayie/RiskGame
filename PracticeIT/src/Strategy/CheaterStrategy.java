/**
 * 
 */
package Strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.units.Contestant;
import com.units.Map;
import com.units.Territories;



/**
 * @author k_bethi
 *
 */
public class CheaterStrategy implements PlayerBehaviorStrategy {
		List<Territories> attackTerList= new ArrayList<Territories>();
	/* (non-Javadoc)
	 * @see Strategy.PlayerBehaviorStrategy#reinforcementPhase(java.util.List, com.units.Territories, com.units.Contestant)
	 */
	@Override
	public void reinforcementPhase(List<Territories> territoryList, Territories territory,
			Contestant currentContestant) {
		
			for (Territories territory1 : territoryList) {
				territory1.setBatallion(territory1.getBatallion() * 2);
				System.out.println("Batallion have been doubled on territory " + territory1.getAssignName() + "\n");
						
			}
			
			attackTerList.clear();
			attackTerList.addAll(territoryList);
			currentContestant.setBatallion(0);
		}

	

	/* (non-Javadoc)
	 * @see Strategy.PlayerBehaviorStrategy#attackPhase(java.util.List, java.util.List)
	 */
	@Override
	public void attackPhase(List<Territories> attackingTerritoryList, List<Territories> defendingTerritoryList) {
		// TODO Auto-generated method stub
		
			List<Territories> territoryWon = new ArrayList<Territories>();
			List<Territories> attackTerList = this.attackTerList;
			Iterator<Territories> terrIterator = attackTerList.iterator();
			while (terrIterator.hasNext()) {
				Territories attackingTerritory = terrIterator.next();
				List<Territories> defendingTerritories = getDefendingTerritory(attackingTerritory);
				if (defendingTerritories.size() > 0) {
					Territories defendingTerr = defendingTerritories.get(0);
					defendingTerr.setBatallion(1);
					attackingTerritory.setBatallion(attackingTerritory.getBatallion() - 1);

					defendingTerr.getContestant().getContestantTrrtrlist().remove(defendingTerr);
					defendingTerr.setContestant(attackingTerritory.getContestant());

					attackingTerritory.getContestant().getContestantTrrtrlist().add(defendingTerr);

					territoryWon.add(defendingTerr);
					System.out.println(defendingTerr.getAssignName() + " has been conquered by "
							+ attackingTerritory.getContestant().getContestantName() + "\n");
					break;
				} else {
					continue;
				}
			}
			attackingTerritoryList.addAll(territoryWon);
	}
	@Override
	public boolean fortificationPhase(List<Territories> selectedTerritoryList, List<Territories> adjTerritoryList,Contestant currentContestant) {

		Iterator<Territories> iterateTerritory = attackTerList.iterator();

		while (iterateTerritory.hasNext()) {
			Territories fortifyingTerritory = iterateTerritory.next();
			List<Territories> particularFortifyingTerritory = fortifyingTerritory.getTouchingTrrtrsExpand().stream()
					.filter(t -> (fortifyingTerritory.getContestant() != t.getContestant())).collect(Collectors.toList());

			for (Territories t : particularFortifyingTerritory) {
				t.setBatallion(t.getBatallion() * 2);
				System.out.println("Battalion have been doubled on territory " + t.getAssignName() + "\n");
			}
		}
		return true;
	}
	
	public boolean isFortificationPhaseValid(Map map, Contestant currentContestant) {
		boolean isFortificationAvaialble = false;
		outer: for (Territories territory : attackTerList) {
			if (territory.getContestant().equals(currentContestant)) {
				if (territory.getBatallion() > 1) {
					for (Territories adjterritory : territory.getTouchingTrrtrsExpand()) {
						if (adjterritory.getContestant().equals(currentContestant)) {
							isFortificationAvaialble = true;
							break outer;
						}
					}
				}
			}
		}

		return isFortificationAvaialble;
	}
	
	public boolean contestantHasAValidAttackMove(List<Territories> territories) {
		boolean hasAValidMove = false;
		if (attackTerList == null || attackTerList.isEmpty()) {
			attackTerList.addAll(territories);
		}
		for (Territories territory : attackTerList) {
			if (getDefendingTerritory(territory).size() > 0) {
				hasAValidMove = true;
			}
		}

		if (!hasAValidMove) {
			System.out.println("No valid attack move avialble move to Fortification phase.\n");
			
			return hasAValidMove;
		}
		return hasAValidMove;
	}
}
