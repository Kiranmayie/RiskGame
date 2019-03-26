package com.model;


import java.io.Serializable;
import java.util.ArrayList;
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
public class PlayersAssignment  implements  Serializable,Observable{
	/** The current contestant. */
	Contestant currentContestant;
	int timer=1;
	/** The Constant TWO_PLAYER_ARMIES. */
	public static final Integer TWO_PLAYER_ARMIES = 23;
	
	/** The Constant THREE_PLAYER_ARMIES. */
	public static final Integer THREE_PLAYER_ARMIES = 35;
	
	/** The Constant FOUR_PLAYER_ARMIES. */

	public static final Integer FOUR_PLAYER_ARMIES = 30;
	
	/** The Constant FIVE_PLAYER_ARMIES. */
	public static final Integer FIVE_PLAYER_ARMIES = 25;
	
	/** The Constant SIX_PLAYER_ARMIES. */
	public static final Integer SIX_PLAYER_ARMIES = 20;

	private static final String INFANTRY = null;

	private static final String CAVALRY = null;

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
	private List<Territories> contestantTrrtrlist;
	static int m1 = 0;	
	static int m2 = 0;	
	public ArrayList<Observer> observers = new ArrayList<Observer>();
	public boolean changed;
	CardExchangeView cev;
	public static int CardCount;
	public static String cardtype;
	





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



/**
 * Attack phase.
 * @param attackTrrtsList 
 * @param defendTrrtrsList

 */

public void attackPhase(List<Territories> attackTrrtsList, List<Territories> defendTrrtrsList, Contestant currentContestant)  {
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
		if (territory.getAssignName().equals(attackingTerritory ) && territory.getBatallion() > 1 && getDefendingTerritory(territory).size() > 0) 
		{ 
			  int n=territory.getBatallion();
		
				System.out.println("Attacker's Turn");
				int[] attackerDiceValue=rollDiceAttacker(n);
				int m=defendingDiceCalculation(getDefendingTerritory(territory), beingAttackedTerritory);
				System.out.println("Defender's Turn");
				int[] defenderDiceValue=rollDiceDefender(m);
				for(int i=0;i<attackerDiceValue.length;i++) {
					for(int j=0;j<defenderDiceValue.length;j++) {
						System.out.println("Attacker Dice Values "+"Defender Dice Values "+ "\n" +attackerDiceValue[i]+"                     " +defenderDiceValue[j]);
					}
				}
				System.out.println("Attacker: "+currentContestant.getContestantName()+" Choose your Dice");
				Scanner d=new Scanner(System.in);
				int Attackerdice=d.nextInt();
				System.out.println("Defender: "+Contestant+" Choose your Dice");
				int DefenderDice=d.nextInt();
				if(attackerDiceValue[Attackerdice-1]>defenderDiceValue[DefenderDice-1])
				{	System.out.println("Attacker won "+attackerDiceValue[Attackerdice-1]+" is greater than "+defenderDiceValue[DefenderDice-1]);
							int count1=attackTerritory(getDefendingTerritory(territory), beingAttackedTerritory,Contestant);
							System.out.println(count1);
							System.out.println("Do you wish to continue attacking Yes/No");
							String Answer=d.next();
							String Yes = null,No;
							if(Answer.equals("Yes"))
									{
										attackPhase(attackTrrtsList, defendTrrtrsList, currentContestant);
									}
							else
							{
								fortificationPhase(attackTrrtsList, defendTrrtrsList, currentContestant);
							}
							}
				else if(attackerDiceValue[Attackerdice-1]==defenderDiceValue[DefenderDice-1]||attackerDiceValue[Attackerdice-1]<defenderDiceValue[DefenderDice-1])
				{
					System.out.println("Defender won "+defenderDiceValue[DefenderDice-1]+" is greater than "+attackerDiceValue[Attackerdice-1]);		
					int count2=DefendTerritory(attackTrrtsList, attackingTerritory,Contestant);
					System.out.println(count2);
					System.out.println("Do you wish to continue attacking Yes/No");
					String Answer=d.next();
					String Yes = null,No;
					if(Answer.equals("Yes"))
							{
								attackPhase(attackTrrtsList, defendTrrtrsList, currentContestant);
							}
								}
						}
	}
	
							
					}	
				

	public boolean fortificationPhase(List<Territories> selectedTerritoryList, List<Territories> adjancentTerritory, Contestant currentContestant) {
		Scanner a = new Scanner(System.in);
		String Territory1 = " ";
		String Territory2=" ";
		System.out.println(selectedTerritoryList);
		boolean count = true;
		System.out.println(adjancentTerritory);
		System.out.println("Select the territory you want to move the armies from");
		Territory1=a.next();
		Territory2=a.next();
		
		System.out.println("You have selected to move your armies from "+Territory1 + "to" + Territory2);
		for(Territories territory:selectedTerritoryList) {
			System.out.println(getDefendingTerritory(territory));
			if(Territory1.equals(selectedTerritoryList) && Territory2.equals(getDefendingTerritory(territory))) {
				int size=getDefendingTerritory(territory).size()-1;
		   System.out.println("Enter armies to fortify(max " +size + ") :");
			int f_army1=a.nextInt();
			int currentBatallion1=territory.getBatallion();
			currentBatallion1=currentBatallion1+f_army1;
			int currentBatallion2=((Contestant) getDefendingTerritory(territory)).getBatallion();
			currentBatallion2=currentBatallion2-f_army1;}
			count = false;
			   }
		return count;
		}
		
		


