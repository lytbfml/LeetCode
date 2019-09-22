package algorithms.dynamic_programming;

import java.util.Arrays;

/**
 * @author Yangxiao Wang on 5/18/2019
 */
public class Last_Stone_Weight_II {
	
	
	public static void main(String[] args) {
		Last_Stone_Weight_II cs = new Last_Stone_Weight_II();
		int[] x = {2, 7, 4, 1, 8, 1};
		System.out.println(cs.lastStoneWeightII_fas(x));
	}
	
	public int lastStoneWeightII_sol(int[] A) {
		int[] dp = new int[1501];
		dp[0] = 1;
		int sumA = 0, res = 100;
		for (int a : A) {
			sumA += a;
			for (int i = 1500; i >= a; --i)
				dp[i] |= dp[i - a];
		}
		for (int i = 0; i < 1500; ++i)
			res = Math.min(res, Math.abs(sumA - dp[i] * i * 2));
		return res;
	}
	
	public int lastStoneWeightII_Sol2(int[] stones) {
		int[] dp = new int[5000];
		dp[0] = 1;
		int sum = 0;
		for (int s : stones)
			sum += s;
		for (int s : stones) {
			for (int i = sum; i >= 0; i--)
				if (dp[i] > 0)
					dp[i + s] = 1;
		}
		int res = sum;
		for (int i = 0; i <= sum; i++)
			if (dp[i] > 0) {
				res = Math.min(res, Math.abs(sum - i - i));
			}
		return res;
	}
	
	/**
	 * This question eaquals to partition an array into 2 subsets whose difference is minimal
	 * (1) S1 + S2  = S
	 * (2) S1 - S2 = diff
	 * <p>
	 * ==> equation (1) - (2) -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2
	 * <p>
	 * Now we should find the maximum of S2 , range from 0 to S / 2, using dp can solve this
	 * <p>
	 * dp[i][j]   = {true if some subset from 1st to j'th has a sum equal to sum i, false otherwise}
	 * i ranges from (sum of all elements) {1..n}
	 * j ranges from  {1..n}
	 * <p>
	 * same as 494. Target Sum
	 */
	public int lastStoneWeightII_fas(int[] stones) {
		int S = 0, S2 = 0;
		for (int s : stones)
			S += s;
		int n = stones.length;
		boolean[][] dp = new boolean[S + 1][n + 1];
		// Why ? because when s == stones[i - 1], dp[s-stones[i - 1]][i-1] = dp[0][i-1]
		for (int i = 0; i <= n; i++) {
			dp[0][i] = true;
		}
		for (int s = 1; s <= S / 2; s++) {
			for (int i = 1; i <= n; i++) {
				if (dp[s][i - 1] || (s >= stones[i - 1] && dp[s - stones[i - 1]][i - 1])) {
					dp[s][i] = true;
					S2 = Math.max(S2, s);
				}
			}
		}
		
		return S - 2 * S2;
	}
	
	class Solution_fast {
		int res = 0;
		
		public int lastStoneWeightII(int[] stones) {
			int sum = 0;
			Arrays.sort(stones);
			for (int i = 0; i < stones.length; i++) {
				sum += stones[i];
				stones[i] *= 2;
			}
			res = sum;
			dfs(stones, 0, sum);
			return res;
		}
		
		public void dfs(int[] stones, int index, int sum) {
			if (res == 0 || res == 1)
				return;
			if (index == stones.length || sum < stones[index]) {
				res = Math.min(sum, res);
				return;
			}
			for (int i = index; i < stones.length; i++) {
				if (sum >= stones[i]) {
					dfs(stones, i + 1, sum - stones[i]);
				} else {
					break;
				}
			}
		}
	}
}
