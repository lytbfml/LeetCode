package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 4/15/2019.
 */
public class Jump_Game {
	public boolean canJump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int n = nums.length;
		int next = 0;
		for (int i = 0; i < n; i++) {
			next = Math.max(next, i + nums[i]);
			if (next == i && i != n - 1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean canJump_back(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int n = nums.length;
		int next = n - 1;
		for (int i = n - 2; i >= 0; i--) {
			if (i + nums[i] >= next) {
				next = i;
			}
		}
		return next == 0;
	}
	
	public boolean canJump_dp(int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int n = nums.length;
		int[] dp = new int[n];
		dp[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			int maxDis = Math.min(n - 1, i + nums[i]);
			for (int j = i + 1; j <= maxDis; j++) {
				if (dp[j] == 1) {
					dp[i] = 1;
					break;
				}
			}
		}
		
		return dp[0] == 1;
	}
}
