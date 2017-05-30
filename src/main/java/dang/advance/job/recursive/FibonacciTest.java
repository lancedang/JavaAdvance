package dang.advance.job.recursive;

public class FibonacciTest {
	/**
	 * 非递归方式计算斐波那契数列 第n列 {0, 1, 1, 2, 3, 5.....}
	 * 
	 * @param n
	 * @return
	 */
	public static int getFibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else { // 斐波那契数列 是 第 n项是前 两项的和，所以 每次用变量保存前两项的值，每计算一次，改变 两变量
			int a = 0;
			int b = 1;
			int c = 0;
			while (n > 1) {
				c = a + b;
				a = b;
				b = c;

				n--;
			}
			return c;
		}
	}
	
	/**
	 * 递归斐波那契数列
	 * @param n
	 * @return
	 */
	public static int getFibonacci2(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return getFibonacci(n - 1) + getFibonacci(n - 2);
		}
	}
}
