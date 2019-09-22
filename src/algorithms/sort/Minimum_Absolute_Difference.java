package algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao Wang on 9/21/2019
 */
public class Minimum_Absolute_Difference {
	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		int n = arr.length;
		List<List<Integer>> ret = new ArrayList<>();
		Arrays.sort(arr);
		int mindis = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			mindis = Math.min(mindis, arr[i] - arr[i - 1]);
		}
		
		for (int i = 1; i < n; i++) {
			if (arr[i] - arr[i - 1] == mindis) {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(arr[i - 1]);
				tmp.add(arr[i]);
				ret.add(tmp);
			}
		}
		
		return ret;
	}
}
