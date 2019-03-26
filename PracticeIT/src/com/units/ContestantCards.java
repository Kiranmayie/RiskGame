package com.units;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Class ContestantCards is used to populate the fields related to contestantcards being hold by particular contestant.
 */
public class ContestantCards implements Serializable{
	
	/** The contestant card type. */
	
	
	/** The trrtry. */
	private Territories trrtry;
	
	/**
	 * Instantiates a new contestant cards.
	 *
	 * @return the trrtrs
	 */
	
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



}
