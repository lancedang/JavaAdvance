package com.dangdang.JavaAdvance;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	private static boolean flag = true;
	private static int num = 10;
	public static void main2(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str;
		StringBuffer result; // 用于输出最后返回行可以append很方便
		String str2;
		// scanner 会一直读下去的,除非1)程序写判定语句如 str.equals("end") return; 2) Ctrl+Z手动退出
		while (scanner.hasNextLine()) {
			result = new StringBuffer("");
			str = scanner.nextLine();
			System.out.println("str " + str);

			int start = Integer.parseInt(str.substring(0, 3)); // substring
																// 返回new
																// String不影响原引用
			int end = Integer.parseInt(str.substring(4));

			for (int i = start; i <= end; i++) {
				if (isDaffodil(i)) {
					result.append(i + " ");
				}
			}

			if (result.length() > 1)
				System.out.println(result.toString().trim());
			else
				System.out.println("no");
			;

		}

	}

	public static boolean isDaffodil2(int key) {
		int hundreds = key / 100;
		int ones = key % 10;
		int tens = key / 10 - hundreds * 10;

		return key == Math.pow(hundreds, 3) + Math.pow(tens, 3) + Math.pow(ones, 3) ? true : false;

	}

	public static boolean isDaffodil(int key) {
		String s = String.valueOf(key);
		// System.out.println("number " + s);
		int i = Integer.parseInt(s.charAt(0) + "");
		int j = Integer.parseInt(s.charAt(1) + "");
		int k = Integer.parseInt(s.charAt(2) + "");
		// System.out.println("i " + i + "; j " + j + " k " + k);
		return (key == (Math.pow(j, 3) + Math.pow(i, 3) + Math.pow(k, 3))) ? true : false;

	}

	public static double getSqrtNext(double item) {
		double args = Math.sqrt(item);
		DecimalFormat format = new DecimalFormat("#.00");
		// format.format(args);
		// String.format("%.2f", args);
		return args;
	}

	public static void main3(String[] args) {
		/*
		 * char a = '1'; int ia = a; System.out.println(ia);
		 */
		/*
		 * Scanner sc = new Scanner(System.in); String str; while
		 * (sc.hasNextLine()) { str = sc.nextLine(); if (str.equals("end")) {
		 * return; } System.out.println(str); }
		 */
		Comparator<Main> comparator;
		Object object;
		Scanner scanner = new Scanner(System.in);
		String str;
		while (scanner.hasNextLine()) {
			str = scanner.nextLine();
			int first = Integer.parseInt(str.split(" ")[0]);
			int number = Integer.parseInt(str.split(" ")[1]);
			double sum = first;
			double tep = first;
			while (number > 1) {
				tep = getSqrtNext(tep);
				sum = sum + tep;
				number--;
			}
			System.out.printf("%.2f", sum);
			System.out.println();
			;
			// return ;
			/*
			 * int m; double sum,n; Scanner sc = new Scanner(System.in);
			 * while(sc.hasNext()){ n=sc.nextInt(); m=sc.nextInt(); sum=0;
			 * for(int i=0;i<m;i++){ sum=sum+n; n=Math.sqrt(n); }
			 * System.out.printf("%.2f",sum); System.out.println(); }
			 */
		}

	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int len = 100;
        int key = 0;

        String str;
        int[] array;

        array = new int[len];
        for (int i = 0; i < len; i++) {
              array[i] = scanner.nextInt();
              num ++;
              //System.out.println(array[i]);

        }
		
       int result = getLongestDistance(array);
       System.out.println(result);
	}

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
	
	public static int loopBinaryFind(int[] array, int begin, int end, int key) {
		int mid;
		while (begin <= end) {
			mid = (begin + end) / 2;

			if (array[mid] == key) {
				return mid;
			} else if (array[mid] < key) {
				begin = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		flag = false;
		return begin;
	}
}
