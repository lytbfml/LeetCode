package algorithms.array;

/**
 * @author Yangxiao on 4/20/2019.
 */
public class Maximum_Sum_of_Two_NonOverlapping_Subarrays {
	
	public int maxSumTwoNoOverlap(int[] A, int L, int M) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int n = A.length;
		if (n == L + M) {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += A[i];
			}
			return sum;
		}
		
		int maxL = 0, maxM = 0, sumL = 0, sumM = 0, res = 0;
		for (int i = 0; i < n; i++) {
			sumM += A[i];
			if (i - M >= 0) {
				sumM -= A[i - M];
				sumL += A[i - M];
			}
			if (i - M - L >= 0)
				sumL -= A[i - L - M];
			maxL = Math.max(sumL, maxL);
			res = Math.max(res, sumM + maxL);
		}
		sumL = sumM = maxL = maxM = 0;
		for (int i = 0; i < n; i++) {
			sumL += A[i];
			if (i - L >= 0) {
				sumL -= A[i - L];
				sumM += A[i - L];
			}
			if (i - M - L >= 0)
				sumM -= A[i - L - M];
			maxM = Math.max(sumM, maxM);
			res = Math.max(res, sumL + maxM);
		}
		return res;
	}
	
	public int maxSumTwoNoOverlap_Sol(int[] A, int L, int M) {
		for (int i = 1; i < A.length; ++i)
			A[i] += A[i - 1];
		int res = A[L + M - 1], Lmax = A[L - 1], Mmax = A[M - 1];
		for (int i = L + M; i < A.length; ++i) {
			Lmax = Math.max(Lmax, A[i - M] - A[i - L - M]);
			Mmax = Math.max(Mmax, A[i - L] - A[i - L - M]);
			res = Math.max(res, Math.max(Lmax + A[i] - A[i - M], Mmax + A[i] - A[i - L]));
		}
		return res;
	}
}
