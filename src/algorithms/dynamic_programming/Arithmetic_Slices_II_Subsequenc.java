package algorithms.dynamic_programming; /**
 * @author Yangxiao on 10/25/2018.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The function should return the number of arithmetic subsequence slices in the array A.
 */
class Arithmetic_Slices_II_Subsequenc {
	
	public int numberOfArithmeticSlices_Sol(int[] A) {
		int n = A.length;
		long ans = 0;
		Map<Integer, Integer>[] cnt = new Map[n];
		for (int i = 0; i < n; i++) {
			cnt[i] = new HashMap<>(i);
			for (int j = 0; j < i; j++) {
				long delta = (long) A[i] - (long) A[j];
				if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
					continue;
				}
				int diff = (int) delta;
				int sum = cnt[j].getOrDefault(diff, 0);
				// for repeated element: 
				int origin = cnt[i].getOrDefault(diff, 0);
				cnt[i].put(diff, origin + sum + 1);
				ans += sum;
			}
		}
		
		return (int) ans;
	}
	
	
	public static void main(String[] args) {
		
		Arithmetic_Slices_II_Subsequenc as = new Arithmetic_Slices_II_Subsequenc();
		int in[] = {2, 4, 6, 8, 10};
		as.numberOfArithmeticSlices_Sol(in);
	}
	
	//Fast solution
	class Solution {
		public int numberOfArithmeticSlices(int[] A) {
			if (A == null || A.length < 3)
				return 0;
			Map<Integer, List<Integer>> indexMap = new HashMap<>();
			for (int i = 0; i < A.length; i++) {
				if (!indexMap.containsKey(A[i])) {
					indexMap.put(A[i], new ArrayList<>());
				}
				indexMap.get(A[i]).add(i);
			}
			int result = 0;
			for (Integer key : indexMap.keySet()) {
				int size = indexMap.get(key).size();
				if (size >= 3) {
					result += (1 << size) - 1 - size - size * (size - 1) / 2;
				}
			}
			for (int i = 0; i < A.length - 2; i++) {
				for (int j = i + 1; j < A.length - 1; j++) {
					long delta = (long) A[j] - A[i];
					if (delta == 0)
						continue;
					result += dfs(A, j, delta, indexMap);
				}
			}
			return result;
		}
		
		private int dfs(int[] A, int index, long delta, Map<Integer, List<Integer>> indexMap) {
			long nextValue = delta + A[index];
			if (nextValue < Integer.MIN_VALUE || nextValue > Integer.MAX_VALUE ||
					!indexMap.containsKey((int) nextValue)) {
				return 0;
			}
			int result = 0;
			for (int i : indexMap.get((int) nextValue)) {
				if (i <= index)
					continue;
				result += dfs(A, i, delta, indexMap) + 1;
			}
			return result;
		}
	}
}
