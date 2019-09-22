package algorithms.dynamic_programming;

import java.util.Arrays;

/**
 * @author Yangxiao Wang on 5/20/2019
 */
public class Target_Sum {
	
	public static void main(String[] args) {
		Target_Sum cs = new Target_Sum();
		int[] s = {1, 1};
		int[] s2 = {1, 1, 1, 1, 1};
		System.out.println(Solution_bruteForce.findTargetSumWays(s2, 3));
	}
	
	static class Solution_bruteForce {
		static int res = 0;
		static int target = 0;
		
		public static int findTargetSumWays(int[] nums, int S) {
			if (nums.length == 0)
				return 0;
			target = S;
			
			helper(nums, 0, 0);
			return res;
		}
		
		
		private static void helper(int[] nums, int index, int cur) {
			if (index == nums.length && cur == target) {
				res++;
				return;
			}
			if (index >= nums.length)
				return;
			
			helper(nums, index + 1, cur + nums[index]);
			helper(nums, index + 1, cur - nums[index]);
		}
	}
	
	class Solution_Mem {
		int res = 0;
		int target = 0;
		
		public int findTargetSumWays(int[] nums, int S) {
			if (nums.length == 0)
				return 0;
			target = S;
			int[][] mem = new int[nums.length][2001];
			for (int[] row : mem)
				Arrays.fill(row, Integer.MIN_VALUE);
			helper(nums, 0, 0, mem);
			return res;
		}
		
		
		private int helper(int[] nums, int index, int cur, int[][] mem) {
			if (index == nums.length && cur == target) {
				return 1;
			}
			if (index >= nums.length)
				return 0;
			
			if (mem[index][cur + 1000] != Integer.MIN_VALUE) {
				return mem[index][cur + 1000];
			}
			int add = helper(nums, index + 1, cur + nums[index], mem);
			int minus = helper(nums, index + 1, cur - nums[index], mem);
			mem[index][cur + 1000] = add + minus;
			return mem[index][cur + 1000];
		}
	}
	
	class Solution_DP {
		public int findTargetSumWays(int[] nums, int s) {
			int sum = 0;
			for (int i : nums)
				sum += i;
			if (s > sum || s < -sum)
				return 0;
			int[] dp = new int[2 * sum + 1];
			dp[sum] = 1;
			
			for (int i = 0; i < nums.length; i++) {
				int[] next = new int[2 * sum + 1];
				for (int k = 0; k < 2 * sum + 1; k++) {
					if (dp[k] != 0) {
						next[k + nums[i]] += dp[k];
						next[k - nums[i]] += dp[k];
					}
				}
				dp = next;
			}
			return dp[sum + s];
		}
		
	}
	
	/**
	 * https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C%2B%2B-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
	 * The recursive solution is very slow, because its runtime is exponential
	 * <p>
	 * The original problem statement is equivalent to:
	 * Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
	 * <p>
	 * Let P be the positive subset and N be the negative subset
	 * For example:
	 * Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
	 * Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
	 * <p>
	 * Then let's see how this can be converted to a subset sum problem:
	 * <p>
	 * (1) sum(P) - sum(N) = target
	 * (2) sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
	 * 2 * sum(P) = target + sum(nums)
	 * So the original problem has been converted to a subset sum problem as follows:
	 * Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
	 * <p>
	 * Note that the above formula has proved that target + sum(nums) must be even
	 * We can use that fact to quickly identify inputs that do not have a solution (Thanks to @BrunoDeNadaiSarnaglia for the suggestion)
	 * For detailed explanation on how to solve subset sum problem, you may refer to Partition Equal Subset Sum
	 * <p>
	 * Here is Java solution (15 ms)
	 */
	class Solution_SubsetSum {
		
		public int findTargetSumWays_fast(int[] nums, int S) {
			if (nums.length == 0)
				return S == 0 ? 1 : 0;
			int sum = 0;
			for (int i : nums)
				sum += i;
			if ((sum - S) % 2 != 0)
				return 0;
			int t = (sum - S) / 2;
			if (t < 0)
				return 0;
			int[] dp = new int[t + 1];
			dp[0] = 1;
			for (int num : nums) {
				for (int i = t; i >= num; i--) {
					dp[i] += dp[i - num];
				}
			}
			return dp[t];
		}
		
		public int findTargetSumWays(int[] nums, int s) {
			int sum = 0;
			for (int n : nums)
				sum += n;
			return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
		}
		
		public int subsetSum(int[] nums, int s) {
			int[] dp = new int[s + 1];
			dp[0] = 1;
			for (int n : nums)
				for (int i = s; i >= n; i--)
					dp[i] += dp[i - n];
			return dp[s];
		}
	}
}
