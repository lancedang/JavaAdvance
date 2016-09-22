package dang.JavaAdvance.zuhejicheng;

/**
 * 组合以达到重用的目的
 * 
 * @author Dangdang
 *
 */
public class SprinkleSystem {
	private String val1 = "val1", val2, val3;
	private int i = 1;
	private float j;
	private double k;
	// private WaterSource source;
	private WaterSource source = new WaterSource();// 对象（不是类变量） 包含的引用变量若在定义时
													// 即初始化（new出来），则该对象的初始化（执行构造函数）发生在
													// 其外包类 初始化之前（执行构造函数）
	private WaterSource source2;
	public SprinkleSystem() {
		// TODO Auto-generated constructor stub
		j = 1.0f;
		source2 = new WaterSource();
		System.out.println("SprinkleSystem Constructor.");
	}

	public static void main(String[] args) {
		SprinkleSystem sprinkleSystem = new SprinkleSystem(); // 先调用其包含的new
																// WaterHouse()再
																// 执行自己的构造函数
		// System.out.println(sprinkleSystem); //把注释去掉 测验
	}

	public String toString() {
		if(val2 == null	) val2 = "val2";
		return "val1 = " + val1 + " val2 = " + val2 + " val3 = " + val3 + " i = " + i + " j = " + j + " k = " + k
				+ " Watersource = " + source;
	}
}
