package algorithms.sort;

import java.util.Collections;
import java.util.List;

/**
 * @author Yangxiao on 11/1/2018.
 */

class Get_Minimum_Unique_Sum {
	
	public static int getMinimumUniqueSum(List<Integer> arr) {
		int size = arr.size();
		Collections.sort(arr);
		int temp = arr.get(0);
		int sum = temp;
		for (int i = 1; i < size; i++) {
			int cur = arr.get(i);
			if (cur <= temp) {
				cur = temp + 1;
			}
			sum += cur;
			temp = cur;
		}
		return sum;
	}
}
