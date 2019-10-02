import util.Utilities;

import java.util.*;

/**
 * @author Yangxiao on 10/1/2019.
 */
public class Bricks_Falling_When_Hit {
	
	public static void main(String[] args) {
		Bricks_Falling_When_Hit cs = new Bricks_Falling_When_Hit();
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
	
	public int[] hitBricks(int[][] grid, int[][] hits) {
		int[] res = new int[hits.length];
		for (int i = 0; i < hits.length; i++) {
			int x = hits[i][0], y = hits[i][1];
			if (grid[x][y] == 1) {
				grid[x][y] = 0;
				for (int j = 0; j < 4; j++) {
					boolean find = dfs(grid, x + add[j][0], y + add[j][1]);
					// System.out.println(find);
					if (!find) {
						Queue<int[]> q = new LinkedList<>();
						q.add(new int[]{x + add[j][0], y + add[j][1]});
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
								int ttx = tx + add[k][0], tty = ty + add[k][1];
								// System.out.println("HHHHHHH " + ttx + " " + tty);
								if (ttx < grid.length && tty < grid[0].length && ttx >= 0 && tty >= 0) {
									// System.out.println("HHHHHHH " + ttx + " " + tty);
									if (grid[ttx][tty] != 0) {
										q.offer(new int[]{ttx, tty});
									}
								}
							}
						}
						res[i] += tmp;
					}
				}
				// Utilities.print2DArray(grid);
				// System.out.println(Arrays.toString(res));
			}
		}
		return res;
	}
	
	int[][] add = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	private boolean dfs(int[][] grid, int x, int y) {
		if (x >= grid.length || y >= grid[0].length || x < 0 || y < 0) return false;
		if (x == 0 && grid[x][y] == 1) return true;
		if (grid[x][y] == 0) {
			return false;
		}
		boolean find = false;
		grid[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			if (dfs(grid, x + add[i][0], y + add[i][1])) {
				find = true;
				break;
			}
		}
		grid[x][y] = 1;
		return find;
	}
	
}
