package algorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao on 10/26/2019.
 * @see GreyCode
 */
public class Circular_Permutation_Binary_Representation {
	
	// ??
	public List<Integer> circularPermutation(int n, int start) {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < 1 << n; ++i)
			res.add(start ^ i ^ i >> 1);
		return res;
	}
	
	public List<Integer> circularPermutation1(int n, int start) {
		List<Integer> res = new ArrayList<>();
		List<Integer> tmp = oneBitDiffPermutation(n); // generate one bit diff permutations
		
		// reorganize the elememnts in tmp
		// before: {0, 1, ..., start, ..., end}
		// after:  {start, ..., end, 0, 1, ...}
		int i = 0;
		for (; i < tmp.size(); i++) {
			if (tmp.get(i) == start) {
				break;
			}
		}
		
		for (int k = i; k < tmp.size(); k++) {
			res.add(tmp.get(k));
		}
		
		for (int q = 0; q < i; q++) {
			res.add(tmp.get(q));
		}
		
		return res;
	}
	
	// generate one bit diff permutations
	// 0, 1
	// 0, 1, 11, 10
	// 000, 001, 011, 010, 110, 111, 101, 100
	public List<Integer> oneBitDiffPermutation(int n) {
		List<Integer> tmp = new ArrayList<>();
		tmp.add(0);
		if (n == 0) {
			return tmp;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = tmp.size() - 1; j >= 0; j--) {
				tmp.add(tmp.get(j) + (1 << i));
			}
		}
		
		return tmp;
	}
}
