package dang.advance.job.thread;

class SynchronizedObject {
	private String name = "a";
	private String pwd = "aa";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	synchronized public void printString(String name, String pwd) {
		try {
			this.name = name;
			Thread.sleep(2000);
			this.pwd = pwd;
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

class MyStopThread extends Thread {
	SynchronizedObject synchronizedObject;

	public MyStopThread(SynchronizedObject synchronizedObject) {
		// TODO Auto-generated constructor stub
		this.synchronizedObject = synchronizedObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		synchronizedObject.printString("b", "bb");
	}
}

public class StopThrowLock {
	public static void main(String[] args) {
		SynchronizedObject synchronizedObject = new SynchronizedObject();
		MyStopThread myStopThread = new MyStopThread(synchronizedObject);
		myStopThread.start();
		try {
			Thread.sleep(1000);
			myStopThread.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("name = " + synchronizedObject.getName() + "; pwd = " + synchronizedObject.getPwd());
	}
}
