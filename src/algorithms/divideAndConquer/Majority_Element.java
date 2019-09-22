package algorithms.divideAndConquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-10
 */
public class Majority_Element {
	
	// Hash map
	public int majorityElement_hash(int[] nums) {
		int n = nums.length;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : nums) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		int max = Integer.MIN_VALUE;
		int res = -1;
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			int val = e.getValue();
			if (val > max) {
				max = val;
				res = e.getKey();
				if (res > n / 2) return res;
			}
		}
		return res;
	}
	
	// Mjrty alg - Moore voting algorithm
	public int majorityElement_smart(int[] nums) {
		int count = 0;
		Integer candidate = null;
		for (int num : nums) {
			if (count == 0) {
				candidate = num;
			}
			count += (num == candidate) ? 1 : -1;
		}
		return candidate;
	}
	
	// Sort
	class Solution {
		public int majorityElement(int[] nums) {
			Arrays.sort(nums);
			return nums[nums.length / 2];
		}
	}
	
	public int majorityElement(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		return helper(nums, 0, nums.length - 1);
	}
	
	private int helper(int[] a, int i, int j) {
		if (i == j) return a[i];
		int mid = i + (j - i) / 2;
		int L = helper(a, i, mid);
		int R = helper(a, mid + 1, j);
		if (L == R) return R;
		int cL = 0, cR = 0;
		for (int k = i; k <= j; k++) {
			if (a[k] == L) cL++;
			if (a[k] == R) cR++;
		}
		return cL > cR ? L : R;
	}
	
	
	public int majorityElement_bitManipulation(int[] nums) {
		int[] bit = new int[32];
		for (int num: nums)
			for (int i=0; i<32; i++)
				if ((num>>(31-i) & 1) == 1)
					bit[i]++;
		int ret=0;
		for (int i=0; i<32; i++) {
			bit[i]=bit[i]>nums.length/2?1:0;
			ret += bit[i]*(1<<(31-i));
		}
		return ret;
	}
}
