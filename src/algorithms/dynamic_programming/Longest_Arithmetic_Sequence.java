package algorithms.dynamic_programming;

import java.util.*;

/**
 * @author Yangxiao on 4/13/2019.
 */
public class Longest_Arithmetic_Sequence {
	
	public static void main(String[] args) {
		Longest_Arithmetic_Sequence cs = new Longest_Arithmetic_Sequence();
		int[] a = {24, 13, 1, 100, 0, 94, 3, 0, 3};
		int[] b = {0, 8, 45, 88, 48, 68, 28, 55, 17, 24};
		System.out.println(cs.longestArithSeqLength(b));
	}
	
	
	public int longestArithSeqLength(int[] A) {
		int n = A.length;
		Map<Integer, Integer>[] map = new Map[n];
		int max = 2;
		for (int i = 0; i < n; i++) {
			map[i] = new HashMap<>();
			for (int j = 0; j < i; j++) {
				int diff = A[i] - A[j];
				int len = map[j].getOrDefault(diff, 1) + 1;
				map[i].put(diff, len);
				max = Math.max(max, len);
			}
		}
		
		return max;
	}
	
	public int longestArithSeqLength_Foo(int[] A) {
		int n = A.length;
		int max = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				
				int diff = A[j] - A[i];
				int next = A[j] + diff;
				int cnt = 2;
				for (int k = j + 1; k < n; k++) {
					if (A[k] == next) {
						cnt++;
						next = A[k] + diff;
					}
				}
				max = Math.max(max, cnt);
			}
		}
		
		return max;
	}
}
