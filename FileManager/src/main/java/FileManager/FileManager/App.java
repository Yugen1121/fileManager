package FileManager.FileManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

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
		
		DataHandler dh = new DataHandler();
		
		Env env = new Env(dh);
		
		loader.setControllerFactory( clazz -> 
			new Controller(dh, env, primaryStage)
				
				);
		
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
