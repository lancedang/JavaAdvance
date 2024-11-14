package advance.job.link;

public class MergeList {

	/**
	 * 合并两个有序链表,最后为升序,递归
	 * 
	 * @param root1
	 *            有序链表1
	 * @param root2
	 *            有序链表2
	 * @return
	 */
	public static LinkNode merge2ListRec(LinkNode root1, LinkNode root2) {
		if (root1 == null) {
			return root2;
		}
		if (root2 == null) {
			return root1;
		}

		if (root1.value < root2.value) {
			root1.next = merge2ListRec(root1.next, root2);
			return root1;

		} else {
			root2.next = merge2ListRec(root1, root2.next);
			return root2;
		}

	}

	/**
	 * 合并两个链表使升序，
	 * 
	 * @param root1
	 *            有序链表1升序
	 * @param root2
	 *            有序链表2升序
	 * @return
	 */
	public static LinkNode merge2ListLoop(LinkNode root1, LinkNode root2) {
		if (root1 == null) {
			return root2;
		}
		if (root2 == null) {
			return root1;
		}
		LinkNode root = null;// 用于记录头结点
		LinkNode cur = null; // 用于中间节点建立前后节点连接

		while (root1 != null && root2 != null) {

			if (root1.value < root2.value) {
				if (root == null) { // 初始化根节点
					root = cur = root1;
				} else { //
					cur.next = root1;// 建立连接
					cur = cur.next;// "遍历"节点下滑
				}
				root1 = root1.next;
			} else {
				if (root == null) {
					root = cur = root2;
				} else {
					cur.next = root2;
					cur = cur.next;
				}
				cur = root2;
				root2 = root2.next;
			}
		}

		if (root1 == null) {
			cur.next = root2; //
		} else { 
			cur.next = root1; //
		}

		return root;

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
