package dang.JavaAdvance.job.link;

/**
 * 利用指针反转输出链表，是破坏性的最初的链表结构已毁坏
 * 
 * @author Dangdang
 *
 */
public class ReverseLinkList {

	/**
	 * 写程序是讲究逻辑思维的，什么是逻辑思维，就是整个过程细分下来怎么执行的，初始值，判断条件，执行顺序，但这些不是一开始就能彻底确定好无误的，
	 * 后期在写的过程中不断去调整初始值判断条件啥的
	 * 
	 * @param root
	 * @return
	 */
	public static LinkNode reverseList(LinkNode root) {
		LinkNode current;
		LinkNode pre = null;
		LinkNode head = root;

		// 空链表
		if (root == null) {
			return null;
		}

		// 只有一个元素的链表
		if (head.getNext() == null) {
			return root;
		}
		// 只有两个元素的链表
		if (head.getNext().getNext() == null) {
			current = head.getNext();
			current.setNext(head);
			return current;
		}
		// 大于等于3的链表
		while (head != null) {
			current = head.getNext();

			head.setNext(pre);

			pre = head;
			head = current;
		}

		return pre;
	}

	/**
	 * 由这个过程可以看出来，整个过程在运行时内存里完成的，最后输出
	 * 只保留一个节点，但它指向其后继，而且后继在哪我们外界看不到，只能确定后继在内存的某个位置，同样的，该后继也指向其自己的后继，层层循环
	 * 
	 * @param root
	 * @return
	 */
	public static LinkNode reverseList2(LinkNode root) {
		LinkNode pre = null;
		LinkNode next = null;
		while (root != null) {
			next = root.getNext(); // 保留后继节点
			root.setNext(pre); // 收拾前面节点，前面部分节点向后转，反向
			pre = root; // 上位
			root = next;// 上位
		}
		return pre;
	}

	public static void main(String[] args) {
		LinkNode n1 = new LinkNode(1);
		LinkNode n2 = new LinkNode(2);
		LinkNode n3 = new LinkNode(3);

		LinkNode n4 = new LinkNode(4);
		LinkNode n5 = new LinkNode(5);
		n5.setNext(n4);
		n4.setNext(n3);
		n3.setNext(n2);
		n2.setNext(n1);
		LinkNode result = reverseList(n5);
		while (result != null) {
			System.out.println(result);
			result = result.getNext();
		}
	}
}
