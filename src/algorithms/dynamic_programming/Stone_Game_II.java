package algorithms.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 7/27/2019
 */
public class Stone_Game_II {
	
	public int stoneGameII(int[] piles) {
		if (piles == null || piles.length == 0) return 0;
		int n = piles.length;
		int[] acc = new int[n];
		acc[n - 1] = piles[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			acc[i] = piles[i] + acc[i + 1];
		}
		int[][] dp = new int[n][n];
		return helper(acc, dp, 0, 1);
	}
	
	private int helper(int[] acc, int[][] hash, int i, int M) {
		if (i == acc.length) return 0;
		if (2 * M + i >= acc.length) return acc[i];
		if (hash[i][M] != 0) return hash[i][M];
		int min = Integer.MAX_VALUE;
		for (int x = 1; x <= 2 * M; x++) {
			min = Math.min(min, helper(acc, hash, x + i, Math.max(x, M)));
		}
		hash[i][M] = acc[i] - min;
		return hash[i][M];
	}
	
	
	public int stoneGameII_sol(int[] piles) {
		for (int i = 1; i < piles.length; i++)
			piles[i] = piles[i - 1] + piles[i];
		Map<String, Integer> cache = new HashMap<>();
		return dfs(1, 0, piles, cache);
	}
	
	int dfs(int M, int i, int[] piles, Map<String, Integer> cache) {
		if (piles.length <= i) return 0;
		if (cache.containsKey(M + " " + i)) return cache.get(M + " " + i);
		int max = -1;
		int preSum = i - 1 < 0 ? 0 : piles[i - 1];
		for (int k = 0; k <= 2 * M - 1; k++) {
			if (k + i == piles.length) break;
			int next = dfs(Math.max(k + 1, M), i + k + 1, piles, cache);
			int count = next + piles[k + i] - preSum;
			if (max < count) {
				max = count;
			}
		}
		int nextValue = piles[piles.length - 1] - preSum - max;
		cache.put(M + " " + i, nextValue);
		if (i == 0) return max;
		return nextValue;
	}
}
