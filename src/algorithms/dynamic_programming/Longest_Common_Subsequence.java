package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 2019-08-08
 */
public class Longest_Common_Subsequence {
	
	public static void main(String[] args) {
		Longest_Common_Subsequence cs = new Longest_Common_Subsequence();
		cs.longestCommonSubsequence("bsbininm", "jmjkbkjkv");
	}
	
	public int longestCommonSubsequence(String text1, String text2) {
		int n = text1.length(), m = text2.length();
		int[][] dp = new int[n + 1][m + 1];
		char[] c1 = text1.toCharArray();
		char[] c2 = text2.toCharArray();
		
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
