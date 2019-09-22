package algorithms.hashtable;

import java.util.*;

/**
 * @author Yangxiao Wang on 5/11/2019
 */
public class Flower_Planting_With_No_Adjacent {
	
	public static void main(String[] args) {
		Flower_Planting_With_No_Adjacent cs = new Flower_Planting_With_No_Adjacent();
		int n = 3;
		int[][] pa = {{1, 2}, {2, 3}, {3, 1}};
		
		int y = 4;
		int[][] paths = {{1, 2}, {3, 4}};
		
		cs.gardenNoAdj(n, pa);
		cs.gardenNoAdj(y, paths);
	}
	
	public int[] gardenNoAdj(int N, int[][] paths) {
		int[] res = new int[N];
		Set<Integer> set = new HashSet<>();
		for (int k = 1; k <= N; k++) {
			boolean[] nums = new boolean[5];
			for (int i = 0; i < paths.length; i++) {
				int source = paths[i][0];
				int dist = paths[i][1];
				if (source == k) {
					nums[res[dist - 1]] = true;
				}
				if (dist == k) {
					nums[res[source - 1]] = true;
				}
			}
			for (int i = 1; i < 5; i++) {
				if (!nums[i]) {
					res[k - 1] = i;
					break;
				}
			}
			set.add(k);
		}
		return res;
	}
	
	public int[] gardenNoAdj_Sol(int N, int[][] paths) {
		if (N == 1) {
			return new int[]{1};
		}
		if (paths == null || paths.length == 0 || paths[0].length == 0) {
			int[] res = new int[N];
			for (int i = 0; i < N; i++) {
				res[i] = 1;
			}
			return res;
		}
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int[] path : paths) {
			int x = path[0], y = path[1];
			map.putIfAbsent(x, new HashSet<>());
			map.get(x).add(y);
			map.putIfAbsent(y, new HashSet<>());
			map.get(y).add(x);
		}
		int[] res = new int[N];
		// System.out.println(map);
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[4];
			Set<Integer> adj = map.get(i + 1);
			if (adj == null || adj.size() == 0) {
				res[i] = 1;
				continue;
			}
			for (int neigh : adj) {
				if (res[neigh - 1] != 0) {
					visited[res[neigh - 1] - 1] = true;
				}
			}
			
			for (int j = 0; j < 4; j++) {
				if (!visited[j]) {
					res[i] = j + 1;
					break;
				}
			}
			// System.out.println(Arrays.toString(res));
		}
		for (int i = 0; i < N; i++) {
			if (res[i] == 0) {
				res[i] = 1;
			}
		}
		return res;
	}
	
	
	class solution_fast {
		public int[] gardenNoAdj(int n, int[][] paths) {
			int m = paths.length;
			int[] from = new int[m];
			int[] to = new int[m];
			for (int i = 0; i < m; i++) {
				from[i] = paths[i][0] - 1;
				to[i] = paths[i][1] - 1;
			}
			int[][] g = packU(n, from, to);
			int[] color = new int[n];
			for (int i = 0; i < n; i++) {
				int ptn = 0;
				for (int e : g[i]) {
					if (color[e] != 0) {
						ptn |= 1 << color[e] - 1;
					}
				}
				color[i] = Integer.numberOfTrailingZeros(~ptn) + 1;
			}
			return color;
		}
		
		int[][] packU(int n, int[] from, int[] to) {
			int[][] g = new int[n][];
			int[] p = new int[n];
			for (int f : from)
				p[f]++;
			for (int t : to)
				p[t]++;
			for (int i = 0; i < n; i++)
				g[i] = new int[p[i]];
			for (int i = 0; i < from.length; i++) {
				g[from[i]][--p[from[i]]] = to[i];
				g[to[i]][--p[to[i]]] = from[i];
			}
			return g;
		}
		
	}
	
	class Solution_fast_okspeed {
		int[] color;
		List<List<Integer>> G;
		
		public int[] gardenNoAdj(int N, int[][] paths) {
			
			color = new int[N];
			G = new ArrayList<>();
			for (int i = 0; i < N; i++)
				G.add(new ArrayList<>());
			for (int[] p : paths) {
				G.get(p[0] - 1).add(p[1] - 1);
				G.get(p[1] - 1).add(p[0] - 1);
			}
			
			for (int i = 0; i < N; i++) {
				if (color[i] != 0)
					continue;
				boolean[] x = new boolean[5];
				for (int u : G.get(i))
					x[color[u]] = true;
				for (int j = 1; j < 5; j++)
					if (!x[j]) {
						color[i] = j;
						break;
					}
			}
			return color;
		}
	}
	
	class Solution_greedy {
		public int[] gardenNoAdj(int N, int[][] paths) {
			Map<Integer, Set<Integer>> map = new HashMap<>();
			for (int i = 1; i <= N; i++)
				map.put(i, new HashSet<>());
			
			for (int[] edge : paths) {
				map.get(edge[0]).add(edge[1]);
				map.get(edge[1]).add(edge[0]);
			}
			
			int[] ans = new int[N];
			for (int i = 1; i <= N; i++) {
				Set<Integer> set = new HashSet<>();
				for (int k = 1; k <= 4; k++)
					set.add(k);
				
				for (int next : map.get(i)) {
					if (set.contains(ans[next - 1]))
						set.remove(ans[next - 1]);
				}
				ans[i - 1] = set.iterator().next();
			}
			
			return ans;
		}
	}
}
