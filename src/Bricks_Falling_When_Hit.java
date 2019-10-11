import algorithms.array.Spiral_Matrix;
import util.Utilities;

import java.util.*;

/**
 * DFS, BFS, Union Find
 *
 * @author Yangxiao on 10/1/2019.
 */
public class Bricks_Falling_When_Hit {
	
	public static void main(String[] args) {
		Solution_Fast cs = new Solution_Fast();
		int[][] x1 = {{1, 0, 0, 0}, {1, 1, 1, 0}};
		int[][] x2 = {{1, 0}};
		System.out.println(Arrays.toString(cs.hitBricks(x1, x2)));
		x1 = new int[][]{{1, 0, 0, 0}, {1, 1, 0, 0}};
		x2 = new int[][]{{1, 1}, {1, 0}};
		System.out.println(Arrays.toString(cs.hitBricks(x1, x2)));
		x1 = new int[][]{{1}, {1}, {1}, {1}, {1}};
		x2 = new int[][]{{3, 0}, {4, 0}, {1, 0}, {2, 0}, {0, 0}};
		System.out.println(Arrays.toString(cs.hitBricks(x1, x2)));
		x1 = new int[][]{{1, 0, 1}, {1, 1, 1}};
		x2 = new int[][]{{0, 0}, {0, 2}, {1, 1}};
		System.out.println(Arrays.toString(cs.hitBricks(x1, x2)));
	}
	
	int[][] neighbor = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public int[] hitBricks(int[][] grid, int[][] hits) {
		int[] res = new int[hits.length];
		for (int i = 0; i < hits.length; i++) {
			int x = hits[i][0], y = hits[i][1];
			if (grid[x][y] == 1) {
				grid[x][y] = 0;
				for (int j = 0; j < 4; j++) {
					boolean find = dfs(grid, x + neighbor[j][0], y + neighbor[j][1]);
					if (!find) {
						Queue<int[]> q = new LinkedList<>();
						q.add(new int[]{x + neighbor[j][0], y + neighbor[j][1]});
						int tmp = 0;
						while (!q.isEmpty()) {
							int[] pos = q.poll();
							int tx = pos[0], ty = pos[1];
							if (tx >= grid.length || ty >= grid[0].length || tx < 0 || ty < 0 || grid[tx][ty] == 0) {
								continue;
							}
							grid[tx][ty] = 0;
							tmp++;
							for (int k = 0; k < 4; k++) {
								int ttx = tx + neighbor[k][0], tty = ty + neighbor[k][1];
								if (ttx < grid.length && tty < grid[0].length && ttx >= 0 && tty >= 0) {
									if (grid[ttx][tty] != 0) {
										q.offer(new int[]{ttx, tty});
									}
								}
							}
						}
						res[i] += tmp;
					}
				}
			}
		}
		return res;
	}
	
	private boolean dfs(int[][] grid, int x, int y) {
		if (x >= grid.length || y >= grid[0].length || x < 0 || y < 0) return false;
		if (x == 0 && grid[x][y] == 1) return true;
		if (grid[x][y] == 0) {
			return false;
		}
		boolean find = false;
		grid[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			if (dfs(grid, x + neighbor[i][0], y + neighbor[i][1])) {
				find = true;
				break;
			}
		}
		grid[x][y] = 1;
		return find;
	}
	
	static class Solution_Fast {
		// first remove all hits block, then dfs from top block and mark connected blocks to 2
		// All remaining 1s will be blocks that will drop
		// dfs from hits to find all remaining 1s
		public int[] hitBricks(int[][] grid, int[][] hits) {
			int[] res = new int[hits.length];
			for (int[] hit : hits) {
				if (grid[hit[0]][hit[1]] == 0) {
					grid[hit[0]][hit[1]] = -1;
					
				} else grid[hit[0]][hit[1]] = 0;
			}
			for (int i = 0; i < grid[0].length; i++) dfs(grid, 0, i);
			Utilities.print2DArray(grid);
			for (int i = hits.length - 1; i >= 0; i--) {
				int[] hit = hits[i];
				if (grid[hit[0]][hit[1]] == -1) res[i] = 0;
				else {
					if (isConnected(grid, hit[0], hit[1])) {
						grid[hit[0]][hit[1]] = 1;
						res[i] = dfs(grid, hit[0], hit[1]) - 1;
					} else {
						res[i] = 0;
						grid[hit[0]][hit[1]] = 1;
					}
				}
			}
			Utilities.print2DArray(grid);
			return res;
		}
		
