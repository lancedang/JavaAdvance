package dang.advance.job.polymorp.inter;

public class Shape3 {
	public void show() {
		System.out.println("Shape3 show.");
	}

	// private 方法是隐含 final的, 不能被覆盖, 从而不能实现多态
	private void say() {
		System.out.println("Shape3 say.");
	}
}
