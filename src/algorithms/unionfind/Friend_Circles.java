package algorithms.unionfind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/friend-circles/
 *
 * @author Yangxiao on 9/14/2019.
 * @see Sentence_Similarity_II
 */
public class Friend_Circles {
	
	public class Solution_DFS {
		public int findCircleNum(int[][] M) {
			int[] visited = new int[M.length];
			int count = 0;
			for (int i = 0; i < M.length; i++) {
				if (visited[i] == 0) {
					dfs(M, visited, i);
					count++;
				}
			}
			return count;
		}
		
		public void dfs(int[][] M, int[] visited, int i) {
			visited[i] = 1; // or delete this line also works
			for (int j = 0; j < M.length; j++) {
				if (M[i][j] == 1 && visited[j] == 0) {
					visited[j] = 1;
					dfs(M, visited, j);
				}
			}
		}
	}
	
	public class Solution_FastforThis {
		public int findCircleNum(int[][] M) {
			if (M == null) {
				return 0;
			}
			
			int n = M.length;
			int[] parent = new int[n];
			Arrays.fill(parent, -1);
			
			int ans = n;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (M[i][j] == 1) {
						//union i && j, remove j form fatherSet
						int rootA = find(i, parent);
						int rootB = find(j, parent);
						if (rootA != rootB) {
							parent[rootB] = rootA;
							ans--;
						}
					}
				}
			}
			return ans;
		}
		
		public int find(int a, int[] parent) {
			if (parent[a] == -1) {
				parent[a] = a;
			}
			if (parent[a] != a) {
				return parent[a] = find(parent[a], parent);
			}
			return parent[a];
		}
	}
	
	class Solution_UnionFind {
		public int findCircleNum(int[][] M) {
			int n = M.length, res = 0;
			int[] parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (M[i][j] == 1 && i != j) {
						union(parent, i, j);
					}
				}
			}
			int count = 0;
			for (int i = 0; i < parent.length; i++) {
				if (parent[i] == i)
					count++;
			}
			return count;
		}
		
		private int find(int[] parent, int x) {
			if (parent[x] != x) {
				parent[x] = find(parent, parent[x]);
			}
			return parent[x];
		}
		
		private void union(int[] parent, int x, int y) {
			int xset = find(parent, x);
			int yset = find(parent, y);
			parent[xset] = yset;
		}
	}
	
	class Solution_UnionFind_HashMap {
		public int findCircleNum(int[][] M) {
			int n = M.length;
			int res = n;
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (M[i][j] == 1) {
						int parent1 = find(map, i), parent2 = find(map, j);
						if (parent1 != parent2) {
							map.put(parent1, parent2);
							n--;
						}
					}
				}
			}
			return res;
		}
		
		public int find(Map<Integer, Integer> map, int t) {
			if (!map.containsKey(t)) {
				map.put(t, t);
				return t;
			}
			if (t != map.get(t)) {
				map.put(t, find(map, map.get(t)));
			}
			return map.get(t);
		}
		
	}
}
