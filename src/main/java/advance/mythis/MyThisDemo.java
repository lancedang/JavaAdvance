package advance.mythis;

/**
 * this的3种功能
 * 1. 控制构造函数流程
 * 2. 明确成员变量使用
 * 3. 对象引用
 *
 */

class BeCaller {
	void f() {
		System.out.println("BeCaller f method : " + this.getClass());
	}
}

class Caller {
	BeCaller beCaller = new BeCaller();

	void g() {
		beCaller.f();
		System.out.println("Caller g method : " + this.getClass());
	}
}

class Father {
	public Father() {
		System.out.println("father constructor no param");
	}
	Father(String str) {
		System.out.println("father constructor with param");
	}
	void f() {
		System.out.println("father f method : " + this.getClass());
	}
}
class Son extends Father {
	String s;
	public Son() {
		this("...");
		System.out.println("son constructor no param");
	}
	public Son(String s) {
		super("");
		this.s = "";
		System.out.println("son constructor with param");
	}
	Son getIns() {
		return this;
	}
	void g() {
		f();
		System.out.println("son g method : " + this.getClass());
	}
}

public class MyThisDemo {
	public static void main(String[] args) {
		// BeCaller beCaller = new BeCaller();
		// Caller caller = new Caller();
		// caller.g();
		Father father = new Son();
		// Father father2 = new Father();
		Son son = new Son();

		father.f();
		// father2.f();
		// son.g();
		System.out.println(son.s);
	}
}
