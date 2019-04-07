package ConcretePatterns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.units.Contestant;
import com.units.Territories;
import Patterns.ContestantStrategies;

public class Human implements ContestantStrategies {
	
	
	private Iterator<Contestant> contestantLooper;
	public static List<Contestant> contestantsList=new ArrayList<Contestant>();
	Contestant currentContestant; 
	//List<Territories> selectedTerritoryList = new ArrayList<>();
	private int trrtrsConquered;
	
	public Human() {
		
		this.contestantLooper=contestantsList.iterator();
		}
		
	
	public void loadBatallion(List<Territories> selectedTerritoryList,Contestant currentContestant,List<Contestant> Contestants){
		
		  System.out.println("Placing Batallion against each player");
		  placeBatallion(currentContestant, selectedTerritoryList, Contestants);
			//selectedTerritoryList.removeAll(selectedTerritoryList);
			
	}
		
		
	
	
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
}
