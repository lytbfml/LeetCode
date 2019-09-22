package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 3/24/2019.
 */
public class House_Robber {
	
	public static void main(String[] args) {
		House_Robber cs = new House_Robber();
		int[] n = {1, 10, 1, 10, 1, 10, 1, 1, 20};
		System.out.println(cs.robSimp(n) + ", ");
	}
	
	// 1 1 1 1 20 | 10 10 10 1
	// 1 10 1 10 1 10 1 1 20
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int n = nums.length;
		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = nums[1];
		dp[2] = nums[2] + nums[0];
		for (int i = 3; i < n; i++) {
			int temp1 = nums[i] + dp[i - 2];
			int temp2 = nums[i] + dp[i - 3];
			int temp = Math.max(temp1, temp2);
			dp[i] = temp;
		}
		return Math.max(dp[n - 1], dp[n - 2]);
	}
	
	public int robSimp(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = nums[0];
		for (int i = 1; i < n; i++) {
			dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
		}
		return dp[n];
	}
	
	public int rob2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int temp1 = 0, temp2 = 0;
		for (int i = 0; i < n; i++) {
			int temp = temp1;
			temp1 = Math.max(nums[i] + temp2, temp1);
			temp2 = temp;
		}
		
		return temp1;
	}
	
	public int rob_Sol2_Recursive(int[] nums) {
		return rob(nums, nums.length - 1);
	}
	
	private int rob(int[] nums, int i) {
		if (i < 0) {
			return 0;
		}
		return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
	}
}
