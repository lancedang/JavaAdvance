package dang.advance.reflect;

public class MyTest {
	private int id;
	public String name;

	public MyTest() {
		System.out.println("Non-param Constructor");
	}

	public MyTest(int id) {
		this.id = id;
		System.out.println("Int param Constuctor");
	}

	public MyTest(String name) {
		this.name = name;
		System.out.println("String param Constuctor");
	}

	public MyTest(int id, String name) {
		this.id = id;
		this.name = name;
		System.out.println("Int and String param Constuctor");

	}
	
	public void show() {
		System.out.println("This is test show method.");
	}
	
	public String hello(String hi) {
		return "Hello. ";
	}
}
