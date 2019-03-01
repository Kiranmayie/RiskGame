package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import com.main.MapSStep;
import com.model.MapMiniature;
import com.model.MapSaver;
import com.units.Continents;
import com.units.Map;
import com.units.Territories;
import com.model.AuthenticatingEnhanedmap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
//import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapBuildController implements Initializable,EventHandler{
	
    private Map map;
         
	private MapMiniature mapMini;

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
	private Label sameContinentName;
	
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

	public MapBuildController() {
		this.mapMini = new MapMiniature();
	} 
	
	@Override
	public void handle(Event RG) {
		
		File mapReturnedFile = MapSStep.mapFileValidator();
		 MapSStep mapsstep=new MapSStep();
		 try {
			map=mapsstep.readingMapFile(mapReturnedFile);
			System.out.println(map.toString());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 MapBuildController mvc= new MapBuildController(map, file);
		 if(AuthenticatingEnhanedmap.x>0) {
		 System.out.println("Alert: Something is Wrong");;
		 }
		 else {

        stage.setTitle("FXML Welcome");
        Parent root = null;
        System.out.println(map.toString());
			 stage.setTitle("FXML Welcome");

        try {
        	FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("MapEditorNew.fxml"));
        	loader.setController(mvc);
           	root = (Parent) loader.load();
            Scene scene= new Scene(root);
            stage.setScene(scene);
			} 
        catch (IOException e) {
				e.printStackTrace();
		}
        stage.setResizable(true);
        stage.show();
		 }
        }

        
        

	
	public static void enable(Control... controls) {
		for (Control control : controls) {
			control.setDisable(false);
		}
	}

	public static void erasingTF(TextField... fields) {
		for (TextField field : fields) {
			field.clear();
		}
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
		enable(newContName, addingContinent);
		erasingTF(newContName, newContValue);
	}
    
	@FXML
	private void deleteContinent(ActionEvent event) {
		Continents continent = cntntList.getSelectionModel().getSelectedItem();
		if (continent != null && continent.getTrrtrs() != null) {
			if (continent.getTrrtrs().size() > 1) {
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
		HashSet<Territories> territoriesDetachedFrom = new HashSet<>();
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
						territoriesDetachedFrom.add(terr);
					}
				}
			}
			for (Territories t : territoriesDetachedFrom) {
				t.getTouchingTrrtrsExpand().remove(territory);
			}
			continent.getTrrtrs().remove(territory);
			trrtrsList.getItems().remove(territory);
			System.out.println("Territory removed succesfully");
		}
	}
	
	@FXML
	private void updateTerritories(ActionEvent event) {
		Territories territory = trrtrsList.getSelectionModel().getSelectedItem();
		Territories adjTerritory = joiningAdjTerritories.getSelectionModel().getSelectedItem();
		int newTXaxis= Integer.parseInt(tXaxis.getText());
		int newTYaxis= Integer.parseInt(tYaxis.getText());
		territory = mapMini.updateTerritories(territory, newTXaxis, newTYaxis,
				adjTerritory);
		
	}

	
	private Map s_uMap(Map map) {
        System.out.println("Map Read");
        map.getMapData().put("Author", Author.getText());
        map.getMapData().put("Warn", Warn.getText());
		map.getMapData().put("Scroll", Scroll.getText());
		map.getMapData().put("Wrap", Wrap.getText());
		map.getMapData().put("Image", Image.getText());
		return map;}
	@FXML
	private void SavingMap(ActionEvent event) {
		System.out.println("Map Read");
		map = s_uMap(map);
		try {
			AuthenticatingEnhanedmap.AuthFStep(map);
			MapSaver fileSave = new MapSaver();

				if (file == null) {
					FileChooser fileChooser = new FileChooser();
					FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Map files (*.map)", "*.map");
					fileChooser.getExtensionFilters().add(extFilter);
					file = fileChooser.showSaveDialog(null);
				}

				fileSave.fStep(map, file);
		} catch (Exception ex) {
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

	public MapBuildController(Map map, File file) {
		this.map = map;
		this.file = file;
		this.mapMini = new MapMiniature();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (this.map == null)
			map = new Map();
		else 
			loadMapData();
		cntntList.setCellFactory(param -> new ListCell<Continents>() {
			@Override
			protected void updateItem(Continents item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null || item.getAssignName() == null) {
					setText(null);
				} else {
					setText(item.getAssignName());
				}
			}
		});
		cntntList.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) {
				onClickingContinentList(event);
			}
		});
		trrtrsList.setCellFactory(param -> new ListCell<Territories>() {
			@Override
			protected void updateItem(Territories item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null || item.getAssignName() == null) {
					setText(null);
				} else {
					setText(item.getAssignName());
				}
			}
		});
		trrtrsList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				onClickingTerritoryList(event);
			}
		});

		joiningAdjTerritories.setCellFactory(param -> new ListCell<Territories>() {
			@Override
			protected void updateItem(Territories item, boolean empty) {
				super.updateItem(item, empty);

				if (empty || item == null || item.getAssignName() == null) {
					setText(null);
				} else {
					setText(item.getAssignName());
				}
			}
		});

		}
	
	private void onClickingContinentList(MouseEvent event) {
		Continents continent = cntntList.getSelectionModel().getSelectedItem();
		sameContinentName.setText(continent.getAssignName());
		newContName.setText(continent.getAssignName());
		newContName.setDisable(true);
		newContValue.setText(continent.getCValue());
		addingContinent.setDisable(true);
		adjTrrtrsList.getItems().clear();
		displayTerritory(cntntList.getSelectionModel().getSelectedItem());
	}
	
	private void displayTerritory(Continents continent) {
		trrtrsList.getItems().clear();
		if (continent != null && continent.getTrrtrs() != null) {
			for (Territories territory : continent.getTrrtrs()) {
				trrtrsList.getItems().add(territory);
			}
		}
	}
	
	private void onClickingTerritoryList(MouseEvent event) {
		Territories territory = trrtrsList.getSelectionModel().getSelectedItem();
		newTName.setText(territory.getAssignName());
		tXaxis.setText(String.valueOf(territory.getPointX()));
		tYaxis.setText(String.valueOf(territory.getPointY()));
		newTName.setDisable(true);
		addTerritory.setDisable(true);
		displayCorrespondingTrrtrs(territory);

	}

