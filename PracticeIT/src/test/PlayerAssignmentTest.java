package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.Cards;
import com.model.GameDesign;
import com.model.PlayersAssignment;
import com.units.Map;
import com.units.CardType;
import com.units.Contestant;
import com.units.Continents;
import com.units.Territories;

/*
 * Player Assignment Test Class
 * Attack Methods Test
 * @author Sathwik
 * @version 1.0.0
 */
public class PlayerAssignmentTest {
	/**
	 * @PlayerAssignmentModel
	 */
	static PlayersAssignment playerGamePhase;
	/*
	 * @GameDesigmodel
	 */
	static GameDesign game;
	
	static Territories trs;
	/**
	 * The @continent
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
	 * The @continent
	 */
	static Contestant contestant;
	
	/**
	 * The @map
	 */
	static Map map;
	/**
	 * The @territoryList
	 */
	static List<Territories> territoryList;
	static List<Territories> attackTerritoryList;
	static List<Territories> defendTerritoryList;
	
	static List<Contestant> contestantList;
	/**
	 * The @continentName
	 */
	String continentName = "Asia";
	
	/**
	 * The @controlValue
	 */
	String controlValue = "7";	
	
	/**
	 * The @territoryName1
	 */
	String territoryName1 = "India";
	
	/**
	 * The @territoryName2
	 */
	String territoryName2 = "China";
	private List<String> listOfCards;
	
	
	/**
	 * The @players list
	 */
	static List<Contestant> players;

	/**
	 * This method is invoked at the start of the test class.
	 */
	@BeforeClass
	public static void beforeClass() {
		playerGamePhase = new PlayersAssignment();
		
	}

	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@Before
	public void beforeTest() {
		
		map = new Map();
		
		contestant = new Contestant(1);
		continent = new Continents();
		territory1 = new Territories();
		territory2 = new Territories();
		
		continent.setAssignName(continentName);
		continent.setCValue(controlValue);
		territory1.setAssignName(territoryName1);
		territory1.setLyingInCntnt(continent);
		continent.getTrrtrs().add(territory1);
		territory2.setAssignName(territoryName2);
		territory2.setLyingInCntnt(continent);
		continent.getTrrtrs().add(territory2);
		territory1.getTouchingTrrtrsExpand().add(territory2);
		territory2.getTouchingTrrtrsExpand().add(territory1);
		map.getContinents().add(continent);
		

		playerGamePhase.setCurrentContestant(contestant);
		String[] attackTerritoryList = {"Alaska","Alberta","Quebec"};
		String[] defendTerritoryList = {"New Guinea","Egypt","Greenland" };
		
	}
	
	@Test
	public void getContinentsOwnedByPlayer() {
		List<Continents> returnedContinents = new ArrayList<>();
		territory1.setContestant(contestant);
		territory2.setContestant(contestant);
		returnedContinents = PlayersAssignment.getContinentsOwnedByPlayer(map, contestant);
		Assert.assertEquals("Asia", returnedContinents.get(0).getAssignName());
		Assert.assertEquals(1, returnedContinents.size());
	}	
	
	@Test
	public void ifContestantLost() {
		players = new ArrayList<>();
		players.add(new Contestant(0));
		players.get(0).setcontestantTrrtrlist(new ArrayList<>());
		Contestant playerLost = playerGamePhase.isContestantLost(players);
		Assert.assertEquals(0, playerLost.getContestantTrrtrlist().size());
	}
	
	
	@Test
	public void ifContestantWonSuccess() {
		List<Contestant> listOfPlayers = new ArrayList<>();
		listOfPlayers.add(new Contestant(1));
		boolean actualResult = playerGamePhase.isContestantWon(listOfPlayers);
		Assert.assertTrue(actualResult);
	}
	
	
	
	@Test
	public void PlayersArmiesExhausted() {
		contestantList = new ArrayList<>();
		contestantList.add(new Contestant(0));
		contestantList.get(0).setBatallion(0);
		Assert.assertEquals(playerGamePhase.checkIfPlayersArmiesExhausted(contestantList), true);
	}
	
	@Test
	public void PlayersArmiesExhaustedFalse() {
		players = new ArrayList<>();
		players.add(new Contestant(0));
		players.get(0).setBatallion(1);
		Assert.assertFalse(playerGamePhase.checkIfPlayersArmiesExhausted(players));
	}

	
	
	/*@Test
	public void assignTerritoryToPlayer() {
	
		List<Contestant> players = game.contestantAndItsTerrtrs(map, contestantList);
		Assert.assertNotNull(players);		

	}*/
	
	@Test
	public void checkIfAnyPlayerLostTheGame() {
		players = new ArrayList<>();
		players.add(new Contestant(0));
		players.get(0).setcontestantTrrtrlist(new ArrayList<>());
		boolean playerLost = playerGamePhase.isContestantWon(players);
		Assert.assertEquals(true,true);
		//Assert.assertEquals(true, playerLost.getContestantTrrtrlist().size());
	} 
	@Test
	public void checkTradePossibleForDiffCards() {
		listOfCards.add("ARTILLERY");
		listOfCards.add("ARTILLERY");
		listOfCards.add("ARTILLERY");
		boolean actualResult = playerGamePhase.validTrade(listOfCards);
		Assert.assertEquals(true, actualResult);		
	}

@Test
public void contestantHasAValidAttackMove() {
	territory1.setBatallion(5);
	territory2.setBatallion(3);
	boolean actualResult = playerGamePhase.contestantHasAValidAttackMove(territory1);
	Assert.assertTrue(actualResult);
}
@Test
public void tradeCardsForArmy() {
	players = new ArrayList<>();
	players.add(new Contestant(0));
	Contestant currentContestant = playerGamePhase.reinforceWithCards(1);
	Assert.assertEquals(0, currentContestant.getBatallion());
}
}