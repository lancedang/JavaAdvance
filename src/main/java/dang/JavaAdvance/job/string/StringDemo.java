package dang.JavaAdvance.job.string;

/**
 * 案例为了说明对String和StringBuffer操作是基于自身(return this)的还是 返回新值的(return new String)
 * @author Dangdang
 *
 */
public class StringDemo {
	public static void main(String[] args) {
		String s = "Hello";
		s.toUpperCase();
		s.replace('o', 'O');
		s.substring(3);
		System.out.println(s);
		System.out.println(s.toUpperCase());
		System.out.println(s == s.toUpperCase());
		
		StringBuffer ss = new StringBuffer("hello");
		ss.reverse();
		ss.append("123");
		System.out.println(ss);
		ss.substring(3);
		System.out.println(ss);
		System.out.println(ss == ss.reverse());
	}
}
