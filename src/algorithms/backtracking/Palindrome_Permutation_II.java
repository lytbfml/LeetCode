package algorithms.backtracking;

import java.util.*;

/**
 * @author Yangxiao on 9/10/2019.
 */
public class Palindrome_Permutation_II {
	
	public class Solution {
		Set<String> set = new HashSet<>();
		
		public List<String> generatePalindromes(String s) {
			int[] map = new int[128];
			char[] st = new char[s.length() / 2];
			if (!canPermutePalindrome(s, map))
				return new ArrayList<>();
			char ch = 0;
			int k = 0;
			for (int i = 0; i < map.length; i++) {
				if (map[i] % 2 == 1)
					ch = (char) i;
				for (int j = 0; j < map[i] / 2; j++) {
					st[k++] = (char) i;
				}
			}
			permute(st, 0, ch);
			return new ArrayList<>(set);
		}
		
		public boolean canPermutePalindrome(String s, int[] map) {
			int count = 0;
			for (int i = 0; i < s.length(); i++) {
				map[s.charAt(i)]++;
				if (map[s.charAt(i)] % 2 == 0)
					count--;
				else
					count++;
			}
			return count <= 1;
		}
		
		public void swap(char[] s, int i, int j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
		}
		
		void permute(char[] s, int l, char ch) {
			if (l == s.length) {
				set.add(new String(s) + (ch == 0 ? "" : ch) + new StringBuffer(new String(s)).reverse());
			} else {
				for (int i = l; i < s.length; i++) {
					if (s[l] != s[i] || l == i) {
						swap(s, l, i);
						permute(s, l + 1, ch);
						swap(s, l, i);
					}
				}
			}
		}
	}
	
	class Solution2 {
		public List<String> generatePalindromes(String s) {
			int odd = 0;
			String mid = "";
			List<String> res = new ArrayList<>();
			List<Character> list = new ArrayList<>();
			Map<Character, Integer> map = new HashMap<>();
			
			// step 1. build character count map and count odds
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
				odd += map.get(c) % 2 != 0 ? 1 : -1;
			}
			
			// cannot form any palindromic string
			if (odd > 1)
				return res;
			
			// step 2. add half count of each character to list
			for (Map.Entry<Character, Integer> entry : map.entrySet()) {
				char key = entry.getKey();
				int val = entry.getValue();
				
				if (val % 2 != 0)
					mid += key;
				
				for (int i = 0; i < val / 2; i++)
					list.add(key);
			}
			
			// step 3. generate all the permutations
			getPerm(list, mid, new boolean[list.size()], new StringBuilder(), res);
			
			return res;
		}
		
		// generate all unique permutation from list
		void getPerm(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> res) {
			if (sb.length() == list.size()) {
				// form the palindromic string
				res.add(sb.toString() + mid + sb.reverse().toString());
				sb.reverse();
				return;
			}
			
			for (int i = 0; i < list.size(); i++) {
				// avoid duplication
				if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1])
					continue;
				
				if (!used[i]) {
					used[i] = true;
					sb.append(list.get(i));
					// recursion
					getPerm(list, mid, used, sb, res);
					// backtracking
					used[i] = false;
					sb.deleteCharAt(sb.length() - 1);
				}
			}
		}
	}
}
