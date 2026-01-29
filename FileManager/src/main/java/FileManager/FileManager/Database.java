package FileManager.FileManager;

public interface Database<T, J, K extends Exception> {
	T load() throws K;
	void update(J data) throws K;
}
