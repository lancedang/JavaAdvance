package advance.job.recursive;

/**
 * 操作任何一个数都是 从 操作根root入手的,根有leftRoot, rightRoot,继而操作所有元素, 提纲挈领的感觉 1. 要想到递归; 2.
 * 确定递归的参数变化
 * 
 * @author Dangdang
 *
 */
public class RestoreBinTree {
	public static TreeNode restoreTree(int[] pre, int[] in) {
		return getRoot(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	/**
	 * 递归函数，这个函数的建立很重要，遍历的参数再不断缩小或接近if判断标准，所以参数中必须有不断变化减小的量
	 * 
	 * @param pre
	 *            前序数组，在所有递归中 不变
	 * @param p_head
	 *            前序数组的 head，表示 划分后的 前序子序列起点
	 * @param p_tail
	 *            前序数组的tail，表示 划分后的 前序子序列终点
	 * @param in
	 * @param in_head
	 *            中序，起点
	 * @param in_tail
	 *            中序，终点
	 * @return
	 */
	public static TreeNode getRoot(int[] pre, int p_head, int p_tail, int[] in, int in_head, int in_tail) {

		if (p_head > p_tail) {
			return null;
		}

		int root = pre[p_head]; // 前序首值即为根
		TreeNode head = new TreeNode(root);

		int index_root = find(in, root); // 找到树根在 中序遍历中的位置，从而可以将 二叉树划分为左右两个子树
		// 最主要的是确定 递归左右子树时 起始和结束的 位置
		// 划分两半后，左右子树已清楚，分别对左右子树 进行 递归，求根，并将父亲节点指向 左右树根
		// 左子树的起点必为前序树根右侧第一个，左子树终点 可用中序 根位置 左侧(子树)节点个数 在前序中计算出来，
		// 右子树的起点为前序中除了左子树之外的（即左子树终点 + 1），右子树 前序终点 
		
		TreeNode leftRoot = getRoot(pre, p_head + 1, p_head + index_root - in_head, in, in_head, index_root - 1);
		TreeNode rightRoot = getRoot(pre, p_head + index_root - in_head + 1, p_tail, in, index_root + 1, in_tail);

		//这就是提纲挈领之笔，将所有的节点（根	） 连接起来
		head.leftNode = leftRoot;
		head.rightNode = rightRoot;

		return head;

	}

	/**
	 * 从中序序列中获得根的位置
	 * 
	 * @param a
	 * @param key
	 * @return
	 */
	public static int find(int[] a, int key) {
		int i = 0;
		while (i < a.length) {
			if (key == a[i]) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/**
	 * 递归后序遍历
	 * 
	 * @param root
	 *            树根
	 */
	public static void postTraverse(TreeNode root) {
		if (root != null) { // 递归消除的是 while 循环，因为递归函数的参数再不断变化（缩小或接近if判断）
			postTraverse(root.leftNode);
			postTraverse(root.rightNode);
			System.out.println(root.val + " ");
		}
	}

	public static void main(String[] args) {
		int[] pre = { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] in = { 4, 7, 2, 1, 5, 3, 8, 6 };

		TreeNode root = restoreTree(pre, in);

		postTraverse(root);

	}

}
