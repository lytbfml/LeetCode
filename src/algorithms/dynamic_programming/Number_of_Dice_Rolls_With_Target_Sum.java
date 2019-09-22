package algorithms.dynamic_programming;

/**
 * contest 149
 * @author Yangxiao Wang on 8/10/2019
 */
public class Number_of_Dice_Rolls_With_Target_Sum {
	
	int mod = 1000000007;
	
	public int numRollsToTarget(int d, int f, int target) {
		int total = d * f;
		if (target > (total)) return 0;
		if (target == total) return 1;
		if (d == 1) return 1;
		if (d > target) return 1;
		int[][] dp = new int[d + 1][target + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= d; i++) {
			for (int j = 1; j <= f; j++) {
				for (int k = j; k <= target; k++) {
					dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % mod;
				}
			}
		}
		return dp[d][target] % mod;
	}
	
	public int numRollsToTarget_dp2(int d, int f, int target) {
		int MOD = 1000000007;
		int[][] dp = new int[d + 1][target + 1];
		dp[0][0] = 1;
		//how many possibility can i dices sum up to j;
		for (int i = 1; i <= d; i++) {
			for (int j = 1; j <= target; j++) {
				if (j > i * f) {
					continue;           //If j is larger than largest possible sum of i dices, there is no possible ways.
				} else {                //watch out this condition, or NPE
					for (int k = 1; k <= f && k <= j; k++) {
						dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
					}
				}
			}
		}
		return dp[d][target];
	}
}
