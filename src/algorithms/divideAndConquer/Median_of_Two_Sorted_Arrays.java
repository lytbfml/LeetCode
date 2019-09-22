package algorithms.divideAndConquer;


/**
 * @author Yangxiao on 3/29/2019.
 */
public class Median_of_Two_Sorted_Arrays {
	
	public static void main(String[] args) {
		Median_of_Two_Sorted_Arrays cs = new Median_of_Two_Sorted_Arrays();
		int[] w = {1, 2, 2, 4, 5};
		int[] x = {3, 3, 4, 4, 5};
		int[] w1 = {1, 2, 3};
		int[] x1 = {4, 4};
		int[] w2 = {1, 3};
		int[] x2 = {2};
		System.out.println(cs.findMedianSortedArrays(w2, x2));
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		if (m > n) {
			return findMedianSortedArrays(nums2, nums1);
		}
		int l = 0, r = m;
		int midIndex = (m + n + 1) / 2;
		boolean even = (m + n) % 2 == 0;
		while (l <= r) {
			int mid1 = l + (r - l) / 2;
			int mid2 = midIndex - mid1;
			if ((mid1 == m || mid2 == 0 || nums1[mid1] >= nums2[mid2 - 1]) &&
					(mid2 == n || mid1 == 0 || nums1[mid1 - 1] <= nums2[mid2])) {
				double left;
				if (mid1 == 0) {
					left = nums2[mid2 - 1];
				} else if (mid2 == 0) {
					left = nums1[mid1 - 1];
				} else {
					left = Math.max(nums1[mid1 - 1], nums2[mid2 - 1]);
				}
				if (!even) {
					return left;
				}

				double right;
				if (mid1 >= m) {
					right = nums2[mid2];
				} else if (mid2 >= n) {
					right = nums1[mid1];
				} else {
					right = Math.min(nums1[mid1], nums2[mid2]);
				}
				return (left + right) / 2.0;

			} else if (mid1 > 0 && mid2 < n && nums1[mid1 - 1] > nums2[mid2]) {
				r = mid1 - 1;
			} else if (mid1 < m && mid2 > 0 && nums1[mid1] < nums2[mid2 - 1]) {
				l = mid1 + 1;
			}
		}
		return 0.0;
	}


	double findMedianSortedArrays_Sol(int[] nums1, int[] nums2) {
		int N1 = nums1.length;
		int N2 = nums2.length;
		if (N1 < N2) {
			return findMedianSortedArrays(nums2, nums1);    // Make sure A2 is the shorter one.
		}

		int lo = 0, hi = N2 * 2;
		while (lo <= hi) {
			int mid2 = (lo + hi) / 2;   // Try Cut 2
			int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly

			double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) /
					2];    // Get L1, R1, L2, R2 respectively
			double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
			double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1) / 2];
			double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2) / 2];

			if (L1 > R2) {
				lo = mid2 + 1;        // A1's lower half is too big; need to move C1 left (C2 right)
			} else if (L2 > R1) {
				hi = mid2 - 1;    // A2's lower half too big; need to move C2 left.
			} else {
				// Otherwise, that's the right cut.
				return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
			}
		}
		return -1;
	}


}
