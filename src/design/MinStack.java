package design;

/**
 * @author Yangxiao on 3/24/2019.
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class MinStack {
	
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());
	}
	
	/**
	 * initialize your data structure here.
	 */
	private Node top;
	
	public MinStack() {
		top = null;
	}
	
	public void push(int x) {
		if (top == null) {
			top = new Node(x, x, null);
		} else {
			top = new Node(x, Math.min(x, top.min), top);
		}
	}
	
	public void pop() {
		top = top.prev;
	}
	
	public int top() {
		return top.val;
	}
	
	public int getMin() {
		return top.min;
	}
	
	private class Node {
		int val;
		int min;
		Node prev;
		
		private Node(int val, int min, Node prev) {
			this.val = val;
			this.min = min;
			this.prev = prev;
		}
	}
}