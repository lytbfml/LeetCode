package algorithms.dynamic_programming; /**
 * @author Yangxiao on 10/25/2018.
 */


/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 */
class Maximum_Subarray {
	
	public static void main(String[] args) {
		Maximum_Subarray ms = new Maximum_Subarray();
		int[] nums = {-4, -1, -2, -1};
		System.out.println(ms.maxSubArray(nums));
		System.out.println(ms.maxSubArray_Sol2(nums));
		;
	}
	
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int sumCur = 0;
		int sumAll = sumCur;
		for (int i = 0; i < n; i++) {
			if (nums[i] + sumCur > 0) {
				sumCur += nums[i];
			} else {
				sumCur = 0;
			}
			sumAll = Math.max(sumCur, sumAll);
		}
		
		if (sumAll <= 0) {
			int large = Integer.MIN_VALUE;
			
			for (int i = 0; i < n; i++) {
				large = large < nums[i] ? nums[i] : large;
			}
			return large;
		}
		return sumAll;
	}
	
	
	//-2,1,-3,4,-1,2,1,-5,4
	//4,-1,2,1
	public int maxSubArray_Sol1(int[] nums) {
		int result = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = Math.max(nums[i] + sum, nums[i]);
			result = Math.max(result, sum);
		}
		return result;
	}
	
	//DP Solution
	public int maxSubArray_Sol2(int[] A) {
		int n = A.length;
		int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
		dp[0] = A[0];
		int max = dp[0];
		
		for (int i = 1; i < n; i++) {
			dp[i] = A[i] + (Math.max(dp[i - 1], 0));
			max = Math.max(max, dp[i]);
		}
		
		return max;
	}
	
	//Fast
	public int maxSubArray_Sol3(int[] nums) {
		
		int maxSum = Integer.MIN_VALUE;
		int currentSum = 0;
		for (int i = 0; i < nums.length; i++) {
			currentSum += nums[i];
			if (currentSum > maxSum) {
				maxSum = currentSum;
			}
			if (currentSum < 0) {
				currentSum = 0;
			}
		}
		return maxSum;
	}
	
	//keep tracking the max subarray sum that ends with index i
	//Time O(N), Space O(1)
	
	//	int maxSubArray(int* nums, int numsSize) {
	//		if (!nums) return 0;
	//		if(numsSize==1) return nums[0];
	//
	//		int prev = nums[0];
	//		int curr = prev;
	//
	//		int i;
	//		int max=prev;
	//
	//		for(i=1;i<numsSize;i++){
	//			curr = nums[i] + (prev<0?0:prev);
	//			if(curr>max) max = curr;
	//			prev = curr;
	//		}
	//		return max;
	//	}
}
