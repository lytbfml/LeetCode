package algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 11/9/2019.
 */
public class Reconstruct_a_Row_Binary_Matrix {
	
	public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
		List<List<Integer>> res = new ArrayList<>();
		res.add(new ArrayList<>());
		res.add(new ArrayList<>());
		int sum = 0;
		int n = colsum.length;
		
		for (int i = 0; i < n; i++) {
			sum += colsum[i];
		}
		if ((upper + lower) != sum) {
			return new ArrayList<>();
		}
		
		for (int i = 0; i < n; i++) {
			if (colsum[i] == 2) {
				upper--;
				lower--;
				if (upper < 0 || lower < 0) {
					return new ArrayList<>();
				}
				res.get(0).add(1);
				res.get(1).add(1);
			} else if (colsum[i] == 1) {
				res.get(0).add(-1);
				res.get(1).add(-1);
			} else {
				res.get(0).add(0);
				res.get(1).add(0);
			}
		}
		
		for (int i = 0; i < n; i++) {
			if (res.get(0).get(i) == -1) {
				if (upper > 0) {
					upper--;
					res.get(0).set(i, 1);
					res.get(1).set(i, 0);
				} else if (lower > 0) {
					lower--;
					res.get(0).set(i, 0);
					res.get(1).set(i, 1);
				} else {
					return new ArrayList<>();
				}
			}
		}
		
		return res;
	}
}
