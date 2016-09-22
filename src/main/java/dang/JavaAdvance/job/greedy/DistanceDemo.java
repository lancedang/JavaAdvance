package dang.JavaAdvance.job.greedy;

public class DistanceDemo {
	
	/**
	 * 有一个长为n的数组A，求满足0≤a≤b<n的A[b]-A[a]的最大值。给定数组A及它的大小n，请返回最大差值。
	 * 
	 * @param array
	 *            任意数组
	 * @return 距离最大值
	 */
	public static int getLongestDistance(int[] array) {
		int len = array.length;
		int min = array[0]; // 遍历数组，保存最小值元素
		int maxDistance = 0; // 保存之前元素差最大值
		for (int i = 1; i < len; i++) {
			min = Math.min(min, array[i]);
			maxDistance = Math.max(maxDistance, array[i] - min);
		}
		return maxDistance;
	}

	public static String subStr(String s1, String s2) {
		String s = null;
		int flag = 0;
		if (s1.length() > s2.length()) { // 找到更短的字符串！
			s = s1;
			s1 = s2;
			s2 = s;
		}
		for (int i = 0; i < s1.length(); ++i) { // 如果更短的字符串几个，遍历就有几层，
			for (int j = 0; j < i + 1; ++j) { // 第j层有i+1种可能,从长往短找
				s = s1.substring(j, s1.length() - i + j);
				if (s2.contains(s)) { // 如果更长的字符串包含当前这个子串，退出循环
					flag = 1; // 两重循环，设置一个记号，方便一次性退出
					break;
				}
			}
			if (flag == 1)
				break;
		}
		return s;
	}

	public static int getShortestLength(int[] array) {
		int len = array.length;
		int minDistance = 0;
		int max = array[0];
		for (int i = 1; i < len; i++) {
			max = Math.max(max, array[i]);
			minDistance = Math.min(minDistance, array[i] - max);
		}
		return minDistance;
	}

	public static int getMin(int[] array) {
		int len = array.length;
		int min = array[0];
		for (int item : array) {
			/*
			 * if(min > item) { min = item; }
			 */
			min = Math.min(min, item);
			min = (min > item) ? item : min;

		}
		return min;
	}

	public static void main(String[] args) {
		int[] array = { 3, 12, 11, 1, 0, 21 };
		System.out.println(getLongestDistance(array));
		System.out.println(getShortestLength(array));
	}
}
