package algorithms.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
	
	class Solution_backtrack {
		public List<List<Integer>> permute(int[] nums) {
			List<List<Integer>> res = new ArrayList<>();
			backtrack(res, new ArrayList<>(), nums);
			return res;
		}
		
		public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums) {
			if (list.size() == nums.length) {
				res.add(new ArrayList<>(list));
			} else {
				for (int i = 0; i < nums.length; i++) {
					if (list.contains(nums[i])) {
						continue;
					}
					list.add(nums[i]);
					backtrack(res, list, nums);
					list.remove(list.size() - 1);
				}
			}
		}
		
		public void backtrack_withused(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
			if (list.size() == nums.length) {
				res.add(new ArrayList<>(list));
			} else {
				for (int i = 0; i < nums.length; i++) {
					if (used[i]) {
						continue;
					}
					used[i] = true;
					list.add(nums[i]);
					backtrack_withused(res, list, nums, used);
					used[i] = false;
					list.remove(list.size() - 1);
				}
			}
		}
	}
	
	class Solution {
		public void backtrack(int n, ArrayList<Integer> nums, List<List<Integer>> output, int first) {
			if (first == n)
				output.add(new ArrayList<>(nums));
			for (int i = first; i < n; i++) {
				// place i-th integer first in the current permutation
				Collections.swap(nums, first, i);
				// use next integers to complete the permutations
				backtrack(n, nums, output, first + 1);
				// backtrack
				Collections.swap(nums, first, i);
			}
		}
		
		public List<List<Integer>> permute(int[] nums) {
			List<List<Integer>> output = new LinkedList<>();
			ArrayList<Integer> nums_lst = new ArrayList<>();
			for (int num : nums)
				nums_lst.add(num);
			
			backtrack(nums.length, nums_lst, output, 0);
			return output;
		}
	}
	
	class Solution_buildPermu {
		public List<List<Integer>> permute_Sol(int[] num) {
			List<List<Integer>> ans = new ArrayList<>();
			if (num.length == 0)
				return ans;
			List<Integer> l0 = new ArrayList<>();
			l0.add(num[0]);
			ans.add(l0);
			for (int i = 1; i < num.length; ++i) {
				List<List<Integer>> new_ans = new ArrayList<>();
				for (int j = 0; j <= i; ++j) {
					for (List<Integer> l : ans) {
						List<Integer> new_l = new ArrayList<>(l);
						new_l.add(j, num[i]);
						new_ans.add(new_l);
					}
				}
				ans = new_ans;
			}
			return ans;
		}
		
		public List<List<Integer>> permute_Sol2(int[] nums) {
			List<List<Integer>> result = new ArrayList<>();
			if (nums.length == 0) return result;
			backtrack2(result, nums, new ArrayList<>(), 0);
			return result;
		}
		
		private void backtrack2(List<List<Integer>> result, int[] nums, List<Integer> currentList, int index) {
			if (currentList.size() == nums.length) {
				result.add(currentList);
				return;
			}
			
			int n = nums[index];
			for (int i = 0; i <= currentList.size(); i++) {
				List<Integer> copy = new ArrayList<>(currentList);
				copy.add(i, n);
				backtrack2(result, nums, copy, index + 1);
			}
		}
	}
	
	
}
