package dang.advance.job.polymorp.factory;

import java.util.Random;

import dang.advance.job.polymorp.inter.Shape;
import dang.advance.job.polymorp.interImpl.Circle;
import dang.advance.job.polymorp.interImpl.Square;
import dang.advance.job.polymorp.interImpl.Triangle;

public class ShapeGenerator {

	private Random random = new Random(47);

	/**
	 * random.next()说明返回的导出类具体类型在编译期是不确定的,只有当运行时才能确定, 但不影响编译进行,动态/运行时/后期绑定的体现,最终
	 * 导致多态的 出现
	 * 
	 * @return
	 */
	public Shape next() {
		int num = random.nextInt(3);
		switch (num) {
		default:
		case 0:
			return new Circle();
		case 1:
			return new Triangle();
		case 2:
			return new Square();
		}
	}
}
