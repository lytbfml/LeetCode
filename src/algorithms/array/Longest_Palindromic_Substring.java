package algorithms.array;

/**
 * See Algorithms.Dynamic_Programming for full sol
 * @author Yangxiao Wang on 5/17/2019
 */
public class Longest_Palindromic_Substring {
	
	public String longestPalindrome(String s) {
		if (s == null || s.isEmpty())
			return "";
		int n = s.length();
		if (n == 1)
			return s;
		if (isPalindrome(s))
			return s;
		
		int max = 0, start = 0;
		
		for (int i = 0; i < n; i++) {
			int lenC = isPalindorme(i, i, s);
			int lenD = isPalindorme(i, i + 1, s);
			int len = Math.max(lenC, lenD);
			if (len > max) {
				max = len;
				start = i - (len - 1) / 2;
			}
		}
		// 0 1 2 3 4 5 6
		return s.substring(start, start + max);
	}
	
	public int isPalindorme(int i, int j, String s) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}
		return j - i - 1;
	}
	
	public boolean isPalindrome(String s) {
		String rev = new StringBuffer(s).reverse().toString();
		return s.equals(rev);
	}
}
