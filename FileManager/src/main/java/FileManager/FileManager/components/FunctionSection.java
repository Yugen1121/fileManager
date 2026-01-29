package FileManager.FileManager.components;

import FileManager.FileManager.util.TriConsumer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
public class FunctionSection {
	// builds the function section
	private VBox root = new VBox();
	private TriConsumer<String, String, String> setOnAction;
	public FunctionSection(String path) {
			root.getChildren().clear();
			ObservableList<Node> children = root.getChildren();
			
			Label functions = new Label("Function");
			TextField fileTypeInput = new TextField();
			TextField filePath  = new TextField();
			Label addNewPath = new Label("Added new path for file type");
			Label newType = new Label("Type:");
			Label newPath = new Label("Path:");
			Button btn = new Button("Submit");
			
			btn.setOnAction(e -> {
				if (this.setOnAction != null) {
					this.setOnAction.accept(path, fileTypeInput.getText(), filePath.getText());
					filePath.clear();
					fileTypeInput.clear();
				}
			});
			
			children.addAll(functions, addNewPath, newType, fileTypeInput, newPath, filePath, btn);
	}
	
	public void setOnAction(TriConsumer<String, String, String> callable) {
		this.setOnAction = callable;
	}
	
	public VBox getRoot() {
		return this.root;
	}
}
