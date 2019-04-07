package ConcretePatterns;

import java.util.List;

import com.units.Contestant;
import com.units.Territories;

import Patterns.ContestantStrategies;

public class Aggressive implements ContestantStrategies{

	@Override
	public void loadBatallion(List<Territories> selectedTerritoryList, Contestant currentContestant,List<Contestant> Contestants) {
	
		System.out.println("Placing Batallion against player "+currentContestant.getContestantName());
		System.out.println(selectedTerritoryList);
		int contestantArmies = currentContestant.getBatallion();
		
		if (currentContestant!= null && contestantArmies > 0)  {
			for(Territories terrtry:selectedTerritoryList) {
			
			if(terrtry.getTouchingTrrtrs().size()>1) {
				
	terrtry.setBatallion(terrtry.getBatallion() + 1);
	currentContestant.setBatallion(contestantArmies - 1);
	System.out.println(selectedTerritoryList);
	System.out.println( terrtry.getAssignName() + ":-" + terrtry.getBatallion() + "-" + currentContestant.getContestantName());
	break;
}

	}

}
	}
}
