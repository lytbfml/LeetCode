package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 2019-08-20
 */
public class Profitable_Schemes {
	
	
	public int profitableSchemes(int G, int P, int[] group, int[] profit) {
		int n = group.length, res = 0, mod = 1000000007;
		int[][][] dp = new int[n + 1][G + 1][P + 1];
		dp[0][0][0] = 1;
		for (int k = 1; k <= n; k++) {
			int g = group[k - 1], p = profit[k - 1];
			for (int i = 0; i <= G; i++) {
				for (int j = 0; j <= P; j++) {
					dp[k][i][j] = dp[k - 1][i][j];
					if (i >= g) {
						dp[k][i][j] = (dp[k][i][j] + dp[k - 1][i - g][Math.max(j - p, 0)]) % mod;
					}
				}
			}
		}
		for (int i = 0; i <= G; i++) res = (res + dp[n][i][P]) % mod;
		return res;
	}
	
	public int profitableSchemes_2d(int G, int P, int[] group, int[] profit) {
		int n = group.length, res = 0, mod = 1000000007;
		int[][] dp = new int[G + 1][P + 1];
		dp[0][0] = 1;
		for (int k = 0; k < n; k++) {
			int g = group[k], p = profit[k];
			for (int i = G; i >= g; i--) {
				for (int j = P; j >= 0; j--) {
					dp[i][j] = (dp[i][j] + dp[i - g][Math.max(j - p, 0)]) % mod;
				}
			}
		}
		for (int i = 0; i <= G; i++) res = (res + dp[i][P]) % mod;
		return res;
	}
	
	public int profitableSchemes_2d2(int G, int P, int[] group, int[] profit) {
		int n = group.length, res = 0, mod = 1000000007;
		int[][] dp = new int[G + 1][P + 1];
		dp[0][0] = 1;
		for (int k = 0; k < n; k++) {
			int g = group[k], p = profit[k];
			for (int i = G - g; i >= 0; i--) {
				for (int j = P; j >= 0; j--) {
					dp[i + g][Math.min(P, j + p)] = (dp[i][j] + dp[i + g][Math.min(P, j + p)]) % mod;
				}
			}
		}
		for (int i = 0; i <= G; i++) res = (res + dp[i][P]) % mod;
		return res;
	}
	
	/**
	 * d[k][g][v]: means:
	 * the number of schemes by
	 * using 1, 2, .., k plan given g persons to get more than v profits
	 * the result will be d[K][G][P]
	 * <p>
	 * due to some plan's profit is 0, we need to consider 3 cases .
	 */
	class Solution_clear {
		public int profitableSchemes(int G, int P, int[] group, int[] profit) {
			int K = group.length;
			int V = P;
			int MOD = 1_000_000_007;
			
			//  given g person,  create more the v profit
			int[][][] d = new int[K + 1][G + 1][V + 1];
			for (int k = 1; k <= K; ++k) {
				for (int g = 1; g <= G; ++g) {
					int need_person = group[k - 1];
					int get_value = profit[k - 1];
					for (int v = 0; v <= V; ++v) {
						d[k][g][v] = 0;
						// case 0, only use plan[k]
						if (v <= get_value && g >= need_person) {
							d[k][g][v] += 1;
						}
						
						// case 1: not use plan[k]
						d[k][g][v] += (k < 1 ? 0 : d[k - 1][g][v]) % MOD;
						
						// case 2: use plan[k] and use plan before
						if (g > need_person) {
							d[k][g][v] += (k < 1 ? 0 : d[k - 1][g - need_person][Math.max(0, v - get_value)]) % MOD;
						}
						d[k][g][v] %= MOD;
					}
				}
			}
			int sum = d[K][G][P];
			return sum;
		}
	}
	
	class Solution_mem {
		static final int MOD = (int) 1e9 + 7;
		
		public int profitableSchemes(int G, int P, int[] group, int[] profit) {
			int len = group.length;
			Integer[][][] memo = new Integer[len][G + 1][P + 1];
			return dfs(0, G, P, group, profit, memo);
		}
		
		private int dfs(int idx, int G, int P, int[] group, int[] profit, Integer[][][] memo) {
			if (idx == group.length) return 0;
			int actP = Math.max(P, 0);
			if (memo[idx][G][actP] != null) return memo[idx][G][actP];
			int res = 0;
			if (G >= group[idx]) {
				if (P - profit[idx] <= 0) res++;
				res += dfs(idx + 1, G - group[idx], P - profit[idx], group, profit, memo);
				res %= MOD;
			}
			res += dfs(idx + 1, G, P, group, profit, memo);
			res %= MOD;
			memo[idx][G][actP] = res;
			return res;
		}
	}
}
