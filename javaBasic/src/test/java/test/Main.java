package test;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		System.out.println(commonStrLen("abcd", "dacb"));
	}

	public static int commonStrLen(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		String min = null;
		String max = null;

		int num = 0;
		int[][] dis = new int[len1 + 1][len2 + 1];
		for (int i = 0; i < len1; i++) {
			char c1 = str1.charAt(i);
			for (int j = 1; j <= len2; j++) {
				char c2 = str2.charAt(j - 1);
				if (c1 == c2) {
					dis[j][j] = dis[i][j - 1] + 1;
				} else {
					dis[i][j] = 0;
				}
				num = Math.max(num, dis[i][j]);
			}
		}
		return num;

	}

	public static void main3(String[] ags) {

		Scanner sc = new Scanner(System.in);
		int item = sc.nextInt();
		for (int i = 0; i < item; i++) {
			String a = sc.next();
			int b = sc.nextInt();
			System.out.println(reverse(a, b));
		}

		System.out.println(reverse("computer", 3));
	}

	public static String reverse(String str, int n) {
		int len = str.length();
		StringBuffer sb = new StringBuffer("");
		String sub = str.substring(len - n);
		sb.append(sub);
		sb.append(str.substring(0, len - n));
		return sb.toString();
	}

	public static void main2(String[] args) {

		// System.out.println(nomatch(3));
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			System.out.println(getcases(m, n));
		}
	}

	public static long getcases(int x, int y) {

		return cmn(x, y) * nomatch(y);
	}

	/**
	 * n对锁 都不配对的可能方式
	 * 
	 * @param n
	 * @return
	 */
	public static long nomatch(int n) {
		if (n == 2)
			return 1;
		else if (n == 3) {
			return 2;
		} else {
			long pai = 1;
			for (int i = 2; i <= n; i++) {
				pai *= i;
			}
			long sum = 0;
			for (int i = 1; i <= n - 2; i++) {
				sum += cmn(n, i) * nomatch(n - i);
			}
			return pai - sum - 1;
		}
	}

	/**
	 * m种选n个
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static long cmn(int m, int n) {

		int tep;
		if (n >= m / 2) {
			tep = m - n;
		} else {
			tep = n;
		}

		int tep2 = tep;
		// fen mu
		long fenmu = 1;
		for (int i = tep; i > 0; i--) {
			fenmu *= i;
		}

		// fen zi
		long fenzi = 1;
		for (int j = m; j >= m - tep + 1; j--) {
			fenzi *= j;
		}

		return fenzi / fenmu;

	}


	public void test() {

	}
}
