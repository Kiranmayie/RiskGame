package com.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import com.controller.StartGameController;

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




// TODO: Auto-generated Javadoc
/**
 * The Class PlayersAssignment.
 */
public class PlayersAssignment  extends Observable implements Observer, Serializable{
	
	/** The current contestant. */
	Contestant currentContestant;
	
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
	
	/** The trrtrs conquered. */
	private int trrtrsConquered;
	
	/** The contestant looper. */
	private Iterator<Contestant> contestantLooper;
	
	/** The pa. */
	PlayersAssignment pa;
	
	/** The chosen trrtry list. */
	List<Territories> chosenTrrtryList;
	
	/** The map. */
	private Map map;
	
	/** The cntnts. */
	private Continents cntnts;
	
	/** The trrtry. */
	private Territories trrtry;
	
	/** The selected territory list. */
	List<Territories> selectedTerritoryList = new ArrayList<>();;




/**
 * Players army assign.
 *
 * @param contestants the contestants
 * @return true, if successful
 */
public static boolean playersArmyAssign(List<Contestant> contestants) {
	//MapUtil.appendTextToGameConsole("===Assigning armies to players.===\n", textArea);
	boolean state = false;
	int currentArmSz = 0;
	int numberPlayers = contestants.size();
	System.out.println(numberPlayers);
	
 switch(numberPlayers)
 {
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
		state = true;
	}
	return state;
}

/**
 * Creates the contestant.
 *
 * @param noOfPlayer the no of player
 * @param contestants the contestants
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
 *
 * @param map the map
 * @param presentContestant the present contestant
 * @return the continents owned by player
 */
public List<Continents> getContinentsOwnedByPlayer(Map map, Contestant presentContestant) {
	List<Continents> cntnts = new ArrayList<>();

	for (Continents cntnt : map.getContinents()) {
		boolean contestantHasThisCntnt = true;
		for (Territories trrtr : cntnt.getTrrtrs()) {
			if (!trrtr.getContestant().equals(presentContestant)) {
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

/* (non-Javadoc)
 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
 */
public void update(Observable o, Object arg) {
	String view = (String) arg;

	if (view.equals("rollDiceComplete")) {
		//DiceModel diceModel = (DiceModel) o;
		//setTerritoryWon(diceModel.getNumOfTerritoriesWon());
		setChanged();
		notifyObservers("rollDiceComplete");
	}
}

/**
 * Value generator.
 *
 * @param num the num
 * @return the int
 */
public int valueGenerator(int num) {
	return (int) (Math.random() * num) + 0;
}

/**
 * Gets the current contestant.
 *
 * @return the current contestant
 */
public Contestant getCurrentContestant() {
	return currentContestant;
}

/**
 * Sets the current contestant.
 *
 * @param currentContestant the new current contestant
 */
public void setCurrentContestant(Contestant currentContestant) {
	this.currentContestant = currentContestant;
}

/**
 * Gets the territory won.
 *
 * @return the territory won
 */
public int getTerritoryWon() {
	return trrtrsConquered;
}

/**
 * Sets the territory conquered.
 *
 * @param trrtrsConquered the new territory conquered
 */
public void setTerritoryConquered(int trrtrsConquered) {
	this.trrtrsConquered = trrtrsConquered;
}

/**
 * Gets the contestants list.
 *
 * @return the contestants list
 */
public List<Contestant> getContestantsList() {
	return contestantsList;
}

/**
 * Sets the contestants list.
 *
 * @param contestantsList the new contestants list
 */
public void setContestantsList(List<Contestant> contestantsList) {
	this.contestantsList=contestantsList;
}


/**
 * Gets the reinforce batallion.
 *
 * @param map the map
 * @param currentContestant the current contestant
 * @return the reinforce batallion
 */
public Contestant getReinforceBatallion(Map map, Contestant currentContestant) {
	int presentBatallion = currentContestant.getBatallion();
	int trrtrSum = currentContestant.getcontestantTrrtrlist().size();
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
 *
 * @param attackTrrtsList the attack trrts list
 * @param defendTrrtrsList the defend trrtrs list
 */
public void attackPhase(ListView<Territories> attackTrrtsList, ListView<Territories> defendTrrtrsList)  {
	currentContestant.getGamePlan().attackPhase(attackTrrtsList, defendTrrtrsList,this );

	
}


/**
 * Place batallion.
 *
 * @param currentContestant the current contestant
 * @param selectedTerritoryList2 the selected territory list 2
 * @param contestants the contestants
 */
public void placeBatallion(Contestant currentContestant, List<Territories> selectedTerritoryList2, List<Contestant> contestants)
{
	if (currentContestant!= null)  {
		int contestantArmies = currentContestant.getBatallion();
		 System.out.println("Select the country on which you want to place your batallion");
		 Scanner sc=new Scanner(System.in);
		 String st=sc.nextLine();
			for(Territories terrtry:selectedTerritoryList2) {
				if (contestantArmies > 0) {
					if(st.equalsIgnoreCase(terrtry.getAssignName())) {
						System.out.println(terrtry.getAssignName()+terrtry.getBatallion());
			terrtry.setBatallion(terrtry.getBatallion() + 1);
			currentContestant.setBatallion(contestantArmies - 1);
			
		}
					}
				//System.out.println( terrtry.getAssignName() + ":-" + terrtry.getBatallion() + "-" + currentContestant.getContestantName());	
			}
	} else {
		contestantAssignmentToTerritories(currentContestant);
	}
		
	boolean batallionAttacked = isContestantBatallionattacked(contestantsList);
	if (batallionAttacked) {
		
		setChanged();
		notifyObservers("Attack First");
	} else {
		setChanged();
		notifyObservers("Place the Battalion");
	}
}
	

/**
 * Checks if is contestant batallionattacked.
 *
 * @param contestantList the contestant list
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
 *
 * @param currentContestant the current contestant
 */
private void contestantAssignmentToTerritories(Contestant currentContestant) {
	if (currentContestant.getBatallion() > 0) {
		Territories territory = currentContestant.getcontestantTrrtrlist()
				.get(anyNumber(currentContestant.getcontestantTrrtrlist().size() - 1));
		territory.setBatallion(territory.getBatallion() + 1);
		currentContestant.setBatallion(currentContestant.getBatallion() - 1);
	}
	
}

/**
 * Any number.
 *
 * @param i the i
 * @return the int
 */
private int anyNumber(int i) {
	return (int) (Math.random() * i) + 0;
	
}

/**
 * Reinforce phase.
 *
 * @param territoryList the territory list
 * @param territory the territory
 * @param gameConsole the game console
 */
public void reinforcePhase(ObservableList<Territories> territoryList, Territories territory, TextArea gameConsole) {
	//currentContestant.getStrategy().reinforcementPhase(territoryList, territory, gameConsole, currentContestant);
	// start attack phase
	if (currentContestant.getBatallion() <= 0 && contestantsList.size() > 1) {
		
		setChanged();
		notifyObservers("Attack");
	}
}

/**
 * Fortify phase.
 *
 * @param selectedTerritory the selected territory
 * @param adjTerritory the adj territory
 */
public void fortifyPhase(ListView<Territories> selectedTerritory, ListView<Territories> adjTerritory) {
	//boolean isFortificationDone = currentContestant.getStrategy().fortificationPhase(selectedTerritory, adjTerritory,
		//eConsole, currentContestant);

	boolean FortifySuccess = false;
	if (FortifySuccess && contestantsList.size() > 1) {
		setChanged();
		notifyObservers("Reinforce");
	}

}

/**
 * Fortify phase valid.
 *
 * @param map the map
 * @param currentContestant the current contestant
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
 * Checks if is contestant lost.
 *
 * @param currentContestant the current contestant
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
 *
 * @param currentContestant the current contestant
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
 *
 * @param enhancedmap the enhancedmap
 * @param contestants the contestants
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
	System.out.println(contestantsList);
	contestantLooper=contestants.iterator();
	System.out.println(contestantLooper);
	return contestants;
}

/**
 * Executing current contestant.
 *
 * @return the list
 */
public List<Territories> executingCurrentContestant() {
	if (!contestantLooper.hasNext()) {
		System.out.println(contestantLooper);
		contestantLooper = contestantsList.iterator();
	}
	//System.out.println(contestantLooper);
	
	Contestant newContestant = contestantLooper.next();
	
	//System.out.println(newContestant);
	//System.out.println(currentContestant);
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
	//getChosenTerritory(map,cntnts);
	for (Territories trrtry1 : currentContestant.getContestantTrrtrlist()) {
		//
		if(trrtry1.getBatallion()>=1) {
		
		
		 selectedTerritoryList.add(trrtry1);}
		System.out.println( trrtry1.getAssignName() + ":-" + trrtry1.getBatallion() + "-" + currentContestant.getContestantName());
	       System.out.println(trrtry1.getTouchingTrrtrs());
	      
	}
	System.out.println(selectedTerritoryList);
return selectedTerritoryList;
}
	

/**
 * Load batallion.
 *
 * @param selectedTerritoryList the selected territory list
 */
public void loadBatallion(List<Territories> selectedTerritoryList) {
	  System.out.println("Placing Batallion against each player");
	  
		placeBatallion(currentContestant, selectedTerritoryList, contestantsList);
		selectedTerritoryList.removeAll(selectedTerritoryList);
		executingCurrentContestant();
		
			loadBatallion(selectedTerritoryList);
		
		
		
}



	




}


	









