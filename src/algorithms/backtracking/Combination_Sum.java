package algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combination_Sum {
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack(res, new ArrayList<>(), candidates, 0, target);
		return res;
	}
	
	private void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int index, int target) {
		
		if (target == 0) {
			res.add(new ArrayList<>(list));
		} else {
			for (int i = index; i < nums.length; i++) {
				if(nums[i] <= target) {
					list.add(nums[i]);
					backtrack(res, list, nums, i, target - nums[i]);
					list.remove(list.size() - 1);
				}
			}
		}
	}
	
	class Solution_BetterSpace {
		List<List<Integer>> list;
		int N;
		int[] nums;
		
		public List<List<Integer>> combinationSum(int[] candidates, int target) {
			list = new ArrayList<>();
			N = candidates.length;
			nums = candidates;
			helper(new int[target], 0, target, 0);
			return list;
		}
		
		private void helper(int[] curr, int start, int target, int count) {
			if (target == 0) {
				List<Integer> res = new ArrayList<>(count);
				for (int i = 0; i < count; ++i)
					res.add(curr[i]);
				list.add(res);
				return;
			}
			for (int i = start; i < N; ++i) {
				if (nums[i] <= target) {
					curr[count] = nums[i];
					helper(curr, i, target - nums[i], count + 1);
				}
			}
		}
	}
}
