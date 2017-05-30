package dang.advance.job.array;

public class DaoShu {

	/**
	 * 获取倒数第k个节点
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode findKthToTail(ListNode head, int k) {
		ListNode current = head;
		int i = 1;
		while ((i < k) && head != null) {
			head = head.next;
			i++;
		}
		if (head == null) {
			return null;
		}
		while (head != null) {
			current = current.next;
			head = head.next;
		}
		return current;
	}

	public static void main(String[] args) {
		
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		
		ListNode node = findKthToTail(node1, 1);
		System.out.println(node.val);
		
	}
}
