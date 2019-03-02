package com.units;

import java.io.Serializable;


public class ContestantCards implements Serializable{
	ContestantcardType contestantCardType;
	
	private Territories trrtry;
	public ContestantCards(ContestantcardType contestantCardType) {
		this.contestantCardType = contestantCardType;
	}
	
	public Territories getTrrtrs() {
		return trrtry;
	}

	
	public void setTrrtry(Territories trrtry) {
		this.trrtry = trrtry;
	}

	@Override
	public boolean equals(Object object) {

		if (object == this) {
			return true;
		}

		if (!(object instanceof ContestantCards)) {
			return false;
		}

		ContestantCards cards = (ContestantCards) object;
		//return cards.ContestantcardType().toString().equalsIgnoreCase(cardType.toString())
			//	&& cards.getTrrtrs().equals(territory);
		return true;
	}



	public enum ContestantcardType {
		INFANTRY, CAVALRY, ARTILLERY
	}

}
