package advance.job.recursive;

public class JumpFloor {

	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级，求该青蛙跳上一个n级的台阶总共有多少种跳法。 思路：调到target上，只能从
	 * target-1，和target-2 台阶 跳过来，实际上是菲波那切数列f(n)=f(n-1)+f(n-2)
	 * 
	 * @param target
	 * @return
	 */
	public static int jumpFloorII(int target) {
		if (target == 1) {
			return 1;
		} else if (target == 2) {
			return 2;
		} else {
			int a = 1;
			int b = 2;
			int c = 0;
			while (target > 2) {
				c = a + b;
				a = b;
				b = c;
				target--;
			}
			return c;
		}
	}

	/**
	 * 青蛙这次升级了，可以一次跳 n个台阶了； 递归思路：f(n) = f(n-1) + f(n-2) + f(n-3) + ... + f(2) +
	 * f(1); f(n-1) = f(n-2) + f(n-3) + f(n-4) + ... + f(2) + f(1); f(n) = 2 *
	 * f(n-1)
	 * 
	 * @param target
	 * @return
	 */
	public static int jumpFloorN(int target) {
		if (target == 1) {
			return 1;
		}else {
			return 2 * jumpFloorN(target - 1);
		}
	}
	
	/**
	 * 非递归，用循环实现
	 * @param target
	 * @return
	 */
	public static int jumpFloorN2(int target) {
		if (target == 1) {
			return 1;
		}else {
			int sum = 0;
			int a = 1;
			while (target > 1) {
				sum = 2 * a;
				a = sum;
				target--;
			}
			return sum;
		}
	}

}
