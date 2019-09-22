package algorithms.sort;

import java.util.*;

/**
 * Priority Queue
 * Bucket sort
 * <p>
 * Greedy
 *
 * @author Yangxiao Wang on 2019-08-20
 */
public class Campus_Bikes {
	
	class Solution1 {
		public int[] assignBikes(int[][] workers, int[][] bikes) {
			int n = workers.length, m = bikes.length;
			int[] res = new int[n];
			PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[0] == o2[0]) {
						if (o1[1] == o2[1])
							return o1[2] - o2[2];
						else
							return o1[1] - o2[1];
					} else {
						return o1[0] - o2[0];
					}
				}
			});
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					q.add(new int[]{distance(workers[i], bikes[j]), i, j});
				}
			}
			
			Integer[] mem = new Integer[m];
			Arrays.fill(res, -1);
			int cnt = 0;
			while (cnt < n) {
				int[] cur = q.poll();
				if (res[cur[1]] == -1 && mem[cur[2]] == null) {
					res[cur[1]] = cur[2];
					mem[cur[2]] = cur[1];
					cnt++;
				}
			}
			
			return res;
		}
		
		private int distance(int[] a, int[] b) {
			return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
		}
	}
	
	class Solution {
		
		public int[] assignBikes(int[][] workers, int[][] bikes) {
			int n = workers.length, m = bikes.length;
			List<int[]>[] mem = new ArrayList[2001];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					int dis = distance(workers[i], bikes[j]);
					if (mem[dis] == null) mem[dis] = new ArrayList<>();
					mem[dis].add(new int[]{i, j});
				}
			}
			
			int[] res = new int[n];
			Arrays.fill(res, -1);
			boolean[] bikeMem = new boolean[m];
			for (int i = 0; i < 2001; i++) {
				if (mem[i] == null) continue;
				for (int j = 0; j < mem[i].size(); j++) {
					int[] cur = mem[i].get(j);
					if (res[cur[0]] == -1 && !bikeMem[cur[1]]) {
						res[cur[0]] = cur[1];
						bikeMem[cur[1]] = true;
					}
				}
			}
			
			return res;
		}
		
		private int distance(int[] a, int[] b) {
			return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
		}
	}
}
