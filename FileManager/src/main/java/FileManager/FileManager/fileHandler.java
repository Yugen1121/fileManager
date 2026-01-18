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
		// Throw file not found exception if target doesn't exist
		Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
	}
}
	