package algorithms.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yangxiao on 9/27/2019.
 */
public class Minimum_Knight_Moves {
	public int minKnightMoves(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		int[][] board = new int[300][300];
		boolean[][] visited = new boolean[300][300];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{0, 0, 0});
		visited[0][0] = true;
		int[][] dir = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-2, -2}};
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == x && cur[1] == y) {
				return cur[2];
			}
			
			for (int i = 0; i < 8; i++) {
				int[] m = dir[i];
				int tx = cur[0] + m[0], ty = cur[1] + m[1];
				if (tx >= 0 && ty >= 0 && !visited[tx][ty]) {
					q.offer(new int[]{tx, ty, cur[2] + 1});
				}
			}
		}
		
		return 0;
	}
	
	public int minKnightMoves_Math(int x, int y) {
		// Symmetry for axes
		x = Math.abs(x);
		y = Math.abs(y);
		// Symmetry for diagonal
		if (x < y) {
			int temp = x;
			x = y;
			y = temp;
		}
		if (x == 1 && y == 0) {
			return 3;
		}
		if (x == 2 && y == 2) {
			return 4;
		}
		int delta = x - y;
		if (y > delta) {
			return (int) (delta - 2 * Math.floor((float) (delta - y) / 3));
		} else {
			return (int) (delta - 2 * Math.floor((float) (delta - y) / 4));
		}
	}
}
