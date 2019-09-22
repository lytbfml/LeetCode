package design;

import java.util.Stack;

/**
 * @author Yangxiao on 3/24/2019.
 */
public class MinStack2 {
	
	public static void main(String[] args) {
		MinStack2 ms = new MinStack2();
		ms.push(10);
		ms.push(5);
		System.out.println(ms.top());
	}
	
	Stack<Integer> stack;
	private int min;
	
	public MinStack2() {
		this.stack = new Stack<>();
		this.min = Integer.MAX_VALUE;
	}
	
	public void push(int x) {
		if (stack.isEmpty()) {
			stack.push(0);
			min = x;
		} else {
			stack.push(x - min);//Could be negative if min value needs to change
			if (x < min) { min = x; }
		}
	}
	
	public void pop() {
		if (stack.isEmpty()) { return; }
		
		int pop = stack.pop();
		
		if (pop < 0) {
			min = min - pop;//If negative, increase the min value
		}
	}
	
	public int top() {
		int res = stack.peek();
		if (res > 0) {
			return res + min;
		} else {
			return min;
		}
	}
	
	public int getMin() {
		return min;
	}
}
