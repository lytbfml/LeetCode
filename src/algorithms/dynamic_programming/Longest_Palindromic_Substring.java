package algorithms.dynamic_programming;

/**
 * @author Yangxiao on 4/16/2019.
 */
public class Longest_Palindromic_Substring {
	
	public static void main(String[] args) {
		Longest_Palindromic_Substring cs = new Longest_Palindromic_Substring();
		System.out.println(cs.longestPalindrome_ManachersAlgorithm("abababa"));
		System.out.println(cs.longestPalindrome_ManachersAlgorithm("abaaba"));
		System.out.println(cs.longestPalindrome_ManachersAlgorithm("babcbabcbaccba"));
		System.out.println(cs.longestPalindrome_ManachersAlgorithm("a"));
	}
	
	public String longestPalindrome_ManachersAlgorithm(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int n = s.length();
		int N = 2 * n + 1;
		int[] dp = new int[N];
		dp[0] = 0;
		dp[1] = 1;
		
		int centerPos = 1;
		int centerR = 2;
		int curR = 0;
		int curL = 0;
		int maxCenter = 1;
		int maxLen = 1;
		
		
		for (curR = 2; curR < N; curR++) {
			boolean expand = false;
			curL = centerPos * 2 - curR;
			int dis = centerR - curR;
			if (dis > 0) {
				if (dp[curL] < dis) { // case 1
					dp[curR] = dp[curL];
				} else if (dp[curL] == dis && curR == N - 1) {
					dp[curR] = dp[curL];
				} else if (dp[curL] == dis && curR < N - 1) {
					dp[curR] = dp[curL];
					expand = true;
				} else if (dp[curL] > dis) {
					dp[curR] = dis;
					expand = true;
				}
			} else {
				dp[curR] = 0;
				expand = true;
			}
			
			if (expand) {
				while (((curR + dp[curR]) < N - 1 && (curR - dp[curR]) > 0) &&
						(((curR + dp[curR] + 1) % 2 == 0) || (s.charAt((curR + dp[curR] + 1) / 2) ==
								s.charAt((curR - dp[curR] - 1) / 2)))) {
					dp[curR]++;
				}
			}
			
			if (dp[curR] > maxLen) {
				maxLen = dp[curR];
				maxCenter = curR;
			}
			
			if (curR + dp[curR] > centerR) {
				centerPos = curR;
				centerR = curR + dp[curR];
			}
		}
		// System.out.println(maxCenter + ", " + maxLen);
		// System.out.println(Arrays.toString(dp));
		
		int start = (maxCenter - maxLen) / 2;
		int end = (maxCenter + maxLen) / 2;
		
		// System.out.println(start + ", end " + end);
		// if (start == end) {
		// 	return s.substring(start, end + 1);
		// }
		return s.substring(start, end);
	}
	
	public String longestPalindrome_2(String s) {
		if (isPalindrome(s)) {
			return s;
		}
		int startPos = 0;
		int maxLength = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = maxPalinAt(s, i, i);
			int len2 = maxPalinAt(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (maxLength < len) {
				maxLength = len;
				startPos = i - ((len - 1) / 2);
			}
		}
		
		return s.substring(startPos, startPos + maxLength);
	}
	
	private int maxPalinAt(String s, int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}
		return j - i - 1;
	}
	
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int n = s.length();
		int left = 0, right = 0;
		for (int i = 0; i < n; i++) {
			int len1 = lenOfPalindrome(s, i, i);
			int len2 = lenOfPalindrome(s, i, i + 1);
			int maxLen = Math.max(len1, len2);
			if (maxLen > right - left) {
				left = i - (maxLen - 1) / 2;
				right = i + maxLen / 2;
			}
		}
		return s.substring(left, right + 1);
	}
	
	private int lenOfPalindrome(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return right - left - 1;
	}
	
	public String longestPalindrome_bruteForce(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int n = s.length();
		String max = "";
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				String temp = s.substring(i, j);
				if (isPalindrome(temp)) {
					if (j - i > max.length()) {
						max = temp;
					}
				}
			}
		}
		return max;
	}
	
	public boolean isPalindrome(String s) {
		String rev = new StringBuffer(s).reverse().toString();
		return s.equals(rev);
	}
	
	private boolean isPalindrome_2(String s1) {
		int i = 0, j = s1.length() - 1;
		while (i < j) {
			if (s1.charAt(i) != s1.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	public class Solution_FastestButOn2 {
		int len = 0, maxLength = 0, init = 0;
		
		public String longestPalindrome(String s) {
			char[] chars = s.toCharArray();
			len = s.length();
			if (len <= 1) { return s; }
			for (int i = 0; i < len; i++) {
				i = manacher(chars, i);
			}
			return s.substring(init, init + maxLength);
		}
		
		public int manacher(char[] chars, int k) {
			int i = k - 1, j = k;
			while (j < len - 1 && chars[j] == chars[j + 1])
				j++;
			int nextCenter = j++;
			while (i >= 0 && j < len && chars[i] == chars[j]) {
				i--;
				j++;
			}
			if (j - i - 1 > maxLength) {
				maxLength = j - i - 1;
				init = i + 1;
			}
			return nextCenter;
		}
	}
}
