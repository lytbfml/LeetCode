package algorithms.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangxiao Wang on 8/10/2019
 */
public class Swap_For_Longest_Repeated_Character_Substring {
	
	public static void main(String[] args) {
		Swap_For_Longest_Repeated_Character_Substring cs = new Swap_For_Longest_Repeated_Character_Substring();
		System.out.println(cs.maxRepOpt1("ababa"));
		System.out.println(cs.maxRepOpt1("aaabaaa"));
		System.out.println(cs.maxRepOpt1("aaabbaaa"));
		System.out.println(cs.maxRepOpt1("aaaaa"));
		System.out.println(cs.maxRepOpt1("abcdef"));
		System.out.println(cs.maxRepOpt1("bbababaaaa"));
	}
	
	public int maxRepOpt1(String text) {
		int n = text.length();
		int[] mem = new int[26];
		char[] cc = text.toCharArray();
		for (int i = 0; i < n; i++) {
			mem[cc[i] - 'a']++;
		}
		
		int res = 0;
		for (int i = 0; i < n; i++) {
			if (mem[cc[i] - 'a'] > res) {
				if (mem[cc[i] - 'a'] == 1) {
					res = 1;
					continue;
				}
				
				char curChar = cc[i];
				int diff = 0, start = i;
				while (start < n && diff <= 1) {
					start++;
					if (start == n) break;
					if (cc[start] != curChar || mem[curChar - 'a'] == (start - i)) {
						diff++;
					}
				}
				
				res = Math.max(res, start - i);
			}
		}
		// for case like abbaa
		for (int i = n - 1; i >= 0; i--) {
			if (mem[cc[i] - 'a'] > res) {
				if (mem[cc[i] - 'a'] == 1) {
					res = 1;
					continue;
				}
				
				char curChar = cc[i];
				int diff = 0, start = i;
				while (start >= 0 && diff <= 1) {
					start--;
					if (start == 0) break;
					if (cc[start] != curChar || mem[curChar - 'a'] == (i - start)) {
						diff++;
					}
				}
				
				res = Math.max(res, i - start);
			}
		}
		
		return res;
	}
	
	class Solution {
		public int maxRepOpt1(String text) {
			List<CharState> stateList = new ArrayList<CharState>();
			int startPos = 0, endPos = 0;
			char c = text.charAt(0);
			int i = 1;
			
			int[] charCount = new int[26];
			
			charCount[c - 'a']++;
			
			while (i < text.length()) {
				char cur = text.charAt(i);
				charCount[cur - 'a']++;
				
				if (cur != c) {
					stateList.add(new CharState(c, startPos, endPos));
					c = cur;
					startPos = i;
					endPos = i;
				} else {
					endPos = i;
				}
				
				i++;
			}
			
			stateList.add(new CharState(c, startPos, endPos));
			
			int maxLen = 1;
			// scenario 1, aaabba, find another a to replace b to increase maxlen+1;
			for (i = 0; i < stateList.size(); i++) {
				CharState cs = stateList.get(i);
				int len = cs.endPos - cs.startPos + 1;
				
				if (len < charCount[cs.c - 'a']) {
					len++;
				}
				
				maxLen = Math.max(maxLen, len);
			}
			
			// scenario 2, aaabaa, find another a to replace b
			
			for (i = 1; i < stateList.size() - 1; i++) {
				CharState before = stateList.get(i - 1);
				CharState cs = stateList.get(i);
				CharState after = stateList.get(i + 1);
				
				int beforeLen = before.endPos - before.startPos + 1;
				int afterLen = after.endPos - after.startPos + 1;
				
				if (cs.startPos == cs.endPos && before.c == after.c) {
					int totalLen = beforeLen + afterLen;
					if (beforeLen + afterLen < charCount[before.c - 'a']) {
						totalLen++;
					}
					maxLen = Math.max(maxLen, totalLen);
				}
			}
			
			return maxLen;
		}
		
		public class CharState {
			char c;
			int startPos;
			int endPos;
			
			public CharState(char ch, int s, int e) {
				c = ch;
				startPos = s;
				endPos = e;
			}
		}
	}
}
