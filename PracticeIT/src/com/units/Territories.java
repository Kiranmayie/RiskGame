
package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to implement Serializable.
 * @author Admin
 *
 */



public class Territories implements Serializable {
	
    private String assignName;
	
	/**
	 * The @xCoordinate.
	 */
	private int pointX;
	
	/**
	 * The pointY.
	 */
	private int pointY;
	
	/**
	 * The @belongToContinent.
	 */
	private Continents lyingInCntnt;
	
	/**
	 * The @touchingTrrtrs.
	 */
	private List<String> touchingTrrtrs;
	
	/**
	 * The @adjacentTerritories
	 */
	private List<Territories> touchingTrrtrsExpand;
	
	private int batallion;
	
	private Contestant contestant;


	
	private boolean isConnected;

	Contestant currentContestant;
	
	public Territories() {
		touchingTrrtrs = new ArrayList<>();
		touchingTrrtrsExpand = new ArrayList<>();
	}
	
	public List<String> getTouchingTrrtrs() {
		return touchingTrrtrs;
	}
	
	public void setTouchingTrrtrs(List<String> touchingTrrtrs) {
		this.touchingTrrtrs = touchingTrrtrs;
	}
	
	public String getAssignName() {
		return assignName;
	}
	
	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}
	
	public int getPointX() {
		return pointX;
	}
	
	public void setPointX(int pointX) {
		this.pointX = pointX;
	}
	
	public int getPointY() {
		return pointY;
	}
	
	public void setPointY(int pointY) {
		this.pointY = pointY;
	}
	
	public List<Territories> getTouchingTrrtrsExpand() {
		return touchingTrrtrsExpand;
	}
	
	public void setTouchingTrrtrsExpand(List<Territories> touchingTrrtrsExpand) {
		this.touchingTrrtrsExpand = touchingTrrtrsExpand;
	}
	
	public Continents getLyingInCntnt() {
		return lyingInCntnt;
	}

	//
	public void setLyingInCntnt(Continents lyingInCntnt) {
		this.lyingInCntnt = lyingInCntnt;
	}
	
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Territories)) {
			return false;
		}

		Territories territory = (Territories) obj;
		return territory.getAssignName().equalsIgnoreCase(assignName);
	}
	
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public int getBatallion() {
		return batallion;
	}
	
	public void setBatallion(int batallion) {
		this.batallion = batallion;
	}
	
	/**
	 * Get Player
	 * 
	 * @return player
	 */
	public Contestant getContestant() {
		return contestant;
	}

	/**
	 * Set Player
	 * 
	 * @param player 
	 * 			    the player to set
	 */
	public void setContestant(Contestant contestant) {
		this.contestant = contestant;
	}
	
	public Contestant getCurrentContestant() {
		return currentContestant;
	}
	public String toString() {

		return ""+assignName.toString();


	}
}