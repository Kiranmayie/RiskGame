package com.model;

import com.units.Map;
import com.units.Territories;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import com.units.Continents;
/**
 * The Class AuthenticatingEnhanedmap which authenticates the Map is Valid or Invalid.
 */
public class AuthenticatingEnhanedmap {

/** The Static Integer Variable X */
	public static int x;

	/**
	 * Authentication First step.
	 * @param enhancedMap 
	 */
	public static void AuthFStep(Map enhancedMap) {
		if (enhancedMap != null) {
			if (enhancedMap.getContinents().size() > 0) {
				for (Continents cntnt : enhancedMap.getContinents()) {
					if (cntnt != null) {
						if (cntnt.getTrrtrs().size() < 1) {
							System.out.println("Continent: " + cntnt.getAssignName() + " must contain one territory. Restart The game.");
							x=x+1;
							//System.exit(0);
						}
						if (!cntntConnectedToAnotherCntnt(cntnt, enhancedMap)) {
							System.out.println("Continent: " + cntnt.getAssignName().toUpperCase()
									+ " is not connected, should be linked to another continent via territory. Restart The game.");
							x=x+1;
							//System.exit(0);
						}
						for (Territories trrtr : cntnt.getTrrtrs()) {
							if (trrtr != null) {
								List<Territories> partnerTrrtrList = trrtr.getTouchingTrrtrsExpand();
								if (partnerTrrtrList != null && partnerTrrtrList.size() < 1) {
									System.out.println("Territory: " + trrtr.getAssignName() + " must be linked with one adjacent territory. Restart the game.");
									x=x+1;
									//System.exit(0);
								} else if (!isTrrtrInterConnected(trrtr)) {
									x=x+1;
									System.out.println("Territory: " + trrtr.getAssignName() + " is not organized as tree. Restart the game.");
									//System.exit(0);
								} 

							}
						}
					}
				}
			} else {
				System.out.println("Map must have one continent. Restart the game.");
				x=x+1;
				//System.exit(0);
			}
			HashMap<Territories, Integer> trrtrConnection = new HashMap<>();
			for (Continents cntnt : enhancedMap.getContinents()) {
				for (Territories trrtr : cntnt.getTrrtrs()) {
					if (trrtrConnection.containsKey(trrtr)) {
						trrtrConnection.put(trrtr, trrtrConnection.get(trrtr) + 1);
					} else {
						trrtrConnection.put(trrtr, 1);
					}
				}
			}
			for (Entry<Territories, Integer> s : trrtrConnection.entrySet()) {
				if (s.getValue() > 1) {
					System.out.println("Territory: " + s.getKey().getAssignName() + " exists in different continents. Restart the game.");
					x=x+1;
					//System.exit(0);
				}
			}
		} else  {
			System.out.println("Alert: No Map exists in the File selected.");
			x=x+1;
			//System.exit(0);
		}
	}
/**
 * Continents connected to another Continent.
 * @param cntn
 * @param enhancedMap 
 * @return true, if successful
 */
public static boolean cntntConnectedToAnotherCntnt(Continents cntnt, Map enhancedMap) {
	boolean isConnected = false;
	HashSet<Continents> hs = new HashSet<>();
	for (Territories trrtr : cntnt.getTrrtrs()) {
		for (Continents partnerCntnts : enhancedMap.getContinents()) {
			if (!partnerCntnts.equals(cntnt)) {
				for (Territories partnerTrrtr : partnerCntnts.getTrrtrs()) {
					if (partnerTrrtr.getTouchingTrrtrsExpand().contains(trrtr)) {
						hs.add(partnerCntnts);
					}
				}
			}
		}
	}
	if (hs.size() > 0) {
		isConnected = true;
	}
	return isConnected;
}

/**
 * Checks if is Territories inter connected.
 * @param trrtr 
 * @return true, if Territories are inter connected
 */
public static boolean isTrrtrInterConnected(Territories trrtr) {
	HashSet<Territories> hs = new HashSet<>();
	Continents cntnt = trrtr.getLyingInCntnt();
	List<Territories> partnerTrrtrList = cntnt.getTrrtrs();
	hs.add(trrtr);
	trrtr.setConnected(true);
	checkConnection(trrtr, hs);
	for (Territories trrtrs : cntnt.getTrrtrs()) {
		trrtrs.setConnected(false);
	}
	if (hs.containsAll(partnerTrrtrList)) {
		return true;
	} else {
		return false;
	}
}

/**
 * Check connection.
 * @param trrtr 
 * @param hs 
 */
public static void checkConnection(Territories trrtr, HashSet<Territories> hs) {
	boolean isUnConnectedTrrtr = false;
	for (Territories trrtrs : trrtr.getTouchingTrrtrsExpand()) {
		if (trrtrs.getLyingInCntnt().equals(trrtr.getLyingInCntnt()) && !trrtrs.isConnected()) {
			trrtrs.setConnected(true);
			isUnConnectedTrrtr = true;
			hs.add(trrtrs);
			checkConnection(trrtrs, hs);
		}
	}
	if (!isUnConnectedTrrtr) {
		return;
	}
}}
