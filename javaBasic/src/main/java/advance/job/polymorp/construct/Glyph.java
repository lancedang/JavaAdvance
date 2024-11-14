package advance.job.polymorp.construct;

public class Glyph {
	void draw() { // 被子类覆盖的方法
		System.out.println("Father Glyph draw.");
	}

	public Glyph() {
		System.out.println("In Father Glyph Constructor.");
		System.out.println("Glyph() before draw().");
		draw(); // 构造函数中的多态，指向了被子对象重写了的方法，因为子类未调用自己的构造函数，此刻子类的所有成员变量初始化为0
		System.out.println("Glyph() after draw().");
		System.out.println("End Father Glyph Constructor.");
	}
}
