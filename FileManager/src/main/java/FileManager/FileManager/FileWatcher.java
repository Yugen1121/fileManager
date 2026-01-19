package FileManager.FileManager;

import com.darylteo.nio.*;

import javafx.collections.ObservableMap;
import javafx.collections.FXCollections;

public class FileWatcher {
	private ObservableMap<String, String> filePath = FXCollections.observableHashMap();
	private String directoryName;
	
	public FileWatcher(String name, ObservableMap<String, String> map) {
		this.filePath = map;
		this.directoryName = name;
	}
	
	public String directoryName() {
		return this.directoryName;
	}
	
	public ObservableMap <String, String> getFilePaths(){
		return this.filePath;
	}
	
}
