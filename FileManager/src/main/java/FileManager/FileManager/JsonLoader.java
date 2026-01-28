package FileManager.FileManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javafx.collections.ObservableMap;

public class JsonLoader {
	private String url = "src/main/resources/data.json";
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	
	public Map<String, DirectoryConfig> load() throws IOException{
		FileReader reader = new FileReader(this.url);
		
		// Initialising Gson
		Gson gson = new Gson();
		
		// Creating a type for the data 
		Type type = new TypeToken<Map<String, DirectoryConfig>>(){}.getType();
		
		
		// initialising the data 
		Map<String, DirectoryConfig>data = gson.fromJson(reader, type);
		
		reader.close();
		
		return data;
	}
	
	public void update(ObservableMap<String, DirectoryConfig> New) {
		try (FileWriter writer = new FileWriter(this.url)){
			this.gson.toJson(New, writer);
		}
		catch(Exception e) {
			System.out.println("Failed to save data");
		}
		
	}

}
