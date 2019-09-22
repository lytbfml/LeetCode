package algorithms.searching;

import java.util.Stack;

/**
 * Greedy
 * @author Yangxiao Wang on 2019-07-06
 */
public class Maximum_Nesting_Depth_of_Two_Valid_Parentheses_Strings {
	
	public int[] maxDepthAfterSplit(String seq) {
		int n = seq.length();
		Stack<Integer> stack = new Stack<>();
		int[] res = new int[n];
		int pushAB = 0;
		for (int i = 0; i < n; i++) {
			if (seq.charAt(i) == '(') {
				stack.push(pushAB);
				res[i] = pushAB;
				if (seq.charAt(i + 1) != ')') {
					pushAB = pushAB == 0 ? 1 : 0;
				}
			} else if (seq.charAt(i) == ')') {
				res[i] = stack.pop();
			}
		}
		return res;
	}
}
