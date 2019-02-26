package com.functionalities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.units.Continents;
import com.units.Map;

public class MapFileLoader {
	private Map map;
	private Scanner scan;
	public Map ReadMapFile(File file) {
		/**
		 * This method is use to parse and read map file before converting into {@link Map} object.
		 * This further validates map object.
		 * @param file of type {@link File}
		 * @return map object of type {@link Map
		 */
			this.map = MapFileToMapObject(file);
			//MapValidate.validateMap(map);
			return map;
	}
	private Map MapFileToMapObject(File file) {
		StringBuilder stringBuilder = new StringBuilder();

		Scanner mapFileScanner = null;
		try {
			mapFileScanner = new Scanner(new FileInputStream(file));
			while (mapFileScanner.hasNextLine()) {
				String data = mapFileScanner.nextLine();
				if (!data.isEmpty()) {
					stringBuilder.append(data + "//");
				} else {
					stringBuilder.append("\n");
				}
			}
		} catch (IOException ex) {
			System.out.println("No map file selected");
		}

		mapFileScanner = new Scanner(stringBuilder.toString());
		Map map = scanMap(mapFileScanner);
		mapFileScanner.close();
		return map;
	}
	private Map scanMap(Scanner mapFileScanner) {
		Map map = new Map();

		HashMap<String, String> mapAttributes = new HashMap<>();

		StringTokenizer token = new StringTokenizer(scan.nextLine(), "//");
		while (token.hasMoreTokens()) {
			String element = token.nextToken();
			if (element.equalsIgnoreCase("[Map]")) {
				continue;
			} else {
				String[] data = element.split("=");
				mapAttributes.put(data[0], data[1]);
			}
		}
		map.setMapData(mapAttributes);
		List<Continents> continents = scanContinent(scan);
		HashMap<String, Continents> contMap = new HashMap<>();
		for (Continents continent : continents) {
			contMap.put(continent.getAssignName(), continent);
		}
		map.setContinentMap(contMap);
		map.setContinents(continents);

		return map;
	
	}
	private List<Continents> scanContinent(Scanner scan)  {
		List<Continents> continents = new ArrayList<>();
		StringTokenizer creator = new StringTokenizer(scan.nextLine(), "//");
		while (creator.hasMoreTokens()) {
			String element = creator.nextToken();
			if (element.equalsIgnoreCase("[Continents]")) {
				continue;
			} else {
				Continents continent = new Continents();
				String[] data = element.split("=");
				continent.setAssignName(data[0]);
				continent.setCValue(data[1]);
				continents.add(continent);

	}
		}
		return continents;
}
}
