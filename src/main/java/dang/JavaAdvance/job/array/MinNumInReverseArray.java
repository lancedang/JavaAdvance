package dang.JavaAdvance.job.array;

import org.hibernate.stat.Statistics;

/**
 * 二分差找的变形，通过此题可以得出结论，有序数组可以用二分查找，同样的旋转后的数组同样可以用二分查找
 * 
 * @author Dangdang
 *
 */
public class MinNumInReverseArray {
	/**
	 * 输入为一个非递减数组的旋转数组 {345 12} 为 {12 345}的一个旋转数组
	 * 
	 * @param array
	 * @return
	 */
	public static int minNumInReverseArray(int[] array) {
		int size = array.length;
		if (size == 0) {
			return 0;
		}

		int head = 0;
		int tail = size - 1;
		int mid;

		while (head != tail - 1) {
			mid = (head + tail) / 2;
			if (array[mid] < array[tail]) { // 这里的mid和tail比较同样可以换成和head比较一样的
				tail = mid;
			} else if (array[mid] > array[tail]) {
				head = mid;
			}

			else { // 若不考虑这种情况，一旦碰到array[mid] = array[head]的情况就会是死循环，此刻只能顺序查找
				return find(array);
			}

		}
		return array[tail];
	}

	/**
	 * 顺序查找元素
	 * 
	 * @param array
	 * @return
	 */
	public static int find(int[] array) {
		int i = 1;
		int len = array.length;
		int min = array[0];

		while (i < len) {
			if (min > array[i]) {
				min = array[i];
			}
			i++;
		}
		return min;

	}

	public static int Fibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return Fibonacci(n - 1) + Fibonacci(n - 2);
		}
	}

	public static int Fibonacci2(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
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

	public static void sortInteger(int[] arr) {

	}

	public static void quickSort(int[] arr, int start, int end, int pivot) {
		if (start >= end) {
			return;
		}
		int mid = getIndex(arr, start, end);

		quickSort(arr, start, mid - 1, arr[start]);
		quickSort(arr, mid + 1, end, arr[mid + 1]);

	}

	public static int getIndex(int[] arr, int start, int end) {
		int pivot = arr[start];
		int head = start;
		int tail = end;

		while (true) {
			while (arr[head] < pivot && head < tail)
				head++;
			while (arr[tail] > pivot && head < tail) {
				tail--;
			}
			if (head < tail)
				swap(arr, head, tail);
			else {
				break;
			}
		}
		swap(arr, start, head - 1);
		return head - 1;
	}

	public static void swap(int[] arr, int a, int b) {
		int tep = 0;
		tep = arr[a];
		arr[a] = arr[b];
		arr[b] = tep;
	}

	public static void main(String[] args) {
		/*
		 * int[] array = { 0, 5, 6, 0, 0, 0, 0 }; int[] array2 = { 1, 0, 1, 1, 1
		 * }; int minNum = minNumInReverseArray(array);
		 * System.out.println(minNum);
		 */
		int[] arr = { 1, 3, -4, -5, 7, -8 };
		//System.out.println(getIndex(arr, 0, 5));
		quickSort(arr, 0, arr.length - 1, arr[0]);
		for (int item : arr)
			System.out.print(item + " ");
		
	}

}
