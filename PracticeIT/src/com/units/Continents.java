package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * This class is used to initialize all the continents .
 */
public class Continents implements Serializable {
	
	/** The assign name. */
	private String assignName;
	
	/** The c value. */
	private String cValue;
	
	
	/** The trrtrs mp. */
	private HashMap<String, Territories> trrtrsMp;
	
	
	/** The trrtrs. */
	private List<Territories> trrtrs;
	
	/**
	 * Instantiates a new continents.
	 */
	public Continents() {
		trrtrsMp = new HashMap<>();
		trrtrs = new ArrayList<>();
	}
   
	/**
	 * Get Territory Map.
	 *
	 * @return the trrtrsMp
	 */
	public HashMap<String, Territories> geTtrrtrsMp() {
		return trrtrsMp;
	}

	/**
	 * Set territory Map.
	 * @param trrtrsMp 
	 */
	public void seTtrrtrsMp(HashMap<String, Territories> trrtrsMp) {
		this.trrtrsMp = trrtrsMp;
	}

	/**
	 * Get name.
	 * @return assignName
	 */
	public String getAssignName() {
		return assignName;
	}

	/**
	 * Set Name.
	 * @param assignName 
	 */
	public void setAssignName(String assignName) {
		this.assignName = assignName;
	}

	/**
	 * Get cValue.
	 * @return cValue
	 */
	public String getCValue() {
		return cValue;
	}

	/**
	 * This set method is used to Set continent Value.
	 * @param cValue
	 * the value to set
	 */
	public void setCValue(String cValue) {
		this.cValue = cValue;
	}

	/**
	 * Get Territories.
	 * @return trrtrs
	 */
	public List<Territories> getTrrtrs() {
		return trrtrs;
	}

	/**
	 * This set method is used to Set territories.
	 * @param trrtrs 
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

		return " " + assignName  ;	}

}
