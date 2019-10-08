package algorithms.backtracking;

/**
 * @author Yangxiao on 10/5/2019.
 */
public class Path_with_Maximum_Gold {
	
	public int getMaximumGold(int[][] grid) {
		int m = grid.length, n = grid[0].length, res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] != 0) {
					boolean[][] visited = new boolean[m][n];
					res = Math.max(dfs(grid, i, j, visited), res);
				}
			}
		}
		return res;
	}
	
	private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
			return 0;
		}
		int base = grid[i][j];
		int res;
		visited[i][j] = true;
		res = dfs(grid, i + 1, j, visited);
		res = Math.max(dfs(grid, i - 1, j, visited), res);
		res = Math.max(dfs(grid, i, j + 1, visited), res);
		res = Math.max(dfs(grid, i, j - 1, visited), res);
		return res + base;
	}
}