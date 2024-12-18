package designPattern.singleton;

public class MySingletonThread implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		for (int i=0; i<100000; i ++) {
			MyEarlySingleton.getInstance();
			
		}
		System.out.println("MySingleton Spend: " + (System.currentTimeMillis() - start) + " ms");
	}

}
