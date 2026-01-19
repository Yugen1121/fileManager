package FileManager.FileManager;

import com.darylteo.nio.*;

import javafx.collections.ObservableMap;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class FileWatcher {
	private String directoryPath;
	private DataHandler dh;
	private DirectoryConfig config;
	
	public FileWatcher(String path, DataHandler dh) {
		this.directoryPath = path;
		this.dh = dh;
		this.config = dh.getData().get(path);
	}
	
	public String getDirectoryPath() {
		return this.directoryPath;
	}
	
	public StringProperty getName() {
		return this.config.getName();
	}
	
	// gets all file path
	public ObservableMap <String, String> getFilePaths(){
		return this.config.getFilePaths();
	}
	
	// adds a new file path 
	public void addFilePath(String fileType, String path) {
		this.config.getFilePaths().put(fileType, path);
		this.dh.updateData();
	}
	
}
