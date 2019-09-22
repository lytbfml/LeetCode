package algorithms.array;

/**
 * @author Yangxiao on 10/24/2018.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 */
class Contains_Duplicate {
	
	public boolean containsDuplicate(int[] nums) {
		HashSet<Integer> sets = new HashSet<>();
		
		for (int i = 0; i < nums.length; i++) {
			if(sets.contains(nums[i])) {
				return true;
			}
			sets.add(nums[i]);
		}
		
		return false;
	}
	
	public boolean containsDuplicatex(int[] nums) {
		Set<Integer> set = new HashSet<>(nums.length);
		for (int x: nums) {
			if (set.contains(x)) return true;
			set.add(x);
		}
		return false;
	}
}
