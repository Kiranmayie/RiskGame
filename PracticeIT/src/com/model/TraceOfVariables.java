package com.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TraceOfVariables implements Serializable {
	
	public int totalContestants;
	public HashMap<String, Integer> contestantName;
	public File file;
	public List<List<String>> trrtsPercontestant = new ArrayList<List<String>>();
	public int noOfArmiesPerPlayer;
	public HashMap<String, Integer> armiesForEachtrrtrs = new HashMap<String, Integer>();
	public List<List<String>> continentListPerContestant = new ArrayList<List<String>>();
	public int totaltrrtrs;
	public int armiesForEachContestant;
	public ArrayList<String> trrtrsName;
	public HashMap<String, Integer> cValue1 = new HashMap<String, Integer>();
	public HashMap<String, String> territoriesForEachContinent = new HashMap<String, String>();
	public HashMap<String, Integer> cValue = new HashMap<String, Integer>();
	public ArrayList<String> trrtrsList;
	public ArrayList<String> continentList;
	public HashMap<Integer, Integer> armies = new HashMap<Integer, Integer>();
	int flag1 = 0;
	int flag2 = 0;
	int flag3 = 0;
	public int i;


}
