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
 


public class MapSStep {
	
	Map enhancedMap;
	HashMap<String, Integer> trrtrCntntAggregate = new HashMap<>();
	
	public static File mapFileValidator() {
		
		FileChooser fChooser = new FileChooser();
		fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Map (*.map)", "*.map"));
		File mapFile = fChooser.showOpenDialog(null);
		return mapFile;
	}
	
	public Map readingMapFile(File mapReturnedFile) throws FileNotFoundException {
		
		this.enhancedMap=conversionOne(mapReturnedFile);

		//System.out.println(enhancedMap.toString());

		AuthenticatingEnhanedmap.AuthFStep(enhancedMap);
		//System.out.println(enhancedMap.toString());
		return enhancedMap;
	}
	
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
		//System.out.println(sb);
		Scanner scNew=new Scanner(sb.toString());
		sc.close();
		Map enhancedMap=conversionTwo(scNew);
		scNew.close();
		return enhancedMap;
	
	}
	
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

	public List<Continents> conversionThree(Scanner scNew) {
		// TODO Auto-generated method stub
		
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
					System.out.println("Territory: " + var + " not mapped with any continent.");
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
							System.out.println("A Territory cannot be assigned to more than one Continent.");
						}
					}
				}
				if (trrtrCntntAggregate.get(trrtrData[0]) == null) {
					System.out.println("A Territory should be assigned to one Continent.");
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
