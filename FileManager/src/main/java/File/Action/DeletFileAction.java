package File.Action;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;

public class DeletFileAction implements FileAction{

	@Override
	public void act(Path src) { 
		try {
			Files.delete(src);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
