package algorithms.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yangxiao Wang on 2019-07-23
 */
public class Minimum_Window_Substring {
	
	public static void main(String[] args) {
		Minimum_Window_Substring cs = new Minimum_Window_Substring();
		System.out.println(cs.minWindow("bbaa", "aba"));
		System.out.println(cs.minWindow("ADOBECODEBANC", "ABC")); // "BANC"
	}
	
	public String minWindow(String s, String t) {
		Map<Character, Integer> set = new HashMap<>();
		int m = t.length();
		int n = s.length();
		if (m > n) return "";
		for (char c : t.toCharArray()) {
			set.put(c, set.getOrDefault(c, 0) + 1);
		}
		
		char[] source = s.toCharArray();
		int l = 0;
		int resL = 0, resR = -1;
		
		Map<Character, Integer> map = new HashMap<>();
		int mapCnt = 0; // if mapCnt >= m, we found a valid window
		
		for (int i = 0; i < n; i++) {
			if (!set.containsKey(source[i])) continue;
			map.put(source[i], map.getOrDefault(source[i], 0) + 1);
			if (map.get(source[i]) <= set.get(source[i])) {
				mapCnt++;
			}
			if (mapCnt >= m) {
				while ((!set.containsKey(source[l])) || map.get(source[l]) > set.get(source[l])) {
					if (set.containsKey(source[l])) {
						map.put(source[l], map.get(source[l]) - 1);
					}
					l++;
				}
				if (resR == -1) resR = i;
				if ((i - l + 1) < (resR - resL + 1)) {
					resL = l;
					resR = i;
				}
			}
		}
		
		return s.substring(resL, resR + 1);
	}
	
	public String minWindow_improve(String s, String t) {
		if (t.length() > s.length()) {
			return "";
		}
		int m = t.length(), n = s.length();
		char[] source = s.toCharArray();
		int[] target = new int[128];
		for (char c : t.toCharArray()) {
			target[c]++;
		}
		int l = 0;
		int resL = 0, resR = -1;
		int[] mem = new int[128];
		
		int mapCnt = 0;
		
		for (int i = 0; i < n; i++) {
			if (target[source[i]] == 0) continue;
			mem[source[i]]++;
			if (mem[source[i]] <= target[source[i]]) {
				mapCnt++;
			}
			if (mapCnt >= m) {
				while ((target[source[l]] == 0) || mem[source[l]] > target[source[l]]) {
					if (target[source[l]] != 0) {
						mem[source[l]]--;
					}
					l++;
				}
				if (resR == -1) resR = i;
				if ((i - l + 1) < (resR - resL + 1)) {
					resL = l;
					resR = i;
				}
			}
		}
		return s.substring(resL, resR + 1);
	}
	
	public String minWindow_sol(String s, String t) {
		int[] map = new int[128];
		for (char c : t.toCharArray()) map[c]++;
		int counter = t.length(), begin = 0, end = 0, d = Integer.MAX_VALUE, head = 0;
		char[] source = s.toCharArray();
		while (end < source.length) {
			if (map[source[end++]]-- > 0) counter--;
			while (counter == 0) {
				if (end - begin < d) d = end - (head = begin);
				if (map[source[begin++]]++ == 0) counter++;
			}
		}
		return d == Integer.MAX_VALUE ? "" : s.substring(head, head + d - 1);
	}
}
