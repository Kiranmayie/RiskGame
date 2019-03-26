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
/**
 * This class is about populating territories and continents from the world map. 
 */
public class MapBuildController implements Initializable,EventHandler{
	
    /** The Map object */
    private Map map;
         
	/** The Map Miniature object */
	private MapMiniature mapMini;

	/** The file object */
	private File file;
	
	/** The Author Textfield */
	@FXML
	private TextField Author;

	/** The Warn Textfield */
	@FXML
	private TextField Warn;

	/** The Scroll Textfield */
	@FXML
	private TextField Scroll;
	
	/** The Wrap Textfield */
	@FXML
	private TextField Wrap;
	
	/** The Image Textfield */
	@FXML
	private TextField Image;

	/** Button for adding continent. */
	@FXML
	private Button addingContinent;
	
	/** Button for updating continent. */
	@FXML
	private Button updatingContinent;
	
	/** The new continent name TextField */
	@FXML
	private TextField newContName;

	/** The new continent value TextField */
	@FXML
	private TextField newContValue;

	/** Button for deleting continent. */
	@FXML
	private Button deletingContinent;
	
	/** The same continent name Label */
	@FXML
	private Label sameContinentName;
	
	/** The new Territory name TextField */
	@FXML
	private TextField newTName;

	/** The TextField for Territory X-Axis */
	@FXML
	private TextField tXaxis;

	/** The TextField for Territory Y-Axis */
	@FXML
	private TextField tYaxis;
	

	/** Button for add territory. */
	@FXML
	private Button addTerritory;
	
	/** ComboBox for joining adjacent territories. */
	@FXML
	private ComboBox<Territories> joiningAdjTerritories;

	/** The List View for Continent List */
	@FXML
    private ListView<Continents> cntntList;
	
	/** The List View for Territory List */
	@FXML
	private ListView<Territories> trrtrsList;

	/** The List View for Adjacent Territories List */
	@FXML
	private ListView<Territories> adjTrrtrsList;

	/** Buttonn for removing adjacent Territories */
	@FXML
	private Button removingAdjTrrtrs;

	/** Button for update territory. */
	@FXML
	private Button updateTerr;
	
	/** Button for deleting territory */
	@FXML
	private Button delTerr;
	
	/** Button For saving map. */
	@FXML
	private Button saveMap;
	
	/** Exit button. */
	@FXML
	private Button exitButton;
	
	/** Object for javaFx stage method */
	Stage stage = new Stage();

	/** The button. */
	public Button button;

	/**
	 * Instantiates a new map build controller.
	 */
	public MapBuildController() {
		this.mapMini = new MapMiniature();
	} 
	
	/* (non-Javadoc)
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
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
			 System.out.println("Alert: Something is Wrong. Restart the gaame.");
			 System.exit(0);
			 }
		 else {

        stage.setTitle("Map Selection");
        Parent root = null;
        System.out.println(map.toString());
			 stage.setTitle("Map Selection");

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

	/**
	 * Enabling TextField
	 * @param fields 
	 */

	public static void enable(Control... controls) {
		for (Control control : controls) {
			control.setDisable(false);
		}
	}

	/**
	 * Erasing TextField
	 * @param fields 
	 */
	public static void erasingTF(TextField... fields) {
		for (TextField field : fields) {
			field.clear();
		}
	}
    
    /**
     * Adding new continent.
     * @param event 
     */
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

	/**
	 * Updating new continent.
	 * @param event 
	 */
	@FXML
	private void updatingNewContinent(ActionEvent event) {
		Continents continent = cntntList.getSelectionModel().getSelectedItem();
		continent = mapMini.updatingContinent(continent, newContValue.getText());
		enable(newContName, addingContinent);
		erasingTF(newContName, newContValue);
	}
    
	/**
	 * Delete continent.
	 * @param event 
	 */
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

