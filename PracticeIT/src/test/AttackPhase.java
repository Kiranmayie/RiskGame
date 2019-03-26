/**
 * 
 */
package test;



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



/**
 * @author k_bethi
 *
 */

public class AttackPhase {
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
	@Test
	public final void testAttackPhaseListOfTerritoriesListOfTerritoriesContestant() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.model.PlayersAssignment#fortificationPhase(java.util.List, java.util.List, com.units.Contestant)}.
	 */
	@Test
	public final void testFortificationPhase() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.model.PlayersAssignment#isFortificationPhaseValid(com.units.Map, com.units.Contestant)}.
	 */
	@Test
	public final void testIsFortificationPhaseValid() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.model.PlayersAssignment#isContestantLost(java.util.List)}.
	 */
	@Test
	public final void testIsContestantLost() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.model.PlayersAssignment#isContestantWon(java.util.List)}.
	 * @return 
	 */
	@Test
	public final void testIsContestantWon() {
		boolean ContestantWon = false;
		if (listOfcontestant.size() == 1) {
			ContestantWon = true;
		}
		Assert.assertEquals(isContestantWon(listOfcontestant contestant), true);
		
	}

}
