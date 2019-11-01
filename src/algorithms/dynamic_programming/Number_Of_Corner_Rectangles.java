package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 10/31/2019
 */
public class Number_Of_Corner_Rectangles {
	
	
	public int countCornerRectangles(int[][] grid) {
		int[][] dp = new int[grid[0].length][grid[0].length];
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = i + 1; j < grid[0].length; j++) {
				if (grid[0][i] == 1 && grid[0][j] == 1) {
					dp[i][j] = 1;
				}
			}
		}
		
		int res = 0;
		for (int i = 1; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					for (int k = j + 1; k < grid[0].length; k++) {
						if (grid[i][k] == 1) {
							res += dp[j][k];
							dp[j][k]++;
						}
					}
				}
			}
		}
		return res;
	}
	
	public int countCornerRectangles_beter(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[] mem = new int[n * n];
		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					for (int k = j + 1; k < n; k++) {
						if (grid[i][k] == 1) {
							int pos = j * n + k;
							res += mem[pos];
							mem[pos]++;
						}
					}
				}
			}
		}
		return res;
	}
	
}
