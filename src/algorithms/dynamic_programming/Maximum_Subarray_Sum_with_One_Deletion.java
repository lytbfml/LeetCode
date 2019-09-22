package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 9/7/2019.
 */
public class Maximum_Subarray_Sum_with_One_Deletion {
	
	public static void main(String[] args) {
		Maximum_Subarray_Sum_with_One_Deletion cs = new Maximum_Subarray_Sum_with_One_Deletion();
		System.out.println(cs.maxSumSubarrayRemovingOneEle(new int[]{1, -2, 0, 3}));
		System.out.println(cs.maxSumSubarrayRemovingOneEle(new int[]{1, -2, -2, 3}));
		System.out.println(cs.maxSumSubarrayRemovingOneEle(new int[]{-1, -1, -1, -1}));
	}
	
	public int maximumSum(int[] arr) {
		int n = arr.length;
		int res = arr[0];
		for (int i = 0; i < n; i++) {
			int tsum = 0, tmax = arr[i], prevNeg = 0;
			for (int j = i; j < n; j++) {
				if (arr[j] < 0 && arr[j] < prevNeg) {
					tsum += prevNeg;
					prevNeg = arr[j];
				} else {
					tsum += arr[j];
				}
				if (j != i && tsum > tmax) {
					tmax = tsum;
				}
			}
			res = Math.max(res, tmax);
		}
		return res;
	}
	
	int maxSumSubarrayRemovingOneEle(int arr[]) {
		int n = arr.length;
		// Maximum sum subarrays in forward and backward directions
		int fw[] = new int[n];
		int bw[] = new int[n];
		
		// Initialize current max and max so far.
		int curMax = arr[0], max = arr[0];
		
		// calculating maximum sum subarrays in forward direction
		fw[0] = arr[0];
		
		for (int i = 1; i < n; i++) {
			curMax = Math.max(arr[i], curMax + arr[i]);
			max = Math.max(max, curMax);
			// storing current maximum till ith, in forward array
			fw[i] = curMax;
		}
		
		// calculating maximum sum subarrays in backward direction
		curMax = max = bw[n - 1] = arr[n - 1];
		
		for (int i = n - 2; i >= 0; i--) {
			curMax = Math.max(arr[i], curMax + arr[i]);
			max = Math.max(max, curMax);
			// storing current maximum from ith, in backward array
			bw[i] = curMax;
		}
		
		// Initializing final ans by max so that,
		// case when no element is removed to get max sum subarray is also handled
		int res = max;
		
		// choosing maximum ignoring ith element
		for (int i = 1; i < n - 1; i++)
			res = Math.max(res, fw[i - 1] + bw[i + 1]);
		
		return res;
	}
}
