package Strategy;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.units.Contestant;
import com.units.Territories;

public class AggressiveStrategy<territory> implements PlayerBehaviorStrategy {

		private Territories territory;

		public void reinforcementPhase(List<Territories> territoryList, Territories territory,Contestant currentContestant) {

			List<Territories> simplifiedList = getMaximumAdjacentAndArmy(territoryList);
			if (!simplifiedList.isEmpty()) {
				territory = simplifiedList.get(0);
				territory.setBatallion(territory.getBatallion() + currentContestant.getBatallion());
				currentContestant.setBatallion(0);
			}

		}

		public boolean playerHasAValidAttackMove(List<Territories> territories) {

			territory = checkIfTerritoryNull(territories);
			List<Territory> defendingTerritory = getDefendingTerritory(territory);
			if (defendingTerritory.size() > 0 && territory.getBatallion() > 1) {
				return true;
			}
			return false;
		}

		public boolean fortificationPhase(List<Territories> selectedTerritoryList, List<Territories> adjTerritoryList,Contestant currentContestant) {

			List<Territories> simplifiedList = getMaximumAdjacentAndArmy(selectedTerritoryList);
			for (Territories territory : simplifiedList) {
				if (territory.getBatallion() > 1) {
					List<Territories> fortifyingtTerritory = getTerritoryOwnedByCurrentPlayer(territory);
					if (fortifyingtTerritory.size() != 0) {
						Collections.sort(fortifyingtTerritory, new Comparator<Territories>() {
							@Override
							public int compare(Territories o1, Territories o2) {
								return Integer.valueOf(o2.getBatallion()).compareTo(Integer.valueOf(o1.getBatallion()));
							}
						});
						fortifyingtTerritory.get(0)
								.setArmies(fortifyingtTerritory.get(0).getBatallion() + territory.getBatallion() - 1);
						territory.setArmies(1);
						return true;
					}
				}
			}
			return false;
		}

		private void attack(Territories attacking, Territories defending) {
			DiceModel diceModel = new DiceModel(attacking, defending);
			if (gamePhase != null) {
				diceModel.addObserver(gamePhase);
			}
			DiceRollController diceController = new DiceRollController(diceModel, this,);
			diceController.autoStartDiceRollController();
		}

		@Override
		public void attackPhase(List<Territories> attackingTerritoryList, List<Territories> defendingTerritoryList)  {

			territory = checkIfTerritoryNull(attackingTerritoryList);
			List<Territories> defendingTerritory = getDefendingTerritory(territory);
			Iterator<Territories> defendingTerritoryIterator = defendingTerritory.iterator();
			while (defendingTerritoryIterator.hasNext()) {
				if (territory.getBatallion() > 1) {
					attack(territory, defendingTerritoryIterator.next());
					break;
				}

			}

		}

		public Territories checkIfTerritoryNull(List<Territories> attackingTerritoryList) {
			if (territory == null || (territory.getBatallion() <= 1 || getDefendingTerritory(territory).size() == 0)) {
				List<Territories> simplifiedList = getMaximumAdjacentAndArmy(attackingTerritoryList.getItems());
				for (Territories t : simplifiedList) {
					if (t.getBatallion() > 1) {
						territory = t;
						break;
					}
				}
			}
			return territory;

		}

		public List<Territories> getTerritoryOwnedByCurrentPlayer(Territories territory) {
			List<Territories> listWithValidAdjacentTerriroty = territory.getTouchingTrrtrsExpand().stream()
					.filter(t -> (territory.getContestant().equals(t.getContestant()))).collect(Collectors.toList());
			return listWithValidAdjacentTerriroty;
		}

		public List<Territories> getMaximumAdjacentAndArmy(List<Territories> list) {
			Collections.sort(list, new Comparator<Territories>() {
				public int compare(Territories o1, Territories o2) {
					return Integer.valueOf(getDefendingTerritory(o2).size()).compareTo(getDefendingTerritory(o1).size());
				}
			});

			return list;
		}

	}

}
