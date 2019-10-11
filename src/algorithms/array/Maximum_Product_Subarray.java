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
			} else {
				int lastMax = max;
				max = Math.max(current, current * min);
				min = Math.min(current, current * lastMax);
			}
			answer = Math.max(answer, max);
		}
		return answer;
	}
	
	public int maxProductSol2(int[] nums) {
		int max_product_so_far = Integer.MIN_VALUE;
		int curr_product = 1;
		for (int i = 0; i < nums.length; i++) {
			curr_product *= nums[i];
			if (curr_product > max_product_so_far) {
				max_product_so_far = curr_product;
			}
			if (curr_product == 0) {
				curr_product = 1;
			}
		}
		
		curr_product = 1; // reset
		for (int i = nums.length - 1; i >= 0; i--) {
			curr_product *= nums[i];
			if (curr_product > max_product_so_far) {
				max_product_so_far = curr_product;
			}
			if (curr_product == 0) {
				curr_product = 1;
			}
		}
		
		return max_product_so_far;
	}
	
}
