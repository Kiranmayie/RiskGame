package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;
import javafx.stage.FileChooser;
import com.model.AuthenticatingEnhanedmap;
/**
 * The Class MapSStep.
 */
public class MapSStep {
	
	/** The enhanced map object for Map. */
	Map enhancedMap;
	
	/** The Territory continent aggregation. */
	HashMap<String, Integer> trrtrCntntAggregate = new HashMap<>();
	public static int systemExit=0;
	
	/**
	 * Map file validator.
	 * @return the file
	 */
	public static File mapFileValidator() {
		
		FileChooser fChooser = new FileChooser();
		fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Map (*.map)", "*.map"));
		File mapFile = fChooser.showOpenDialog(null);
		return mapFile;
	}
	
	/**
	 * Reading map file.
	 * @param mapReturnedFile 
	 * @return the map
	 * @throws FileNotFoundException the file not found exception
	 */
	public Map readingMapFile(File mapReturnedFile) throws FileNotFoundException {
		
		this.enhancedMap=conversionOne(mapReturnedFile);
		AuthenticatingEnhanedmap.AuthFStep(enhancedMap);
		return enhancedMap;
	}
	
	/**
	 * Conversion one.
	 * @param mapReturnedFile 
	 * @return the map
	 * @throws FileNotFoundException the file not found exception
	 */
	public Map conversionOne(File mapReturnedFile) throws FileNotFoundException {
		StringBuilder sb=new StringBuilder();
		Scanner sc=new Scanner(new FileReader(mapReturnedFile));
		while (sc.hasNextLine()) {
			String mapData = sc.nextLine();
			if (mapData.isEmpty()) {
				sb.append("\n");	
			} else {
				sb.append(mapData + "//");
			}
		}
		Scanner scNew=new Scanner(sb.toString());
		sc.close();
		Map enhancedMap=conversionTwo(scNew);
		scNew.close();
		return enhancedMap;
	
	}
	
	/**
	 * Conversion two.
	 * @param scNew 
	 * @return the map
	 */
	public Map conversionTwo(Scanner scNew) {
		Map enhancedMap=new Map();
		HashMap<String, String> mapFeatures = new HashMap<>();
		StringTokenizer creator = new StringTokenizer(scNew.nextLine(), "//");
		while(creator.hasMoreTokens()) {
			String element = creator.nextToken();
			if (element.equalsIgnoreCase("[Map]")) {
				continue;
			} else {
				String[] mapData = element.split("=");
				mapFeatures.put(mapData[0], mapData[1]);
			}
		}
		enhancedMap.setMapData(mapFeatures);
		List<Continents> cntnts = conversionThree(scNew);
		HashMap<String, Continents> hashMapOne = new HashMap<>();
		for (Continents cntnt : cntnts) {
			hashMapOne.put(cntnt.getAssignName(), cntnt);
		}
		enhancedMap.setContinentMap(hashMapOne);
		enhancedMap.setContinents(cntnts);
		return enhancedMap;
	}

	/**
	 * Conversion three.
	 * @param scNew 
	 * @return the list
	 */
	public List<Continents> conversionThree(Scanner scNew) {
		List<Continents> cntnts = new ArrayList<>();
		StringTokenizer creator = new StringTokenizer(scNew.nextLine(), "//");
		while (creator.hasMoreTokens()) {
			String element = creator.nextToken();
			if (element.equalsIgnoreCase("[Continents]")) {
				continue;
			} else {
				Continents cntnt = new Continents();
				String[] cntntData = element.split("=");
				cntnt.setAssignName(cntntData[0]);
				cntnt.setCValue(cntntData[1]);
				cntnts.add(cntnt);
			}
		}
		List<Territories> trrtrs = new ArrayList<>();
		while (scNew.hasNext()) {
			String td = scNew.nextLine();
			trrtrs.addAll(conversionFour(td, cntnts));
		}
		HashMap<String, Territories> hashMap = new HashMap<>();
		for (Territories trrtr : trrtrs) {
			hashMap.put(trrtr.getAssignName(), trrtr);
		}
		for (Territories trrtr : trrtrs) {
			for (String var : trrtr.getTouchingTrrtrs()) {
				if (hashMap.containsKey(var)) {
					if (trrtr.getTouchingTrrtrsExpand() == null) {
						trrtr.setTouchingTrrtrsExpand(new ArrayList<Territories>());
					}
					trrtr.getTouchingTrrtrsExpand().add(hashMap.get(var));
				} else {
					System.out.println("Territory: " + var + " not mapped with any continent. Restart The game.");
					systemExit=systemExit+1;
					//System.exit(0);
				}
			}
		}
		for (Continents cntnt : cntnts) {
			HashMap<String, Territories> hashMapOne = new HashMap<>();
			for (Territories trrtr : trrtrs) {
				if (trrtr.getLyingInCntnt().equals(cntnt)) {
					if (cntnt.getTrrtrs() == null) {
						cntnt.seTtrrtrs(new ArrayList<>());
						hashMapOne.put(trrtr.getAssignName(), trrtr);
					}
					cntnt.getTrrtrs().add(trrtr);
					hashMapOne.put(trrtr.getAssignName(), trrtr);
				}
			}
			cntnt.seTtrrtrsMp(hashMapOne);
		}

		return cntnts;
	}
	
	 /**
 	 * Conversion four.
 	 * @param td 
 	 * @param cntnts 
 	 * @return the list
 	 */
 	List<Territories> conversionFour(String td, List<Continents> cntnts){
			List<Territories> trrtrs = new ArrayList<>();
			StringTokenizer creator = new StringTokenizer(td, "//");
			while (creator.hasMoreTokens()) {
				String element = creator.nextToken();
				if (element.equalsIgnoreCase("[Territories]")) {
					continue;
				} else {	
					Territories trrtr = new Territories();
					List<String> touchingTrrtrsExpand  = new ArrayList<>();
					String[] trrtrData = element.split(",");
					trrtr.setAssignName(trrtrData[0]);
					trrtr.setPointX(Integer.parseInt(trrtrData[1]));
					trrtr.setPointY(Integer.parseInt(trrtrData[2]));
				for (Continents cntnt : cntnts) {
					if (cntnt.getAssignName().equalsIgnoreCase(trrtrData[3])) {
						trrtr.setLyingInCntnt(cntnt);
						if (trrtrCntntAggregate.get(trrtrData[0]) == null) {
							trrtrCntntAggregate.put(trrtrData[0], 1);
						} else {
							System.out.println("A Territory cannot be assigned to more than one Continent. Restart The game.");
							//System.exit(0);
							systemExit=systemExit+1;
						}
					}
				}
				if (trrtrCntntAggregate.get(trrtrData[0]) == null) {
					System.out.println("A Territory should be assigned to one Continent. Restart The game.");
					//System.exit(0);
					systemExit=systemExit+1;
				}
				for (int j = 4; j < trrtrData.length; j++) {
					touchingTrrtrsExpand.add(trrtrData[j]);
				}
				trrtr.setTouchingTrrtrs(touchingTrrtrsExpand);
				trrtrs.add(trrtr);
				}
			}
		 return trrtrs;
	 }
}
