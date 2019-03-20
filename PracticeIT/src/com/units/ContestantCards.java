package com.units;

import java.io.Serializable;
/**
 * The Class ContestantCards.
 */
public class ContestantCards implements Serializable{
	
	/** The contestant card type. */
	ContestantcardType contestantCardType;
	
	/** The trrtry. */
	private Territories trrtry;
	
	/**
	 * Instantiates a new contestant cards.
	 * @param contestantCardType the contestant card type
	 */
	public ContestantCards(ContestantcardType contestantCardType) {
		this.contestantCardType = contestantCardType;
	}
	
	/**
	 * Gets the trrtrs.
	 * @return the trrtrs
	 */
	public Territories getTrrtrs() {
		return trrtry;
	}

	
	/**
	 * Sets the trrtry.
	 *
	 * @param trrtry the new trrtry
	 */
	public void setTrrtry(Territories trrtry) {
		this.trrtry = trrtry;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		if (object == this) {
			return true;
		}

		if (!(object instanceof ContestantCards)) {
			return false;
		}

		ContestantCards cards = (ContestantCards) object;
		return true;
	}



	/**
	 * The Enum ContestantcardType.
	 */
	public enum ContestantcardType {
			INFANTRY, 
			CAVALRY, 
			ARTILLERY
	}

}
