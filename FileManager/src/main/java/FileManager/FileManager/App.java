package FileManager.FileManager;



import java.util.HashMap;
import java.util.Map;

import FileManager.FileManager.components.Card;
import javafx.application.Application;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;

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
		// load FXML document 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
		
		// load the component from FXMl file to the parent component
		Parent ld = loader.load();
				
		// load components with id from the FXML file 
		Controller controller = loader.getController();

		// initialise scene
		Scene scene = new Scene(ld);
		
		scene.getStylesheets().add(
				getClass().getResource("/fx.css").toExternalForm()
				);		
		
		
		ObservableList<Node> leftTopChildren = controller.leftTop.getChildren();
		
		// initialise data handler
		DataHandler dh = new DataHandler();
		// initialise the environment
		Env env = new Env(dh);
		
		Map<String, Node> leftTopNodes = new HashMap<>();
		
		// add path cards to the GUI
		env.getWatchers().addListener((MapChangeListener<String, FileWatcher>) change -> {
			String key = change.getKey();
			if (change.wasAdded()) {
				FileWatcher newFw = env.getWatchers().get(key);
				Card cd = new Card(newFw, controller.rightTop);
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
			Card cd = new Card(fw, controller.rightTop);
			leftTopChildren.add(cd.getRoot());
		}
		
		primaryStage.setTitle("Test");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
