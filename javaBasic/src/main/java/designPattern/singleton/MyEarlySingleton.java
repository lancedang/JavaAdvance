package designPattern.singleton;

//饱汉模式
public class MyEarlySingleton {

	//初始化的时候，直接创建单例对象
	private static final MyEarlySingleton myEarlySingleton = new MyEarlySingleton();

	private MyEarlySingleton() {
		System.out.println("This is my Singleton private construction method.");
	}
	
	public static MyEarlySingleton getInstance() {
		return myEarlySingleton;
	}
	
	public void show() {
		System.out.println("My Singleton static show method.");
	}

	public static void main(String[] args) {
		MyEarlySingleton instance = getInstance();
		instance.show();
	}
	
}
