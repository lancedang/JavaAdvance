package advance.job.thread;

public class Program implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 1000; i++) {
			System.out.println("一边helloword " + i);
		}
	}

}
