package algorithms.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Yangxiao Wang on 5/18/2019
 */
public class Longest_String_Chain {
	
	public static void main(String[] args) {
		Longest_String_Chain sc = new Longest_String_Chain();
		String[] x = {"sgtnz", "sgtz", "sgz", "ikrcyoglz", "ajelpkpx", "ajelpkpxm", "srqgtnz", "srqgotnz", "srgtnz",
		              "ijkrcyoglz"};
		sc.longestStrChain(x);
	}
	
	public int longestStrChain(String[] words) {
		int n = words.length;
		Arrays.sort(words, ((o1, o2) -> o1.length() - o2.length()));
		// System.out.println(Arrays.toString(words));
		int[] mm = new int[n];
		int max = 0;
		mm[0] = 1;
		for (int i = 1; i < n; i++) {
			mm[i] = 1;
			for (int j = 0; j < i; j++) {
				if (isPred(words[j], words[i])) {
					mm[i] = Math.max(mm[j] + 1, mm[i]);
				}
			}
			max = Math.max(mm[i], max);
		}
		// System.out.println(Arrays.toString(mm));
		return max;
	}
	
	public boolean isPred(String pred, String s) {
		if (pred.length() != s.length() - 1)
			return false;
		int n = s.length();
		for (int i = 0; i < n; i++) {
			StringBuilder sb = new StringBuilder(s);
			sb.deleteCharAt(i);
			if (sb.toString().equals(pred)) {
				return true;
			}
		}
		return false;
	}
	
	public int longestStrChain_hash(String[] words) {
		if (words == null || words.length == 0)
			return 0;
		int res = 0;
		Arrays.sort(words, (str1, str2) -> str1.length() - str2.length());
		HashMap<String, Integer> map = new HashMap();
		for (String word : words) {
			if (map.containsKey(word))
				continue;
			map.put(word, 1);
			for (int i = 0; i < word.length(); i++) {
				StringBuilder sb = new StringBuilder(word);
				sb.deleteCharAt(i);
				String next = sb.toString();
				if (map.containsKey(next) && map.get(next) + 1 > map.get(word)) {
					map.put(word, map.get(next) + 1);
				}
			}
			res = Math.max(map.get(word), res);
		}
		return res;
	}
}
