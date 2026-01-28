package File.Action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveFileAction implements FileAction{

	private final Path target;
	public MoveFileAction(Path tget) {
		this.target = tget;
	}

	public void act(Path source) {				
		// throw exception if source file doesn't exist 
		if(!Files.exists(source)) {
			throw new RuntimeException("File doesn't exist.");
		}
				
		// Target path
		
		Path tgtFile = target.resolve(source.getFileName());
		// Atomically moves the file
		// Throw file not found exception 	if target doesn't exist
		try {
			Files.move(source, tgtFile, StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
