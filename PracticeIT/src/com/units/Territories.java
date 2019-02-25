package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Territories implements Serializable {
	
    private String assignName;
	
	/**
	 * The @xCoordinate.
	 */
	private int pointX;
	
	/**
	 * The @yCoordinate.
	 */
	private int pointY;
	
	/**
	 * The @belongToContinent.
	 */
	private Continents lyingInCntnt;
	
	/**
	 * The @adjTerritories.
	 */
	private List<String> touchingTrrtrs;
	
	/**
	 * The @adjacentTerritories
	 */
	private List<Territories> touchingTrrtrsExpand;

	private int army;
	
	
	public int Army() {
		return army;
	}
	
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
}
