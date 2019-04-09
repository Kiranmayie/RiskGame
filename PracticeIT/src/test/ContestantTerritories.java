package test;

import static org.junit.Assert.*;

import org.junit.Test;
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
public class ContestantTerritories {

	/**
	 * Test method for {@link com.model.contestantAssignment#contestantArmyAssign(java.util.List)}.
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
	 * The @Contestant 
	 */
	static Contestant contestant;
	String territoryName1 = "India";
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
	 * The @listOfcontestant
	 */
	static List<Contestant> listOfcontestant;
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
		listOfcontestant = new ArrayList<>();
	}
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@Before
	public void beforeTest() {
		
		territory1.setAssignName(territoryName1);
		
		territory2.setAssignName(territoryName2);
				
		contestant.setBatallion(35);
		
		listOfTerritories.add(territory1);
		playersAssignment.territoryAssignToContestant(map, listOfcontestant);
		listOfcontestant.add(contestant);
	}
	/**
	 * Test method for {@link com.model.contestantAssignment#territoryAssignToContestant(com.units.Map, java.util.List)}.
	 */
	@Test
	public void testCreateContestant() {
		List<Contestant> contestants = new ArrayList<>();
		listOfcontestant = new ArrayList<>();
		listOfcontestant.add(new Contestant(0));
		listOfcontestant.add(new Contestant(1));
		listOfcontestant.add(new Contestant(2));
		playersAssignment.createContestant(listOfcontestant.size(), contestants);
		Assert.assertEquals(3, contestants.size());
	}
	@Test
	public void testPlayersArmyAssign4() {
		contestant.setBatallion(20);
		listOfcontestant = new ArrayList<>();
		listOfcontestant.add(new Contestant(1));
		listOfcontestant.add(new Contestant(2));
		listOfcontestant.add(new Contestant(3));
		listOfcontestant.add(new Contestant(4));
		listOfcontestant.add(new Contestant(5));
		Assert.assertEquals(playersAssignment.SIX_PLAYER_ARMIES, (Integer) contestant.getBatallion());
		
	}
	@Test
	public void testTerritoryAssignToContestant() {
		List<Contestant> contestant = playersAssignment.territoryAssignToContestant(map, listOfcontestant);
		Assert.assertNotNull(contestant);	 // TODO
	}
	
	

	
	@Test
	public void testPlayersArmyAssign1() {
		contestant.setBatallion(35);
		listOfcontestant = new ArrayList<>();
		listOfcontestant.add(new Contestant(1));
		listOfcontestant.add(new Contestant(2));
		listOfcontestant.add(new Contestant(3));
		Assert.assertEquals(playersAssignment.THREE_PLAYER_ARMIES, (Integer) contestant.getBatallion());
	}

	@Test
	public void testPlayersArmyAssign2() {
		contestant.setBatallion(30);
		listOfcontestant = new ArrayList<>();
		listOfcontestant.add(new Contestant(1));
		listOfcontestant.add(new Contestant(2));
		listOfcontestant.add(new Contestant(3));
		listOfcontestant.add(new Contestant(4));
		Assert.assertEquals(playersAssignment.FOUR_PLAYER_ARMIES, (Integer) contestant.getBatallion());
		
	}
	@Test
	public void testPlayersArmyAssign3() {
		contestant.setBatallion(25);
		listOfcontestant = new ArrayList<>();
		listOfcontestant.add(new Contestant(1));
		listOfcontestant.add(new Contestant(2));
		listOfcontestant.add(new Contestant(3));
		listOfcontestant.add(new Contestant(4));
		listOfcontestant.add(new Contestant(5));
		Assert.assertEquals(playersAssignment.FIVE_PLAYER_ARMIES, (Integer) contestant.getBatallion());
		
	}
}
