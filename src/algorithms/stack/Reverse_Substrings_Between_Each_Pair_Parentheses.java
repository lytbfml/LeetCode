package algorithms.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Yangxiao on 9/14/2019.
 */
public class Reverse_Substrings_Between_Each_Pair_Parentheses {
	
	public String reverseParentheses_Stack(String s) {
		Stack<Character> stack = new Stack<>();
		char[] cc = s.toCharArray();
		for (int i = 0; i < cc.length; i++) {
			if (cc[i] == ')') {
				StringBuilder sb = new StringBuilder();
				while (!stack.isEmpty() && stack.peek() != '(') {
					char c = stack.pop();
					sb.append(c);
				}
				stack.pop();
				for (int j = 0; j < sb.length(); j++) {
					stack.push(sb.charAt(j));
				}
			} else {
				stack.push(cc[i]);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.reverse().toString();
	}
	
	
	public String reverseParentheses_QueuePStack(String s) {
		Stack<Character> st = new Stack<>();
		boolean start = true;
		for (char c : s.toCharArray()) {
			if (start && c == '(')
				continue;
			if (c == ')') {
				Queue<Character> p = new LinkedList<>();
				while (!st.isEmpty() && st.peek() != '(') {
					p.add(st.pop());
				}
				if (!st.isEmpty())
					st.pop();
				while (!p.isEmpty())
					st.push(p.remove());
				
			} else {
				st.push(c);
			}
			
			start = false;
		}
		StringBuilder sb = new StringBuilder();
		while (!st.isEmpty())
			sb.append(st.pop());
		
		return sb.reverse().toString();
	}
	
	public static String reverseParentheses(String S) {
		StringBuilder res = new StringBuilder(S);
		int s = -1, e = -1;
		for (int i = 0; i < res.length(); i++) {
			if (res.charAt(i) == '(')
				s = i;
			if (res.charAt(i) == ')') {
				e = i;
				break;
			}
		}
		if (s == -1)
			return res.toString();
		StringBuilder temp = new StringBuilder(res.substring(s + 1, e)).reverse();
		res = new StringBuilder(res.substring(0, s) + temp + res.substring(e + 1));
		return reverseParentheses(res.toString());
	}
}
