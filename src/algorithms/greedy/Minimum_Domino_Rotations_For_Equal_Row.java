package algorithms.greedy;

/**
 * @author Yangxiao Wang on 9/21/2019
 */
public class Minimum_Domino_Rotations_For_Equal_Row {
	
	public int minDominoRotations(int[] A, int[] B) {
		int n = A.length;
		for (int i = 0, a = 0, b = 0; i < n && (A[i] == A[0] || B[i] == A[0]); ++i) {
			if (A[i] != A[0]) a++;
			if (B[i] != A[0]) b++;
			if (i == n - 1) return Math.min(a, b);
		}
		for (int i = 0, a = 0, b = 0; i < n && (A[i] == B[0] || B[i] == B[0]); ++i) {
			if (A[i] != B[0]) a++;
			if (B[i] != B[0]) b++;
			if (i == n - 1) return Math.min(a, b);
		}
		return -1;
	}
	
	/**
	 * Count the frequency of each number in A and B, respectively;
	 * Count the frequency of A[i] if A[i] == B[i];
	 * If countA[i] + countB[i] - same[i] == A.length, we find a solution; otherwise, return -1;
	 * min(countA[i], countB[i]) - same[i] is the answer.
	 */
	class Solution {
		public int minDominoRotations(int[] A, int[] B) {
			if (A.length != B.length) { return -1; }
			int[] countA = new int[7]; // countA[i] records the occurrence of i in A.
			int[] countB = new int[7]; // countB[i] records the occurrence of i in B.
			int[] same = new int[7]; // same[k] records the occurrence of k, where k == A[i] == B[i].
			for (int i = 0; i < A.length; ++i) {
				++countA[A[i]];
				++countB[B[i]];
				if (A[i] == B[i]) { ++same[A[i]]; }
			}
			for (int i = 1; i < 7; ++i) {
				if (countA[i] + countB[i] - same[i] >= A.length) {
					return Math.min(countA[i], countB[i]) - same[i];
				}
			}
			return -1;
		}
	}
	
	class Solution1 {
		public int minDominoRotations(int[] A, int[] B) {
			for (int i = 1; i <= 6; i++) {
				int res = helper(i, A, B);
				if (res != -1) return res;
			}
			return -1;
		}
		
		private int helper(int target, int[] A, int[] B) {
			int rotateA = 0, rotateB = 0;
			for (int i = 0; i < A.length; i++) {
				if (A[i] != target && B[i] != target)
					return -1;
				if (A[i] != target)
					rotateA++;
				if (B[i] != target)
					rotateB++;
			}
			return Math.min(rotateA, rotateB);
		}
	}
}
