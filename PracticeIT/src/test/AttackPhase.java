/**
 * 
 */
package test;



import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.PlayersAssignment;
import com.units.Contestant;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;



// TODO: Auto-generated Javadoc
/**
 * The Class AttackPhase.
 *
 * @author k_bethi
 */

public class AttackPhase {

/** The players assignment. */
static PlayersAssignment playersAssignment;
   
   /**
    * The player game phase.
    *
    * @PlayersAssignment reference
    */
static PlayersAssignment playerGamePhase;

	/** The @continent reference. */
	static Continents continent;
	
	/** The contestant list. */
	static List<Contestant> contestantList;
	
	/** The @territory1. */
	static Territories territory1;
	
	/** The @territory2. */
	static Territories territory2;
	
	/** The @map reference. */
	static Map map;
	
	/** The @Contestant. */
	static Contestant contestant;
	
	/** The territory name 1. */
	String territoryName1 = "India";
	
	/** The territory name 2. */
	String territoryName2 = "China";

	

	/** The @listOfContinents. */
	static List<Continents> listOfContinents;
	
	/** The @listOfTerritories. */
	static List<Territories> listOfTerritories;
	
	/** The @listOfcontestant. */
	static List<Contestant> listOfcontestant;
	
	/**
	 * Test method for {@link com.model.PlayersAssignment#attackPhase(java.util.List, java.util.List, com.units.Contestant)}.
	 */
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
	 * This method tests if a player has zero armies.
	 */
	/*@Test
	public void checkIfPlayersArmiesExhausted() {
		contestantList = new ArrayList<>();
		contestantList.add(new Contestant(0));
		contestantList.get(0).setBatallion(0);
		//System.out.println("Hello");
		Assert.assertEquals(playerGamePhase.checkIfPlayersArmiesExhausted(contestantList), true);
	}*/
	
	@Test
	public final void testAttackPhaseListOfTerritoriesListOfTerritoriesContestant() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.model.PlayersAssignment#fortificationPhase(java.util.List, java.util.List, com.units.Contestant)}.
	 */
	/*@Test
	public final void testFortificationPhase() {
		territory1.setContestant(contestant);
		territory1.setBatallion(2);
		territory2.setContestant(contestant);
		boolean ifPhaseValid = playerGamePhase.fortificationPhase(listOfTerritories,listOfTerritories,contestant);
		Assert.assertEquals(true, ifPhaseValid);
		fail("Not yet implemented"); // TODO
	}*/

	/**
	 * Test method for {@link com.model.PlayersAssignment#isFortificationPhaseValid(com.units.Map, com.units.Contestant)}.
	 */
	@Test
	public final void testIsFortificationPhaseValid() {
		fail("Not yet implemented"); // TODO
	}


	}


