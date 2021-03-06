package com.model;

import java.util.ArrayList;
import java.util.List;
import com.units.*;
import com.units.Map;
// TODO: Auto-generated Javadoc
/**
 * The Class MapMiniature has functionalities for Map Build Controller.
 */
public class MapMiniature {
	
	/**
	 * Adding continent.
	 *
	 * @param map the map
	 * @param contName the cont name
	 * @param cValue the c value
	 * @return the continents
	 * @throws Exception the exception
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
	 *
	 * @param territory the territory
	 * @param xAxis the x axis
	 * @param yAxis the y axis
	 * @param adjTerritory the adj territory
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
	 *
	 * @param continent the continent
	 * @param cValue the c value
	 * @return the continents
	 */
	public Continents updatingContinent(Continents continent,String cValue){
		continent.setCValue(cValue);
		return continent;
	}

	/**
	 * Adds the territory.
	 *
	 * @param map the map
	 * @param assignName the assign name
	 * @param xAxis the x axis
	 * @param yAxis the y axis
	 * @param adjTerritory the adj territory
	 * @param continent the continent
	 * @return the territories
	 * @throws Exception the exception
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
	 *
	 * @param continent the continent
	 * @param territory the territory
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
