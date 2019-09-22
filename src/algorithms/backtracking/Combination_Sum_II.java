package algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination_Sum_II {
	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(candidates);
		backtrack(res, new ArrayList<>(), candidates, 0, target);
		return res;
	}
	
	private void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int index, int target) {
		
		if (target == 0) {
			res.add(new ArrayList<>(list));
		} else {
			for (int i = index; i < nums.length; i++) {
				if (i > index && nums[i] == nums[i - 1])
					continue;
				if (nums[i] <= target) {
					list.add(nums[i]);
					backtrack(res, list, nums, i + 1, target - nums[i]);
					list.remove(list.size() - 1);
				}
			}
		}
	}
	
	class Solution_betterTime {
		public List<List<Integer>> combinationSum2(int[] candidates, int target) {
			int n = candidates.length;
			List<List<Integer>> combination = new ArrayList<>();
			if (n == 0)
				return combination;
			Arrays.sort(candidates);
			helper(candidates, target, 0, n, 0, new int[n], combination);
			return combination;
		}
		
		public void helper(int[] candidates, int target, int start, int n, int num, int[] stack, List<List<Integer>> combination) {
			for (int i = start; i < n && candidates[i] <= target; i++) {
				if (i > start && candidates[i] == candidates[i - 1])
					continue;
				int curr = candidates[i];
				stack[num++] = curr;
				if (target <= curr) {
					List<Integer> list = new ArrayList<>();
					for (int j = 0; j < num; j++) {
						list.add(stack[j]);
					}
					combination.add(list);
				} else {
					helper(candidates, target - curr, i + 1, n, num, stack, combination);
				}
				num--;
			}
		}
	}
}
