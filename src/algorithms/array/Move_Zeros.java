package algorithms.array;

/**
 * @author Yangxiao on 10/24/2018.
 */

/**
 * Given an array nums, write a function to move all 0's
 * to the end of it while maintaining the relative order of the non-zero elements.
 */
class Move_Zeros {
	
	public void moveZeroes(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				if (i == nums.length - count || i == nums.length - count - 1)
					return;
				for (int j = i + 1; j < nums.length - count; j++) {
					nums[j - 1] = nums[j];
				}
				nums[nums.length - 1 - count] = 0;
				count++;
				i--;
			}
		}
	}
	
	
	public void moveZeroes_Sol(int[] nums) {
		for (int i = 0, writeIndx = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[writeIndx++] = nums[i];
				if (i + 1 != writeIndx)
					nums[i] = 0;
			}
		}
	}
}
