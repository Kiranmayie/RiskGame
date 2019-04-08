package ConcretePatterns;

import java.util.List;

import com.units.Contestant;
import com.units.Territories;

import Patterns.ContestantStrategies;
import Patterns.Observer;

public class Cheater implements ContestantStrategies{

	@Override
	public void loadBatallion(List<Territories> selectedTerritoryList, Contestant currentContestant,
			List<Contestant> Contestants) {
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

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers(String obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attackPhase(List<Territories> getcontestantTrrtrlist, List<Territories> getcontestantTrrtrlist2,
			Contestant currentContestant) {
		// TODO Auto-generated method stub
		
	}

}
