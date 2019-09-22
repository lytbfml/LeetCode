package algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations_II {
	
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(res, new ArrayList<>(), nums, new boolean[nums.length]);
		return res;
	}
	
	public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
		if (list.size() == nums.length) {
			res.add(new ArrayList<>(list));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
					continue;
				}
				used[i] = true;
				list.add(nums[i]);
				backtrack(res, list, nums, used);
				used[i] = false;
				list.remove(list.size() - 1);
			}
		}
	}
}
