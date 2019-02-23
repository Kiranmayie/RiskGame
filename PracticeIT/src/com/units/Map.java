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
	
	public HashMap<String, Continents> getContinentMap() {
		return cntnsMap;
	}

	
	public void setContinentMap(HashMap<String, Continents> continentMap) {
		this.cntnsMap = continentMap;
	}

	
	public HashMap<String, String> getMapData() { 
		return data;
	}

	
	public void setMapData(HashMap<String, String> mapData) {
		this.data = mapData;
	}

	
	public List<Continents> getContinents() {
		return cntnts;
	}

	
	public void setContinents(List<Continents> continents) {
		this.cntnts = continents;
	}
	
	public String toString() {
		return "Map data: " + data.toString() + "List of continents : " + cntnts.toString();
	}

}
