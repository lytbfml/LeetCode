package algorithms.sort;

import java.util.Arrays;

/**
 * @author Yangxiao on 4/2/2019.
 */
public class Search_for_a_Range {
	
	public static void main(String[] args) {
		Search_for_a_Range cs = new Search_for_a_Range();
		int[] s = {2,2};
		System.out.println(Arrays.toString(cs.searchRange(s, 2)));
	}
	
	public int[] searchRange(int[] nums, int target) {
		int[] res = new int[]{-1, -1};
		if (nums == null || nums.length == 0) {
			return res;
		}
		
		int n = nums.length;
		if (n == 1 && nums[0] == target) {
			return new int[]{0, 0};
		}
		int l = 0, r = n - 1;
		boolean find = false;
		int lS = -1;
		while (l < r && res[0] == -1) {
			int mid = l + (r - l) / 2;
			if (nums[mid] < target) {
				if (mid != n - 1 && nums[mid + 1] == target) {
					res[0] = mid + 1;
				}
				l = mid + 1;
			} else if (nums[mid] > target) {
				if (mid != 0 && nums[mid - 1] == target) {
					res[1] = mid - 1;
				}
				r = mid;
			} else {
				if (mid == 0 || nums[mid - 1] != target) {
					res[0] = mid;
				} else {
					if (!find) {
						find = true;
						lS = l;
					}
					r = mid;
				}
			}
		}
		
		
		l = 0;
		r = n;
		if (find) {
			l = lS;
		}
		while (l < r && res[1] == -1) {
			int mid = l + (r - l) / 2;
			if (nums[mid] < target) {
				l = mid + 1;
			} else if (nums[mid] > target) {
				if (mid != 0 && nums[mid - 1] == target) {
					res[1] = mid - 1;
				}
				r = mid;
			} else {
				if (mid == n - 1 || nums[mid + 1] != target) {
					res[1] = mid;
				} else {
					l = mid + 1;
				}
			}
		}
		
		return res;
	}
}
