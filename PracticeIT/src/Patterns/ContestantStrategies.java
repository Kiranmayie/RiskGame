package Patterns;

import java.util.List;

import com.units.Contestant;
import com.units.Territories;

public interface ContestantStrategies {

	public void loadBatallion(List<Territories> selectedTerritoryList,Contestant currentContestant,List<Contestant> Contestants);

	void registerObserver(Observer o);

	void notifyObservers(String obj);

	void removeObserver(Observer o);

	public void attackPhase(List<Territories> getcontestantTrrtrlist, List<Territories> getcontestantTrrtrlist2,
			Contestant currentContestant);
}
