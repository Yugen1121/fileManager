package FileManager.FileManager;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.beans.property.SimpleStringProperty;

public class DirectoryConfig {
	private String name;
	private Boolean active;
	private Map<String, String> filePath = new HashMap<>();
	
	private transient StringProperty nameProp;
    private transient BooleanProperty activeProp;
    private transient ObservableMap<String, String> observableFilePaths;
	
	public DirectoryConfig(String name, Boolean active, Map<String, String> filePath) {
		this.name = name;
		this.active = active;
		this.filePath = filePath;
	}
	
	
	public StringProperty getName() {
		if (this.nameProp == null) {
			this.nameProp = new SimpleStringProperty(name);
			this.nameProp.addListener((obv, old, newVal) -> {
				this.name = newVal;
			});
		}
		return this.nameProp; 
	}
	
	public BooleanProperty getActive() {
		if (this.activeProp == null) {
			
			this.activeProp = new SimpleBooleanProperty(this.active != null? this.active: true);
			this.activeProp.addListener((obv, old, newVal) -> {
				this.active = newVal;
			});
		}
		return this.activeProp;
	}
	
	public ObservableMap<String, String> getFilePaths() {
		if (this.observableFilePaths == null) setFilePath(this.filePath);
		return this.observableFilePaths; 
	}
	
	public void toggleActive() {
		if (this.activeProp == null) this.activeProp.set(this.active);
		this.activeProp.set(!this.activeProp.get()); 
	}
	public void setName(String name) { this.nameProp.set(name); }
	public void setFilePath(Map<String, String> filePaths) {
		if (this.observableFilePaths == null) {
			this.observableFilePaths = FXCollections.observableMap(this.filePath);
		};
		this.observableFilePaths.clear();
		this.observableFilePaths.putAll(filePaths);
	}
	
	
}
