package algorithms.sliding_window;

/**
 * @author Yangxiao on 9/28/2019.
 */
public class Get_Equal_Substrings_Within_Budget {
	
	public static void main(String[] args) {
		Get_Equal_Substrings_Within_Budget cs = new Get_Equal_Substrings_Within_Budget();
		cs.equalSubstring("abcd", "bcdf", 3);
		cs.equalSubstring("abcd", "cdef", 3);
		cs.equalSubstring("abcd", "acde", 0);
		cs.equalSubstring("abcd", "cdef", 1);
	}
	
	public int equalSubstring(String s, String t, int maxCost) {
		int n = s.length();
		char[] cs = s.toCharArray();
		char[] ct = t.toCharArray();
		int[] cost = new int[n];
		for (int i = 0; i < n; i++) {
			cost[i] = Math.abs(cs[i] - ct[i]);
		}
		
		int res = 0, left = 0, cur = 0;
		for (int i = 0; i < n; i++) {
			if (cost[i] > maxCost) {
				left = i + 1;
				cur = 0;
				continue;
			}
			while (cur + cost[i] > maxCost) {
				cur -= cost[left++];
			}
			cur += cost[i];
			res = Math.max(res, i - left + 1);
		}
		return res;
	}
	
	public int equalSubstring2(String s, String t, int k) {
		int n = s.length(), i = 0, j;
		int[] A = new int[n];
		for (j = 0; j < n; ++j) {
			A[j] = Math.abs(s.charAt(j) - t.charAt(j));
			k -= A[j];
			if (k < 0) k += A[i++];
		}
		return j - i;
	}
}
