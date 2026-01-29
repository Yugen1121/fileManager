module FileManager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
	requires javafx.base;
	requires directory.watcher;

    // This line is the key! 
    // It allows JavaFX to see your App class even if it's inside a module.
    opens FileManager.FileManager to javafx.graphics, javafx.fxml, com.google.gson;

    exports FileManager.FileManager;
}