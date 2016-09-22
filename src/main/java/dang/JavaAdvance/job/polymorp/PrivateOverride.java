package dang.JavaAdvance.job.polymorp;

/**
 * 这个例子不错, 子类"覆盖" 父类私有方法？
 * @author Dangdang
 *
 */
public class PrivateOverride {
	public void f() {
		System.out.println("public f in father");
	}

	private void f2() {
		System.out.println("private f2 in father");
	}

	public static void main(String[] args) {
		PrivateOverride demo = new Derived();
		//
		demo.f();
		demo.f2();
		// other 方法只存在子类中,父类无此方法 ,儿子私有的父类你也够不到
		// demo.other();
	}
}