private int DefendTerritory(List<Territories> attackTrrtsList, String attackingTerritory, String contestant) {
int count=0;
	
	for (Territories territory : attackTrrtsList) {
		
		if(territory.getAssignName().equals(attackingTerritory))
		{		
				int attackerBatallionleft=territory.getBatallion();
				System.out.println("The Attacker batallion before attack are "+territory.getBatallion() + "for "+territory.getAssignName());
				attackerBatallionleft=attackerBatallionleft-1;
				territory.setBatallion(attackerBatallionleft);
				System.out.println("The Attacker batallion left after attack are "+ territory.getBatallion() + " for the territory " +territory.getAssignName());
				int currentBatallion=currentContestant.getBatallion();
				System.out.println("The Attacker before attack has "+currentContestant.getBatallion()+" Batallion left");
				currentBatallion=currentBatallion-1;
				for(Contestant defContestant : contestantsList)
				{
					if(defContestant.getContestantName().equals(contestant)&&territory.getBatallion()==0)
					{
						System.out.println("the Defender has conquored the territory " +territory.getAssignName());
						count++;
						territory.setBatallion(m2);
						defContestant.setCardsInPocket(count);
						System.out.println(defContestant.getCardsInPocket());
					}
				}
				
				currentContestant.setBatallion(currentBatallion);
				System.out.println("The Attacker after attack  has "+currentContestant.getBatallion()+" Batallion left");

		}
	}
	return count;
	
}

