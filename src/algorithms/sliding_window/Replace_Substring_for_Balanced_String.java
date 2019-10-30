package algorithms.sliding_window;

/**
 * @author Yangxiao Wang on 10/30/2019
 */
public class Replace_Substring_for_Balanced_String {
	
	public static void main(String[] args) {
		Replace_Substring_for_Balanced_String cs = new Replace_Substring_for_Balanced_String();
		System.out.println(cs.balancedString("QWER"));
		System.out.println(cs.balancedString("QQWE"));
		System.out.println(cs.balancedString("QQQW"));
		System.out.println(cs.balancedString("QQQQ"));
		System.out.println(cs.balancedString("WWQQRRRRQRQQ"));
	}
	
	public int balancedString(String s) {
		int n = s.length(), avg = n / 4;
		int[] mem = new int[4];
		for (int i = 1; i <= n; i++) {
			mem[conv(s.charAt(i - 1))]++;
		}
		
		boolean matched = true;
		for (int i = 0; i < 4; i++) {
			mem[i] = avg - mem[i];
			matched = mem[i] == 0 && matched;
		}
		if (matched) return 0;
		int left = 0, res = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			mem[conv(s.charAt(i))]++;
			while (check(mem, i - left + 1)) {
				res = Math.min(res, i - left + 1);
				mem[conv(s.charAt(left++))]--;
			}
		}
		
		return res;
	}
	
	private boolean check(int[] missing, int len) {
		int total = 0;
		for (int i = 0; i < missing.length; i++) {
			if (missing[i] < 0) {
				return false;
			}
			total += missing[i];
		}
		return total <= len;
	}
	
	private int conv(char cc) {
		if (cc == 'Q') {
			return 0;
		} else if (cc == 'W') {
			return 1;
		} else if (cc == 'E') {
			return 2;
		} else {
			return 3;
		}
	}
	
	class Solution {
		
		public int balancedString(String s) {
			int[] count = new int[128];
			int n = s.length(), res = n, i = 0, k = n / 4;
			for (int j = 0; j < n; ++j) {
				++count[s.charAt(j)];
			}
			for (int j = 0; j < n; ++j) {
				--count[s.charAt(j)];
				while (i < n && count['Q'] <= k && count['W'] <= k && count['E'] <= k && count['R'] <= k) {
					res = Math.min(res, j - i + 1);
					++count[s.charAt(i++)];
				}
			}
			return res;
		}
		
	}
}
