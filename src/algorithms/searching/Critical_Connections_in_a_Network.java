package algorithms.searching;

import java.util.*;

/**
 * https://leetcode.com/problems/critical-connections-in-a-network/
 * @author Yangxiao on 9/14/2019.
 */
public class Critical_Connections_in_a_Network {
	
	// https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/
	static class Solution_Tarjan {
		public static void main(String[] args) {
			Solution_Tarjan cs = new Solution_Tarjan();
			List<List<Integer>> conn = new ArrayList<>();
			conn.add(new ArrayList<>(Arrays.asList(0, 1)));
			conn.add(new ArrayList<>(Arrays.asList(1, 2)));
			conn.add(new ArrayList<>(Arrays.asList(2, 0)));
			conn.add(new ArrayList<>(Arrays.asList(1, 3)));
			System.out.println(cs.criticalConnections(4, conn));
		}
		
		public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
			int[] disc = new int[n], low = new int[n];
			// use adjacency list instead of matrix will save some memory, adjmatrix will cause MLE
			List<Integer>[] graph = new ArrayList[n];
			List<List<Integer>> res = new ArrayList<>();
			Arrays.fill(disc, -1); // use disc to track if visited (disc[i] == -1)
			for (int i = 0; i < n; i++) {
				graph[i] = new ArrayList<>();
			}
			// build graph
			for (int i = 0; i < connections.size(); i++) {
				int from = connections.get(i).get(0), to = connections.get(i).get(1);
				graph[from].add(to);
				graph[to].add(from);
			}
			
			for (int i = 0; i < n; i++) {
				if (disc[i] == -1) {
					dfs(i, low, disc, graph, res, 0);
				}
			}
			// System.out.println(Arrays.toString(low));
			return res;
		}
		
		int time = 0; // time to discover each vertices
		
		private void dfs(int u, int[] low, int[] disc, List<Integer>[] graph, List<List<Integer>> res,
		                 int pre) {
			disc[u] = low[u] = ++time; // discover u
			// System.out.println(u + " " + time);
			for (int j = 0; j < graph[u].size(); j++) {
				int v = graph[u].get(j);
				if (v == pre) {
					continue; // if parent vertex, ignore
				}
				if (disc[v] == -1) { // if not discovered
					dfs(v, low, disc, graph, res, u);
					low[u] = Math.min(low[u], low[v]);
					if (low[v] > disc[u]) {
						// u - v is critical, there is no path for v to reach back to u or u's previous vertices
						res.add(Arrays.asList(u, v));
					}
				} else { // if discovered but is not parent, update low[u]
					low[u] = Math.min(low[u], disc[v]);
				}
			}
		}
		
	}
	
	class Solution_TopoSort {
		public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
			List<Integer>[] graph = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				graph[i] = new ArrayList<>();
			}
			for (List<Integer> conn : connections) {
				int from = conn.get(0), to = conn.get(1);
				graph[from].add(to);
				graph[to].add(from);
			}
			
			Queue<Integer> q = new LinkedList<>();
			int[] degree = new int[n];
			for (int i = 0; i < n; i++) {
				degree[i] = graph[i].size();
				if (degree[i] == 1) {
					q.offer(i);
				}
			}
			// topsort
			List<List<Integer>> result = new ArrayList<>();
			while (!q.isEmpty()) {
				int from = q.poll();
				int to = graph[from].get(0);
				List<Integer> cur = new ArrayList<>();
				cur.add(from);
				cur.add(to);
				result.add(cur);
				graph[from].remove((Integer) to);
				graph[to].remove((Integer) from);
				if (graph[to].size() == 1) {
					q.offer(to);
				}
			}
			return result;
		}
	}
}
