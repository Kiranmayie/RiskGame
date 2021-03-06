package ConcretePatterns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.main.CardExchangeView;
import com.model.Cards;
import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;
import Patterns.ContestantStrategies;
import Patterns.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class Human.
 */
public class Human implements ContestantStrategies {
	
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
	
	/** The contestant looper. */
	private Iterator<Contestant> contestantLooper;
	
	/** The contestants list. */
	public static List<Contestant> contestantsList=new ArrayList<Contestant>();
	
	/** The current contestant. */
	Contestant currentContestant; 
	
	/** The trrtrs conquered. */
	//List<Territories> selectedTerritoryList = new ArrayList<>();
	private int trrtrsConquered;
	
	/** The timer. */
	private int timer;
	
	/** The pa. */
	private PlayersAssignment pa;
	
	/**
	 * Instantiates a new human.
	 */
	public Human() {
		
		this.contestantLooper=contestantsList.iterator();
		}
		
	
	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#loadBatallion(java.util.List, com.units.Contestant, java.util.List)
	 */
	public void loadBatallion(List<Territories> selectedTerritoryList,Contestant currentContestant,List<Contestant> Contestants){
		
		  System.out.println("Placing Batallion against each player");
		  placeBatallion(currentContestant, selectedTerritoryList, Contestants);
			//selectedTerritoryList.removeAll(selectedTerritoryList);
			
	}
		
		
	
	
	/**
	 * Place batallion.
	 *
	 * @param currentContestant the current contestant
	 * @param selectedTerritoryList the selected territory list
	 * @param contestants the contestants
	 */
	public void placeBatallion(Contestant currentContestant, List<Territories> selectedTerritoryList, List<Contestant> contestants)
	{
		int contestantArmies = currentContestant.getBatallion();
		if (currentContestant!= null && contestantArmies > 0)  {
			System.out.println(selectedTerritoryList);
			 System.out.println("Select the country on which you want to place your batallion");
			 Scanner sc=new Scanner(System.in);
			 String st=sc.nextLine();
				for(Territories terrtry:selectedTerritoryList) {
						//System.out.println(terrtry.getAssignName()+terrtry.getBatallion());
						if(st.equalsIgnoreCase(terrtry.getAssignName())) {
							//System.out.println(terrtry.getAssignName());
				terrtry.setBatallion(terrtry.getBatallion() + 1);
				currentContestant.setBatallion(contestantArmies - 1);
				System.out.println( terrtry.getAssignName() + ":-" + terrtry.getBatallion() + "-" + currentContestant.getContestantName());
				break;
			}
					
						}
					
		}
	}
	

	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#attackPhase(java.util.List, java.util.List, com.units.Contestant, com.units.Map)
	 */
	public void attackPhase(List<Territories> attackTrrtsList, List<Territories> defendTrrtrsList, Contestant currentContestant,Map map)  {

		for(Territories trrtr: currentContestant.getContestantTrrtrlist()) {
			List<Territories> ls = new ArrayList<Territories>();
			List<Territories> lsNew = new ArrayList<Territories>();
			List<Territories> lsNewNew = new ArrayList<Territories>();
			ls.addAll(getDefendingTerritory(trrtr));
			lsNew.addAll(getDefendingTerritory(trrtr));
			
			for(int i=0;i<ls.size();i++) {
				
				if(ls.get(i).getContestant().getContestantName().equals(currentContestant.getContestantName())) {
					lsNewNew.add(ls.get(i));
					
				}
			}
			for(Territories trr:lsNewNew) {
			lsNew.remove(trr);
			}
			
			System.out.println("Territory "+trrtr+" can attack the following territories:- ");
			System.out.println(lsNew);
			
		}
		
		System.out.println("Select the attacking territory, defending territory and player you want to attack");
		System.out.println("Territories from which you can attack are:- "+currentContestant.getContestantTrrtrlist());
		Scanner sa=new Scanner(System.in);
		String attackingTerritory=sa.nextLine();
		String beingAttackedTerritory=sa.nextLine();
		String Contestant=sa.nextLine();
		
		for (Territories territory1 : attackTrrtsList) {
			/*System.out.println(getDefendingTerritory(territory1));
			System.out.println(territory1.getBatallion());
			System.out.println(getDefendingTerritory(territory1).size());
			System.out.println(territory1.getAssignName());*/
				
				//System.out.println("Current contestant is " + currentContestant.getContestantName());
			if (  territory1.getBatallion() > 1 && getDefendingTerritory(territory1).size() > 0 ) 
			{ 
				  int n=territory1.getBatallion();
			
					System.out.println("Attacker's Turn");
					int[] attackerDiceValue=rollDiceAttacker(n);
					int m=defendingDiceCalculation(getDefendingTerritory(territory1), beingAttackedTerritory);
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
								int count1=attackTerritory(getDefendingTerritory(territory1), beingAttackedTerritory,Contestant);
								System.out.println(count1);
								System.out.println("Do you wish to continue attacking Yes/No");
								String Answer=d.next();
								String Yes = null,No;
								if(Answer.equals("Yes"))
										{
											attackPhase(attackTrrtsList, defendTrrtrsList, currentContestant,map);
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
									attackPhase(attackTrrtsList, defendTrrtrsList, currentContestant,map);
								}
									}
							}
			
		}
								
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
	 * @return the contestant
	 */
	public Contestant reinforceWithCards(int counter) {
		
		if(counter==3)
		{
		System.out.println("Do you want to reinforce");
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
	 * Roll dice attacker.
	 *
	 * @param n the n
	 * @return the int[]
	 */
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

	/**
	 * Gets the defending territory.
	 *
	 * @param territory the territory
	 * @return the defending territory
	 */
	private List<Territories> getDefendingTerritory(Territories territory) {
		List<Territories> defendingTerritories = new ArrayList<Territories>();
		defendingTerritories.addAll(territory.getTouchingTrrtrsExpand());
		return defendingTerritories;
	}

/**
 * Defend territory.
 *
 * @param attackTrrtsList the attack trrts list
 * @param attackingTerritory the attacking territory
 * @param contestant the contestant
 * @return the int
 */
private int DefendTerritory(List<Territories> attackTrrtsList, String attackingTerritory, String contestant) {

	
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
					if(defContestant.getContestantName().equals(contestant))
					{
						System.out.println("The Defender has won " +territory.getAssignName());
						CardCountDefender++;
						territory.setBatallion(m2);
						defContestant.setCardsInPocket(CardCountDefender);
						System.out.println(CardCountDefender);
						System.out.println(defContestant.getCardsInPocket());
					}
				}
				
				currentContestant.setBatallion(currentBatallion);
				System.out.println("The Attacker after attack  has "+currentContestant.getBatallion()+" Batallion left");

		}
	}
	return CardCountDefender;
	
}

