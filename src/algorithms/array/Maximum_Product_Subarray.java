package algorithms.array;

/**
 * @author Yangxiao on 11/1/2018.
 */

class Maximum_Product_Subarray {
	
	public int maxProduct(int[] nums) {
		if (nums.length <= 0)
			return 0;
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int maxPod = dp[0];
		int left = 0;
		for (int i = 1; i < nums.length; i++) {
			dp[i] = dp[i - 1] * nums[i];
			if (dp[i] > 0) {
				maxPod = Math.max(dp[i], maxPod);
			} else if (dp[i] < 0) {
				int temp = dp[i] / dp[left];
				if (temp < 0 && nums[i] < 0) {
					left = i;
				} else if (temp > 0) {
					maxPod = Math.max(temp, maxPod);
				}
			} else {
				dp[i] = nums[i];
				maxPod = Math.max(dp[i], maxPod);
				if (nums[i] == 0) {
					left = i + 1;
				} else {
					left = i;
				}
			}
		}
		
		return maxPod;
	}
	
	public int maxProduct_Sol1(int[] nums) {
		int max = nums[0];
		int min = nums[0];
		int answer = max;
		for (int i = 1; i < nums.length; i++) {
			int current = nums[i];
			if (current >= 0) {
				max = Math.max(current, current * max);
				min = Math.min(current, current * min);
			} else if (current < 0) {
				int lastMax = max;
				max = Math.max(current, current * min);
				min = Math.min(current, current * lastMax);
			}
			answer = Math.max(answer, max);
		}
		return answer;
	}
	
	public int maxProduct_Sol2(int[] nums) {
		int maxForward = Integer.MIN_VALUE;
		int maxBackWard = Integer.MIN_VALUE;
		int runningMax = 1;
		
		//Forward
		for(int i = 0; i < nums.length; i++) {
			runningMax *= nums[i];
			if(runningMax > maxForward) {
				maxForward = runningMax;
			}
			if(runningMax == 0) {
				runningMax = 1;
			}
		}
		
		runningMax = 1;
		//BackWard
		for(int i = (nums.length - 1); i >= 0; i--) {
			runningMax *= nums[i];
			if(runningMax > maxBackWard) {
				maxBackWard = runningMax;
			}
			if(runningMax == 0) {
				runningMax = 1;
			}
		}
		
		return Math.max(maxForward, maxBackWard);
	}
	
}
