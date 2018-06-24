package advance.job.polymorp.special;

/**
 * 此案例为了说明父类引用指向子类，对于静态方法，同同名域一样，多态也无作用
 * 
 * @author Dangdang
 *
 */
public class StaticMethodPoloDemo {
	public static void main(String[] args) {
		StaticFather father = new StaticSon();
		System.out.println("父类指向子引用 ,对于static 方法：");
		father.staticShow();
		System.out.println("父类指向子引用 ,对于非 static 方法：");
		father.nonStaticShow();
	}
}
