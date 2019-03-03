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




public class PlayersAssignment  extends Observable implements Observer, Serializable{
	
	Contestant currentContestant;
	public static final Integer THREE_PLAYER_ARMIES = 35;
	public static final Integer FOUR_PLAYER_ARMIES = 30;
	public static final Integer FIVE_PLAYER_ARMIES = 25;
	public static final Integer SIX_PLAYER_ARMIES = 20;
	List<Contestant> contestantsList=new ArrayList<>();
	private int trrtrsConquered;
	private Iterator<Contestant> contestantLooper;
	PlayersAssignment pa;
	List<Territories> chosenTrrtryList;
	private Map map;
	private Continents cntnts;
	private Territories trrtry;
	List<Territories> selectedTerritoryList = new ArrayList<>();;




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

public void update(Observable o, Object arg) {
	String view = (String) arg;

	if (view.equals("rollDiceComplete")) {
		//DiceModel diceModel = (DiceModel) o;
		//setTerritoryWon(diceModel.getNumOfTerritoriesWon());
		setChanged();
		notifyObservers("rollDiceComplete");
	}
}

public int valueGenerator(int num) {
	return (int) (Math.random() * num) + 0;
}

public Contestant getCurrentContestant() {
	return currentContestant;
}

public void setCurrentContestant(Contestant currentContestant) {
	this.currentContestant = currentContestant;
}

public int getTerritoryWon() {
	return trrtrsConquered;
}

public void setTerritoryConquered(int trrtrsConquered) {
	this.trrtrsConquered = trrtrsConquered;
}

public List<Contestant> getContestantsList() {
	return contestantsList;
}

public void setContestantsList(List<Contestant> contestantsList) {
	this.contestantsList=contestantsList;
}


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



public void attackPhase(ListView<Territories> attackTrrtsList, ListView<Territories> defendTrrtrsList)  {
	currentContestant.getGamePlan().attackPhase(attackTrrtsList, defendTrrtrsList,this );

	
}


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

private void contestantAssignmentToTerritories(Contestant currentContestant) {
	if (currentContestant.getBatallion() > 0) {
		Territories territory = currentContestant.getcontestantTrrtrlist()
				.get(anyNumber(currentContestant.getcontestantTrrtrlist().size() - 1));
		territory.setBatallion(territory.getBatallion() + 1);
		currentContestant.setBatallion(currentContestant.getBatallion() - 1);
	}
	
}

private int anyNumber(int i) {
	return (int) (Math.random() * i) + 0;
	
}

public void reinforcePhase(ObservableList<Territories> territoryList, Territories territory, TextArea gameConsole) {
	//currentContestant.getStrategy().reinforcementPhase(territoryList, territory, gameConsole, currentContestant);
	// start attack phase
	if (currentContestant.getBatallion() <= 0 && contestantsList.size() > 1) {
		
		setChanged();
		notifyObservers("Attack");
	}
}
public void fortifyPhase(ListView<Territories> selectedTerritory, ListView<Territories> adjTerritory) {
	//boolean isFortificationDone = currentContestant.getStrategy().fortificationPhase(selectedTerritory, adjTerritory,
		//eConsole, currentContestant);

	boolean FortifySuccess = false;
	if (FortifySuccess && contestantsList.size() > 1) {
		setChanged();
		notifyObservers("Reinforce");
	}

}
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
public boolean isContestantWon(List<Contestant> currentContestant) {
	boolean ContestantWon = false;
	if (currentContestant.size() == 1) {
		ContestantWon = true;
	}
	return ContestantWon;
}

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
		if(trrtry1.getBatallion()==1) {
		
		
		 selectedTerritoryList.add(trrtry1);}
		System.out.println( trrtry1.getAssignName() + ":-" + trrtry1.getBatallion() + "-" + currentContestant.getContestantName());
	       System.out.println(trrtry1.getTouchingTrrtrs());
	      
	}
	System.out.println(selectedTerritoryList);
return selectedTerritoryList;
}
	

public void loadBatallion(List<Territories> selectedTerritoryList) {
	  System.out.println("Placing Batallion against each player");
	  
		placeBatallion(currentContestant, selectedTerritoryList, contestantsList);
		selectedTerritoryList.removeAll(selectedTerritoryList);
		executingCurrentContestant();
		
			loadBatallion(selectedTerritoryList);
		
		
		
}



	




}


	









