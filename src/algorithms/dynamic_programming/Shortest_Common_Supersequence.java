package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 2019-08-13
 */
public class Shortest_Common_Supersequence {
	
	class Solution_DP_LCS {
		
		public String shortestCommonSupersequence(String str1, String str2) {
			int n = str1.length(), m = str2.length();
			int[][] dp = new int[n + 1][m + 1];
			int[][] mem = new int[n + 1][m + 1];
			char[] c1 = str1.toCharArray();
			char[] c2 = str2.toCharArray();
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					if (dp[i - 1][j] > dp[i][j - 1]) {
						mem[i][j] = 1;
						dp[i][j] = dp[i - 1][j];
					} else {
						mem[i][j] = 2;
						dp[i][j] = dp[i][j - 1];
					}
					// del down dp[i - 1][j], ins -> dp[i][j - 1]
					if (c1[i - 1] == c2[j - 1] && dp[i - 1][j - 1] + 1 > dp[i][j]) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
						mem[i][j] = 3;
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			int i = n, j = m;
			while (i > 0 && j > 0) {
				if (mem[i][j] == 1) {
					i--;
					sb.append(c1[i]);
				} else if (mem[i][j] == 2) {
					j--;
					sb.append(c2[j]);
				} else if (mem[i][j] == 3) {
					i--;
					j--;
					sb.append(c1[i]);
				} else {
					break;
				}
			}
			// Arrays.stream(mem).forEach(a -> System.out.println(Arrays.toString(a)));
			// System.out.println(i + " " + j);
			while (i > 0) sb.append(c1[--i]);
			while (j > 0) sb.append(c2[--j]);
			return sb.reverse().toString();
		}
	}
	
	class Solution_LCS_FastButSame {
		
		public String shortestCommonSupersequence(String str1, String str2) {
			//first we need to get the longest common subsequence
			int l1 = str1.length();
			int l2 = str2.length();
			char[] array1 = str1.toCharArray();
			char[] array2 = str2.toCharArray();
			//dp[i][j] is the longest common subsequence length from s1[0:i-1] and s2[0:j-1]
			int[][] dp = new int[l1 + 1][l2 + 1];
			//dp[0][i] = 0;
			//dp[i][0] = 0;
			for (int i = 1; i <= l1; i++) {
				for (int j = 1; j <= l2; j++) {
					if (array1[i - 1] == array2[j - 1]) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
				}
			}
			
			StringBuilder ans = new StringBuilder();
			while (l1 != 0 || l2 != 0) {
				char c = ' ';
				if (l1 == 0) {
					c = array2[--l2];
				} else if (l2 == 0) {
					c = array1[--l1];
				} else if (array1[l1 - 1] == array2[l2 - 1]) {
					c = array1[l1 - 1];
					l1--;
					l2--;
				} else if (dp[l1 - 1][l2] == dp[l1][l2]) {
					c = array1[l1 - 1];
					l1--;
				} else if (dp[l1][l2 - 1] == dp[l1][l2]) {
					c = array2[l2 - 1];
					l2--;
				}
				ans.append(c);
			}
			return ans.reverse().toString();
		}
	}
	
}
