package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
	
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack(res, new ArrayList<>(), nums, 0);
		return res;
	}
	
	public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
		res.add(new ArrayList<>(list));
		for (int i = index; i < nums.length; i++) {
			list.add(nums[i]);
			backtrack(res, list, nums, i + 1);
			list.remove(list.size() - 1);
		}
	}
}
