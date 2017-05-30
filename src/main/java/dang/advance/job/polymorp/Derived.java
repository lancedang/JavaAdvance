package dang.advance.job.polymorp;

public class Derived extends PrivateOverride {
	// 覆盖父类f
	public void f() {
		System.out.println("public f in son");
	}

	// 没有覆盖父类的f2(),因为父类f2()为私有的(私有暗含是final修饰的)
	public void f2() {
		System.out.println("public f2 in son");
	}

	public void other() {
		System.out.println("mehtod only in Derived not in father");
	}
}
