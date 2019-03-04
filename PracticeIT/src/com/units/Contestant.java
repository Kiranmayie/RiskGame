package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.model.GamePlan;

public class Contestant implements Serializable {
	
	
	int contestantId;
	String contestantName;
	int batallion;
	List<Territories> contestantTrrtrlist;
	Contestant currentContestant;
	List<Contestant> contestantList;
	GamePlan gamePlan;
	//List<Cards> contestantCards;

	
	public Contestant(int contestantId){
		this.contestantId=contestantId;
		this.contestantTrrtrlist= new ArrayList();
	
	}
	
	
	public int getContestantId(){
		return contestantId;
	}
	
	
	public void setContestantId(int contestantId){
		this.contestantId=contestantId;
	}
	
	
	 public String getContestantName(){
		 return contestantName;
	 }
	 
	 
	 public void setContestantName(String contestantName){
		 this.contestantName=contestantName;
	 	 }
	 
	 
	 public int getBatallion() {
		 return batallion;
	 	 }
	 
	 
	public void setBatallion(int batallion){
		this.batallion=batallion;
	}
	
	
	public List<Territories> getcontestantTrrtrlist(){
		return contestantTrrtrlist;
	}
	
	
	public void setcontestantTrrtrlist(List<Territories> contestantTrrtrlist){
		this.contestantTrrtrlist=contestantTrrtrlist;
		} 
	
	public List<Territories> getContestantTrrtrlist() {
		return contestantTrrtrlist;
	}
	
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


	public GamePlan getGamePlan() {
		return gamePlan;

	}
	
	public void setGamePlan(GamePlan gamePlan) {
		this.gamePlan=gamePlan;

	}
	
		
	}