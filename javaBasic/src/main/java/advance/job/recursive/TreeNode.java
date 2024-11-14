package advance.job.recursive;

/**
 * 二叉树节点，封装数据节点，左右节点，多叉树可以用List封装子节点
 * 
 * @author Dangdang
 *
 */
public class TreeNode {

	int val;
	TreeNode leftNode;
	TreeNode rightNode;

	public TreeNode(int value) {
		this.val = value;
		leftNode = null;
		rightNode = null;
	}
	
	public void show() {
		System.out.print("val=" + val + " ");
	}
}
