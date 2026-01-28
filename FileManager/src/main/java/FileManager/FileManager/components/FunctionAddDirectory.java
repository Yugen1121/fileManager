package FileManager.FileManager.components;

import java.util.function.BiConsumer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FunctionAddDirectory {
	private BiConsumer<String, String> onActionCallable;
	private VBox root = new VBox();
	public FunctionAddDirectory() {
		Label path = new Label("Directory Path.");
		TextField pathField = new TextField();
		Label name = new Label("Give a name to your directory");
		TextField nameField = new TextField();
		Button btn = new Button("Add");
		btn.setOnAction(event -> {
			this.onActionCallable.accept(pathField.getText(), nameField.getText());
			pathField.clear();
			nameField.clear();
		});
		this.root.getChildren().addAll(path, pathField, name, nameField);
	}
	
	public void setOnAction(BiConsumer<String, String> callable) {
		this.onActionCallable = callable;
	}
	
	public VBox getRoot() {
		return this.root;
	}
}