private int[] rollDiceDefender(int m) {
	
	int[] DiceResult = null;
	Scanner sc=new Scanner(System.in);
	switch(m)
	{
	case 2: System.out.println("Roll the dice you can only roll maximum of " +(m-1)+"dice hence you cannot choose");
	DiceResult=anyNumber(1);
	
	break;
	
	case 3:System.out.println("Roll the dice you can only roll maximum of " +(m-1)+"dice. So How many you want to choose");
	m2=sc.nextInt();
	DiceResult=anyNumber(m2);
	
	break;
	
	default:System.out.println("Roll the dice you can only roll maximum of 2 dice. So How many you want to choose");
	m2=sc.nextInt();
	DiceResult=anyNumber(m2);
	
	break;
	}
	
	return DiceResult;

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

public int attackTerritory(List<Territories> defendTrrtrsList,String beingAttackedTerritory, String contestant) {
	int count=0;
	pa = new PlayersAssignment();
	cev=new CardExchangeView(pa);
	
	for (Territories territory : defendTrrtrsList) {
		
		if(territory.getAssignName().equals(beingAttackedTerritory))
		{		
				int defenderBatallionleft=territory.getBatallion();
				System.out.println("The Defender batallion before attack are "+territory.getBatallion() + "for "+territory.getAssignName());
				defenderBatallionleft=defenderBatallionleft-1;
				territory.setBatallion(defenderBatallionleft);
				System.out.println("The Defender batallion left after attack are "+ territory.getBatallion() + " for the territory " +territory.getAssignName());
				int currentBatallion=currentContestant.getBatallion();
				System.out.println("The Attacker before attack has "+currentContestant.getBatallion()+" Batallion left");
				currentBatallion=currentBatallion+1;
				if(territory.getBatallion()==0)
				{
					System.out.println("the Attacker has conquored the territory " +territory.getAssignName());
					count++;
					territory.setBatallion(m1);
					currentContestant.setCardsInPocket(count);
					contestantTrrtrlist=currentContestant.getcontestantTrrtrlist();
					contestantTrrtrlist.add(territory);
					currentContestant.setcontestantTrrtrlist(contestantTrrtrlist);
					 cardtype=Cards.selectCards();
					 CardCount = currentContestant.getCardsInPocket();
					System.out.println("The player has been assigned"+currentContestant.getCardsInPocket()+"of type" +cardtype);
					
					
					 somethingChanged();
						 notifyObservers();
						 
					if(validTrade(cardtype)) reinforceWithCards(count);
									}					
				currentContestant.setBatallion(currentBatallion);
				System.out.println("The Attacker after attack  has "+currentContestant.getBatallion()+" Batallion left");
								

		}
	}
	return count;
	
}


public boolean checkIfPlayersArmiesExhausted(List<Contestant> players) {
	
	//System.out.println("Hello");
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

private boolean validTrade(String cardtype) {
	int infantry = 0;
	int cavalry = 0;
	int artillery = 0;
	switch(cardtype)
	{
	case "INFANTRY": infantry++;
	break;
	case "CAVALRY": cavalry++;
	break;
	case "ARTILLERY": artillery++;
	break;
	
	}
	
	if ((infantry == 1 && cavalry == 1 && artillery == 1) || infantry == 3 || cavalry == 3 || artillery == 3) {
		return true;
	}
	else return false;
}



private void reinforceWithCards(int counter) {
	if(currentContestant.getCardsInPocket()>=3)
	{	
		System.out.println("You have 3 or more cards. please exchange them with armies");
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
	
}

private int[] rollDiceAttacker(int n) {
	
	int[] DiceResult = null;
	Scanner sc=new Scanner(System.in);
	switch(n)
	{
	case 2: System.out.println("Roll the dice you can only roll maximum of " +(n-1)+" dice hence you cannot choose");
	DiceResult=anyNumber(1);
	
	break;
	
	case 3:System.out.println("Roll the dice you can only roll maximum of " +(n-1)+" dice. So How many you want to choose");
	m1=sc.nextInt();
	DiceResult=anyNumber(m1);
	
	break;
	
	case 4:System.out.println("Roll the dice you can only roll maximum of " +(n-1)+" dice. So How many you want to choose");
	m1=sc.nextInt();
	DiceResult=anyNumber(m1);
	
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
	int max = 6; 
    int min = 1;
    int range = max - min + 1; 
	int allresultOfDice[] = new int[i];
	for(int j=0;j<i;j++) {
	allresultOfDice[j] = (int) (Math.random()* range) + min; 
	System.out.println(allresultOfDice[j]);
	}
	
	return allresultOfDice;
}
public void attackPhase(ListView<Territories> attackTrrtsList, ListView<Territories> defendTrrtrsList)  {
	currentContestant.getGamePlan().attackPhase(attackTrrtsList, defendTrrtrsList,this );
// attack\phase

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
 * @param selectedTerritory 
 * @param adjTerritory 
 */
public void fortifyPhase(ListView<Territories> selectedTerritory, ListView<Territories> adjTerritory) {
		boolean FortifySuccess = false;
	if (FortifySuccess && contestantsList.size() > 1) {
	//	setChanged();
	//	notifyObservers("Reinforce");
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
 * @param currentContestant 
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
public void removeObserver(Observer o) {
	int i = observers.indexOf(o);
	if(i>=0) {
	observers.remove(i);
	}
}

@Override
public void notifyObservers(String obj) {
	if(changed) {
		//System.out.println(supLocal.observers);
	//	System.out.println(supLocal.observers.get(0));
	for(Observer obs:pa.observers) {
	
		obs.update(obj);
	}}
	
}

public void notifyObservers() {
	if(changed) {
		cev.update("Card Given");
		
	}
}


public void somethingChanged() {
	changed=true;
}

@Override
public void registerObserver(Observer o) {
	observers.add(o);
	//System.out.println(observers.get(0));
	//System.out.println(observers.size());
}

}


