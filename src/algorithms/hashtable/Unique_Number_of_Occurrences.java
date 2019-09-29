package algorithms.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Yangxiao on 9/28/2019.
 */
public class Unique_Number_of_Occurrences {
	
	public boolean uniqueOccurrences(int[] arr) {
		int n = arr.length, max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			int prev = map.getOrDefault(arr[i], 0) + 1;
			max = Math.max(prev, max);
			map.put(arr[i], prev);
		}
		
		boolean[] mem = new boolean[max + 1];
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			if (mem[e.getValue()]) {
				return false;
			}
			mem[e.getValue()] = true;
		}
		
		return true;
	}
	
	public boolean uniqueOccurrences2(int[] arr) {
		Map<Integer, Integer> count = new HashMap<>();
		for (int a : arr) {
			count.put(a, 1 + count.getOrDefault(a, 0));
		}
		return count.size() == new HashSet<>(count.values()).size();
	}
	
}
