package FileManager.FileManager;
import java.util.HashMap;
import java.util.Map;

import FileManager.FileManager.components.Card;
import FileManager.FileManager.components.fileTypeCard;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller {
	@FXML private HBox root;
	@FXML private VBox right;
	@FXML private VBox left;
	@FXML private VBox rightTop;
	@FXML private VBox rightBottom;
	@FXML private VBox leftTop;
	@FXML private VBox leftBottom;
	
	private Stage stage;
	private DataHandler dh;
	private Env env;
	
	public Controller(DataHandler dh, Env env, Stage stage) {
		this.env = env;
		this.dh = dh;
		this.stage = stage;
	}
	
	@FXML
	public void initialize() {
		leftTop.getStyleClass().add("quarter");
		ObservableList<Node> leftTopChildren = leftTop.getChildren();
		
		ScrollPane ltPane = new ScrollPane(leftTop);
		
		Map<String, Node> leftTopNodes = new HashMap<>();
		
		// add path cards to the GUI
		env.getWatchers().addListener((MapChangeListener<String, FileWatcher>) change -> {
			String key = change.getKey();
			if (change.wasAdded()) {
				FileWatcher newFw = env.getWatchers().get(key);
				Card cd = new Card(newFw.getDirectoryPath(), newFw.getName());
				cd.setOnClick(path -> {
						this.buildRight(path);
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
		
		for (FileWatcher fw: env.getWatchers().values()) {
			Card cd = new Card(fw.getDirectoryPath(), fw.getName());
			cd.setOnClick(path -> {
				this.buildRight(path);
			});
			
			leftTopChildren.add(cd.getRoot());
		}
	}

	private void buildRight(String path) {
		this.buildFilePathSection(path);
		this.buildFunctionSection(path);
	}
	
	private void buildFilePathSection(String path) {
		ObservableList<Node> children = this.rightTop.getChildren();
		children.clear();
		DirectoryConfig dConfig = this.dh.getData().get(path);
		if (dConfig == null) {
			throw new RuntimeException("File path for the file type could'nt be found");
		}
		ObservableMap<String, String> map = dConfig.getFilePaths();
		Map<String, Node> Nodes = new HashMap<>();
		map.addListener((MapChangeListener<String, String>) change -> {
			String k = change.getKey();
			if (change.wasAdded()) {
				fileTypeCard ftc = new fileTypeCard(k, map.get(k));
				ftc.setOnDelet(fileType-> {
					map.remove(fileType);
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
			});
			Nodes.put(key, ftc.getRoot());
			children.add(ftc.getRoot());
		}
	}
	
	private void buildFunctionSection(String path) {
		rightBottom.getChildren().clear();
		ObservableList<Node> children = rightBottom.getChildren();
		Label functions = new Label("Function");
		TextField fileTypeInput = new TextField();
		TextField filePath  = new TextField();
		Label addNewPath = new Label("Added new path for file type");
		Button btn = new Button("Submit");
		btn.setOnAction(e -> {
			try {
				this.env.getWatchers().get(path).addFilePath(fileTypeInput.getText(), filePath.getText());
				fileTypeInput.clear();
				filePath.clear();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}
		});
		children.addAll(functions, addNewPath, fileTypeInput, filePath, btn);
	}
}


