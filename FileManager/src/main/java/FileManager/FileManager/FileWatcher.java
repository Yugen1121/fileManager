package FileManager.FileManager;

import com.darylteo.nio.*;

import javafx.collections.ObservableMap;
import javafx.collections.FXCollections;

public class FileWatcher {
	private ObservableMap<String, String> filePath = FXCollections.observableHashMap();
	private String directoryName;
	private DataHandler dh;
	
	public FileWatcher(String name, ObservableMap<String, String> map, DataHandler dh) {
		this.filePath = map;
		this.directoryName = name;
		this.dh = dh;
	}
	
	public String getDirectoryName() {
		return this.directoryName;
	}
	
	// gets all file path
	public ObservableMap <String, String> getFilePaths(){
		return this.filePath;
	}
	
	// adds a new file path 
	public void addFilePath(String fileType, String path) {
		this.filePath.put(fileType, path);
		this.dh.updateData();
	}
	
}
