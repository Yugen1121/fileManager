package FileManager.FileManager.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Card {
	public static VBox getCard(String path) { 
		VBox root = new VBox();
		HBox name = new HBox();
		name.getChildren().add(new Label(path));
		root.getChildren().add(name);
		return root;
	}
}
