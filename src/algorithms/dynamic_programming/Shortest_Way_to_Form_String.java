package algorithms.dynamic_programming;

import java.util.Arrays;

/**
 * @author Yangxiao Wang on 2019-07-03
 */
public class Shortest_Way_to_Form_String {
	
	public int shortestWay(String source, String target) {
		int n = source.length();
		int m = target.length();
		
		for (int i = 0; i < m; i++) {
			if (!source.contains(target.charAt(i) + "")) return -1;
		}
		int res = 0;
		int cursor = 0;
		while (cursor < m) {
			int start = cursor;
			for (int i = 0; i < n; i++) {
				if (cursor < m && source.charAt(i) == target.charAt(cursor)) {
					cursor++;
				}
			}
			if (cursor == start) return -1;
			res++;
		}
		
		return res;
	}
	
	class Solution {
		public int shortestWay(String source, String target) {
			int count = 0;
			int positionConvered = 0;
			
			char[] s = source.toCharArray();
			char[] t = target.toCharArray();
			
			while (positionConvered < t.length) {
				int prePositionCovered = positionConvered;
				positionConvered = findSubSeqInTar(s, t, positionConvered);
				if (positionConvered == prePositionCovered) {
					return -1;
				}
				count++;
			}
			return count;
		}
		
		public int findSubSeqInTar(char[] s, char[] t, int positionCovered) {
			for (int i = 0; i < s.length && positionCovered < t.length; i++) {
				if (s[i] == t[positionCovered]) {
					positionCovered++;
				}
			}
			return positionCovered;
		}
	}
	
	public int shortestWay_MPlusN(String source, String target) {
		char[] S = source.toCharArray();
		char[] T = target.toCharArray();
		int m = S.length;
		int n = T.length;
		int[][] dict = new int[m][26];
		Arrays.fill(dict[m - 1], -1);
		dict[m - 1][S[m - 1] - 'a'] = n - 1;
		for (int i = m - 2; i >= 0; i--) {
			dict[i] = Arrays.copyOf(dict[i + 1], 26);
			dict[i][S[i] - 'a'] = i;
		}
		int idxOfS = 0;
		int res = 0;
		for (int i = 0; i < T.length; i++) {
			char ch = T[i];
			if (dict[idxOfS][ch - 'a'] == -1) {
				res += 1;
				idxOfS = 0;
			}
			idxOfS = dict[idxOfS][ch - 'a'] + 1;
			if (idxOfS == 0) {
				return -1;
			} else if (idxOfS == n) {
				idxOfS = 0;
				res += 1;
			}
		}
		return res + (idxOfS == 0 ? 0 : 1);
	}
}
