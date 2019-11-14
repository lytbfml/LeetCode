package algorithms.stack;

import java.util.Stack;

/**
 * @author Yangxiao on 11/2/2019.
 */
public class Minimum_Remove_to_Make_Valid_Parentheses {
	
	public String minRemoveToMakeValid(String s) {
		StringBuilder sb = new StringBuilder(s);
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < sb.length(); ++i) {
			if (sb.charAt(i) == '(') st.add(i);
			if (sb.charAt(i) == ')') {
				if (!st.empty()) st.pop();
				else sb.setCharAt(i, '*');
			}
		}
		while (!st.empty()) {
			sb.setCharAt(st.peek(), '*');
			st.pop();
		}
		return sb.toString().replaceAll("\\*", "");
	}
}


