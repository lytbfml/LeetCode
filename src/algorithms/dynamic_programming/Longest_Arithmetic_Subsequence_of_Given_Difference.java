package algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2sum
 *
 * @author Yangxiao on 10/5/2019.
 */
public class Longest_Arithmetic_Subsequence_of_Given_Difference {
	
	public int longestSubsequence(int[] arr, int difference) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int a : arr) {
			map.put(a, Math.max(map.getOrDefault(a - difference, 0) + 1, map.getOrDefault(a, 0)));
		}
		int res = 0;
		for (int val : map.values()) {
			res = Math.max(res, val);
		}
		return res;
	}
	
	public int longestSubsequence2(int[] arr, int difference) {
		int ans = 0;
		Map<Integer, Integer> count = new HashMap<>();
		for (int a : arr)
			ans = Math.max(ans, count.compute(a, (k, v) -> 1 + count.getOrDefault(a - difference, 0)));
		return ans;
	}
	
	class Solution {
		public int longestSubsequence(int[] arr, int difference) {
			HashMap<Integer, Integer> countFrequency = new HashMap<>();
			int longest = 0;
			
			for (int i = 0; i < arr.length; i++) {
				countFrequency.put(arr[i], countFrequency.getOrDefault(arr[i] - difference, 0) + 1);
				longest = Math.max(longest, countFrequency.get(arr[i]));
			}
			return longest;
		}
	}
	
}
