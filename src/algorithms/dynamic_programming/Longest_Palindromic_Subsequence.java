package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 2019-08-09
 */
public class Longest_Palindromic_Subsequence {
	
	class Solution_BruteForce {
		
		public int longestPalindromeSubseq(String s) {
			int n = s.length();
			return helper(0, n - 1, s);
		}
		
		private int helper(int l, int r, String s) {
			if (l == r) return 1;
			if (l > r) return 0;
			return s.charAt(l) == s.charAt(r) ? 2 + helper(l + 1, r - 1, s) :
			       Math.max(helper(l + 1, r, s), helper(l, r - 1, s));
		}
	}
	
	class Solution_RecWithMem {
		
		public int longestPalindromeSubseq(String s) {
			int n = s.length();
			return helper(0, n - 1, s, new Integer[n][n]);
		}
		
		private int helper(int i, int j, String s, Integer[][] mem) {
			if (mem[i][j] != null) return mem[i][j];
			if (i > j) return 0;
			if (i == j) return 1;
			if (s.charAt(i) == s.charAt(j)) {
				mem[i][j] = helper(i + 1, j - 1, s, mem) + 2;
			} else {
				mem[i][j] = Math.max(helper(i, j - 1, s, mem), helper(i + 1, j, s, mem));
			}
			return mem[i][j];
		}
	}
	
	class Solution_dp {
		
		public int longestPalindromeSubseq(String s) {
			int n = s.length();
			int[][] dp = new int[n][n];
			
			for (int i = n - 1; i >= 0; i--) {
				dp[i][i] = 1;
				for (int j = i + 1; j < n; j++) {
					if (s.charAt(i) == s.charAt(j)) {
						dp[i][j] = dp[i + 1][j - 1] + 2;
					} else {
						dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
					}
				}
			}
			
			return dp[0][n - 1];
		}
		
		public int longestPalindromeSubseq_Improve(String s) {
			char[] cc = s.toCharArray();
			int n = cc.length;
			int[] dp = new int[n];
			
			for (int i = 0; i < n; i++) {
				dp[i] = 1;
				int maxPair = 0;
				for (int j = i - 1; j >= 0; j--) {
					int prev = dp[j];
					if (cc[j] == cc[i]) {
						dp[j] = maxPair + 2;
					}
					maxPair = Math.max(maxPair, prev);
				}
			}
			int max = 0;
			for (int d : dp) max = Math.max(max, d);
			return max;
		}
	}
	
	class Solution_LCS {
		
		// find LCS between string s and its reversed string
		public int longestPalindromeSubseq(String s) {
			int n = s.length(), m = s.length();
			int[][] dp = new int[n + 1][m + 1];
			char[] c1 = s.toCharArray();
			char[] c2 = new StringBuilder(s).reverse().toString().toCharArray();
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (c1[i - 1] == c2[j - 1]) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
				}
			}
			return dp[n][m];
		}
	}
}
