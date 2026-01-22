package FileManager.FileManager.components;


import FileManager.FileManager.FileWatcher;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
	private int height = 250;
	private int width = 500;
	
	
	public Card(FileWatcher fw, VBox right) { 
		this.root = new VBox();
		this.root.getStyleClass().add("filePathBox");
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
			mainSection.getChildren().add(buildFilePathCard(k, map.get(k)));
		}
		TextField fileTypeInput = new TextField();
		TextField filePath  = new TextField();
		mainSection.getChildren().add(fileTypeInput);
		mainSection.getChildren().add(filePath);
		Button btn = new Button("Submit");
		btn.setOnAction(e -> {
			try {
				fw.addFilePath(fileTypeInput.getText(), filePath.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
		});
		mainSection.getChildren().add(btn);
	}
	
	private HBox buildFilePathCard(String key, String path) {
		HBox root = new HBox();
		root.setPrefWidth(width);
		Label k = new Label(key+": ");
		Label p = new Label(path);
		Button btn = new Button("delet");
		btn.setOnAction(e -> {
			this.fw.removeFilePath(key);
		});
		ScrollPane sk = new ScrollPane(k);
		ScrollPane sp = new ScrollPane(p);
		sk.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sk.setFitToHeight(true);
		sp.setFitToHeight(true);
		sk.setPrefWidth(width*0.2);
		sp.setPrefWidth(width*0.5);
		btn.setPrefWidth(width*0.3);
		root.getChildren().add(sk);
		root.getChildren().add(sp);
		root.getChildren().add(btn);
		return root;
		
	}
	
}
