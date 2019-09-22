package algorithms.string;

/**
 * @author Yangxiao on 11/14/2018.
 */

import java.util.ArrayList;

/**
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of
 * haystack.
 */
class Implement_strStr {
	
	public int strStr(String haystack, String needle) {
		if (needle == null || needle.isEmpty()) { return 0; }
		if (haystack == null || haystack.isEmpty()) { return -1; }
		if (needle.length() > haystack.length()) { return -1; }
		
		int n = haystack.length();
		int len = needle.length();
		char first = needle.charAt(0);
		int index = 0;
		
		if (haystack.indexOf(first) == -1) {
			return -1;
		}
		
		ArrayList<Integer> indexes = new ArrayList<>();
		while (index < n && index != -1) {
			index = haystack.indexOf(first, index);
			if (index == -1) {
				break;
			}
			indexes.add(index);
			index++;
		}
		
		for (int i = 0; i < indexes.size(); i++) {
			int cur = indexes.get(i);
			if ((n - cur) < len) {
				continue;
			}
			String tempS = haystack.substring(cur, cur + len);
			if (tempS.equals(needle)) {
				return cur;
			}
		}
		
		return -1;
	}
	
	
	public int strStr_Sol1(String haystack, String needle) {
		if (needle == null || needle.isEmpty()) { return 0; }
		if (haystack == null || haystack.isEmpty()) { return -1; }
		if (needle.length() > haystack.length()) { return -1; }
		char head = needle.charAt(0);
		int n = needle.length(), m = haystack.length();
		for (int i = 0; i < m - n + 1; i++) {
			if (haystack.charAt(i) == head) {
				if (needle.equals(haystack.substring(i, i + n))) { return i; }
			}
		}
		return -1;
	}
	
	public int strStr_Sol2(String haystack, String needle) {
		for (int i = 0; ; i++) {
			for (int j = 0; ; j++) {
				if (j == needle.length()) return i;
				if (i + j == haystack.length()) return -1;
				if (needle.charAt(j) != haystack.charAt(i + j)) break;
			}
		}
	}
	
	public static void main(String[] args) {
		Implement_strStr is = new Implement_strStr();
		int x = is.strStr("aaaaa", "bba");
		
		System.out.println(x);
	}
}
