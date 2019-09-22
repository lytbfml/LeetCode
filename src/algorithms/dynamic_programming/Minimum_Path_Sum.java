package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 2019-07-17
 */
public class Minimum_Path_Sum {
	
	public int minPathSum_bad(int[][] grid) {
		if (grid == null || grid.length == 0) return 0;
		int n = grid.length, m = grid[0].length;
		int[][] dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = grid[0][0];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (j != m - 1) dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + grid[i][j + 1]);
				if (i != n - 1) dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + grid[i + 1][j]);
			}
		}
		return dp[n - 1][m - 1];
	}
	
	public int minPathSum_noexSpace(int[][] grid) {
		if (grid == null || grid.length == 0) return 0;
		int n = grid.length, m = grid[0].length;
		
		for (int i = n - 2; i >= 0; i--) {
			grid[i][m - 1] = grid[i][m - 1] + grid[i + 1][m - 1];
		}
		
		for (int i = m - 2; i >= 0; i--) {
			grid[n - 1][i] = grid[n - 1][i] + grid[n - 1][i + 1];
		}
		
		for (int i = n - 2; i >= 0; i--) {
			for (int j = m - 2; j >= 0; j--) {
				grid[i][j] = Math.min(grid[i + 1][j], grid[i][j + 1]) + grid[i][j];
			}
		}
		
		return grid[0][0];
	}
	
	public int minPathSum_noexSpace2(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j != 0) grid[i][j] += grid[i][j - 1];
				if (i != 0 && j == 0) grid[i][j] += grid[i - 1][j];
				if (i != 0 && j != 0) grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
			}
		}
		return grid[m - 1][n - 1];
	}
	
	public int minPathSum_rec(int[][] grid) {
		if (grid == null || grid.length == 0) return 0;
		int n = grid.length, m = grid[0].length;
		int[][] mem = new int[n][m];
		return helper(grid, mem, 0, 0);
	}
	
	private int helper(int[][] grid, int[][] dp, int i, int j) {
		if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
		if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
		if (dp[i][j] != 0) return dp[i][j]; // have to use dp because
		dp[i][j] = Math.min(helper(grid, dp, i, j + 1), helper(grid, dp, i + 1, j)) + grid[i][j];
		return grid[i][j];
	}
}
