package algorithms.searching;

/**
 * @author Yangxiao Wang on 2019-08-13
 */
public class Check_Majority_Element_Sorted_Array {
	
	public static void main(String[] args) {
		Check_Majority_Element_Sorted_Array cs = new Check_Majority_Element_Sorted_Array();
		cs.isMajorityElement_2(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2}, 3);
		
		cs.isMajorityElement_2(new int[]{438885258, 438885258}, 438885258);
		cs.isMajorityElement_2(new int[]{50641772, 203716494, 225129539, 243027159, 277351609, 347229659, 486511312,
		                                 593429344, 656384929, 861816366}, 624248163);
	}
	
	
	// brute_force
	public boolean isMajorityElement(int[] nums, int target) {
		int n = nums.length;
		int cnt = 0;
		for (int i = 0; i < n; i++) if (nums[i] == target) cnt++;
		return cnt > n / 2;
	}
	
	// binary search
	public boolean isMajorityElement_2(int[] nums, int target) {
		int n = nums.length;
		int l = 0, r = n - 1;
		int left = 0, right = -1;
		while (l <= r) {
			int mid = (l + r) >> 1;
			if (target < nums[mid]) r = mid - 1;
			else if (target > nums[mid]) l = mid + 1;
			else {
				if (mid == 0 || nums[mid - 1] != target) {
					left = mid;
					break;
				} else r = mid - 1;
			}
		}
		l = 0;
		r = n - 1;
		while (l <= r) {
			int mid = (l + r) >> 1;
			if (target < nums[mid]) r = mid - 1;
			else if (target > nums[mid]) l = mid + 1;
			else {
				if (mid == n - 1 || nums[mid + 1] != target) {
					right = mid;
					break;
				} else {
					l = mid + 1;
				}
			}
		}
		return (right - left + 1) > n / 2;
	}
}
