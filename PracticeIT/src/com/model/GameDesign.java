package com.model;

import java.util.ArrayList;
import java.util.List;

import com.units.Contestant;
import com.units.Territories;
import com.units.Continents;
import com.units.Map;

import javafx.scene.control.TextArea;

public class GameDesign {

	
	public List<Contestant> contestantAndItsTerrtrs(Map map, List<Contestant> contestants, TextArea textAres) {

		List<Territories> sumTrrtrs = new ArrayList<>();

		if (map.getContinents() != null) {
			for (Continents cntnt : map.getContinents()) {
				if (cntnt != null && cntnt.getTrrtrs() != null) {
					for (Territories trrtr : cntnt.getTrrtrs()) {
						sumTrrtrs.add(trrtr);
					}
				}
			}
		}
		int count = 0;
		int totalTerritory = sumTrrtrs.size();
		while (count < totalTerritory) {
			for (Contestant contestant : contestants) {
				for (Territories trrtr : sumTrrtrs) {
					if (trrtr.getContestant() == null) {
						count++;
						trrtr.setContestant(contestant);
						trrtr.setBatallion(trrtr.getBatallion() + 1);
						contestant.setBatallion(contestant.getBatallion() - 1);
						contestant.getContestantTrrtrlist().add(trrtr);
						
						System.out.println(trrtr.getAssignName() + " assigned to " + contestant.getContestantName() + " ! \n");
						break;
					}
					continue;
				}
			}
		}
		return contestants;
	}
}
