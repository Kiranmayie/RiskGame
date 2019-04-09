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
import com.units.Map;
import com.units.Territories;
import com.units.Contestant;
import Patterns.ContestantStrategies;
import Patterns.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class Aggressive.
 */
public class Aggressive implements ContestantStrategies{

	/** The contestants list. */
	public static List<Contestant> contestantsList;
	
	/** The ls new. */
	List<Territories> lsNew=null;
	
	/** The pa. */
	PlayersAssignment pa=new PlayersAssignment();
	
	/** The current contestant. */
	private Contestant  currentContestant;
	
	/** The cev. */
	private CardExchangeView cev;
	
	/** The attacking territories list. */
	private  List<Territories> attackingTerritoriesList;
	
	/** The defending territories list. */
	private  List<Territories> defendingTerritoriesList;
    
    /** The count. */
    int count;
	
	/** The territory. */
	public Territories territory;
	
	/** The map. */
	private Map map;

	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#loadBatallion(java.util.List, com.units.Contestant, java.util.List)
	 */
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


	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#reinforcementPhase(java.util.List, com.units.Contestant, com.units.Map)
	 */
	@Override
	public Territories reinforcementPhase(List<Territories> territoryList, Contestant currentContestant,Map map) {
		System.out.println(map.getContinents());
		currentContestant=pa.getReinforceBatallion(map, currentContestant);
		List<Territories> sortedList = getMaximumAdjacentAndArmy(territoryList);
		if (!sortedList.isEmpty()) {
			territory = sortedList.get(0);
			territory.setBatallion(territory.getBatallion() + currentContestant.getBatallion());
			currentContestant.setBatallion(0);
			
		}
		return territory;
	}

	/**
	 * Contestant has A valid attack move.
	 *
	 * @param territories the territories
	 * @return true, if successful
	 */
	public boolean contestantHasAValidAttackMove(List<Territories> territories) {
		List<Territories> defendingTerritories=new ArrayList<Territories>();
		territory = checkIfTerritoriesNull(territories);
		for(Territories territory:territories) {
		defendingTerritories = getDefendingTerritories(territory);}
		if (defendingTerritories.size() > 0 && territory.getBatallion() > 1) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the defending territories.
	 *
	 * @param territory2 the territory 2
	 * @return the defending territories
	 */
	private List<Territories> getDefendingTerritories(Territories territory2) {
		List<Territories> defendingTerritories = new ArrayList<Territories>();
		defendingTerritories.addAll(territory2.getTouchingTrrtrsExpand());
		
		return defendingTerritories;
	}


	/**
	 * Fortification phase.
	 *
	 * @param selectedTerritoriesList the selected territories list
	 * @param adjTerritoriesList the adj territories list
	 * @param currentContestant the current contestant
	 * @return true, if successful
	 */
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

	/**
	 * Attack.
	 *
	 * @param attacking the attacking
	 * @param defending the defending
	 * @param currentContestant the current contestant
	 * @param map the map
	 */
	private void attack(Territories attacking, Territories defending, Contestant currentContestant, Map map) {
		System.out.println(attacking.getAssignName() + attacking.getBatallion());
		System.out.println(defending.getAssignName() + defending.getBatallion());
		if(attacking.getBatallion()>=4 && defending.getBatallion()>=3)
		{
		Integer[] attackerdicevalues=pa.autoStartDiceRollattacker(3);
		Integer[] defenderdicevalues=pa.autoStartDiceRollDefender(2);
		if(attacking.getBatallion()==0) {
			fortificationPhase(attackingTerritoriesList, attackingTerritoriesList, currentContestant);
		}
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
		if(attackerdicevalues[i]>defenderdicevalues[j])
		{	System.out.println("Attacker won "+attackerdicevalues[i]+" is greater than "+defenderdicevalues[j]);
		currentContestant=pa.attackTerritory(attacking, defending,currentContestant);
					//System.out.println(count1);
					attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
					defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
								attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
					
			}
		else
		{
			System.out.println("Defender won "+attackerdicevalues[i]+" is less than "+defenderdicevalues[j]);
			currentContestant=pa.DefendTerritory(attacking, defending,currentContestant);
			attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			
						attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
		}
		
			}
		}
		}
		else if(attacking.getBatallion()>=4 && defending.getBatallion()<3)
		{	
		Integer[] attackerdicevalues1=pa.autoStartDiceRollattacker(3);
		Integer[] defenderdicevalues1=pa.autoStartDiceRollDefender(1);
		if(attacking.getBatallion()==0) {
			fortificationPhase(attackingTerritoriesList, attackingTerritoriesList, currentContestant);
		}
		for(int i=0;i<attacking.getBatallion()-1;i++) {
			for(int j=0;j<1;j++) {
		if(attackerdicevalues1[i]>defenderdicevalues1[j])
		{	System.out.println("Attacker won "+attackerdicevalues1[i]+" is greater than "+defenderdicevalues1[j]);
					currentContestant=pa.attackTerritory(attacking, defending,currentContestant);
					//System.out.println(count1);
					attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
					defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
								attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
					
			}
		else
		{
			System.out.println("Defender won "+attackerdicevalues1[i]+" is less than "+defenderdicevalues1[j]);
			currentContestant=pa.DefendTerritory(attacking, defending,currentContestant);
			attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			
						attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
		}
		
			}}
		}
		
