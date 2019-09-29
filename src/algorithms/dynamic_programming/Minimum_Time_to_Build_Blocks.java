package algorithms.dynamic_programming;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-time-to-build-blocks/
 *
 * @author Yangxiao on 9/27/2019.
 */
public class Minimum_Time_to_Build_Blocks {
	
	public int minBuildTime(int[] blocks, int split) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int b : blocks) pq.add(b);
		while (pq.size() > 1) {
			int x = pq.poll(), y = pq.poll();
			pq.add(y + split);
		}
		return pq.poll();
	}
	
	class Solution {
		int[][] dp;
		int Max = 1000000;
		
		public int minBuildTime(int[] blocks, int split) {
			Arrays.sort(blocks);
			int n = blocks.length;
			dp = new int[n][n];
			return domin(blocks, n - 1, 1, split);
		}
		
		// min cost from 0...i with given workers
		int domin(int[] blocks, int i, int workers, int cost) {
			if (i == -1) {
				return 0;
			}
			// remaing work undone, used up workers
			if (workers == 0) {
				return Max;
			}
			// no need to think about split
			if (workers >= i + 1) {
				return blocks[i];
			}
			if (dp[i][workers] != 0) {
				return dp[i][workers];
			}
			// choice: split or not
			int sp = cost + domin(blocks, i, workers * 2, cost);
			int nosp = Math.max(blocks[i], domin(blocks, i - 1, workers - 1, cost));
			int min = Math.min(sp, nosp);
			dp[i][workers] = min;
			return min;
		}
	}
}