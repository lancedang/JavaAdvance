package dang.advance.job.myfinal;

import java.util.Random;

public class FinalDemo {
	private String id;
	private static Random random = new Random(30);

	// 编译期常量
	private final int valone = 9;
	private static final int VAL_TWO = 99;

	// 典型的公共编译期常量
	public static final int VAL_THREE = 39;

	// 非编译期常量
	private final int val4 = random.nextInt(20);
	static final int VAL_5 = random.nextInt(20);

	private ValueData v1 = new ValueData(11);
	private final ValueData v2 = new ValueData(22);
	private static final ValueData VAL_3 = new ValueData(33);

	private final int[] array = { 1, 3, 4, 5, 6 };

	public FinalDemo(String id) {
		this.id = id;
	}

	public String toString() {
		return id + ": val4 = " + val4 + " , VAL_5 = " + VAL_5;
	}

	public static void main(String[] args) {
		FinalDemo demo = new FinalDemo("finalDemo");
		// final 变量(基本数据类型) 不能改变
		// demo.valone++;

		// 私有非final 引用类型
		demo.v1.i++;
		// final 引用类型(也能修改其封装的变量),Object not constant
		demo.v2.i++;
		// 修改非 final引用变量值
		demo.v1 = new ValueData(4);
		// 修改 final 引用变量值
		// demo.v2 = new ValueData(4);

		// 数组final但其中内容(元素值)还是可更改的，Object not constant
		for (int i = 0; i < demo.array.length; i++) {
			demo.array[i]++;
		}
		// final修饰的引用 ，该引用反正是不能改变的，但引用值标志的地址里的内容可变
		// demo.VAL_3 = new ValueData(4);
		//同上
		//demo.array = new int[4];
	}

}
