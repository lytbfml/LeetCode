package algorithms.sliding_window;

/**
 * @author Yangxiao Wang on 2019-07-24
 */
public class Longest_Substring_with_At_Most_K_Distinct_Characters {
	
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int n = s.length(), cnt = 0, start = 0, res = 0;
		int[] mem = new int[128];
		char[] ss = s.toCharArray();
		for (int i = 0; i < n; i++) {
			if (mem[ss[i]]++ == 0) {
				cnt++;
			}
			while (cnt > k) {
				if (mem[ss[start++]]-- == 1) {
					cnt--;
				}
			}
			res = Math.max(res, i - start + 1);
		}
		return res;
	}
}
