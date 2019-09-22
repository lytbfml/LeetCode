package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 2019-08-09
 */
public class Wildcard_Matching {
	
	class Solution_DPMEM {
		
		public boolean isMatch(String s, String p) {
			int n = s.length(), m = p.length();
			Boolean[][] dp = new Boolean[s.length()][p.length()];
			return matchHelper(s, p, 0, 0, dp);
		}
		
		private boolean matchHelper(String s, String p, int i, int j, Boolean[][] dp) {
			if (j >= p.length()) return i >= s.length();
			if (i >= s.length()) return (p.charAt(j) == '*') && matchHelper(s, p, i, j + 1, dp);
			if (dp[i][j] != null) return dp[i][j];
			boolean match = i < s.length() && s.charAt(i) == p.charAt(j);
			boolean res;
			if (p.charAt(j) == '*') {
				res = matchHelper(s, p, i, j + 1, dp) ||
						matchHelper(s, p, i + 1, j, dp) || matchHelper(s, p, i + 1, j + 1, dp);
			} else if (p.charAt(j) == '?') {
				res = matchHelper(s, p, i + 1, j + 1, dp);
			} else {
				res = match && matchHelper(s, p, i + 1, j + 1, dp);
			}
			dp[i][j] = res;
			return res;
		}
	}
	
	class Solution_DP {
		
		public boolean isMatch(String s, String p) {
			int n = s.length(), m = p.length();
			
			boolean[][] dp = new boolean[n + 1][m + 1];
			dp[n][m] = true;
			for (int i = p.length() - 1; i >= 0; i--) {
				if (p.charAt(i) != '*') break;
				else dp[s.length()][i] = true;
			}
			
			for (int i = n - 1; i >= 0; i--) {
				for (int j = m - 1; j >= 0; j--) {
					boolean match = i < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
					if (p.charAt(j) == '*') dp[i][j] = dp[i][j + 1] || dp[i + 1][j] || dp[i + 1][j + 1];
					else dp[i][j] = match && dp[i + 1][j + 1];
				}
			}
			
			return dp[0][0];
		}
	}
	
	class Solution {
		
		public boolean isMatch(String s, String p) {
			int n = s.length(), m = p.length();
			int i = 0, j = 0;
			int starIndex = -1, sIndex = -1;
			while (i < n) {
				if (j < m && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
					i++;
					j++;
				} else if (j < m && p.charAt(j) == '*') {
					starIndex = j;
					sIndex = i;
					j++;
				} else if (starIndex == -1) {
					return false;
				} else {
					j = starIndex + 1;
					i = sIndex + 1;
					sIndex = i;
				}
			}
			
			for (int k = j; k < m; k++) {
				if (p.charAt(k) != '*') return false;
			}
			
			return true;
		}
		
		// Alternative
		boolean comparison(String str, String pattern) {
			int s = 0, p = 0, match = 0, starIdx = -1;
			while (s < str.length()) {
				// advancing both pointers
				if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
					s++;
					p++;
				}
				// * found, only advancing pattern pointer
				else if (p < pattern.length() && pattern.charAt(p) == '*') {
					starIdx = p;
					match = s;
					p++;
				}
				// last pattern pointer was *, advancing string pointer
				else if (starIdx != -1) {
					p = starIdx + 1;
					match++;
					s = match;
				}
				//current pattern pointer is not star, last patter pointer was not *
				//characters do not match
				else return false;
			}
			
			//check for remaining characters in pattern
			while (p < pattern.length() && pattern.charAt(p) == '*')
				p++;
			
			return p == pattern.length();
		}
	}
}
