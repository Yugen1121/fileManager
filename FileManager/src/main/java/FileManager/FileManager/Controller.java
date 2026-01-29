package FileManager.FileManager;

import FileManager.FileManager.components.DirectoryPathSection;
import FileManager.FileManager.components.FilePathSection;
import FileManager.FileManager.components.FunctionAddDirectory;
import FileManager.FileManager.components.FunctionSection;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {
	@FXML private HBox root;
	@FXML private VBox right;
	@FXML private VBox left;
	@FXML private VBox rightTop;
	@FXML private VBox rightBottom;
	@FXML private VBox leftTop;
	@FXML private VBox leftBottom;
	

	private DataHandler dh;
	private Env env;
	
	public Controller(DataHandler dh, Env env) {
		this.env = env;
		this.dh = dh;

	}
	
	@FXML
	public void initialize() {
		DirectoryPathSection DPS = new DirectoryPathSection(env.getWatchers(), this.dh);
		DPS.setBuildFunctionAddDirectory(this::BuildFunctionAddSection);
		DPS.setBuildFunctionFilePathSection(this::BuildFunctionFilePathSection);
		DPS.setBuildFunctionSection(this::BuildFunctionSection);
		DPS.setWhenRemoved(this::removeDirectory);
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
		FAD.setOnAction(this::addDirectory);
		this.rightBottom.getChildren().clear();
		this.rightBottom.getChildren().add(FAD.getRoot());
	}
	
	private void addDirectory(String path, String name) {
		try {
			this.dh.addDirectory(path, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void removeDirectory(String directory) {
		try {
			this.dh.removeDirectory(directory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addNewFilePath(String dirPath, String fileType, String path) {
		try {
			this.dh.addNewFilePath(dirPath, fileType, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void updateData() {
		try {
			dh.updateData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


