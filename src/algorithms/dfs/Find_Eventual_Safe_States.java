package algorithms.dfs;

import java.util.*;

/**
 * Topological Sort
 *
 * @author Yangxiao on 10/1/2019.
 */
public class Find_Eventual_Safe_States {
	
	public List<Integer> eventualSafeNodes(int[][] graph) {
		int n = graph.length;
		List<Integer> res = new ArrayList<>();
		int[] visited = new int[n];
		for (int i = 0; i < graph.length; i++) {
			if (dfs(graph, visited, i)) {
				res.add(i);
			}
		}
		return res;
	}
	
	private boolean dfs(int[][] graph, int[] visited, int i) {
		if (visited[i] != 0) {
			return visited[i] == 1;
		}
		visited[i] = 2;
		for (int j = 0; j < graph[i].length; j++) {
			if (!dfs(graph, visited, graph[i][j])) {
				return false;
			}
		}
		visited[i] = 1;
		return true;
	}
	
	public List<Integer> eventualSafeNodes_BFS(int[][] graph) {
		int N = graph.length;
		int[] degree = new int[N];
		Map<Integer, List<Integer>> neighbors = new HashMap<>();
		for (int i = 0; i < graph.length; i++) {
			for (int neighbor : graph[i]) {
				if (!neighbors.containsKey(neighbor)) neighbors.put(neighbor, new ArrayList<>());
				neighbors.get(neighbor).add(i);
				degree[i]++;
			}
		}
		
		Set<Integer> res = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			if (degree[i] == 0) {
				res.add(i);
				queue.add(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int v = queue.poll();
			res.add(v);
			if (neighbors.containsKey(v)) {
				for (int neighbor : neighbors.get(v)) {
					degree[neighbor]--;
					if (degree[neighbor] == 0) {
						queue.offer(neighbor);
					}
				}
			}
			
		}
		List<Integer> list = new ArrayList<>(res);
		Collections.sort(list);
		return list;
	}
	
}
