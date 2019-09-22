package algorithms.array;

/**
 * @author Yangxiao on 11/17/2018.
 */

/**
 * Given an array A of integers, return true if and only if it is a valid mountain array.
 * <p>
 * Recall that A is a mountain array if and only if:
 * <p>
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[B.length - 1]
 */
class Valid_Mountain_Array {
	
	public boolean validMountainArray(int[] A) {
		if (A == null || A.length < 3) {
			return false;
		}
		
		int n = A.length;
		int status = 0;
		for (int i = 1; i < n; i++) {
			if (A[i] > A[i-1]) {
				if (status == -1 || i == n-1) {
					return false;
				}
				status = 1;
			} else if (A[i] < A[i - 1]) {
				if (status == 0 || i == 1) {
					return false;
				}
				status = -1;
			} else {
				if (i == n - 1 || i == 1) {
					return false;
				}
				status = 0;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Valid_Mountain_Array va = new Valid_Mountain_Array();
		int[] x = {2,1};
		int[] x1 = {3,5,5};
		int[] x2 = {0,3,2,1};
		int[] x3 = {0,0,5,0,0};
		int[] x4 = {0,1,2,3,4,5,6,7,8,9};
		int[] x5 = {1,7,9,5,4,1,2};
		System.out.println(va.validMountainArray(x));
		System.out.println(va.validMountainArray(x1));
		System.out.println(va.validMountainArray(x2));
		System.out.println(va.validMountainArray(x3));
		System.out.println(va.validMountainArray(x4));
		System.out.println(va.validMountainArray(x5));
		
	}
}
