package algorithms.dynamic_programming;

import algorithms.tree.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yangxiao on 10/5/2019.
 */
public class Valid_Palindrome_III {
	
	public boolean isValidPalindrome(String s, int k) {
		StringBuilder sb = new StringBuilder(s);
		String t = sb.reverse().toString();
		int dis = longestCommonSubsequence(s, t);
		return (s.length() - dis) <= k;
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
