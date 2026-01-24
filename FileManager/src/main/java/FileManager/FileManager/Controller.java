package FileManager.FileManager;
import java.util.HashMap;
import java.util.Map;

import FileManager.FileManager.components.Card;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
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
		this.stage = stage;
	}
	
	@FXML
	public void initialize() {
		
		leftTop.getStyleClass().add("quarter");
		left.getChildren().add(leftTop);
		
		ObservableList<Node> leftTopChildren = leftTop.getChildren();
		
		ScrollPane ltPane = new ScrollPane(leftTop);
		
		Map<String, Node> leftTopNodes = new HashMap<>();
		
		// add path cards to the GUI
		env.getWatchers().addListener((MapChangeListener<String, FileWatcher>) change -> {
			String key = change.getKey();
			if (change.wasAdded()) {
				FileWatcher newFw = env.getWatchers().get(key);
				Card cd = new Card(newFw, right);
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
			Card cd = new Card(fw, right);
			leftTopChildren.add(cd.getRoot());
		}
	}
	
	

}
