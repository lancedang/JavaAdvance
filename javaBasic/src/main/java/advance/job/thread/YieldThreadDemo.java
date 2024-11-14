package advance.job.thread;

class MyYieldThread extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}

public class YieldThreadDemo {
	public static void demo(String[] args) {
		MyYieldThread myYieldThread = new MyYieldThread();
		Thread thread1 = new Thread(myYieldThread);
		Thread thread2 = new Thread(myYieldThread);
		Thread thread3 = new Thread(myYieldThread);
		thread1.start();
		thread2.start();
		thread3.start();

	}

	public static void main(String[] args) {
		String aString = "abc";
		System.out.println(aString.hashCode());
		aString.toUpperCase();
		System.out.println(aString.hashCode());
		System.out.println();
		aString = "a";
		System.out.println(aString.hashCode());
		
		String c = "1as";
		String bString = new String("1as");
		String dString = "1as";
		System.out.println("--" + bString+ "--");
		System.out.println("bString == c ?? " + bString==c);
		System.out.println("dString == c ?? " + dString==c);
		
	}

}
