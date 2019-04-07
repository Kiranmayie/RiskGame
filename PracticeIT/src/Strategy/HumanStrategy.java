package Strategy;

import java.util.List;

import com.units.Contestant;
import com.units.Map;
import com.units.Territories;

public class HumanStrategy implements PlayerBehaviorStrategy {

	@Override
	public void reinforcementPhase(List<Territories> territoryList, Territories territory,
			Contestant currentContestant) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attackPhase(List<Territories> attackingTerritoryList, List<Territories> defendingTerritoryList) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFortificationPhaseValid(Map map, Contestant currentContestant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fortificationPhase(List<Territories> selectedTerritoryList, List<Territories> adjTerritoryList,
			Contestant currentContestant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contestantHasAValidAttackMove(List<Territories> territories) {
		// TODO Auto-generated method stub
		return false;
	}

}
