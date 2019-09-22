package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 9/17/2019.
 */
public class Paint_House_II {
	
	public static void main(String[] args) {
		Paint_House_II cs = new Paint_House_II();
		int[][] tt = {{1, 5, 3}, {2, 9, 4}};
		int[][] dd = {{4, 16}, {15, 5}, {18, 17}, {10, 12}, {14, 10}, {3, 10}, {2, 11}, {18, 14}, {9, 1}, {14, 13}};
		// System.out.println(cs.minCostII(tt));
		System.out.println(cs.minCostII(dd));
	}
	
	public int minCostII(int[][] costs) {
		if (costs == null || costs.length == 0) {
			return 0;
		}
		int n = costs.length, k = costs[0].length, res = 0;
		int low = 0, secendLow = 0, lowIdx = 0;
		int[][] dp = new int[n + 1][k];
		for (int i = 1; i <= n; i++) {
			int curLow = Integer.MAX_VALUE, curSecLow = Integer.MAX_VALUE, curLowIdx = 0;
			for (int j = 0; j < k; j++) {
				dp[i][j] = costs[i - 1][j] + (j == lowIdx ? secendLow : low);
				if (dp[i][j] < curLow) {
					int tmp = curLow;
					curLow = dp[i][j];
					curLowIdx = j;
					curSecLow = tmp;
				} else if (dp[i][j] < curSecLow) {
					curSecLow = dp[i][j];
				}
			}
			low = curLow;
			lowIdx = curLowIdx;
			secendLow = curSecLow;
		}
		return low;
	}
	
	public int minCostII_NoExtraMem(int[][] costs) {
		if (costs == null || costs.length == 0) {
			return 0;
		}
		int n = costs.length, k = costs[0].length, res = 0;
		int low = 0, secendLow = 0, lowIdx = 0;
		for (int i = 1; i <= n; i++) {
			int curLow = Integer.MAX_VALUE, curSecLow = Integer.MAX_VALUE, curLowIdx = 0;
			for (int j = 0; j < k; j++) {
				int cur = costs[i - 1][j] + (j == lowIdx ? secendLow : low);
				if (cur < curLow) {
					int tmp = curLow;
					curLow = cur;
					curLowIdx = j;
					curSecLow = tmp;
				} else if (cur < curSecLow) {
					curSecLow = cur;
				}
			}
			low = curLow;
			lowIdx = curLowIdx;
			secendLow = curSecLow;
		}
		return low;
	}
	
}
