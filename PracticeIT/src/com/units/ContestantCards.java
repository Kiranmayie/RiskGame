package com.units;

import java.io.Serializable;




/**
 * The Class ContestantCards.
 */
public class ContestantCards implements Serializable{
	
	/** The contestant card type. */
	 CardType cardType; 
		
	/** The trrtry. */
	private Territories trrtry;
	
	public ContestantCards(CardType cardType) {
		this.cardType = cardType;
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
	
	/**
	 * @return the cardType
	 */
	public CardType getCardType() {
		return cardType;
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
