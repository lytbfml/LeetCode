package algorithms.searching;

/**
 * @author Yangxiao on 4/10/2019.
 */
public class Max_Area_of_Island {
	
	public int maxAreaOfIsland(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int n = grid.length;
		int m = grid[0].length;
		int[][] check = new int[n][m];
		int size = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (check[i][j] == 0 && grid[i][j] == 1) {
					size = Math.max(size, bfs(i, j, grid, check));
				}
			}
		}
		return size;
	}
	
	private int bfs(int i, int j, int[][] grid, int[][] check) {
		
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
			return 0;
		}
		int sum = 0;
		if (check[i][j] == 0) {
			check[i][j] = 1;
			if (grid[i][j] == 1) {
				sum++;
				sum += bfs(i, j + 1, grid, check);
				sum += bfs(i, j - 1, grid, check);
				sum += bfs(i + 1, j, grid, check);
				sum += bfs(i - 1, j, grid, check);
			}
		}
		return sum;
	}
}
