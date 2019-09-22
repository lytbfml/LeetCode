package algorithms.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao on 4/16/2019.
 */
public class Coin_Change {
	
	Map<Integer, Integer> amountDict = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		Coin_Change cs = new Coin_Change();
		int[] c = {1, 2, 5};
		int[] a = {2, 5};
		int[] d = {186, 419, 83, 408};
		System.out.println(cs.coinChange(c, 11));
		System.out.println(cs.coinChange(a, 10));
		// System.out.println(cs.coinChange(d, 6249));
	}
	
	public int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0 && amount != 0) {
			return -1;
		} else if (coins.length == 0) {
			return 0;
		}
		int[] dp = new int[amount + 1];
		int sum = 0;
		while (++sum <= amount) {
			int min = -1;
			for (int coin : coins) {
				if (sum >= coin && dp[sum - coin] != -1) {
					int cnt = dp[sum - coin] + 1;
					min = min < 0 ? cnt : Math.min(cnt, min);
				}
			}
			dp[sum] = min;
		}
		return dp[amount];
	}
	
	public int coinChange_2(int[] coins, int amount) {
		int n = coins.length;
		int[] dp = new int[amount + 1];
		
		for (int i = 1; i <= amount; i++) {
			int num = -1;
			for (int j = 0; j < n; j++) {
				if (i >= coins[j] && dp[i - coins[j]] != -1) {
					int cnt = dp[i - coins[j]] + 1;
					num = num < 0 ? cnt : Math.min(cnt, num);
				}
			}
			dp[i] = num;
		}
		
		return dp[amount];
	}
	
	public int coinChange_recursive(int[] coins, int amount) {
		if (amount < 1) { return 0; }
		return helper(coins, amount, new int[amount]);
	}
	
	private int helper(int[] coins, int rem, int[] count) {
		// rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
		if (rem < 0) {
			return -1; // not valid
		}
		if (rem == 0) {
			return 0; // completed
		}
		if (count[rem - 1] != 0) {
			return count[rem - 1]; // already computed, so reuse
		}
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = helper(coins, rem - coin, count);
			if (res >= 0 && res < min) { min = 1 + res; }
		}
		count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
		return count[rem - 1];
	}
	
	public int coinChange_recur2(int[] coins, int amount) {
		if (amount == 0) { return 0; }
		int n = amount + 1;
		for (int coin : coins) {
			int curr = 0;
			if (amount >= coin) {
				int next = coinChange(coins, amount - coin);
				if (next >= 0) { curr = 1 + next; }
			}
			if (curr > 0) { n = Math.min(n, curr); }
		}
		int finalCount = (n == amount + 1) ? -1 : n;
		return finalCount;
	}
	
	public int coinChange_rec2Dp(int[] coins, int amount) {
		if (amount == 0) { return 0; }
		if (amountDict.containsKey(amount)) { return amountDict.get(amount); }
		int n = amount + 1;
		for (int coin : coins) {
			int curr = 0;
			if (amount >= coin) {
				int next = coinChange(coins, amount - coin);
				if (next >= 0) { curr = 1 + next; }
			}
			if (curr > 0) { n = Math.min(n, curr); }
		}
		int finalCount = (n == amount + 1) ? -1 : n;
		amountDict.put(amount, finalCount);
		return finalCount;
	}
	
	
	public int coinChange_100Fast(int[] coins, int amount) {
		if (amount < 0 || coins == null) return -1;
		if (amount == 0) return 0;
		if (coins.length == 0) return -1;
		
		Arrays.sort(coins);
		int [] ret = new int[1];
		ret[0] = amount+1;
		_coinChange(coins, amount, coins.length-1, 0, ret);
		return ret[0] == amount+1 ? -1 : ret[0];
	}
	
	private void _coinChange(int[] coins, int amount, int start, int coinNum, int[] ret) {
		if (amount % coins[start] == 0) {
			ret[0] = Math.min(ret[0], coinNum + amount/coins[start]);
			return;
		}
		if (start != 0) {
			for (int i = amount / coins[start]; i >= 0; i--) {
				if (coinNum + i >= ret[0] - 1) break;
				_coinChange(coins, amount - i * coins[start], start - 1, coinNum + i, ret);
			}
		}
	}
}
