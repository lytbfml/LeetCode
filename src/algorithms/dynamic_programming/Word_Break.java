package algorithms.dynamic_programming;

import java.util.*;

/**
 * @author Yangxiao on 4/17/2019.
 */
public class Word_Break {
	
	public static void main(String[] args) {
		Word_Break cs = new Word_Break();
		Solution_recursive sr = new Solution_recursive();
		Solution_DP dp = new Solution_DP();
		List<String> ss = new ArrayList<>(Arrays.asList("leet", "code"));
		List<String> ss2 = new ArrayList<>(
				Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa",
				              "aaaaaaaaa", "aaaaaaaaaa"));
		List<String> ss3 = new ArrayList<>(Arrays.asList("a", "b"));
		List<String> ss4 = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
		System.out.println(dp.wordBreak("leetcode", ss));
		
		System.out.println(dp.wordBreak("ab", ss3));
		System.out.println(dp.wordBreak("catsandog", ss4));
		// System.out.println(sr.wordBreak(
		// 		"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
		// 		ss2));
	}
	
	// find a way that cut the string so that each segment is in wordDict
	static class Solution_DP {
		
		public boolean wordBreak(String s, List<String> wordDict) {
			Set<String> set = new HashSet<>(wordDict);
			int n = s.length();
			int[] dp = new int[n + 1];
			dp[0] = 1;
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j < i; j++) {
					if (dp[j] == 1 && set.contains(s.substring(j, i))) {
						dp[i] = 1;
						break;
					}
				}
			}
			return dp[n] == 1;
		}
	}
	
	static class Solution_BFS {
		
		public boolean wordBreak(String s, List<String> wordDict) {
			Set<String> set = new HashSet<>(wordDict);
			int n = s.length();
			Queue<Integer> queue = new LinkedList<>();
			int[] visited = new int[n];
			queue.add(0);
			
			while (!queue.isEmpty()) {
				int start = queue.poll();
				if (visited[start] == 0) {
					for (int i = start + 1; i <= n; i++) {
						if (set.contains(s.substring(start, i))) {
							queue.add(i);
						}
						if (i == n) {
							return true;
						}
					}
				}
				visited[start] = 1;
			}
			return false;
		}
	}
	
	static class Solution_recursive {
		
		public boolean wordBreak(String s, List<String> wordDict) {
			Set<String> set = new HashSet<>(wordDict);
			return split(s, set, 0, new int[s.length()]);
		}
		
		private boolean split(String s, Set<String> wordDict, int start, int[] dp) {
			if (start == s.length()) {
				return true;
			} else if (dp[start] != 0) {
				return dp[start] == 1;
			}
			for (int i = start + 1; i <= s.length(); i++) {
				if (wordDict.contains(s.substring(start, i)) && split(s, wordDict, i, dp)) {
					dp[start] = 1;
					return true;
				}
			}
			dp[start] = 2;
			return false;
		}
	}
	
	static class Solution_Fast {
		
		public boolean wordBreak(String s, List<String> wordDict) {
			return dfs(s, 0, wordDict, new int[s.length()]);
		}
		
		public boolean dfs(String s, int start, List<String> dic, int[] cache) {
			if (start >= s.length() || cache[start] == -1) { return false; }
			
			for (int i = 0; i < dic.size(); i++) {
				int end = isSubStr(s, start, dic.get(i));
				if (end != -1) {
					if (end == s.length() - 1) {
						return true;
					} else if (dfs(s, end + 1, dic, cache)) {
						return true;
					}
				}
			}
			cache[start] = -1;
			return false;
		}
		
		public int isSubStr(String s, int start, String sub) {
			if (start + sub.length() - 1 >= s.length()) { return -1; }
			for (int i = 0; i < sub.length(); i++) {
				if (sub.charAt(i) != s.charAt(start + i)) { return -1; }
			}
			return start + sub.length() - 1;
		}
	}
}
