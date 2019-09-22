package algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets_II {
	
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(res, new ArrayList<>(), nums, 0);
		return res;
	}
	
	public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
		res.add(new ArrayList<>(list));
		for (int i = index; i < nums.length; i++) {
			if (i > index && nums[i] == nums[i - 1])
				continue;
			list.add(nums[i]);
			backtrack(res, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
	}
}
