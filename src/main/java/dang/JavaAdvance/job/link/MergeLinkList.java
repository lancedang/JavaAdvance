package dang.JavaAdvance.job.link;

public class MergeLinkList {
	public static LinkNode mergeList(LinkNode root1, LinkNode root2) {
		LinkNode root3 = null;
		while (root1 != null && root2 != null) {

			if (root1.value < root2.value) {
				root3 = root1;
			}
		}
		return root3;
	}

	public static int[] merge2Array(int[] array1, int[] array2) {
		int len1 = array1.length;
		int len2 = array2.length;
		int[] result = new int[len1 + len2];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < len1 && j < len2) {
			if (array1[i] < array2[j]) {
				result[k++] = array1[i++];
			} else {
				result[k++] = array1[j++];
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println();
	}
}
