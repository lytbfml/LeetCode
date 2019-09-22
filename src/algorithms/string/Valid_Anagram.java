package algorithms.string;

/**
 * @author Yangxiao on 11/12/2018.
 */

import java.util.Arrays;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 */
class Valid_Anagram {
	
	public boolean isAnagram(String s, String t) {
		int n1 = s.length();
		int n2 = t.length();
		if (n1 != n2) { return false; }
		char[] s1 = s.toCharArray();
		char[] t1 = t.toCharArray();
		Arrays.sort(s1);
		Arrays.sort(t1);
		for (int i = 0; i < n1; i++) {
			if (s1[i] != t1[i]) { return false; }
		}
		
		return true;
	}
	
	public boolean isAnagram_Sol1(String s, String t) {
		int n1 = s.length();
		int n2 = t.length();
		if (n1 != n2) { return false; }
		int[] arr = new int[26];
		for (int i = 0; i < n1; i++) {
			arr[s.charAt(i) - 'a']++;
			arr[t.charAt(i) - 'a']--;
		}
		for (int i : arr) {
			if (i != 0) { return false; }
		}
		return true;
	}
	
	public boolean isAnagram_Sol2(String s, String t) {
		int[] freqS = new int[26];
		int[] freqT = new int[26];
		for (char ch : s.toCharArray()) {
			freqS[ch - 'a'] += 1;
		}
		
		for (char ch : t.toCharArray()) {
			freqT[ch - 'a'] += 1;
		}
		
		for (int i = 0; i < 26; i++) {
			if (freqS[i] != freqT[i]) {
				return false;
			}
		}
		return true;
	}
}
