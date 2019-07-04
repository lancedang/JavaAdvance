package basic.generic;

import java.util.List;
import java.util.Map;

/**
 * 泛型只能接受引用类型，基本类型int不可用
 * 
 * @author Dangdang
 *
 */
public class GenericTest {

	public static void main(String[] args) {
		Box<String> b1 = new Box<String>();
		b1.setData("StringType");
		b1.show();

		Box<Integer> b2 = new Box<Integer>();
		b2.setData(new Integer(5));
		b2.show();

		Box<Character> b3 = new Box<Character>();
		b3.setData('H');
		b3.show();

		System.out.println(b1.getClass());
		System.out.println(b2.getClass());
		System.out.println(b3.getClass());
		System.out.println(b1.getClass() == b2.getClass());
		System.out.println(b1.getClass() == b3.getClass());

	}
}

/**
 * 泛型类
 * @author Dangdang
 *
 * @param <T>
 */
class Box<T> {
	private T data;
	List<?> list;
	Map<?, ?> map;
	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}

	public void show() {
		System.out.println("This is test of Generic, data is " + data);
	}
}
