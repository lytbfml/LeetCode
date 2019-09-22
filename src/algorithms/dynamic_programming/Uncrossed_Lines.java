package algorithms.dynamic_programming;

/**
 * also dfs
 *
 * @author Yangxiao Wang on 4/27/2019
 */
public class Uncrossed_Lines {
	
	public static void main(String[] args) {
		Uncrossed_Lines cs = new Uncrossed_Lines();
		int[] a = {1, 4, 2};
		int[] b = {1, 2, 4};
		cs.maxUncrossedLines(a, b);
		int x = 0;
		System.out.println(x);
	}
	
	public int maxUncrossedLines(int[] A, int[] B) {
		int n = A.length;
		int m = B.length;
		
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (A[i - 1] == B[i - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		return dp[n][m];
	}
}
