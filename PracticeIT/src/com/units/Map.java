package com.units;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map implements Serializable {
	
	
	private HashMap<String, String> data;
	 
	
	
	private List<Continents> cntnts;
	
	
	
	private HashMap<String, Continents> cntnsMap;
	
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
