package dang.JavaAdvance.job.polymorp.special;

/**
 * 此案例为了说明父类指向子类同名域Field时 无多态(还是返回的父类域 值)
 * @author Dangdang
 *
 */
public class FieldPoloDemo {
	public static void main(String[] args) {
		
		FieldFather father = new FieldSon();
		FieldFather father2 = new FieldSon2();

		// 按照多态的概念, 存在继承,且父类指向子对象, father会自动调用不同 的子类的值，但可以看出对于field变量来说并非如此
		
		System.out.println("父类指向子类同名 Field 成员变量, 无多态");
		System.out.println("father.field = " + father.field);
		System.out.println("father.field = " + father2.field);

		System.out.println("父类指向子类同名 方法(子类覆盖), 存在多态");
		System.out.println("father.getField() = " + father.getField());
		System.out.println("father.getField() = " + father2.getField());

		FieldSon son = new FieldSon();
		System.out.println("son.field = " + son.getField());
		System.out.println("son.getField = " + son.getField());
		System.out.println("son.getFatherField = " + son.getFatherField());

	}
}
