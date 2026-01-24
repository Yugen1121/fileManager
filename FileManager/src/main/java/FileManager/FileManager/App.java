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
import javafx.scene.control.ScrollPane;
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
				

		// initialise scene
		Scene scene = new Scene(ld);
		
		scene.getStylesheets().add(
				getClass().getResource("/fx.css").toExternalForm()
				);		
		
		
		
		primaryStage.setTitle("Test");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
