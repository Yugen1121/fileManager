package FileManager.FileManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.nio.file.Files;
import java .nio.file.Path;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class DataHandler {
	
	private String url = "src/main/resources/data.json";
	private ObservableMap<String, DirectoryConfig> data = FXCollections.observableHashMap();
	private JsonLoader repository;
	public DataHandler(JsonLoader repo) throws Exception{
		this.repository = repo;
		this.data.putAll(this.repository.load());
	}
	
	// getter function for data
	public ObservableMap<String, DirectoryConfig> getData(){
		return this.data;
	}
	
	public void updateData() { 
		this.repository.update(this.data);
	}
	
	// function to add directory path to watch
	public void addDirectoryWatcherPath(String path, String name) throws Exception{
		if (this.data.containsKey(path)) {
			throw new RuntimeException("Path aready exists");
		}
		Path p = Paths.get(path);
		if (!Files.exists(p)) {
			throw new RuntimeException("Path doesn't exist");
		}
		Map<String, String>map = new HashMap<>();
		this.data.put(path, new DirectoryConfig(name, true, map));
		
	}
	
	public ObservableMap<String, String> getFilePaths(String path){ 
		return this.data.get(path).getFilePaths();
	}
	
	public DirectoryConfig getDirectoryConfig(String path) {
		return this.data.get(path);
	}
	
	public void addDirectory(String path, String name) throws Exception {
		if (this.data.containsKey(path)) {
			throw new RuntimeException("Path "+path+" already exists");
		}
		ObservableMap<String, String> map = FXCollections.observableHashMap();
		DirectoryConfig DC = new DirectoryConfig(name, true, map);
		this.data.put(path, DC);
		this.updateData();
	}
}
