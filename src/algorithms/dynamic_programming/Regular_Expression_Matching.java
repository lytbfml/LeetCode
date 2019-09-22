package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 4/18/2019.
 */
public class Regular_Expression_Matching {
	
	class Solution_Recursive {
		
		public boolean isMatch_recBase(String s, String p) {
			if (p.isEmpty()) { return s.isEmpty(); }
			boolean match = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
			if (p.length() >= 2 && p.charAt(1) == '*') {
				return (isMatch(s, p.substring(2)) || (match && isMatch(s.substring(1), p)));
			} else {
				return match && isMatch(s.substring(1), p.substring(1));
			}
		}
		
		
		public boolean isMatch(String s, String p) {
			return isMatchHelper(s, p, 0, 0);
		}
		
		private boolean isMatchHelper(String s, String p, int i, int j) {
			if (j >= p.length()) { return i >= s.length(); }
			boolean match = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
			if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
				return (isMatchHelper(s, p, i, j + 2) || (match && isMatchHelper(s, p, i + 1, j)));
			} else {
				return match && isMatchHelper(s, p, i + 1, j + 1);
			}
		}
	}
	
	class Solution_DP_mem {
		
		public boolean isMatch(String s, String p) {
			int n = s.length(), m = p.length();
			Boolean[][] dp = new Boolean[n + 1][m + 1];
			return helper(s, p, dp, 0, 0);
		}
		
		private boolean helper(String s, String p, Boolean[][] dp, int i, int j) {
			if (j >= p.length()) return i >= s.length();
			if (dp[i][j] != null) return dp[i][j];
			boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
			boolean res;
			if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
				res = (helper(s, p, dp, i, j + 2) || (match && helper(s, p, dp, i + 1, j)));
			} else {
				res = match && helper(s, p, dp, i + 1, j + 1);
			}
			
			dp[i][j] = res;
			return res;
		}
		
		
		public boolean isMatch2(String s, String p) {
			int[][] dp = new int[s.length() + 1][p.length() + 1];
			return isMatchRecDP_helper(s, p, 0, 0, dp);
		}
		
		private boolean isMatchRecDP_helper(String s, String p, int i, int j, int[][] dp) {
			if (dp[i][j] != 0)
				return dp[i][j] == 1;
			if (j >= p.length())
				return i >= s.length();
			boolean res;
			boolean match = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
			if (j < p.length() - 1 && p.charAt(j + 1) == '*')
				res = (isMatchRecDP_helper(s, p, i, j + 2, dp) ||
						(match && isMatchRecDP_helper(s, p, i + 1, j, dp)));
			else
				res = match && isMatchRecDP_helper(s, p, i + 1, j + 1, dp);
			dp[i][j] = res ? 1 : 2;
			return res;
		}
		
	}
	
	class Solution_DP {
		
		public boolean isMatch(String s, String p) {
			if (s == null || s.isEmpty()) {
				return false;
			}
			if (p == null || p.isEmpty()) {
				return true;
			}
			
			int n = s.length();
			int m = p.length();
			
			boolean[][] dp = new boolean[n + 1][m + 1];
			dp[n][m] = true;
			
			for (int i = n; i >= 0; i--) {
				for (int j = m - 1; j >= 0; j--) {
					boolean match = i < n && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
					if (j < m - 1 && p.charAt(j + 1) == '*') {
						dp[i][j] = dp[i][j + 2] || match && dp[i + 1][j];
					} else {
						dp[i][j] = match && dp[i + 1][j + 1];
					}
				}
			}
			return dp[0][0];
		}
		
		/**
		 * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
		 * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
		 * 3, If p.charAt(j) == '*':
		 * here are two sub conditions:
		 * 1    if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  in this case, a* only counts as empty
		 * 2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
		 * dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
		 * or dp[i][j] = dp[i][j-1] // in this case, a* counts as single a
		 * or dp[i][j] = dp[i][j-2] // in this case, a* counts as empty
		 */
		public boolean isMatch_DPSol(String s, String p) {
			
			if (s == null || p == null) {
				return false;
			}
			boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
			dp[0][0] = true;
			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) == '*' && dp[0][i - 1]) {
					dp[0][i + 1] = true;
				}
			}
			for (int i = 0; i < s.length(); i++) {
				for (int j = 0; j < p.length(); j++) {
					if (p.charAt(j) == '.') {
						dp[i + 1][j + 1] = dp[i][j];
					}
					if (p.charAt(j) == s.charAt(i)) {
						dp[i + 1][j + 1] = dp[i][j];
					}
					if (p.charAt(j) == '*') {
						if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
							dp[i + 1][j + 1] = dp[i + 1][j - 1];
						} else {
							dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
						}
					}
				}
			}
			return dp[s.length()][p.length()];
		}
	}
}
