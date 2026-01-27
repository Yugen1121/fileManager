package FileManager.FileManager;



import javafx.collections.ObservableMap;


import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

import io.methvin.watcher.DirectoryWatcher;

public class FileWatcher {
	private String directoryPath;
	private DirectoryConfig config;
	private DirectoryWatcher watcher;
	public FileWatcher(String path, Env env) {
		this.directoryPath = path;
		DataHandler dh = env.getData();
		this.config = dh.getData().get(path);
		if (config.getActive().get() == true) {
			try {
				this.startFileWatcher();
			}
			catch (Exception e) {
				System.out.println("Failed to start watcher for: " + this.directoryPath);
			}
		}
	}
	
	
	// starts a file watcher
	public void startFileWatcher() throws Exception{
		Path p = Paths.get(directoryPath).toAbsolutePath().normalize();
		this.watcher = DirectoryWatcher.builder()
				.path(p)
				.listener(e -> {
					switch (e.eventType()) {
					case CREATE: {
						try {
							Thread.sleep(500);
							Path iP = e.path();
							String sP= iP.toString();
							int ind = sP.lastIndexOf(".");
							String type = sP.substring(ind+1);
							if (config.getFilePaths().containsKey(type)) {
								fileHandler.moveFile(sP, config.getFilePaths().get(type));
							}
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					}
					
					default:
						throw new IllegalArgumentException("Unexpected value: ");
					}
				}).build();
		Thread watcherThread = new Thread(()->{
			try {
				watcher.watch();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		watcherThread.setDaemon(true);
		watcherThread.start();
	}
	
	public void closeWatcher() throws Exception {
		if (this.watcher != null) {
			try {
				this.watcher.close();
			}
			catch (IOException e) {
				throw new RuntimeException("Closing watcher failed");
			}
		}
	}
	
	
	
}
