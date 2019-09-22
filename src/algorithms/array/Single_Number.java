package algorithms.array;

/**
 * @author Yangxiao on 10/24/2018.
 */


import java.util.Arrays;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
class Single_Number {
	
	public int singleNumber(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i += 2) {
			if (nums[i] != nums[i - 1]) {
				return nums[i - 1];
			}
		}
		
		return nums[nums.length - 1];
	}
	
	
	public int singleNumber_Sol(int[] nums) {
		int r = 0;
		for (int n : nums)
			r ^= n;
		return r;
	}
}
