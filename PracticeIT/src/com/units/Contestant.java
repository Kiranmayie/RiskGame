package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.model.GamePlan;
// TODO: Auto-generated Javadoc
/**
 * The Class Contestant immplements Serializable .
 */
public class Contestant implements Serializable {
	
	
	/** The contestant id. */
	int contestantId;
	
	/** The contestant name. */
	String contestantName;
	
	/** The batallion. */
	int batallion;
	
	/** The contestant trrtrlist. */
	List<Territories> contestantTrrtrlist;
	
	/** The current contestant. */
	Contestant currentContestant;
	
	/** The contestant list. */
	List<Contestant> contestantList;
	
	/** The game plan. */
	GamePlan gamePlan;
	
	/** The count. */
	int count;

	/** The timer. */
	private int timer;

	
	/**
	 * Instantiates a new contestant.
	 *
	 * @param contestantId the contestant id
	 */
	public Contestant(int contestantId){
		this.contestantId=contestantId;
		this.contestantTrrtrlist= new ArrayList();
	
	}
	/**
	 * Gets the contestant id.
	 * @return the contestant id
	 */
	public int getContestantId(){
		return contestantId;
	}
	
	/**
	 * Sets the contestant id.
	 *
	 * @param contestantId the new contestant id
	 */
	public void setContestantId(int contestantId){
		this.contestantId=contestantId;
	}
	
	
	
	 /**
 	 * Gets the contestant name.
 	 * @return the contestant name
 	 */
 	public String getContestantName(){
		 return contestantName;
	 }
	 
 	/**
 	 * Sets the contestant name.
 	 *
 	 * @param contestantName the new contestant name
 	 */
 	public void setContestantName(String contestantName){
		 this.contestantName=contestantName;
	 	 }
	 
	 
	 /**
 	 * Gets the batallion.
 	 * @return the batallion
 	 */
 	public int getBatallion() {
		 return batallion;
	 	 }
	 
	 
	/**
	 * Sets the batallion.
	 *
	 * @param batallion the new batallion
	 */
	public void setBatallion(int batallion){
		this.batallion=batallion;
	}
	
	
	/**
	 * Gets the contestant trrtrlist.
	 * @return the contestant trrtrlist
	 */
	public List<Territories> getcontestantTrrtrlist(){
		return contestantTrrtrlist;
	}
	
	/**
	 * Sets the contestant trrtrlist.
	 *
	 * @param contestantTrrtrlist the new contestant trrtrlist
	 */
	public void setcontestantTrrtrlist(List<Territories> contestantTrrtrlist){
		this.contestantTrrtrlist=contestantTrrtrlist;
		} 
	
	/**
	 * Gets the contestant trrtrlist.
	 * @return the contestant trrtrlist
	 */
	public List<Territories> getContestantTrrtrlist() {
		return contestantTrrtrlist;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (!(object instanceof Contestant)) {
			return false;
		}

		Contestant contestant = (Contestant) object;
		return contestant.getContestantId() == contestantId;
	}
	
	/**
	 * Gets the plan.
	 *
	 * @return the plan
	 */
	public GamePlan getPlan() {
		return gamePlan;
	}

	/**
	 * Sets the plan.
	 *
	 * @param strategy the new plan
	 */
	public void setPlan(GamePlan strategy) {
		this.gamePlan = gamePlan;
	}

	/**
	 * Gets the game plan.
	 * @return the game plan
	 */
	public GamePlan getGamePlan() {
		return gamePlan;

	}
	
	/**
	 * Sets the game plan.
	 * @param gamePlan the new game plan
	 */
	public void setGamePlan(GamePlan gamePlan) {
		this.gamePlan=gamePlan;

	}
	
	
	/**
	 * Gets the cards in pocket.
	 *
	 * @return the cards in pocket
	 */
	public int getCardsInPocket() {
		
		return count;
	}
	
	/**
	 * Sets the cards in pocket.
	 *
	 * @param count the new cards in pocket
	 */
	public void setCardsInPocket(int count) {
		// TODO Auto-generated method stub
		this.count=count;
	}
	
	/**
	 * Sets the timer.
	 *
	 * @param timer the new timer
	 */
	public void setTimer(int timer) {
		this.timer=timer+1;
		
	}
	
	/**
	 * Gets the timer.
	 *
	 * @return the timer
	 */
	public int getTimer() {
		
		return timer;
	}
}
	
	