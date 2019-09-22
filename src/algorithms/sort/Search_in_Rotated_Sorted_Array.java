package algorithms.sort;

/**
 * @author Yangxiao on 4/3/2019.
 */
public class Search_in_Rotated_Sorted_Array {
	
	public static void main(String[] args) {
		Search_in_Rotated_Sorted_Array cs = new Search_in_Rotated_Sorted_Array();
		int[] nums = {4, 5, 6, 7, 0, 1, 2};
		cs.search(nums, 0);
	}
	
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int n = nums.length;
		int lo = 0, hi = n - 1;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] > nums[hi]) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		int start = lo;
		
		lo = 0;
		hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int midI = mid < n - start ? mid + start : mid - (n - start);
			if (target > nums[midI]) {
				lo = mid + 1;
			} else if (target < nums[midI]) {
				hi = mid - 1;
			} else {
				return midI;
			}
		}
		
		return -1;
	}
	
	public int search_sol(int[] A, int target) {
		int lo = 0;
		int hi = A.length - 1;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (A[mid] == target) { return mid; }
			
			if (A[lo] <= A[mid]) {
				if (target >= A[lo] && target < A[mid]) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else {
				if (target > A[mid] && target <= A[hi]) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}
		return A[lo] == target ? lo : -1;
	}
}
