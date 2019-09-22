package algorithms.array;

import java.util.*;

/**
 * @author Yangxiao on 3/26/2019.
 */
public class Three_Sum {
	
	public static void main(String[] args) {
		Three_Sum cs = new Three_Sum();
		int[] nums = {0,0,0};
		System.out.println(cs.threeSum(nums));
		
	}
	
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		int n = nums.length;
		for (int i = 0; i < n - 2; i++) {
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int complemet = 0 - nums[i];
			int left = i + 1, right = n - 1;
			while (left < right) {
				int sum = nums[left] + nums[right];
				if (sum == complemet) {
					res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
					while (left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					while (left < right && nums[right] == nums[right - 1]) {
						right--;
					}
					left++;
					right--;
				} else if (sum > complemet) {
					right--;
				} else {
					left++;
				}
			}
		}
		return res;
	}
}
