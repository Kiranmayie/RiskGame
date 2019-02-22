package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Continents implements Serializable {
	
	private String assignName;
	
	
	private String cValue;
	
	
	private HashMap<String, Territories> trrtrsMp;
	
	
	private List<Territories> trrtrs;
	
	public Continents() {
		trrtrsMp = new HashMap<>();
		trrtrs = new ArrayList<>();
	}

	/**
	 * Get Territory Map
	 * 
	 * @return the territoryMap
	 */
	public HashMap<String, Territories> geTtrrtrsMp() {
		return trrtrsMp;
	}

	/**
	 * Set territory Map
	 * 
	 * @param territoryMap
	 *            the territoryMap to set
	 */
	public void seTtrrtrsMp(HashMap<String, Territories> trrtrsMp) {
		this.trrtrsMp = trrtrsMp;
	}

	/**
	 * Get name
	 * 
	 * @return name 
	 */
	public String getAssignName() {
		return assignName;
	}

	/**
	 * Set Name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	/**
	 * Get Value
	 * 
	 * @return value 
	 */
	public String getCValue() {
		return cValue;
	}

	/**
	 * Set Value
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setCValue(String cValue) {
		this.cValue = cValue;
	}

	/**
	 * Get Territories
	 * 
	 * @return territories 
	 */
	public List<Territories> getTrrtrs() {
		return trrtrs;
	}

	/**
	 * Set territories
	 * 
	 * @param territories
	 *            the territories to set
	 */
	public void seTtrrtrs(List<Territories> trrtrs) {
		this.trrtrs = trrtrs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Continents)) {
			return false;
		}

		Continents continent = (Continents) obj;
		return continent.getAssignName().equalsIgnoreCase(assignName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Name: " + assignName + "value: " + "List of territory :" + trrtrs.toString();
	}

}
