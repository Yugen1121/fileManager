package FileManager.FileManager;

import java.nio.file.*;

public class fileHandler {
	public static void moveFile(String src, String tgt) throws Exception{
		
		// Source path
		Path source = Paths.get(src);
		
		// throw exception if source file doesn't exist 
		if(!Files.exists(source)) {
			throw new RuntimeException("File doesn't exist.");
		}
		
		// Target path
		Path target = Paths.get(tgt);
		
		// Atomically moves the file
		// Throw file not found exception 	if target doesn't exist
		Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
	}
	
	public static void renameFile(String path, String name) throws Exception{
		// Source path
		Path source = Paths.get(path);
		
		// throw exception if source file doesn't exist 
		if(!Files.exists(source)) {
			throw new RuntimeException("File doesn't exist.");
		}
		
		// parent directory
		Path parent = source.getParent();
		
		// Target path
		Path target = parent.resolve(name);	
		
		// Atomically moves the file
		// Throw file not found exception 	if target doesn't exist
		Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
		
		
	}
}
	