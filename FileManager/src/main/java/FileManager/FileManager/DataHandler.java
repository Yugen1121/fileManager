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
	
	public DataHandler() throws Exception{
		
			// Reading the data from the json file
			FileReader reader = new FileReader(this.url);
			
			// Initialising Gson
			Gson gson = new Gson();
			
			// Creating a type for the data 
			Type type = new TypeToken<Map<String, DirectoryConfig>>(){}.getType();
			
			
			// initialising the data 
			Map<String, DirectoryConfig> data = gson.fromJson(reader, type);
			
			if (data == null)return;
			
			for (String str: data.keySet()) {
				this.data.put(str, data.get(str));
			}
		
			// closing reader
			reader.close();	
	}
	
	// getter function for data
	public ObservableMap<String, DirectoryConfig> getData(){
		return this.data;
	}
	
	public void updateData() { 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try(FileWriter writer = new FileWriter(this.url)){
			gson.toJson(this.data, writer);
		}catch(Exception e) {

		}
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
	
}
