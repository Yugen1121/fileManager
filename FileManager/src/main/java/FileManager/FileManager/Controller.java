package FileManager.FileManager;
import java.util.HashMap;
import java.util.Map;

import FileManager.FileManager.components.Card;
import FileManager.FileManager.components.DirectoryPathSection;
import FileManager.FileManager.components.FilePathSection;
import FileManager.FileManager.components.FunctionAddDirectory;
import FileManager.FileManager.components.FunctionSection;
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
		DirectoryPathSection DPS = new DirectoryPathSection(env.getWatchers());
		DPS.setBuildFunctionAddDirectory(null);
		DPS.setBuildFunctionFilePathSection(this::BuildFunctionFilePathSection);
		this.leftTop.getChildren().add(DPS.getRoot());
	}
	
	private void BuildFunctionFilePathSection(String path) {
		this.rightTop.getChildren().clear();
		FilePathSection FPS = new FilePathSection(path, dh.getFilePaths(path));
		FPS.setOnDataUpdate(this::updateData);
		this.rightTop.getChildren().add(FPS.getRoot());
	}
	
	private void BuildFunctionSection(String path) {
		FunctionSection FS = new FunctionSection(path);
		FS.setOnAction(this::addNewFilePath);
		this.rightBottom.getChildren().clear();
		this.rightBottom.getChildren().add(FS.getRoot());
		
	}
	
	private void BuildFunctionAddSection() {
		FunctionAddDirectory FAD = new FunctionAddDirectory();
		this.rightBottom.getChildren().clear();
		this.rightBottom.getChildren().add(FAD.getRoot());
	}
	
	private void addNewFilePath(String dirPath, String fileType, String path) {
		DirectoryConfig DC = dh.getDirectoryConfig(dirPath);
		try {
			DC.addNewFilePath(fileType, path);
		} catch (Exception e) { 
			System.out.print(e.getMessage());
		}
	}
	private void updateData() {
		dh.updateData();
	}
	
	
}


