package com.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


import com.controller.StartGameController;
import com.main.CardExchangeView;
import com.main.PhaseView;

import com.sun.xml.internal.bind.v2.runtime.Name;
import com.units.Contestant;
import com.units.Territories;

import Patterns.Observable;
import Patterns.Observer;


import com.units.Continents;
import com.units.Map;
//
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
public class PlayersAssignment  implements  Serializable,Observable{
	/** The current contestant. */
	Contestant currentContestant;
	
	/** The timer. */
	int timer=1;
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

	/** The Constant INFANTRY. */
	private static final String INFANTRY = null;

	/** The Constant CAVALRY. */
	private static final String CAVALRY = null;

	/** The Constant ARTILLERY. */
	private static final String ARTILLERY = null;
	
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
	
	/** The contestant trrtrlist. */
	private List<Territories> contestantTrrtrlist;
	
	/** The m 1. */
	static int m1 = 0;	
	
	/** The m 2. */
	static int m2 = 0;	
	
	/** The observers. */
	public ArrayList<Observer> observers = new ArrayList<Observer>();
	
	/** The changed. */
	public boolean changed;
	
	/** The cev. */
	CardExchangeView cev;
	
	/** The Card count attacker. */
	public static int CardCountAttacker;
	
	/** The Card count defender. */
	public static int CardCountDefender;
	
	/** The cardtype attacker. */
	public static List<String> cardtypeAttacker=new ArrayList<String> ();
	
	/** The cardtype defender. */
	public static List<String> cardtypeDefender=new ArrayList<String> ();

/**
 * Players army assign.
 *
 * @param contestants the contestants
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
 *
 * @param noOfPlayer the no of player
 * @param contestants the contestants
 * @return the list
 */
public List<Contestant> createContestant(int noOfPlayer, List<Contestant> contestants) {
	for (int i = 0; i < noOfPlayer; i++) {
		System.out.println("Select The Contestant Type from the below \n 1.HUMAN \n 2.AGGRESSIVE \n 3.BENEVOLENT \n 4.RANDOM \n 5.CHEATER");
		System.out.println("Enter Contestants name of " + (i+1)+" and strategy to be considered.");
		Scanner sc=new Scanner(System.in);
		contestants.add(new Contestant(i));
		String name=sc.nextLine();
		String strategyName=sc.nextLine();
		contestants.get(i).setContestantName(name);
		contestants.get(i).setContestantStrategy(strategyName);
		System.out.println(contestants.get(i).getContestantName() + " is created with the strategy "+contestants.get(i).getContestantStrategy()+"\n");

	}
	return contestants;
}



/**
 * Gets the continents owned by player.
 *
 * @param map the map
 * @param currentContestant the current contestant
 * @return the continents owned by player
 */


public static List<Continents> getContinentsOwnedByPlayer(Map map, Contestant currentContestant) {

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
 *
 * @param num the num
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
 *
 * @param currentContestant the new current contestant
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
 *
 * @param trrtrsConquered the new territory conquered
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
 *
 * @param contestantsList the new contestants list
 */
public void setContestantsList(List<Contestant> contestantsList) {
	this.contestantsList=contestantsList;
}


	
				




/**
 * Check if players armies exhausted.
 *
 * @param players the players
 * @return true, if successful
 */
public boolean checkIfPlayersArmiesExhausted(List<Contestant> players) {
	
	
	int count = 0;

	for (Contestant player : players) {
		if (player.getBatallion() == 0) {
			count++;
		}
	}
	if (count == players.size()) {
		return true;
	} else {
		return false;
	}
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
				.get(anynumber(currentContestant.getcontestantTrrtrlist().size() - 1));
		territory.setBatallion(territory.getBatallion() + 1);
		currentContestant.setBatallion(currentContestant.getBatallion() - 1);
	}
	
}

/**
 * Anynumber.
 *
 * @param i the i
 * @return the int
 */
private int anynumber(int i) {
	// TODO Auto-generated method stub
	return (int) ((Math.random() * i)+0) ;
}


