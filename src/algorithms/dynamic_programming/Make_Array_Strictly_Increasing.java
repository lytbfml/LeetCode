package algorithms.dynamic_programming;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Yangxiao on 9/7/2019.
 */
public class Make_Array_Strictly_Increasing {
	
	public int makeArrayIncreasing(int[] arr1, int[] arr2) {
		TreeSet<Integer> candidates = new TreeSet<>();
		for (int num : arr2) {
			candidates.add(num);
		}
		
		HashMap<Integer, Integer> endingValueWithMinSwap = new HashMap<>(), next = null;
		endingValueWithMinSwap.put(arr1[0], 0);
		if (candidates.first() < arr1[0]) {
			endingValueWithMinSwap.put(candidates.first(), 1);
		}
		
		for (int i = 1; i < arr1.length; ++i) {
			next = new HashMap<>();
			for (Map.Entry<Integer, Integer> entry : endingValueWithMinSwap.entrySet()) {
				// no need to replace
				if (arr1[i] > entry.getKey()) {
					if (!next.containsKey(arr1[i])) {
						next.put(arr1[i], entry.getValue());
					} else {
						next.put(arr1[i], Math.min(next.get(arr1[i]), entry.getValue()));
					}
				}
				
				// replace with possible candidates from arr2
				Integer candidate = candidates.higher(entry.getKey());
				if (candidate != null) {
					if (!next.containsKey(candidate)) {
						next.put(candidate, 1 + entry.getValue());
					} else {
						next.put(candidate, Math.min(next.get(candidate), 1 + entry.getValue()));
					}
				}
			}
			
			endingValueWithMinSwap = next;
			if (endingValueWithMinSwap.size() == 0) {
				return -1;
			}
		}
		
		int minReplace = arr1.length;
		for (Map.Entry<Integer, Integer> entry : endingValueWithMinSwap.entrySet()) {
			minReplace = Math.min(entry.getValue(), minReplace);
		}
		
		return minReplace;
	}
}
