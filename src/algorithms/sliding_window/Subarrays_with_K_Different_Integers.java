package algorithms.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-24
 * <p>
 * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number
 * of different integers in that subarray is exactly K.
 * <p>
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 * <p>
 * Return the number of good subarrays of A.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1,2,1,2,3], K = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2],
 * [1,2,1,2].
 * Example 2:
 * <p>
 * Input: A = [1,2,1,3,4], K = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 */
public class Subarrays_with_K_Different_Integers {
	
	public static void main(String[] args) {
		Subarrays_with_K_Different_Integers cs = new Subarrays_with_K_Different_Integers();
	}
	
	public int subarraysWithKDistinct_bruteForce(int[] A, int K) {
		int n = A.length;
		int cnt = 0, l = 0, res = 0;
		int[] mem = new int[n + 1];
		for (int i = 0; i < n; i++) {
			cnt = 0;
			for (int j = i; j < n; j++) {
				if (mem[A[j]]++ == 0) {
					cnt++;
				}
				if (cnt == K) {
					res++;
				}
				if (cnt > K) break;
			}
			mem = new int[n + 1];
		}
		return res;
	}
	
	public int subarraysWithKDistinct_Sol1(int[] a, int k) {
		return atMostK(a, k) - atMostK(a, k - 1);
	}
	
	private int atMostK(int[] a, int k) {
		int res = 0;
		Map<Integer, Integer> count = new HashMap<>();
		for (int i = 0, j = 0; j < a.length; j++) {
			if (count.getOrDefault(a[j], 0) == 0) k--;
			count.put(a[j], count.getOrDefault(a[j], 0) + 1);
			while (k < 0) {
				count.put(a[i], count.get(a[i]) - 1);
				if (count.get(a[i]) == 0) k++;
				i++;
			}
			res += j - i + 1;
		}
		
		return res;
	}
	
	
	public int subarraysWithKDistinct_prefixedSliding(int[] A, int K) {
		int res = 0, pre = 0;
		int[] mem = new int[A.length + 1];
		for (int i = 0, j = 0, cnt = 0; i < A.length; ++i) {
			if (mem[A[i]]++ == 0) ++cnt;
			if (cnt > K) {
				--mem[A[j++]];
				--cnt;
				pre = 0;
			}
			while (mem[A[j]] > 1) {
				++pre;
				--mem[A[j++]];
			}
			if (cnt == K) res += pre + 1;
		}
		return res;
	}
}
