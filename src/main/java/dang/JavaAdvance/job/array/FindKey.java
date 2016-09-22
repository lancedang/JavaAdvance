package dang.JavaAdvance.job.array;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 考察的数据结构：二维数组， 考察的算法：查找算法（二分查找） 。 记住，只要是查找（不论是顺序查找，二分查找还是分块查找 ），我们所有查找的 思想 就是
 * 能通过 每次的比较 最大可能地排除 大量 不相干元素，也就是 急速的缩小 剩余查找范围 如 二分查找 一次比较就排除了一半的元素，顺序查找一次只 排除
 * 一个元素
 * 
 * @author Dangdang
 *
 */
public class FindKey {

	/**
	 * 对每行调用二分查找方法，a[m][n], 时间复杂度O( m*log(n))
	 * 
	 * @param array
	 * @param key
	 * @return
	 */
	public static boolean isExists(int[][] array, int key) {
		int len = array.length;
		int wid = array[0].length;

		for (int i = 0; i < len; i++) {
			boolean flag = binaryFind(array[i], 0, wid - 1, key);
			if (flag) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 二分查找判断数组中是否存在元素
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @param key
	 * @return
	 */
	public static boolean binaryFind(int[] array, int left, int right, int key) {
		int mid = (left + right) / 2;
		while (left <= right) {
			if (array[mid] == key) {
				return true;
			} else if (array[mid] > key) {
				right = mid - 1;
				return binaryFind(array, left, right, key);
			} else {
				left = mid + 1;
				return binaryFind(array, left, right, key);
			}
		}
		return false;
	}

	/**
	 * 遵循一次比较排除大量元素(急剧缩小范围)的的原则，从 a[m][n] 处开始比较 一次比较就能排除一行或一列，时间复杂度O(m + n)
	 * 
	 * @param array
	 * @param key
	 * @return
	 */
	public static boolean isExists2(int[][] array, int key) {
		int len = array.length - 1;
		int wid = array[0].length - 1;

		int i = 0;
		int j = wid;

		while (j >= 0 && i <= len) {
			int temp = array[i][j];
			if (temp == key) {
				return true;
			} else if (temp < key) {
				i++;
			} else {
				j--;
			}
		}
		return false;

	}

	public static int findRepeatKey(int[] array) {
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] array = { { 1, 3, 4, 5 }, { 2, 4, 6, 7 }, { 8, 10, 13, 15 } };
		boolean exists = isExists(array, 18);
		boolean exists2 = isExists2(array, 0);
		System.out.println(exists2);
	}

}
