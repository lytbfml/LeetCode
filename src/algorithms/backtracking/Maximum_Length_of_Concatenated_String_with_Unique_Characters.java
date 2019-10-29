package algorithms.backtracking;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao on 10/26/2019.
 */
public class Maximum_Length_of_Concatenated_String_with_Unique_Characters {
	
	public static void main(String[] args) {
		Maximum_Length_of_Concatenated_String_with_Unique_Characters cs = new Maximum_Length_of_Concatenated_String_with_Unique_Characters();
		cs.maxLength(Arrays.asList("aa", "jb"));
	}
	
	public int maxLength(List<String> arr) {
		int n = arr.size();
		int[] mem = new int[n];
		int[] lens = new int[n];
		for (int i = 0; i < n; i++) {
			char[] cc = arr.get(i).toCharArray();
			lens[i] = cc.length;
			for (char c : cc) {
				if ((mem[i] & (1 << (c - 'a'))) != 0) {
					lens[i] = -1;
					break;
				}
				mem[i] |= (1 << (c - 'a'));
			}
		}
		return dfs(arr, 0, 0, mem, lens, 0);
	}
	
	private int dfs(List<String> arr, int i, int curStr, int[] mem, int[] lens, int curLen) {
		if (i == arr.size()) {
			return curLen;
		}
		
		if (lens[i] >= 0 && validate(mem[i], curStr)) {
			return Math.max(dfs(arr, i + 1, (curStr | mem[i]), mem, lens, curLen + lens[i]),
					dfs(arr, i + 1, curStr, mem, lens, curLen));
		}
		return dfs(arr, i + 1, curStr, mem, lens, curLen);
	}
	
	private boolean validate(int targetStr, int curStr) {
		if ((targetStr & curStr) == 0) {
			return true;
		}
		return false;
	}
}
