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
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

import javafx.stage.FileChooser;
public class MapValidate {

static AuthenticatingEnhanedmap mapValidator;
	
	static Continents continent;
	static Territories territory;
	static Map map;
	
	String Author = "Sean O'Connor";
	String Image = "world.bmp";
	String Wrap = "no";
	String Scroll = "horizontal";
	String Warn = "yes";
	
	String continentName = "Asia";
	String controlValue = "7";
	
	static HashMap<String, String> mapValue;

	public static MapSStep mrm;
	List<Continents> ContinentsList;

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
		
	
	@Test 
	public void validateMapForNullMap()  {
		AuthenticatingEnhanedmap.AuthFStep(null);
	}
	
	
	@Test 
	public void validateMapForContinent() {
		AuthenticatingEnhanedmap.AuthFStep(new Map());
	}
	@Test
	public void checkValidNumberOfContinents() throws FileNotFoundException {
		file = new File("C:/Users/sathw_000/Desktop/Map Files/World.map");
		Map map = mrm.readingMapFile(file);
		Assert.assertEquals(map.getContinents().size(), 7);
	}
	@Test 
	public void checkInvalidMapForContinentWithoutTerritory() throws FileNotFoundException {
		file = new File("C:/Users/sathw_000/Desktop/Map Files/World_Invalid_Continent.map");
		Map map = mrm.readingMapFile(file);
		
		
	}
	@Test
	public void checkInvalidMapForTerritorySubgraph() throws FileNotFoundException  {
		file = new File("C:/Users/sathw_000/Desktop/Map Files/World_Invalid_Territory_Subgraph.map");
		Map map = mrm.readingMapFile(file);
		
	}
	
	
	@Test 
	public void checkInvalidMapForContinentSubgraph() throws FileNotFoundException  {
		file = new File("C:/Users/sathw_000/Desktop/Map Files/World_Invalid_Continent_Subgraph.map");
		Map map = mrm.readingMapFile(file);
	}

}
