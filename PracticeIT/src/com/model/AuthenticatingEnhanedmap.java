package com.model;

import com.units.Map;
import com.units.Territories;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import com.units.Continents;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticatingEnhanedmap.
 */
public class AuthenticatingEnhanedmap {

/** The x. */
//
	public static int x;
	
	/**
	 * Auth F step.
	 *
	 * @param enhancedMap the enhanced map
	 */
	public static void AuthFStep(Map enhancedMap) {
		if (enhancedMap != null) {
			if (enhancedMap.getContinents().size() > 0) {
				for (Continents cntnt : enhancedMap.getContinents()) {
					if (cntnt != null) {
						authCntnt(cntnt, enhancedMap);
					}
				}
			} else {
				
				System.out.println("Invalid Map:Map should contain atleast one continent.");
				x=x+1;
			}
			isTerritoryUniquelyAssociated(enhancedMap);
		} else  {
			System.out.println("Empty file no Map exist.");
			x=x+1;
		}
	}


/**
 * Auth cntnt.
 *
 * @param cntnt the cntnt
 * @param enhancedMap the enhanced map
 */
public static void authCntnt(Continents cntnt, Map enhancedMap)  {
	if (cntnt.getTrrtrs().size() < 1) {
		System.out.println("Invalid Map:Continent: " + cntnt.getAssignName() + " should contain atleast one territory");
		x=x+1;
	}
	if (!cntntConnectedToAnotherCntnt(cntnt, enhancedMap)) {
		System.out.println("Continent: " + cntnt.getAssignName().toUpperCase()
				+ " is not a subgraph. The continent should be connected to another continent via territory.");
		x=x+1;
	}
	for (Territories trrtr : cntnt.getTrrtrs()) {
		if (trrtr != null) {
			authTrrtr(trrtr, enhancedMap);
		}
	}
}

/**
 * Cntnt connected to another cntnt.
 *
 * @param cntnt the cntnt
 * @param enhancedMap the enhanced map
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
 * Auth trrtr.
 *
 * @param trrtr the trrtr
 * @param enhancedMap the enhanced map
 */
public static void authTrrtr(Territories trrtr, Map enhancedMap) {

	List<Territories> partnerTrrtrList = trrtr.getTouchingTrrtrsExpand();

	if (partnerTrrtrList != null && partnerTrrtrList.size() < 1) {
		System.out.println("Invalid Map:Territory: " + trrtr.getAssignName() + " should be mapped with atleas one adjacent territory.");
		x=x+1;
	} else if (!isTrrtrInterConnected(trrtr)) {
		System.out.println("Territory: " + trrtr.getAssignName() + " is not forming a connected sub graph.");
		x=x+1;
	} 
}

/**
 * Checks if is trrtr inter connected.
 *
 * @param trrtr the trrtr
 * @return true, if is trrtr inter connected
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
 *
 * @param trrtr the trrtr
 * @param hs the hs
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
}

/**
 * Checks if is territory uniquely associated.
 *
 * @param enhancedMap the enhanced map
 */
public static void isTerritoryUniquelyAssociated(Map enhancedMap)  {
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
			System.out.println("Territory: " + s.getKey().getAssignName() + " belongs to multiple continent.");
			x=x+1;
		}
	}
}
//
}