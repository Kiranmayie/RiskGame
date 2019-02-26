package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import com.Functionalities.MapFunctionalities;
import com.main.MapSStep;
import com.model.MapMiniature;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;
import com.model.AuthenticatingEnhanedmap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
//import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapBuildController implements Initializable,EventHandler{
	
    Map map;
         
	MapMiniature mapMini;

	private File file;
	
	@FXML
	private TextField Author;

	@FXML
	private TextField Warn;

	@FXML
	private TextField Scroll;
	
	@FXML
	private TextField Wrap;
	
	@FXML
	private TextField Image;

	@FXML
	private Button addingContinent;
	
	@FXML
	private Button updatingContinent;
	
	@FXML
	private TextField newContName;

	@FXML
	private TextField newContValue;

	@FXML
	private Button deletingContinent;
	
	@FXML
	private TextField sameContinentName;
	
	@FXML
	private TextField newTName;

	@FXML
	private TextField tXaxis;

	@FXML
	private TextField tYaxis;
	

	@FXML
	private Button addTerritory;
	
	@FXML
	private ComboBox<Territories> joiningAdjTerritories;

	@FXML
    private ListView<Continents> cntntList;
	
	@FXML
	private ListView<Territories> trrtrsList;

	@FXML
	private ListView<Territories> adjTrrtrsList;

	@FXML
	private Button removingAdjTrrtrs;

	@FXML
	private Button updateTerr;
	
	@FXML
	private Button delTerr;
	
	@FXML
	private Button saveMap;
	
	@FXML
	private Button exitButton;
	
	Stage stage = new Stage();

	public Button button;
	
	@Override
	public void handle(Event arg0) {
		
		File mapReturnedFile = MapSStep.mapFileValidator();
		 MapSStep mapsstep=new MapSStep();
		 try {
			mapsstep.readingMapFile(mapReturnedFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 if(AuthenticatingEnhanedmap.x>0) {
			 
			 System.out.println("Sorry Something went wrong. We are investigating the issue.");;
		 }
		 else {
        stage.setTitle("FXML Welcome");
        try {
			stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MapEditorNew.fxml"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        stage.setResizable(true);

        stage.show();
        }
	
    @FXML
    private void addingNewContinent(ActionEvent event) {

    Continents cont = null;

    try {
    cont = mapMini.addingContinent(map, newContName.getText(), newContValue.getText());
    } 
    catch (Exception ex) {
    System.out.println("Invalid Map");
  	}
  	if (cntntList == null) {
  	cntntList = new ListView<Continents>();
  	}
  	cntntList.getItems().add(cont);
  	map.getContinents().add(cont);
  	sameContinentName.setText(cont.getAssignName());
   	}

	@FXML
	private void updatingNewContinent(ActionEvent event) {
		Continents continent = cntntList.getSelectionModel().getSelectedItem();
		continent = mapMini.updatingContinent(continent, newContName.getText());
	}
    
	@FXML
	private void deleteContinent(ActionEvent event) {
		Continents continent = cntntList.getSelectionModel().getSelectedItem();

		if (continent != null && continent.getTrrtrs() != null) {
			if (continent.getTrrtrs().size() > 1) {
				//MapUtil.outPutMessgae(outPutConsole, "Remove associated territories first", false);
				System.out.println("Discarding attached territories");
				return;
			}
		}
		if (map.getContinents() != null) {
			map.getContinents().remove(continent);
			cntntList.getItems().remove(continent);
			System.out.println("Continents detached successfully");
		}
	}

	@FXML
	private void addingNewTerritory(ActionEvent event) {

		Continents continent = cntntList.getSelectionModel().getSelectedItem();
		Territories adjTerritory = joiningAdjTerritories.getSelectionModel().getSelectedItem();

		Territories territory = null;

		try {
			territory = mapMini.addTerritory(map, newTName.getText(), tXaxis.getText(),
					tYaxis.getText(), adjTerritory, continent);
		} catch (Exception ex) {
			System.out.println("Error: Invalid Map");
			return;
		}
		continent = mapMini.assignTerrToContinent(continent, territory);
		joiningAdjTerritories.getItems().add(territory);
		trrtrsList.getItems().add(territory);
	}

	@FXML
	private void removingAdjacentTrrtrs(ActionEvent event) {
		Territories adjTerritory = adjTrrtrsList.getSelectionModel().getSelectedItem();

		Territories territory = trrtrsList.getSelectionModel().getSelectedItem();
		if (territory != null && territory.getTouchingTrrtrsExpand() != null) {
			if (territory.getTouchingTrrtrsExpand().size() == 1) {
				System.out.println("Alert: One adjacent territory should exist");
				return;
			}
			territory.getTouchingTrrtrsExpand().remove(adjTerritory);
			adjTrrtrsList.getItems().remove(adjTerritory);
			System.out.println("Success: Adjacent Territory removed");
			}
	}

	@FXML
	private void deleteTerritories(ActionEvent event) {
		Territories territory = trrtrsList.getSelectionModel().getSelectedItem();
		HashSet<Territories> territoryToBeRemovedFrom = new HashSet<>();
		Continents continent = cntntList.getSelectionModel().getSelectedItem();

		if (continent != null && continent.getTrrtrs() != null) {
			if (continent.getTrrtrs().size() == 1) {
				System.out.println("Alert: Atleats One territory must be selecteed within the Continent ");
				return;
			}
			for (Continents cont : map.getContinents()) {
				for (Territories terr : cont.getTrrtrs()) {
					if (terr.getTouchingTrrtrsExpand().contains(territory)
							&& (terr.getTouchingTrrtrsExpand().size() == 1)) {
						System.out.println("Alert: Territory"+territory.getAssignName()+ "onnly adjacent territory"+ terr.getAssignName()+", Cannot be deleted.");
						return;
					}
					if (terr.getTouchingTrrtrsExpand().contains(territory)  && terr.getTouchingTrrtrsExpand().size()>1) {
						territoryToBeRemovedFrom.add(terr);
					}
				}
			}
			//If there was no exception than remove this territory from this other territory
			for (Territories t : territoryToBeRemovedFrom) {
				t.getTouchingTrrtrsExpand().remove(territory);
			}
			continent.getTrrtrs().remove(territory);
			trrtrsList.getItems().remove(territory);
			System.out.println("Territory removed succesfully");
			//MapUtil.outPutMessgae(outPutConsole, "Territory removed successfully.", true);

		}
	}
	
	@FXML
	private void updateTerritories(ActionEvent event) {
		Territories territory = trrtrsList.getSelectionModel().getSelectedItem();

		Territories adjTerritory = joiningAdjTerritories.getSelectionModel().getSelectedItem();
		territory = mapMini.updateTerritories(territory, tXaxis.getText(), tYaxis.getText(),
				adjTerritory);
	}

	
	private Map s_uMap(Map map) {

		map.getMapData().put("Author", getEmptyBlank(Author.getText()));
		map.getMapData().put("Warn", getEmptyBlank(Warn.getText()));
		map.getMapData().put("Scroll", getEmptyBlank(Scroll.getText()));
		map.getMapData().put("Wrap", getEmptyBlank(Wrap.getText()));
		map.getMapData().put("Image", getEmptyBlank(Image.getText()));
		return map;
	}
        stage.show(); 
		 }
	}
	private String getEmptyBlank(String value) {
		return value;
	}

	@FXML
	private void SavingMap(ActionEvent event) {
		map = s_uMap(map);
		try {
			MapFunctionalities.saveMap(this.file, map);
		} catch (Exception ex) {
			//MapUtil.infoBox(ex.getMessage(), "Error", "InvalidMap");
			return;
		}
		Stage stage = (Stage) saveMap.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void mapEditExit(ActionEvent event) {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
	public void handleButtonClick() {
		
		button.setText("Stop Touching Me..!!");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
       
    
	}

