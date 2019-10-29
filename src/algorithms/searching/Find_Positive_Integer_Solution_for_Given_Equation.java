package algorithms.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao on 10/26/2019.
 * @see algorithms.sort.Search_a_2D_Matrix_II same problem
 */
public class Find_Positive_Integer_Solution_for_Given_Equation {
	
	public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= 1000; j++) {
				if (customfunction.f(i, j) == z) {
					res.add(Arrays.asList(i, j));
				}
				if (customfunction.f(i, j) > z) {
					break;
				}
			}
		}
		return res;
	}
	
	
	static class CustomFunction {
		// Returns f(x, y) for any given positive integers x and y.
		// Note that f(x, y) is increasing with respect to both x and y.
		// i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
		public int f(int x, int y) {
			return 0;
		}
	}
	
	
	public List<List<Integer>> findSolution_Sol(CustomFunction customfunction, int z) {
		List<List<Integer>> res = new ArrayList<>();
		int x = 1, y = 1000;
		while (x <= 1000 && y > 0) {
			int v = customfunction.f(x, y);
			if (v > z) --y;
			else if (v < z) ++x;
			else res.add(Arrays.asList(x++, y--));
		}
		return res;
	}
	
	// left and right change when bs
	public List<List<Integer>> findSolution_bsearch(CustomFunction customFunction, int z) {
		List<List<Integer>> ans = new ArrayList<>();
		int left = 1, right = 1001;
		for (int x = 1; x < 1001; ++x) {
			int l = left, r = right;
			while (l < r) {
				int y = (l + r) / 2;
				if (customFunction.f(x, y) < z) {
					l = y + 1;
				} else {
					r = y;
				}
			}
			int val = customFunction.f(x, l);
			if (val >= z) {
				if (val == z) ans.add(Arrays.asList(x, l));
				right = l;
			} else {
				left = l;
			}
		}
		return ans;
	}
}
