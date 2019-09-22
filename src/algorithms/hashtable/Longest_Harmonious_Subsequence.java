package algorithms.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao on 4/19/2019.
 */
public class Longest_Harmonious_Subsequence {
	
	public static void main(String[] args) {
		int[] f = {1, 3, 2, 2, 5, 2, 3, 7};
		Longest_Harmonious_Subsequence cs = new Longest_Harmonious_Subsequence();
		cs.findLHS_Brute_Force(f);
	}
	
	public int findLHS_Brute_Force(int[] nums) {
		int res = 0;
		for (int i = 0; i < (1 << nums.length); i++) {
			int count = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			for (int j = 0; j < nums.length; j++) {
				if ((i & (1 << j)) != 0) {
					System.out.println(i + ", " + j);
					min = Math.min(min, nums[j]);
					max = Math.max(max, nums[j]);
					count++;
				}
			}
			if (max - min == 1)
				res = Math.max(res, count);
		}
		return res;
	}
	
	public int findLHS_bruteForceImproved(int[] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			int count = 0;
			boolean flag = false;
			for (int j = 0; j < nums.length; j++) {
				if (nums[j] == nums[i])
					count++;
				else if (nums[j] + 1 == nums[i]) {
					count++;
					flag = true;
				}
			}
			if (flag)
				res = Math.max(count, res);
		}
		return res;
	}
	
	public int findLHS_Sort(int[] nums) {
		Arrays.sort(nums);
		int prev_count = 1, res = 0;
		for (int i = 0; i < nums.length; i++) {
			int count = 1;
			if (i > 0 && nums[i] - nums[i - 1] == 1) {
				while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
					count++;
					i++;
				}
				res = Math.max(res, count + prev_count);
				prev_count = count;
			} else {
				while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
					count++;
					i++;
				}
				prev_count = count;
			}
		}
		return res;
	}
	
	public int findLHS_Sol(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
			int x = Math.max(map.getOrDefault(num - 1, 0),
			                 map.getOrDefault(num + 1, 0));
			if (x > 0) {
				max = Math.max(max, map.get(num) + x);
			}
		}
		return max;
	}
}