	/**
	 * Adding new territory.
	 * @param event 
	 */
	@FXML
	private void addingNewTerritory(ActionEvent event) {
		Continents continent = cntntList.getSelectionModel().getSelectedItem();
		Territories adjTerritory = joiningAdjTerritories.getSelectionModel().getSelectedItem();
		Territories territory = null;
		try {
			territory = mapMini.addTerritory(map, newTName.getText(), tXaxis.getText(),tYaxis.getText(), adjTerritory, continent);
		} catch (Exception ex) {
			System.out.println("Error: Invalid Map");
			return;
		}
		continent = mapMini.assignTerrToContinent(continent, territory);
		joiningAdjTerritories.getItems().add(territory);
		trrtrsList.getItems().add(territory);
	}

	/**
	 * Removing adjacent Territories.
	 * @param event 
	 */
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

	/**
	 * Delete territories.
	 * @param event the event
	 */
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
	
	/**
	 * Update territories.
	 * @param event 
	 */
	@FXML
	private void updateTerritories(ActionEvent event) {
		Territories territory = trrtrsList.getSelectionModel().getSelectedItem();
		Territories adjTerritory = joiningAdjTerritories.getSelectionModel().getSelectedItem();
		int newTXaxis= Integer.parseInt(tXaxis.getText());
		int newTYaxis= Integer.parseInt(tYaxis.getText());
		territory = mapMini.updateTerritories(territory, newTXaxis, newTYaxis,
				adjTerritory);
		
	}
	
	
	/**
	 * Saving map.
	 * @param event the event
	 */
	@FXML
	private void SavingMap(ActionEvent event) {
		System.out.println("Map Read");
        map.getMapData().put("author", Author.getText());
        map.getMapData().put("warn", Warn.getText());
		map.getMapData().put("scroll", Scroll.getText());
		map.getMapData().put("srap", Wrap.getText());
		map.getMapData().put("image", Image.getText());
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
	
	/**
	 * Map editor exit.
	 * @param event 
	 */
	@FXML
	private void mapEditExit(ActionEvent event) {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}


	/**
	 * Instantiates a new map build controller.
	 * @param map 
	 * @param file 
	 */
	public MapBuildController(Map map, File file) {
		this.map = map;
		this.file = file;
		this.mapMini = new MapMiniature();
	}
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (this.map == null)
			map = new Map();
		else {
			    Author.setText(map.getMapData().get("author"));
				Image.setText(map.getMapData().get("scroll"));
				Scroll.setText(map.getMapData().get("image"));
				Warn.setText(map.getMapData().get("warn"));
				Wrap.setText(map.getMapData().get("wrap"));

				loadAdjTerritoryList();
				for (Continents continent : map.getContinents()) 
					cntntList.getItems().add(continent);
				}

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
					Continents continent = cntntList.getSelectionModel().getSelectedItem();
					sameContinentName.setText(continent.getAssignName());
					newContName.setText(continent.getAssignName());
					newContName.setDisable(true);
					newContValue.setText(continent.getCValue());
					addingContinent.setDisable(true);
					adjTrrtrsList.getItems().clear();
					displayTerritory(cntntList.getSelectionModel().getSelectedItem());
				}
			}
		);
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
					Territories territory = trrtrsList.getSelectionModel().getSelectedItem();
					newTName.setText(territory.getAssignName());
					tXaxis.setText(String.valueOf(territory.getPointX()));
					tYaxis.setText(String.valueOf(territory.getPointY()));
					newTName.setDisable(true);
					addTerritory.setDisable(true);
					displayCorrespondingTrrtrs(territory);

				}
			}
		);

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
	
	/**
	 * Display territory.
	 * @param continent 
	 */

	private void displayTerritory(Continents continent) {
		trrtrsList.getItems().clear();
		if (continent != null && continent.getTrrtrs() != null) {
			for (Territories territory : continent.getTrrtrs()) {
				trrtrsList.getItems().add(territory);
			}
		}
	}
	
/**
 * Display corresponding Territories.
 * @param territory 
 */
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
		/**
		 * Load map data.
		 */
		private void loadMapData() {
			Author.setText(map.getMapData().get("author"));
			Image.setText(map.getMapData().get("scroll"));
			Scroll.setText(map.getMapData().get("image"));
			Warn.setText(map.getMapData().get("warn"));
			Wrap.setText(map.getMapData().get("wrap"));
			loadAdjTerritoryList();
			for (Continents continent : map.getContinents()) {
				cntntList.getItems().add(continent);
			}
		}

		/**
		 * Loading adjacent territory list.
		 */

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