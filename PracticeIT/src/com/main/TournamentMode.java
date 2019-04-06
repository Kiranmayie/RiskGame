package com.main;

import java.util.List;

import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TournamentMode implements EventHandler<ActionEvent> {
	/** The enhanced map. */
	public static Map enhancedMap;
	/** The Continents. */
	private Continents cntnts;
	/** The Player Assignment. */
	private PlayersAssignment pa;
	/** The current contestant. */
	private Contestant currentContestant;
	
	/** The Territories 1. */
	private Territories trrtry1;
	
	/** The contestants. */
	private List<Contestant> contestants;
	
	/** The selected territory list. */
	List<Territories> selectedTerritoryList;

	@Override
	public void handle(ActionEvent event) {
		
		
	}

}
