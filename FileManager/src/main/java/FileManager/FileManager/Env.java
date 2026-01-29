package FileManager.FileManager;

import File.FileWatcher;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class Env {
	private DataHandler data;
	private ObservableMap<String, FileWatcher> watchers = FXCollections.observableHashMap();
	public Env(DataHandler data) {
		this.data = data;
		for (String s: data.getData().keySet()) {
			try {
				this.watchers.put(s, new FileWatcher(s, data.getDirectoryConfig(s), 
						data.getDirectoryConfig(s).getActive().get()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		data.getData().addListener((MapChangeListener<String, DirectoryConfig>) change -> {
			String key = change.getKey();
			try {
				if (change.wasAdded()) {
					this.watchers.put(key, new FileWatcher(key, data.getDirectoryConfig(key), 
							data.getDirectoryConfig(key).getActive().get()));
				}
				else if (change.wasRemoved()) {
					this.removeWatcher(key);
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		
	}
	
	public DataHandler getData() {
		return this.data;
	}
	
	public void removeWatcher(String key) throws Exception {
		this.watchers.get(key).closeWatcher();
		this.watchers.remove(key);
	}
	
	public ObservableMap<String, FileWatcher> getWatchers(){
		return this.watchers;
	}
	
}
