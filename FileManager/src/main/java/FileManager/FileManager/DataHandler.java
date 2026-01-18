package FileManager.FileManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.FileReader;

import java.nio.file.Paths;
import java .nio.file.Path;

import java.lang.reflect.Type;

import java.util.Map;

public class DataHandler {
	
	private Map<String, Map<String, String>> data;
	
	public DataHandler() throws Exception{
		
			// Reading the data from the json file
			FileReader reader = new FileReader("src/main/resources/data.json");
			
			// Initialising Gson
			Gson gson = new Gson();
			
			// Creating a type for the data 
			Type type = new TypeToken<Map<String, Map<String, String>>>(){}.getType();
			
			
			// initialising the data 
			this.data = gson.fromJson(reader, type);
			
			// closing reader
			reader.close();
	}
	
	// getter function for data
	public Map<String, Map<String, String>> getData(){
		return this.data;
	}
}
