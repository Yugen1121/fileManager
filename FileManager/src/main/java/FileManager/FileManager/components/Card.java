package FileManager.FileManager.components;


import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class Card{
	
	private HBox root = new HBox();
	private Consumer<String> callableOnDelet;
	
	public Card(String path, String name) { 
		this.root.getStyleClass().add("filePathBox");
		Label pth = new Label(name);
		Button btn = new Button("Delet");
		btn.setOnAction(event -> {
			if (this.callableOnDelet != null) {
				this.callableOnDelet.accept(path);
			}
			event.consume();
		});
		root.getChildren().addAll(pth, btn);
		
	}

	public void setOnDelet(Consumer<String> callable) {
		this.callableOnDelet = callable;
	}
	
	
	public HBox getRoot() {
		return this.root;
	}
	
}
