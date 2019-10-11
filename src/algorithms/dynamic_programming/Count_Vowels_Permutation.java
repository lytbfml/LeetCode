package algorithms.dynamic_programming;

import java.util.Arrays;

/**
 * @author Yangxiao on 10/7/2019.
 */
public class Count_Vowels_Permutation {
	
	class Solution {
		public int countVowelPermutation(int n) {
			int mod = (int) 1e9 + 7;
			long[][] dp = new long[n][5];
			Arrays.fill(dp[0], 1);
			for (int i = 1; i < n; i++) {
				dp[i][0] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]) % mod;
				dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
				dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % mod;
				dp[i][3] = dp[i - 1][2] % mod;
				dp[i][4] = (dp[i - 1][2] + dp[i - 1][3]) % mod;
			}
			int res = 0;
			for (int i = 0; i < 5; i++) {
				res = (int) (res + dp[n - 1][i]) % mod;
			}
			return res;
		}
	}
	
	class Solution_Fast {
		static final long M = 1_000_000_000 + 7;
		
		public int countVowelPermutation(int n) {
			if (n == 1) return 5;
			final long[][] m = new long[][]{
					{0, 1, 0, 0, 0},
					{1, 0, 1, 0, 0},
					{1, 1, 0, 1, 1},
					{0, 0, 1, 0, 1},
					{1, 0, 0, 0, 0}
			};
			long[][] z = power(m, n - 1);
			long result = 0;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					result = (result + z[i][j]) % M;
				}
			}
			return (int) result;
		}
		
		long[][] power(final long[][] A, int n) {
			long[][] origin = A.clone();
			long[][] ans = new long[5][5];
			for (int i = 0; i < 5; i++) ans[i][i] = 1;
			while (n != 0) {
				if ((n & 1) == 1) {
					ans = mul(ans, origin);
				}
				origin = mul(origin, origin);
				n /= 2;
			}
			return ans;
		}
		
		long[][] mul(long[][] A, long[][] B) {
			long[][] z = new long[5][5];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					for (int t = 0; t < 5; t++) {
						z[i][j] = (z[i][j] + A[i][t] * B[t][j]) % M;
					}
				}
			}
			return z;
		}
		
	}
	
}
