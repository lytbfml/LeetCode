package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 4/15/2019.
 */
public class Unique_Paths {
	
	public int uniquePaths(int m, int n) {
		int[][] dp = new int[n][m];
		dp[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i != 0) {
					dp[i][j] += dp[i - 1][j];
				}
				if (j != 0) {
					dp[i][j] += dp[i][j - 1];
				}
			}
		}
		return dp[n - 1][m - 1];
	}
	
	// https://leetcode.com/problems/unique-paths/discuss/23090/JAVA-solution-0ms-4lines
	// If you mark the south move as '1' and the east move as '0'. This problem shall be equal to :
	// Given (m+n-2) bits. you can fill in '1' for (m-1) times and '0' for (n-1) times,
	// what is the number of different numbers?
	// the result is clear that the formula shall be C(m-1)(m+n-2),
	// where m-1 is the superscript behind C and m+n-2 is the subscript behind C.
	// To avoid overflow, I write the program in this manner.
	public int uniquePaths_ss(int m, int n) {
		long result = 1;
		for (int i = 0; i < Math.min(m - 1, n - 1); i++) {
			result = result * (m + n - 2 - i) / (i + 1);
		}
		return (int) result;
	}
}
