package structures.graph;

import java.util.*;

/**
 * @author Yangxiao Wang on 2019-08-06
 */
public class Alien_Dictionary {
	
	
	LinkedHashMap<Character, LinkedHashSet<Character>> graph = new LinkedHashMap<>();
	
	public String alienOrder(String[] words) {
		Set<Character> set = new HashSet<>();
		
		for (int i = 0; i < words.length; i++) {
			
			for (int j = 0; j < words[i].length(); j++) {
				graph.putIfAbsent(words[i].charAt(j), new LinkedHashSet<>());
			}
			
			char cur = words[i].charAt(0);
			
			if (i == 0 || words[i - 1].charAt(0) != cur) {
				if (set.contains(cur)) {
					return "";
				}
				set.add(cur);
				if (i != 0) {
					graph.get(words[i - 1].charAt(0)).add(cur);
				}
			} else {
				if (getNext(words[i - 1], words[i])) {
					return "";
				}
			}
		}
		
		Set<Character> visited = new HashSet<>();
		Stack stack = new Stack();
		
		for (Map.Entry<Character, LinkedHashSet<Character>> e : graph.entrySet()) {
			if (!visited.contains(e.getKey())) {
				dfs(e.getKey(), visited, stack);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.toString();
	}
	
	private void dfs(char v, Set<Character> visited, Stack stack) {
		
		visited.add(v);
		if (graph.containsKey(v)) {
			for (char i : graph.get(v)) {
				if (!visited.contains(i)) {
					dfs(i, visited, stack);
				}
			}
		}
		
		stack.push(v);
	}
	
	private boolean getNext(String prev, String cur) {
		for (int i = 0; i < prev.length() && i < cur.length(); i++) {
			char pre = prev.charAt(i);
			char now = cur.charAt(i);
			if (pre != now) {
				
				if (graph.get(now).contains(pre)) {
					return true;
				}
				
				graph.get(pre).add(now);
				return false;
			}
		}
		return false;
	}
	
	class Solution {
		
		Map<Integer, LinkedHashSet<Integer>> graph = new LinkedHashMap<>();
		
		public String alienOrder(String[] words) {
			Set<Character> set = new HashSet<>();
			
			for (int i = 0; i < words.length; i++) {
				
				for (int j = 0; j < words[i].length(); j++) {
					graph.putIfAbsent(words[i].charAt(j) - 'a', new LinkedHashSet<>());
				}
				
				char cur = words[i].charAt(0);
				
				if (i == 0 || words[i - 1].charAt(0) != cur) {
					if (set.contains(cur)) {
						return "";
					}
					set.add(cur);
					if (i != 0) {
						graph.get(words[i - 1].charAt(0) - 'a').add(cur - 'a');
					}
				} else {
					if (getNext(words[i - 1], words[i])) {
						return "";
					}
				}
			}
			
			boolean[] visited = new boolean[250];
			Arrays.fill(visited, false);
			Stack<Integer> stack = new Stack();
			
			for (Map.Entry<Integer, LinkedHashSet<Integer>> e : graph.entrySet()) {
				if (!visited[e.getKey()]) {
					dfs(e.getKey(), visited, stack);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			while (!stack.isEmpty()) {
				sb.append((char) (stack.pop() + 'a'));
			}
			
			return sb.toString();
		}
		
		private void dfs(int v, boolean[] visited, Stack<Integer> stack) {
			
			visited[v] = true;
			if (graph.containsKey(v)) {
				for (int i : graph.get(v)) {
					if (!visited[i]) {
						dfs(i, visited, stack);
					}
				}
			}
			stack.push(v);
		}
		
		private boolean getNext(String prev, String cur) {
			for (int i = 0; i < prev.length() && i < cur.length(); i++) {
				char pre = prev.charAt(i);
				char now = cur.charAt(i);
				if (pre != now) {
					
					if (graph.get(now - 'a').contains(pre - 'a')) {
						return true;
					}
					
					graph.get(pre - 'a').add(now - 'a');
					return false;
				}
			}
			return false;
		}
	}
	
	class Solution_go {
		private final int N = 26;
		
		public String alienOrder(String[] words) {
			boolean[][] adj = new boolean[N][N];
			int[] visited = new int[N];
			buildGraph(words, adj, visited);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				if (visited[i] == 0) {                 // unvisited
					if (!dfs(adj, visited, sb, i)) return "";
				}
			}
			return sb.reverse().toString();
		}
		
		public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
			visited[i] = 1;                            // 1 = visiting
			for (int j = 0; j < N; j++) {
				if (adj[i][j]) {                        // connected
					if (visited[j] == 1) return false;  // 1 => 1, cycle
					if (visited[j] == 0) {              // 0 = unvisited
						if (!dfs(adj, visited, sb, j)) return false;
					}
				}
			}
			visited[i] = 2;                           // 2 = visited
			sb.append((char) (i + 'a'));
			return true;
		}
		
		public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
			Arrays.fill(visited, -1);                 // -1 = not even existed
			for (int i = 0; i < words.length; i++) {
				for (char c : words[i].toCharArray()) visited[c - 'a'] = 0;
				if (i > 0) {
					String w1 = words[i - 1], w2 = words[i];
					int len = Math.min(w1.length(), w2.length());
					for (int j = 0; j < len; j++) {
						char c1 = w1.charAt(j), c2 = w2.charAt(j);
						if (c1 != c2) {
							adj[c1 - 'a'][c2 - 'a'] = true;
							break;
						}
					}
				}
			}
		}
	}
}
