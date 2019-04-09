package test;

import static org.junit.Assert.*;

import org.junit.Test;
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
import ConcretePatterns.Human;
public class Reinforce {

	/*
	 * Player Assignment Model Test class
	 * @author Sathwik
	 * @version 1.0.1
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
	@Test
	public final void testGetReinforceBatallionMapContestant() {
	
			contestant.setBatallion(8);
			playersAssignment.territoryAssignToContestant(map, listOfcontestant);
			territory1.setContestant(contestant);
			playersAssignment.territoryAssignToContestant(map, listOfcontestant);
			territory2.setContestant(contestant);;
			Contestant returningContestant = Human.getReinforceBatallion(map, contestant);

			Assert.assertEquals(returningContestant.getBatallion(), 11);
		}

	@Test
	public void calculateReinforcementArmiesCaseOne() {
		contestant.setBatallion(35);
		contestant.getcontestantTrrtrlist().add(territory1);
		territory1.setContestant(contestant);
		contestant.getcontestantTrrtrlist().add(territory2);
		territory2.setContestant(contestant);
		Contestant returnedPlayer = Human.getReinforceBatallion(map, contestant);
		Assert.assertEquals(returnedPlayer.getBatallion(), 38);
	}
	
	/**
	 * This method tests number of armies for 2 continents during each reinforcement
	 * phase.
	 */
	@Test
	public void calculateReinforcementArmies() {
		Continents newContinent = new Continents();
		Territories terr = new Territories();
		newContinent.setAssignName("Africa");
		newContinent.setCValue("5");
		terr.setAssignName("New Territory");
		terr.setLyingInCntnt(newContinent);
		terr.getTouchingTrrtrsExpand().add(territory1);
		territory1.getTouchingTrrtrsExpand().add(terr);
		newContinent.getTrrtrs().add(terr);
		map.getContinents().add(newContinent);
		contestant.setBatallion(35);
		contestant.getcontestantTrrtrlist().add(territory1);
		territory1.setContestant(contestant);
		contestant.getcontestantTrrtrlist().add(territory2);
		territory2.setContestant(contestant);
		contestant.getcontestantTrrtrlist().add(terr);
		terr.setContestant(contestant);
		Contestant returnedPlayer = Human.getReinforceBatallion(map, contestant);
		Assert.assertEquals(returnedPlayer.getBatallion(),43 );
	}

	
	
	}


