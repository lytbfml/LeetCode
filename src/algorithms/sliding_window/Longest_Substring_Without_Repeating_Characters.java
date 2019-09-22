package algorithms.sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * @author Yangxiao Wang on 5/17/2019
 */
public class Longest_Substring_Without_Repeating_Characters {
	
	public int lengthOfLongestSubstring_sliding(String s) {
		int n = s.length();
		int start = 0, res = 0, cnt = 0;
		char[] ss = s.toCharArray();
		int[] mem = new int[128];
		for (int i = 0; i < n; i++) {
			if (mem[ss[i]]++ > 0) {
				while (true) if (mem[ss[start++]]-- == 2) break;
			}
			res = Math.max(res, i - start + 1);
		}
		
		return res;
	}
	
	
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		int n = s.length();
		Map<Character, Integer> map = new HashMap<>();
		int len = 0;
		int max = 0;
		for (int i = 0; i < n; i++) {
			char temp = s.charAt(i);
			if (!map.containsKey(temp) || map.get(temp) < i - len) {
				map.put(temp, i);
				len++;
			} else {
				len = i - map.get(temp);
				map.put(temp, i);
			}
			max = Math.max(len, max);
		}
		return max;
	}
	
	public int lengthOfLongestSubstring_Sol2(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max = 0;
		for (int i = 0, j = 0; i < s.length(); ++i) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(j, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}
	
	public int lengthOfLongestSubstring_fast2(String s) {
		int n = s.length(), ans = 0;
		int[] index = new int[128]; // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			i = Math.max(index[s.charAt(j)], i);
			ans = Math.max(ans, j - i + 1);
			index[s.charAt(j)] = j + 1;
		}
		return ans;
	}
	
	public int lengthOfLongestSubstring_Fast(String s) {
		char[] array = s.toCharArray();
		int start = 0;
		int maxLen = 0;
		if (array.length == 0)
			return 0;
		if (array.length == 1)
			return 1;
		for (int cursor = 1; cursor < array.length; cursor++) {
			for (int i = start; i < cursor; i++) {
				if (array[i] == array[cursor]) {
					maxLen = maxLen < (cursor - start) ? cursor - start : maxLen;
					start = i + 1;
					break;
				}
			}
		}
		return maxLen < (array.length - start) ? array.length - start : maxLen;
	}
	
	public int lengthOfLongestSubstring_oneset(String s) {
		int i = 0, j = 0, max = 0;
		Set<Character> set = new HashSet<>();
		
		while (j < s.length()) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				max = Math.max(max, set.size());
			} else {
				// i++ but j is not changed
				set.remove(s.charAt(i++));
			}
		}
		
		return max;
	}
	
	public int lengthOfLongestSubstring_prevBad(String s) {
		String sb = "";
		int len = 0;
		for (int i = 0; i < s.length(); i++) {
			String x = String.valueOf(s.charAt(i));
			if (sb.lastIndexOf(x) == -1) {
				sb += x;
			} else {
				if (len < sb.length()) {
					len = sb.length();
				}
				sb = sb.substring(sb.lastIndexOf(x) + 1);
				sb += x;
			}
		}
		
		return len > sb.length() ? len : sb.length();
	}
	
	public int lengthOfLongestSubstring_Sol1(String s) {
		char[] chars = s.toCharArray();
		if (chars.length == 0)
			return 0;
		int current = 0;
		int last = 0;
		int length = 1;
		for (int i = 1; i < chars.length; i++) {
			for (int y = current; y < i; y++) {
				if (chars[i] == chars[y]) {
					int cLength = i - current;
					length = cLength > length ? cLength : length;
					current = y + 1;
					last = y;
					break;
				}
				last = i;
			}
		}
		return length > last - current + 1 ? length : last - current + 1;
	}
	
	public int lengthOfLongestSubstring_Sol3(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>(); // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}
}
