package algorithms.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 7/13/2019
 */
public class Longest_WellPerforming_Interval {
	
	public int longestWPI(int[] hours) {
		int n = hours.length;
		int max = 0;
		for (int i = 0; i < n; i++) {
			int tire = 0;
			for (int j = i; j < n; j++) {
				if (hours[j] > 8) tire++;
				if (j - i + 1 - tire < tire) max = Math.max(j - i + 1, max);
			}
		}
		return max;
	}
	
	public int longestWPI_good(int[] hours) {
		int res = 0, score = 0, n = hours.length;
		Map<Integer, Integer> seen = new HashMap<>();
		seen.put(0, -1);
		for (int i = 0; i < n; ++i) {
			score += hours[i] > 8 ? 1 : -1;
			if (score > 0) {
				res = i + 1;
			} else {
				seen.putIfAbsent(score, i);
				if (seen.containsKey(score - 1))
					res = Math.max(res, i - seen.get(score - 1));
			}
		}
		return res;
	}
}
