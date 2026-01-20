package FileManager.FileManager;



import javafx.collections.ObservableMap;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.methvin.watcher.DirectoryWatcher;

public class FileWatcher {
	private String directoryPath;
	private DataHandler dh;
	private DirectoryConfig config;
	
	public FileWatcher(String path, DataHandler dh) {
		this.directoryPath = path;
		this.dh = dh;
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
	
	public void startFileWatcher() throws Exception{
		Path p = Paths.get(directoryPath).toAbsolutePath().normalize();
		System.out.println(p.toString());
		DirectoryWatcher watcher = DirectoryWatcher.builder()
				.path(p)
				.listener(e -> {
					System.out.println("0");
					switch (e.eventType()) {
					case CREATE: {
						try {
							System.out.println(1);
							Thread.sleep(500);
							Path iP = e.path();
							String sP= iP.toString();
							int ind = sP.lastIndexOf(".");
							String type = sP.substring(ind+1);
							if (config.getFilePaths().containsKey(type)) {
								fileHandler.moveFile(sP, config.getFilePaths().get(type));
								System.out.println("Successful");
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
	
	public String getDirectoryPath() {
		return this.directoryPath;
	}
	
	public StringProperty getName() {
		return this.config.getName();
	}
	
	// gets all file path
	public ObservableMap <String, String> getFilePaths(){
		return this.config.getFilePaths();
	}
	
	// adds a new file path 
	public synchronized void addFilePath(String fileType, String path) {
		this.config.getFilePaths().put(fileType, path);
		this.dh.updateData();
	}

	public synchronized void removeFilePath(String fileType) {
		this.config.getFilePaths().remove(fileType);
		this.dh.updateData();
	}
	
}
