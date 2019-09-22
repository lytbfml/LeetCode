package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Palindrome_Partitioning {
	
	public List<List<String>> partition(String s) {
		List<List<String>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), s, 0);
		return list;
	}
	
	public void backtrack(List<List<String>> list, List<String> tempList, String s, int start) {
		if (start == s.length())
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < s.length(); i++) {
				if (isPalindrome(s, start, i)) {
					tempList.add(s.substring(start, i + 1));
					backtrack(list, tempList, s, i + 1);
					tempList.remove(tempList.size() - 1);
				}
			}
		}
	}
	
	public boolean isPalindrome(String s, int low, int high) {
		while (low < high)
			if (s.charAt(low++) != s.charAt(high--))
				return false;
		return true;
	}
	
	
	public class Solution_DP {
		public List<List<String>> partition(String s) {
			List<List<String>> res = new ArrayList<>();
			boolean[][] dp = new boolean[s.length()][s.length()];
			for(int i = 0; i < s.length(); i++) {
				for(int j = 0; j <= i; j++) {
					if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
						dp[j][i] = true;
					}
				}
			}
			helper(res, new ArrayList<>(), dp, s, 0);
			return res;
		}
		
		private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
			if(pos == s.length()) {
				res.add(new ArrayList<>(path));
				return;
			}
			
			for(int i = pos; i < s.length(); i++) {
				if(dp[pos][i]) {
					path.add(s.substring(pos,i+1));
					helper(res, path, dp, s, i+1);
					path.remove(path.size()-1);
				}
			}
		}
	}
}
