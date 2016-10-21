package dang.JavaAdvance.job.array;

public class MoveOdd {
	/**
	 * 空间换时间，先计算总共奇数个数，再启动一个同长度数组，将奇数放到数组前端，偶数放到oddNum处，分别递增
	 * 
	 * @param array
	 */
	public void reOrderArray(int[] array) {
		int len = array.length;
		int[] array2 = new int[len];
		int oddNum = oddNumber(array);
		
		for (int i = 0, k = 0, j = oddNum; i < len; i++) {
			if ((array[i] & 1) == 1) {
				array2[k++] = array[i];
			} else {
				array2[j++] = array[i];
			}
		}

		// array = Arrays.copyOf(array2, len);
		for (int f = 0; f < len; f++) {
			array[f] = array2[f];
		}
	}
	/**
	 * 利用插入排序的思想
	 * @param array
	 */
	public void reOrderArray2(int[] array) {
		int len = array.length;
		for (int i = 1; i < len; i++) {
			if ((array[i] & 1) == 1) {	// 只有当array[i]为奇数的时候才往前移
				int j = i - 1;
				int temp = array[i];
				while (j >= 0 && ((array[j] & 1) != 1)) {
					array[j + 1] = array[j];
					j--;
				}
				array[j + 1] = temp;
			}

		}
	}

	/**
	 * @param array
	 */
	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > temp) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = temp;
		}
	}

	public static void insertSort_(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int j = i - 1;
			while (j >= 0 && array[j] > temp) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = temp;
		}
	}

	public static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static boolean isOdd(int key) {
		return (key & 1) == 1 ? true : false;
	}

	public int oddNumber(int[] array) {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if ((array[i] & 1) == 1) {
				System.out.println("odd " + array[i]);
				count++;
			}
		}
		System.out.println("odd count " + count);
		return count;
	}

	public static void main(String[] args) {

		MoveOdd test = new MoveOdd();
		int[] d = { 0, 0, 1, 1, 2, 3, 4, 5, 6 };
		test.reOrderArray2(d);
		for (int i : d) {
			System.out.println(i);
		}
		/**
		 * int[] a = { 5, 5, 1, 8, 0, 10, 3 }; insertSort(a); for (int i : a) {
		 * System.out.println(i); }
		 **/
	}
}
