package algorithms.bfs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yangxiao Wang on 8/17/2019
 */
public class As_Far_from_Land_as_Possible {
	
	int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public int maxDistance(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					queue.offer(new int[]{i, j});
					visited[i][j] = true;
				}
			}
		}
		int level = -1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] start = queue.poll();
				int x = start[0];
				int y = start[1];
				for (int[] dir : directions) {
					int newX = x + dir[0];
					int newY = y + dir[1];
					if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
							&& !visited[newX][newY] && grid[newX][newY] == 0) {
						visited[newX][newY] = true;
						queue.offer(new int[]{newX, newY});
					}
				}
			}
			level++;
		}
		return level <= 0 ? -1 : level;
	}
	
	class Solution {
		public int maxDistance(int[][] grid) {
			int m = grid.length, n = grid[0].length;
			boolean[][] visited = new boolean[m][n];
			Queue<int[]> q = new LinkedList<>();
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == 1) {
						visited[i][j] = true;
						q.offer(new int[]{i, j});
					}
				}
			}
			int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
			int result = -1;
			while (!q.isEmpty()) {
				int size = q.size();
				while (size-- > 0) {
					int[] cur = q.poll();
					result = Math.max(result, grid[cur[0]][cur[1]] - 1);
					for (int[] dir : dirs) {
						int x = cur[0] + dir[0], y = cur[1] + dir[1];
						if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
							visited[x][y] = true;
							grid[x][y] = grid[cur[0]][cur[1]] + 1;
							q.offer(new int[]{x, y});
						}
					}
				}
			}
			return result == 0 ? -1 : result;
		}
	}
}
