import java.util.concurrent.locks.ReentrantLock;


public abstract class DatabaseCommand implements Runnable {

	public static final String DB_ADDRESS = "jdbc:mysql://localhost/";
	public static final String DB_NAME = "mafia";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USER = "root";
	public static final String PASSWORD = "MediaPortal";	// Please set this to your password 
	//TODO set password via GUI
	
	protected ReentrantLock queryLock;
	
	public DatabaseCommand(ReentrantLock queryLock) {
		this.queryLock = queryLock;
	}
	
	@Override
	public void run() {
		queryLock.lock();
		execute();
		queryLock.unlock();
	}
	
	protected abstract boolean execute();
	
	// TODO remove this
	/* testing code -- to remove
	public static void main(String [] args) {
		ReentrantLock lock = new ReentrantLock();
		AddResult test = new AddResult(lock, "jamesbond", "mafia", true);
		test.run();
	}
	*/
}

