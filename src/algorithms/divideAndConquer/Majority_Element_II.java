package algorithms.divideAndConquer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-10
 */
public class Majority_Element_II {
	
	public List<Integer> majorityElement(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if (nums == null || nums.length == 0) return res;
		int n = nums.length;
		int cand1 = nums[0], cand2 = nums[0], count1 = 0, count2 = 0;
		
		for (int num : nums) {
			if (num == cand1) count1++;
			else if (num == cand2) count2++;
			else if (count1 == 0) {
				cand1 = num;
				count1++;
			} else if (count2 == 0) {
				cand2 = num;
				count2++;
			} else {
				count1--;
				count2--;
			}
		}
		count1 = 0;
		count2 = 0;
		for (int x : nums) {
			if (x == cand1) count1++;
			else if (x == cand2) count2++;
		}
		
		if (count1 > n / 3) res.add(cand1);
		if (count2 > n / 3) res.add(cand2);
		return res;
	}
	
	class Solution {
		public List<Integer> majorityElement(int[] nums) {
			int limit = (nums.length / 3);
			Map<Integer, Integer> m = new HashMap<>();
			for (int i = 0; i < nums.length; i++) {
				m.put(nums[i], m.getOrDefault(nums[i], 0) + 1);
			}
			List<Integer> l1 = new ArrayList<>();
			
			for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
				if (entry.getValue() > limit) l1.add(entry.getKey());
			}
			return l1;
		}
	}
	
}