/**
 * Roll dice defender.
 *
 * @param m the m
 * @return the int[]
 */
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

/**
 * Fortification phase.
 *
 * @param selectedTerritoryList the selected territory list
 * @param adjancentTerritory the adjancent territory
 * @param currentContestant the current contestant
 * @return true, if successful
 */
public boolean fortificationPhase(List<Territories> selectedTerritoryList, List<Territories> adjancentTerritory, Contestant currentContestant) {
	Scanner a = new Scanner(System.in);
	String Territory1 = " ";
	String Territory2=" ";
	System.out.println(selectedTerritoryList);
	boolean count = true;
	
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
		else
		{
			System.out.println("The territories you have selected are not connected");
			
		}
		count = false;
		   }
	fortificationPhase(selectedTerritoryList, adjancentTerritory,currentContestant);
	return count;
	}
	
	
/**
 * Defending dice calculation.
 *
 * @param defendTrrtrsList the defend trrtrs list
 * @param beingAttackedTerritory the being attacked territory
 * @return the int
 */
private int defendingDiceCalculation(List<Territories> defendTrrtrsList,String beingAttackedTerritory) {
	
	int m=0;
	for (Territories territory : defendTrrtrsList) {
		if (territory.getAssignName().equals(beingAttackedTerritory) && territory.getBatallion() > 1) 
		
			{   m=territory.getBatallion();
			
			}}
		System.out.println(m);
	return m;
}

/**
 * Attack territory.
 *
 * @param defendTrrtrsList the defend trrtrs list
 * @param beingAttackedTerritory the being attacked territory
 * @param contestant the contestant
 * @return the int
 */
public int attackTerritory(List<Territories> defendTrrtrsList,String beingAttackedTerritory, String contestant) {

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
				CardCountAttacker++;
				currentContestant.setCardsInPocket(CardCountAttacker);
				contestantTrrtrlist=currentContestant.getcontestantTrrtrlist();
				contestantTrrtrlist.add(territory);
				currentContestant.setcontestantTrrtrlist(contestantTrrtrlist);
				 cardtypeAttacker.add(Cards.selectCards());
				 CardCountAttacker = currentContestant.getCardsInPocket();
				System.out.println("The player has been assigned "+currentContestant.getCardsInPocket()+" of type " +PlayersAssignment.cardtypeAttacker);
				
				
				 somethingChanged();
					 notifyObservers();
					 
				if(validTrade(cardtypeAttacker)) 
				{	System.out.println("Reinforce is possible");
				currentContestant = reinforceWithCards(CardCountAttacker);
				}		
				if(territory.getBatallion()==0)
				{
					System.out.println("the Attacker has conquored the territory " +territory.getAssignName());
					
					territory.setBatallion(m1);
	
					 somethingChanged();
						 notifyObservers();
					
									}					
				currentContestant.setBatallion(currentBatallion);
				System.out.println("The Attacker after attack  has "+currentContestant.getBatallion()+" Batallion left");
								

		}
	}
	return CardCountAttacker;
	
}

/**
 * Any number.
 *
 * @param i the i
 * @return the int[]
 */
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
	 * Removes the observer.
	 *
	 * @param o the o
	 */
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if(i>=0) {
		observers.remove(i);
		}
	}

	
	/**
	 * Notify observers.
	 *
	 * @param obj the obj
	 */
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

	
	/**
	 * Register observer.
	 *
	 * @param o the o
	 */
	public void registerObserver(Observer o) {
		observers.add(o);
		//System.out.println(observers.get(0));
		//System.out.println(observers.size());
	}


	/* (non-Javadoc)
	 * @see Patterns.ContestantStrategies#reinforcementPhase(java.util.List, com.units.Contestant, com.units.Map)
	 */
	@Override
	public Territories reinforcementPhase(List<Territories> territoryList, 
			Contestant currentContestant,Map map) {
				return null;
		// TODO Auto-generated method stub
		
	}

}
