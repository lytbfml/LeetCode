package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 4/19/2019.
 */
public class Edit_Distance {
	
	public int minDistance_2(String word1, String word2) {
		int n = word1.length(), m = word2.length();
		int[][] dp = new int[n + 1][m + 1];
		char[] c1 = word1.toCharArray();
		char[] c2 = word2.toCharArray();
		
		for (int i = 0; i <= n; i++) {
			dp[i][0] = i;
		}
		for (int i = 0; i <= m; i++) {
			dp[0][i] = i;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int no = 1;
				if (c1[i - 1] == c2[j - 1]) {
					no--;
				}
				dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + no);
			}
		}
		return dp[n][m];
	}
	
	
	public int minDistance(String word1, String word2) {
		if (word1 == null && word2 == null) {
			return 0;
		} else if (word1 == null) {
			return word2.length();
		} else if (word2 == null) {
			return word1.length();
		}
		
		int n = word1.length();
		int m = word2.length();
		
		if (n * m == 0)
			return m + n;
		
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i < n; i++) {
			dp[i][0] = i;
		}
		for (int i = 0; i < m; i++) {
			dp[0][i] = i;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int t1 = dp[i - 1][j];
				int t2 = dp[i][j - 1];
				int t3 = dp[i - 1][j - 1];
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					t3--;
				}
				dp[i][j] = 1 + Math.min(t1, Math.min(t2, t3));
			}
		}
		return dp[n][m];
	}
	
	class Solution {
		public int minDistance(String word1, String word2) {
			int[][] memo = new int[word1.length()][word2.length()];
			return dfs(word1, word2, word1.length() - 1, word2.length() - 1, memo);
		}
		
		private int dfs(String word1, String word2, int row, int col, int[][] memo) {
			if (row < 0 || col < 0)
				return Math.max(row, col) + 1;
			if (memo[row][col] != 0)
				return memo[row][col];
			if (word1.charAt(row) == word2.charAt(col)) {
				return memo[row][col] = dfs(word1, word2, row - 1, col - 1, memo);
			} else {
				int min = Math.min(
						Math.min(dfs(word1, word2, row - 1, col, memo),
						         dfs(word1, word2, row, col - 1, memo)),
						dfs(word1, word2, row - 1, col - 1, memo));
				return memo[row][col] = min + 1;
			}
			
		}
	}
}