		else if(attacking.getBatallion() < 4 && defending.getBatallion()<3)
		{	
		Integer[] attackerdicevalues1=pa.autoStartDiceRollattacker(attacking.getBatallion()-1);
		Integer[] defenderdicevalues1=pa.autoStartDiceRollDefender(1);
		if(attacking.getBatallion()==0) {
			fortificationPhase(attackingTerritoriesList, attackingTerritoriesList, currentContestant);
		}
		
		for(int i=0;i<attacking.getBatallion()-1;i++) {
			for(int j=0;j<1;j++) {
		if(attackerdicevalues1[i]>defenderdicevalues1[j])
		{	System.out.println("Attacker won "+attackerdicevalues1[i]+" is greater than "+defenderdicevalues1[j]);
		currentContestant=pa.attackTerritory(attacking, defending,currentContestant);
					//System.out.println(count1);
					attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
					defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
								attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
					
			}
		else
		{
			System.out.println("Defender won "+attackerdicevalues1[i]+" is less than "+defenderdicevalues1[j]);
			currentContestant=pa.DefendTerritory(attacking, defending,currentContestant);
			attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			
						attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
		}
		
			}
			}
	}

		else if(attacking.getBatallion() ==3 && defending.getBatallion()==3)
		{	
		Integer[] attackerdicevalues1=pa.autoStartDiceRollattacker(2);
		Integer[] defenderdicevalues1=pa.autoStartDiceRollDefender(2);
		if(attacking.getBatallion()==0) {
			fortificationPhase(attackingTerritoriesList, attackingTerritoriesList, currentContestant);
		}
		for(int i=0;i<attacking.getBatallion()-1;i++) {
			for(int j=0;j<1;j++) {
		if(attackerdicevalues1[i]>defenderdicevalues1[j])
		{	System.out.println("Attacker won "+attackerdicevalues1[i]+" is greater than "+defenderdicevalues1[j]);
		currentContestant=pa.attackTerritory(attacking, defending,currentContestant);
					//System.out.println(count1);
					attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
					defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
								attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
					
			}
		else
		{
			System.out.println("Defender won "+attackerdicevalues1[i]+" is less than "+defenderdicevalues1[j]);
			currentContestant=pa.DefendTerritory(attacking, defending,currentContestant);
			attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			
						attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
		}
		
			}}
		}
		
		else if(attacking.getBatallion() ==3 && defending.getBatallion()>=4)
		{	
		Integer[] attackerdicevalues1=pa.autoStartDiceRollattacker(2);
		Integer[] defenderdicevalues1=pa.autoStartDiceRollDefender(2);
		 if(attacking.getBatallion()==0) {
			fortificationPhase(attackingTerritoriesList, attackingTerritoriesList, currentContestant);
		}
		
		for(int i=0;i<attacking.getBatallion()-1;i++) {
			for(int j=0;j<1;j++) {
		if(attackerdicevalues1[i]>defenderdicevalues1[j])
		{	System.out.println("Attacker won "+attackerdicevalues1[i]+" is greater than "+defenderdicevalues1[j]);
					currentContestant=pa.attackTerritory(attacking, defending,currentContestant);
					System.out.println(currentContestant.getContestantName());
					attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
					defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
								attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
					
			}
		else
		{
			System.out.println("Defender won "+attackerdicevalues1[i]+" is less than "+defenderdicevalues1[j]);
			currentContestant=pa.DefendTerritory(attacking, defending,currentContestant);
			attackingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			defendingTerritoriesList=currentContestant.getcontestantTrrtrlist();
			
						attackPhase(attackingTerritoriesList, defendingTerritoriesList,currentContestant,map);
		}
		
			}}
		}
		}
		


