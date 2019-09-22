package algorithms.stack;

import java.util.Stack;

/**
 * @author Yangxiao on 9/12/2019.
 */
public class Daily_Temperatures {
	
	public int[] dailyTemperatures(int[] T) {
		int top = -1, n = T.length;
		int[] stack = new int[n];
		int[] ret = new int[n];
		for (int i = 0; i < n; i++) {
			while (top > -1 && T[i] > T[stack[top]]) {
				int idx = stack[top--];
				ret[idx] = i - idx;
			}
			stack[++top] = i;
		}
		return ret;
	}
	
	public int[] dailyTemperatures_stack(int[] T) {
		int n = T.length;
		int[] res = new int[n];
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < n; i++) {
			while (!s.isEmpty() && T[s.peek()] < T[i]) {
				int idx = s.pop();
				res[idx] = i - idx;
			}
			s.push(i);
		}
		
		while (!s.isEmpty()) {
			res[s.pop()] = 0;
		}
		
		return res;
	}
}
