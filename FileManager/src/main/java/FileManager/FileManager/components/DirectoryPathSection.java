package FileManager.FileManager.components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import File.FileWatcher;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DirectoryPathSection {
	private VBox root = new VBox();
	private Runnable buildFunctionAddDirectory;
	private Consumer<String> buildFunctionFilePathSection;
	public DirectoryPathSection(ObservableMap<String, FileWatcher> fileWatchers) {
		ObservableList<Node> leftTopChildren = root.getChildren();
		Map<String, Node> leftTopNodes = new HashMap<>();
		
		HBox DWABox = new HBox();
		Label Lbl = new Label("Add new directory to watch");
		Button btn2 = new Button("Add");
		DWABox.setOnMouseClicked(event -> {
			if (this.buildFunctionAddDirectory != null) {
				this.buildFunctionAddDirectory.run();
			}
		});
		
		DWABox.getChildren().addAll(Lbl, btn2);
		leftTopChildren.add(DWABox);
		
		// add path cards to the GUI
		fileWatchers.addListener((MapChangeListener<String, FileWatcher>) change -> {
			String key = change.getKey();
			if (change.wasAdded()) {
				FileWatcher newFw = fileWatchers.get(key);
				Card cd = new Card(newFw.getDirectoryPath(), newFw.getName());
				cd.setOnClick(path -> {
						if (this.buildFunctionFilePathSection != null) {
							this.buildFunctionFilePathSection.accept(path);
						}
				});
				leftTopNodes.put(key, cd.getRoot());
				leftTopChildren.add(cd.getRoot());
			}
			
			if (change.wasRemoved()) {
				Node removedNode = leftTopNodes.remove(key);
				if (removedNode != null) {
					leftTopChildren.remove(removedNode);
				}
			}
		});
		
		// Adds the directory watcher button to the GUI
		for (FileWatcher fw: fileWatchers.values()) {
			Card cd = new Card(fw.getDirectoryPath(), fw.getName());
			cd.setOnClick(path -> {
				if (this.buildFunctionFilePathSection != null){
					this.buildFunctionFilePathSection.accept(path);
				}
			});
			
			leftTopChildren.add(cd.getRoot());
		}
	}
	
	public void setBuildFunctionAddDirectory(Runnable runnable) {
		this.buildFunctionAddDirectory = runnable;
	}
	
	public void setBuildFunctionFilePathSection(Consumer<String> callable) {
		this.buildFunctionFilePathSection = callable;
	}
	
	public VBox getRoot() {
		return this.root;
	}
}
