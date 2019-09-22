package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 2019-08-15
 */
public class Coin_Change_2 {
	
	public static void main(String[] args) {
		Coin_Change_2 cs = new Coin_Change_2();
		System.out.println(cs.change(0, new int[]{1, 2, 5}));
	}
	
	public int change(int amount, int[] coins) {
		if (amount == 0) return 1;
		if (coins == null || coins.length == 0) return 0;
		int n = coins.length;
		int[][] dp = new int[amount + 1][n];
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < n; j++) {
				dp[0][j] = 1;
				for (int k = 0; k <= j; k++) {
					if (i < coins[k]) continue;
					dp[i][j] += dp[i - coins[k]][k];
				}
			}
		}
		return dp[amount][n - 1];
	}
	
	
	public int change_2(int amount, int[] coins) {
		if (amount == 0) return 1;
		if (coins == null || coins.length == 0) return 0;
		int n = coins.length;
		int[][] dp = new int[n + 1][amount + 1];
		dp[0][0] = 1;
		// dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= amount; j++) {
				dp[i][j] = dp[i - 1][j] + (j < coins[i - 1] ? 0 : dp[i][j - coins[i - 1]]);
			}
		}
		return dp[n][amount];
	}
	
	
	public int change_3(int amount, int[] coins) {
		if (amount == 0) return 1;
		if (coins == null || coins.length == 0) return 0;
		int n = coins.length;
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = coins[i]; j <= amount; j++) {
				dp[j] += dp[j - coins[i]];
			}
		}
		return dp[amount];
	}
}
