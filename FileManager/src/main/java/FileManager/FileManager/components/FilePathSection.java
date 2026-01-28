package FileManager.FileManager.components;

import java.util.HashMap;
import java.util.Map;

import FileManager.FileManager.DataHandler;
import FileManager.FileManager.DirectoryConfig;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class FilePathSection {
	private VBox root = new VBox();
	private Runnable onDataUpdate;
	
	public FilePathSection(String path, ObservableMap<String, String> map) {
		ObservableList<Node> children = root.getChildren();
		children.clear();
		
		Map<String, Node> Nodes = new HashMap<>();
		map.addListener((MapChangeListener<String, String>) change -> {
			
				String k = change.getKey();
				if (change.wasAdded()) {
					fileTypeCard ftc = new fileTypeCard(k, map.get(k));
					ftc.setOnDelet(fileType-> {
						map.remove(fileType);
						if (this.onDataUpdate != null) {
							this.onDataUpdate.run();
						}
					});
					children.add(ftc.getRoot());
					Nodes.put(k, ftc.getRoot());
					
				}
				else if (change.wasRemoved()) {
					Node removed = Nodes.remove(k);
					if (removed != null) {
						children.remove(removed);
					}
				}
		});
		
		for (String key: map.keySet()) {
			fileTypeCard ftc = new fileTypeCard(key, map.get(key));
			ftc.setOnDelet(fileType-> {
				map.remove(fileType);
				if (this.onDataUpdate != null) {
					this.onDataUpdate.run();
				}
				
			});
			Nodes.put(key, ftc.getRoot());
			children.add(ftc.getRoot());
		}
	}
	
	public void setOnDataUpdate(Runnable action) {
		this.onDataUpdate = action;
	}
	
	public VBox getRoot() {
		return this.root;
	}

}
