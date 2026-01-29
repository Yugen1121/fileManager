package FileManager.FileManager.components;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import File.FileWatcher;
import FileManager.FileManager.DataHandler;
import javafx.beans.property.StringProperty;
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
	private Consumer<String> buildFunctionSection;
	private Consumer<String> removeDirectory;
	
	public DirectoryPathSection(ObservableMap<String, FileWatcher> fileWatchers, DataHandler dh) {
		ObservableList<Node> children = root.getChildren();
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
		children.add(DWABox);
		
		// add path cards to the GUI
		fileWatchers.addListener((MapChangeListener<String, FileWatcher>) change -> {
			String key = change.getKey();
			if (change.wasAdded()) {
				Node cd = this.createCard(key, dh.getData().get(key).getName());
				children.add(cd);
				leftTopNodes.put(key, cd);
			}
			
			if (change.wasRemoved()) {
				Node removedNode = leftTopNodes.remove(key);
				if (removedNode != null) {
					children.remove(removedNode);
				}
			}
		});
		
		// Adds the directory watcher button to the GUI
		for (String key: fileWatchers.keySet()) {
			Node nd = this.createCard(key, dh.getData().get(key).getName());
			children.add(nd);
			leftTopNodes.put(key, nd);
		}
	}
	
	public HBox createCard(String path, StringProperty name) {
		Card cd = new Card(path, name);
		cd.setOnDelet(pa -> {
			if (this.removeDirectory != null) {
				this.removeDirectory.accept(pa);
			}
		});
		cd.setOnClick(pa -> {
			if (this.buildFunctionFilePathSection != null){
				this.buildFunctionFilePathSection.accept(pa);
				this.buildFunctionSection.accept(pa);
			}
		});
		return cd.getRoot();
	}
	public void setBuildFunctionAddDirectory(Runnable runnable) {
		this.buildFunctionAddDirectory = runnable;
	}
	
	public void setBuildFunctionFilePathSection(Consumer<String> callable) {
		this.buildFunctionFilePathSection = callable;
	}
	
	public void setBuildFunctionSection(Consumer<String> callable) {
		this.buildFunctionSection = callable;
	}
	
	public void setWhenRemoved(Consumer<String> callable) {
		this.removeDirectory = callable;
	}
	
	public VBox getRoot() {
		return this.root;
	}
}
