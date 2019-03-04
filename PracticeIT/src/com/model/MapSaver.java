package com.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;



// TODO: Auto-generated Javadoc
/**
 * The Class MapSaver.
 */
public class MapSaver {
	
	
	/**
	 * F step.
	 *
	 * @param map the map
	 * @param file the file
	 */
	public void fStep(Map map, File file) {
		FileWriter fileSaver = null;
	
		try {
			if (map == null) {
				System.out.println("Cannot read the map data");
			}
			String data = changeFormat(map);
			fileSaver = new FileWriter(file, false);
			fileSaver.write(data);
			fileSaver.close();
		} catch (IOException ex) {
			System.out.println("Cannot read data to file");
		}
	}

	/**
	 * Change format.
	 *
	 * @param map the map
	 * @return the string
	 */
	private String changeFormat(Map map) {
		StringBuilder mapIndex = new StringBuilder();
		mapIndex = loadingMapData(map, mapIndex);
		return mapIndex.toString();
	}
	
	/**
	 * Loading map data.
	 *
	 * @param map the map
	 * @param mapIndex the map index
	 * @return the string builder
	 */
	private StringBuilder loadingMapData(Map map, StringBuilder mapIndex) {
		mapIndex.append("[Map]");
		mapIndex.append("\n");
		for (java.util.Map.Entry<String, String> e : map.getMapData().entrySet()) {
			mapIndex.append(e.getKey() + "=" + e.getValue());
			mapIndex.append("\n");
		}
		mapIndex = LoadingCntntData(map, mapIndex);
		return mapIndex;
	}
	
	/**
	 * Loading cntnt data.
	 *
	 * @param map the map
	 * @param mapIndex the map index
	 * @return the string builder
	 */
	private StringBuilder LoadingCntntData(Map map, StringBuilder mapIndex) {
		mapIndex.append("\n");
		mapIndex.append("[Continents]");
		mapIndex.append("\n");
		for (Continents continent : map.getContinents()) {
			mapIndex.append(continent.getAssignName() + "=" + continent.getCValue());
			mapIndex.append("\n");
		}
		mapIndex = loadingTrrtrsData(map, mapIndex);
		return mapIndex;
	}
	
	/**
	 * Loading trrtrs data.
	 *
	 * @param map the map
	 * @param mapIndex the map index
	 * @return the string builder
	 */
	private StringBuilder loadingTrrtrsData(Map map, StringBuilder mapIndex) {
		mapIndex.append("\n");
		mapIndex.append("[Territories]");
		mapIndex.append("\n");
		for (Continents continent : map.getContinents()) {
			List<Territories> territories = continent.getTrrtrs();
			if (territories != null) {
				for (Territories territory : continent.getTrrtrs()) {
					mapIndex.append(territory.getAssignName() + "," + territory.getPointX() + ","
							+ territory.getPointY() + "," + territory.getLyingInCntnt().getAssignName());
					for (Territories adjTerritory : territory.getTouchingTrrtrsExpand()) {
						mapIndex.append(",");
						mapIndex.append(adjTerritory.getAssignName());
					}
					mapIndex.append("\n");
				}
				mapIndex.append("\n");
			} else {
				System.out.println("Error");
			}
		}
		return mapIndex;
	}

}

