package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import com.model.AuthenticatingEnhanedmap;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;

import junit.framework.Assert;

class MapValidate {
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
	List<Continents> ContinentsList;
	
	/**
	 * This method is invoked at the start of the test class.
	 */
	@BeforeClass
	public static void beforeClass() {
		continent = new Continents();
		territory = new Territories();
		map = new Map();
		mapValidator = new AuthenticatingEnhanedmap();
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
	public void validateContinentForNullTerritory() {
		AuthenticatingEnhanedmap.authCntnt(new Continents(),map);	
	}
	
	
	@Test 
	public void validateContinentForTerritory() {
		map.setContinents(ContinentsList);
		AuthenticatingEnhanedmap.authCntnt(continent,map);	
	} 
	
	
	@Test
	public void validateContinentForSubGraph(){		
		Assert.assertFalse(AuthenticatingEnhanedmap.cntntConnectedToAnotherCntnt(continent, map));
	}
	
	
	
	

}
