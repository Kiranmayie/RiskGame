package test;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.main.MapSStep;
import com.model.AuthenticatingEnhanedmap;
import com.model.MapMiniature;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

import javafx.stage.FileChooser;
// TODO: Auto-generated Javadoc

/**
 * The Class MapValidate.
 */
public class MapValidate {

/** The map validator. */
static AuthenticatingEnhanedmap mapValidator;
	
	/** The continent. */
	static Continents continent;
	
	/** The territory. */
	static Territories territory;
	
	/** The map. */
	static Map map;
	
	/** The map mini. */
	static MapMiniature mapMini;	
	
	/** The Author. */
	String Author = "Sean O'Connor";
	
	/** The Image. */
	String Image = "world.bmp";
	
	/** The Wrap. */
	String Wrap = "no";
	
	/** The Scroll. */
	String Scroll = "horizontal";
	
	/** The Warn. */
	String Warn = "yes";
	
	/** The continent name. */
	String continentName = "Asia";
	
	/** The control value. */
	String controlValue = "7";
	
	/** The control value 1. */
	String controlValue1 = "2";
	
	/** The territory name. */
	String territoryName = "India";
	
	/** The x axis 1. */
	String xAxis1 = "1";
	
	/** The x axis 2. */
	String xAxis2 = "2";
	
	/** The y axis 1. */
	String yAxis1 = "1";
	
	/** The y axis 2. */
	String yAxis2 = "2";
	
	/** The map value. */
	static HashMap<String, String> mapValue;

	/** The mrm. */
	public static MapSStep mrm;
	
	/** The Continents list. */
	List<Continents> ContinentsList;

	/** The file. */
	public File file;

   
	
	/**
	 * This method is invoked at the start of the test class.
	 */
	@BeforeClass
	public static void beforeClass() {
		continent = new Continents();
		territory = new Territories();
		map = new Map();
		mapValidator = new AuthenticatingEnhanedmap();
		mrm = new MapSStep();
		mapMini =new MapMiniature(); 
	}	
	
	/**
	 * This method is invoked at the start of all the test methods.
	 */
	@Before
	public void beforeTest() {
		
			
		mapValue = new HashMap<>();
		mapValue.put("author", Author);
		mapValue.put("image", Image);
		mapValue.put("wrap", Wrap);
		mapValue.put("scroll", Scroll);
		mapValue.put("warn", Warn);		
		map.setMapData(mapValue);
		
		continent.setAssignName(continentName);
		continent.setCValue(controlValue);
		
		ContinentsList = new ArrayList<>();
		ContinentsList.add(continent);
	}
		
	
	/**
	 * Validate map for null map.
	 */
	@Test 
	public void validateMapForNullMap()  {
		AuthenticatingEnhanedmap.AuthFStep(null);
	}
	
	
	/**
	 * Validate map for continent.
	 */
	@Test 
	public void validateMapForContinent() {
		AuthenticatingEnhanedmap.AuthFStep(new Map());
	}
	
	/**
	 * Check valid number of continents.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	@Test
	public void checkValidNumberOfContinents() throws FileNotFoundException {
		file = new File("C:/Users/sathw_000/Desktop/Map Files/World.map");
		Map map = mrm.readingMapFile(file);
		Assert.assertEquals(map.getContinents().size(), 7);
	}
	
	/**
	 * Check invalid map for continent without territory.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	@Test 
	public void checkInvalidMapForContinentWithoutTerritory() throws FileNotFoundException {
		file = new File("C:/Users/sathw_000/Desktop/Map Files/World_Invalid_Continent.map");
		Map map = mrm.readingMapFile(file);
		
		
	}
	
	/**
	 * Check invalid map for territory subgraph.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	@Test
	public void checkInvalidMapForTerritorySubgraph() throws FileNotFoundException  {
		file = new File("C:/Users/sathw_000/Desktop/Map Files/World_Invalid_Territory_Subgraph.map");
		Map map = mrm.readingMapFile(file);
		
	}
	
	
	/**
	 * Check invalid map for continent subgraph.
	 *
	 * @throws FileNotFoundException the file not found exception
	 */
	@Test 
	public void checkInvalidMapForContinentSubgraph() throws FileNotFoundException  {
		file = new File("C:/Users/sathw_000/Desktop/Map Files/World_Invalid_Continent_Subgraph.map");
		Map map = mrm.readingMapFile(file);
	}

	/**
	 * Adds the continent.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addContinent() throws Exception {
		continent =  mapMini.addingContinent(map, continentName, controlValue1);
		Assert.assertNotNull(continent);
		Assert.assertEquals(continent.getAssignName(), continentName);
		Assert.assertEquals(continent.getCValue(), controlValue1);
	}
	
	/**
	 * Update continent.
	 */
	@Test
	public void updateContinent() {
		continent =  mapMini.updatingContinent(continent, controlValue1);
		Assert.assertNotNull(continent);
		Assert.assertEquals(continent.getCValue(), controlValue1);
		//Assert.assertNotEquals(continent.getCValue(), controlValue1);
	}
	
	/**
	 * Adds the territory.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void addTerritory() throws Exception {
		territory = mapMini.addTerritory(map, territoryName, xAxis1, yAxis1, null, continent);
		Assert.assertNotNull(territory);
		Assert.assertEquals(territory.getAssignName(), territoryName);
		Assert.assertEquals(territory.getPointX(), Integer.parseInt(xAxis1));
		Assert.assertEquals(territory.getPointY(), Integer.parseInt(yAxis1));
		Assert.assertEquals(territory.getLyingInCntnt(), continent);
	}
}
