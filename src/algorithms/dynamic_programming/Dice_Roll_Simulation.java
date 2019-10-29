package algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao on 10/12/2019.
 */
public class Dice_Roll_Simulation {
	
	
	class Solution_dfs_Slow {
		long[][][] cache = new long[5002][16][6];
		int mod = (int) 1e9 + 7;
		
		public int dieSimulator(int n, int[] rollMax) {
			
			for (int i = 0; i < 5002; i++) {
				for (int j = 0; j < 16; j++) {
					for (int k = 0; k < 6; k++) {
						cache[i][j][k] = -1;
					}
				}
			}
			return (int) dfs(0, 0, n, rollMax);
		}
		
		private long dfs(int k, int index, int n, int[] rollMax) {
			if (n == 0) {
				return 1;
			}
			if (cache[n][k][index] != -1) {
				return cache[n][k][index];
			}
			
			long ans = 0;
			for (int i = 0; i < 6; i++) {
				if (index != i) {
					ans += dfs(1, i, n - 1, rollMax);
				} else if (k < rollMax[i]) {
					ans += dfs(k + 1, i, n - 1, rollMax);
				}
			}
			cache[n][k][index] = ans % mod;
			return cache[n][k][index];
		}
	}
	
	class Solution {
		public int dieSimulator(int n, int[] rollMax) {
			int mod = (int) 1e9 + 7;
			if (n == 1) {
				return rollMax.length;
			}
			
			long[][] dp = new long[n + 1][rollMax.length + 1]; // numbers end with current value.
			for (int i = 0; i < rollMax.length; ++i) {
				dp[1][i] = 1;
			}
			
			dp[0][rollMax.length] = 1; // total number of combinations with i number of rolls
			dp[1][rollMax.length] = rollMax.length; // total number with 1 roll;
			
			long total = 0;
			long totalEndWithCurNum = 0;
			for (int i = 2; i <= n; ++i) {
				total = 0;
				for (int j = 0; j < rollMax.length; ++j) {
					totalEndWithCurNum = 0;
					for (int k = 1; k <= rollMax[j] && i >= k; ++k) {
						// based on max allowed occurrence to calculate the possible combinations end with j + 1
						totalEndWithCurNum += (dp[i - k][rollMax.length] - dp[i - k][j] + mod) % mod;
						totalEndWithCurNum %= mod;
					}
					
					total += totalEndWithCurNum;
					total %= mod;
					dp[i][j] = totalEndWithCurNum;
				}
				
				dp[i][rollMax.length] = total;
			}
			
			return (int) total;
		}
	}
	
	class Solution2 {
		public int dieSimulator(int n, int[] rollMax) {
			long divisor = (long) Math.pow(10, 9) + 7;
			long[][] dp = new long[n][7];
			for (int i = 0; i < 6; i++) {
				dp[0][i] = 1;
			}
			dp[0][6] = 6;
			for (int i = 1; i < n; i++) {
				long sum = 0;
				for (int j = 0; j < 6; j++) {
					dp[i][j] = dp[i - 1][6];
					if (i - rollMax[j] < 0) {
						sum = (sum + dp[i][j]) % divisor;
					} else {
						if (i - rollMax[j] - 1 >= 0) dp[i][j] =
								(dp[i][j] - (dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j])) % divisor +
										divisor;
						else dp[i][j] = (dp[i][j] - 1) % divisor;
						sum = (sum + dp[i][j]) % divisor;
					}
					
				}
				dp[i][6] = sum;
			}
			return (int) (dp[n - 1][6]);
		}
		
	}
}
