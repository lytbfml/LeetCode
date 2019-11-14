package algorithms.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao on 11/2/2019.
 * @see algorithms.sliding_window.Subarrays_with_K_Different_Integers
 */
public class Count_Number_of_Nice_Subarrays {
	
	public static void main(String[] args) {
		int[] xx = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
		int[] yy = {1, 1, 2, 1, 1};
		Count_Number_of_Nice_Subarrays ww = new Count_Number_of_Nice_Subarrays();
		System.out.println(ww.numberOfSubarrays(yy, 3));
	}
	
	public int numberOfSubarrays(int[] A, int k) {
		return atMost(A, k) - atMost(A, k - 1);
	}
	
	public int atMost(int[] A, int k) {
		int res = 0, i = 0, n = A.length;
		for (int j = 0; j < n; j++) {
			k -= A[j] % 2;
			while (k < 0)
				k += A[i++] % 2;
			res += j - i + 1; // number of subarrays end with from i - j, end with j
		}
		return res;
	}
	
	public int numberOfSubarrays2(int[] nums, int k) {
		int ans = 0;
		for (int hi = 0, lo = 0, prev = -1; hi < nums.length; ++hi) {
			k -= nums[hi] % 2;
			if (nums[lo] % 2 == 0) // move lo to 1st odd index.
				++lo;
			if (k == 0) { // accumulate k odd numbers in window.
				ans += lo - prev; // update result.
			} else if (k < 0) { // more than k.
				prev = lo;
				while (nums[++lo] % 2 == 0) {} // need to shrink from low bound.
				k = 0;
				ans += lo - prev; // update result.
			}
		}
		return ans;
	}
	
	public int numberOfSubarrays_bruteForce(int[] nums, int k) {
		int n = nums.length;
		int[] mem = new int[n];
		mem[0] = nums[0] % 2 == 0 ? 0 : 1;
		for (int i = 1; i < n; i++) {
			mem[i] = mem[i - 1] + ((nums[i] % 2 == 0) ? 0 : 1);
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int res = 0;
		
		for (int i = 0; i < n; i++) {
			if (mem[i] - k >= 0) {
				res += map.get(mem[i] - k);
			}
			map.put(mem[i], map.getOrDefault(mem[i], 0) + 1);
		}
		
		return res;
	}
}