		private boolean isConnected(int[][] grid, int x, int y) {
			if (x == 0) return true;
			if (x > 0 && grid[x - 1][y] == 2) return true;
			if (y > 0 && grid[x][y - 1] == 2) return true;
			if (x < grid.length - 1 && grid[x + 1][y] == 2) return true;
			if (y < grid[0].length - 1 && grid[x][y + 1] == 2) return true;
			return false;
		}
		
		private int dfs(int[][] grid, int x, int y) {
			if (grid[x][y] != 1) return 0;
			grid[x][y] = 2;
			int res = 1;
			if (x > 0 && grid[x - 1][y] == 1) res += dfs(grid, x - 1, y);
			if (y > 0 && grid[x][y - 1] == 1) res += dfs(grid, x, y - 1);
			if (x < grid.length - 1 && grid[x + 1][y] == 1) res += dfs(grid, x + 1, y);
			if (y < grid[0].length - 1 && grid[x][y + 1] == 1) res += dfs(grid, x, y + 1);
			return res;
		}
	}
	
	// https://leetcode.com/problems/bricks-falling-when-hit/discuss/137465/Java-Union-Find-beats-100
	class Solution_UnionFind {
		public int[] hitBricks(int[][] grid, int[][] hits) {
			// Think of it reversely: from end to start each hit will add one block to the grid,
			// What we want to find is how many blocks that have been fixed onto the ceiling.
			// This is a Union-Find Solution
			if (grid.length == 0) return new int[0];
			final int M = grid.length, N = grid[0].length, K = hits.length;
			for (int[] hit : hits) {
				// Using grid[i][j]-- every time, we can check if grid[i][j] > 0
				// to find the first time the block has been removed if there are duplicated hits in the sequence.
				grid[hit[0]][hit[1]]--;
			}
			int[] ufs = new int[M *
					N];         // Union find set, ufs[key] is key's parent, thru which we can find root recursively
			int[] num = new int[M * N];         // Number of nodes in this set, only stored at the root node
			int[] ret = new int[K];             // the retval we need to compute
			// init ufs and num
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (grid[i][j] < 1) continue;
					addOneBlock(grid, ufs, num, i, j, M, N, false);
				}
			}
			// start to go thru hits (reversely)
			for (int k = K - 1; k >= 0; k--) {
				int x = hits[k][0], y = hits[k][1];
				if (++grid[x][y] <= 0) ret[k] = 0;
				else ret[k] = addOneBlock(grid, ufs, num, x, y, M, N, true);
			}
			return ret;
		}
		
		private int addOneBlock(int[][] grid, int[] ufs, int[] num, int x, int y, int M, int N, boolean union4) {
			// This func is used to union neighbors and calculate the count of blocks that have been unioned to ceiling
			// Note that if this is iterating from left-top to right-bottom, we only need to union current with left and top
			// So union4 is a flag determining if we go thru 4 neighbors or just 2;
			int key = x * N + y;
			ufs[key] = key;
			num[key] = 1;
			int count = key < N ? 1 : 0;    // if key is at ceiling, init count as 1, because this block has
			// already been fixed.
			if (x > 0 && grid[x - 1][y] == 1) count += union(ufs, num, key - N, key, N);
			if (y > 0 && grid[x][y - 1] == 1) count += union(ufs, num, key - 1, key, N);
			if (union4 && x < M - 1 && grid[x + 1][y] == 1) count += union(ufs, num, key + N, key, N);
			if (union4 && y < N - 1 && grid[x][y + 1] == 1) count += union(ufs, num, key + 1, key, N);
			count--;                        // remove the added block itself if we have some blocks fixed.
			return Math.max(0, count);
		}
		
		private int union(int[] ufs, int[] num, int k1, int k2, int N) {
			// Union the two set
			// If one root is ceiling, union the other to this
			// Only if some out-ceiling nodes has been unioned to ceiling set, we return the number of blocks settled.
			while (k1 != ufs[k1]) k1 = ufs[k1];
			while (k2 != ufs[k2]) k2 = ufs[k2];
			if (k1 == k2) return 0;
			if (k1 < N || k2 >= N) {
				num[k1] += num[k2];
				ufs[k2] = k1;
				if (k1 < N && k2 >= N) return num[k2];
				return 0;
			}
			// k1 >= N && k2 < N
			num[k2] += num[k1];
			ufs[k1] = k2;
			return num[k1];
		}
	}
}
