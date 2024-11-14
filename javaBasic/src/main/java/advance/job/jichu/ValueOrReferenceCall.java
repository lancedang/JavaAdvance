package advance.job.jichu;

public class ValueOrReferenceCall {

	public static void main(String[] args) {
		int a = 1;
		System.out.println("a == " + a);
		setValue(a);
		System.out.println("a == " + a);

		StringBuffer str = new StringBuffer("abc");
		System.out.println("str = " + str);
		setStr(str);
		System.out.println("str = " + str);

		changeStr(str);
		System.out.println("str = " + str);

	}

	// 基本类型, 原生类型, 值传递, 不改变原值
	static void setValue(int a) {
		a = a + 10;
	}

	// 引用传递, 原引用值不变,
	static void setStr(StringBuffer str) {
		str = new StringBuffer("123"); // str引用值发生变化,指向新对象地址
	}

	// 引用传递, 原引用值不变, 但引用地址所指向的内容 发生了改变
	static void changeStr(StringBuffer str) {
		str = str.append("456"); // 原地址处, 修改指向内存中内容
	}

}
