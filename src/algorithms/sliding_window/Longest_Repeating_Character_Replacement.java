package algorithms.sliding_window;

/**
 * @author Yangxiao Wang on 2019-07-03
 */
public class Longest_Repeating_Character_Replacement {
	
	/**
	 * There's no edge case for this question. The initial step is to extend the window to its limit, that is, the
	 * longest we can get to with maximum number of modifications. Until then the variable start will remain at 0.
	 * <p>
	 * Then as end increase, the whole substring from 0 to end will violate the rule, so we need to update start
	 * accordingly (slide the window). We move start to the right until the whole string satisfy the constraint again.
	 * Then each time we reach such situation, we update our max length.
	 *
	 * @param s
	 * @param k
	 * @return
	 */
	public int characterReplacement(String s, int k) {
		if (s.isEmpty()) return 0;
		int n = s.length();
		if (n <= k) return n;
		int[] mm = new int[26];
		int left = 0;
		int res = 0, cnt = 0;
		for (int right = 0; right < n; right++) {
			cnt = Math.max(cnt, ++mm[s.charAt(right) - 'A']);
			while (right - left + 1 - cnt > k) {
				mm[s.charAt(left++) - 'A']--;
			}
			res = Math.max(res, right - left + 1);
		}
		return res;
	}
}
