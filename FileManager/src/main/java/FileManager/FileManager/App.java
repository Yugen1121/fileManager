package FileManager.FileManager;


import FileManager.FileManager.components.Card;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


public class App extends Application
{
    public static void main( String[] args )
    {
    	launch(args);
    }
    
    public App() {
    	
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// root component to go to the root
		StackPane root = new StackPane();
		
		// initialise scene
		Scene scene = new Scene(root, 400, 400);
		
		// initialise a main component
		HBox main = new HBox();
		
		root.getChildren().add(main);
		
		VBox left = new VBox();
		VBox right = new VBox();
		
		ObservableList<Node> leftChildren = left.getChildren();
		
		// initialise data handler
		DataHandler dh = new DataHandler();
		System.out.println(dh.getData());
		// add path cards to the GUI
		for (String pth: dh.getData().keySet()) {
			FileWatcher d = new FileWatcher(pth, dh);
			Card c = new Card(d, right);
			leftChildren.add(c.getRoot());
		}
		System.out.println(leftChildren);
		
		main.getChildren().add(left);
		main.getChildren().add(right);
		System.out.println(leftChildren);
		primaryStage.setTitle("Test");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
