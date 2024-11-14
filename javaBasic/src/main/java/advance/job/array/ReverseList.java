package advance.job.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 考察的数据结构：ArrayList，get(i), add(int i);
 * 
 * @author Dangdang
 *
 */
public class ReverseList {
	/**
	 * 逆序输出链表，注意链头设定是 指针还是实体元素
	 * 
	 * @param listNode
	 * @return
	 */
	public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		int num = 0;
		while (listNode != null) { // 区别front.next != null
			list.add(listNode.val);
			listNode = listNode.next;
			num++;
		}

		while (num > 0) {
			list2.add(list.get(num - 1)); // get 的永远是下标，num 是总个数，比下标大一
			num--;
		}
		return list2;
	}

	ArrayList<Integer> list = new ArrayList<Integer>();

	/**
	 * 递归实现，这个需要一个成员变量存储最终结果
	 * 
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
		if (listNode != null) {
			printListFromTailToHead2(listNode.next);
			list.add(listNode.val);
		}
		return list;
	}

	/**
	 * 运用栈实现
	 * 
	 * @param listNode
	 * @return
	 */
	public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		while (listNode != null) {
			stack.push(listNode.val);
			listNode = listNode.next;
		}

		while (!stack.isEmpty()) {
			list2.add(stack.pop());
		}
		return list2;
	}

	public static List<ListNode> myReverseList(ListNode head) {

		Stack<ListNode> stack = new Stack<ListNode>();
		ArrayList<ListNode> list2 = new ArrayList<ListNode>();
		ListNode root;
		while (head != null) {
			stack.push(head);
			head = head.next;
		}
		while (!stack.isEmpty()) {
			list2.add(stack.pop());
		}
		
		for (ListNode item : list2) {
			System.out.println("list2 " + item.val);
		}
		
		//return deal(list2);
		return list2;
	}

	public static ListNode deal(ArrayList<ListNode> list) {
		
		for (ListNode item : list) {
			System.out.println(item.val);
		}
		
		for (int i = 0; i < list.size() - 1; i++) {
			list.get(i).next = list.get(i + 1);
		}
		
		return list.get(0);
	}

	public static void main(String[] args) {

		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		//ReverseList demo = new ReverseList();

		myReverseList(node1);
		
		/*
		 * ArrayList<Integer> listTailToHead =
		 * demo.printListFromTailToHead3(node1); for (int i : listTailToHead) {
		 * System.out.println(i); }
		 */
	}
}
