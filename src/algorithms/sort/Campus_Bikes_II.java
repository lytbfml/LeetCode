package algorithms.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Priority Queue
 * Bucket sort
 * <p>
 * Greedy
 *
 * @author Yangxiao Wang on 2019-08-20
 */
public class Campus_Bikes_II {
	
	public static void main(String[] args) {
		Campus_Bikes_II cs = new Campus_Bikes_II();
		int i = 1 << 3;
		int k = Integer.parseInt("10001", 2);
		System.out.println((i & k) == 0);
	}
	
	/**
	 * Backtracking and permutation
	 */
	class Solution_bruteForce {
		
		int min = Integer.MAX_VALUE;
		
		public int assignBikes(int[][] workers, int[][] bikes) {
			helper(workers, bikes, 0, 0, new boolean[bikes.length]);
			return min;
		}
		
		private void helper(int[][] workers, int[][] bikes, int curWk, int dis, boolean[] assigned) {
			if (dis >= min) return;
			if (curWk >= workers.length) {
				min = dis;
				return;
			}
			
			for (int i = 0; i < bikes.length; i++) {
				if (!assigned[i]) {
					assigned[i] = true;
					helper(workers, bikes, curWk + 1, dis + distance(workers[curWk], bikes[i]), assigned);
					assigned[i] = false;
				}
			}
		}
		
		private int distance(int[] a, int[] b) {
			return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
		}
	}
	
	class Solution_DP {
		
		public int assignBikes(int[][] workers, int[][] bikes) {
			int n = workers.length, m = bikes.length;
			int numM = 1 << m;
			int[][] dp = new int[n + 1][numM];
			for (int i = 0; i <= n; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
			}
			dp[0][0] = 0;
			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j < numM; j++) {
					for (int k = 0; k < m; k++) {
						if ((j & (1 << k)) == 0) { // will be 0 if nothing matched: 1000 & 11001 != 0, 1000 & 10111 == 0
							continue;
						}
						int pre = j ^ (1 << k);
						int curDis = distance(workers[i - 1], bikes[k]);
						
						dp[i][j] = Math.min(curDis + dp[i - 1][pre], dp[i][j]);
						if (i == n) {
							min = Math.min(min, dp[i][j]);
						}
					}
				}
			}
			
			return min;
		}
		
		private int distance(int[] a, int[] b) {
			return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
		}
	}
	
	
	class Solution_DPMem {
		
		public int assignBikes(int[][] workers, int[][] bikes) {
			int[] dp = new int[1 << bikes.length];
			return dfs(workers, bikes, 0, 0, dp);
		}
		
		private int dfs(int[][] workers, int[][] bikes, int idx, int visited, int[] dp) {
			if (idx == workers.length) return 0;
			if (dp[visited] > 0) return dp[visited];
			int res = Integer.MAX_VALUE;
			for (int i = 0; i < bikes.length; i++) {
				if ((visited & (1 << i)) != 0) continue;
				res = Math.min(res, distance(workers[idx], bikes[i]) +
						dfs(workers, bikes, idx + 1, visited | (1 << i), dp));
			}
			return dp[visited] = res;
		}
		
		private int distance(int[] a, int[] b) {
			return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
		}
	}
	
	
	class Solution_PriorityQueue {
		
		public int assignBikes(int[][] workers, int[][] bikes) {
			int j = bikes.length;
			PriorityQueue<int[]> pq = new PriorityQueue<>((o, q) -> o[0] - q[0]);
			pq.offer(new int[]{0, 0, 0});
			Set<Path> visited = new HashSet<>();
			while (true) {
				int[] curr = pq.poll();
				if (!visited.add(new Path(curr[1], curr[2]))) {
					continue;
				}
				if (curr[1] == workers.length) {
					return curr[0];
				}
				for (int i = 0; i < j; i++) {
					if (((1 << i) & curr[2]) == 0) {
						int manhattanDist = dist(workers[curr[1]], bikes[i]);
						pq.offer(new int[]{curr[0] + manhattanDist, curr[1] + 1, curr[2] | (1 << i)});
					}
				}
			}
		}
		
		public int dist(int[] worker, int[] bike) {
			return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
		}
		
		private class Path {
			int worker;
			int visited;
			
			public Path(int worker, int visited) {
				this.worker = worker;
				this.visited = visited;
			}
			
			@Override
			public boolean equals(Object o) {
				Path p = (Path) o;
				return this.worker == p.worker && this.visited == p.visited;
			}
			
			@Override
			public int hashCode() {
				int res = 17;
				res = res * 31 + worker;
				res = res * 31 + visited;
				return res;
			}
		}
	}
}