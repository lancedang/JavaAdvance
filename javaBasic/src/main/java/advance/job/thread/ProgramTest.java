package advance.job.thread;


public class ProgramTest {
	public static void main(String[] args) {
		Program program = new Program();
		Thread proxy = new Thread(program);
		proxy.start();
	}
}
