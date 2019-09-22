package algorithms.dynamic_programming;

/**
 * wc 148
 * @author Yangxiao Wang on 2019-08-03
 */
public class Longest_Chunked_Palindrome_Decomposition {
	
	public int longestDecomposition(String text) {
		for (int i = 1; i <= text.length() / 2; i++)
			if (text.substring(0, i).equals(text.substring(text.length() - i, text.length())))
				return 2 + longestDecomposition(text.substring(i, text.length() - i));
		return text.length() == 0 ? 0 : 1;
	}
	
	public int longestDecomposition_Greedy(String S) {
		int res = 0, n = S.length();
		String l = "", r = "";
		for (int i = 0; i < n; ++i) {
			l = l + S.charAt(i);
			r = S.charAt(n - i - 1) + r;
			if (l.equals(r)) {
				++res;
				l = "";
				r = "";
			}
		}
		return res;
	}
	
	static class Solution {
		public static int longestDecomposition(String text) {
			char[] chars = text.toCharArray();
			return longestDecomposition2(chars, 0, chars.length - 1);
		}
		
		private static int longestDecomposition2(char[] chars, int start, int end) {
			int middle = (end + start) / 2;
			for (int i = end; i > middle; i--) {
				if (chars[i] == chars[start]) {
					int count = end - i + 1;
					boolean found = true;
					while (count > 0) {
						if (chars[start + count - 1] != chars[i + count - 1]) {
							found = false;
							break;
						}
						count--;
					}
					if (found) {
						return 2 + longestDecomposition2(chars, start + end - i + 1, i - 1);
					}
				}
			}
			
			return end < start ? 0 : 1;
		}
		
		
	}
}