private void displayCorrespondingTrrtrs(Territories territory) {
	adjTrrtrsList.getItems().clear();
	for (Territories adjTerritory : territory.getTouchingTrrtrsExpand()) {
		if (adjTerritory != null) {
			adjTrrtrsList.getItems().add(adjTerritory);
		}
	}
	adjTrrtrsList.setCellFactory(param -> new ListCell<Territories>() {
		@Override
		protected void updateItem(Territories item, boolean empty) {
			super.updateItem(item, empty);

			if (empty || item == null || item.getAssignName() == null) {
				setText(null);
			} else {
				setText(item.getAssignName());
			}
		}
	});
	adjTrrtrsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			}
	});
}

		private void loadMapData() {
			Author.setText(map.getMapData().get("Author"));
			Image.setText(map.getMapData().get("Scroll"));
			Scroll.setText(map.getMapData().get("Image"));
			Warn.setText(map.getMapData().get("Warn"));
			Wrap.setText(map.getMapData().get("Wrap"));

			// Load adjacent erritory
			loadAdjTerritoryList();
			for (Continents continent : map.getContinents()) {
				cntntList.getItems().add(continent);
			}
		}

		private void loadAdjTerritoryList() {

			ObservableList<Territories> adjTerritoryList = FXCollections.observableArrayList();
			for (Continents continent : map.getContinents()) {
				for (Territories territory : continent.getTrrtrs()) {
					adjTerritoryList.add(territory);
				}
			}
			joiningAdjTerritories.setItems(adjTerritoryList);
		}

	}