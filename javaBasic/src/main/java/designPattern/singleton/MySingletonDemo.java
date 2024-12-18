package designPattern.singleton;

public class MySingletonDemo {
	public static void main(String[] args) {
/*		
		MySingleton mySingleton = MySingleton.getInstance();
		MyLazySingleton myLazySingleton = MyLazySingleton.getInstance();
		*/
		
		//MyEarlySingleton.show();
		MyLazySingleNullCheckSingleton.show();
		
		MySingletonThread singletonThread = new MySingletonThread();
		Thread thread1_1 = new Thread(singletonThread);
		Thread thread1_2 = new Thread(singletonThread);
		Thread thread1_3 = new Thread(singletonThread);
		Thread thread1_4 = new Thread(singletonThread);
		Thread thread1_5 = new Thread(singletonThread);
		
		MyLazySingletonThread myLazySingletonThread = new MyLazySingletonThread();
		
		Thread thread2_1 = new Thread(myLazySingletonThread);
		Thread thread2_2 = new Thread(myLazySingletonThread);
		Thread thread2_3 = new Thread(myLazySingletonThread);
		Thread thread2_4 = new Thread(myLazySingletonThread);
		Thread thread2_5 = new Thread(myLazySingletonThread);
		
		thread1_1.start();
		thread2_1.start();
	}
}
