package com.model;

import java.util.ArrayList;
import java.util.List;
import com.units.*;
import com.units.Map;
/**
 * The Class MapMiniature has functionalities for Map Build Controller.
 */
public class MapMiniature {
	
	/**
	 * Adding continent.
	 * @param map 
	 * @param contName 
	 * @param cValue
	 * @return the continents
	 * @throws Exception 
	 */
	public Continents addingContinent(Map map,String contName,String cValue) throws Exception{
		Continents continent= new Continents();
		continent.setAssignName(contName);
		continent.setCValue(cValue);
		if(map.getContinents().contains(continent)) 
		System.out.println("Continent exists");
		return continent;	
	}
	/**
	 * Update territories.
	 * @param territory 
	 * @param xAxis 
	 * @param yAxis 
	 * @param adjTerritory 
	 * @return the territories
	 */
	public Territories updateTerritories(Territories territory, int xAxis, int yAxis, Territories adjTerritory) {
		System.out.println("updated terrtrs");
		territory.setPointX(xAxis);
		territory.setPointY(yAxis);
		if (adjTerritory != null) {
			if (!territory.getTouchingTrrtrsExpand().contains(adjTerritory)) {
				territory.getTouchingTrrtrsExpand().add(adjTerritory);
			}
			if (!adjTerritory.getTouchingTrrtrsExpand().contains(territory)) {
				adjTerritory.getTouchingTrrtrsExpand().add(territory);
			}
		}

		return territory;
	}

	/**
	 * Updating continent.
	 * @param continent 
	 * @param cValue 
	 * @return the continents
	 */
	public Continents updatingContinent(Continents continent,String cValue){
		continent.setCValue(cValue);
		return continent;
	}

	/**
	 * Adds the territory.
	 * @param map 
	 * @param assignName 
	 * @param xAxis 
	 * @param yAxis 
	 * @param adjTerritory
	 * @param continent
	 * @return the territories
	 * @throws Exception
	 */
	public Territories addTerritory(Map map, String assignName, String xAxis, String yAxis, Territories adjTerritory,
			Continents continent) throws Exception {
		Territories territory = new Territories();
		List<Territories> tList = new ArrayList<>();
		territory.setAssignName(assignName);
		territory.setPointX(Integer.parseInt(xAxis));
		territory.setPointY(Integer.parseInt(yAxis));
		territory.setLyingInCntnt(continent);
		if (adjTerritory != null) {
			tList.add(adjTerritory);
		}
		territory.setTouchingTrrtrsExpand(tList);
		for (Continents existContinent : map.getContinents()) {
			if (existContinent.getTrrtrs().contains(territory)) {
				throw new Exception(
						"Territory: " + assignName + " already exist in continent " + existContinent.getAssignName());
			}
		}
		if (adjTerritory != null) {
			adjTerritory.getTouchingTrrtrsExpand().add(territory);
		}
		return territory;
	}

	/**
	 * Assign territory to continent.
	 * @param continent 
	 * @param territory 
	 * @return the continents
	 */
	public Continents assignTerrToContinent(Continents continent, Territories territory) {
		if (continent.getTrrtrs() == null) {
			List<Territories> nTList = new ArrayList<>();
			nTList.add(territory);
			continent.seTtrrtrs(nTList);
		} else {
			continent.getTrrtrs().add(territory);
		}
		return continent;
	}
}
