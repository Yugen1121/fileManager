package FileManager.FileManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java .nio.file.Path;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class DataHandler {
	
	private String url = "src/main/resources/data.json";
	private ObservableMap<String,ObservableMap<String, String>> data = FXCollections.observableHashMap();
	
	public DataHandler() throws Exception{
		
			// Reading the data from the json file
			FileReader reader = new FileReader(this.url);
			
			// Initialising Gson
			Gson gson = new Gson();
			
			// Creating a type for the data 
			Type type = new TypeToken<Map<String, Map<String, String>>>(){}.getType();
			
			
			// initialising the data 
			Map<String, Map<String, String>> data = gson.fromJson(reader, type);
			
			if (data == null)return;
			
			// changing the data to observable map
			for (String str: data.keySet()) {
				ObservableMap<String, String> nested = FXCollections.observableHashMap();
				Map<String, String> mp = data.get(str); 
				for (String k: mp.keySet()) {
					nested.put(k, mp.get(k));
				}
				this.data.put(str, nested);
			}
		
			// closing reader
			reader.close();
	}
	
	// getter function for data
	public ObservableMap<String, ObservableMap<String, String>> getData(){
		return this.data;
	}
	
	public void updateData() { 
		Gson gson = new GsonBuilder().create();
		try(FileWriter writer = new FileWriter(this.url)){
			gson.toJson(this.data, writer);
		}catch(Exception e) {

		}
	}
	
}
