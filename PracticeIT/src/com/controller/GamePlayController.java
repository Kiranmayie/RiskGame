package com.controller;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.URL;

import java.util.ResourceBundle;

import com.model.GameDesign;

import com.units.Map;

import javafx.fxml.Initializable;

public class GamePlayController implements Initializable, Externalizable {

	private Map map;
	private GameDesign gameDesign;
	

	public GamePlayController(Map map) {
		this.map = map;
		this.gameDesign = new GameDesign();
		/*this.playerGamePhase = new PlayerGamePhase();
		this.cardModel = new CardModel();
		playerGamePhase.addObserver(this);
		cardModel.addObserver(this);
		worldDomination = new PlayerWorldDomination();
		worldDomination.addObserver(this);
		this.setNumberOfCardSetExchanged(0);
		gamePlayerList = new ArrayList<>();*/ 
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
