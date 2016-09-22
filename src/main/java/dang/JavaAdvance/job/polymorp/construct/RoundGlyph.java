package dang.JavaAdvance.job.polymorp.construct;

/**
 * 
 * @author Dangdang
 *
 */
public class RoundGlyph extends Glyph {
	private int radius;
	// 成员变量初始化 在父类Glyph constructor执行完开始,在 RoundGlyph 构造函数前结束
	private Glyph glyph = new Glyph();
	//private RoundGlyph roundGlyph = new RoundGlyph(2);
	public RoundGlyph(int radius) {
		// TODO Auto-generated constructor stub
		this.radius = radius;
		System.out.println("Son RoundGlyph Constructor radius = " + radius);
	}

	void draw() {
		System.out.println("Son RoundGlyph draw raidus = " + radius);
	}
}
