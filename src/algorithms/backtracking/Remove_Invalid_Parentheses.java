package algorithms.backtracking;

import java.util.*;

/**
 * @author Yangxiao Wang on 8/1/2019
 */
public class Remove_Invalid_Parentheses {
	
	public static void main(String[] args) {
		Remove_Invalid_Parentheses cs = new Remove_Invalid_Parentheses();
		
		// System.out.println(cs.removeInvalidParentheses("()))((()"));
		// System.out.println(cs.removeInvalidParentheses("()())()"));
		// System.out.println(cs.removeInvalidParentheses(")("));
		// System.out.println(cs.removeInvalidParentheses("(a)())()"));
		System.out.println(cs.removeInvalidParentheses("()())"));
	}
	
	public List<String> removeInvalidParentheses(String s) {
		int numL = 0, numR = 0;
		char[] cc = s.toCharArray();
		for (int i = 0; i < cc.length; i++) {
			if (cc[i] == '(') {
				numL++;
			} else if (cc[i] == ')') {
				if (numL > 0) {
					numL--;
				} else {
					numR++;
				}
			}
		}
		List<String> res = new ArrayList<>();
		dfs(cc, 0, 0, numL, numR, new StringBuilder(), res);
		return res;
	}
	
	private void dfs(char[] cc, int index, int open, int numL, int numR, StringBuilder sb, List<String> res) {
		if (open < 0) return;
		if (index == cc.length) {
			if (numL == 0 && numR == 0 && open == 0) {
				res.add(sb.toString());
			}
			return;
		}
		int n = sb.length();
		
		if (cc[index] == '(') {
			int i = 1;
			while (index + i < cc.length && cc[index + i] == '(') i++;
			if (numL > 0) dfs(cc, index + 1, open, numL - 1, numR, sb, res);
			sb.append(cc, index, i);
			dfs(cc, index + i, open + i, numL, numR, sb, res);
		} else if (cc[index] == ')') {
			int i = 1;
			while (index + i < cc.length && cc[index + i] == ')') i++;
			if (numR > 0) dfs(cc, index + 1, open, numL, numR - 1, sb, res);
			dfs(cc, index + i, open - i, numL, numR, sb.append(cc, index, i), res);
		} else {
			dfs(cc, index + 1, open, numL, numR, sb.append(cc[index]), res);
		}
		sb.setLength(n);
	}
	
	static class Solution_bfs_fast {
		
		public List<String> removeInvalidParentheses(String s) {
			if (isValid(s)) return Collections.singletonList(s);
			List<String> ans = new ArrayList<>();
			//The queue only contains invalid middle steps
			Queue<Tuple> queue = new LinkedList<>();
			//The 3-Tuple is (string, startIndex, lastRemovedChar)
			queue.add(new Tuple(s, 0, ')'));
			while (!queue.isEmpty()) {
				Tuple cur = queue.poll();
				//Observation 2, start from last removal position
				for (int i = cur.start; i < cur.string.length(); ++i) {
					char ch = cur.string.charAt(i);
					//Not parentheses
					if (ch != '(' && ch != ')') continue;
					//Observation 1, do not repeatedly remove from consecutive ones
					if (i != cur.start && cur.string.charAt(i - 1) == ch) continue;
					//Observation 3, do not remove a pair of valid parentheses
					if (cur.removed == '(' && ch == ')') continue;
					String t = cur.string.substring(0, i) + cur.string.substring(i + 1);
					//Check isValid before add
					if (isValid(t)) ans.add(t);
						//Avoid adding leaf level strings
					else if (ans.isEmpty()) queue.add(new Tuple(t, i, ch));
				}
			}
			return ans;
		}
		
		static boolean isValid(String s) {
			int count = 0;
			for (int i = 0; i < s.length(); ++i) {
				char c = s.charAt(i);
				if (c == '(') ++count;
				if (c == ')' && count-- == 0) return false;
			}
			return count == 0;
		}
		
		private class Tuple {
			public final String string;
			public final int start;
			public final char removed;
			
			public Tuple(String string, int start, char removed) {
				this.string = string;
				this.start = start;
				this.removed = removed;
			}
		}
	}
	
	class Solution_bfs_slow {
		
		public List<String> removeInvalidParentheses(String s) {
			Set<String> mem = new HashSet<>();
			List<String> res = new ArrayList<>();
			Queue<String> q = new LinkedList<>();
			q.add(s);
			mem.add(s);
			boolean found = false;
			while (!q.isEmpty()) {
				String cur = q.poll();
				if (isValid(cur)) {
					found = true;
					res.add(cur);
				}
				if (found) continue;
				
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == '(' || s.charAt(i) == ')') {
						String cc = s.substring(0, i) + s.substring(i + 1);
						if (!mem.contains(cc)) {
							mem.add(cc);
							q.add(cc);
						}
					}
				}
			}
			
			return res;
		}
		
		
		private boolean isValid(String s) {
			int cnt = 0;
			
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '(') cnt++;
				if (c == ')' && cnt-- == 0) return false;
			}
			
			return cnt == 0;
		}
	}
	
	class Solution_dfs_slow {
		
		int minRev = Integer.MAX_VALUE;
		
		public List<String> removeInvalidParentheses(String s) {
			Set<String> mem = new HashSet<>();
			Map<Integer, List<String>> res = new HashMap<>();
			mem.add(s);
			dfs(s, mem, res, 0);
			System.out.println(minRev);
			return res.get(minRev);
		}
		
		private void dfs(String s, Set<String> mem, Map<Integer, List<String>> res, int numRev) {
			if (numRev <= minRev && isValid(s)) {
				minRev = numRev;
				if (res.containsKey(minRev)) {
					res.get(minRev).add(s);
				} else {
					res.put(minRev, new ArrayList<>(Arrays.asList(s)));
				}
				return;
			}
			
			if (numRev > minRev) return;
			
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(' || s.charAt(i) == ')') {
					String cc = s.substring(0, i) + s.substring(i + 1);
					if (!mem.contains(cc)) {
						mem.add(cc);
						dfs(cc, mem, res, numRev + 1);
					}
				}
			}
		}
		
		private boolean isValid(String s) {
			int cnt = 0;
			
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c == '(') cnt++;
				if (c == ')' && cnt-- == 0) return false;
			}
			
			return cnt == 0;
		}
	}
}
