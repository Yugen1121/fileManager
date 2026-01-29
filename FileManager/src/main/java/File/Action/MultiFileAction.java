package File.Action;

import java.util.ArrayList;
import java.util.List;

public class MultiFileAction {
	private List<FileAction> actions = new ArrayList<>();
	
	public void addAction(FileAction action) {
		this.actions.add(action);
	}
	
	public void execute() {
		
	};
}
