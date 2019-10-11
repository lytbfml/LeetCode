package algorithms.dynamic_programming;

/**
 * @author Yangxiao Wang on 2019-07-22
 */
public class Split_Array_Largest_Sum {
	
	public static void main(String[] args) {
		Split_Array_Largest_Sum.Solution_DP cs = new Solution_DP();
		cs.splitArray(new int[]{7, 2, 5, 10, 8}, 3);
	}
	
	// nums = [7,2,5,10,8]
	// m = 2
	// 7 2 5 | 10 8
	// 0 7 9 14 24 33
	// 18
	
	class Solution_dfs_bruteForce {
		private int ans;
		private int n, m;
		
		private void dfs(int[] nums, int i, int cntSubarrays, int curSum, int curMax) {
			if (i == n && cntSubarrays == m) {
				ans = Math.min(ans, curMax);
				return;
			}
			if (i == n) {
				return;
			}
			if (i > 0) {
				dfs(nums, i + 1, cntSubarrays, curSum + nums[i], Math.max(curMax, curSum + nums[i]));
			}
			if (cntSubarrays < m) {
				dfs(nums, i + 1, cntSubarrays + 1, nums[i], Math.max(curMax, nums[i]));
			}
		}
		
		public int splitArray(int[] nums, int M) {
			ans = Integer.MAX_VALUE;
			n = nums.length;
			m = M;
			dfs(nums, 0, 0, 0, 0);
			return ans;
		}
	}
	
	class solution_dfs {
		
		public int splitArray(int[] nums, int m) {
			int n = nums.length;
			int[] acc = new int[n + 1];
			acc[0] = 0;
			for (int i = 1; i <= n; i++) {
				acc[i] += nums[i - 1] + acc[i - 1];
			}
			int[][] visited = new int[n][m + 1];
			return dfs(acc, 0, m, visited);
		}
		
		private int dfs(int[] acc, int start, int m, int[][] visited) {
			if (m == 1) {
				return acc[acc.length - 1] - acc[start];
			}
			if (visited[start][m] != 0) {
				return visited[start][m];
			}
			
			int maxSum = Integer.MAX_VALUE;
			
			for (int i = start; i < acc.length - 2; i++) {
				int leftSum = acc[i + 1] - acc[start];
				int rightSum = dfs(acc, i + 1, m - 1, visited);
				maxSum = Math.min(maxSum, Math.max(leftSum, rightSum));
			}
			
			visited[start][m] = maxSum;
			return maxSum;
		}
	}
	
	/**
	 * Let's define f[i][j] to be the minimum largest subarray sum for splitting nums[0..i] into j parts.
	 * <p>
	 * Consider the jth subarray. We can split the array from a smaller index k to i to form it. Thus f[i][j] can be
	 * derived from max(f[k][j - 1], nums[k + 1] + ... + nums[i]). For all valid index k, f[i][j] should choose the
	 * minimum value of the above formula.
	 * <p>
	 * The final answer should be f[n][m], where n is the size of the array.
	 * <p>
	 * For corner situations, all the invalid f[i][j] should be assigned with INFINITY, and f[0][0] should be
	 * initialized with 0.
	 */
	static class Solution_DP {
		
		public int splitArray(int[] nums, int m) {
			int n = nums.length;
			int[][] dp = new int[n + 1][m + 1];
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= m; j++) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
			dp[0][0] = 0;
			int[] acc = new int[n + 1];
			acc[0] = 0;
			for (int i = 1; i <= n; i++) {
				acc[i] += nums[i - 1] + acc[i - 1];
			}
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					for (int k = 0; k < i; k++) {
						dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], acc[i] - acc[k]));
					}
				}
			}
			return dp[n][m];
		}
	}
	
	/**
	 * 1. The answer is between maximum value of input array numbers and sum of those numbers.
	 * <p>
	 * 2. Use binary search to approach the correct answer. We have l = max number of array; r = sum of all numbers in
	 * the array; Every time we do mid = (l + r) / 2; Use greedy to narrow down left and right boundaries in binary
	 * search.
	 * <p>
	 * <p>
	 * 3.1 Cut the array from left.
	 * <p>
	 * 3.2 Try our best to make sure that the sum of numbers between each two cuts (inclusive) is large enough but still
	 * less than mid.
	 * <p>
	 * 3.3 We'll end up with two results: either we can divide the array into more than m subarrays or we cannot. If we
	 * can, it means that the mid value we pick is too small because we've already tried our best to make sure each part
	 * holds as many non-negative numbers as we can but we still have numbers left. So, it is impossible to cut the
	 * array into m parts and make sure each parts is no larger than mid. We should increase m. This leads to l = mid +
	 * 1; If we can't, it is either we successfully divide the array into m parts and the sum of each part is less than
	 * mid, or we used up all numbers before we reach m. Both of them mean that we should lower mid because we need to
	 * find the minimum one. This leads to r = mid - 1;
	 * <p>
	 * https://leetcode.com/problems/split-array-largest-sum/discuss/89817/Clear-Explanation%3A-8ms-Binary-Search-Java
	 */
	static class Solution_Best_binarySearch {
		
		public int splitArray(int[] nums, int m) {
			int max = 0;
			long sum = 0;
			for (int num : nums) {
				max = Math.max(num, max);
				sum += num;
			}
			if (m == 1) return (int) sum;
			//binary search
			long l = max;
			long r = sum;
			while (l <= r) {
				long mid = (l + r) / 2;
				if (valid(mid, nums, m)) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			}
			return (int) l;
		}
		
		public boolean valid(long target, int[] nums, int m) {
			int count = 1;
			long total = 0;
			for (int num : nums) {
				total += num;
				if (total > target) {
					total = num;
					count++;
					if (count > m) {
						return false;
					}
				}
			}
			return true;
		}
	}
	
	class Solution_bin {
		public int splitArray(int[] nums, int m) {
			long l = 0;
			long r = 0;
			int n = nums.length;
			for (int i = 0; i < n; i++) {
				r += nums[i];
				if (l < nums[i]) {
					l = nums[i];
				}
			}
			long ans = r;
			while (l <= r) {
				long mid = (l + r) >> 1;
				long sum = 0;
				int cnt = 1;
				for (int i = 0; i < n; i++) {
					if (sum + nums[i] > mid) {
						cnt++;
						sum = nums[i];
					} else {
						sum += nums[i];
					}
				}
				if (cnt <= m) {
					ans = Math.min(ans, mid);
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			}
			return (int) ans;
		}
	}
}









