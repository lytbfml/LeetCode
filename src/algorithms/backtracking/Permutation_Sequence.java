package algorithms.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yangxiao Wang on 9/9/2019
 */
public class Permutation_Sequence {
	
	
	class Solution_backtrack {
		
		int ind = 0;
		String res = "";
		boolean found = false;
		
		public String getPermutation(int n, int k) {
			boolean[] used = new boolean[n + 1];
			backtrack(new ArrayList<>(), n, k, used);
			return res;
		}
		
		public void backtrack(List<Integer> list, int n, int k, boolean[] used) {
			if (list.size() == n) {
				ind++;
				if (ind == k) {
					for (int i = 0; i < list.size(); i++)
						res += list.get(i) + "";
					found = true;
				}
			} else {
				for (int i = 1; i <= n; i++) {
					if (used[i])
						continue;
					used[i] = true;
					list.add(i);
					backtrack(list, n, k, used);
					used[i] = false;
					if (found) return;
					list.remove(list.size() - 1);
				}
			}
		}
	}
	
	class Solution {
		
		public String getPermutation(int n, int k) {
			StringBuilder sb = new StringBuilder();
			ArrayList<Integer> list = new ArrayList<>();
			
			int[] facto = new int[n + 1];
			facto[0] = 1;
			
			for (int i = 1; i <= n; i++)
				facto[i] = i * facto[i - 1];
			
			for (int i = 1; i <= n; i++)
				list.add(i);
			k--;
			
			for (int i = 1; i <= n; i++) {
				int index = k / facto[n - i]; // (n - 1)! = n! / n
				k -= index * facto[n - i]; // or k = k % fact[n - i];
				sb.append(list.get(index + 1));
				list.remove(index);
			}
			
			return sb.toString();
		}
		
		public String getPermutation2(int n, int k) {
			List<Integer> num = new LinkedList<Integer>();
			for (int i = 1; i <= n; i++) num.add(i);
			int[] fact = new int[n];  // factorial
			fact[0] = 1;
			for (int i = 1; i < n; i++) fact[i] = i * fact[i - 1];
			k = k - 1;
			StringBuilder sb = new StringBuilder();
			for (int i = n; i > 0; i--) {
				int ind = k / fact[i - 1];
				k = k % fact[i - 1];
				sb.append(num.get(ind));
				num.remove(ind);
			}
			return sb.toString();
		}
	}
	
	class Solution_NextPermutation {
		
		public String getPermutation(int n, int k) {
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = i + 1;
			}
			
			for (int i = 1; i < k; i++) {
				nextPermutation(nums);
			}
			
			StringBuilder builder = new StringBuilder();
			for (int num : nums) {
				builder.append(num);
			}
			return builder.toString();
		}
		
		public void nextPermutation(int[] nums) {
			int n = nums.length;
			if (n == 0 || n == 1)
				return;
			int idx = -1;
			for (int i = n - 2; i >= 0; i--) {
				if (nums[i] < nums[i + 1]) {
					idx = i;
					break;
				}
			}
			
			if (idx >= 0) {
				int i = n - 1;
				while (i >= 0 && nums[i] <= nums[idx]) {
					i--;
				}
				swap(nums, i, idx);
			}
			
			reverse(nums, idx + 1, n - 1);
		}
		
		private void reverse(int[] nums, int i, int j) {
			while (i < j) {
				swap(nums, i, j);
				i++;
				j--;
			}
		}
		
		private void swap(int[] nums, int i, int j) {
			int tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
		}
	}
}
