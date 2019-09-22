package algorithms.array;

/**
 * @author Yangxiao on 10/24/2018.
 */

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 */
class Rotate_Array {
	
	/**
	 * Brute Force [Time Limit Exceeded]
	 */
	public void rotate1(int[] nums, int k) {
		int len = nums.length;
		for (int i = 0; i < k; i++) {
			int temp = nums[len - 1];
			for (int j = (len - 1); j > 0; j--) {
				nums[j] = nums[j - 1];
			}
			nums[0] = temp;
		}
	}
	
	/**
	 * Using Cyclic Replacements [Accepted]
	 *
	 * https://leetcode.com/problems/rotate-array/solution/
	 */
	public void rotate2(int[] nums, int k) {
		k = k % nums.length;
		int count = 0;
		for (int start = 0; count < nums.length; start++) {
			int current = start;
			int prev = nums[start];
			do {
				int next = (current + k) % nums.length;
				int temp = nums[next];
				nums[next] = prev;
				prev = temp;
				current = next;
				count++;
			} while (start != current);
		}
	}
	
	/**
	 * Using Extra Structures.Array [Accepted]
	 * Time complexity : O(n). One pass is used to put the numbers in the new array.
	 * And another pass to copy the new array to the original one.
	 *
	 * Space complexity : O(n). Another array of the same size is used.
	 */
	public void rotate3(int[] nums, int k) {
		int[] a = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			a[(i + k) % nums.length] = nums[i];
		}
		for (int i = 0; i < nums.length; i++) {
			nums[i] = a[i];
		}
	}
	
	/**
	 * Using Reverse [Accepted]
	 *
	 * Original List                   : 1 2 3 4 5 6 7
	 * After reversing all numbers     : 7 6 5 4 3 2 1
	 * After reversing first k numbers : 5 6 7 4 3 2 1
	 * After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
	 *
	 * Time complexity : O(n)O(n). nn elements are reversed a total of three times.
	 * Space complexity : O(1)O(1). No extra space is used.
	 */
	public void rotate_Sol(int[] nums, int k) {
		k %= nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, nums.length - 1);
	}
	
	public void reverse(int[] nums, int start, int end) {
		while (start < end) {
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}
	
}
