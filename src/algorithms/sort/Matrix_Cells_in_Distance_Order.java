package algorithms.sort;

import java.util.*;

/**
 * @author Yangxiao on 4/20/2019.
 */
public class Matrix_Cells_in_Distance_Order {
	
	class Solution {
		public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
			int[][] res = new int[R * C][2];
			ArrayList<pair> list = new ArrayList<>();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int dis = Math.abs(r0 - i) + Math.abs(c0 - j);
					list.add(new pair(i, j, dis));
				}
			}
			list.sort(Comparator.comparingInt(o -> o.dis));
			for (int i = 0; i < list.size(); i++) {
				pair x = list.get(i);
				res[i][0] = x.i;
				res[i][1] = x.j;
			}
			return res;
		}
		
		class pair {
			int dis;
			int i, j;
			
			public pair(int i, int j, int dis) {
				this.dis = dis;
				this.i = i;
				this.j = j;
			}
		}
	}
	
	class Solution_BFS {
		
		public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
			boolean[][] visited = new boolean[R][C];
			int[][] res = new int[R * C][2];
			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[]{r0, c0});
			visited[r0][c0] = true;
			int cnt = 0;
			while (!queue.isEmpty()) {
				int[] rc = queue.poll();
				int r = rc[0];
				int c = rc[1];
				
				res[cnt++] = rc;
				if (r > 0 && !visited[r - 1][c]) {
					visited[r - 1][c] = true;
					queue.offer(new int[]{r - 1, c});
				}
				if (r < R - 1 && !visited[r + 1][c]) {
					visited[r + 1][c] = true;
					queue.offer(new int[]{r + 1, c});
				}
				if (c > 0 && !visited[r][c - 1]) {
					visited[r][c - 1] = true;
					queue.offer(new int[]{r, c - 1});
				}
				if (c < C - 1 && !visited[r][c + 1]) {
					visited[r][c + 1] = true;
					queue.offer(new int[]{r, c + 1});
				}
			}
			
			return res;
		}
	}
}
