package test;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class Test {
	public static void main(String[] args) {
		System.out.println(test());;
	}
	static String test() {
		String a = new String("a");
		WeakReference<String> b = new WeakReference<String>(a);
		WeakHashMap<String, Integer> map = new WeakHashMap<>();
		map.put(b.get(), 1);
		a = null;
		System.gc();
		String cString = "";
		try {
			cString=b.get().replace("a", "b");
			return cString;
		} catch (Exception e) {
			// TODO: handle exception
			cString = "c";
			return cString;
		}finally {
			cString += "d";
			return cString + "e";
		}
	}
	public static void main2(String[] args) {
		Test2 test2_1 = new Test2();
		Test2 test2_2 = new Test2();
		Thread t1 = new Thread(test2_1, "thread1");
		Thread t2 = new Thread(test2_2, "thread2");
		t1.start();
		t2.start();
	}
}

class Test2 implements Runnable {
	@Override
	public void run() {
		System.out.println("Hello");
		System.out.println(Thread.currentThread().getName());
	}

	public void show() {

	}
}

class Test3 extends Thread {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

}
