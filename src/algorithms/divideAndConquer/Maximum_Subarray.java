package algorithms.divideAndConquer;


/**
 * @author Yangxiao on 10/25/2018.
 * <p>
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum
 * * and return its sum. this is divide and conquer approach
 */
class Maximum_Subarray {
	
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		return helper(nums, 0, nums.length - 1);
	}
	
	private int helper(int[] a, int i, int j) {
		if (i == j) return a[i];
		int mid = i + (j - i) / 2;
		int sumL = helper(a, i, mid);
		int sumR = helper(a, mid + 1, j);
		int left = a[mid];
		int right = a[mid + 1];
		int sum = 0;
		for (int k = mid; k >= 0; k--) {
			sum += a[k];
			if (sum > left) left = sum;
		}
		sum = 0;
		for (int k = mid + 1; k < a.length; k++) {
			sum += a[k];
			if (sum > right) right = sum;
		}
		
		return Math.max(left + right, Math.max(sumL, sumR));
	}
}
