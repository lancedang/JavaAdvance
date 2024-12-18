package designPattern.singleton;

public class MyLazySingletonThread implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		for (int i=0; i<100000; i++) {
			MyLazySingleNullCheckSingleton.getInstance();
		}
		System.out.println("MyLazySingleton Spend: " + (System.currentTimeMillis() - start) + " ms");
	}

}
