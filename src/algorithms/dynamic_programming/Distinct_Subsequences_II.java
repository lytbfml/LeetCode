package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 11/10/2018.
 */

import java.util.Arrays;

/**
 * Given a string S, count the number of distinct, non-empty subsequences of S .
 * <p>
 * Since the result may be large, return the answer modulo 10^9 + 7.
 */
class Distinct_Subsequences_II {
	
	
	public int distinctSubseqII(String S) {
		int mod = 1000000007;
		int n = S.length();
		int[] record = new int[26];
		Arrays.fill(record, -1);
		int[] dp = new int[n + 1];
		dp[0] = 1;
		
		for (int i = 0; i < n; i++) {
			int ch = S.charAt(i) - 'a';
			dp[i + 1] = 2 * dp[i] % mod;
			if (record[ch] >= 0) {
				dp[i + 1] -= dp[record[ch]];
			}
			dp[i + 1] %= mod;
			record[ch] = i;
		}
		
		dp[n]--;
		if (dp[n] < 0) { dp[n] += mod; }
		
		return dp[n];
	}
	
	
	public int distinctSubseqII_Sol1(String S) {
		int MOD = 1_000_000_007;
		int N = S.length();
		int[] dp = new int[N + 1];
		dp[0] = 1;
		
		int[] last = new int[26];
		Arrays.fill(last, -1);
		
		for (int i = 0; i < N; ++i) {
			int x = S.charAt(i) - 'a';
			dp[i + 1] = dp[i] * 2 % MOD;
			if (last[x] >= 0) { dp[i + 1] -= dp[last[x]]; }
			dp[i + 1] %= MOD;
			last[x] = i;
		}
		
		dp[N]--;
		if (dp[N] < 0) { dp[N] += MOD; }
		return dp[N];
	}
	
	public int distinctSubseqII_Sol2(String S) {
		return distinctSubsequence(S.toCharArray(), 1000000007);
	}
	
	public int distinctSubsequence(char[] a, int mod) {
		int n = a.length;
		int[] bucket = new int[26];
		Arrays.fill(bucket, -1);
		
		int cum = 0;
		for (int i = 0; i < n; i++) {
			int v = cum;
			int ind = a[i] - 'a';
			if (bucket[ind] == -1) {
				v++;
			} else {
				v += mod - bucket[ind];
			}
			if (v >= mod) { v -= mod; }
			bucket[ind] = cum;
			cum += v;
			if (cum >= mod) { cum -= mod; }
		}
		return cum;
	}
	
	public int distinctSubseqII_Sol3(String S) {
		if (S.length() == 0) { return 0; }
		int[] dp = new int[S.length() + 1];
		dp[0] = 1;
		int M = 1000000007;
		for (int i = 1; i <= S.length(); i++) {
			long sum = 0;
			for (int j = i - 1; j >= 0; j--) {
				sum += dp[j];
				if (j == 0 || S.charAt(i - 1) == S.charAt(j - 1)) { break; }
			}
			dp[i] = (int) (sum % M);
		}
		int res = 0;
		for (int i = 1; i <= S.length(); i++) {
			res = (res + dp[i]) % M;
		}
		return res;
	}
}
