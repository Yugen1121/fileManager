package FileManager.FileManager;

import File.FileWatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class Env {
	private DataHandler data;
	private ObservableMap<String, FileWatcher> watchers = FXCollections.observableHashMap();
	public Env(DataHandler data) {
		this.data = data;
		for (String s: data.getData().keySet()) {
			this.watchers.put(s, new FileWatcher(s, this));
		}
	}
	
	public DataHandler getData() {
		return this.data;
	}
	
	public ObservableMap<String, FileWatcher> getWatchers(){
		return this.watchers;
	}
	
}
