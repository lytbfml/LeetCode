package algorithms.sliding_window;

import java.util.Collections;
import java.util.HashMap;

/**
 * @author Yangxiao Wang on 2019-07-24
 * <p>
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */
public class Longest_Substring_with_At_Most_Two_Distinct_Characters {
	
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int n = s.length(), cnt = 0, start = 0, res = 0;
		int[] mem = new int[128];
		char[] ss = s.toCharArray();
		for (int i = 0; i < n; i++) {
			if (mem[ss[i]]++ == 0) cnt++;
			while (cnt > 2) if (mem[ss[start++]]-- == 1) cnt--;
			res = Math.max(res, i - start + 1);
		}
		return res;
	}
	
	class Solution_Sol {
		public int lengthOfLongestSubstringTwoDistinct(String s) {
			int n = s.length();
			if (n < 3) return n;
			
			// sliding window left and right pointers
			int left = 0;
			int right = 0;
			// hashmap character -> its rightmost position
			// in the sliding window
			HashMap<Character, Integer> hashmap = new HashMap<>();
			
			int max_len = 2;
			
			while (right < n) {
				// slidewindow contains less than 3 characters
				if (hashmap.size() < 3)
					hashmap.put(s.charAt(right), right++);
				
				// slidewindow contains 3 characters
				if (hashmap.size() == 3) {
					// delete the leftmost character
					int del_idx = Collections.min(hashmap.values());
					hashmap.remove(s.charAt(del_idx));
					// move left pointer of the slidewindow
					left = del_idx + 1;
				}
				
				max_len = Math.max(max_len, right - left);
			}
			return max_len;
		}
	}
	
}
