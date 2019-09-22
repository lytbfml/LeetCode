package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 9/14/2019.
 */
public class KConcatenation_Maximum_Sum {
	
	public int kConcatenationMaxSum(int[] arr, int K) {
		int mod = (int) 1e9 + 7;
		int n = arr.length, res = 0;
		long maxSum = arr[0], currentSum = 0, sumNow = 0;
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < n; i++) {
				sumNow += arr[i];
				currentSum += arr[i];
				if (currentSum > maxSum) {
					maxSum = currentSum;
				}
				if (currentSum < 0) {
					currentSum = 0;
				}
			}
			if (maxSum < 0) {
				return 0;
			}
			if (sumNow == maxSum) {
				return (int) ((K * sumNow) % mod);
			}
		}
		
		sumNow /= 2;
		if (sumNow > 0) {
			return (int) Math.max(maxSum, maxSum + (sumNow * (K - 2)) % mod);
		}
		
		return (int) maxSum % mod;
	}
}
