package algorithms.backtracking;

import java.util.*;

/**
 * @author Yangxiao on 9/10/2019.
 */
public class Palindrome_Permutation {
	
	public boolean canPermutePalindrome(String s) {
		int[] mem = new int[256];
		int n = s.length();
		for (int i = 0; i < n; i++) {
			mem[s.charAt(i)]++;
		}
		boolean odd = (n % 2 != 0);
		int cnt = 0;
		for (int i = 0; i < 256; i++) {
			if ((mem[i] & 1) != 0) {
				cnt++;
				if (cnt > 1) {
					return false;
				}
			}
		}
		
		if (odd) {
			return cnt == 1;
		} else {
			return cnt != 1;
		}
	}
	
	public class Solution {
		public boolean canPermutePalindrome(String s) {
			Set<Character> set = new HashSet<>();
			for (int i = 0; i < s.length(); ++i) {
				if (!set.contains(s.charAt(i)))
					set.add(s.charAt(i));
				else
					set.remove(s.charAt(i));
			}
			return set.size() == 0 || set.size() == 1;
		}
	}
	
}
