package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

class ContestantTerritories {
	
	static PlayersAssignment pa;
	
	/**
	 * The @continent reference
	 */
	static Continents continent;
	
	/**
	 * The @territory1
	 */
	static Territories territory1;
	
	/**
	 * The @territory2
	 */
	static Territories territory2;
	
	/**
	 * The @map reference
	 */
	static Map map;
	
	
	static Contestant contestant;
	
	
	String territoryName1 = "India";
	
	/**
	 * The @territoryName2
	 */
	String territoryName2 = "China";	
	
	/**
	 * The @listOfContinents
	 */
	static List<Continents> listOfContinents;
	
	/**
	 * The @listOfTerritories
	 */
	static List<Territories> listOfTerritories;
	
	/**
	 * The @listOfPlayers
	 */
	static List<Contestant> listOfPlayers;
	
	/**
	 * This method is invoked at the start of the test class.
	 */
	@BeforeClass
	public static void beforeClass() {
		pa = new PlayersAssignment();
		continent = new Continents();
		territory1 = new Territories();
		territory2 = new Territories();
		map = new Map();
		contestant = new Contestant(1);
		listOfContinents = new ArrayList<>();
		listOfTerritories = new ArrayList<>();
		listOfPlayers = new ArrayList<>();
				
	}	
	
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@Before
	public void beforeTest() {
		
		
		territory1.setAssignName(territoryName1);
		territory1.setLyingInCntnt(continent);
		territory2.setAssignName(territoryName2);
		territory2.getTouchingTrrtrs().add(territoryName2);
		territory1.setTouchingTrrtrs(territory2.getTouchingTrrtrs());		
		territory1.getTouchingTrrtrs().add(territoryName1);
		territory2.setTouchingTrrtrs(territory1.getTouchingTrrtrs());
		
		listOfContinents.add(continent);
		map.setContinents(listOfContinents);
		
		contestant.setBatallion(100);
		
		listOfTerritories.add(territory1);
		contestant.setcontestantTrrtrlist(listOfTerritories);
		listOfPlayers.add(contestant);
	}
	

	@Test
	public void assignTerritoryToPlayer() {
		List<Contestant> players = pa.territoryAssignToContestant(map, listOfPlayers);
		Assert.assertNotNull(players);		

	}

}
