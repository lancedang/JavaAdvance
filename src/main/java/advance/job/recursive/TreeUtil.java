package advance.job.recursive;

/**
 * 二叉树，包含各种操作，插入、删除、查找
 * 
 * @author Dangdang
 *
 */
public class TreeUtil {
	TreeNode rootNode;

	public TreeUtil() {
		rootNode = null;
	}

	public static int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.leftNode == null && root.rightNode == null) {
			return 1;
		}
		int leftH = getHeight(root.leftNode);
		int rightH = getHeight(root.rightNode);

		return 1 + Math.max(leftH, rightH);
	}

	/**
	 * 向二叉树插入元素，相当于构建二叉搜索树
	 * 
	 * @param newNode
	 */
	public void insert(TreeNode newNode) {
		if (rootNode == null) {
			rootNode = newNode;
			return;
		}

		TreeNode father = rootNode;
		TreeNode next = rootNode;

		while (true) {
			if (newNode.val <= next.val) {
				next = next.leftNode;
				if (next == null) {
					father.leftNode = newNode;
					return;
				}
				// father = next;

			} else {
				next = father.rightNode;
				if (next == null) {
					father.rightNode = newNode;
					return;
				}
				// father = next;
			}
			father = next;
		}

	}

	/**
	 * 遍历二叉搜索树，中序遍历正好等于升序
	 * 
	 * @param rootNode
	 */
	public void visit(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}

		visit(rootNode.leftNode);
		rootNode.show();
		visit(rootNode.rightNode);

	}

	/**
	 * 找到树种最大值
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode getMax(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode cur = root;
		TreeNode pre = root;
		while (cur != null) {
			pre = cur;
			cur = cur.rightNode;
		}
		return pre;
	}

	/**
	 * 找到最小值
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode getMin(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode cur = root;
		TreeNode pre = null;

		while (cur != null) {
			pre = cur;
			cur = cur.leftNode;
		}

		return pre;
	}

	/**
	 * 判断是否存在某个节点
	 * 
	 * @param key
	 * @return
	 */
	public boolean find(TreeNode key) {
		TreeNode cur = rootNode;
		if (cur == null)
			return false;
		while (cur != null) {
			if (cur.val == key.val) {
				return true;
			} else if (cur.val > key.val) {
				cur = cur.leftNode;
			} else {
				cur = cur.rightNode;
			}
		}
		return false;
	}

	public void delete(TreeNode root, TreeNode key) {
		// 1. 找到该节点
		// 2. 该节点为叶子节点; 只有一个子节点; 有左右两个子节点

		TreeNode cur = root;
		TreeNode pre = null;
		
		while (cur != null) {
			//pre = cur;
			if (key.val == cur.val) {
				key = cur; //找到key所在位置, 此时的key已非单一节点, 而是 以其为根的子树
				break;
			} else if (key.val < cur.val) {
				pre = cur;
				cur = cur.leftNode;
			} else {
				pre = cur;
				cur = cur.rightNode;
			}
		}
		
		//对以key为根的树进行操作，判断此节点子节点如何

		
		if(cur.leftNode == null && cur.rightNode == null) {
			
		}else if(cur.leftNode != null && cur.rightNode != null){
			
		}else {
			
		}
		
		
	}

	public static void main(String[] args) {

		TreeNode n1 = new TreeNode(60);
		TreeNode n2 = new TreeNode(111);
		TreeNode n3 = new TreeNode(82);
		TreeNode n4 = new TreeNode(45);
		TreeNode n5 = new TreeNode(9);

		TreeNode n6 = new TreeNode(91);

		TreeUtil myTree = new TreeUtil();

		myTree.insert(n1);
		myTree.insert(n2);
		myTree.insert(n3);
		myTree.insert(n4);
		myTree.insert(n5);

		myTree.visit(n1);
		System.out.println(myTree.getMax(n1).val);
		System.out.println(myTree.getMin(n1).val);
		System.out.println(TreeUtil.getHeight(n1));

	}

}
