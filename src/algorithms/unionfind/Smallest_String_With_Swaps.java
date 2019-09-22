package algorithms.unionfind;

import java.util.*;

/**
 * https://leetcode.com/problems/smallest-string-with-swaps/
 * @author Yangxiao Wang on 9/21/2019
 */
class Smallest_String_With_Swaps {
	
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		int n = s.length();
		char[] cc = s.toCharArray();
		int[] parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < pairs.size(); i++) {
			List<Integer> mm = pairs.get(i);
			union(parent, mm.get(0), mm.get(1));
		}
		
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int iparent = find(parent, i);
			map.putIfAbsent(iparent, new ArrayList<>());
			map.get(parent[i]).add(i);
		}
		for (Map.Entry<Integer, ArrayList<Integer>> e : map.entrySet()) {
			ArrayList<Integer> idxs = e.getValue();
			int m = idxs.size();
			char[] stm = new char[m];
			for (int i = 0; i < m; i++) {
				stm[i] = cc[idxs.get(i)];
			}
			Arrays.sort(stm);
			for (int i = 0; i < m; i++) {
				cc[idxs.get(i)] = stm[i];
			}
		}
		return new String(cc);
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
