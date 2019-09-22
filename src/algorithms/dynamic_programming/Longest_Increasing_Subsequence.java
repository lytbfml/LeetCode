package algorithms.dynamic_programming;

import java.util.Arrays;

/**
 * @author Yangxiao on 4/16/2019.
 */
public class Longest_Increasing_Subsequence {
	
	public static void main(String[] args) {
		Longest_Increasing_Subsequence cs = new Longest_Increasing_Subsequence();
		int[] cc = {10, 9, 2, 5, 3, 7, 1, 18};
		int[] dd = {10, 9, 2, 5, 3, 7, 101, 100, 99, 98, 97, 96};
		System.out.println(cs.lengthOfLIS(cc));
		System.out.println(cs.lengthOfLIS(dd));
	}
	
	public int lengthOfLIS(int[] nums) {
		
		int[] dp = new int[nums.length + 1];
		Arrays.fill(dp, Integer.MIN_VALUE);
		int max = 0;
		for (int n : nums) {
			for (int i = max; i >= 0; i--) {
				if (n > dp[i]) {
					dp[i + 1] = n;
					if (i == max) {
						max++;
					}
					break;
				}
			}
		}
		return max;
	}
	
	public int lengthOfLIS_SolDP(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		int n = nums.length;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					if (dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;
					}
				}
			}
		}
		
		int res = 0;
		for (int i = 0; i < n; i++) {
			res = Math.max(dp[i], res);
		}
		return res + 1;
	}
	
	// tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
	// For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:
	//
	// len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
	// len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
	// len = 3   :      [4, 5, 6]            => tails[2] = 6
	// We can easily prove that tails is a increasing array.
	// Therefore it is possible to do a binary search in tails array to find the one needs update.
	//
	// Each time we only do one of the two:
	//
	// (1) if x is larger than all tails, append it, increase the size by 1
	// (2) if tails[i-1] < x <= tails[i], update tails[i]
	//
	// Doing so will maintain the tails invariant. The the final answer is just the size.
	//
	public int lengthOfLIS_Solnlogn(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		int[] dp = new int[nums.length];
		int len = 0;
		
		for (int cur : nums) {
			int cnt = Arrays.binarySearch(dp, 0, len, cur);
			if (cnt < 0) {
				cnt = -(cnt + 1);
			}
			dp[cnt] = cur;
			if (cnt == len) {
				len++;
			}
		}
		
		return len;
	}
	
	public int lengthOfLIS_Solnlogn2(int[] nums) {
		int[] tails = new int[nums.length];
		int size = 0;
		for (int x : nums) {
			int i = 0, j = size;
			while (i != j) {
				int m = (i + j) / 2;
				if (tails[m] < x) {
					i = m + 1;
				} else {
					j = m;
				}
			}
			tails[i] = x;
			if (i == size) {
				++size;
			}
		}
		return size;
	}
}
