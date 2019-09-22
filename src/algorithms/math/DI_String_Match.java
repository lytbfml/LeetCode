package algorithms.math;



/**
 * @author Yangxiao on 11/17/2018.
 */

import java.util.Arrays;

/**
 * Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
 * <p>
 * Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:
 * <p>
 * If S[i] == "I", then A[i] < A[i+1]
 * If S[i] == "D", then A[i] > A[i+1]
 * <p>
 * Example 1:
 * Input: "IDID"
 * Output: [0,4,1,3,2]
 * <p>
 * Example 2:
 * Input: "III"
 * Output: [0,1,2,3]
 * <p>
 * Example 3:
 * Input: "DDI"
 * Output: [3,2,0,1]
 * <p>
 * Note:
 * 1 <= S.length <= 1000
 * S only contains characters "I" or "D".
 */
class DI_String_Match {
	
	public int[] diStringMatch(String S) {
		if (S == null || S.length() < 1) {
			return null;
		}
		int n = S.length();
		int[] re = new int[n + 1];
		int D = n;
		int I = 0;
		for (int i = 0; i < n; i++) {
			if (S.charAt(i) == 'D') {
				re[i] = D--;
			} else {
				re[i] = I++;
			}
		}
		
		if (S.charAt(n - 1) == 'D') {
			re[n] = D--;
		} else {
			re[n] = I++;
		}
		
		return re;
	}
	
	public static void main(String[] args) {
		DI_String_Match ds = new DI_String_Match();
		String[] t = {"IDID", "III", "DDI"};
		for (int i = 0; i < t.length; i++) {
			System.out.println(Arrays.toString(ds.diStringMatch(t[i])));
		}
	}
}
