package advance.job.polymorp;

import advance.job.polymorp.factory.ShapeGenerator;
import advance.job.polymorp.inter.Shape;

/**
 * 运行时生成导出类,才最终选定具体类的实现代码,next()方法, random()作用
 * 
 * @author Dangdang
 *
 */
public class ShapePoloDemo {
	public static void main(String[] args) {
		ShapeGenerator generator = new ShapeGenerator();
		Shape[] shapes = new Shape[5];
		int i = 0;
		while (i < 5) {
			shapes[i] = generator.next();
			i++;
		}

		while (i > 0) {
			shapes[i - 1].draw();
			shapes[i - 1].erase();
			i--;
		}

	}
}
