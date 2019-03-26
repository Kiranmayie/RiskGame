package com.model;

import java.util.List;
import java.util.Random;

import com.units.*;


// TODO: Auto-generated Javadoc
/**
 * The Class Cards.
 */
public class Cards {
	
	/** The Contestant cards. */
	private List<ContestantCards> ContestantCards;
	 
 	/** The rgen. */
 	private static Random rgen = new Random();
	
	/**
	 * Select cards.
	 *
	 * @return the string
	 */
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
	
	
