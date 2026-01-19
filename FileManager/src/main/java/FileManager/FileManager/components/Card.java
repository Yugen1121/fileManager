package FileManager.FileManager.components;

import FileManager.FileManager.FileWatcher;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class Card{
	private VBox mainSection;
	private FileWatcher fw;
	private VBox root;
	
	public Card(FileWatcher fw, VBox right) { 
		this.root = new VBox();
		this.fw = fw;
		this.mainSection = right; 
		build();
	}
	
	public VBox getRoot() {
		return this.root;
	}
	
	private void build() {
		this.root.getChildren().clear();
		HBox name = new HBox();
		name.getChildren().add(new Label(fw.getName().get()));
		name.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			buildMainSection();
		});
		
		root.getChildren().add(name);
	}
	
	private void buildMainSection() {
		mainSection.getChildren().clear();
		fw.getFilePaths().addListener((MapChangeListener<String, String>) change -> {
			buildMainSection();
		});
		ObservableMap<String, String> map = fw.getFilePaths(); 
		for (String k: map.keySet()) {
			mainSection.getChildren().add(new Label(k+": "+map.get(k)));
		}
		TextField fileTypeInput = new TextField();
		TextField filePath  = new TextField();
		mainSection.getChildren().add(fileTypeInput);
		mainSection.getChildren().add(filePath);
		Button btn = new Button("Submit");
		btn.setOnAction(e -> {
			fw.addFilePath(fileTypeInput.getText(), filePath.getText());
		});
		mainSection.getChildren().add(btn);
	}
	
}
