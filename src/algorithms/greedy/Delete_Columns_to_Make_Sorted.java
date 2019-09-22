package algorithms.greedy;

/**
 * @author Yangxiao on 11/17/2018.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * We are given an array A of N lowercase letter strings, all of the same length.
 * <p>
 * Now, we may choose any set of deletion indices, and for each string, we delete all the characters
 * in those indices.
 * <p>
 * For example, if we have a string "abcdef" and deletion indices {0, 2, 3}, then the final string
 * after deletion is "bef".
 * <p>
 * Suppose we chose a set of deletion indices D such that after deletions, each remaining column in
 * A is in sorted order.
 * <p>
 * Formally, the c-th column is [A[0][c], A[1][c], ..., A[A.length-1][c]]
 * <p>
 * Return the minimum possible value of D.length.
 */
class Delete_Columns_to_Make_Sorted {
	
	public int minDeletionSize(String[] A) {
		if (A == null || A.length <= 1) {
			return 0;
		}
		int n = A.length;
		int m = A[0].length();
		Set<Integer> del = new HashSet<>();
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (A[i].charAt(j) < A[i - 1].charAt(j)) {
					del.add(j);
				}
			}
		}
		
		return del.size();
	}
	
	public static void main(String[] args) {
		Delete_Columns_to_Make_Sorted dl = new Delete_Columns_to_Make_Sorted();
		String[][] x1 = {{"cba","daf","ghi"}, {"a","b"}, {"zyx","wvu","tsr"}, {"dads","ebas","fads"}};
		
		for (int i = 0; i < x1.length; i++) {
			System.out.println(dl.minDeletionSize(x1[i]));
		}
	}
}
