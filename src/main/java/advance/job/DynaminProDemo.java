package advance.job;

/**
 * 动态规划,
 * 
 * @author Dangdang
 *
 */
public class DynaminProDemo {

	/**
	 * 求两个字符串的最长公共字符串
	 * 
	 * @param st1
	 * @param st2
	 * @return
	 */
	public static String getLongestCommonStr(String st1, String st2) {
		// 用于动态保存公共字符串长度最大值
		int max = 0;
		int index = 0;// 记录最长字符串结尾下标
		int len1 = st1.length();
		int len2 = st2.length();

		// 辅助数组
		int[][] auxiArr = new int[len2][len1];

		char[] arr1 = st1.toCharArray();
		char[] arr2 = st2.toCharArray();

		// 初始化第一行第一列,初始化原则是对应char相同则赋1，否则赋0
		for (int j = 0; j < len2; j++) {
			auxiArr[j][0] = (arr2[j] == arr1[0] ? 1 : 0);
		}

		for (int j = 0; j < len1; j++) {
			auxiArr[0][j] = (arr2[0] == arr1[j] ? 1 : 0);
		}

		// 对矩阵其余元素初始化
		for (int i = 1; i < len1; i++) {
			for (int j = 1; j < len2; j++) {
				// 赋值时若相同则用左上角矩阵元算值+1,这样可以统计一共有多少个连续相同的char, 否则赋0
				auxiArr[j][i] = (arr1[i] == arr2[j] ? auxiArr[j - 1][i - 1] + 1 : 0);
				int max2 = Math.max(max, auxiArr[j][i]);
				index = (max2 > max ? i : index); // 动态更新最长字符串结尾下标
				max = max2; // 动态更新公共字符串最大长度
			}
		}

		return st1.substring(index - max + 1, index + 1);

		// return max;

	}

	/**
	 * 求int数组中arr[i] - arr[j] 的最大值, 其中i>=j
	 * 
	 * @param arr
	 *            任意随机数组
	 * @return
	 */
	public static int getMaxDistance(int[] arr) {
		int min = arr[0];
		int max = 0;
		int len = arr.length;
		// 思想：当前元素与 前面最小元素 差 集合中的最大值
		for (int i = 0; i < len; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i] - min);
		}

		return max;
	}
	
	/**
	 * 动态规划节解背包问题
	 * @param maxSize
	 * @param weights
	 * @param values
	 * @return
	 */
	public static int[][] getMaxValue(int maxSize, int[] weights, int[] values) {
		int len = weights.length;
		int[][] auxiArr = new int[len][maxSize + 1];

		for (int i = 0; i < len; i++) {
			auxiArr[i][0] = 0;
		}

		for (int i = 1; i < maxSize + 1; i++) {

		}
		return auxiArr;
	}

	public static void main(String[] args) {
		int[] weights = { 2, 2, 6, 5, 4 };
		int[] values = { 6, 3, 5, 4, 6 };
		int maxSize = 10;

		int[][] auxiArr = getMaxValue(maxSize, weights, values);

		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < maxSize + 1; j++) {
				System.out.print(auxiArr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
