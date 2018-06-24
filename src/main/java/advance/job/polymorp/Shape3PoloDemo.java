package advance.job.polymorp;

import advance.job.polymorp.factory.Shape3Generator;
import advance.job.polymorp.inter.Shape3;
import advance.job.polymorp.interImpl.Circle3;

public class Shape3PoloDemo {
	public static void main(String[] args) {
		Shape3Generator generator = new Shape3Generator();
		Shape3 shape3_1 = generator.next("circle");
		Shape3 shape3_2 = generator.next("square");
		Circle3 circle3_3 = (Circle3) generator.next("circle");
		Shape3 shape3_4 = new Shape3();
		shape3_1.show();
		// shape3中的say是私有的, 虽然其继承者Circle3中 public 了此方法, 但是父类Shape3是引用不到子类sya的
		// shape3_1.say();
		//shape3_4.say();
		shape3_2.show();
		circle3_3.show();
		circle3_3.say();
	}
}
