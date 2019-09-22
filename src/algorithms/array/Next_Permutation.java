package algorithms.array;

/**
 * @author Yangxiao Wang on 9/9/2019
 */
public class Next_Permutation {
	
	class Solution {
		public void nextPermutation(int[] nums) {
			int n = nums.length;
			if (n == 0 || n == 1)
				return;
			int idx = -1;
			for (int i = n - 2; i >= 0; i--) {
				if (nums[i] < nums[i + 1]) {
					idx = i;
					break;
				}
			}
			
			if (idx >= 0) {
				int i = n - 1;
				while (i >= 0 && nums[i] <= nums[idx]) {
					i--;
				}
				swap(nums, i, idx);
			}
			
			reverse(nums, idx + 1, n - 1);
		}
		
		private void reverse(int[] nums, int i, int j) {
			while (i < j) {
				swap(nums, i, j);
				i++;
				j--;
			}
		}
		
		private void swap(int[] nums, int i, int j) {
			int tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
		}
	}
}

// 123 132 213 231 312 321
// 1234 1243 1324 1342 1423 1432
// 2134 2143
// 3421
// 4321
