package com.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.controller.StartGameController;

import com.sun.xml.internal.bind.v2.runtime.Name;
import com.units.Contestant;
import com.units.Territories;
import com.units.Continents;
import com.units.Map;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
/**
 * The Class PlayersAssignment.
 */
public class PlayersAssignment  extends Observable implements Observer, Serializable{
	/** The current contestant. */
	Contestant currentContestant;
	
	/** The Constant TWO_PLAYER_ARMIES. */
	public static final Integer TWO_PLAYER_ARMIES = 8;
	
	/** The Constant THREE_PLAYER_ARMIES. */
	public static final Integer THREE_PLAYER_ARMIES = 35;
	
	/** The Constant FOUR_PLAYER_ARMIES. */

	public static final Integer FOUR_PLAYER_ARMIES = 30;
	
	/** The Constant FIVE_PLAYER_ARMIES. */
	public static final Integer FIVE_PLAYER_ARMIES = 25;
	
	/** The Constant SIX_PLAYER_ARMIES. */
	public static final Integer SIX_PLAYER_ARMIES = 20;
	
	/** The contestants list. */
	List<Contestant> contestantsList=new ArrayList<>();
	
	/** The Territories conquered. */
	private int trrtrsConquered;
	
	/** The contestant looper. */
	private Iterator<Contestant> contestantLooper;
	
	/** The PlayerAssignment Object. */
	PlayersAssignment pa;
	
	/** The chosen Territories list. */
	List<Territories> chosenTrrtryList;
	
	/** The map. */
	private Map map;
	
	/** The Continents. */
	private Continents cntnts;
	
	/** The Territories. */
	private Territories trrtry;
	
