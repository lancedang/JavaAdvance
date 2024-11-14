package advance.job.myfinal;

final class D{
	final int a = 3;
	final String s = "";
}

class Father {
	int i = 1;

	final void show() {
		System.out.println("father final void show");
	}
	
	private void f() {
		System.out.println("father f method");
	}

	void test() {
		System.out.println("father test not final method");
	}
}

public class FinalOverrideAndLoad extends Father {
	int i = 2;
	/*
	 * void show() {
	 * 
	 * }
	 */

	void f() {
		System.out.println("son f method");
	}
	
	@Override
	void test() {
		// TODO Auto-generated method stub
		super.test();
	}

	void show(int p) {
		System.out.println("in son overload method which has the same method name with father final method");
	}

	public static void main(String[] a) {
		// TODO Auto-generated method stub
		
		Father demo = new FinalOverrideAndLoad();
		Father demo3 = new Father();
		FinalOverrideAndLoad demo2 = new FinalOverrideAndLoad();
		
		D d = new D();
		
		demo2.f();
		
		demo.show();
		demo.test();
		demo2.show(3);
		System.out.println("demo.i = " + demo.i);
		System.out.println("demo3.i = " + demo3.i);
		System.out.println("demo2.i = " + demo2.i);

	}
}
