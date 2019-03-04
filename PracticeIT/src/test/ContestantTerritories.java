/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

/**
 * @author Pritpal Kaur
 *
 */
public class ContestantTerritories {

	/**
	 * Test method for {@link com.model.PlayersAssignment#playersArmyAssign(java.util.List)}.
	 */
	
static PlayersAssignment playersAssignment;
	
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
	
	/**
	 * The @player 
	 */
	static Contestant contestant;
	/**
	 * The @territoryName1
	 */
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
	@BeforeClass
	public static void beforeClass() {
		playersAssignment = new PlayersAssignment();
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
		
		territory2.setAssignName(territoryName2);
				
		contestant.setBatallion(100);
		
		listOfTerritories.add(territory1);
		playersAssignment.territoryAssignToContestant(map, listOfPlayers);
		listOfPlayers.add(contestant);
	}
	/**
	 * Test method for {@link com.model.PlayersAssignment#territoryAssignToContestant(com.units.Map, java.util.List)}.
	 */
	@Test
	public void testTerritoryAssignToContestant() {
		List<Contestant> contestant = playersAssignment.territoryAssignToContestant(map, listOfPlayers);
		Assert.assertNotNull(contestant);	 // TODO
	}

}
