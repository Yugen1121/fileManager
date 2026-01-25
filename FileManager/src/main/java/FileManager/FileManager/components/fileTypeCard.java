package FileManager.FileManager.components;

import java.util.function.Consumer;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class fileTypeCard {
	
	private Consumer<String> callableOnClick;
	private Consumer<String> callableOnDelet;
	private HBox root = new HBox();

	public fileTypeCard(String fileType,String path) { 
		Label k = new Label(fileType+": ");
		Label p = new Label(path);
		Button btn = new Button("delet");
		btn.setOnAction(e -> {
			if (this.callableOnDelet != null) {
				this.callableOnDelet.accept(fileType);
			}
		}
		);
		
		ScrollPane sk = new ScrollPane(k);
		ScrollPane sp = new ScrollPane(p);
		sk.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		sk.setFitToHeight(true);
		sp.setFitToHeight(true);
		root.getChildren().addAll(sk, sp, btn);
		
		}

		public void setOnDelet(Consumer<String> callable) {
			this.callableOnDelet = callable;
		}
	
		public void setOnClick(Consumer<String> callable) {
			this.callableOnClick = callable; 
		}
	
		public HBox getRoot() {
			return this.root;
		}
}
