package com.controller;
//package com.risk.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is an entity class for Map with mapData, continents, continentMap and territories as its member variables.
 * @author rahul
 * @version 1.0.0
 */
public class Map implements Serializable{
	
	/**
	 * The @mapData.
	 */
	private HashMap<String, String> mapData;
	
	/**
	 * The @continents.
	 */
	private List<Continents> continents;
	
	/**
	 * The @continentMap.
	 */
	private HashMap<String, Continents> continentMap;
	
	/**
	 * Constructor for Map 
	 */
	public Map() {
		mapData = new HashMap<>();
		continents = new ArrayList<>();
		continentMap = new HashMap<>();
	}

	/**
	 * Get Continent Map
	 * 
	 * @return continentMap
	 */
	public HashMap<String, Continents> getContinentMap() {
		return continentMap;
	}

	/**
	 * Set Continent Map
	 * 
	 * @param continentMap
	 *            the continentMap to set
	 */
	public void setContinentMap(HashMap<String, Continents> continentMap) {
		this.continentMap = continentMap;
	}

	/**
	 * Get Map Data
	 * 
	 * @return mapData
	 */
	public HashMap<String, String> getMapData() {
		return mapData;
	}

	/**
	 * Set Map Data
	 * 
	 * @param mapData
	 *            the mapData to set
	 */
	public void setMapData(HashMap<String, String> mapData) {
		this.mapData = mapData;
	}

	/**
	 * Get continents
	 * 
	 * @return continents
	 */
	public List<Continents> getContinents() {
		return continents;
	}

	/**
	 * Set Continents
	 * 
	 * @param continents
	 *            the continents to set
	 */
	public void setContinents(List<Continents> continents) {
		this.continents = continents;
	}
	
	/**
	 * @return a string after converting value from mapData object.
	 */
	public String toString() {
		return "Map data: " + mapData.toString() + "List of continents : " + continents.toString();
	}
}
