package algorithms.string;

import java.util.Stack;

/**
 * @author Yangxiao on 10/31/2018.
 */

class Longest_Valid_Parentheses {
	
	
	public int longestValidParentheses_dp(String s) {
		char[] c = s.toCharArray();
		int[] dp = new int[c.length];
		int re = 0;
		for (int i = 1; i < c.length; i++) {
			if (c[i] == ')') {
				if (c[i - 1] == '(') {
					if (i < 2) {
						dp[i] = 2;
					} else {
						dp[i] = dp[i - 2] + 2;
					}
				} else if (i - dp[i - 1] > 0 && c[(i - dp[i - 1] - 1)] == '(') {
					dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0);
				}
				re = Math.max(re, dp[i]);
			}
		}
		
		return re;
	}
	
	public int longestValidParentheses_Sol2(String s) {
		int maxans = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.empty()) {
					stack.push(i);
				} else {
					maxans = Math.max(maxans, i - stack.peek());
				}
			}
		}
		return maxans;
	}
	
	public int longestValidParentheses_Sol3(String s) {
		int left = 0, right = 0, maxlength = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				maxlength = Math.max(maxlength, 2 * right);
			} else if (right >= left) {
				left = right = 0;
			}
		}
		left = right = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				maxlength = Math.max(maxlength, 2 * left);
			} else if (left >= right) {
				left = right = 0;
			}
		}
		return maxlength;
	}
	
	public static void main(String[] args) {
		Longest_Valid_Parentheses lv = new Longest_Valid_Parentheses();
		int x = lv.longestValidParentheses_dp("(()())");
		System.out.println(x);
	}
}
