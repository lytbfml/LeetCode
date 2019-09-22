package algorithms.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yangxiao Wang on 4/27/2019
 */
public class Coloring_A_Border {
	
	int[] neibor = null;
	
	public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
		int colorC = grid[r0][c0];
		if (colorC == color) {
			return grid;
		}
		int n = grid.length;
		int m = grid[0].length;
		int[][] g2 = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				g2[i][j] = grid[i][j];
			}
		}
		
		int[][] dp = new int[n][m];
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{r0, c0});
		while (!q.isEmpty()) {
			int[] ind = q.poll();
			
			if (dp[ind[0]][ind[1]] == 1) {
				continue;
			}
			dp[ind[0]][ind[1]] = 1;
			
			if (ind[0] - 1 <= 0 || grid[ind[0] - 1][ind[1]] != colorC) {
				g2[ind[0]][ind[1]] = color;
				if (ind[0] - 1 == 0 && grid[ind[0] - 1][ind[1]] == colorC) {
					q.add(new int[]{ind[0] - 1, ind[1]});
				}
			} else {
				q.add(new int[]{ind[0] - 1, ind[1]});
			}
			
			if (ind[0] + 1 >= n - 1 || grid[ind[0] + 1][ind[1]] != colorC) {
				g2[ind[0]][ind[1]] = color;
				if (ind[0] + 1 == n - 1 && grid[ind[0] + 1][ind[1]] == colorC) {
					q.add(new int[]{ind[0] + 1, ind[1]});
				}
			} else {
				q.add(new int[]{ind[0] + 1, ind[1]});
			}
			
			if (ind[1] + 1 >= m - 1 || grid[ind[0]][ind[1] + 1] != colorC) {
				g2[ind[0]][ind[1]] = color;
				if (ind[1] + 1 == m - 1 && grid[ind[0]][ind[1] + 1] == colorC) {
					q.add(new int[]{ind[0], ind[1] + 1});
				}
			} else {
				q.add(new int[]{ind[0], ind[1] + 1});
			}
			
			if (ind[1] - 1 <= 0 || grid[ind[0]][ind[1] - 1] != colorC) {
				g2[ind[0]][ind[1]] = color;
				if (ind[1] - 1 == 0 && grid[ind[0]][ind[1] - 1] == colorC) {
					q.add(new int[]{ind[0], ind[1] - 1});
				}
			} else {
				q.add(new int[]{ind[0], ind[1] - 1});
			}
		}
		return g2;
	}
	
	int[] xs = {-1, 1, 0, 0};
	int[] ys = {0, 0, -1, 1};
	
	public int[][] colorBorder_2(int[][] grid, int r0, int c0, int color) {
		int colorC = grid[r0][c0];
		if (colorC == color) {
			return grid;
		}
		int n = grid.length;
		int m = grid[0].length;
		
		boolean[][] seen = new boolean[n][m];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{r0, c0});
		seen[r0][c0] = true;
		
		while (!q.isEmpty()) {
			int[] ind = q.poll();
			int r = ind[0];
			int c = ind[1];
			if (r == 0 || r == m - 1 || c == 0 || c == n - 1) {
				grid[r][c] = color;
			}
			
			for (int i = 0; i < 4; i++) {
				int x = r + xs[i];
				int y = c + ys[i];
				if (x < 0 || x >= m || y < 0 || y >= n || seen[x][y]) {
					continue;
				}
				if (grid[x][y] == colorC) { // its neighbor is of same color.
					seen[x][y] = true; // avoid visiting again.
					q.offer(new int[]{x, y}); // put it into Queue.
				} else { // its neighbor is of different color, hence it is on component boundary.
					grid[r][c] = color;
				}
			}
		}
		return grid;
	}
	
	class Solution {
		private int[] d = {0, 1, 0, -1, 0}; // neighbors'  relative displacements.
		
		public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
			dfs(grid, r0, c0, grid[r0][c0]);
			for (int[] g : grid) {
				for (int i = 0; i < g.length; ++i) {
					if (g[i] < 0) {
						g[i] = color;
					}
				}
			}
			return grid;
		}
		
		private void dfs(int[][] grid, int r, int c, int clr) {
			if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != clr) {
				return;
			}
			grid[r][c] = -1; // mark as visited.
			int cnt = 0; // use to count grid[r][c]'s component neighbors (same color as it).
			for (int i = 0; i < 4; ++i) { // traverse 4 neighbors.
				int x = r + d[i], y = c + d[i + 1]; // nieghbor's coordinates.
				if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
					continue;
				} // out of grid.
				if (grid[x][y] == clr || grid[x][y] < 0) {
					++cnt;
				} // only if the 4 neighbors of grid[r][c] are all have same color as it, it is on inner part.
				dfs(grid, x, y, clr);
			}
			if (cnt == 4) {
				grid[r][c] = clr;
			} // inner part, change back.
		}
	}
}