	/** The selected territory list. */
	List<Territories> selectedTerritoryList = new ArrayList<>();





/**
 * Players army assign.
 * @param contestants 
 * @return true, if successful
 */
public static void playersArmyAssign(List<Contestant> contestants) {
	//boolean state = false;
	int currentArmSz = 0;
	int numberPlayers = contestants.size();
	System.out.println(numberPlayers);
	switch(numberPlayers)
 {
	
 case 2:
		currentArmSz = TWO_PLAYER_ARMIES;
		break;
 case 3:
		currentArmSz = THREE_PLAYER_ARMIES;
		break;
 case 4:		
	 currentArmSz = FOUR_PLAYER_ARMIES;
	 break;
 case 5:
		currentArmSz = FIVE_PLAYER_ARMIES;
		break;
 case 6:
		currentArmSz = SIX_PLAYER_ARMIES;
		break;
 }
 	for (Contestant contestant : contestants) {
		contestant.setBatallion(currentArmSz);
		System.out.println(contestant.getContestantName() + " assigned: " + currentArmSz + "\n");
		//state = true;
	}
	
}

/**
 * Creates the contestant.
 * @param noOfPlayer 
 * @param contestants 
 * @return the list
 */
public List<Contestant> createContestant(int noOfPlayer, List<Contestant> contestants) {
	for (int i = 0; i < noOfPlayer; i++) {
		System.out.println("Enter players name of " + (i+1));
		Scanner sc=new Scanner(System.in);
		contestants.add(new Contestant(i));
		String name=sc.nextLine();
		contestants.get(i).setContestantName(name);
		System.out.println(contestants.get(i).getContestantName() + " created!\n");
	}
	return contestants;
}


/**
 * Gets the continents owned by player.
 * @param map 
 * @param presentContestant 
 * @return the continents owned by player
 */


public List<Continents> getContinentsOwnedByPlayer(Map map, Contestant currentContestant) {

	List<Continents> cntnts = new ArrayList<>();
    //System.out.println( map.getContinents());
	for (Continents cntnt : map.getContinents()) {
		boolean contestantHasThisCntnt = true;
		for (Territories trrtr : cntnt.getTrrtrs()) {
			if (!trrtr.getContestant().equals(currentContestant)) {
				contestantHasThisCntnt = false;
				break;
			}
		}
		if (contestantHasThisCntnt) {
			cntnts.add(cntnt);
		}
	}

	return cntnts;
}


/**
 * Value generator.
 * @param num 
 * @return the int
 */
public int valueGenerator(int num) {
	return (int) (Math.random() * num) + 0;
}

/**
 * Gets the current contestant.
 * @return the current contestant
 */
public Contestant getCurrentContestant() {
	return currentContestant;
}

/**
 * Sets the current contestant.
 * @param currentContestant 
 */
public void setCurrentContestant(Contestant currentContestant) {
	this.currentContestant = currentContestant;
}

/**
 * Gets the territory won.
 * @return the territory won
 */
public int getTerritoryWon() {
	return trrtrsConquered;
}

/**
 * Sets the territory conquered.
 * @param trrtrsConquered 
 */
public void setTerritoryConquered(int trrtrsConquered) {
	this.trrtrsConquered = trrtrsConquered;
}

/**
 * Gets the contestants list.
 * @return the contestants list
 */
public List<Contestant> getContestantsList() {
	return contestantsList;
}

/**
 * Sets the contestants list.
 * @param contestantsList 
 */
public void setContestantsList(List<Contestant> contestantsList) {
	this.contestantsList=contestantsList;
}

/**
 * Gets the reinforce batallion.
 * @param map 
 * @param currentContestant 
 * @return the reinforce batallion
 */
public Contestant getReinforceBatallion(Map map, Contestant currentContestant) {
	int presentBatallion = currentContestant.getBatallion();
	int trrtrSum =  currentContestant.getcontestantTrrtrlist().size();
	if (trrtrSum < 9) {
		presentBatallion = presentBatallion + 3;
	} else {
		presentBatallion = presentBatallion + (trrtrSum / 3);
	}

	List<Continents> continents = getContinentsOwnedByPlayer(map, currentContestant);
	if (continents.size() > 0) {
		for (Continents continent : continents) {
			presentBatallion = presentBatallion + Integer.parseInt(continent.getCValue());
		}
	}
	currentContestant.setBatallion(presentBatallion);

	return currentContestant;
}



/**
 * Attack phase.
 * @param attackTrrtsList 
 * @param defendTrrtrsList
 */
public void attackPhase(List<Territories> attackTrrtsList, List<Territories> defendTrrtrsList)  {
	System.out.println("Select the attacking territory, defending territory and player you want to attack");
	System.out.println(attackTrrtsList);
	
	Scanner sa=new Scanner(System.in);
	String attackingTerritory=sa.nextLine();
	String beingAttackedTerritory=sa.nextLine();
	String Contestant=sa.nextLine();
	
	for (Territories territory : attackTrrtsList) {
		System.out.println(getDefendingTerritory(territory));
		System.out.println(territory.getBatallion());
		System.out.println(getDefendingTerritory(territory).size());
		System.out.println(territory.getAssignName());
		if (territory.getAssignName().equals(attackingTerritory ))//&& territory.getBatallion() > 1 && getDefendingTerritory(territory).size() > 0) 
		{ 
			  int n=territory.getBatallion();
		
				System.out.println("Attacker's Turn");
				int[] attackerDiceValue=rollDice(n);
				int m=defendingDiceCalculation(getDefendingTerritory(territory), beingAttackedTerritory);
				System.out.println("Defender's Turn");
				int[] defenderDiceValue=rollDice(m);
				for(int i=0;i<attackerDiceValue.length;i++) {
					for(int j=0;j<defenderDiceValue.length;j++) {
						if(attackerDiceValue[i]>defenderDiceValue[j]) {
							attackTerritory(defendTrrtrsList, attackTrrtsList, beingAttackedTerritory,Contestant);
							placeBatallion(currentContestant, attackTrrtsList, contestantsList);
						}
						}
							
					}
				
				
				
			}
		}
	}					
private int defendingDiceCalculation(List<Territories> defendTrrtrsList,String beingAttackedTerritory) {
	// TODO Auto-generated method stub
	int m=0;
	for (Territories territory : defendTrrtrsList) {
		if (territory.getAssignName().equals(beingAttackedTerritory) && territory.getBatallion() > 1) 
		
			{   m=territory.getBatallion();
			
			}}
		System.out.println(m);
	return m;
}

private void attackTerritory(List<Territories> attackTrrtsList,List<Territories> defendTrrtrsList,String beingAttackedTerritory, String contestant) {
	for (Territories territory : defendTrrtrsList) {
		if(territory.getAssignName()==beingAttackedTerritory)
		{
			//currentterritory.getBatallion()
		}
	}
	
}

private int[] rollDice(int n) {
	int m = 0;	
	int[] DiceResult = null;
	Scanner sc=new Scanner(System.in);
	switch(n)
	{
	case 2: System.out.println("Roll the dice you can only roll maximum of " +(n-1)+"dice hence you cannot choose");
	DiceResult=anyNumber(1);
	break;
	
	case 3:System.out.println("Roll the dice you can only roll maximum of " +(n-1)+"dice. So How many you want to choose");
	m=sc.nextInt();
	DiceResult=anyNumber(m);
	break;
	
	case 4:System.out.println("Roll the dice you can only roll maximum of " +(n-1)+"dice. So How many you want to choose");
	m=sc.nextInt();
	DiceResult=anyNumber(m);
	break;
	}
	
	return DiceResult;
}

private List<Territories> getDefendingTerritory(Territories territory) {
	// TODO Auto-generated method stub
	List<Territories> defendingTerritories = territory.getTouchingTrrtrsExpand();
	return defendingTerritories;
}
private int[] anyNumber(int i) {
	
	int allresultOfDice[] = new int[i];
	for(int j=0;j<i;j++) {
	allresultOfDice[j] = (int) (Math.random() * i);
	}
	
	return allresultOfDice;
	
}


/**
 * Place batallion.
 * @param currentContestant 
 * @param selectedTerritoryList2 
 * @param contestants the contestants
 */
public void placeBatallion(Contestant currentContestant, List<Territories> selectedTerritoryList2, List<Contestant> contestants)
{
	int contestantArmies = currentContestant.getBatallion();
	if (currentContestant!= null && contestantArmies > 0)  {
		 System.out.println("Select the country on which you want to place your batallion");
		 Scanner sc=new Scanner(System.in);
		 String st=sc.nextLine();
			for(Territories terrtry:selectedTerritoryList2) {
					//System.out.println(terrtry.getAssignName()+terrtry.getBatallion());
					if(st.equalsIgnoreCase(terrtry.getAssignName())) {
						//System.out.println(terrtry.getAssignName());
			terrtry.setBatallion(terrtry.getBatallion() + 1);
			currentContestant.setBatallion(contestantArmies - 1);
			break;
		}
				
					}
				
	} else {
		System.out.println("Moving on to attack face and forward...Awaiting implementation strategies.");
		//getReinforceBatallion();
		}
}
		

/**
 * Checks if is contestant batallionattacked.
 * @param contestantList 
 * @return true, if is contestant batallionattacked
 */
private boolean isContestantBatallionattacked(List<Contestant> contestantList) {
	int count = 0;

	for (Contestant contestant : contestantList) {
		if (contestant.getBatallion() == 0) {
			count++;
		}
	}
	if (count == contestantList.size()) {
		return true;
	} else {
		return false;
	}
	
}

/**
 * Contestant assignment to territories.
 * @param currentContestant 
 */
private void contestantAssignmentToTerritories(Contestant currentContestant) {
	if (currentContestant.getBatallion() > 0) {
		Territories territory = currentContestant.getcontestantTrrtrlist()
				.get(anynumber(currentContestant.getcontestantTrrtrlist().size() - 1));
		territory.setBatallion(territory.getBatallion() + 1);
		currentContestant.setBatallion(currentContestant.getBatallion() - 1);
	}
	
}

private int anynumber(int i) {
	// TODO Auto-generated method stub
	return (int) ((Math.random() * i)+0) ;
}

/**
 * Any number.
 * @param i 
 * @return the int
 */


/**
 * Reinforce phase.
 * @param territoryList 
 * @param territory 
 * @param gameConsole 
 */
public void reinforcePhase(ObservableList<Territories> territoryList, Territories territory, TextArea gameConsole) {
	if (currentContestant.getBatallion() <= 0 && contestantsList.size() > 1) {
		setChanged();
		notifyObservers("Attack");
	}
}

/**
 * Fortify phase.
 * @param selectedTerritory 
 * @param adjTerritory 
 */
public void fortifyPhase(ListView<Territories> selectedTerritory, ListView<Territories> adjTerritory) {
		boolean FortifySuccess = false;
	if (FortifySuccess && contestantsList.size() > 1) {
		setChanged();
		notifyObservers("Reinforce");
	}

}

/**
 * Fortify phase valid.
 * @param map 
 * @param currentContestant 
 * @return true, if successful
 */
public boolean FortifyPhaseValid(Map map, Contestant currentContestant) {
	boolean isFortifyPossible=false;
	//boolean isFortificationAvaialble = currentContestant.getStrategy().isFortificationPhaseValid(map, playerPlaying);
	if (isFortifyPossible) {
		setChanged();
		notifyObservers("Fortify");
	} else {
		setChanged();
		notifyObservers("Dont fortify");
	}
	return isFortifyPossible;
}

/**
 * Checks if its contestant lost.
 * @param currentContestant 
 * @return the contestant
 */
public Contestant isContestantLost(List<Contestant> currentContestant) {
	Contestant contestantLost = null;
	for (Contestant contestant : currentContestant) {
		if (contestant.getcontestantTrrtrlist().isEmpty()) {
			contestantLost = contestant;
			//contestant.getContestantCards().addAll(contestantLost.getContestantCards());
		}
	}
	return contestantLost;
}

/**
 * Checks if is contestant won.
 * @param currentContestant 
 * @return true, if is contestant won
 */
public boolean isContestantWon(List<Contestant> currentContestant) {
	boolean ContestantWon = false;
	if (currentContestant.size() == 1) {
		ContestantWon = true;
	}
	return ContestantWon;
}

/**
 * Territory assign to contestant.
 * @param enhancedmap 
 * @param contestants 
 * @return the list
 */
public List<Contestant> territoryAssignToContestant(Map enhancedmap, List<Contestant> contestants) {
	System.out.println("Territory Assignment to the Contestants");
	List<Territories> trrtrs = new ArrayList<>();
	if (enhancedmap.getContinents() != null) {
		for (Continents cntnt : enhancedmap.getContinents()) {
			if (cntnt != null && cntnt.getTrrtrs() != null) {
				for (Territories trrtry : cntnt.getTrrtrs()) {
					trrtrs.add(trrtry);
				}
			}
		}
	}
	int count = 0;
	int totalTerritory = trrtrs.size();
	while (count < totalTerritory) {
		for (Contestant contestant : contestants) {
			for (Territories trrtry : trrtrs) {
				if (trrtry.getContestant() == null) {
					count++;
					trrtry.setContestant(contestant);
					trrtry.setBatallion(trrtry.getBatallion() + 1);
					contestant.setBatallion(contestant.getBatallion() - 1);
					contestant.getcontestantTrrtrlist().add(trrtry);
					System.out.println(trrtry.getAssignName() + " assigned to " + contestant.getContestantName());
							
					break;
				}
				continue;
			}
		}
	}
	contestantsList.addAll(contestants);
	//System.out.println(contestantsList);
	contestantLooper=contestants.iterator();
	//System.out.println(contestantLooper);
	return contestants;
}

/**
 * Executing current contestant.
 *
 * @return the list
 */
public List<Territories> executingCurrentContestant() {
	
	if (!contestantLooper.hasNext()) {
		//System.out.println(contestantLooper);
		contestantLooper = contestantsList.iterator();
	}
	Contestant newContestant = contestantLooper.next();
	if (newContestant.equals(currentContestant)) {
		System.out.println("Second if loop");
		if (contestantLooper.hasNext()) {
			newContestant = contestantLooper.next();
		}
	}
	currentContestant = newContestant;
	setCurrentContestant(currentContestant);
	setContestantsList(contestantsList);
	setTerritoryConquered(0);
	
	System.out.println(currentContestant.getContestantName() + "!....started playing.\n");
	System.out.println(currentContestant.getContestantName() + currentContestant.getBatallion() + " Batallion left.\n");
	for (Territories trrtry1 : currentContestant.getContestantTrrtrlist()) {
		if(trrtry1.getBatallion()>=1) {
		 selectedTerritoryList.add(trrtry1);
		 }
		System.out.println( trrtry1.getAssignName() + ":-" + trrtry1.getBatallion() + "-" + currentContestant.getContestantName());
	       System.out.println(trrtry1.getTouchingTrrtrs());
	}
	System.out.println(selectedTerritoryList);
return selectedTerritoryList;
}
	

/**
 * Load batallion.
 * @param selectedTerritoryList 
 */
public void loadBatallion(List<Territories> selectedTerritoryList) {
	  System.out.println("Placing Batallion against each player");
	  placeBatallion(currentContestant, selectedTerritoryList, contestantsList);
		selectedTerritoryList.removeAll(selectedTerritoryList);
		executingCurrentContestant();
		for(Contestant contestant:contestantsList) {
		if(contestant.getBatallion()!=0) {
			loadBatallion(selectedTerritoryList);
		}
		
		
}
	
	
	
}



@Override
public void update(Observable arg0, Object arg1) {
	// TODO Auto-generated method stub
	
}}


