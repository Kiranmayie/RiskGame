package com.model;

import java.util.List;
import java.util.Random;

import com.units.*;


public class Cards {
	private List<ContestantCards> ContestantCards;
	 private static Random rgen = new Random();
	public static String selectCards() {
	
		        int s = 1 + rgen.nextInt(3);
		        String card;
				switch (s) {
		        case 1:
		            card = "INFANTRY";
		            break;
		        case 2:
		            card = "CAVALRY";
		            break;
		        default:
		            card = "ARTILLERY";
		            break;
		      
		        }
		        return card;
		    }
	
	
	}
	
	
