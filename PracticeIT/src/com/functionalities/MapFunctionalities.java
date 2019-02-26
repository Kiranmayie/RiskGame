package com.functionalities;

import java.io.File;

import javafx.stage.FileChooser;

public class MapFunctionalities {

	public static File showFileChooser() {
		FileChooser fileChooser = new FileChooser();
		File file = null;
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Map files (*.map)", "*.map");
		fileChooser.getExtensionFilters().add(extensionFilter);
		file = fileChooser.showOpenDialog(null);
		return file;
	
	}

}