/* (non-Javadoc)
 * @see Patterns.ContestantStrategies#attackPhase(java.util.List, java.util.List, com.units.Contestant, com.units.Map)
 */
@Override
	public void attackPhase(List<Territories> attackingTerritoriesList, List<Territories> defendingTerritoriesList,Contestant currentContestant,Map map) {
		System.out.println("Attack Phase Started:Aggressive");
		System.out.println(attackingTerritoriesList);
		//System.out.println(map.getContinents());
		territory = checkIfTerritoriesNull(attackingTerritoriesList);
			if(territory!=null) {
			
		System.out.println(territory.getAssignName());
		System.out.println(getDefendingTerritories(territory));
		//if(count == 0) {
		//for(Territories trrtr: attackingTerritoriesList) {
			List<Territories> ls = new ArrayList<Territories>();
			 lsNew = new ArrayList<Territories>();
			List<Territories> lsNewNew = new ArrayList<Territories>();
			ls.addAll(getDefendingTerritories(territory));
			lsNew.addAll(getDefendingTerritories(territory));
			
			for(int i=0;i<ls.size();i++) {
				
				if(ls.get(i).getContestant().getContestantName().equals(currentContestant.getContestantName())) {
					lsNewNew.add(ls.get(i));
					
				
			}
			for(Territories trr:lsNewNew) {
			lsNew.remove(trr);
			}
			
			System.out.println("Territory "+territory+" can attack the following territories:- ");
			System.out.println(lsNew);
			//count=1;
			}
		Iterator<Territories> defendingTerritoriesIterator = lsNew.iterator();
		while (defendingTerritoriesIterator.hasNext()) {
			System.out.println(lsNew);
			if (territory.getBatallion() >1) {
				System.out.println(territory.getBatallion());
				attack(territory, defendingTerritoriesIterator.next(),currentContestant,map);
				break;
			}
			else
			{
				System.out.println(map.getContinents());
				System.out.println("Reinforcement Phase started");
				territory=reinforcementPhase(attackingTerritoriesList, currentContestant, map);
				System.out.println(territory.getAssignName());
				attackPhase(attackingTerritoriesList, defendingTerritoriesList, currentContestant,map);
			}
			
		}
			}
			else 
			{
				System.out.println("Won");
				
			}
			
		

	}

	/**
	 * Check if territories null.
	 *
	 * @param attackingTerritoriesList the attacking territories list
	 * @return the territories
	 */
	public Territories checkIfTerritoriesNull(List<Territories> attackingTerritoriesList) {
		if (territory == null || (territory.getBatallion() <= 1 || getDefendingTerritories(territory).size() == 0)) {
			List<Territories> sortedList = getMaximumAdjacentAndArmy(attackingTerritoriesList);
			System.out.println(sortedList);
			for (Territories t : sortedList) {
				if (t.getBatallion() > 1) {
					territory = t;
					break;
				}
				
				
			}
		}
		return territory;

	}

	/**
	 * Gets the territories owned by current contestant.
	 *
	 * @param territory the territory
	 * @return the territories owned by current contestant
	 */
	public List<Territories> getTerritoriesOwnedByCurrentContestant(Territories territory) {
		List<Territories> listWithValidAdjacentTerriroty = territory.getTouchingTrrtrsExpand().stream().
				filter(t -> (territory.getContestant().equals(t.getContestant()))).collect(Collectors.toList());
		return listWithValidAdjacentTerriroty;
	}

	/**
	 * Gets the maximum adjacent and army.
	 *
	 * @param list the list
	 * @return the maximum adjacent and army
	 */
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
