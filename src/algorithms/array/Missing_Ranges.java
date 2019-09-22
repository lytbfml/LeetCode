package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao Wang on 5/17/2019
 */
public class Missing_Ranges {
	
	public static void main(String[] args) {
		Missing_Ranges cs = new Missing_Ranges();
		int[] s = {2147483647};
		System.out.println(Integer.MAX_VALUE + ", " + (2147483646 + 1));
		cs.findMissingRanges(s, 0, 2147483647);
	}
	
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0) {
			if (lower == upper) {
				return new ArrayList<>(Arrays.asList(lower + ""));
			} else {
				return new ArrayList<>(Arrays.asList(lower + "->" + upper));
			}
		}
		int n = nums.length;
		List<String> list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			if (nums[i] > lower) {
				if (nums[i] - 1 > lower) {
					list.add(lower + "->" + (nums[i] - 1));
				} else {
					list.add(String.valueOf(lower));
				}
			}
			if (nums[i] < upper) {
				lower = nums[i] + 1;
			} else if (nums[i] == upper) {
				return list;
			}
		}
		
		if (lower < upper) {
			list.add(lower + "->" + upper);
		} else if (lower == upper) {
			list.add(String.valueOf(upper));
		}
		
		return list;
	}
	
	public List<String> findMissingRanges_Sol(int[] nums, int lower, int upper) {
		List<String> res = new ArrayList<String>();
		long low = (long) lower;
		long high = (long) lower;
		
		for (int i = 0; i <= nums.length; i++) {        //i<=nums.length can add last missing range
			low = i == 0 ? low : (long) nums[i - 1] + 1;
			high = i == nums.length ? (long) upper : (long) nums[i] - 1;
			
			if (low == high)
				res.add(low + "");
			if (low < high)
				res.add(low + "->" + high);
		}
		return res;
	}
}
