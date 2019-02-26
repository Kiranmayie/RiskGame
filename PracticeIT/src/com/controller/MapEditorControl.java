package com.controller;

import java.awt.TextField;
import java.io.File;
import java.nio.channels.FileChannel.MapMode;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import com.sun.javafx.collections.MappingChange.Map;

import javafx.fxml.Initializable;

public class MapEditorControl implements Initializable{
	/**
	 * the @map
	 */
		private Map map;
		private File file;
		private MapModel mapmodel;
		private TextField Author;
		private TextField Image;
		private TextField Scroll;
		private TextField Warn;
		private TextField Wrap;
		private Button SaveMap;
		private Button Exit;
		private Label Continent;
		private Listview<Continent> ContinentsList;
		private Label Territory;
		private Listview<Territories> AdjTerritoryList;
		private TextField newContinentname;
		private TextField newContinentvalue;
		private TextField newTerritoryname;
		private TextField territoryXaxis;
		private TextField territoryYaxis;
		private Button addContinent;
		private Button addTerritory;
		private Button updateContinent;
		private Button updateTerritory;
		private Button delAdjTerritory;
		private Textarea outputconsole;
		private ComboBox<Territories> AdjTerritories;
		/**
			* @Constructor for MapEditorControl
		 */
		
		public MapEditorControl(Map map,File file)
		{    this.map=map;
				this.file=file;
				this.mapModel=new MapModel();
		}
		
		
		
}
