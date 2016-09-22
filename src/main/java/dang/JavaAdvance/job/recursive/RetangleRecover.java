package dang.JavaAdvance.job.recursive;

/**
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * 
 * @author Dangdang
 *
 */
public class RetangleRecover {
	/**
	 * 当target为1时，只有一种方法；当target为2时，有两种；当target>2时，第一步可分为两种可能：1,
	 * 横放，剩余可能f(n-2)因为横放下面的也就随之确定了2，竖放，剩余的就是f(n-1)了
	 * 
	 * @param target
	 * @return
	 */
	public static int retanRecover(int target) {
		if (target == 1) {
			return 1;
		} else if (target == 2) {
			return 2;
		} else {
			int sum = 0;
			int a = 1;
			int b = 2;
			while (target > 2) {
				sum = a + b;
				a = b;
				b = sum;
				target--;
			}
			return sum;
		}
	}
}
