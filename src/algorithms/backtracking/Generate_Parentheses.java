package algorithms.backtracking;

import java.util.*;

/**
 * @author Yangxiao Wang on 5/17/2019
 */
public class Generate_Parentheses {
	
	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<String>();
		backtrack(list, "", 0, 0, n);
		return list;
	}
	
	public void backtrack(List<String> list, String str, int open, int close, int max) {
		
		if (str.length() == max * 2) {
			list.add(str);
			return;
		}
		
		if (open < max)
			backtrack(list, str + "(", open + 1, close, max);
		if (close < open)
			backtrack(list, str + ")", open, close + 1, max);
	}
	
	
	class Solution {
		public List<String> generateParenthesis(int n) {
			List<String> res = new ArrayList<>();
			if (n == 0)
				return res;
			StringBuilder sb = new StringBuilder();
			dfs(n, sb, res, 0, 0);
			return res;
		}
		
		private void dfs(int n, StringBuilder sb, List<String> res, int left, int right) {
			if (2 * n == left + right) {
				res.add(sb.toString());
				return;
			}
			//left
			if (left < n) {
				sb.append('(');
				dfs(n, sb, res, left + 1, right);
				sb.setLength(sb.length() - 1);
			}
			//right
			if (right < left) {
				sb.append(')');
				dfs(n, sb, res, left, right + 1);
				sb.setLength(sb.length() - 1);
			}
		}
	}
}
