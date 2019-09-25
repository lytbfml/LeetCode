package algorithms.stack;

import java.util.Stack;

/**
 * @author Yangxiao Wang on 9/25/2019
 */
public class Score_of_Parentheses {
	
	public int scoreOfParentheses(String S) {
		int n = S.length(), res = 0, prev = 0, back = 0;
		char[] cc = S.toCharArray();
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for (int i = 0; i < n; i++) {
			if (cc[i] == '(') {
				stack.push(0);
			} else {
				int a = stack.pop();
				int b = stack.pop();
				stack.push(Math.max(1, 2 * a) + b);
			}
		}
		
		return stack.pop();
	}
	
	public int scoreOfParentheses2(String S) {
		Stack<Integer> stack = new Stack<>();
		int cur = 0;
		for (char c : S.toCharArray()) {
			if (c == '(') {
				stack.push(cur);
				cur = 0;
			} else {
				cur = stack.pop() + Math.max(cur * 2, 1);
			}
		}
		return cur;
	}
	
	public int scoreOfParentheses_Array(String S) {
		int res[] = new int[30], i = 0;
		for (char c : S.toCharArray())
			if (c == '(') res[++i] = 0;
			else res[i - 1] += Math.max(res[i--] * 2, 1);
		return res[0];
	}
	
	public int scoreOfParentheses_O1Space(String S) {
		int res = 0, l = 0;
		for (int i = 0; i < S.length(); ++i) {
			if (S.charAt(i) == '(') {
				l++;
			} else {
				l--;
				if (S.charAt(i - 1) == '(') {
					res += 1 << l;
				}
			}
			
		}
		return res;
	}
	
	class Solution {
		int i;
		
		public int scoreOfParentheses(String S) {
			i = 0;
			return helper(S);
		}
		
		public int helper(String S) {
			int parenthesisCount = 0;
			while (i < S.length()) {
				if (S.charAt(i) == '(') {
					i = i + 1;
					parenthesisCount = parenthesisCount + helper(S);
				} else {
					if (S.charAt(i) == ')') {
						i = i + 1;
					}
					if (parenthesisCount == 0) {
						return 1;
					} else {
						return 2 * parenthesisCount;
					}
				}
				
			}
			return parenthesisCount;
		}
	}
}
