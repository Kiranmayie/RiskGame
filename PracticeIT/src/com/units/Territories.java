
package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is used to implement Serializable and Store, read the Territories.
 */
public class Territories implements Serializable {
	
    /** The assign name. */
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
	
	/** The @adjacentTerritories. */
	private List<Territories> touchingTrrtrsExpand;
	
	/** The batallion. */
	private int batallion;
	
	/** The contestant. */
	private Contestant contestant;


	
	/** The is connected. */
	private boolean isConnected;

	/** The current contestant. */
	Contestant currentContestant;
	
	/**
	 * Instantiates a new territories.
	 */
	public Territories() {
		touchingTrrtrs = new ArrayList<>();
		touchingTrrtrsExpand = new ArrayList<>();
	}
	
	/**
	 * Gets the touching Territories.
	 * @return the touching trrtrs
	 */
	public List<String> getTouchingTrrtrs() {
		return touchingTrrtrs;
	}
	
	/**
	 * Sets the touching Territories.
	 * @param touchingTrrtrs the new touching trrtrs
	 */
	public void setTouchingTrrtrs(List<String> touchingTrrtrs) {
		this.touchingTrrtrs = touchingTrrtrs;
	}
	
	/**
	 * Gets the assign name.
	 * @return the assign name
	 */
	
	public String getAssignName() {
		return assignName;
	}
	/**
	 * Sets the assign name.
	 * @param assignName 
	 */
	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}
	
	/**
	 * Gets the point X.
	 * @return the point X
	 */
	public int getPointX() {
		return pointX;
	}
	
	/**
	 * Sets the point X.
	 * @param pointX 
	 */
	public void setPointX(int pointX) {
		this.pointX = pointX;
	}
	
	/**
	 * Gets the point Y.
	 * @return the point Y
	 */
	public int getPointY() {
		return pointY;
	}
	
	/**
	 * Sets the point Y.
	 * @param pointY 
	 */
	public void setPointY(int pointY) {
		this.pointY = pointY;
	}
	
	/**
	 * Gets the touching Territories expand.
	 * @return the touching trrtrs expand
	 */
	public List<Territories> getTouchingTrrtrsExpand() {
		return touchingTrrtrsExpand;
	}
	
	/**
	 * Sets the touching Territories expand.
	 * @param touchingTrrtrsExpand 
	 */
	public void setTouchingTrrtrsExpand(List<Territories> touchingTrrtrsExpand) {
		this.touchingTrrtrsExpand = touchingTrrtrsExpand;
	}
	
	/**
	 * Gets the lying in Continents.
	 * @return the lying in cntnt
	 */
	public Continents getLyingInCntnt() {
		return lyingInCntnt;
	}

	/**
	 * Sets the lying in cntnt.
	 * @param lyingInCntnt 
	 */
	public void setLyingInCntnt(Continents lyingInCntnt) {
		this.lyingInCntnt = lyingInCntnt;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	/**
	 * Sets the connected.
	 * @param isConnected the new connected
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	
	/**
	 * Checks if is connected.
	 * @return true, if is connected
	 */
	public boolean isConnected() {
		return isConnected;
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
	 * @param batallion the new batallion
	 */
	public void setBatallion(int batallion) {
		this.batallion = batallion;
	}
	
	/**
	 * Get Player.
	 * @return player
	 */
	public Contestant getContestant() {
		return contestant;
	}

	/**
	 * Set Player.
	 * @param contestant the new contestant
	 */
	public void setContestant(Contestant contestant) {
		this.contestant = contestant;
	}
	
	/**
	 * Gets the current contestant.
	 * @return the current contestant
	 */
	public Contestant getCurrentContestant() {
		return currentContestant;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		return ""+assignName.toString();


	}
}