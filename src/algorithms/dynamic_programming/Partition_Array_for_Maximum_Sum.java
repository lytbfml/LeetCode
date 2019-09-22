package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 5/11/2019
 */
public class Partition_Array_for_Maximum_Sum {
	
	
	public static void main(String[] args) {
		Partition_Array_for_Maximum_Sum cs = new Partition_Array_for_Maximum_Sum();
		int[] m = {9, 8, 7, 3, 2, 1};
		cs.maxSumAfterPartitioning(m, 2);
	}
	
	public int maxSumAfterPartitioning(int[] A, int K) {
		int n = A.length;
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			int max = 0;
			for (int j = 1; j <= K && i - j + 1 >= 0; j++) {
				max = Math.max(max, A[i - j + 1]);
				dp[i] = Math.max(dp[i], (i >= j ? dp[i - j] : 0) + max * j);
			}
		}
		
		return dp[n - 1];
	}
	
	
	public int maxSumAfterPartitioning_Sol(int[] A, int K) {
		int N = A.length, dp[] = new int[N];
		for (int i = 0; i < N; ++i) {
			int curMax = 0;
			for (int k = 1; k <= K && i - k + 1 >= 0; ++k) {
				curMax = Math.max(curMax, A[i - k + 1]);
				dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + curMax * k);
			}
		}
		return dp[N - 1];
	}
}
