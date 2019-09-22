package algorithms.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-18
 * <p>
 * The length of the array is in range [1, 20,000]. The range of numbers in the array is [-1000, 1000] and the range of
 * the integer k is [-1e7, 1e7].
 * <p>
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
 * equals to k.
 */
public class Subarray_Sum_Equals_K {
	
	public int subarraySum_bruteForce(int[] nums, int k) {
		int n = nums.length;
		int res = 0;
		for (int i = 1; i < n; i++) {
			nums[i] += nums[i - 1];
		}
		for (int i = 0; i < n; i++) {
			if (nums[i] == k) res++;
			for (int j = 0; j < i; j++) {
				if (nums[i] - nums[j] == k) {
					res++;
				}
			}
		}
		return res;
	}
	
	
	public int subarraySum_Hash(int[] nums, int k) {
		int n = nums.length;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(nums[0], 1);
		int res = 0;
		if (nums[0] == k) res++;
		for (int i = 1; i < n; i++) {
			nums[i] += nums[i - 1];
			int mis = nums[i] - k;
			if (mis == 0) {
				res++;
			}
			if (map.containsKey(mis)) {
				res += map.get(mis);
			}
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}
		return res;
	}
	
	public class Solution {
		public int subarraySum(int[] nums, int k) {
			int count = 0;
			for (int start = 0; start < nums.length; start++) {
				int sum = 0;
				for (int end = start; end < nums.length; end++) {
					sum += nums[end];
					if (sum == k)
						count++;
				}
			}
			return count;
		}
		
		public int subarraySum_HashMOD(int[] nums, int k) {
			int n = nums.length;
			Map<Integer, Integer> map = new HashMap<>();
			map.put(0, 1);
			int res = 0, sum = 0;
			for (int i = 0; i < n; i++) {
				sum += nums[i];
				if (map.containsKey(sum - k)) res += map.get(sum - k);
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
			return res;
		}
	}
}
