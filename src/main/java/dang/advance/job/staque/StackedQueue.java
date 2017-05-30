package dang.advance.job.staque;

import java.util.Stack;

/**
 * push存入stack1,pop时先将数据pop到stack2, 然后取出栈顶
 * @author Dangdang
 *
 */
public class StackedQueue {
	
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	
	int count = 0;
	
	/**
	 * 每次保证直接往stack1中push就ok了 
	 * @param key
	 */
	public void queuePush(int key) {
		stack1.push(key);
		count++;
	}

	/**
	 * pop麻烦一点，每次pop的时候都是悄悄地 把stack1中的数据 倒腾到 stack2中， stack1留下最好一个元素 pop出来, 等
	 * 完事了，再把倒腾到 stack2的数据 倒腾回去(每次必倒腾 ，挺勤奋的，保证stack1原样如初，不是按需倒腾)
	 * @return
	 */
	public int queuePop() {
		int i = 0;
		while (i < count - 1) {	 //stack2作为中间过滤，只保存count - 1 个，留一个(最初存入的)在stack1中pop出来
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
	}
	
	public static void main(String[] args) {
		StackedQueue sQueue = new StackedQueue();
		
		sQueue.queuePush(1);
		sQueue.queuePush(2);
		sQueue.queuePush(3);
		
		System.out.println(sQueue.queuePop());
		System.out.println(sQueue.queuePop());
		System.out.println(sQueue.queuePop());
		
	}
	
}