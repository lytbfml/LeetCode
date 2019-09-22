package algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yangxiao Wang on 9/8/2019
 */
public class Is_Subsequence {
	
	public boolean isSubsequence(String s, String t) {
		int n = s.length(), m = t.length(), i = 0;
		for (int j = 0; i < n && j < m; j++) {
			if (s.charAt(i) == t.charAt(j)) {
				i++;
			}
		}
		return i == n;
	}
	
	class Solution_Fast {
		public boolean isSubsequence(String s, String t) {
			if (s.length() < 1) return true;
			int pre = t.indexOf(s.charAt(0));
			for (int i = 1; i < s.length() && pre != -1; i++) {
				pre = t.indexOf(s.charAt(i), pre + 1);
			}
			return pre != -1;
		}
	}
	
	class Solution_FollowUp {
		public boolean isSubsequence(String s, String t) {
			List<Integer>[] idx = new List[256]; // Just for clarity
			for (int i = 0; i < t.length(); i++) {
				int tmp = t.charAt(i);
				if (idx[tmp] == null)
					idx[tmp] = new ArrayList<>();
				idx[tmp].add(i);
			}
			
			int prev = 0;
			for (int i = 0; i < s.length(); i++) {
				int tmp = s.charAt(i);
				if (idx[tmp] == null) return false; // Note: char of S does NOT exist in T causing NPE
				int j = Collections.binarySearch(idx[tmp], prev);
				if (j < 0) j = -j - 1;
				if (j == idx[tmp].size()) return false;
				prev = idx[tmp].get(j) + 1;
			}
			return true;
		}
	}
}
