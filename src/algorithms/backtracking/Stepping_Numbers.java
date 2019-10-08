package algorithms.backtracking;

import algorithms.tree.TreeNode;

import java.util.*;

/**
 * @author Yangxiao on 10/5/2019.
 */
public class Stepping_Numbers {
	
	public List<Integer> countSteppingNumbers(int low, int high) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			dfs(low, high, i, res);
		}
		Collections.sort(res);
		return res;
	}
	
	private void dfs(int low, int hi, long stepNum, List<Integer> res) {
		if (stepNum <= hi && stepNum >= low) {
			res.add((int)stepNum);
		}
		if (stepNum == 0 || stepNum > hi) {
			return;
		}
		long lastD = stepNum % 10;
		long stepNumA = stepNum * 10 + (lastD - 1);
		long stepNumB = stepNum * 10 + (lastD + 1);
		
		if (lastD == 0) {
			dfs(low, hi, stepNumB, res);
		} else if (lastD == 9) {
			dfs(low, hi, stepNumA, res);
		} else {
			dfs(low, hi, stepNumA, res);
			dfs(low, hi, stepNumB, res);
		}
	}
	class solution {
		public List<Integer> countSteppingNumbers2(int low, int high) {
			List<Integer> list = new ArrayList();
			
			for (long i = 0; i <= 9; i++) {
				dfs(low, high, i, list);
			}
			Collections.sort(list);
			return list;
		}
		
		private void dfs(int low, int high, long cur, List<Integer> list) {
			if (cur >= low && cur <= high) list.add((int) cur);
			if (cur == 0 || cur > high) return;
			
			long last = cur % 10, inc = cur * 10 + last + 1, dec = cur * 10 + last - 1;
			
			if (last == 0) {
				dfs(low, high, inc, list);
			} else if (last == 9) {
				dfs(low, high, dec, list);
			} else {
				dfs(low, high, inc, list);
				dfs(low, high, dec, list);
			}
		}
	}
}
