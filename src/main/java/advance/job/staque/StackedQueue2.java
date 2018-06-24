package advance.job.staque;

import java.util.Stack;

/**
 * push存入stack1,pop时先将数据pop到stack2, 然后取出栈顶
 * 
 * @author Dangdang
 *
 */
public class StackedQueue2 {

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	int count = 0;

	/**
	 * 从stack1 push数据时, 先看stack1是否为空，若为空则 先将 stack2中的数据 倒腾过来；若不为空，直接push进去
	 * 
	 * @param key
	 */
	public void queuePush(int key) {
		if (stack1.isEmpty()) { // 从stack2中pop(倒腾)过来
			int i = count;
			while (i > 0) {
				int item = stack2.pop();
				stack1.push(item);
				i--;
			}
		}
		stack1.push(key);
		count++;

	}

	/**
	 * 队列取数据时，stack2先检查是否为空，若不为空说明 提前从stack1倒腾好了，直接pop出来；若为空，则首先从stack1倒腾过来
	 * 这是按需倒腾，对那种连续 push 和 pop的操作有利
	 * @return
	 */
	public int queuePop() {
		if (stack2.isEmpty()) { // stack2为空, 得从stack1 倒腾出来
			int i = 0;
			while (i < count - 1) { // stack2作为中间过滤，只保存count - 1
									// 个，留一个(最初存入的)在stack1中pop出来
				int item = stack1.pop();
				stack2.push(item);
				i++;
			}

			int result = stack1.pop();

			while (i > 0) {
				int item = stack2.pop();
				stack1.push(item);
				i--;
			}

			count--;
			return result;
		} else {
			count--;
			return stack2.pop();
		}

	}

	public static void main(String[] args) {
		StackedQueue2 sQueue = new StackedQueue2();

		sQueue.queuePush(3);
		

		System.out.println(sQueue.queuePop());


	}

}