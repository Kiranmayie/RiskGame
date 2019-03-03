package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO: Auto-generated Javadoc
/*
 * Map files to initiALIZE CONTINENTS
 */

/**
 * The Class Map.
 */
public class Map implements Serializable {
	
	/** The data. */
	private HashMap<String, String> data;
	 
	
	/** The cntnts. */
	private List<Continents> cntnts;
	
	
	/** The cntns map. */
	private HashMap<String, Continents> cntnsMap;
	
	/**
	 * Instantiates a new map.
	 */
	public Map() {
		data = new HashMap<>();
		cntnts = new ArrayList<>();
		cntnsMap = new HashMap<>();
	}
	
	/**
	 * Gets the continent map.
	 *
	 * @return the continent map
	 */
	public HashMap<String, Continents> getContinentMap() {
		return cntnsMap;
	}

	
	/**
	 * Sets the continent map.
	 *
	 * @param continentMap the continent map
	 */
	public void setContinentMap(HashMap<String, Continents> continentMap) {
		this.cntnsMap = continentMap;
	}

	
	/**
	 * Gets the map data.
	 *
	 * @return the map data
	 */
	public HashMap<String, String> getMapData() { 
		return data;
	}

	
	/**
	 * Sets the map data.
	 *
	 * @param mapData the map data
	 */
	public void setMapData(HashMap<String, String> mapData) {
		this.data = mapData;
	}

	
	/**
	 * Gets the continents.
	 *
	 * @return the continents
	 */
	public List<Continents> getContinents() {
		return cntnts;
	}

	
	/**
	 * Sets the continents.
	 *
	 * @param continents the new continents
	 */
	public void setContinents(List<Continents> continents) {
		this.cntnts = continents;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Map data: " + data.toString() + "List of continents : " + cntnts.toString();
	}

}
