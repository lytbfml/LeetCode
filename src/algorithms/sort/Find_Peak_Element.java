package algorithms.sort;

/**
 * @author Yangxiao on 3/29/2019.
 */
public class Find_Peak_Element {
	
	// ok but not work for [4,4,4,4,1,2,1]
	public int findPeakElement(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		if (n == 1) {
			return 0;
		}
		int left = 0;
		int right = n-1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < nums[mid + 1]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	public int findPeakElement_2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		if (n == 1) {
			return 0;
		}
		return helper(nums, 0, n -1);
	}
	
	public int helper(int[] a, int l, int r) {
		if (l >= r) {
			return l;
		}
		int mid = l + (r - l) / 2;
		if (a[mid] < a[mid + 1]) {
			return helper(a, mid + 1, r);
		} else {
			return helper(a, l, mid);
		}
	}
	
}