 /**
  * Checks if is fortification phase valid.
  *
  * @param map the map
  * @param pPlay the play
  * @return true, if is fortification phase valid
  */
 public boolean isFortificationPhaseValid(Map map, Contestant pPlay) {
	boolean isFortificationAvaialble = pPlay.getGamePlan().isFortificationPhaseValid(map, pPlay);
	if (isFortificationAvaialble) {
	//	setChanged();
	//	notifyObservers("Fortification");
	} else {
	//	setChanged();
	//	notifyObservers("noFortificationMove");
	}
	return isFortificationAvaialble;
}


/**
 * Fortify phase.
 *
 * @param selectedTerritory the selected territory
 * @param adjTerritory the adj territory
 */
public void fortifyPhase(ListView<Territories> selectedTerritory, ListView<Territories> adjTerritory) {
		boolean FortifySuccess = false;
	if (FortifySuccess && contestantsList.size() > 1) {
	
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
	//	setChanged();
	//	notifyObservers("Fortify");
	} else {
	//	setChanged();
	//	notifyObservers("Dont fortify");
	}
	return isFortifyPossible;
}

/**
 * Checks if its contestant lost.
 *
 * @param currentContestant the current contestant
 * @return the contestant
 */
public Contestant isContestantLost(List<Contestant> currentContestant) {
	Contestant contestantLost = null;
	for (Contestant contestant : currentContestant) {
		if (contestant.getcontestantTrrtrlist().isEmpty()) {
			contestantLost = contestant;
			
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
	contestantLooper=contestants.iterator();

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
 *
 * @param selectedTerritoryList the selected territory list
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

/**
 * Contestant has A valid attack move.
 *
 * @param territory1 the territory 1
 * @return true, if successful
 */
public boolean contestantHasAValidAttackMove(Territories territory1) {
	boolean hasAValidMove = false;
	
		if (territory1.getBatallion() > 1) {
			hasAValidMove = true;
		
	}
	if (!hasAValidMove) {
		System.out.println("No valid attack move avialble move to Fortification phase");

	}
	return hasAValidMove;
}



/* (non-Javadoc)
 * @see Patterns.Observable#removeObserver(Patterns.Observer)
 */
@Override
public void removeObserver(Observer o) {
	int i = observers.indexOf(o);
	if(i>=0) {
	observers.remove(i);
	}
}

/* (non-Javadoc)
 * @see Patterns.Observable#notifyObservers(java.lang.String)
 */
@Override
public void notifyObservers(String obj) {
	if(changed) {
		//System.out.println(supLocal.observers);
	//	System.out.println(supLocal.observers.get(0));
	for(Observer obs:pa.observers) {
	
		obs.update(obj);
	}}
	
}

/**
 * Notify observers.
 */
public void notifyObservers() {
	if(changed) {
		cev.update("Card Given");
		
	}
}


/**
 * Something changed.
 */
public void somethingChanged() {
	changed=true;
}

/* (non-Javadoc)
 * @see Patterns.Observable#registerObserver(Patterns.Observer)
 */
@Override
public void registerObserver(Observer o) {
	observers.add(o);
	//System.out.println(observers.get(0));
	//System.out.println(observers.size());
}

/**
 * Auto start dice rollattacker.
 *
 * @param i the i
 * @return the integer[]
 */
public Integer[] autoStartDiceRollattacker(int i) {
	Integer[]attackerdicevalues=attackerdicevalues(i);
	
	return(attackerdicevalues);
	
	}

/**
 * Auto start dice roll defender.
 *
 * @param j the j
 * @return the integer[]
 */
public Integer[] autoStartDiceRollDefender(int j) {
	Integer[] defenderdicevalues=defenderdicevalues(j);
	return(defenderdicevalues);
}

/**
 * Defenderdicevalues.
 *
 * @param j the j
 * @return the integer[]
 */
private Integer[] defenderdicevalues(int j) {
	int max = 6; 
    int min = 1;
    int range = max - min + 1; 
    Integer allresultOfDice[] = new Integer[j];
	for(int f=0;f<j;f++) {
	allresultOfDice[f] = (int) (Math.random()* range) + min; 
	System.out.println("Defender Dice values " +allresultOfDice[f]);
	}
	Arrays.sort(allresultOfDice, Collections.reverseOrder());
	return allresultOfDice;
}

/**
 * Attackerdicevalues.
 *
 * @param i the i
 * @return the integer[]
 */
private Integer[] attackerdicevalues(int i) {
	int max = 6; 
    int min = 1;
    int range = max - min + 1; 
	Integer allresultOfDice[] = new Integer[i];
	for(int j=0;j<i;j++) {
	allresultOfDice[j] = (int) (Math.random()* range) + min; 
	System.out.println("Attacker Dice values " +allresultOfDice[j]);
	}
	Arrays.sort(allresultOfDice, Collections.reverseOrder());
	return allresultOfDice;
	
}

/**
 * Gets the defending territory.
 *
 * @param territory the territory
 * @return the defending territory
 */
public List<Territories> getDefendingTerritory(Territories territory) {
	List<Territories> defendingTerritories = new ArrayList<Territories>();
	defendingTerritories.addAll(territory.getTouchingTrrtrsExpand());
	return defendingTerritories;
}

/**
 * Attack territory.
 *
 * @param attacking the attacking
 * @param defending the defending
 * @param currentContestant the current contestant
 * @return the contestant
 */
public Contestant attackTerritory(Territories attacking,Territories defending,Contestant currentContestant) {

	pa = new PlayersAssignment();
	cev=new CardExchangeView(pa);
				
				int defenderBatallionleft=defending.getBatallion();
				defenderBatallionleft=defenderBatallionleft-1;
				defending.setBatallion(defenderBatallionleft);
				Contestant defContestant=defending.getContestant();
				System.out.println(currentContestant.getContestantName());
				//System.out.println("The Defender batallion left after attack are "+ territory.getBatallion() + " for the territory " +territory.getAssignName());
				int currentBatallion=currentContestant.getBatallion();
				//System.out.println("The Attacker before attack has "+currentContestant.getBatallion()+" Batallion left");
				//currentBatallion=currentBatallion+1;
				int defenderBatallion=defContestant.getBatallion();
				defenderBatallion=defenderBatallion-1;
				defContestant.setBatallion(defenderBatallion);
				
				CardCountAttacker++;
				currentContestant.setCardsInPocket(CardCountAttacker);
				
				 cardtypeAttacker.add(Cards.selectCards());
				 CardCountAttacker = currentContestant.getCardsInPocket();
				//System.out.println("The player has been assigned "+currentContestant.getCardsInPocket()+" of type " +PlayersAssignment.cardtypeAttacker);
				
				
				 somethingChanged();
					 notifyObservers();
					 
				if(validTrade(cardtypeAttacker)) 
				{	System.out.println("Reinforce is possible");
				currentContestant = reinforceWithCards(CardCountAttacker,currentContestant);
				}		
				if(defending.getBatallion()==0)
				{
					System.out.println("the Attacker has conquored the territory " +defending.getAssignName());
					m1=attacking.getBatallion()-1;
					attacking.setBatallion(1);
					defending.setBatallion(m1);
					System.out.println("Before Conquering");
					contestantTrrtrlist=currentContestant.getContestantTrrtrlist();
					System.out.println(contestantTrrtrlist);
					System.out.println(defending.getContestant().getContestantTrrtrlist());
					contestantTrrtrlist=currentContestant.getContestantTrrtrlist();
					contestantTrrtrlist.add(defending);
					
					defending.getContestant().getContestantTrrtrlist().remove(defending);
					defending.setContestant(currentContestant);
					currentContestant.setcontestantTrrtrlist(contestantTrrtrlist);
					System.out.println("After Conquering");
					System.out.println(contestantTrrtrlist);
					System.out.println(defending.getContestant().getContestantTrrtrlist());
					
					 somethingChanged();
						 notifyObservers();
					
									}					
				//currentContestant.setBatallion(currentBatallion);
				System.out.println("The Attacker after attack  has "+currentContestant.getBatallion()+" Batallion left");
								

		
	return currentContestant;
	
}

/**
 * Defend territory.
 *
 * @param attacking the attacking
 * @param defending the defending
 * @param currentContestant the current contestant
 * @return the contestant
 */
public Contestant DefendTerritory(Territories attacking,Territories defending, Contestant currentContestant) {

	
	pa = new PlayersAssignment();
	cev=new CardExchangeView(pa);
				
				int attackerBatallionleft=attacking.getBatallion();
				//System.out.println("The Attacker batallion before attack are "+attacking.getBatallion() + "for "+attacking.getAssignName());
				attackerBatallionleft=attackerBatallionleft-1;
				Contestant defContestant=defending.getContestant();
				attacking.setBatallion(attackerBatallionleft);
				//System.out.println("The Attacker batallion left after attack are "+ attacking.getBatallion() + " for the territory " +attacking.getAssignName());
				int currentBatallion=currentContestant.getBatallion();
				//System.out.println("The Attacker before attack has "+currentContestant.getBatallion()+" Batallion left");
				currentBatallion=currentBatallion-1;
				int defenderBatallion=defContestant.getBatallion();
				defenderBatallion=defenderBatallion+1;
				defContestant.setBatallion(defenderBatallion);
				
						//System.out.println("The Defender has won " );
						CardCountDefender++;
					
						if(attacking.getBatallion()==0)
						{
							System.out.println("the Defender has conquored the territory " +defending.getAssignName());
							m2=defending.getBatallion()-1;
							attacking.setBatallion(m2);
							
							contestantTrrtrlist=currentContestant.getContestantTrrtrlist();
							contestantTrrtrlist.remove(attacking);
							defending.getContestant().getContestantTrrtrlist().add(attacking);
							defending.getContestant().setcontestantTrrtrlist(contestantTrrtrlist);
							attacking.setContestant(defContestant);
							 somethingChanged();
								 notifyObservers();
							
											}					
	return currentContestant;
	
}

/**
 * Valid trade.
 *
 * @param cardtype the cardtype
 * @return true, if successful
 */
public boolean validTrade(List<String> cardtype) {
	int infantry = 0;
	int cavalry = 0;
	int artillery = 0;
	for(String s:cardtype) {
	if(s=="INFANTRY") infantry++;
	else if(s=="CAVALRY")  cavalry++;
	else if(s=="ARTILLERY") artillery++;
	}
	
	if ((infantry == 1 && cavalry == 1 && artillery == 1) || infantry == 3 || cavalry == 3 || artillery == 3) {
		return true;
	}
	else return false;
}



/**
 * Reinforce with cards.
 *
 * @param counter the counter
 * @param currentContestant the current contestant
 * @return the contestant
 */
public Contestant reinforceWithCards(int counter,Contestant currentContestant) {
	
	if(counter==3)
	{
	//System.out.println("Do you want to reinforce");
	int currentBatallion=currentContestant.getBatallion();
	
	
	if(currentContestant.getTimer()>1) {
	currentBatallion=currentBatallion+5*timer;}
	else {
		currentBatallion=currentBatallion+5;
	}
	currentContestant.setTimer(timer);
	currentContestant.setBatallion(currentBatallion);
	int count=currentContestant.getCardsInPocket();
	count=count-3;
	currentContestant.setCardsInPocket(count);;
	
}
	else if(counter>=5)
	{	
		System.out.println("You have 5 or more cards. please exchange them with armies");
		int currentBatallion=currentContestant.getBatallion();
		
		
		if(currentContestant.getTimer()>1) {
		currentBatallion=currentBatallion+5*timer;}
		else {
			currentBatallion=currentBatallion+5;
		}
		currentContestant.setTimer(timer);
		currentContestant.setBatallion(currentBatallion);
		System.out.println("Your Batallion got increased :) "+currentContestant.getBatallion());
		int count=currentContestant.getCardsInPocket();
		count=count-3;
		currentContestant.setCardsInPocket(count);;
		System.out.println("Now you have only " +currentContestant.getCardsInPocket()+" left");
	}
	return currentContestant;
}

/**
 * Gets the reinforce batallion.
 *
 * @param map the map
 * @param currentContestant the current contestant
 * @return the reinforce batallion
 */
public static Contestant getReinforceBatallion(Map map, Contestant currentContestant) {
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




}


