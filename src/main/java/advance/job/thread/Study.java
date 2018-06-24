package advance.job.thread;

public class Study implements Runnable {
	private boolean flag = true;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (flag) {
			System.out.println("this is run");
		}
	}

	public void stop() {
		this.flag = false;
	}

	public static void main(String[] args) throws InterruptedException {
		Study study = new Study();
		Thread proxy = new Thread(study);
		proxy.start();
		Thread.sleep(100);
		for (int i = 0; i < 1000; i++) {
			if (i == 500) {
				study.stop();
			}
		}
	}
}
